/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.facade;

import lk.gov.health.entity.Sms;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sniper619
 */
@Stateless
public class SmsFacade extends AbstractFacade<Sms> {
    @PersistenceContext(unitName = "hmisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SmsFacade() {
        super(Sms.class);
    }
    
}
