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
    private Long factoryId;
    private Calendar deliveryDate = Calendar.getInstance();
    private String result = null;
    private Boolean isValid = true;

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
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public DisplayContractForManuallyGeneratedPO() {
    }

    public void checkInput() {
        Calendar checkDate = Calendar.getInstance();
        checkDate.add(Calendar.DAY_OF_MONTH, this.contract.getLeadTime());

        if ((purchaseAmount % this.contract.getLotSize()) == 0) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("purchaseAmount", purchaseAmount);
            result = "Purchase amount valid.\n";
            isValid = true;
        } else {
            result = "Purchase amount required to be multiples of lot size. \n";
            isValid = false;

        }
        System.out.println("Check Date:" + checkDate.toString());
        if (removeTime(checkDate).before(removeTime(this.deliveryDate))) {
            result = result + "Arrival date valid.";
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("deliveryDate", this.deliveryDate);
        } else {
            int month = checkDate.get(Calendar.MONTH);
            month++;
            result = result + "Arrival date should be later than " + checkDate.get(Calendar.DAY_OF_MONTH) + "/"
                    + month + "/" + checkDate.get(Calendar.YEAR);
            isValid = false;
        }
        
        System.out.println("Is valid: " + isValid);

    }

    public Calendar removeTime(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

}
