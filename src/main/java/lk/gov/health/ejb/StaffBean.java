/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lk.gov.health.ejb;

import lk.gov.health.entity.Staff;
import lk.gov.health.facade.StaffFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author pdhs
 */
@Stateless
public class StaffBean {

    @EJB
    StaffFacade facade;
    
    public void updateStaffCredit(Staff staff, double value) {
        if(staff==null || staff.getId()==null){
         //   ////System.out.println("Staff Null or Not previously persisted.");
            return;
        }
        staff.setAnnualWelfareUtilized(staff.getAnnualWelfareUtilized() + value);
        getFacade().edit(staff);
    }

    public StaffFacade getFacade() {
        return facade;
    }

    public void setFacade(StaffFacade facade) {
        this.facade = facade;
    }

    
    
    
    
}
