/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.SCM;

import Entity.Factory.SCM.InboundMovementEntity;
import Entity.Factory.SCM.RawMaterialInFactoryUseMovementEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Yoky
 */
@Stateful
//within the same year
public class RawMaterialInventoryMonitoringModule implements RawMaterialInventoryMonitoringModuleLocal {

    @PersistenceContext(unitName = "IslandFurnitureERPSystem-ejbPU")
    private EntityManager em;

    @Override
    public List ViewWeeklyRawMaterialInventoryInFlow() {
        try {
            Calendar currentDate = new GregorianCalendar();
            List weeklyRawMaterialInventoryInFlow = new ArrayList();

            Query q = em.createQuery("SELECT i from InboundMovement i");
            for (Object o : q.getResultList()) {
                InboundMovementEntity inboundMovement = (InboundMovementEntity) o;
                if (inboundMovement.getFactoryRawMaterial() != null) {
                    if (inboundMovement.getCreationDate().get(java.util.Calendar.WEEK_OF_YEAR) == currentDate.get(java.util.Calendar.WEEK_OF_YEAR)) {
                        weeklyRawMaterialInventoryInFlow.add(inboundMovement);
                    }
                }
            }
            return weeklyRawMaterialInventoryInFlow;
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.RawMaterialInventoryMonitoringModule: recordInboundMovement(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List ViewWeeklyRawMaterialInventoryOutFlow() {
        try {
            Calendar currentDate = new GregorianCalendar();
            List weeklyRawMaterialInventoryOutFlow = new ArrayList();

            Query q = em.createQuery("SELECT r from RawMaterialInFactoryUseMovement r");
            for (Object o : q.getResultList()) {
                RawMaterialInFactoryUseMovementEntity rawMaterialInFactoryUseMovement = (RawMaterialInFactoryUseMovementEntity) o;
                if (rawMaterialInFactoryUseMovement.getCreationDate().get(java.util.Calendar.WEEK_OF_YEAR) == currentDate.get(java.util.Calendar.WEEK_OF_YEAR)) {
                    weeklyRawMaterialInventoryOutFlow.add(rawMaterialInFactoryUseMovement);
                }
            }
            return weeklyRawMaterialInventoryOutFlow;
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.RawMaterialInventoryMonitoringModule: recordInboundMovement(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }
}
