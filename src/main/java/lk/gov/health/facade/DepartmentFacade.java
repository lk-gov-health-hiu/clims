/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.facade;

import lk.gov.health.entity.Department;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author buddhika
 */
@Stateless
public class DepartmentFacade extends AbstractFacade<Department> {
    @PersistenceContext(unitName = "hmisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        if(em == null){}return em;
    }

    public DepartmentFacade() {
        super(Department.class);
    }
    
}
