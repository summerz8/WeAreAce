/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchasedItemAndSupplierManagementModule;

import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.FactoryRetailProductEntity;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author zhangshiyu
 */
@ManagedBean(name = "selectedFactoryItem")
@ViewScoped
public class SelectedFactoryItem implements Serializable {

    FactoryRawMaterialEntity selectedFRM;
    FactoryRetailProductEntity selectedFRP;

    public FactoryRawMaterialEntity getSelectedFRM() {
        return selectedFRM;
    }

    public void setSelectedFRM(FactoryRawMaterialEntity selectedFRM) {
        System.out.println("setSelectedFRM()");

        this.selectedFRM = selectedFRM;
    }

    public FactoryRetailProductEntity getSelectedFRP() {
        return selectedFRP;
    }

    public void setSelectedFRP(FactoryRetailProductEntity selectedFRP) {
        System.out.println("setSelectedFRP()");

        this.selectedFRP = selectedFRP;
    }

    public SelectedFactoryItem() {
    }

    public void selectedFRMItem(FactoryRawMaterialEntity frm) {
        System.out.println("selectFRMItem():");

        this.setSelectedFRM(frm);

    }

    public void selectedFRPItem(FactoryRetailProductEntity frp) {
        System.out.println("selectFRPItem():");

        this.setSelectedFRP(frp);

    }

}
