/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.data.dataStructure;

import lk.gov.health.entity.Bill;
import lk.gov.health.entity.inward.AdmissionType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author safrin
 */
public class AdmissionTypeBills {

    private AdmissionType admissionType;
    private List<Bill> bills=new ArrayList<>();
    private double total;

    public AdmissionType getAdmissionType() {
        return admissionType;
    }

    public void setAdmissionType(AdmissionType admissionType) {
        this.admissionType = admissionType;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public double getTotal() {

        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
