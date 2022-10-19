/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lk.gov.health.entity.Upload;

/**
 *
 * @author safrin
 */
@Stateless
public class UploadFacade extends AbstractFacade<Upload> {
    @PersistenceContext(unitName = "hmisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        if(em == null){}return em;
    }

    public UploadFacade() {
        super(Upload.class);
    }
    
}
