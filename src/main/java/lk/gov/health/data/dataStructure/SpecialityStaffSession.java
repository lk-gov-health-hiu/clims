/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.data.dataStructure;

import lk.gov.health.entity.Speciality;
import java.util.List;

/**
 *
 * @author safrin
 */
public class SpecialityStaffSession {

    private Speciality speciality;
    private List<StaffSession> staffSessions;

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public List<StaffSession> getStaffSessions() {
        return staffSessions;
    }

    public void setStaffSessions(List<StaffSession> staffSessions) {
        this.staffSessions = staffSessions;
    }
}
