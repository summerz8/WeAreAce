/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.MRP;

import Entity.Factory.FactoryProductAmountEntity;
import Entity.Factory.FactoryRetailProductAmountEntity;
import Entity.Factory.MRP.SalesForecastEntity;
import SessionBean.MRP.SalesForecastModuleLocal;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author apple
 */
@Named(value = "viewSalesForecastBean")
@RequestScoped
public class ViewSalesForecastBean {

    @EJB    
    private SalesForecastModuleLocal salesForecastModule;
//    
//    @ManagedProperty("#{salesForecastBean}")
//    private SalesForecastBean salesForecastBean;
            
//    private Long storeId;
//    private Calendar targetPeriod;
//    private Object product;
    private SalesForecastEntity salesForecast;
    private List<SalesForecastEntity> salesForecastList;
    private List<FactoryProductAmountEntity> factoryProductList;
    private List<FactoryRetailProductAmountEntity> factoryRetailProductList;
    
    public ViewSalesForecastBean() {
    }
    
 
    public void getSalesForecastList(Long storeId,Object product, Calendar targetPeriod){
        System.out.println("storeId:"+storeId+ "       targetPeriod:"+targetPeriod);
        salesForecastList = salesForecastModule.ListSalesForecast(storeId, null, targetPeriod);
        if(salesForecastList.get(0).getFactoryProductList().isEmpty())
        factoryRetailProductList=salesForecastList.get(0).getFactoryRetailProductList();
        else
        factoryProductList=salesForecastList.get(0).getFactoryProductList();
      
    }

    public String ViewSalesForecast(Long salesForecastId){

            salesForecast=salesForecastModule.GetSalesForecast(salesForecastId);
            factoryProductList=salesForecast.getFactoryProductList();
            factoryRetailProductList=salesForecast.getFactoryRetailProductList();
        return "MRPViewSalesForecast";
    }
    
    public SalesForecastModuleLocal getSalesForecastModule() {
        return salesForecastModule;
    }

    public void setSalesForecastModule(SalesForecastModuleLocal salesForecastModule) {
        this.salesForecastModule = salesForecastModule;
    }


    public List<SalesForecastEntity> getSalesForecastList() {
        return salesForecastList;
    }

    public void setSalesForecastList(List<SalesForecastEntity> salesForecastList) {
        this.salesForecastList = salesForecastList;
    }

    public List<FactoryProductAmountEntity> getFactoryProductList() {
        return factoryProductList;
    }

    public void setFactoryProductList(List<FactoryProductAmountEntity> factoryProductList) {
        this.factoryProductList = factoryProductList;
    }

    public List<FactoryRetailProductAmountEntity> getFactoryRetailProductList() {
        return factoryRetailProductList;
    }

    public void setFactoryRetailProductList(List<FactoryRetailProductAmountEntity> factoryRetailProductList) {
        this.factoryRetailProductList = factoryRetailProductList;
    }
    
    
    
}