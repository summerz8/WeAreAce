/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.OCRM;

import Entity.Factory.ProductEntity;
import Entity.Store.OCRM.CustomerWebItemEntity;
import Entity.Store.OCRM.SetEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author apple
 */
@Local
public interface CustomerWebModuleLocal {

    public List<CustomerWebItemEntity> listItems();

    public CustomerWebItemEntity getItem(Long itemId);

    public List<SetEntity> getSetList();

    public void createItem(Long productId, String productName, String description, String selectedType, String selectWeb);

    public void editItem(Long itemId, Long productId, String productName, String description, String selectedType);

    public List<ProductEntity> getProductList();

    public Long deleteItem(Long itemId);
    
    public void deleteSet(Long setId);
    
    public SetEntity getSet(Long setId);
    
    public void editSet(Long setId,String setName, String description);
    
    public void addItem(Long setId,Long itemId);
    
    public Long createSet(String setName,String description,String picture);
}
