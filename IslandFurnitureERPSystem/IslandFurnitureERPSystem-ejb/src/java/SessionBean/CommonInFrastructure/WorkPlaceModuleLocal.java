/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.CommonInFrastructure;

import Entity.Factory.FactoryEntity;
import Entity.Store.StoreEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author apple
 */
@Local
public interface WorkPlaceModuleLocal {

    public Integer newMessages(String userId);

    public Integer msgTobeProcessed(String userId);

    public Integer ticketSubmitted(String userId);

    public Integer ticketInReview(String userId);

    public Integer ticketInProcess(String userId);

    public Double revK(String userId, Long departmentId);

    public Double revSP(String userId, Long departmentId);

    public Double stockFP(String userId, Long departmentId);

    public Double revSRP(String userId, Long departmentId);

    public Double stockFRP(String userId, Long departmentId);

    public Double stockFRM(String userId, Long departmentId);

    public List<StoreEntity> listStores();

    public List<FactoryEntity> listFactory();
    
    public StoreEntity getStore(Long storeId);

    public FactoryEntity getFactory(Long factoryId);
}
