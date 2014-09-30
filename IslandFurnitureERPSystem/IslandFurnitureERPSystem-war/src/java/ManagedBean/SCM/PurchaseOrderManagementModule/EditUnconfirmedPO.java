/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchaseOrderManagementModule;

import Entity.Factory.FactoryEntity;
import Entity.Factory.MRP.IntegratedPlannedOrderEntity;
import Entity.Factory.SCM.ContractEntity;
import Entity.Factory.SCM.DeliveryOrderEntity;
import Entity.Factory.SCM.PurchaseOrderEntity;
import Entity.Factory.SCM.SupplierEntity;
import Entity.Store.StoreEntity;
import SessionBean.SCM.PurchaseOrderManagementModuleLocal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author summer
 */
@Named(value = "editUnconfirmedPO")
@ViewScoped
public class EditUnconfirmedPO {

    @EJB
    PurchaseOrderManagementModuleLocal pomml;

    private Collection<SupplierEntity> suppliers;
    private List<String> suppliersNames = new ArrayList<>();
    private PurchaseOrderEntity upo;
    private Long itemId;
    private String itemType;
    private String supplier;
    private Long factoryId;
    private List<StoreEntity> stores;
    private List<String> storeAddress = new ArrayList<>();
    private String store;
    private Double amount;
    private Collection<DeliveryOrderEntity> doe = new ArrayList<>();
    private String result;
    private String destination;
    private long destinationId;

    public EditUnconfirmedPO() {
    }

    @PostConstruct
    public void display() {
        try {
            upo = (PurchaseOrderEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("upo");
            itemId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("itemId");
            factoryId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");

            itemType = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("itemType");
            suppliers = pomml.viewSupplierForItem(itemType, itemId);
            stores = pomml.viewAvailStore(factoryId);

            for (SupplierEntity s : suppliers) {
                suppliersNames.add(s.getSupplierId() + " " + s.getSupplierName());
            }
            for (StoreEntity s : stores) {
                storeAddress.add("store : id = " + s.getStoreId() + " " + s.getAddress());
            }
            storeAddress.add("factory : id = " + factoryId + " " + pomml.getFactoryEntity(factoryId).getAddress());

        } catch (Exception ex) {
            Logger.getLogger(EditUnconfirmedPO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void changeAmount(ValueChangeEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        amount = (Double) newValue;
    }

    public void submit() throws Exception {
        System.out.println("supplierID" + supplier + "      shuliang" + amount);

        String[] parts = supplier.split(" ");
        System.out.println(parts[0]);
        Long supplierId = Long.parseLong(parts[0]);

        ContractEntity contract = pomml.getContract2(supplierId, itemId, itemType);
        if (itemType.equals("RetailProduct")) {
            String[] parts2 = store.split(" ");
            destination = parts2[0];
            destinationId = Long.parseLong(parts2[4]);
        }else{
            destination = "factory";
            destinationId = factoryId;
        }
        if (amount % contract.getLotSize() == 0) {

            Double totalPrice = amount / contract.getLotSize() * contract.getContractPrice();

            //get store, get supplierId
            pomml.editPurchaseOrder(upo.getId(), "unconfirmed", amount, contract.getUnit(), upo.getCreateDate(), destination, destinationId, contract.getLeadTime(),
                    totalPrice, pomml.getFactoryEntity(factoryId), upo.getIntegratedPlannedOrder(), contract);

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("upo");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("itemId");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("itemType");

            result = "Purchase order modified";
        } else {
            result = "Purchase amount is not valid.";
        }
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public PurchaseOrderEntity getUpo() {
        return upo;
    }

    public void setUpo(PurchaseOrderEntity upo) {
        this.upo = upo;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public PurchaseOrderManagementModuleLocal getPomml() {
        return pomml;
    }

    public void setPomml(PurchaseOrderManagementModuleLocal pomml) {
        this.pomml = pomml;
    }

    public Collection<SupplierEntity> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Collection<SupplierEntity> suppliers) {
        this.suppliers = suppliers;
    }

    public List<String> getSuppliersNames() {
        return suppliersNames;
    }

    public void setSuppliersNames(List<String> suppliersNames) {
        this.suppliersNames = suppliersNames;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public List<StoreEntity> getStores() {
        return stores;
    }

    public void setStores(List<StoreEntity> stores) {
        this.stores = stores;
    }

    public List<String> getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(List<String> storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public Collection<DeliveryOrderEntity> getDoe() {
        return doe;
    }

    public void setDoe(Collection<DeliveryOrderEntity> doe) {
        this.doe = doe;
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Delivery Order Edited");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Delviery Order Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
