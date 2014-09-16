/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.CommonInFrastructure;

import Entity.Factory.ProductEntity;
import Entity.Factory.RawMaterialEntity;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;

/**
 *
 * @author zhengyuan
 */
@Stateful
public class EnterpriseInventoryManagementModule_BOM implements EnterpriseInventoryManagementModule_BOMLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
      private EntityManager em;
      public EnterpriseInventoryManagementModule_BOM(){
    }
      private void addANewBOM(long productId, long rawMaterialId, Double quantity) throws Exception{
          ProductEntity product= em.find(ProductEntity.class, productId);
          RawMaterialEntity rawMaterial = em.find(RawMaterialEntity.class, rawMaterialId);
          if(product == null){
          throw new Exception ("Product is not found");
          }
          else if (rawMaterial == null){
              throw new Exception ("Raw Material is not found");
          }
          else{
             
          }
          
      }
      
      
}
