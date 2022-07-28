/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.bean.common;

import lk.gov.health.entity.Department;
import lk.gov.health.entity.Institution;
import lk.gov.health.entity.Logins;
import lk.gov.health.facade.LoginsFacade;
import lk.gov.health.java.CommonFunctions;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.TemporalType;

/**
 *
 * @author Buddhika
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    Department department;
    Institution institution;
    Date fromDate;
    Date toDate;
    Logins longin;
    List<Logins> logins;
    @EJB
    LoginsFacade facade;

    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
    }

    public Date getFromDate() {
        if (fromDate == null) {
            fromDate = CommonFunctions.getStartOfDay();
        }
        return fromDate;
    }

    public LoginsFacade getFacade() {
        return facade;
    }

    public void setFacade(LoginsFacade facade) {
        this.facade = facade;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        if(toDate==null){
            toDate=CommonFunctions.getEndOfDay();
        }
        this.toDate = toDate;
    }

    public Logins getLongin() {
        return longin;
    }

    public void setLongin(Logins longin) {
        this.longin = longin;
    }

    public void fillLogins() {
        String sql;
        Map m = new HashMap();
        m.put("fromDate", fromDate);
        m.put("toDate", toDate);
        sql = "select l from Logins l where l.logedAt between :fromDate and :toDate or l.logoutAt between :fromDate and :toDate  order by l.logedAt, l.logoutAt";
        logins = getFacade().findBySQL(sql, m, TemporalType.TIMESTAMP);
    }

    
    public List<Logins> getLogins() {
        return logins;
    }

    public void setLogins(List<Logins> logins) {
        this.logins = logins;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }
}
