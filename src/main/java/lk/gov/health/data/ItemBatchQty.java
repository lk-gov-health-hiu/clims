/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.data;

import lk.gov.health.entity.pharmacy.ItemBatch;

/**
 *
 * @author Buddhika
 */
public class ItemBatchQty {
    
    ItemBatch itemBatch;
    double qty;

    public ItemBatch getItemBatch() {
        return itemBatch;
    }

    public void setItemBatch(ItemBatch itemBatch) {
        this.itemBatch = itemBatch;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public ItemBatchQty(ItemBatch itemBatch, double qty) {
        this.itemBatch = itemBatch;
        this.qty = qty;
    }
    
    
    
}
