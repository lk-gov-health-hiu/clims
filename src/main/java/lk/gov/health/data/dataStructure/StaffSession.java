/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.data.dataStructure;

import lk.gov.health.entity.ServiceSession;
import lk.gov.health.entity.Staff;
import java.util.List;

/**
 *
 * @author safrin
 */
public class StaffSession {
    private Staff staff;
    private List<ServiceSession> serviceSession;

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public List<ServiceSession> getServiceSession() {
        return serviceSession;
    }

    public void setServiceSession(List<ServiceSession> serviceSession) {
        this.serviceSession = serviceSession;
    }
    
    
}
