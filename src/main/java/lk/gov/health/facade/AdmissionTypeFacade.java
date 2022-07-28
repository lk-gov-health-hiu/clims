/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.facade;

import lk.gov.health.entity.inward.AdmissionType;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author www.divudi.com
 */
@Stateless
public class AdmissionTypeFacade extends AbstractFacade<AdmissionType> {
    @PersistenceContext(unitName = "hmisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        if(em == null){}return em;
    }

    public AdmissionTypeFacade() {
        super(AdmissionType.class);
    }
}
