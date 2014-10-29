/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.CommonInfrastructure;

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
    private List<SelectItem> displayList;
    private String selectedStore;
    private String selectedStore1;
    private String selectedStore2;

    @PostConstruct
    public void init() {

        date = Calendar.getInstance().getTime();
        model = new DefaultDashboardModel();
        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();

        column1.addWidget("message");
        column1.addWidget("ticket");
        column1.addWidget("calendar");

        column2.addWidget("product");
        column2.addWidget("kitchen");
        column2.addWidget("retailproduct");

        model.addColumn(column1);
        model.addColumn(column2);

        displayList = new ArrayList<>();
        storeList = wpml.listStores();

        for (StoreEntity s : storeList) {
            String t = s.getStoreId() + " " + s.getAddress();
            displayList.add(new SelectItem(s.getStoreId(), t));
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


    
}
