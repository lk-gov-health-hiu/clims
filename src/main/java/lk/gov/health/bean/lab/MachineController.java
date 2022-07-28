/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lk.gov.health.bean.lab;

import lk.gov.health.bean.common.SessionController;
import lk.gov.health.bean.common.UtilityController;
import lk.gov.health.entity.Institution;
import lk.gov.health.entity.lab.Machine;
import lk.gov.health.facade.MachineFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
/**
 *
 * @author Sniper 619
 */
@Named
@SessionScoped
public class MachineController implements Serializable {

    @EJB
    MachineFacade ejbFacade;
    @Inject
    SessionController sessionController;
    Machine current;
    List<Machine>items;
    private Institution institution;
    private List<Machine> institutionMachines;
    
    public MachineController() {
    }
    
    
    
    
    
    public List<Machine> getItems() {
        items = getEjbFacade().findAll("name", true);
        return items;
    }
    
    public void prepareAdd() {
        current = new Machine();
    }
    
    public void delete() {

        if (current != null) {
            current.setRetired(true);
            current.setRetiredAt(new Date());
            current.setRetirer(getSessionController().getLoggedUser());
            getEjbFacade().edit(current);
            UtilityController.addSuccessMessage("Deleted Successfully");
        } else {
            UtilityController.addSuccessMessage("Nothing to Delete");
        }
        recreateModel();
        getItems();
        current = null;
        getCurrent();
    }
    
    private void recreateModel() {
        items = null;
    }
    
    public void saveSelected() {

        if (getCurrent().getId() != null && getCurrent().getId() > 0) {
            getEjbFacade().edit(current);
            UtilityController.addSuccessMessage("Updated Successfully.");
        } else {
            current.setCreatedAt(new Date());
            current.setCreater(getSessionController().getLoggedUser());
            getEjbFacade().create(current);
            UtilityController.addSuccessMessage("Saved Successfully");
        }
        recreateModel();
        getItems();
    }

    public MachineFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(MachineFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    public Machine getCurrent() {
        if (current==null) {
            current=new Machine();
        }
        return current;
    }

    public void setCurrent(Machine current) {
        this.current = current;
    }

    public List<Machine> getInstitutionMachines() {
        if(sessionController.getLoggedUser().getInstitution()!= institution){
            institutionMachines=null;
            institution = sessionController.getLoggedUser().getInstitution();
        }
        if(institutionMachines==null){
            String j = "select m from Machine m where m.institution=:ins order by m.name";
            Map m = new HashMap();
            m.put("ins", institution);
            institutionMachines = getEjbFacade().findBySQL(j, m);
        }
        return institutionMachines;
    }

    public void setInstitutionMachines(List<Machine> institutionMachines) {
        this.institutionMachines = institutionMachines;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }
    
    @FacesConverter(forClass = Machine.class)
    public static class MachineControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MachineController controller = (MachineController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "machineController");
            return controller.getEjbFacade().find(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Machine) {
                Machine o = (Machine) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type "
                        + object.getClass().getName() + "; expected type: " + MachineController.class.getName());
            }
        }
    }
}
