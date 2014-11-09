/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.OCRM;

import Entity.Factory.ProductEntity;
import Entity.Store.OCRM.CustomerWebItemEntity;
import Entity.Store.OCRM.MemberEntity;
import Entity.Store.OCRM.SetEntity;
import Entity.Store.OCRM.ShoppingCartItemEntity;
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
    public List<CustomerWebItemEntity> listItems() {
        Query q = em.createQuery("SELECT q FROM CustomerWebItemEntity q");
        return (List<CustomerWebItemEntity>) q.getResultList();
    }

    @Override
    public List<SetEntity> getSetList() {
        Query q = em.createQuery("SELECT q FROM SetEntity q");
        return (List<SetEntity>) q.getResultList();
    }

    @Override
    public void createItem(Long productId, String productName, String description, String selectedType, String selectWeb) {
        CustomerWebItemEntity customerWebItem = new CustomerWebItemEntity();
        ProductEntity product = em.find(ProductEntity.class, productId);

        customerWebItem.setAvailability("Available");
        customerWebItem.setPrice(product.getPrice());
        customerWebItem.setMemberPrice(product.getMemberPrice());
        customerWebItem.setProductName(productName);
        customerWebItem.setDescription(description);
        customerWebItem.setType(selectedType);
        customerWebItem.setProduct(product);
        customerWebItem.setPicture("set1.png");

        em.persist(customerWebItem);
        em.flush();
    }

    @Override
    public List<ProductEntity> getProductList() {

        Query q = em.createQuery("SELECT q FROM ProductEntity q");
        return (List<ProductEntity>) q.getResultList();

    }

    @Override
    public void editItem(Long itemId, Long productId, String productName, String description, String selectedType) {

        CustomerWebItemEntity customerWebItem = em.find(CustomerWebItemEntity.class, itemId);

        ProductEntity product = em.find(ProductEntity.class, productId);

        customerWebItem.setPrice(product.getPrice());
        customerWebItem.setMemberPrice(product.getMemberPrice());
        customerWebItem.setProductName(productName);
        customerWebItem.setDescription(description);
        customerWebItem.setType(selectedType);
        customerWebItem.setProduct(product);
        
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

//    @Override
//    public void editSet(Long itemId, Long productId, String productName, String description, String selectedType) {
//
//        CustomerWebItemEntity customerWebItem = em.find(CustomerWebItemEntity.class, itemId);
//
//        ProductEntity product = em.find(ProductEntity.class, productId);
//
//        customerWebItem.setPrice(product.getPrice());
//        customerWebItem.setMemberPrice(product.getMemberPrice());
//        customerWebItem.setProductName(productName);
//        customerWebItem.setDescription(description);
//        customerWebItem.setType(selectedType);
//        customerWebItem.setProduct(product);
//        customerWebItem.setPicture("set1.png");
//
//        em.persist(customerWebItem);
//        em.flush();
//
//    }
    @Override
    public SetEntity getSet(Long setId) {
        return em.find(SetEntity.class, setId);
    }

    @Override
    public void editSet(Long setId, String setName, String description) {
        SetEntity set = em.find(SetEntity.class, setId);
        set.setDescription(description);
        set.setName(setName);

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
    public Long createSet(String setName, String description, String picture) {
        SetEntity set = new SetEntity();
        set.setDescription(description);
        set.setName(setName);
        set.setPicture(picture);

        em.persist(set);
        em.flush();

        return set.getId();
    }

    @Override
    public void addToShoppingCart(String email, Long itemId, int quantity){
        
        MemberEntity member=cwml.getMember(email);
        CustomerWebItemEntity item=getItem(itemId);
        ShoppingCartItemEntity shoppingCartItem=new ShoppingCartItemEntity();
        shoppingCartItem.setQuantity(quantity);
        shoppingCartItem.setCustomerWebItem(item);
        em.persist(shoppingCartItem);
        
        
        member.getShoppingCartList().add(shoppingCartItem);
        em.flush();
        
    }

}
