/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.MRP;

import Entity.Factory.BOMEntity;
import Entity.Factory.ProductEntity;
import SessionBean.MRP.WeeklyProductionPlanLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author apple
 */
@Named(value = "viewBOM")
@ViewScoped
public class ViewBOMBean {

    @EJB
    WeeklyProductionPlanLocal WPP;

    Long factoryProductId;
    Long ProductId;
    ProductEntity product;
    List<BOMEntity> bom;
    
    public ViewBOMBean() {
    }
    
    @PostConstruct
    public void getBOM(){
          factoryProductId=(Long)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("factoryProductId");
             product=WPP.getProduct(factoryProductId);
          bom=product.getBom();
          
          
    }

    public WeeklyProductionPlanLocal getWPP() {
        return WPP;
    }

    public void setWPP(WeeklyProductionPlanLocal WPP) {
        this.WPP = WPP;
    }

    public Long getFactoryProductId() {
        return factoryProductId;
    }

    public void setFactoryProductId(Long factoryProductId) {
        this.factoryProductId = factoryProductId;
    }

    public Long getProductId() {
        return ProductId;
    }

    public void setProductId(Long ProductId) {
        this.ProductId = ProductId;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public List<BOMEntity> getBom() {
        return bom;
    }

    public void setBom(List<BOMEntity> bom) {
        this.bom = bom;
    }
    
}
