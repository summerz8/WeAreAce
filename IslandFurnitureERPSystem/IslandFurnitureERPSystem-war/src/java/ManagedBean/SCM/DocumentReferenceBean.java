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
import javax.faces.bean.ManagedProperty;
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

    @ManagedProperty(value = "#{loginBean.department}")
    private String department;
    @ManagedProperty(value = "#{loginBean.departmentId}")
    private long factoryId;
    private List blockedStocks = new ArrayList();
    private List contracts = new ArrayList();
    private List goodsReceipts = new ArrayList();
    private List plannedOrders = new ArrayList();
    private List productionPlans = new ArrayList();
    private List purchaseOrders = new ArrayList();
    private List returnedStocks = new ArrayList();
    private List suppliers = new ArrayList();
    private List filteredSuppliers = new ArrayList();

    /**
     * Creates a new instance of DocumentReferenceBean
     */
    public DocumentReferenceBean() {
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(long factoryId) {
        this.factoryId = factoryId;
    }

    @PostConstruct
    public void init() {
        if (department.equals("H")) {
            filteredSuppliers = dr.viewAllSuppliers();
        } else {
            filteredSuppliers = dr.viewSupplierByFactory(factoryId);
        }
    }

    public List getBlockedStocks() {
        if (department.equals("H")) {
            blockedStocks = dr.viewAllBlockedStock();
        } else {
            blockedStocks = dr.viewBlockedStockByFactory(factoryId);
        }
        return blockedStocks;
    }

    public void setBlockedStocks(List blockedStocks) {
        this.blockedStocks = blockedStocks;
    }

    public List getContracts() {
        if (department.equals("H")) {
            contracts = dr.viewAllContracts();
        } else {
            contracts = dr.viewContractsByFactory(factoryId);
        }
        return contracts;
    }

    public void setContracts(List contracts) {
        this.contracts = contracts;
    }

    public List getGoodsReceipts() {
        if (department.equals("H")) {
            goodsReceipts = dr.viewAllGoodsReceipts();
        } else {
            goodsReceipts = dr.viewGoodsReceiptsByFactory(factoryId);
        }
        return goodsReceipts;
    }

    public void setGoodsReceipts(List goodsReceipts) {
        this.goodsReceipts = goodsReceipts;
    }

    public List getPlannedOrders() {
        if (department.equals("H")) {
            plannedOrders = dr.viewAllPlannedOrders();
        } else {
            plannedOrders = dr.viewPlannedOrdersByFactory(factoryId);
        }
        return plannedOrders;
    }

    public void setPlannedOrders(List plannedOrders) {
        this.plannedOrders = plannedOrders;
    }

    public List getProductionPlans() {
        if (department.equals("H")) {
            productionPlans = dr.viewAllProductionPlans();
        } else {
            productionPlans = dr.viewProductionPlansByFactory(factoryId);
        }
        return productionPlans;
    }

    public void setProductionPlans(List productionPlans) {
        this.productionPlans = productionPlans;
    }

    public List getPurchaseOrders() {
        if (department.equals("H")) {
            purchaseOrders = dr.viewAllPurchaseOrders();
        } else {
            purchaseOrders = dr.viewPurchaseOrdersByFactory(factoryId);
        }
        return purchaseOrders;
    }

    public void setPurchaseOrders(List purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    public List viewAllBlockedStock() {
        return dr.viewAllBlockedStock();

    }

    public List getReturnedStocks() {
        if (department.equals("H")) {
            returnedStocks = dr.viewAllReturnedProduct();
        } else {
            returnedStocks = dr.viewReturnedProductByFactory(factoryId);
        }
        return returnedStocks;
    }

    public void setReturnedStocks(List returnedStocks) {
        this.returnedStocks = returnedStocks;
    }

    public List getSuppliers() {
        if (department.equals("H")) {
            suppliers = dr.viewAllSuppliers();
        } else {
            suppliers = dr.viewSupplierByFactory(factoryId);
        }
        return suppliers;
    }

    public void setSuppliers(List suppliers) {
        this.suppliers = suppliers;
    }

    public List getFilteredSuppliers() {
        return filteredSuppliers;
    }

    public void setFilteredSuppliers(List filteredSuppliers) {
        this.filteredSuppliers = filteredSuppliers;
    }

    public List viewAllSuppliers() {
        return dr.viewAllSuppliers();

    }

}
    