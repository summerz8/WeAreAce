/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.OCRM;

import Entity.Store.OCRM.VoucherEntity;
import ManagedBean.CommonInfrastructure.EnterpriseResourceControl.FactoryControlBean;
import SessionBean.OCRM.VoucherManagementModuleLocal;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author dan
 */
@Named(value = "voucherControl")
@ViewScoped
public class VoucherControlBean {

    @EJB
    private VoucherManagementModuleLocal VOMM;
    private List<VoucherEntity> voucherList;
    private List<VoucherEntity> filteredVoucher;

    private String newVoucherName;
    private String newVoucherDescription;
    private Double newVoucherValue;
    private int newVoucherQuantity;

    
    private VoucherEntity selectedVoucher;

    /**
     * Creates a new instance of VoucherControl
     */
    public VoucherControlBean() {
    }
    
    @PostConstruct
    public void init() {
        System.out.println("VoucherControlBean: init:");

        voucherList = VOMM.ListVoucher();
        filteredVoucher = voucherList;

    }

    public void onRowEdit(RowEditEvent event) {
        System.out.println("onRowEdit test:");
        VoucherEntity entity = (VoucherEntity) event.getObject();
        System.out.println("onRowEdit test: " + entity.getVoucherId() + entity.getName());

        VOMM.ModifyVoucher(entity.getVoucherId(), entity.getName(), entity.getDescription(), entity.getValue());
        FacesMessage msg = new FacesMessage("Voucher Edited", String.valueOf(entity.getVoucherId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((VoucherEntity) event.getObject()).getVoucherId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deleteVoucher(long id) {
        System.out.println("VoucherControlBean: deleteVoucher: " + String.valueOf(id));
        VOMM.DeleteVoucher(id);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Voucher deleted successfully! ", ""));

        voucherList = VOMM.ListVoucher();
        filteredVoucher = voucherList;
    }

    public void fulfillVoucher(long id) {
        System.out.println("VoucherControlBean: fulfillVoucher: " + String.valueOf(id));
        VOMM.FulfillVoucher(id);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Voucher used successfully! ", ""));

        voucherList = VOMM.ListVoucher();
        filteredVoucher = voucherList;
    }
    public void addVoucher() {
        System.out.println("VoucherControlBean: addVoucher: ");
        for(int i=0;i<newVoucherQuantity;i++){
        VOMM.AddVoucher(newVoucherName, newVoucherDescription, newVoucherValue);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Voucher added successfully! ", ""));
        }
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("viewVoucher.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(FactoryControlBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<VoucherEntity> getVoucherList() {
        return voucherList;
    }

    public void setVoucherList(List<VoucherEntity> voucherList) {
        this.voucherList = voucherList;
    }

    public List<VoucherEntity> getFilteredVoucher() {
        return filteredVoucher;
    }

    public void setFilteredVoucher(List<VoucherEntity> filteredVoucher) {
        this.filteredVoucher = filteredVoucher;
    }

    public String getNewVoucherName() {
        return newVoucherName;
    }

    public void setNewVoucherName(String newVoucherName) {
        this.newVoucherName = newVoucherName;
    }

    public String getNewVoucherDescription() {
        return newVoucherDescription;
    }

    public void setNewVoucherDescription(String newVoucherDescription) {
        this.newVoucherDescription = newVoucherDescription;
    }

    public Double getNewVoucherValue() {
        return newVoucherValue;
    }

    public void setNewVoucherValue(Double newVoucherValue) {
        this.newVoucherValue = newVoucherValue;
    }

    public VoucherEntity getSelectedVoucher() {
        return selectedVoucher;
    }

    public void setSelectedVoucher(VoucherEntity selectedVoucher) {
        this.selectedVoucher = selectedVoucher;
    }

    public int getNewVoucherQuantity() {
        return newVoucherQuantity;
    }

    public void setNewVoucherQuantity(int newVoucherQuantity) {
        this.newVoucherQuantity = newVoucherQuantity;
    }

    
    
}
