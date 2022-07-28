/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.data.dataStructure;

import lk.gov.health.entity.Item;
import lk.gov.health.entity.pharmacy.Reorder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Buddhika
 */
public class ItemReorders {
    Item item;
    List<Reorder> reorders;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Reorder> getReorders() {
        if(reorders==null){
            reorders= new ArrayList<>();
        }
        return reorders;
    }

    public void setReorders(List<Reorder> reorders) {
        this.reorders = reorders;
    }
    
    
    
}
