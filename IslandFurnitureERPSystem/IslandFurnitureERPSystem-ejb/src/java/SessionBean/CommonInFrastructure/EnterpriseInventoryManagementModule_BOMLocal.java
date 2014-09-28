/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.CommonInFrastructure;

import Entity.Factory.BOMEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author zhengyuan
 */
@Local
public interface EnterpriseInventoryManagementModule_BOMLocal {
<<<<<<< HEAD

=======
  
    
>>>>>>> b8eb2397ec83f52290202fff9465a7b317d3a164
    public Integer addANewBOM(Long productId, Long rawMaterialId, Double quantity) throws Exception;

    public void updateANewBom(Long bomId, Double quantity) throws Exception;

    public void deleteANewBom(Long bomId) throws Exception;

    public List<BOMEntity> getAllBOM(Long productId);
<<<<<<< HEAD

=======
     
     
    
    
    
>>>>>>> b8eb2397ec83f52290202fff9465a7b317d3a164
}
