/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.ejb;

import lk.gov.health.data.BillType;
import lk.gov.health.entity.Bill;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Buddhika
 */
@Stateless
public class EjbApplication {

    List<Bill> billsToCancel;
   
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public List<Bill> getBillsToCancel() {
        if(billsToCancel==null){
            billsToCancel=new ArrayList<>();
        }
        
        return billsToCancel;
    }
    
    public List<Bill> getOpdBillsToCancel(){
        List<Bill> tmp=new ArrayList<>();
        
        for(Bill cb:getBillsToCancel()){
            if(cb.getBillType()== BillType.OpdBill){
                tmp.add(cb);
            }
        }
        
        return tmp;
    
    }
    
     public List<Bill> getLabBillsToCancel(){
        List<Bill> tmp=new ArrayList<>();
        
        for(Bill cb:getBillsToCancel()){
            if(cb.getBillType()== BillType.LabBill){
                tmp.add(cb);
            }
        }
        
        return tmp;
    
    }

    public void setBillsToCancel(List<Bill> billsToCancel) {
        this.billsToCancel = billsToCancel;
    }

}
