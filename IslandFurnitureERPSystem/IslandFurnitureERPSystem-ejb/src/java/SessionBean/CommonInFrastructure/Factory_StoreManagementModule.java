/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.CommonInFrastructure;

import javax.ejb.Stateful;

/**
 *
 * @author dan
 */
@Stateful
public class Factory_StoreManagementModule implements Factory_StoreManagementModuleLocal {

    public Factory_StoreManagementModule() {
    }

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
     @Override
    public void AddFactory() {
    }

    @Override
    public void DeleteFactory() {
    }

    @Override
    public void ModifyFactory() {
    }
    
    @Override
    public void ListFactory(){
    }
    
    @Override
    public void searchFactory(){}

    @Override
    public void AddStore() {
    }

    @Override
    public void DeleteStore() {
    }

    @Override
    public void ModifyStore() {
    }
    
    @Override
    public void ListStore(){}
    
    @Override
    public void searchStore(){
    
    }
}
