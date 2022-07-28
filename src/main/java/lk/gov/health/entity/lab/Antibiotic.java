package lk.gov.health.entity.lab;

import lk.gov.health.entity.pharmacy.Vtm;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Inheritance;

/**
 *
 * @author Buddhika
 */
@Entity
@Inheritance
public class Antibiotic extends Vtm implements Serializable {
    
}
