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

    public void DeleteProduct(Long productId);

    public void DeleteRetailProduct(Long retailProductId);

    public List<RetailProductEntity> ListRetailProduct();

    public void ModifyProduct(Long productId, String name, String description, Double price, Double memberPrice, String unit);

    public void AddRetailProduct(String name, String description, Double price, Double memberPrice, String unit);
   
    public void AddProduct(String name, String description, Double price, Double memberPrice, String unit);

    public void ModifyRetailProduct(Long retailProductId, String name, String unit, Double price, Double memberPrice, String description);

    
    
}
