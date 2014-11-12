/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.CommonInfrastructure;

import Entity.Factory.FactoryEntity;
import Entity.Store.StoreEntity;
import SessionBean.CommonInFrastructure.WorkPlaceModuleLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
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
public class WorkPlaceBean implements Serializable {

    @EJB
    WorkPlaceModuleLocal wpml;

    private String userId;
    private Long departmentId;
    private DashboardModel model;
    private Date date;
    private List<StoreEntity> storeList;
    private List<FactoryEntity> factoryList;
    private List<SelectItem> displayList; //s
    private List<SelectItem> displayList2; //f
    private String selectedStore; //Kitchen
    private String selectedStore1 = null;// SP
    private String selectedStore2 = null;//SRP
    private String selectedFactory1 = null;//FP
    private String selectedFactory2 = null;//FRP
    private String selectedFactory3 = null;//FRM

    private Integer newMessages;
    private Integer msgTobeProcessed;
    private Integer ticketsToBeViewed;
    private Integer ticketsToBeProcessed;
    private Integer ticketsToBeClosed;

    private Double revK;
    private Double revSP;
    private Double revSRP;
    private Double stockFP;
    private Double stockFRP;
    private Double stockFRM;

    @PostConstruct
    public void init() {

        userId = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserId");
        departmentId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");

        date = Calendar.getInstance().getTime();
        model = new DefaultDashboardModel();
        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();
        DashboardColumn column3 = new DefaultDashboardColumn();

        column2.addWidget("message");
        column2.addWidget("ticket");
        column2.addWidget("calendar");

        column1.addWidget("kitchenH");
        column1.addWidget("kitchen");
        column1.addWidget("product");
        column1.addWidget("productH");
        column1.addWidget("retailproduct");
        column1.addWidget("retailproductH");

        column3.addWidget("productf");
        column3.addWidget("productfH");
        column3.addWidget("rawMaterial");
        column3.addWidget("rawMaterialH");
        column3.addWidget("retailproductf");
        column3.addWidget("retailproductfH");

        model.addColumn(column1);
        model.addColumn(column2);
        model.addColumn(column3);

        displayList = new ArrayList<>();
        displayList2 = new ArrayList<>();
        storeList = wpml.listStores();
        factoryList = wpml.listFactory();

        for (StoreEntity s : storeList) {
            String t = s.getStoreId() + " " + s.getAddress();
            displayList.add(new SelectItem(s.getStoreId(), t));
        }
        System.out.println("displayList  =" + displayList.toString());

        for (FactoryEntity f : factoryList) {
            String t = f.getFactoryId() + " " + f.getAddress();
            displayList2.add(new SelectItem(f.getFactoryId(), t));
        }
        System.out.println("displayList2  =" + displayList2.toString());

        this.newMessages = wpml.newMessages(userId);
        this.msgTobeProcessed = wpml.msgTobeProcessed(userId);
        this.ticketsToBeViewed = wpml.ticketSubmitted(userId);
        this.ticketsToBeProcessed = wpml.ticketInReview(userId);
        this.ticketsToBeClosed = wpml.ticketInProcess(userId);

        if (!userId.startsWith("H")) {
            this.revK = wpml.revK(userId, departmentId);
            this.revSP = wpml.revSP(userId, departmentId);
            this.revSRP = wpml.revSRP(userId, departmentId);
            this.stockFP = wpml.stockFP(userId, departmentId);
            this.stockFRM = wpml.stockFRM(userId, departmentId);
            this.stockFRP = wpml.stockFRP(userId, departmentId);

            System.out.println("revK = " + revK);
            System.out.println("revSP = " + revSP);
            System.out.println("revSRP = " + revSRP);
            System.out.println("stockFP = " + stockFP);
            System.out.println("stockFRM = " + stockFRM);
            System.out.println("stockFRP = " + stockFRP);
        }
    }

    public void checkRev() {
        if (selectedStore != null) {
            userId = "S1000001";
            System.out.println(selectedStore);
            Long storeId = Long.valueOf(selectedStore);
            StoreEntity s = wpml.getStore(storeId);
            departmentId = s.getStoreId();
            this.revK = wpml.revK(userId, departmentId);
        }
        if (selectedStore1 != null) {
            userId = "S1000001";
            Long storeId = Long.valueOf(selectedStore1);
            StoreEntity s = wpml.getStore(storeId);
            departmentId = s.getStoreId();
            this.revSP = wpml.revSP(userId, departmentId);
        }
        if (selectedStore2 != null) {
            userId = "S1000001";
            Long storeId = Long.valueOf(selectedStore2);
            StoreEntity s = wpml.getStore(storeId);
            departmentId = s.getStoreId();
            this.revSRP = wpml.revSRP(userId, departmentId);
        }
    }

    public void checkStock() {
        if (selectedFactory1 != null) {
            userId = "F1000001";
            Long factoryId = Long.valueOf(selectedFactory1);
            FactoryEntity f = wpml.getFactory(factoryId);
            departmentId = f.getFactoryId();
            this.stockFP = wpml.stockFP(userId, departmentId);
        }
        if (selectedFactory2 != null) {
            userId = "F1000001";
            Long factoryId = Long.valueOf(selectedFactory1);
            FactoryEntity f = wpml.getFactory(factoryId);
            departmentId = f.getFactoryId();
            this.stockFRP = wpml.stockFRP(userId, departmentId);
        }
        if (selectedFactory3 != null) {
            userId = "F1000001";
            Long factoryId = Long.valueOf(selectedFactory1);
            FactoryEntity f = wpml.getFactory(factoryId);
            departmentId = f.getFactoryId();
            this.stockFRM = wpml.stockFRM(userId, departmentId);
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getSelectedFactory3() {
        return selectedFactory3;
    }

    public void setSelectedFactory3(String selectedFactory3) {
        this.selectedFactory3 = selectedFactory3;
    }

    public List<FactoryEntity> getFactoryList() {
        return factoryList;
    }

    public void setFactoryList(List<FactoryEntity> factoryList) {
        this.factoryList = factoryList;
    }

    public Integer getNewMessages() {
        return newMessages;
    }

    public void setNewMessages(Integer newMessages) {
        this.newMessages = newMessages;
    }

    public Integer getMsgTobeProcessed() {
        return msgTobeProcessed;
    }

    public void setMsgTobeProcessed(Integer msgTobeProcessed) {
        this.msgTobeProcessed = msgTobeProcessed;
    }

    public Integer getTicketsToBeViewed() {
        return ticketsToBeViewed;
    }

    public void setTicketsToBeViewed(Integer ticketsToBeViewed) {
        this.ticketsToBeViewed = ticketsToBeViewed;
    }

    public Integer getTicketsToBeClosed() {
        return ticketsToBeClosed;
    }

    public void setTicketsToBeClosed(Integer ticketsToBeClosed) {
        this.ticketsToBeClosed = ticketsToBeClosed;
    }

    public Integer getTicketsToBeProcessed() {
        return ticketsToBeProcessed;
    }

    public void setTicketsToBeProcessed(Integer ticketsToBeProcessed) {
        this.ticketsToBeProcessed = ticketsToBeProcessed;
    }

    public Double getRevK() {
        return revK;
    }

    public void setRevK(Double revK) {
        this.revK = revK;
    }

    public Double getRevSP() {
        return revSP;
    }

    public void setRevSP(Double revSP) {
        this.revSP = revSP;
    }

    public Double getStockFP() {
        return stockFP;
    }

    public void setStockFP(Double stockFP) {
        this.stockFP = stockFP;
    }

    public Double getRevSRP() {
        return revSRP;
    }

    public void setRevSRP(Double revSRP) {
        this.revSRP = revSRP;
    }

    public Double getStockFRP() {
        return stockFRP;
    }

    public void setStockFRP(Double stockFRP) {
        this.stockFRP = stockFRP;
    }

    public Double getStockFRM() {
        return stockFRM;
    }

    public void setStockFRM(Double stockFRM) {
        this.stockFRM = stockFRM;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public List<SelectItem> getDisplayList2() {
        return displayList2;
    }

    public void setDisplayList2(List<SelectItem> displayList2) {
        this.displayList2 = displayList2;
    }

}
