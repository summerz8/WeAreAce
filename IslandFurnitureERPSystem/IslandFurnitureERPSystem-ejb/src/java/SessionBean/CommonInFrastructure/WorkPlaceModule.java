/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.CommonInFrastructure;

import Entity.Factory.FactoryEntity;
import Entity.Store.StoreEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author apple
 */
@Stateless
public class WorkPlaceModule implements WorkPlaceModuleLocal {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<StoreEntity> listStores() {
        Query q=em.createQuery("SELECT s FROM StoreEntity s");
        return (List<StoreEntity>) q.getResultList();
    }
    
    @Override
    public List<FactoryEntity> listFactory() {
        Query q=em.createQuery("SELECT s FROM FactoryEntity s");
        return (List<FactoryEntity>) q.getResultList();
    }

}
