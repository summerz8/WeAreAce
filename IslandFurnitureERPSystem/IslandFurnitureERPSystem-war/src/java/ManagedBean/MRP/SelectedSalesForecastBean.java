/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.MRP;

import Entity.Factory.MRP.SalesForecastEntity;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "selectedSalesForecastBean")
@ViewScoped
public class SelectedSalesForecastBean {

    private List<SalesForecastEntity> salesForecastList;

    public SelectedSalesForecastBean() {
    }

    @PostConstruct
    public void init() {
        salesForecastList=(List<SalesForecastEntity>)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("salesForecastList", salesForecastList);

    }

    public List<SalesForecastEntity> getSalesForecastList() {
        return salesForecastList;
    }

    public void setSalesForecastList(List<SalesForecastEntity> salesForecastList) {
        this.salesForecastList = salesForecastList;
    }
    
    
    
}


