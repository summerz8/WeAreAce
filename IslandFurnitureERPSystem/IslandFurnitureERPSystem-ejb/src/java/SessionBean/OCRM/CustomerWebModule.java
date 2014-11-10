/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.OCRM;

import Entity.Factory.ProductEntity;
import Entity.Factory.RetailProductEntity;
import Entity.Factory.SetEntity;
import Entity.Store.OCRM.CommentEntity;
import Entity.Store.OCRM.CountryProductEntity;
import Entity.Store.OCRM.CountryRetailProductEntity;
import Entity.Store.OCRM.MemberEntity;
import Entity.Store.OCRM.CountrySetEntity;
import Entity.Store.OCRM.ShoppingCartItemEntity;
import Entity.Store.StoreEntity;
import Entity.Store.StoreProductEntity;
import Entity.Store.StoreRetailProductEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author apple
 */
@Stateless
public class CustomerWebModule implements CustomerWebModuleLocal {

    @PersistenceContext
    private EntityManager em;

    @EJB
    CustomerWebMemberModuleLocal cwml;

    @Override
    public List<CountryProductEntity> listItems(String web) {
        Query q = em.createQuery("SELECT q FROM CountryProductEntity q");
        if (web == null) {
            System.out.println("none web info");

            return (List<CountryProductEntity>) q.getResultList();
        } else {
            System.out.println(web);

            List<CountryProductEntity> list = (List<CountryProductEntity>) q.getResultList();
            List<CountryProductEntity> returnList = new ArrayList<>();
            for (CountryProductEntity c : list) {
                if (c.getWeb().equals(web)) {
                    returnList.add(c);
                }
            }
            return returnList;
        }
    }

    @Override
    public List<CountrySetEntity> getSetList(String web) {
        Query q = em.createQuery("SELECT q FROM CountrySetEntity q");
        if (web == null) {
            System.out.println("none web info");
            return (List<CountrySetEntity>) q.getResultList();
        } else {
            System.out.println(web);
            List<CountrySetEntity> list = (List<CountrySetEntity>) q.getResultList();
            List<CountrySetEntity> returnList = new ArrayList<>();
            for (CountrySetEntity c : list) {
                if (c.getWeb().equals(web)) {
                    returnList.add(c);
                }
            }
            return returnList;
        }
    }

    @Override
    public void createItem(Long productId, String productName, String description, String selectedType, String picture, String web) {
        CountryProductEntity customerWebItem = new CountryProductEntity();
        ProductEntity product = em.find(ProductEntity.class, productId);

        customerWebItem.setAvailability("Available");
        customerWebItem.setPrice(product.getPrice());
        customerWebItem.setMemberPrice(product.getMemberPrice());
        customerWebItem.setProductName(productName);
        customerWebItem.setDescription(description);
        customerWebItem.setType(selectedType);
        customerWebItem.setProduct(product);
        customerWebItem.setPicture(picture);
        customerWebItem.setWeb(web);

        em.persist(customerWebItem);
        em.flush();
    }

    @Override
    public List<ProductEntity> getProductList() {

        Query q = em.createQuery("SELECT q FROM ProductEntity q");
        return (List<ProductEntity>) q.getResultList();

    }

    @Override
    public void editItem(Long itemId, Long productId, String productName, String description, String selectedType, String picture) {

        CountryProductEntity customerWebItem = em.find(CountryProductEntity.class, itemId);

        ProductEntity product = em.find(ProductEntity.class, productId);

        customerWebItem.setPrice(product.getPrice());
        customerWebItem.setMemberPrice(product.getMemberPrice());
        customerWebItem.setProductName(productName);
        customerWebItem.setDescription(description);
        customerWebItem.setType(selectedType);
        customerWebItem.setProduct(product);
        customerWebItem.setPicture(picture);

        em.persist(customerWebItem);
        em.flush();

    }

    @Override
    public Long deleteItem(Long itemId) {
        CountryProductEntity customerWebItem = em.find(CountryProductEntity.class, itemId);
        customerWebItem.setProduct(null);

        List<CountrySetEntity> setList;
        Query q = em.createQuery("SELECT q FROM CountrySetEntity q");
        setList = (List<CountrySetEntity>) q.getResultList();

        for (CountrySetEntity s : setList) {
            List<CountryProductEntity> list = s.getUnitList();
            for (CountryProductEntity c : list) {
                if (c.getId().equals(itemId)) {
                    return s.getId();
                }
            }
        }

        em.remove(customerWebItem);
        em.flush();
        return 0L;
    }

    @Override
    public CountryProductEntity getItem(Long itemId) {
        return em.find(CountryProductEntity.class, itemId);
    }

    @Override
    public void deleteSet(Long setId) {
        CountrySetEntity set = em.find(CountrySetEntity.class, setId);
        em.remove(set);
    }

    @Override
    public CountrySetEntity getSet(Long setId) {
        return em.find(CountrySetEntity.class, setId);
    }

    @Override
    public void editSet(Long setId, String setName, String description, String picture) {
        CountrySetEntity set = em.find(CountrySetEntity.class, setId);
        set.setDescription(description);
        set.setName(setName);
        set.setPicture(picture);

        em.persist(set);
        em.flush();

    }

    @Override
    public void addItem(Long setId, Long itemId) {
        CountrySetEntity set = em.find(CountrySetEntity.class, setId);
        CountryProductEntity item = em.find(CountryProductEntity.class, itemId);
        set.getUnitList().add(item);

        em.persist(set);
        em.flush();
    }

    @Override
    public Long createSet(Long setId, String web) {
        CountrySetEntity set = new CountrySetEntity();
        SetEntity globalSet = em.find(SetEntity.class, setId);
        set.setDescription(globalSet.getDescription());
        set.setName(globalSet.getName());
        set.setWeb(web);

        List<ProductEntity> productList = globalSet.getProductList();
        Query q = em.createQuery("Select b from CountryProductEntity b where b.web = :sId");
        q.setParameter("sId", web);
        boolean exsit;
        List<CountryProductEntity> countryProductList = (List<CountryProductEntity>) q.getResultList();
        for (ProductEntity p : productList) {
            exsit = false;
            for (CountryProductEntity c : countryProductList) {
                if (c.getProduct().equals(p)) {
                    set.getUnitList().add(c);
                    exsit = true;
                    break;
                }
            }
            if (!exsit) {
                CountryProductEntity cp = new CountryProductEntity();
                cp.setMemberPrice(p.getMemberPrice());
                cp.setDescription(p.getDescription());
                cp.setPrice(p.getPrice());
                cp.setProduct(p);
                cp.setProductName(p.getName());
                cp.setWeb(web);
                em.persist(cp);
                set.getUnitList().add(cp);
                em.flush();
            } else {

            }
        }
        em.persist(set);
        em.flush();

        return set.getId();
    }

    @Override
    public void addToShoppingCart(String email, Long itemId, int quantity) {

        MemberEntity member = cwml.getMember(email);
        CountryProductEntity item = getItem(itemId);
        ShoppingCartItemEntity shoppingCartItem = new ShoppingCartItemEntity();
        shoppingCartItem.setQuantity(quantity);
        shoppingCartItem.setCustomerWebItem(item);
        em.persist(shoppingCartItem);

        member.getShoppingCartList().add(shoppingCartItem);
        em.flush();

    }

    @Override
    public List<CountryRetailProductEntity> listRetailItems(String web) {
        Query q = em.createQuery("SELECT q FROM CountryRetailProductEntity q");
        if (web == null) {
            System.out.println("none web info");
            return (List<CountryRetailProductEntity>) q.getResultList();
        } else {
            System.out.println(web);
            List<CountryRetailProductEntity> list = (List<CountryRetailProductEntity>) q.getResultList();
            List<CountryRetailProductEntity> returnList = new ArrayList<>();
            for (CountryRetailProductEntity c : list) {
                if (c.getWeb().equals(web)) {
                    returnList.add(c);
                }
            }
            return returnList;
        }
    }

    @Override
    public CountryRetailProductEntity getRetailItem(Long itemId) {
        return em.find(CountryRetailProductEntity.class, itemId);
    }

    @Override
    public void createRetailItem(Long retailProductId, String retailProductName, String description, String picture, String selectWeb) {
        CountryRetailProductEntity customerWebRetailItem = new CountryRetailProductEntity();
        RetailProductEntity product = em.find(RetailProductEntity.class, retailProductId);

        customerWebRetailItem.setProduct(product);
        customerWebRetailItem.setDescription(description);
        customerWebRetailItem.setPrice(product.getPrice());
        customerWebRetailItem.setProductName(retailProductName);
        customerWebRetailItem.setPicture(picture);
        customerWebRetailItem.setWeb(selectWeb);

        em.persist(customerWebRetailItem);
        em.flush();

    }

    @Override
    public void editRetailItem(Long itemId, Long productId, String retailProductName, String description, String picture) {
        CountryRetailProductEntity customerWebRetailItem = em.find(CountryRetailProductEntity.class, itemId);
        RetailProductEntity product = em.find(RetailProductEntity.class, productId);

        customerWebRetailItem.setProduct(product);
        customerWebRetailItem.setDescription(description);
        customerWebRetailItem.setPrice(product.getPrice());
        customerWebRetailItem.setProductName(retailProductName);
        customerWebRetailItem.setPicture(picture);

        em.persist(customerWebRetailItem);
        em.flush();
    }

    @Override
    public void deleteRetailItem(Long retailItemId) {
        CountryRetailProductEntity customerWebRetailItem = em.find(CountryRetailProductEntity.class, retailItemId);
        em.remove(customerWebRetailItem);
    }

    @Override
    public List<RetailProductEntity> getRetailProductList() {

        Query q = em.createQuery("SELECT q FROM RetailProductEntity q");
        return (List<RetailProductEntity>) q.getResultList();

    }

    @Override
    public void createComment(Long Id, String type, String name, String content, Integer rate, String country) {
        CommentEntity comment = new CommentEntity(name, content, rate, country);
        em.persist(comment);
        switch (type) {
            case "set":
                CountrySetEntity set = em.find(CountrySetEntity.class, Id);
                set.getComments().add(comment);
                em.flush();
                break;
            case "item":
                CountryProductEntity item = em.find(CountryProductEntity.class, Id);
                item.getComments().add(comment);
                em.flush();
                break;
            default:
                CountryRetailProductEntity retail = em.find(CountryRetailProductEntity.class, Id);
                retail.getComments().add(comment);
                em.flush();
                break;
        }

    }

    @Override
    public List<StoreEntity> getStores(String web) {
        Query q = em.createQuery("SELECT q FROM StoreEntity q");
        List<StoreEntity> temp = (List<StoreEntity>) q.getResultList();
        List<StoreEntity> list = new ArrayList<>();
        for (StoreEntity s : temp) {
            if (s.getCountry().equals(web)) {
                list.add(s);
            }
        }
        return list;
    }

    @Override
    public Double checkStock(Long storeId, Long itemId, String type) {
        Double stock = -1D;

        switch (type) {
            case "set":
                CountrySetEntity set = em.find(CountrySetEntity.class, itemId);
                em.flush();
                break;
            case "item":
                CountryProductEntity item = em.find(CountryProductEntity.class, itemId);
                Collection<StoreProductEntity> storeProductList = item.getProduct().getStoreProducts();
                System.out.println("checkStock():1");
                for (StoreProductEntity s : storeProductList) {
                    System.out.println(s.getStoreProductId());
                    if (s.getStore().getStoreId().equals(storeId)) {
                        stock = s.getUnrestrictedInventory();
                        System.out.println(stock);
                        break;
                    }
                }
                em.flush();
                break;
            default:
                CountryRetailProductEntity retail = em.find(CountryRetailProductEntity.class, itemId);
                Collection<StoreRetailProductEntity> storeRetailProductList = retail.getProduct().getStoreRetailProducts();
                System.out.println("checkStock():1");
                for (StoreRetailProductEntity s : storeRetailProductList) {
                    System.out.println(s.getStoreRetailProductId());
                    if (s.getStore().getStoreId().equals(storeId)) {
                        stock = s.getUnrestrictedInventory();
                        System.out.println(stock);
                        break;
                    }
                }
                em.flush();
                break;
        }

        return stock;
    }

    @Override
    public List<SetEntity> getGlobalSetList() {
        Query q = em.createQuery("SELECT q FROM SetEntity q");
        return (List<SetEntity>) q.getResultList();
    }

    @Override
    public void deleteItemInSet(Long countrySetId, Long countryProductId) {
        CountrySetEntity set=em.find(CountrySetEntity.class, countrySetId);
        List<CountryProductEntity> productList=set.getUnitList();
        for(CountryProductEntity c: productList){
            if(c.getId().equals(countryProductId)){
                productList.remove(c);
                break;
            }
        }
        set.setUnitList(productList);
        em.flush();
    }

}
