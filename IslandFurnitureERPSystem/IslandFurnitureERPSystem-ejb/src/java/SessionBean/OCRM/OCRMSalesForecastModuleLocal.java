/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.OCRM;

import Entity.Factory.MRP.SalesForecastEntity;
import Entity.Store.OCRM.ProductSalesForecastEntity;
import Entity.Store.OCRM.SalesRecordEntity;
import Entity.Store.StoreProductEntity;
import Entity.Store.StoreRetailProductEntity;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author apple
 */
@Local
public interface OCRMSalesForecastModuleLocal {

    public List<StoreProductEntity> listStoreProduct(Long storeId);

    public List<StoreRetailProductEntity> listStoreRetailProduct(Long storeId);

    public List<SalesRecordEntity> listSalesRecord(Long productId, String productType);

    public List<ProductSalesForecastEntity> listProductSalesForecast(Long productId, String productType);

    public String checkStoreProduct(Long productId);

    public String checkStoreRetailProduct(Long productId);

    public ProductSalesForecastEntity createProductSalesForecast(Long productId, String productType);

    public ProductSalesForecastEntity confirmProductSalesForecast(Calendar period, Double NewAmount, Long storeId, String productType, Long productId);

    public Boolean checkAvailability(Long storeId);

    public List<SalesForecastEntity> createForecast(Long storeId);

    public SalesForecastEntity getSalesForecast(Long salesForecastId);

    public List<ProductSalesForecastEntity> SortProductSalesForecastList(List<ProductSalesForecastEntity> object);

    public List<SalesRecordEntity> SortSalesRecordList(List<SalesRecordEntity> object);

}
