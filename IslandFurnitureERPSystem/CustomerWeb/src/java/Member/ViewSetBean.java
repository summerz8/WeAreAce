/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Member;

import Entity.Store.OCRM.CommentEntity;
import Entity.Store.OCRM.CustomerWebItemEntity;
import Entity.Store.OCRM.SetEntity;
import Entity.Store.StoreEntity;
import SessionBean.OCRM.CustomerWebModuleLocal;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "viewSetBean")
@ViewScoped
public class ViewSetBean {

    @EJB
    private CustomerWebModuleLocal cwml;

    private Long setId;
    private SetEntity set;
    private List<String> pictureList;
    private List<CustomerWebItemEntity> itemList;
    private String email;
    private CustomerWebItemEntity item;
    private Integer quantity;

    private String name;
    private String comment;
    private Integer rate;
    private List<SelectItem> typeList;
    private String selectedRate;
    private List<CommentEntity> allComment;
    private List<CommentEntity> commentList;
    private Double totalRate;
    private String web;

    private String selectedStore;
    private List<SelectItem> storeList;
    private List<StoreEntity> stores;
    private Double stock;

    public ViewSetBean() {
    }

    @PostConstruct
    public void init() {
        totalRate = 0D;

        setId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("setId");
        System.out.println(setId);
        set = cwml.getSet(setId);
        pictureList = new ArrayList<>();
        pictureList.add(set.getPicture());
        itemList = set.getUnitList();
        for (CustomerWebItemEntity c : itemList) {
            pictureList.add(c.getPicture());
        }
        name = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("FirstName");

        web = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("web");

        allComment = set.getComments();
        commentList = new ArrayList();
        for (CommentEntity c : allComment) {
            if (c.getCountry().equals(web)) {
                commentList.add(c);
            }
        }

        int size = commentList.size();
        for (CommentEntity c : commentList) {
            if (c.getCountry().equals(web)) {
                totalRate = totalRate + c.getRate();
            }
        }
        totalRate = totalRate / size;

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
            cwml.addToShoppingCart(email, item.getId(), quantity);
            return "set?faces-redirect=true";
        }
    }

    public String createComment() {
        rate = Integer.valueOf(selectedRate);

        cwml.createComment(setId, "set", name, comment, rate, web);
        return "set?faces-redirect=true";
    }

    public void addAllToShoppingCart() throws IOException {
        email = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Email");
        if (email == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("LoginPage.xhtml");

        }
        quantity = 1;
        for (CustomerWebItemEntity c : itemList) {
            item = c;
            addToShoppingCart();
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "The whole set of furniture has been added to your shopping cart", ""));
    }

    public void checkStock(Long itemId) {
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

    public void view(Long itemId) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("itemId", itemId);
        System.out.println(itemId);

        FacesContext.getCurrentInstance().getExternalContext().redirect("ViewItem.xhtml");

    }

    public CustomerWebModuleLocal getCwml() {
        return cwml;
    }

    public void setCwml(CustomerWebModuleLocal cwml) {
        this.cwml = cwml;
    }

    public Long getSetId() {
        return setId;
    }

    public void setSetId(Long setId) {
        this.setId = setId;
    }

    public SetEntity getSet() {
        return set;
    }

    public void setSet(SetEntity set) {
        this.set = set;
    }

    public List<String> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
    }

    public List<CustomerWebItemEntity> getItemList() {
        return itemList;
    }

    public void setItemList(List<CustomerWebItemEntity> itemList) {
        this.itemList = itemList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CustomerWebItemEntity getItem() {
        return item;
    }

    public void setItem(CustomerWebItemEntity item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
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

    public void setSelectedRate(String selectedRate) {
        this.selectedRate = selectedRate;
    }

    public List<CommentEntity> getAllComment() {
        return allComment;
    }

    public void setAllComment(List<CommentEntity> allComment) {
        this.allComment = allComment;
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
