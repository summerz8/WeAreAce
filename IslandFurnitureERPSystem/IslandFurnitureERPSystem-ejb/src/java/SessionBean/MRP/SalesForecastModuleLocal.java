/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.MRP;

import Entity.Factory.MRP.IntegratedSalesForecastEntity;
import Entity.Factory.MRP.SalesForecastEntity;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author apple
 */
@Local
public interface SalesForecastModuleLocal {
    public List<SalesForecastEntity> ListSalesForecast(Long storeId, Object product, Calendar period);
    
    public IntegratedSalesForecastEntity IntegrateSalesForecast(Object product, Calendar period);
}
