/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.data.lab;

import lk.gov.health.entity.lab.Investigation;

/**
 *
 * @author buddhika
 */
public class InvestigationWithCount {
    Investigation investigation;
    Long iiCount;

    public InvestigationWithCount(Investigation investigation, Long iiCount) {
        this.investigation = investigation;
        this.iiCount = iiCount;
    }

    public InvestigationWithCount() {
    }

    
    
    public Investigation getInvestigation() {
        return investigation;
    }

    public void setInvestigation(Investigation investigation) {
        this.investigation = investigation;
    }

    public Long getIiCount() {
        return iiCount;
    }

    public void setIiCount(Long iiCount) {
        this.iiCount = iiCount;
    }
    
}
