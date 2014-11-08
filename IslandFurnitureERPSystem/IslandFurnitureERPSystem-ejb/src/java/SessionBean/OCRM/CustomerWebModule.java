/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.OCRM;

import Entity.Factory.ProductEntity;
import Entity.Factory.RetailProductEntity;
import Entity.Store.OCRM.CommentEntity;
import Entity.Store.OCRM.CustomerWebItemEntity;
import Entity.Store.OCRM.CustomerWebRetailItemEntity;
import Entity.Store.OCRM.MemberEntity;
import Entity.Store.OCRM.SetEntity;
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
    public List<CustomerWebItemEntity> listItems(String web) {
        Query q = em.createQuery("SELECT q FROM CustomerWebItemEntity q");
        if (web == null) {
            System.out.println("none web info");

            return (List<CustomerWebItemEntity>) q.getResultList();
        } else {
            System.out.println(web);

            List<CustomerWebItemEntity> list = (List<CustomerWebItemEntity>) q.getResultList();
            List<CustomerWebItemEntity> returnList = new ArrayList<>();
            for (CustomerWebItemEntity c : list) {
                if (c.getWeb().equals(web)) {
                    returnList.add(c);
                }
            }
            return returnList;
        }
    }

    @Override
    public List<SetEntity> getSetList(String web) {
        Query q = em.createQuery("SELECT q FROM SetEntity q");
        if (web == null) {
            System.out.println("none web info");
            return (List<SetEntity>) q.getResultList();
        } else {
            System.out.println(web);
            List<SetEntity> list = (List<SetEntity>) q.getResultList();
            List<SetEntity> returnList = new ArrayList<>();
            for (SetEntity c : list) {
                if (c.getWeb().equals(web)) {
                    returnList.add(c);
                }
            }
            return returnList;
        }
    }

    @Override
    public void createItem(Long productId, String productName, String description, String selectedType, String picture, String web) {
        CustomerWebItemEntity customerWebItem = new CustomerWebItemEntity();
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

        CustomerWebItemEntity customerWebItem = em.find(CustomerWebItemEntity.class, itemId);

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
        CustomerWebItemEntity customerWebItem = em.find(CustomerWebItemEntity.class, itemId);
        customerWebItem.setProduct(null);

        List<SetEntity> setList;
        Query q = em.createQuery("SELECT q FROM SetEntity q");
        setList = (List<SetEntity>) q.getResultList();

        for (SetEntity s : setList) {
            List<CustomerWebItemEntity> list = s.getUnitList();
            for (CustomerWebItemEntity c : list) {
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
    public CustomerWebItemEntity getItem(Long itemId) {
        return em.find(CustomerWebItemEntity.class, itemId);
    }

    @Override
    public void deleteSet(Long setId) {
        SetEntity set = em.find(SetEntity.class, setId);
        em.remove(set);
    }

    @Override
    public SetEntity getSet(Long setId) {
        return em.find(SetEntity.class, setId);
    }

    @Override
    public void editSet(Long setId, String setName, String description, String picture) {
        SetEntity set = em.find(SetEntity.class, setId);
        set.setDescription(description);
        set.setName(setName);
        set.setPicture(picture);

        em.persist(set);
        em.flush();

    }

    @Override
    public void addItem(Long setId, Long itemId) {
        SetEntity set = em.find(SetEntity.class, setId);
        CustomerWebItemEntity item = em.find(CustomerWebItemEntity.class, itemId);
        set.getUnitList().add(item);

        em.persist(set);
        em.flush();
    }

    @Override
    public Long createSet(String setName, String description, String picture, String web) {
        SetEntity set = new SetEntity();
        set.setDescription(description);
        set.setName(setName);
        set.setPicture(picture);
        set.setWeb(web);
        em.persist(set);
        em.flush();

        return set.getId();
    }

    @Override
    public void addToShoppingCart(String email, Long itemId, int quantity) {

        MemberEntity member = cwml.getMember(email);
        CustomerWebItemEntity item = getItem(itemId);
        ShoppingCartItemEntity shoppingCartItem = new ShoppingCartItemEntity();
        shoppingCartItem.setQuantity(quantity);
        shoppingCartItem.setCustomerWebItem(item);
        em.persist(shoppingCartItem);

        member.getShoppingCartList().add(shoppingCartItem);
        em.flush();

    }

    @Override
    public List<CustomerWebRetailItemEntity> listRetailItems(String web) {
        Query q = em.createQuery("SELECT q FROM CustomerWebRetailItemEntity q");
        if (web == null) {
            System.out.println("none web info");
            return (List<CustomerWebRetailItemEntity>) q.getResultList();
        } else {
            System.out.println(web);
            List<CustomerWebRetailItemEntity> list = (List<CustomerWebRetailItemEntity>) q.getResultList();
            List<CustomerWebRetailItemEntity> returnList = new ArrayList<>();
            for (CustomerWebRetailItemEntity c : list) {
                if (c.getWeb().equals(web)) {
                    returnList.add(c);
                }
            }
            return returnList;
        }
    }

    @Override
    public CustomerWebRetailItemEntity getRetailItem(Long itemId) {
        return em.find(CustomerWebRetailItemEntity.class, itemId);
    }

    @Override
    public void createRetailItem(Long retailProductId, String retailProductName, String description, String picture, String selectWeb) {
        CustomerWebRetailItemEntity customerWebRetailItem = new CustomerWebRetailItemEntity();
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
        CustomerWebRetailItemEntity customerWebRetailItem = em.find(CustomerWebRetailItemEntity.class, itemId);
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
        CustomerWebRetailItemEntity customerWebRetailItem = em.find(CustomerWebRetailItemEntity.class, retailItemId);
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
                SetEntity set = em.find(SetEntity.class, Id);
                set.getComments().add(comment);
                em.flush();
                break;
            case "item":
                CustomerWebItemEntity item = em.find(CustomerWebItemEntity.class, Id);
                item.getComments().add(comment);
                em.flush();
                break;
            default:
                CustomerWebRetailItemEntity retail = em.find(CustomerWebRetailItemEntity.class, Id);
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
                SetEntity set = em.find(SetEntity.class, itemId);
                em.flush();
                break;
            case "item":
                CustomerWebItemEntity item = em.find(CustomerWebItemEntity.class, itemId);
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
                CustomerWebRetailItemEntity retail = em.find(CustomerWebRetailItemEntity.class, itemId);
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

}
