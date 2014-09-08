/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.CommonInfrastructure;

import javax.ejb.Local;

/**
 *
 * @author dan
 */
@Local
public interface EnterpriseManagementModuleLocal {   
    
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
