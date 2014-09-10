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
public interface Factory_StoreManagementModuleLocal {

    public void AddFactory();

    public void DeleteFactory();

    public void ModifyFactory();

    public void AddStore();

    public void DeleteStore();

    public void ModifyStore();

    public void ListStore();

    public void searchStore();

    public void searchFactory();

    public void ListFactory();
    
}
