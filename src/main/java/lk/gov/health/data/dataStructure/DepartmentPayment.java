/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.data.dataStructure;

import lk.gov.health.entity.Department;

/**
 *
 * @author safrin
 */
public class DepartmentPayment {
    private Department department;
    private double totalPayment;
    String specility;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getSpecility() {
        return specility;
    }

    public void setSpecility(String specility) {
        this.specility = specility;
    }
}
