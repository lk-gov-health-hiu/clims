/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.entity;

import lk.gov.health.data.BillClassType;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author buddhika
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class CancelledBill extends Bill implements Serializable {

    public CancelledBill() {
        billClassType = BillClassType.CancelledBill;
        qty = 0 - 1;
    }

}
