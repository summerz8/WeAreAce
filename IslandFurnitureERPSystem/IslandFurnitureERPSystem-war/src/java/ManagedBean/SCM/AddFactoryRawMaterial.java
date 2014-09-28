/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM;

import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.RawMaterialEntity;
import SessionBean.SCM.PurchasedItemAndSupplierManagementModuleLocal;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author zhengyuan
 */
@Named(value = "addFactoryRawMaterial")
@ViewScoped
public class AddFactoryRawMaterial {

    /**
     * Creates a new instance of AddFactoryRawMaterial
     */
    public AddFactoryRawMaterial() {
    }

    @EJB
    PurchasedItemAndSupplierManagementModuleLocal PASMM;

    private Collection<FactoryRawMaterialEntity> addedFactoryRawMaterialList;
    private Collection<RawMaterialEntity> notAddRawMaterialList;
    private Long factoryId;
    private String msgPrint;
    private String msgPrint2;
    private FactoryRawMaterialEntity selectedFRM;
    private RawMaterialEntity selectedRawMaterial;

    @PostConstruct
    public void init() {

        try {
            factoryId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
            addedFactoryRawMaterialList = PASMM.viewRawMaterialWithSelectType(factoryId);
            System.out.println("AddFactoryRM ManageBean: addedListsize :" + addedFactoryRawMaterialList.size());
            notAddRawMaterialList = PASMM.viewRawMaterialListNotInFactory(factoryId);
            System.out.println("AddFactoryRM ManageBean: notAddProductList Size: " + notAddRawMaterialList.size());

        } catch (Exception ex) {
            Logger.getLogger(AddFactoryRawMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void selectFactoryRawMaterial(FactoryRawMaterialEntity frm) {
        System.out.println("IT IS SELECTED. SELECTED FRM : "+  frm.getFactoryRawMaterialId());
        selectedFRM = frm;
    }

    public void selectRawMaterial(RawMaterialEntity rm) {
        selectedRawMaterial = rm;
    }

    public void deleteFactoryRawMaterial(FactoryRawMaterialEntity frm) throws Exception {
      //  System.out.println("have a check:" + frm.getFactoryRawMaterialId());
        msgPrint = PASMM.deleteItem("RawMaterial", frm.getFactoryRawMaterialId());
        addedFactoryRawMaterialList = PASMM.viewRawMaterialWithSelectType(factoryId);
        notAddRawMaterialList = PASMM.viewRawMaterialListNotInFactory(factoryId);

    }

    public void addFactoryRawMaterial(RawMaterialEntity rm) throws Exception {
        msgPrint2  = PASMM.addItem(factoryId, "RawMaterial", rm.getMaterialId());
        addedFactoryRawMaterialList = PASMM.viewRawMaterialWithSelectType(factoryId);
        notAddRawMaterialList = PASMM.viewRawMaterialListNotInFactory(factoryId);
        
    }

    public PurchasedItemAndSupplierManagementModuleLocal getPASMM() {
        return PASMM;
    }

    public void setPASMM(PurchasedItemAndSupplierManagementModuleLocal PASMM) {
        this.PASMM = PASMM;
    }

    public Collection<FactoryRawMaterialEntity> getAddedFactoryRawMaterialList() {
        return addedFactoryRawMaterialList;
    }

    public void setAddedFactoryRawMaterialList(Collection<FactoryRawMaterialEntity> addedFactoryRawMaterialList) {
        this.addedFactoryRawMaterialList = addedFactoryRawMaterialList;
    }

    public Collection<RawMaterialEntity> getNotAddRawMaterialList() {
        return notAddRawMaterialList;
    }

    public void setNotAddRawMaterialList(Collection<RawMaterialEntity> notAddRawMaterialList) {
        this.notAddRawMaterialList = notAddRawMaterialList;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public String getMsgPrint() {
        return msgPrint;
    }

    public void setMsgPrint(String msgPrint) {
        this.msgPrint = msgPrint;
    }

    public String getMsgPrint2() {
        return msgPrint2;
    }

    public void setMsgPrint2(String msgPrint2) {
        this.msgPrint2 = msgPrint2;
    }

    public FactoryRawMaterialEntity getSelectedFRM() {
        return selectedFRM;
    }

    public void setSelectedFRM(FactoryRawMaterialEntity selectedFRM) {
        this.selectedFRM = selectedFRM;
    }

    public RawMaterialEntity getSelectedRawMaterial() {
        return selectedRawMaterial;
    }

    public void setSelectedRawMaterial(RawMaterialEntity selectedRawMaterial) {
        this.selectedRawMaterial = selectedRawMaterial;
    }


}
