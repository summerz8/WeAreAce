/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchasedItemAndSupplierManagementModule;

import Entity.Factory.SCM.SupplierEntity;
import SessionBean.SCM.PurchasedItemAndSupplierManagementModuleLocal;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author zhangshiyu
 */
@ManagedBean(name = "addContract")
@ViewScoped
public class AddContract implements Serializable {

    @EJB
    private PurchasedItemAndSupplierManagementModuleLocal pmb;

    private Long factoryId;
    private SupplierEntity selectedSupplier;
    private String itemType;
    private Long itemId;
    private Double contractPrice;
    private Integer leadTime;
    private Double lotSize;

    private Calendar startDate = Calendar.getInstance();
    private Calendar endDate = Calendar.getInstance();

    String result = null;

    public AddContract() {
    }

    @PostConstruct
    public void init() {
        factoryId = (Long)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");

        selectedSupplier = (SupplierEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedSupplier");
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public PurchasedItemAndSupplierManagementModuleLocal getPmb() {
        return pmb;
    }

    public void setPmb(PurchasedItemAndSupplierManagementModuleLocal pmb) {
        this.pmb = pmb;
    }

    public SupplierEntity getSelectedSupplier() {
        return selectedSupplier;
    }

    public void setSelectedSupplier(SupplierEntity selectedSupplier) {
        this.selectedSupplier = selectedSupplier;
    }

    public Double getContractPrice() {

        return contractPrice;
    }

    public void setContractPrice(Double contractPrice) {
        this.contractPrice = contractPrice;
    }

    public Integer getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(Integer leadTime) {
        this.leadTime = leadTime;
    }

    public Double getLotSize() {
        return lotSize;
    }

    public void setLotSize(Double lotSize) {
        this.lotSize = lotSize;
    }

    public Date getStartDate() {
        return startDate.getTime();
    }

    public void setStartDate(Date startDate) {
        this.startDate.setTime(startDate);
    }

    public Date getEndDate() {
        return endDate.getTime();
    }

    public void setEndDate(Date endDate) {
        this.endDate.setTime(endDate);
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void save() throws Exception {

        System.out.println("save() ");
        itemId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("itemId");
        itemType = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("itemType");

        selectedSupplier = (SupplierEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("selectedSupplier");

        System.out.println("SelectedSupplier =  " + this.selectedSupplier.toString());

        if (itemType.equals("RawMaterial")) {
            result = pmb.addContract(factoryId, selectedSupplier.getSupplierId(),
                    itemType, itemId,
                    contractPrice, leadTime, lotSize, startDate, endDate);
        } else {
            result = pmb.addContract(factoryId, selectedSupplier.getSupplierId(),
                    itemType, itemId,
                    contractPrice, leadTime, lotSize, startDate, endDate);
        }
        
        System.out.println("Result = " + result);
    }

}
