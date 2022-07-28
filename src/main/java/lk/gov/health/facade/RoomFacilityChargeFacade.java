/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.facade;

import lk.gov.health.data.inward.RoomFacility;
import lk.gov.health.entity.inward.RoomFacilityCharge;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Buddhika
 */
@Stateless
public class RoomFacilityChargeFacade extends AbstractFacade<RoomFacilityCharge> {
    @PersistenceContext(unitName = "hmisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        if(em == null){}return em;
    }

    public RoomFacilityChargeFacade() {
        super(RoomFacilityCharge.class);
    }
    
     public RoomFacility[] roomFacilityBySql(String sql) {
        Query q = getEntityManager().createQuery(sql);
        return (RoomFacility[]) q.getSingleResult();
    }
    
}
