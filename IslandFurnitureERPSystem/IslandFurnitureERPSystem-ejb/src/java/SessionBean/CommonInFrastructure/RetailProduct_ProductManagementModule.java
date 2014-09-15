/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.CommonInFrastructure;

import Entity.Factory.BOMEntity;
import Entity.Factory.FactoryEntity;
import Entity.Factory.ProductEntity;
import Entity.Factory.RetailProductEntity;
import Entity.Store.StoreEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author dan
 */
@Stateful
public class RetailProduct_ProductManagementModule implements RetailProduct_ProductManagementModuleLocal {

    private EntityManager em;

    public RetailProduct_ProductManagementModule() {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void AddProduct(String name, String description, double price, String unit, BOMEntity bom) {
        System.out.println("RetailProduct_ProductManagementModule: AddProduct(): ");
        ProductEntity pe = new ProductEntity(name, description, price, unit, bom, Boolean.TRUE);
    }

    @Override
    public void DeleteProduct(Long productId) {
        System.out.println("RetailProduct_ProductManagementModule: DeleteProduct(): ");
        ProductEntity pe = em.find(ProductEntity.class, productId);
        pe.setDeleteFlag(Boolean.FALSE);
    }

    @Override
    public void ModifyProduct(Long productId, String name, String description,
            double price, String unit, BOMEntity bom) {
        System.out.println("RetailProduct_ProductManagementModule: ModifyProduct(): ");
        ProductEntity pe = em.find(ProductEntity.class, productId);
        pe.setName(name);
        pe.setDescription(description);
        pe.setPrice(price);
        pe.setUnit(unit);
        pe.setBom(bom);
    }

    @Override
    public List<ProductEntity> ListProduct() {
        System.out.println("RetailProduct_ProductManagementModule: ListProduct(): ");
        Query q = em.createQuery("SELECT t FROM ProductEntity t");
        List requiredProductList = new ArrayList();
        for(Object o:q.getResultList()){
            ProductEntity u = (ProductEntity) o;          
            requiredProductList.add(u);         
        }       
        return requiredProductList;
    }

    //public void SearchProduct(){}
    
    @Override
    public void AddRetailProduct(String name, String description) {
        System.out.println("RetailProduct_ProductManagementModule: AddRetailProduct(): ");
        RetailProductEntity pe = new RetailProductEntity(name, description, Boolean.TRUE);
    }

    @Override
    public void DeleteRetailProduct(Long retailProductId) {
        System.out.println("RetailProduct_ProductManagementModule: DeleteRetialProduct(): ");
        RetailProductEntity pe = em.find(RetailProductEntity.class, retailProductId);
        pe.setDeleteFlag(Boolean.FALSE);
    }

    @Override
    public void ModifyRetailProduct(Long retailProductId, String name, String description) {
        System.out.println("RetailProduct_ProductManagementModule: ModifyRetailProduct(): ");
        RetailProductEntity pe = em.find(RetailProductEntity.class, retailProductId);
        pe.setName(name);
        pe.setDescription(description);
    }

    @Override
    public List<RetailProductEntity> ListRetailProduct() {
        System.out.println("RetailProduct_ProductManagementModule: ListRetailProduct(): ");
        Query q = em.createQuery("SELECT t FROM RetailProductEntity t");
        List requiredRetailProductList = new ArrayList();
        for(Object o:q.getResultList()){
            RetailProductEntity u = (RetailProductEntity) o;          
            requiredRetailProductList.add(u);         
        }       
        return requiredRetailProductList;
    }
    
    //public void SearchRetailProduct(){}

}
