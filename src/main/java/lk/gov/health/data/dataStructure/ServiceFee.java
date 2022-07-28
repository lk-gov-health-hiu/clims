/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.data.dataStructure;

import lk.gov.health.entity.ItemFee;
import lk.gov.health.entity.Service;
import java.util.List;

/**
 *
 * @author safrin
 */
public class ServiceFee {
    private Service service;
    private List<ItemFee> itemFees;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List<ItemFee> getItemFees() {
        return itemFees;
    }

    public void setItemFees(List<ItemFee> itemFees) {
        this.itemFees = itemFees;
    }
    
    
}
