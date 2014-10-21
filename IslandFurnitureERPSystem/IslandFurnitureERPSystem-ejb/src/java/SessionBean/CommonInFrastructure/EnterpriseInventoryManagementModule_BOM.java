/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.CommonInFrastructure;

import Entity.Factory.BOMEntity;
import Entity.Factory.ProductEntity;
import Entity.Factory.RawMaterialEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author zhengyuan
 */
@Stateless
public class EnterpriseInventoryManagementModule_BOM implements EnterpriseInventoryManagementModule_BOMLocal {


/*
 Session Bean Functionality:
 1. add one raw material into a product BOM 
 2. update one raw material into a product BOM
 3. delete one raw material into a product BOM
 4. get all the raw material under one product BOM
*/
    
    
      
      public EnterpriseInventoryManagementModule_BOM(){
    }
      
      @PersistenceContext
      private EntityManager em;
      
 //======================================ADD NEW MATERIAL OF A PRODUCT IN BOM ========================================     
      
      @Override
      public Integer addANewBOM(Long productId, Long rawMaterialId, Double quantity) throws Exception{
      
          ProductEntity product= em.find(ProductEntity.class, productId);
          RawMaterialEntity rawMaterial = em.find(RawMaterialEntity.class, rawMaterialId);
          if(product == null){
          throw new Exception ("Product is not found");
          }
          else if (rawMaterial == null){
              return 2;
          }
          else{
             Boolean hasDeleteBefore = false;
             List<BOMEntity> bomList = product.getBOM();
             List<BOMEntity> deleteCheckList = new ArrayList<BOMEntity> ();
//             for(BOMEntity bome: bomList){
//                 
//                 
//             if(!Objects.equals(bome.getRawMaterial().getMaterialId(), rawMaterialId) ){
             
            //for delete Item that was previously add in the BOM
             for(BOMEntity be: bomList){
                 if(be.getIsDeleted()){
                     if(be.getRawMaterial().getMaterialId() == rawMaterialId){
                         hasDeleteBefore = true;
                         be.setAmount(quantity);
                         be.setIsDeleted(false);
                         break;
                     }
                 }
                     
             }
             if( !hasDeleteBefore){
             BOMEntity productBOM = new BOMEntity();
             productBOM.setProduct(product);
             productBOM.setRawMaterial(rawMaterial);
             productBOM.setAmount(quantity);
             productBOM.setUnit(rawMaterial.getUnit());
             productBOM.setIsDeleted(false);
             em.persist(productBOM);
             product.getBOM().add(productBOM);
             rawMaterial.getBomList().add(productBOM);
             em.flush();
             System.out.println("SessionBean: Add BOM: successfully!!");
             }
             
             return 0;
             }
             
          
      }

 //======================================UPDATE RAW MATERIAL QUANTITY IN BOM =================================     

      @Override
      public void updateANewBom( Long bomId, Double quantity) throws Exception{
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
      @Override
      public void deleteANewBom( Long bomId) throws Exception {
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

      
      @Override
      public List<BOMEntity> getAllBOM(Long productId){
          
          ProductEntity product= em.find(ProductEntity.class, productId);
          System.out.println("SessionBean: getAllBOM: product Id:" + product.getProductId());
          
          List<BOMEntity> temp = product.getBOM();
          System.out.println("SessionBean: getAllBOM: SIZE(); " + temp.size());
          
          
          List<BOMEntity> bomList = new ArrayList<BOMEntity>();
          for(BOMEntity bom : temp){
             BOMEntity be = bom;
             if(!be.getIsDeleted()){
                 bomList.add(bom);
             }
              
          }
          em.flush();
          return bomList;
          
      }
      
      
      
}
