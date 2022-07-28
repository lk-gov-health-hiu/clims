/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.entity.inward;

import lk.gov.health.entity.Item;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author Buddhika
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class TimedItem extends Item implements Serializable {
    private static final long serialVersionUID = 1L;


   
    
    
    
}
