/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.IM;

import Entity.Factory.SetEntity;
import Entity.Store.OCRM.CountrySetEntity;
import Entity.Store.StoreEntity;
import Entity.Store.StoreSetEntity;
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
public class StoreSetControl implements StoreSetControlLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<StoreSetEntity> getListOfStoreSet(Long storeId) {
//        Query q = em.createQuery("Select b from StoreSetEntity b");
///
        Query q = em.createQuery("Select b from StoreSetEntity b where b.store.storeId = :sId and b.deleteFlag = false");
        q.setParameter("sId", storeId);
        return (List<StoreSetEntity>) q.getResultList();
    }

    @Override
    public List<CountrySetEntity> getListOfSetNotInStore(Long storeId) {
        Query q1 = em.createQuery("Select c from CountrySetEntity c");
        List<CountrySetEntity> countrySetList = (List<CountrySetEntity>) q1.getResultList();
        Query q = em.createQuery("Select b from StoreSetEntity b where b.store.storeId = :sId and b.deleteFlag = false");
        q.setParameter("sId", storeId);

        List<StoreSetEntity> storeSetList = (List<StoreSetEntity>) q.getResultList();
        for (CountrySetEntity c : countrySetList) {
            for (StoreSetEntity s : storeSetList) {
                CountrySetEntity countrySet = s.getWebSet();
                if (c.equals(countrySet)) {
                    countrySetList.remove(c);
                    break;
                }
            }
        }
        return countrySetList;
    }

    @Override
    public void addStoreSet(Long storeId, Long countrySetId) {
        StoreEntity store = em.find(StoreEntity.class, storeId);
        CountrySetEntity countrySet = em.find(CountrySetEntity.class, countrySetId);
        SetEntity set = countrySet.getSet();
        StoreSetEntity storeSet = new StoreSetEntity(set, countrySet, store);
        em.persist(storeSet);
    }

    @Override
    public void deleteStoreSet(Long storeSetId) {
        StoreSetEntity storeSet = em.find(StoreSetEntity.class, storeSetId);
        SetEntity set = storeSet.getSet();
        List<StoreSetEntity> setList = set.getStoreSetList();
        for (StoreSetEntity s : setList) {
            if (s.equals(storeSet)) {
                setList.remove(s);
                break;
            }
        }
        set.setStoreSetList(setList);

        CountrySetEntity countrySet = storeSet.getWebSet();
        List<StoreSetEntity> setList1 = countrySet.getSetList();
        for (StoreSetEntity s : setList1) {
            if (s.equals(storeSet)) {
                setList1.remove(s);
                break;
            }
        }
        countrySet.setSetList(setList1);
        storeSet.setDeleteFlag(Boolean.TRUE);
        em.flush();
    }

    @Override
    public StoreSetEntity getStoreSet(Long storeSetId) {
        return em.find(StoreSetEntity.class, storeSetId);
    }
}
