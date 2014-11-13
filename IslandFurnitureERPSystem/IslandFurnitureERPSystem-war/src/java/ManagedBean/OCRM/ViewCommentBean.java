/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.OCRM;

import Entity.Store.OCRM.CommentEntity;
import Entity.Store.OCRM.CountryProductEntity;
import Entity.Store.OCRM.CountryRetailProductEntity;
import Entity.Store.OCRM.CountrySetEntity;
import SessionBean.OCRM.CustomerWebModuleLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "viewCommentBean")
@ViewScoped
public class ViewCommentBean {

    @EJB
    CustomerWebModuleLocal cwml;

    private Long itemId;
    private String itemType;
    private CountryProductEntity product;
    private CountryRetailProductEntity retail;
    private CountrySetEntity set;
    private List<CommentEntity> comments;
    private Double totalRate;

    public ViewCommentBean() {
    }

    @PostConstruct
    public void init() {
        itemType = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("itemType");
        itemId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("itemId");
        if (itemType.equals("product")) {
            product = cwml.getItem(itemId);
            comments = product.getComments();
        } else if (itemType.equals("retail")) {
            retail = cwml.getRetailItem(itemId);
            comments = retail.getComments();
        } else if (itemType.equals("set")) {
            set = cwml.getSet(itemId);
            comments = set.getComments();
        }
        int size = comments.size();
        if (size != 0) {
            for (CommentEntity c : comments) {
                totalRate = totalRate + c.getRate();
            }
            totalRate = totalRate / size;
        }
    }

    public CustomerWebModuleLocal getCwml() {
        return cwml;
    }

    public void setCwml(CustomerWebModuleLocal cwml) {
        this.cwml = cwml;
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

    public CountryProductEntity getProduct() {
        return product;
    }

    public void setProduct(CountryProductEntity product) {
        this.product = product;
    }

    public CountryRetailProductEntity getRetail() {
        return retail;
    }

    public void setRetail(CountryRetailProductEntity retail) {
        this.retail = retail;
    }

    public CountrySetEntity getSet() {
        return set;
    }

    public void setSet(CountrySetEntity set) {
        this.set = set;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }

    public Double getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(Double totalRate) {
        this.totalRate = totalRate;
    }

}
