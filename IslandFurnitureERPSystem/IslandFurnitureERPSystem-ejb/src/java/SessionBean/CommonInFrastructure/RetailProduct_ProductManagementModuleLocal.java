/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.CommonInFrastructure;

import javax.ejb.Local;

/**
 *
 * @author dan
 */
@Local
public interface RetailProduct_ProductManagementModuleLocal {

    public void ListRetailProduct();

    public void ModifyRetailProduct();

    public void DeleteRetailProduct();

    public void AddRetailProduct();

    public void ModifyProduct();

    public void DeleteProduct();

    public void AddProduct();

    public void ListProduct();
    
}
