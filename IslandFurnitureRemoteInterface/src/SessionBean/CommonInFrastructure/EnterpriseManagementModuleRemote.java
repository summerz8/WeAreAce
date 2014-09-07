/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.CommonInFrastructure;

import javax.ejb.Remote;

/**
 *
 * @author dan
 */
@Remote
public interface EnterpriseManagementModuleRemote {   
    
    public void AddStaff(String department, Integer userLevel, String lastName, String firstName, String position, String gender);

    public void DeleteStaff();

    public void ModifyStaff();

    public void AddFactory();

    public void DeleteFactory();

    public void ModifyFactory();

    public void AddStore();

    public void DeleteStore();

    public void ModifyStore();

    public void AddProduct();

    public void DeleteProduct();

    public void ModifyProduct();

    public void AddRetailProduct();

    public void DeleteRetailProduct();

    public void ModifyRetailProduct();

    
    
}
