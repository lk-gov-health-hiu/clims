/*
 * MSc(Biomedical Informatics) Project
 *
 * Development and Implementation of a Web-based Combined Data Repository of
 Genealogical, Clinical, Laboratory and Genetic Data
 * and
 * a Set of Related Tools
 */
package lk.gov.health.bean.clinical;

import lk.gov.health.bean.common.SessionController;
import lk.gov.health.bean.common.UtilityController;
import lk.gov.health.data.SymanticType;
import lk.gov.health.entity.clinical.ClinicalFindingItem;
import lk.gov.health.facade.ClinicalFindingItemFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Dr. M. H. B. Ariyaratne, MBBS, PGIM Trainee for MSc(Biomedical
 * Informatics)
 */
@Named
@SessionScoped
public class TreatementController implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    SessionController sessionController;
    @EJB
    private ClinicalFindingItemFacade ejbFacade;
    List<ClinicalFindingItem > selectedItems;
    private ClinicalFindingItem current;
    private List<ClinicalFindingItem> items = null;
    List<ClinicalFindingItem> insItems =null;
    String selectText = "";

    public List<ClinicalFindingItem> completeTreatments(String qry) {
        List<ClinicalFindingItem> c;
        Map m = new HashMap();
        m.put("t", SymanticType.Pharmacologic_Substance);
        m.put("n", "%" + qry.toUpperCase() + "%");
        String sql;
        sql="select c from ClinicalFindingItem c where c.retired=false and upper(c.name) like :n and c.symanticType=:t order by c.name";
        c = getFacade().findBySQL(sql,m,10);
        if (c == null) {
            c = new ArrayList<>();
        }
        return c;
    }

    public List<ClinicalFindingItem> getSelectedItems() {
        Map m = new HashMap();
        m.put("t", SymanticType.Pharmacologic_Substance);
        m.put("n", "%" + getSelectText().toUpperCase() + "%");
        String sql;
        sql="select c from ClinicalFindingItem c where c.retired=false and upper(c.name) like :n and c.symanticType=:t order by c.name";
        selectedItems = getFacade().findBySQL(sql,m);
        return selectedItems;
    }

    public void prepareAdd() {
        current = new ClinicalFindingItem();
        current.setInstitution(sessionController.getInstitution());
        current.setSymanticType(SymanticType.Pharmacologic_Substance);
        //TODO:
    }

    public void setSelectedItems(List<ClinicalFindingItem> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public String getSelectText() {
        return selectText;
    }

    private void recreateModel() {
        items = null;
    }

    public void saveSelected() {
        current.setSymanticType(SymanticType.Pharmacologic_Substance);
        if (getCurrent().getId() != null && getCurrent().getId() > 0) {
            getFacade().edit(current);
            UtilityController.addSuccessMessage("Saved");
        } else {
            current.setCreatedAt(new Date());
            current.setCreater(getSessionController().getLoggedUser());
            getFacade().create(current);
            UtilityController.addSuccessMessage("Updates");
        }
        recreateModel();
        getItems();
    }

    public void setSelectText(String selectText) {
        this.selectText = selectText;
    }

    public ClinicalFindingItemFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(ClinicalFindingItemFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    public TreatementController() {
    }

    public ClinicalFindingItem getCurrent() {
        if (current == null) {
            current = new ClinicalFindingItem();
        }
        return current;
    }

    public void setCurrent(ClinicalFindingItem current) {
        this.current = current;
    }

    public void delete() {

        if (current != null) {
            current.setRetired(true);
            current.setRetiredAt(new Date());
            current.setRetirer(getSessionController().getLoggedUser());
            getFacade().edit(current);
            UtilityController.addSuccessMessage("Deleted Successfully");
        } else {
            UtilityController.addSuccessMessage("Nothing to Delete");
        }
        recreateModel();
        getItems();
        current = null;
        getCurrent();
    }

    private ClinicalFindingItemFacade getFacade() {
        return ejbFacade;
    }

    public List<ClinicalFindingItem> getItems() {
        if (items == null) {
            Map m = new HashMap();
            m.put("t", SymanticType.Pharmacologic_Substance);
            String sql;
            sql = "select c from ClinicalFindingItem c where c.retired=false and c.symanticType=:t order by c.name";
            items = getFacade().findBySQL(sql, m);
        }
        return items;
    }

    public List<ClinicalFindingItem> getInsItems() {
        if (insItems == null) {
            Map m = new HashMap();
            m.put("t", SymanticType.Pharmacologic_Substance);
            m.put("ins", sessionController.getInstitution());
            String sql;
            sql = "select c "
                    + " from ClinicalFindingItem c "
                    + " where c.retired=false "
                    + " and c.symanticType=:t "
                    + " and c.institution=:ins "
                    + " order by c.name";
            insItems = getFacade().findBySQL(sql, m);
        }
        return insItems;
    }

    public void setInsItems(List<ClinicalFindingItem> insItems) {
        this.insItems = insItems;
    }
    
    
}
