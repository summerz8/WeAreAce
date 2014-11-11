/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Member;

import Entity.Store.OCRM.CommentEntity;
import Entity.Store.OCRM.CountryProductEntity;
import Entity.Store.StoreEntity;
import SessionBean.OCRM.CustomerWebModuleLocal;
import java.text.DecimalFormat;
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
@Named(value = "viewItemBean")
@ViewScoped
public class ViewItemBean {

    @EJB
    private CustomerWebModuleLocal cwml;
    private Long itemId;
    private CountryProductEntity item;
    private String email;
    private int quantity;
    private String name;
    private String comment;
    private Integer rate = 0;
    private List<SelectItem> typeList;
    private String selectedRate;
    private List<CommentEntity> allComment;
    private List<CommentEntity> commentList;
    private Double totalRate = 0D;
    private String web;
    private String selectedStore;
    private List<SelectItem> storeList;
    private List<StoreEntity> stores;
    private Double stock = null;

    public ViewItemBean() {
    }

    @PostConstruct
    public void init() {
        totalRate = 0D;
        itemId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("itemId");
        name = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("FirstName");
        item = cwml.getItem(itemId);
        web = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("web");

        allComment = item.getComments();
        commentList = new ArrayList();
        for (CommentEntity c : allComment) {
            if (c.getCountry().equals(web)) {
                commentList.add(c);
            }
        }

        int size = commentList.size();
        if(size!=0){
        for (CommentEntity c : commentList) {
            if (c.getCountry().equals(web)) {
                totalRate = totalRate + c.getRate();
            }
        }
        totalRate = totalRate / size;
        }
        typeList = new ArrayList<>();
        typeList.add(new SelectItem("1"));
        typeList.add(new SelectItem("2"));
        typeList.add(new SelectItem("3"));
        typeList.add(new SelectItem("4"));
        typeList.add(new SelectItem("5"));
        typeList.add(new SelectItem("6"));
        typeList.add(new SelectItem("7"));
        typeList.add(new SelectItem("8"));
        typeList.add(new SelectItem("9"));
        typeList.add(new SelectItem("10"));

        stores = cwml.getStores(web);
        storeList = new ArrayList<>();
        for (StoreEntity s : stores) {
            System.out.println(s.getAddress());
            storeList.add(new SelectItem(s.getStoreId(), s.getAddress()));
        }
    }

    public String addToShoppingCart() {
        email = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Email");
        if (email == null) {
            return "LoginPage?faces-redirect=true";
        } else {
            cwml.addToShoppingCart(email, item.getId(), quantity, "product");
            return "ViewItem?faces-redirect=true";
        }
    }

    public String createComment() {
        rate = Integer.valueOf(selectedRate);

        cwml.createComment(itemId, "item", name, comment, rate, web);
        return "ViewItem?faces-redirect=true";
    }

    public void checkStock() {
        System.out.println("check");
        Long storeId = Long.valueOf(selectedStore);
        System.out.println("storeId:" + storeId);
        stock = cwml.checkStock(storeId, itemId, "item");
        System.out.println(stock);
    }

    public String checkPrice(Double price) {
        DecimalFormat fnum = new DecimalFormat("##0.00");

        if (web.equals("Singapore")) {
            return "$" + price;
        } else if (web.equals("China")) {
            return "￥" + fnum.format(price * 6.12);
        } else {
            return price + "";
        }
    }
    
    public boolean checkLogIn(){
        if(name==null) return false;
        else return true;
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

    public CountryProductEntity getItem() {
        return item;
    }

    public void setItem(CountryProductEntity item) {
        this.item = item;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public List<SelectItem> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<SelectItem> typeList) {
        this.typeList = typeList;
    }

    public String getSelectedRate() {
        return selectedRate;
    }

    public void setSelectedRate(String selectedType) {
        this.selectedRate = selectedType;
    }

    public List<CommentEntity> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentEntity> commentList) {
        this.commentList = commentList;
    }

    public Double getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(Double totalRate) {
        this.totalRate = totalRate;
    }

    public List<CommentEntity> getAllComment() {
        return allComment;
    }

    public void setAllComment(List<CommentEntity> allComment) {
        this.allComment = allComment;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getSelectedStore() {
        return selectedStore;
    }

    public void setSelectedStore(String selectedStore) {
        this.selectedStore = selectedStore;
    }

    public List<SelectItem> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<SelectItem> storeList) {
        this.storeList = storeList;
    }

    public List<StoreEntity> getStores() {
        return stores;
    }

    public void setStores(List<StoreEntity> stores) {
        this.stores = stores;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

}
