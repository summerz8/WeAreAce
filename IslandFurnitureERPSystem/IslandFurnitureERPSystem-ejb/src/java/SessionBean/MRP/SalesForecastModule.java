/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

import Entity.Factory.MRP.SalesForecastEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author apple
 */
@Stateful
public class SalesForecastModule implements SalesForecastModuleLocal {

    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<SalesForecastEntity> ListSalesForecast(Long StoreId, Calendar Period) {
        List<SalesForecastEntity> list = new ArrayList<SalesForecastEntity>();
        List<SalesForecastEntity> templist = new ArrayList<SalesForecastEntity>();
        try {
            Query query = em.createQuery("SELECT t FROM SalesForecastEntity t ORDER BY t.targetPeriod DESC");
            templist = (List<SalesForecastEntity>) query.getResultList();
            if (StoreId != 0L && Period != null) {
                while (!templist.isEmpty()) {
                    if (StoreId == templist.get(0).getStore().getStoreId() && Period.equals(templist.get(0).getTargetPeriod())) {
                        list.add(templist.get(0));
                        templist.remove(0);
                    }
                }

            } else if (StoreId == 0L && Period != null) {
                while (!templist.isEmpty()) {
                    if (Period.equals(templist.get(0).getTargetPeriod())) {
                        list.add(templist.get(0));
                        templist.remove(0);
                    }
                }
            } else if (StoreId != 0L && Period == null) {
                while (!templist.isEmpty()) {
                    if (StoreId == templist.get(0).getStore().getStoreId()) {
                        list.add(templist.get(0));
                        templist.remove(0);
                    }
                }
            } else if (StoreId == 0L && Period == null) {
                return templist;
            }

            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }
    
    //Forecast Integration will be handled in managed bean
}
