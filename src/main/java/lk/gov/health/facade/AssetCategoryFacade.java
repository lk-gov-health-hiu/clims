/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lk.gov.health.facade;

import lk.gov.health.entity.pharmacy.AssetCategory;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Buddhika
 */
@Stateless
public class AssetCategoryFacade extends AbstractFacade<AssetCategory> {
    @PersistenceContext(unitName = "hmisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        if(em == null){}return em;
    }

    public AssetCategoryFacade() {
        super(AssetCategory.class);
    }
    
}
