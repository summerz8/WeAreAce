/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.OCRM;

import Entity.Store.OCRM.ProductSalesForecastEntity;
import Entity.Store.OCRM.SalesRecordEntity;
import Entity.Store.StoreEventEntity;
import SessionBean.OCRM.OCRMSalesForecastModuleLocal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "createStoreProductSalesForecastBean")
@ViewScoped
public class CreateStoreProductSalesForecastBean {

    @EJB
    OCRMSalesForecastModuleLocal sfml;

    private Long productId;
    private String productType;
    private ProductSalesForecastEntity productSalesForecast;
    private String productName;
    private Double NewForecastAmount;
    private List<StoreEventEntity> eventList;
    private List<StoreEventEntity> disPlayList;
    private StoreEventEntity storeEvent;
    private List<StoreEventEntity> EventEffectList;
    private Long storeId;

    public CreateStoreProductSalesForecastBean() {
    }

    @PostConstruct
    public void init() {
        if ((int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Userlvl") == 0) {
            storeId = -1L;

        } else {
            storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
        }

        productId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ProductId");
        productType = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("productType");
        productSalesForecast = sfml.createProductSalesForecast(productId, productType);
        if (productType.equals("StoreProduct")) {
            productName = productSalesForecast.getStoreProduct().getName();
        } else {
            productName = productSalesForecast.getStoreRetailProduct().getName();
        }

        eventList = sfml.getEventList(storeId);
        EventEffectList=eventList;
        Calendar today = Calendar.getInstance();
        today.add(Calendar.MONTH, 2);
        disPlayList = new ArrayList<>();
        for (StoreEventEntity s : eventList) {
            if ((s.getStartDate().get(Calendar.MONTH) == today.get(Calendar.MONTH)) && s.getStartDate().get(Calendar.YEAR) == today.get(Calendar.YEAR)) {
                disPlayList.add(s);
            }
        }

    }

    public String Confirm() {
        if (productType.equals("StoreProduct")) {
            productSalesForecast = sfml.confirmProductSalesForecast(productSalesForecast.getTargetPeriod(), productSalesForecast.getAmount(), productSalesForecast.getStore().getStoreId(), productType, productSalesForecast.getStoreProduct().getStoreProductId());
        } else {
            productSalesForecast = sfml.confirmProductSalesForecast(productSalesForecast.getTargetPeriod(), productSalesForecast.getAmount(), productSalesForecast.getStore().getStoreId(), productType, productSalesForecast.getStoreRetailProduct().getStoreRetailProductId());
        }

        return "SalesForecastIndex?face-redirect=true";
    }

    public void changeAmount(ValueChangeEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        NewForecastAmount = (Double) newValue;
    }

    public void setStoreEventList(StoreEventEntity event) {

        EventEffectList = sfml.getEventEffectList(event.getId());
        System.out.println("333"+EventEffectList.size());

    }
    
    public boolean check(List<ProductSalesForecastEntity> productSalesForecastList,List<SalesRecordEntity> salesRecordList){
        if(productSalesForecastList.isEmpty()||salesRecordList.isEmpty())
            return false;
        else return true;
    
    }
    
    public boolean checkSalesEffect(StoreEventEntity event){
        EventEffectList = sfml.getEventEffectList(event.getId());
        if(EventEffectList.isEmpty())return false;
        else return true;
    }

    public OCRMSalesForecastModuleLocal getSfml() {
        return sfml;
    }

    public void setSfml(OCRMSalesForecastModuleLocal sfml) {
        this.sfml = sfml;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public ProductSalesForecastEntity getProductSalesForecast() {
        return productSalesForecast;
    }

    public void setProductSalesForecast(ProductSalesForecastEntity productSalesForecast) {
        this.productSalesForecast = productSalesForecast;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getNewForecastAmount() {
        return NewForecastAmount;
    }

    public void setNewForecastAmount(Double NewForecastAmount) {
        this.NewForecastAmount = NewForecastAmount;
    }

    public List<StoreEventEntity> getEventList() {
        return eventList;
    }

    public void setEventList(List<StoreEventEntity> eventList) {
        this.eventList = eventList;
    }

    public List<StoreEventEntity> getDisPlayList() {
        return disPlayList;
    }

    public void setDisPlayList(List<StoreEventEntity> disPlayList) {
        this.disPlayList = disPlayList;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public StoreEventEntity getStoreEvent() {
        return storeEvent;
    }

    public void setStoreEvent(StoreEventEntity storeEvent) {
        this.storeEvent = storeEvent;
    }

    public List<StoreEventEntity> getEventEffectList() {
        return EventEffectList;
    }

    public void setEventEffectList(List<StoreEventEntity> EventEffectList) {
        this.EventEffectList = EventEffectList;
    }

}
