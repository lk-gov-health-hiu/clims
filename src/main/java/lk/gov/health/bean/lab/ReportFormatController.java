/*
 * MSc(Biomedical Informatics) Project
 *
 * Development and Implementation of a Web-based Combined Data Repository of
 Genealogical, Clinical, Laboratory and Genetic Data
 * and
 * a Set of Related Tools
 */
package lk.gov.health.bean.lab;

import lk.gov.health.bean.common.SessionController;
import lk.gov.health.bean.common.UtilityController;
import lk.gov.health.entity.lab.ReportFormat;
import lk.gov.health.facade.ReportFormatFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class ReportFormatController implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    SessionController sessionController;
    @EJB
    private ReportFormatFacade ejbFacade;
    List<ReportFormat> selectedItems;
    private ReportFormat current;
    private List<ReportFormat> items = null;
    String selectText = "";

    public List<ReportFormat> getSelectedItems() {
        selectedItems = getFacade().findBySQL("select c from ReportFormat c where c.retired=false and upper(c.name) like '%" + getSelectText().toUpperCase() + "%' order by c.name");
        return selectedItems;
    }

    public void prepareAdd() {
        current = new ReportFormat();
    }

    public void setSelectedItems(List<ReportFormat> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public String getSelectText() {
        return selectText;
    }

    private void recreateModel() {
        items = null;
    }

    public void saveSelected() {

        if (getCurrent().getId() != null && getCurrent().getId() > 0) {
            getFacade().edit(current);
            UtilityController.addSuccessMessage("Updated Successfully.");
        } else {
            current.setCreatedAt(new Date());
            current.setCreater(getSessionController().getLoggedUser());
            getFacade().create(current);
            UtilityController.addSuccessMessage("Saved Successfully");
        }
        recreateModel();
        getItems();
    }

    public void setSelectText(String selectText) {
        this.selectText = selectText;
    }

    public ReportFormatFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(ReportFormatFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    public ReportFormatController() {
    }

    public ReportFormat getCurrent() {
        if (current == null) {
            current = new ReportFormat();
        }
        return current;
    }

    public void setCurrent(ReportFormat current) {
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

    private ReportFormatFacade getFacade() {
        return ejbFacade;
    }

    public List<ReportFormat> getItems() {
        if (items == null) {
            String sql = "SELECT i FROM ReportFormat i where i.retired=false order by i.name";
            items = getEjbFacade().findBySQL(sql);
        }
        return items;
    }

    public void setItems(List<ReportFormat> items) {
        this.items = items;
    }
    
    
    
    /**
     *
     */
}
