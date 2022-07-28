/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.ejb;

import lk.gov.health.data.BillType;
import lk.gov.health.data.PaymentMethod;
import lk.gov.health.entity.BilledBill;
import lk.gov.health.entity.CancelledBill;
import lk.gov.health.entity.Department;
import lk.gov.health.entity.Institution;
import lk.gov.health.entity.RefundBill;
import lk.gov.health.facade.BillFacade;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TemporalType;

/**
 *
 * @author buddhika
 */
@Stateless
public class BillReportBean {

    @EJB
    BillFacade billFacade;

    public Long calulateRevenueBillItemCount(Date fd, Date td, PaymentMethod pm, Institution institution, Department department, BillType[] billTypes, Class bc) {
        String sql;
        Map m = new HashMap();
        sql = "select count(bi) "
                + " from BillItem bi "
                + " where bi.bill.retired=false "
                + " and bi.bill.billType in :billTypes "
                + " and bi.bill.createdAt between :fd and :td "
                + " and type(bi.bill) =:bc ";
        if (pm != null) {
            sql += " and bi.bill.paymentMethod=:pm ";
            m.put("pm", pm);
        }
        if (institution != null) {
            sql += " and bi.bill.toInstitution=:ins ";
            m.put("ins", institution);
        }
        
        if (department != null) {
            sql += " and bi.bill.toDepartment=:dep ";
            m.put("dep", department);
        }
        
        List<BillType> bts = Arrays.asList(billTypes);
        
        m.put("billTypes", bts);
        m.put("bc", bc);
        m.put("fd", fd);
        m.put("td", td);
        
        
        return billFacade.findLongByJpql(sql, m, TemporalType.TIMESTAMP);
    }

    public Long calulateRevenueBillItemCount(Date fd, Date td, PaymentMethod pm, Institution institution, Department department, BillType[] billTypes) {
        return calulateRevenueBillItemCount(fd, td, pm, institution, department, billTypes, BilledBill.class)
                - calulateRevenueBillItemCount(fd, td, pm, institution, department, billTypes, CancelledBill.class)
                - calulateRevenueBillItemCount(fd, td, pm, institution, department, billTypes, RefundBill.class);
    }

}
