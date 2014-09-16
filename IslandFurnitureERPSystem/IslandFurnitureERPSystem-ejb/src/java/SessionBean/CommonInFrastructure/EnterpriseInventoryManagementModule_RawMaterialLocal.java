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

    public void modifyRawMaterial(long rawMaterialId, String name, String description) throws Exception;

    public void deleteRawMaterial(long rawMaterialId) throws Exception;

    public void addRawMaterial(String name, String description);
       
   
}
