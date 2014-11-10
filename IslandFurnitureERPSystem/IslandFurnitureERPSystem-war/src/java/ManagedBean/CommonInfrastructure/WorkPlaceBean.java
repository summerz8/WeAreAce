/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.CommonInfrastructure;

import Entity.Factory.FactoryEntity;
import Entity.Store.StoreEntity;
import SessionBean.CommonInFrastructure.WorkPlaceModuleLocal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

/**
 *
 * @author apple
 */
@Named(value = "workPlaceBean")
@ViewScoped
public class WorkPlaceBean {

    @EJB
    WorkPlaceModuleLocal wpml;

    private DashboardModel model;
    private Date date;
    private List<StoreEntity> storeList;
    private List<FactoryEntity> factoryList;
    private List<SelectItem> displayList;
    private String selectedStore;
    private String selectedStore1;
    private String selectedStore2;
    private String selectedFactory;
    private String selectedFactory1;
    private String selectedFactory2;

    @PostConstruct
    public void init() {

        date = Calendar.getInstance().getTime();
        model = new DefaultDashboardModel();
        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();
        DashboardColumn column3 = new DefaultDashboardColumn();

        column1.addWidget("message");
        column1.addWidget("ticket");
        column1.addWidget("calendar");

        column2.addWidget("product");
        column2.addWidget("kitchen");
        column2.addWidget("retailproduct");
        
        column3.addWidget("productf");
        column3.addWidget("rawMaterial");
        column3.addWidget("retailproductf");

        model.addColumn(column1);
        model.addColumn(column2);
        model.addColumn(column3);

        displayList = new ArrayList<>();
        storeList = wpml.listStores();
        factoryList = wpml.listFactory();

        for (StoreEntity s : storeList) {
            String t = s.getStoreId() + " " + s.getAddress();
            displayList.add(new SelectItem(s.getStoreId(), t));
        }
        
        for (FactoryEntity f : factoryList) {
            String t = f.getFactoryId() + " " + f.getAddress();
            displayList.add(new SelectItem(f.getFactoryId(), t));
        }
  

    }

    public DashboardModel getModel() {
        return model;
    }

    public void setModel(DashboardModel model) {
        this.model = model;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public WorkPlaceModuleLocal getWpml() {
        return wpml;
    }

    public void setWpml(WorkPlaceModuleLocal wpml) {
        this.wpml = wpml;
    }

    public List<StoreEntity> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<StoreEntity> storeList) {
        this.storeList = storeList;
    }

    public List<SelectItem> getDisplayList() {
        return displayList;
    }

    public void setDisplayList(List<SelectItem> displayList) {
        this.displayList = displayList;
    }

    public String getSelectedStore() {
        return selectedStore;
    }

    public void setSelectedStore(String selectedStore) {
        this.selectedStore = selectedStore;
    }

    public String getSelectedStore1() {
        return selectedStore1;
    }

    public void setSelectedStore1(String selectedStore1) {
        this.selectedStore1 = selectedStore1;
    }

    public String getSelectedStore2() {
        return selectedStore2;
    }

    public void setSelectedStore2(String selectedStore2) {
        this.selectedStore2 = selectedStore2;
    }

    public List<FactoryEntity> getFactoryList() {
        return factoryList;
    }

    public void setFactoryList(List<FactoryEntity> factoryList) {
        this.factoryList = factoryList;
    }

    public String getSelectedFactory() {
        return selectedFactory;
    }

    public void setSelectedFactory(String selectedFactory) {
        this.selectedFactory = selectedFactory;
    }

    public String getSelectedFactory1() {
        return selectedFactory1;
    }

    public void setSelectedFactory1(String selectedFactory1) {
        this.selectedFactory1 = selectedFactory1;
    }

    public String getSelectedFactory2() {
        return selectedFactory2;
    }

    public void setSelectedFactory2(String selectedFactory2) {
        this.selectedFactory2 = selectedFactory2;
    }


    
}
