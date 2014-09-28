/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.CommonInfrastructure.EnterpriseResourceControl;

import Entity.Factory.BOMEntity;
import Entity.Factory.ProductEntity;
import SessionBean.CommonInFrastructure.EnterpriseInventoryManagementModule_BOMLocal;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author zhengyuan
 */
@Named(value = "productBOMControl")
@ViewScoped
public class ProductBOMControl {
    
    @EJB
    private EnterpriseInventoryManagementModule_BOMLocal EMBOM;

    /**
     * Creates a new instance of ProductBOMControl
     */
    public ProductBOMControl() {
    }

    private ProductEntity selectedProduct;
    private List<BOMEntity> productBOMList;
    private List<BOMEntity> filteredBOMList;
    private Long newRawMaterialId;
    private Double newAmount;
    private String message;

    

    @PostConstruct
    public void init() {
        selectedProduct = (ProductEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedProduct");
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).removeAttribute("selectedProduct");
        System.out.println("ProductBOMControlBean: init(): " + selectedProduct.getName());
        productBOMList = EMBOM.getAllBOM(selectedProduct.getProductId());
       
        
        filteredBOMList = productBOMList;
    }

    public void onRowEdit(RowEditEvent event) throws Exception {
        System.out.println("onRowEdit test:");
        BOMEntity entity = (BOMEntity) event.getObject();
        System.out.println("onRowEdit test: " + entity.getBOMId() + entity.getRawMaterial().getMaterialName());
        EMBOM.updateANewBom(entity.getBOMId(), entity.getAmount());

        FacesMessage msg = new FacesMessage("BOM Edited", String.valueOf(entity.getRawMaterial().getMaterialId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((BOMEntity) event.getObject()).getBOMId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deleteBOMItem(Long id) throws Exception {
        System.out.println("ProductControlBean: deleteProduct: " + String.valueOf(id));
        EMBOM.deleteANewBom(id);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "One item in BOM is deleted successfully! ", ""));
        productBOMList = EMBOM.getAllBOM(selectedProduct.getProductId());
        filteredBOMList = productBOMList;

    }

    public void addBOMItem(ActionEvent event) throws Exception {
        Integer isExist = 0;
        System.out.println("addBOMItem(): just in" );
        for (BOMEntity bom : productBOMList) {
            BOMEntity be = bom;
            if (Objects.equals(be.getRawMaterial().getMaterialId(), newRawMaterialId)) {
                isExist = 1;
                message = "Sorry! The raw material exists in current BOM! Please edit from it.";
                System.out.println("1");
                break;
            }
        }
        if(isExist == 0 ){
            
            isExist = EMBOM.addANewBOM(selectedProduct.getProductId(), newRawMaterialId, newAmount);
            productBOMList = EMBOM.getAllBOM(selectedProduct.getProductId());
            filteredBOMList = productBOMList;
            System.out.println("2");
            if( isExist == 0 ) {
            message = "Item is added successfully!";
            System.out.println("3");
            newRawMaterialId = null;
            newAmount = null;
            
            }
            if( isExist == 2){
                message = "Raw Material is not in the record! Please add the material frist.";
                System.out.println("4");
            }
            
        
        }
        
        System.out.println("Message:" + message);
    }

    public ProductEntity getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(ProductEntity selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<BOMEntity> getProductBOMList() {
        return productBOMList;
    }

    public void setProductBOMList(List<BOMEntity> productBOMList) {
        this.productBOMList = productBOMList;
    }

    public List<BOMEntity> getFilteredBOMList() {
        return filteredBOMList;
    }

    public void setFilteredBOMList(List<BOMEntity> filteredBOMList) {
        this.filteredBOMList = filteredBOMList;
    }

    public EnterpriseInventoryManagementModule_BOMLocal getEMBOM() {
        return EMBOM;
    }

    public void setEMBOM(EnterpriseInventoryManagementModule_BOMLocal EMBOM) {
        this.EMBOM = EMBOM;
    }

    public Long getNewRawMaterialId() {
        return newRawMaterialId;
    }

    public void setNewRawMaterialId(Long newRawMaterialId) {
        this.newRawMaterialId = newRawMaterialId;
    }

    public Double getNewAmount() {
        return newAmount;
    }

    public void setNewAmount(Double newAmount) {
        this.newAmount = newAmount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    

}
