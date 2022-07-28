/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.entity.membership;

import lk.gov.health.entity.PriceMatrix;
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
public class OpdMemberShipDiscount extends PriceMatrix implements Serializable {
    private static final long serialVersionUID = 1L;
    
}
