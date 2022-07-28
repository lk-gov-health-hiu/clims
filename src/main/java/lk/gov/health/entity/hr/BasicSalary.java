/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.entity.hr;

import lk.gov.health.data.hr.PaysheetComponentType;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Buddhika
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@XmlRootElement
public class BasicSalary extends PaysheetComponent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public PaysheetComponentType getComponentType() {
        return PaysheetComponentType.BasicSalary;
    }

    @Override
    public void setComponentType(PaysheetComponentType componentType) {
        this.componentType = PaysheetComponentType.BasicSalary;
    }
}
