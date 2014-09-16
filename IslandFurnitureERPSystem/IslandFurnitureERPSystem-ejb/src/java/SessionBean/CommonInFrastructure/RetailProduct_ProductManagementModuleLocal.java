/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.CommonInFrastructure;

import Entity.Factory.BOMEntity;
import Entity.Factory.ProductEntity;
import Entity.Factory.RetailProductEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author dan
 */
@Local
public interface RetailProduct_ProductManagementModuleLocal {

    public List<ProductEntity> ListProduct();

    public void AddProduct(String name, String description, Double price, String unit, Boolean deleteFlag);

    public void DeleteProduct(Long productId);

    public void ModifyProduct(Long productId, String name, String description, double price, String unit);

    public void AddRetailProduct(String name, String description);

    public void DeleteRetailProduct(Long retailProductId);

    public void ModifyRetailProduct(Long retailProductId, String name, String description);

    public List<RetailProductEntity> ListRetailProduct();
    
}
