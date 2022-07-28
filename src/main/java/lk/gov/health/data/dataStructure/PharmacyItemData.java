/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.data.dataStructure;

import lk.gov.health.entity.BillItem;
import lk.gov.health.entity.Item;
import lk.gov.health.entity.pharmacy.PharmaceuticalBillItem;
import java.util.List;

/**
 *
 * @author safrin
 */
public class PharmacyItemData {

    private PharmaceuticalBillItem pharmaceuticalBillItem;    
    private List<Item> suggession;
    private BillItem billItem;
    private BillItem grnBillItem;
    private BillItem poBillItem;

    public PharmaceuticalBillItem getPharmaceuticalBillItem() {
        return pharmaceuticalBillItem;
    }

    public void setPharmaceuticalBillItem(PharmaceuticalBillItem pharmaceuticalBillItem) {
        this.pharmaceuticalBillItem = pharmaceuticalBillItem;
    }

    public List<Item> getSuggession() {
        return suggession;
    }

    public void setSuggession(List<Item> suggession) {
        this.suggession = suggession;
    }

    public BillItem getBillItem() {
        return billItem;
    }

    public void setBillItem(BillItem billItem) {
        this.billItem = billItem;
    }

    public BillItem getGrnBillItem() {
        return grnBillItem;
    }

    public void setGrnBillItem(BillItem grnBillItem) {
        this.grnBillItem = grnBillItem;
    }

    public BillItem getPoBillItem() {
        return poBillItem;
    }

    public void setPoBillItem(BillItem poBillItem) {
        this.poBillItem = poBillItem;
    }
}
