/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lk.gov.health.bean.common;

import lk.gov.health.entity.Patient;
import lk.gov.health.entity.Staff;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author root
 */
@Named(value = "transferController")
@SessionScoped
public class TransferController implements Serializable {

    
    Patient patient;
    Staff staff;
    
    /**
     * Creates a new instance of TransferController
     */
    public TransferController() {
    }

    public Patient getPatient() {
//        //////System.out.println("getting patient = " + patient);
        return patient;
    }

    public void setPatient(Patient patient) {
//        //////System.out.println("setting patient = " + patient);
        this.patient = patient;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
    
    
    
}
