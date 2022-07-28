/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.data;

/**
 *
 * @author Buddhika
 */
public enum DepartmentType {
    Pharmacy,
    Lab,
    Store,
    Theatre,
    Kitchen,
    Opd,
    Inventry,
    Inward;
    
    public String getLabel(){
        return this.toString();
    }
}
