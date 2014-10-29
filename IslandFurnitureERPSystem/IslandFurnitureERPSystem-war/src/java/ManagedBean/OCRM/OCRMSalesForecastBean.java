/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.OCRM;

import Entity.Store.StoreProductEntity;
import Entity.Store.StoreRetailProductEntity;
import SessionBean.OCRM.OCRMSalesForecastModuleLocal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "OCRMSalesForecastBean")
@ViewScoped
public class OCRMSalesForecastBean {

    @EJB
    OCRMSalesForecastModuleLocal sfml;

    private String department;
    private Long storeId;
    private Long storeProductId;
    private List<StoreProductEntity> storeProductList;
    private String selectedStoreProduct;
    private List<SelectItem> storeProductDisplayList;

    private Long storeRetailProductId;
    private List<StoreRetailProductEntity> storeRetailProductList;
    private String selectedStoreRetailProduct;
    private List<SelectItem> storeRetailProductDisplayList;

    private Boolean createSalesForecast;

    public OCRMSalesForecastBean() {
    }

    @PostConstruct
    public void init() {

        storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
        department = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("department");

        storeProductDisplayList = new ArrayList<>();
        if (department.equals("H")) {
            storeProductList = sfml.listStoreProduct(null);
        } else {
            storeProductList = sfml.listStoreProduct(storeId);
        }

        for (StoreProductEntity s : storeProductList) {
            String t = s.getStoreProductId() + " " + s.getName();
            storeProductDisplayList.add(new SelectItem(s.getStoreProductId(), t));
        }

        storeRetailProductDisplayList = new ArrayList<>();
        if (department.equals("H")) {
            storeRetailProductList = sfml.listStoreRetailProduct(null);
        } else {
            storeRetailProductList = sfml.listStoreRetailProduct(storeId);
        }

        for (StoreRetailProductEntity s : storeRetailProductList) {
            String t = s.getStoreRetailProductId() + " " + s.getName();
            storeRetailProductDisplayList.add(new SelectItem(s.getStoreRetailProductId(), t));
        }
    }

    public String ViewStoreProductSalesRecord(Long productId) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ProductId", productId);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("productType", "StoreProduct");

        return "ViewSalesRecord?faces-redirect=true";

    }

    public String ViewStoreRetailProductSalesRecord(Long productId) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ProductId", productId);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("productType", "StoreRetailProduct");

        return "ViewSalesRecord?faces-redirect=true";
    }

    public String ViewStoreProductSalesForecast(Long productId) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ProductId", productId);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("productType", "StoreProduct");

        return "ViewProductSalesForecast?faces-redirect=true";

    }

    public String ViewStoreRetailProductSalesForecast(Long productId) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ProductId", productId);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("productType", "StoreRetailProduct");

        return "ViewProductSalesForecast?faces-redirect=true";
    }

    public String CreateStoreProductSalesForecast() {
        if (department.equals("H")) {
            return "NoRightToProcess?faces-redirect=true";
        } else {
            Long productId = Long.valueOf(selectedStoreProduct);
            if (sfml.CheckExistence(productId, "StoreProduct") == true) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ProductId", productId);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("productType", "StoreProduct");
                return "CreateProductSalesForecast?faces-redirect=true";
            } else {
                return "CantCreateProductSalesForecast?faces-redirect=true";
            }

        }
    }

    public String CreateStoreRetailProductSalesForecast() {
        if (department.equals("H")) {
            return "NoRightToProcess?faces-redirect=true";
        } else {
            Long productId = Long.valueOf(selectedStoreRetailProduct);
            if (sfml.CheckExistence(productId, "StoreRetailProduct") == true) {

                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ProductId", productId);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("productType", "StoreRetailProduct");
                return "CreateProductSalesForecast?faces-redirect=true";
            } else {
                return "CantCreateProductSalesForecast?faces-redirect=true";
            }
        }
    }

    public String createSalesForeacast() {
        if (department.equals("H")) {
            return "NoRightToProcess?faces-redirect=true";
        } else {
            createSalesForecast = sfml.checkAvailability(storeId);
            if (createSalesForecast == true) {
                return "CreateSalesForecast?faces-redirect=true";
            } else {
                return "CantCreateSalesForecast?faces-redirect=true";
            }
        }
    }

    public String CheckStoreProduct(Long productId) {

        return sfml.checkStoreProduct(productId);

    }

    public String CheckStoreRetailProduct(Long productId) {

        return sfml.checkStoreRetailProduct(productId);

    }

    public OCRMSalesForecastModuleLocal getSfml() {
        return sfml;
    }

    public void setSfml(OCRMSalesForecastModuleLocal sfml) {
        this.sfml = sfml;
    }

    public String getDepartement() {
        return department;
    }

    public void setDepartement(String departement) {
        this.department = departement;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getStoreProductId() {
        return storeProductId;
    }

    public void setStoreProductId(Long storeProductId) {
        this.storeProductId = storeProductId;
    }

    public List<StoreProductEntity> getStoreProductList() {
        return storeProductList;
    }

    public void setStoreProductList(List<StoreProductEntity> storeProductList) {
        this.storeProductList = storeProductList;
    }

    public String getSelectedStoreProduct() {
        return selectedStoreProduct;
    }

    public void setSelectedStoreProduct(String selectedStoreProduct) {
        this.selectedStoreProduct = selectedStoreProduct;
    }

    public List<SelectItem> getStoreProductDisplayList() {
        return storeProductDisplayList;
    }

    public void setStoreProductDisplayList(List<SelectItem> storeProductDisplayList) {
        this.storeProductDisplayList = storeProductDisplayList;
    }

    public Long getStoreRetailProductId() {
        return storeRetailProductId;
    }

    public void setStoreRetailProductId(Long storeRetailProductId) {
        this.storeRetailProductId = storeRetailProductId;
    }

    public List<StoreRetailProductEntity> getStoreRetailProductList() {
        return storeRetailProductList;
    }

    public void setStoreRetailProductList(List<StoreRetailProductEntity> storeRetailProductList) {
        this.storeRetailProductList = storeRetailProductList;
    }

    public String getSelectedStoreRetailProduct() {
        return selectedStoreRetailProduct;
    }

    public void setSelectedStoreRetailProduct(String selectedStoreRetailProduct) {
        this.selectedStoreRetailProduct = selectedStoreRetailProduct;
    }

    public List<SelectItem> getStoreRetailProductDisplayList() {
        return storeRetailProductDisplayList;
    }

    public void setStoreRetailProductDisplayList(List<SelectItem> storeRetailProductDisplayList) {
        this.storeRetailProductDisplayList = storeRetailProductDisplayList;
    }

}
