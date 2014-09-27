/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM;

import SessionBean.SCM.DocumentReferenceModuleLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Yoky
 */
@ManagedBean(name = "documentReferenceBean")
@ViewScoped
public class DocumentReferenceBean implements Serializable {

    @EJB
    private DocumentReferenceModuleLocal dr;

    private List allBlockedStocks = new ArrayList();
    private List allContracts = new ArrayList();
    private List allGoodsReceipts = new ArrayList();
    private List allPlannedOrders = new ArrayList();
    private List allProductionPlans = new ArrayList();
    private List allPurchaseOrders = new ArrayList();
    private List allReturnedStocks = new ArrayList();
    private List allSuppliers = new ArrayList();
    private List filteredSuppliers = new ArrayList();

    /**
     * Creates a new instance of DocumentReferenceBean
     */
    public DocumentReferenceBean() {
    }

    @PostConstruct
    public void init() {
        filteredSuppliers = this.viewAllSuppliers();
    }

    public List getAllBlockedStocks() {
        allBlockedStocks = this.viewAllBlockedStock();
        return allBlockedStocks;
    }

    public void setAllBlockedStocks(List allBlockedStocks) {
        this.allBlockedStocks = allBlockedStocks;
    }

    public List getAllContracts() {
        allContracts = this.viewAllContracts();
        return allContracts;
    }

    public void setAllContracts(List allContracts) {
        this.allContracts = allContracts;
    }

    public List getAllGoodsReceipts() {
        allGoodsReceipts = this.viewAllGoodsReceipts();
        return allGoodsReceipts;
    }

    public void setAllGoodsReceipts(List allGoodsReceipts) {
        this.allGoodsReceipts = allGoodsReceipts;
    }

    public List getAllPlannedOrders() {
        allPlannedOrders = this.viewAllPlannedOrders();
        return allPlannedOrders;
    }

    public void setAllPlannedOrders(List allPlannedOrders) {
        this.allPlannedOrders = allPlannedOrders;
    }

    public List getAllProductionPlans() {
        allProductionPlans = this.viewAllProductionPlans();
        return allProductionPlans;
    }

    public void setAllProductionPlans(List allProductionPlans) {
        this.allProductionPlans = allProductionPlans;
    }

    public List getAllPurchaseOrders() {
        allPurchaseOrders = this.viewAllPurchaseOrders();
        return allPurchaseOrders;
    }

    public void setAllPurchaseOrders(List allPurchaseOrders) {
        this.allPurchaseOrders = allPurchaseOrders;
    }

    public List getAllReturnedStocks() {
        allReturnedStocks = this.viewAllReturnedProduct();
        return allReturnedStocks;
    }

    public void setAllReturnedStocks(List allReturnedStocks) {
        this.allReturnedStocks = allReturnedStocks;
    }

    public List getAllSuppliers() {
        allSuppliers = this.viewAllSuppliers();
        return allSuppliers;
    }

    public void setAllSuppliers(List allSuppliers) {
        this.allSuppliers = allSuppliers;
    }

    public List getFilteredSuppliers() {
        return filteredSuppliers;
    }

    public void setFilteredSuppliers(List filteredSuppliers) {
        this.filteredSuppliers = filteredSuppliers;
    }

    public List viewAllBlockedStock() {
        return dr.viewAllBlockedStock();
        
    }

    public List viewAllReturnedProduct() {
        return dr.viewAllReturnedProduct();
    }

    public List viewAllProductionPlans() {
        return dr.viewAllProductionPlans();
    }

    public List viewAllPlannedOrders() {
        return dr.viewAllPlannedOrders();
    }

    public List viewAllPurchaseOrders() {
        return dr.viewAllPurchaseOrders();
    }

    public List viewAllGoodsReceipts() {
        return dr.viewAllGoodsReceipts();
    }

    public List viewAllContracts() {
        return dr.viewAllContracts();
    }

    public List viewAllSuppliers() {
        return dr.viewAllSuppliers();
        
    }

}
