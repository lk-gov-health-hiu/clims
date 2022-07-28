/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.facade;

import lk.gov.health.entity.Channeling;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author buddhika
 */
@Stateless
public class ChannelingFacade extends AbstractFacade<Channeling> {
    @PersistenceContext(unitName = "hmisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        if(em == null){}return em;
    }

    public ChannelingFacade() {
        super(Channeling.class);
    }
    
}
