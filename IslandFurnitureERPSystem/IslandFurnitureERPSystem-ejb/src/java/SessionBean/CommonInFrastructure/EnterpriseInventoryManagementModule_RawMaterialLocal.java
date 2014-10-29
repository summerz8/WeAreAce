/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.CommonInFrastructure;

import Entity.Factory.RawMaterialEntity;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author dan zy
 */
@Local
public interface EnterpriseInventoryManagementModule_RawMaterialLocal {

    public ArrayList<RawMaterialEntity> listRawMaterial();

    public int deleteRawMaterial(Long rawMaterialId) throws Exception;

    
    public void addRawMaterial(String name, String description, String unit);

    public void modifyRawMaterial(Long rawMaterialId, String name, String description, String unit) throws Exception;
       
   
}
