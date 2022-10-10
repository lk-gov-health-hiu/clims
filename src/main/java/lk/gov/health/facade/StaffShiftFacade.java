/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lk.gov.health.facade;

import lk.gov.health.entity.hr.StaffShift;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;





/**
 *
 * @author safrin
 */
@Stateless
public class StaffShiftFacade extends AbstractFacade<StaffShift> {
    @PersistenceContext(unitName = "hmisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        if(em == null){}return em;
    }

    public StaffShiftFacade() {
        super(StaffShift.class);
    }
    
}