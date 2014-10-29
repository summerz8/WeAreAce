/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.OCRM;

import Entity.Store.OCRM.CustomerWebItemEntity;
import Entity.Store.OCRM.SetEntity;
import SessionBean.OCRM.CustomerWebModuleLocal;
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
@Named(value = "editSet")
@ViewScoped
public class EditSet {

    @EJB
    CustomerWebModuleLocal cwml;
    private Long setId;
    private SetEntity set;
    private String description;
    private String setName;
    private String picture;
    private List<CustomerWebItemEntity> itemList;
    private List<CustomerWebItemEntity> allitems;
    private String selectedItem;
    List<SelectItem> displayList;

    public EditSet() {
    }

    @PostConstruct
    public void init() {
        setId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("setId");
        set = cwml.getSet(setId);

        description = set.getDescription();
        setName = set.getName();
        picture = set.getPicture();
        itemList = set.getUnitList();
        allitems = cwml.listItems();
        displayList=new ArrayList<>();
         for (CustomerWebItemEntity s : allitems) {
            String t = s.getId()+ " " + s.getProductName();
            displayList.add(new SelectItem(s.getId(), t));
        }
    }

    public String upDate() {

        cwml.editSet(setId, setName, description);

        return "CustomerWebSingaporeSet?faces-redirect=true";
    }
    
    public String addItem(){
        Long itemId=Long.valueOf(selectedItem);
        cwml.addItem(setId, itemId);
        return "EditSet?faces-redirect=true";
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<CustomerWebItemEntity> getItemList() {
        return itemList;
    }

    public void setItemList(List<CustomerWebItemEntity> itemList) {
        this.itemList = itemList;
    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public List<SelectItem> getDisplayList() {
        return displayList;
    }

    public void setDisplayList(List<SelectItem> displayList) {
        this.displayList = displayList;
    }

    public List<CustomerWebItemEntity> getAllitems() {
        return allitems;
    }

    public void setAllitems(List<CustomerWebItemEntity> allitems) {
        this.allitems = allitems;
    }

    
}
