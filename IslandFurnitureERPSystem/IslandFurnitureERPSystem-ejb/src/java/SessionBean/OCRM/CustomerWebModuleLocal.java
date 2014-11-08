/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.OCRM;

import Entity.Factory.ProductEntity;
import Entity.Factory.RetailProductEntity;
import Entity.Store.OCRM.CustomerWebItemEntity;
import Entity.Store.OCRM.CustomerWebRetailItemEntity;
import Entity.Store.OCRM.SetEntity;
import Entity.Store.StoreEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author apple
 */
@Local
public interface CustomerWebModuleLocal {

    public List<CustomerWebItemEntity> listItems(String web);

    public CustomerWebItemEntity getItem(Long itemId);

    public List<SetEntity> getSetList(String web);

    public void createItem(Long productId, String productName, String description, String selectedType, String picture,String web);

    public void editItem(Long itemId, Long productId, String productName, String description, String selectedType, String picture);

    public List<ProductEntity> getProductList();

    public Long deleteItem(Long itemId);

    public void deleteSet(Long setId);

    public SetEntity getSet(Long setId);

    public void editSet(Long setId, String setName, String description, String picture);

    public void addItem(Long setId, Long itemId);

    public Long createSet(String setName, String description, String picture, String web);

    public void addToShoppingCart(String email, Long itemId, int quantity);

    public List<CustomerWebRetailItemEntity> listRetailItems(String web);

    public CustomerWebRetailItemEntity getRetailItem(Long retailItemId);
    
    public void createRetailItem(Long retailProductId, String retailProductName, String description, String picture, String web);

    public void editRetailItem(Long itemId, Long productId, String productName, String description, String picture);

    public void deleteRetailItem(Long retailItemId);
    
    public List<RetailProductEntity> getRetailProductList();
    
    public void createComment(Long Id,String type,String name,String content, Integer rate, String country);

    public List<StoreEntity> getStores(String web);
    
    public Double checkStock(Long storeId, Long itemId,String type);
}
