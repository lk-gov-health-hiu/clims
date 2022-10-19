/*
 * MSc(Biomedical Informatics) Project
 *
 * Development and Implementation of a Web-based Combined Data Repository of
 Genealogical, Clinical, Laboratory and Genetic Data
 * and
 * a Set of Related Tools
 */
package lk.gov.health.bean.common;

import java.io.IOException;
import java.io.InputStream;
import lk.gov.health.data.Dashboard;
import lk.gov.health.data.Privileges;
import lk.gov.health.entity.Department;
import lk.gov.health.entity.Institution;
import lk.gov.health.entity.Person;
import lk.gov.health.entity.Speciality;
import lk.gov.health.entity.Staff;
import lk.gov.health.entity.WebUser;
import lk.gov.health.entity.WebUserDashboard;
import lk.gov.health.entity.WebUserPrivilege;
import lk.gov.health.facade.DepartmentFacade;
import lk.gov.health.facade.InstitutionFacade;
import lk.gov.health.facade.PersonFacade;
import lk.gov.health.facade.StaffFacade;
import lk.gov.health.facade.WebUserDashboardFacade;
import lk.gov.health.facade.WebUserFacade;
import lk.gov.health.facade.WebUserPrivilegeFacade;
import lk.gov.health.facade.WebUserRoleFacade;
import lk.gov.health.facade.util.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
import lk.gov.health.data.UploadType;
import lk.gov.health.entity.Upload;
import lk.gov.health.facade.UploadFacade;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author Dr. M. H. B. Ariyaratne, MBBS, PGIM Trainee for MSc(Biomedical
 * Informatics)
 */
@Named
@SessionScoped
public class WebUserController implements Serializable {

    /**
     * EJBs
     */
    @EJB
    private WebUserFacade ejbFacade;
    @EJB
    WebUserRoleFacade roleFacade;
    @EJB
    private PersonFacade personFacade;
    @EJB
    private WebUserPrivilegeFacade webUserPrevilageFacade;
    @EJB
    private StaffFacade staffFacade;
    @EJB
    private WebUserDashboardFacade webUserDashboardFacade;
    @EJB
    private UploadFacade uploadFacade;
    /**
     * Controllers
     */
    @Inject
    SessionController sessionController;
    @Inject
    SecurityController securityController;
    @Inject
    private UserPaymentSchemeController userPaymentSchemeController;
    @Inject
    private UserDepartmentController userDepartmentController;

    @Inject
    private UserPrivilageController userPrivilageController;
    /**
     * Class Variables
     */
    private UploadedFile file;
    List<WebUser> items;
    List<WebUser> searchItems;
    private WebUser current;
    private WebUser selected;
    String selectText = "";
    List<Department> departments;
    List<Institution> institutions;
    @EJB
    private DepartmentFacade departmentFacade;
    @EJB
    private InstitutionFacade institutionFacade;
    private Institution institution;
    private Department department;
    private Privileges[] currentPrivilegeses;
    Speciality speciality;
    List<WebUserPrivilege> userPrivileges;
    private List<WebUser> webUsers;
    List<WebUser> itemsToRemove;

    private String newPassword;
    private String newPasswordConfirm;

    private Dashboard dashboard;
    private WebUserDashboard webUserDashboard;
    private List<WebUserDashboard> webUserDashboards;

    public void removeSelectedItems() {
        for (WebUser s : itemsToRemove) {
            s.setRetired(true);
            s.setRetireComments("Bulk Remove");
            s.setRetirer(getSessionController().getLoggedUser());
            try {
                getFacade().edit(s);
            } catch (Exception e) {
            }
        }
        itemsToRemove = null;
        items = null;
    }

    public void updateWebUser(WebUser webUser) {
        personFacade.edit(webUser.getWebUserPerson());
        staffFacade.edit(webUser.getStaff());
    }

    public List<Department> getInstitutionDepatrments() {
        List<Department> d;
        if (getInstitution() == null) {
            return new ArrayList<>();
        } else {
            String sql = "Select d From Department d where d.retired=false and d.institution.id=" + getInstitution().getId();
            d = getDepartmentFacade().findBySQL(sql);
        }

        return d;
    }

    public void saveUser() {
        if (current == null) {
            return;
        }
        if (current.getId() == null || current.getId() == 0) {
            getFacade().create(current);
            UtilityController.addSuccessMessage("Saved");
        } else {
            getFacade().edit(current);
            UtilityController.addSuccessMessage("Updated");
        }
    }

    public void removeUser() {

        if (selected == null) {
            UtilityController.addErrorMessage("Select a user to remove");
            return;
        }
        selected.getWebUserPerson().setRetired(true);
        selected.getWebUserPerson().setRetirer(getSessionController().getLoggedUser());
        selected.getWebUserPerson().setRetiredAt(Calendar.getInstance().getTime());
        getPersonFacade().edit(selected.getWebUserPerson());

        selected.setName(selected.getId().toString());
        selected.setRetired(true);
        selected.setRetirer(getSessionController().getLoggedUser());
        selected.setRetiredAt(Calendar.getInstance().getTime());
        //getFacade().edit(removingUser);
        getFacade().edit(selected);
        UtilityController.addErrorMessage("User Removed");
    }

    public List<WebUser> completeUser(String qry) {
        List<WebUser> a = null;
        if (qry != null) {
            a = getFacade().findBySQL("select c from WebUser c where c.retired=false and  (upper(c.webUserPerson.name) like '%" + qry.toUpperCase() + "%' or upper(c.code) like '%" + qry.toUpperCase() + "%') order by c.webUserPerson.name");
        }
        if (a == null) {
            a = new ArrayList<>();
        }
        return a;
    }

    public void setUserPrivileges(List<WebUserPrivilege> userPrivileges) {
        this.userPrivileges = userPrivileges;
    }

    public StaffFacade getStaffFacade() {
        return staffFacade;
    }

    public void setStaffFacade(StaffFacade staffFacade) {
        this.staffFacade = staffFacade;
    }

    public boolean hasPrivilege(String privilege) {
        boolean hasPri = false;
        if (getSessionController().getLoggedUser() == null) {
            return hasPri;
        }

        if (getSessionController().getLoggedUser().getId() == null) {
            return hasPri;
        }

        for (WebUserPrivilege w : getSessionController().getUserPrivileges()) {
            Privileges p = null;
            try {
                p = Privileges.valueOf(privilege);
            } catch (Exception e) {
                hasPri = false;
                return hasPri;
            }
            if (w.getPrivilege() != null && w.getPrivilege().equals(p)) {

                hasPri = true;
                return hasPri;
            }
        }
        return hasPri;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public List<Department> getDepartments() {
        if (departments == null) {
            String sql;
            if (getInstitution() != null && getInstitution().getId() != null) {
                sql = "select d from Department d where d.retired=false and d.institution.id = " + getInstitution().getId();
                departments = getDepartmentFacade().findBySQL(sql);
            }
        }
        if (departments == null) {
            departments = new ArrayList<>();
        }
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Institution> getInstitutions() {
        if (institutions == null) {
            String sql;
            sql = "select i from Institution i where i.retired=false order by i.name";
            institutions = getInstitutionFacade().findBySQL(sql);
        }
        return institutions;
    }

    public void setInstitutions(List<Institution> institutions) {
        this.institutions = institutions;
        departments = null; // This line is essential. Othervice departments will not be refreshed when institution is changed

    }
    boolean skip;

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public WebUserController() {
    }

    public List<WebUser> getItems() {
        if (items == null) {
            items = getFacade().findBySQL("Select d From WebUser d where d.retired = false order by d.webUserPerson.name");
            dycryptName();
        }

        return items;
    }

    private void dycryptName() {
        List<WebUser> temp = items;

        for (int i = 0; i < temp.size(); i++) {
            WebUser w = temp.get(i);
            w.setName((w.getName()).toLowerCase());
            temp.set(i, w);
        }

        items = temp;
    }

    public void setItems(List<WebUser> items) {
        this.items = items;
    }

    public WebUser getCurrent() {
        if (current == null) {
            current = new WebUser();
            Person p = new Person();
            current.setWebUserPerson(p);
        }
        return current;
    }

    public void setCurrent(WebUser current) {

        this.current = current;
    }

    private WebUserFacade getFacade() {
        return ejbFacade;
    }

    private void recreateModel() {
        items = null;
    }

    public void prepareAdd() {
        current = new WebUser();
    }

    public String toAddNewUser() {
        setCurrent(new WebUser());
        Person p = new Person();
        getCurrent().setWebUserPerson(p);
        setSpeciality(null);
        currentPrivilegeses = null;
        department = null;
        institution = null;
        return "/admin_add_new_user";
    }

    public SecurityController getSecurityController() {
        return securityController;
    }

    public void setSecurityController(SecurityController securityController) {
        this.securityController = securityController;
    }

    public Boolean userNameAvailable(String userName) {
        boolean available = false;
        String j;
        j = "select w from WebUser w where w.retired=false";
        List<WebUser> allUsers = getFacade().findBySQL(j);
        if (allUsers == null) {
            return false;
        }
        for (WebUser w : allUsers) {

            if (userName != null && w != null && w.getName() != null) {
                if (userName.toLowerCase().equals((w.getName()).toLowerCase())) {
                    //////System.out.println("Ift");
                    available = true;
                    return available;// ok. that is may be the issue. we will try with it ok
                }
            }
        }
        return available;
    }

    public String saveNewUser() {
        if (current == null) {
            UtilityController.addErrorMessage("Nothing to save");
            return "";
        }

        if (userNameAvailable(getCurrent().getName())) {
            UtilityController.addErrorMessage("User name already exists. Plese enter another user name");
            return "";
        }

        getCurrent().setActivated(true);
        getCurrent().setActivatedAt(new Date());
        getCurrent().setActivator(getSessionController().getLoggedUser());

        //Save Person
        getCurrent().getWebUserPerson().setCreatedAt(new Date());
        getCurrent().getWebUserPerson().setCreater(getSessionController().getLoggedUser());
        getPersonFacade().create(getCurrent().getWebUserPerson());

        getCurrent().getWebUserPerson().setName(getStaff().getPerson().getName());
        getCurrent().getWebUserPerson().setAddress(getStaff().getPerson().getAddress());
        getCurrent().getWebUserPerson().setMobile(getStaff().getPerson().getMobile());
        getPersonFacade().edit(getCurrent().getWebUserPerson());
        getCurrent().setCode(getStaff().getCode());
        getCurrent().setStaff(getStaff());

        getCurrent().setInstitution(sessionController.getInstitution());
        getCurrent().setDepartment(sessionController.getDepartment());

        getCurrent().setCreatedAt(new Date());
        getCurrent().setCreater(sessionController.loggedUser);
        getCurrent().setName((getCurrent().getName()));
        getCurrent().setWebUserPassword(getSecurityController().hash(getCurrent().getWebUserPassword()));
        getFacade().create(getCurrent());
        UtilityController.addSuccessMessage("Add New User Only");
        recreateModel();
        toAddNewUser();
        selectText = "";
        return backToAdminManageUsers();
    }

    public void onlyAddStaffListner() {
        createOnlyUserForExsistingUser = false;
    }

    public void onlyAddStaffForExsistingUserListner() {
        createOnlyUser = false;
    }

    public List<WebUser> getToApproveUsers() {
        String temSQL;
        temSQL = "SELECT u FROM WebUser u WHERE u.retired=false AND u.activated=false";
        return getEjbFacade().findBySQL(temSQL);
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    public WebUserFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(WebUserFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public WebUserRoleFacade getRoleFacade() {
        return roleFacade;
    }

    public void setRoleFacade(WebUserRoleFacade roleFacade) {
        this.roleFacade = roleFacade;
    }

    public List<WebUser> getSearchItems() {
        if (searchItems == null) {
            if (selectText.equals("")) {
                searchItems = getFacade().findAll("name", true);
            } else {
                searchItems = getFacade().findAll("name", "%" + selectText + "%",
                        true);
                if (searchItems.size() > 0) {
                    current = searchItems.get(0);
                } else {
                    current = null;
                }
            }
        }
        return searchItems;
    }

    public void setSearchItems(List<WebUser> searchItems) {
        this.searchItems = searchItems;
    }

    public void delete() {
        if (current != null) {
            current.setRetired(true);
            current.setRetiredAt(new Date());
            current.setRetirer(sessionController.loggedUser);
            getFacade().edit(current);
            UtilityController.addSuccessMessage("Deleted Successful");
        } else {
            UtilityController.addErrorMessage("Nothing To Delete");
        }
        recreateModel();
        getItems();
        current = null;
    }

    public void createTable() {

        if (selectText.trim().equals("")) {
            items = getFacade().findBySQL("select c from WebUser c where c.retired=false order by c.webUserPerson.name");
        } else {
            items = getFacade().findBySQL("select c from WebUser c where c.retired=false and upper(c.webUserPerson.name) like '%" + getSelectText().toUpperCase() + "%' order by c.webUserPerson.name");
        }
        dycryptName();
    }

    public List<WebUser> getSelectedItems() {

        return items;
    }

    public String getSelectText() {
        return selectText;
    }

    public void setSelectText(String selectText) {
        this.selectText = selectText;
    }

    public DepartmentFacade getDepartmentFacade() {
        return departmentFacade;
    }

    public void setDepartmentFacade(DepartmentFacade departmentFacade) {
        this.departmentFacade = departmentFacade;
    }

    public InstitutionFacade getInstitutionFacade() {
        return institutionFacade;
    }

    public void setInstitutionFacade(InstitutionFacade institutionFacade) {
        this.institutionFacade = institutionFacade;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        departments = null;
        this.institution = institution;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Privileges[] getPrivilegeses() {
        return Privileges.values();
    }

    public PersonFacade getPersonFacade() {
        return personFacade;
    }

    public void setPersonFacade(PersonFacade personFacade) {
        this.personFacade = personFacade;
    }

    public WebUserPrivilegeFacade getWebUserPrevilageFacade() {
        return webUserPrevilageFacade;
    }

    public void setWebUserPrevilageFacade(WebUserPrivilegeFacade webUserPrevilageFacade) {
        this.webUserPrevilageFacade = webUserPrevilageFacade;
    }

    public Privileges[] getCurrentPrivilegeses() {
        return currentPrivilegeses;
    }

    public void setCurrentPrivilegeses(Privileges[] currentPrivilegeses) {
        this.currentPrivilegeses = currentPrivilegeses;
    }

    public List<WebUser> getWebUsers() {
        return webUsers;
    }

    public void setWebUsers(List<WebUser> webUsers) {
        this.webUsers = webUsers;
    }

    public List<WebUser> getItemsToRemove() {
        return itemsToRemove;
    }

    public void setItemsToRemove(List<WebUser> itemsToRemove) {
        this.itemsToRemove = itemsToRemove;
    }

    public WebUser getSelected() {
        return selected;
    }

    public void setSelected(WebUser selected) {
        this.selected = selected;
    }

    public String toManageUser() {
        if (selected == null) {
            JsfUtil.addErrorMessage("Please select a user");
            return "";
        }
        current = selected;
        return "/admin_user";
    }

    public String toManageUsers() {
        return "/admin_view_user";
    }

    public String toManageUserSignature() {
        return "/admin_user_signature";
    }

    public String toManageStaff() {
        if (selected == null) {
            JsfUtil.addErrorMessage("Please select a user");
            return "";
        }

        return "/hr/hr_staff_admin";
    }

    public String toManagePassword() {
        if (selected == null) {
            JsfUtil.addErrorMessage("Please select a user");
            return "";
        }
        current = selected;
        return "/admin_change_password";
    }

    public String toManagePrivileges() {
        if (selected == null) {
            JsfUtil.addErrorMessage("Please select a user");
            return "";
        }
        getUserPrivilageController().setCurrentWebUser(selected);
        getUserPrivilageController().createSelectedPrivilegesForUser();
        return "/admin_user_privilages";
    }

    public String toManagePaymentSchemes() {
        if (selected == null) {
            JsfUtil.addErrorMessage("Please select a user");
            return "";
        }
        getUserPaymentSchemeController().setSelectedUser(selected);
        return "/admin_user_paymentScheme";
    }

    public String toManageSignature() {
        if (selected == null) {
            JsfUtil.addErrorMessage("Please select a user");
            return "";
        }
        return "/admin_user_signature";
    }

    public String toManageDepartments() {
        if (selected == null) {
            JsfUtil.addErrorMessage("Please select a user");
            return "";
        }
        getUserDepartmentController().setSelectedUser(selected);
        return "/admin_user_department";
    }

    public String toManageDashboards() {
        if (selected == null) {
            JsfUtil.addErrorMessage("Please select a user");
            return "";
        }
        current = selected;
        listWebUserDashboards();
        return "/admin_manage_dashboards";
    }

    public String backToAdminManageUsers() {
        return "/admin_manage_users";
    }

    public void addWebUserDashboard() {
        if (current == null) {
            JsfUtil.addErrorMessage("User ?");
            return;
        }
        if (current == null) {
            JsfUtil.addErrorMessage("Dashboard ?");
            return;
        }
        WebUserDashboard d = new WebUserDashboard();
        d.setWebUser(selected);
        d.setDashboard(dashboard);
        d.setCreatedAt(new Date());
        d.setCreater(sessionController.getLoggedUser());
        getWebUserDashboardFacade().create(d);
        JsfUtil.addSuccessMessage("Added");
        listWebUserDashboards();
    }

    public void removeWebUserDashboard() {
        if (webUserDashboard == null) {
            JsfUtil.addErrorMessage("Dashboard ?");
            return;
        }
        WebUserDashboard d = webUserDashboard;
        d.setRetired(true);
        d.setRetiredAt(new Date());
        d.setRetirer(sessionController.getLoggedUser());
        getWebUserDashboardFacade().edit(d);
        JsfUtil.addSuccessMessage("Removed");
        listWebUserDashboards();
    }

    public List<WebUserDashboard> listWebUserDashboards(WebUser wu) {
        List<WebUserDashboard> wuds = new ArrayList<>();
        if (wu == null) {
            return wuds;
        }
        String j = "Select d from WebUserDashboard d "
                + " where d.retired=false "
                + " and d.webUser=:u "
                + " order by d.id";
        Map m = new HashMap();
        m.put("u", wu);
        wuds = getWebUserDashboardFacade().findBySQL(j, m);
        return wuds;
    }

    public void listWebUserDashboards() {
        webUserDashboards = listWebUserDashboards(current);
    }

    public String backToViewUsers() {
        return "/admin_view_user";
    }

    public String changeCurrentUserPassword() {
        if (getCurrent() == null) {
            UtilityController.addErrorMessage("Select a User");
            return "";
        }
        if (!newPassword.equals(newPasswordConfirm)) {
            UtilityController.addErrorMessage("Password and Re-entered password are not maching");
            return "";
        }

        current.setWebUserPassword(getSecurityController().hash(newPassword));
        getFacade().edit(current);
        UtilityController.addSuccessMessage("Password changed");
        return "/admin_manage_users";
    }

    public UserPaymentSchemeController getUserPaymentSchemeController() {
        return userPaymentSchemeController;
    }

    public UserDepartmentController getUserDepartmentController() {
        return userDepartmentController;
    }

    public Upload findUserSignature(WebUser u) {
        String j = "select u "
                + " from Upload u "
                + " where u.retired=:ret "
                + " and u.webUser=:wu "
                + " and u.uploadType=:ut "
                + " order by u.id desc";
        Map m = new HashMap();
        m.put("wu", u);
        m.put("ret", false);
        m.put("ut", UploadType.User_Signature);
        return uploadFacade.findFirstBySQL(j, m);
    }

    private void saveUpload(Upload up) {
        if (up == null) {
            return;
        }
        if (up.getId() == null) {
            up.setCreatedAt(new Date());
            up.setCreater(sessionController.getLoggedUser());
            uploadFacade.create(up);
        } else {
            uploadFacade.edit(up);
        }
    }

    public String uploadUserSignature() {
        if (selected == null) {
            JsfUtil.addErrorMessage("No User");
            return "";
        }
        if (file == null) {
            JsfUtil.addErrorMessage("No file");
            return "";
        }
        Upload u = findUserSignature(selected);

        try {
            InputStream input = file.getInputStream();
            byte[] bytes = IOUtils.toByteArray(input);
            if (u == null) {
                u = new Upload();
                u.setWebUser(selected);
                u.setUploadType(UploadType.User_Signature);
                u.setInstitution(sessionController.getInstitution());
            }
            u.setBaImage(bytes);
            u.setFileName(file.getFileName());
            u.setFileType(file.getContentType());
            saveUpload(u);
            JsfUtil.addSuccessMessage("Signature Saved");
        } catch (IOException ex) {
            System.out.println("ex = " + ex);
            JsfUtil.addSuccessMessage("Error. " + ex);
            return "";
        }
        return toManageUsers();
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordConfirm() {
        return newPasswordConfirm;
    }

    public void setNewPasswordConfirm(String newPasswordConfirm) {
        this.newPasswordConfirm = newPasswordConfirm;
    }

    public UserPrivilageController getUserPrivilageController() {
        return userPrivilageController;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    public WebUserDashboard getWebUserDashboard() {
        return webUserDashboard;
    }

    public void setWebUserDashboard(WebUserDashboard webUserDashboard) {
        this.webUserDashboard = webUserDashboard;
    }

    public List<WebUserDashboard> getWebUserDashboards() {
        return webUserDashboards;
    }

    public void setWebUserDashboards(List<WebUserDashboard> webUserDashboards) {
        this.webUserDashboards = webUserDashboards;
    }

    public WebUserDashboardFacade getWebUserDashboardFacade() {
        return webUserDashboardFacade;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    @FacesConverter("webUs")
    public static class WebUserControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            WebUserController controller = (WebUserController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "webUserController");
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
            if (object instanceof WebUser) {
                WebUser o = (WebUser) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type "
                        + object.getClass().getName() + "; expected type: " + WebUserController.class.getName());
            }
        }
    }
}
