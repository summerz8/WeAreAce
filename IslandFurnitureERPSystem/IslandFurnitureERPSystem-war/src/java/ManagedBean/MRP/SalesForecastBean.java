/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.MRP;

import Entity.Factory.MRP.SalesForecastEntity;
import SessionBean.MRP.SalesForecastModuleLocal;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author apple
 */
@Named(value = "salesForecastBean")
@RequestScoped
public class SalesForecastBean {

@EJB
    private SalesForecastModuleLocal salesForecastModule;
    

    private List<SalesForecastEntity> salesForecastList;
    private Long storeId;
    private Calendar targetPeriod;
    

    public SalesForecastBean() {
    }
    
    @PostConstruct
    public void getAllSalesForecast(){
        try{
        salesForecastList = salesForecastModule.ListSalesForecast(null, null, null);
        }catch(Exception e){
            System.err.println("Caught an unexpected exception managedbean");
            e.printStackTrace();
        }
    
    }
    
    public List<SalesForecastEntity> getSalesForecast(){
        try{
        salesForecastList = salesForecastModule.ListSalesForecast(storeId, null, targetPeriod);
        }catch(Exception e){
            System.err.println("Caught an unexpected exception managedbean");
            e.printStackTrace();
        }
        return salesForecastList;
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
    
    public String ViewSalesForecast(Long salesForecastId){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("salesForecastId",salesForecastId);

        
        return "MRPViewSalesForecast?faces-redirect=true";
    }
}
