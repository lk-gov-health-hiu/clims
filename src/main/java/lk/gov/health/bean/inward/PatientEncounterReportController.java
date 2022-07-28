/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.bean.inward;

import lk.gov.health.data.BillType;
import lk.gov.health.entity.BillItem;
import lk.gov.health.entity.PatientEncounter;
import lk.gov.health.facade.BillItemFacade;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Sniper
 */
@Named(value = "patientEncounterReportController")
@SessionScoped
public class PatientEncounterReportController implements Serializable {

    List<BillItem> billItem;
    PatientEncounter current;
    @EJB
    BillItemFacade billItemFacade;
    
    public void selectAllBillItems(){
//    BillItem bi = new BillItem();
//    bi.getBill().getPatientEncounter();
//    bi.getBill().getBillType();
    
        String sql;
        Map m = new HashMap();
        
        sql = "select b from BillItem b where b.bill.patientEncounter=:pNo and b.bill.billType=:bt and b.retired=false";
        
        m.put("pNo", current);
        m.put("bt", BillType.PharmacyBhtPre);
        billItem = getBillItemFacade().findBySQL(sql, m);
        
    }
    
    
    /**
     * Creates a new instance of PatientEncounterReportController
     */
    public PatientEncounterReportController() {
    }

    public List<BillItem> getBillItem() {
        return billItem;
    }

    public void setBillItem(List<BillItem> billItem) {
        this.billItem = billItem;
    }

    public PatientEncounter getCurrent() {
        return current;
    }

    public void setCurrent(PatientEncounter current) {
        this.current = current;
    }

    public BillItemFacade getBillItemFacade() {
        return billItemFacade;
    }

    public void setBillItemFacade(BillItemFacade billItemFacade) {
        this.billItemFacade = billItemFacade;
    }
    
    
}
