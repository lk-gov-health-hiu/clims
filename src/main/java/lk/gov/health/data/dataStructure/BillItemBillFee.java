/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lk.gov.health.data.dataStructure;

import lk.gov.health.entity.BillFee;
import lk.gov.health.entity.BillItem;
import java.util.List;

/**
 *
 * @author safrin
 */
public class BillItemBillFee {
    private BillItem billItem;
    private List<BillFee> billFees;

    public BillItem getBillItem() {
        return billItem;
    }

    public void setBillItem(BillItem billItem) {
        this.billItem = billItem;
    }

    public List<BillFee> getBillFees() {
        return billFees;
    }

    public void setBillFees(List<BillFee> billFees) {
        this.billFees = billFees;
    }
}
