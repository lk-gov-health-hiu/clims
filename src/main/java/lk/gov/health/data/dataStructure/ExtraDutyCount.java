/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lk.gov.health.data.dataStructure;

import lk.gov.health.data.hr.ExtraDutyType;

/**
 *
 * @author safrin
 */
public class ExtraDutyCount {
    private ExtraDutyType extraDutyType;
    private double count;

    public ExtraDutyType getExtraDutyType() {
        return extraDutyType;
    }

    public void setExtraDutyType(ExtraDutyType extraDutyType) {
        this.extraDutyType = extraDutyType;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }
}
