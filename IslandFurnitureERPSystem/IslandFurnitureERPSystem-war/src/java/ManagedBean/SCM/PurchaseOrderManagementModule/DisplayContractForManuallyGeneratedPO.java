/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchaseOrderManagementModule;

import Entity.Factory.SCM.ContractEntity;
import Entity.Factory.SCM.SupplierEntity;
import SessionBean.SCM.PurchaseOrderManagementModuleLocal;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author zhangshiyu
 */
@Named(value = "displayContractForManuallyGeneratedPO")
@ViewScoped
public class DisplayContractForManuallyGeneratedPO {

    @EJB
    private PurchaseOrderManagementModuleLocal pmb;
    private ContractEntity contract;
    private String itemType;
    private Long itemId;
    private SupplierEntity supplier;
    private Double purchaseAmount;
    private Long factoryId ;
    private Calendar deliveryDate = Calendar.getInstance();

    @PostConstruct
    public void init() {
        try {
            factoryId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
            //be put @displaySuppliersForManuallyGeneratedPO
            itemId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("itemId");
            //be put @displaySuppliersForManuallyGeneratedPO
            itemType = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("itemType");
            //be put @SelectedSupplierPO
            supplier = (SupplierEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedSupplier");

            contract = pmb.selectSupplier(itemType, itemId, supplier.getSupplierId());

        } catch (Exception ex) {
            Logger.getLogger(DisplayContractForManuallyGeneratedPO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public PurchaseOrderManagementModuleLocal getPmb() {
        return pmb;
    }

    public void setPmb(PurchaseOrderManagementModuleLocal pmb) {
        this.pmb = pmb;
    }

    public ContractEntity getContract() {
        return contract;
    }

    public void setContract(ContractEntity contract) {
        this.contract = contract;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Double getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(Double purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("purchaseAmount", purchaseAmount);

    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public SupplierEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierEntity supplier) {
        this.supplier = supplier;
    }

    public Date getDeliveryDate() {
        return deliveryDate.getTime();
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate.setTime(deliveryDate);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("deliveryDate", this.deliveryDate);
    }

    public DisplayContractForManuallyGeneratedPO() {
    }
}
