/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.CommonInFrastructure;

import Entity.Factory.BOMEntity;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author zhengyuan
 */
@Remote
public interface EnterpriseInventoryManagementModule_BOMRemote {

    public Integer addANewBOM(Long productId, Long rawMaterialId, Double quantity) throws Exception;

    public void updateANewBom(Long bomId, Double quantity) throws Exception;

    public void deleteANewBom(Long bomId) throws Exception;

    public List<BOMEntity> getAllBOM(Long productId);
}
