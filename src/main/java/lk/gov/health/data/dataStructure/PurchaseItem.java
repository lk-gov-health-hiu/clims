/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.data.dataStructure;

import lk.gov.health.entity.Item;
import lk.gov.health.entity.pharmacy.ItemBatch;
import lk.gov.health.entity.pharmacy.PharmaceuticalBillItem;

/**
 *
 * @author safrin
 */
public class PurchaseItem {

    private Item item;
    private PharmaceuticalBillItem pharmaceuticalBillItem;
    private ItemBatch itemBatch;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public PharmaceuticalBillItem getPharmaceuticalBillItem() {
        return pharmaceuticalBillItem;
    }

    public void setPharmaceuticalBillItem(PharmaceuticalBillItem pharmaceuticalBillItem) {
        this.pharmaceuticalBillItem = pharmaceuticalBillItem;
    }

    public ItemBatch getItemBatch() {
        return itemBatch;
    }

    public void setItemBatch(ItemBatch itemBatch) {
        this.itemBatch = itemBatch;
    }
}
