/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.CommonInFrastructure;

import Entity.Factory.RawMaterialEntity;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author dan zy
 */
@Remote
public interface EnterpriseInventoryManagementModule_RawMaterialRemote {

    public ArrayList<RawMaterialEntity> listRawMaterial();

    public void deleteRawMaterial(Long rawMaterialId) throws Exception;

    
    public void addRawMaterial(String name, String description, String unit) throws Exception;

    public void modifyRawMaterial(Long rawMaterialId, String name, String description, String unit) throws Exception;
       
   
}
