/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.CommonInFrastructure;

import Entity.Factory.BOMEntity;
import Entity.Factory.ProductEntity;
import Entity.Factory.RawMaterialEntity;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
/**
 *
 * @author zhengyuan
 */
@Stateful
public class EnterpriseInventoryManagementModule_BOM implements EnterpriseInventoryManagementModule_BOMLocal {


/*
 Session Bean Functionality:
 1. add one raw material into a product BOM 
 2. update one raw material into a product BOM
 3. delete one raw material into a product BOM
 4. get all the raw material under one product BOM
*/
    
    
      @PersistenceContext
      private EntityManager em;
      public EnterpriseInventoryManagementModule_BOM(){
    }
      
      
 //======================================ADD NEW MATERIAL OF A PRODUCT IN BOM ========================================     
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
             
             List<BOMEntity> bomList = product.getBom();
             for(BOMEntity bome: bomList){
                 
                 
             if(bome.getRawMaterial().getMaterialId() != rawMaterialId ){
             BOMEntity productBOM = new BOMEntity();
             productBOM.setProduct(product);
             productBOM.setRawMaterial(rawMaterial);
             productBOM.setAmount(quantity);
             productBOM.setUnit(null);
             em.persist(productBOM);
             product.getBom().add(productBOM);
             rawMaterial.getBomList().add(productBOM);
             em.flush();
             }
             
             }
             
          }
          
          
      }

 //======================================UPDATE RAW MATERIAL QUANTITY IN BOM =================================     

      private void updateANewBom( Long bomId, Double quantity) throws Exception{
          BOMEntity bom = em.find(BOMEntity.class, bomId);
          if(bom == null){
          throw new Exception ("Product is not found");
          }
          else {
              bom.setAmount(quantity);  
              em.flush();
          }  
      }
 
 //======================================UPDATE RAW MATERIAL QUANTITY IN BOM =================================     
      private void deleteANewBom( Long bomId) throws Exception {
          BOMEntity bom = em.find(BOMEntity.class, bomId);
          if(bom == null){
          throw new Exception ("Product is not found");
          }
          else {
            bom.setIsDeleted(true);
            em.flush();
          }  
      }

 //========================================== LIST BOM ===================================================     

      private List<BOMEntity> getAllBOM(Long productId){
          
          ProductEntity product= em.find(ProductEntity.class, productId);
          List<BOMEntity> bomList = product.getBom();
          em.flush();
          return bomList;
          
      }
      
      
      
}
