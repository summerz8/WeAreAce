/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.MRP;

import Entity.Factory.FactoryProductEntity;
import SessionBean.MRP.WeeklyProductionPlanLocal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "searchBOM")
@ViewScoped
public class SearchBOMBean {

    @EJB
    WeeklyProductionPlanLocal wppl;
    Long factoryProductId;
    List<FactoryProductEntity> factoryProductList;
    Long factoryId;
    String Selectedfactory;
    List<SelectItem> displayList;

    public SearchBOMBean() {
    }

    @PostConstruct
    public void init() {
        factoryId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
        displayList = new ArrayList<>();
        factoryProductList = wppl.getFactoryProductList(factoryId);

        for (FactoryProductEntity f : factoryProductList) {
            String t = f.getFactoryProductId() + " " + f.getName();
            displayList.add(new SelectItem(f.getFactoryProductId(), t));
        }

    }

    public String goNext() {
        factoryProductId = Long.valueOf(Selectedfactory);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("factoryProductId", factoryProductId);
        return "ViewBOM?faces-redirect=true";

    }

    public Long getFactoryProductId() {
        return factoryProductId;
    }

    public void setFactoryProductId(Long factoryProductId) {
        this.factoryProductId = factoryProductId;
    }

    public WeeklyProductionPlanLocal getWppl() {
        return wppl;
    }

    public void setWppl(WeeklyProductionPlanLocal wppl) {
        this.wppl = wppl;
    }

    public List<FactoryProductEntity> getFactoryProductList() {
        return factoryProductList;
    }

    public void setFactoryProductList(List<FactoryProductEntity> factoryProductList) {
        this.factoryProductList = factoryProductList;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public String getSelectedfactory() {
        return Selectedfactory;
    }

    public void setSelectedfactory(String Selectedfactory) {
        this.Selectedfactory = Selectedfactory;
    }

    public List<SelectItem> getDisplayList() {
        return displayList;
    }

    public void setDisplayList(List<SelectItem> displayList) {
        this.displayList = displayList;
    }

}
