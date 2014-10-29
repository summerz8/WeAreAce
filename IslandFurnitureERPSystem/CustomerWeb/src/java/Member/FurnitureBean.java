/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Member;

import Entity.Store.OCRM.CustomerWebItemEntity;
import SessionBean.OCRM.CustomerWebModuleLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "furnitureBean")
@ViewScoped
public class FurnitureBean {

    @EJB
    private CustomerWebModuleLocal cwml;

    private List<CustomerWebItemEntity> itemList;

    public FurnitureBean() {
    }

    @PostConstruct
    public void init() {
        System.out.println("FurenitureBean:init()");
        itemList = cwml.listItems();
        System.out.println(itemList.get(0).getProductName());

    }

    public CustomerWebModuleLocal getCwml() {
        return cwml;
    }

    public void setCwml(CustomerWebModuleLocal cwml) {
        this.cwml = cwml;
    }

    public List<CustomerWebItemEntity> getItemList() {
        return itemList;
    }

    public void setItemList(List<CustomerWebItemEntity> itemList) {
        this.itemList = itemList;
    }

}
