/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.OCRM;

import Entity.Factory.FactoryEntity;
import Entity.Factory.FactoryProductAmountEntity;
import Entity.Factory.FactoryRetailProductAmountEntity;
import Entity.Factory.MRP.SalesForecastEntity;
import Entity.Store.OCRM.ProductSalesForecastEntity;
import Entity.Store.OCRM.SalesRecordEntity;
import Entity.Store.StoreEntity;
import Entity.Store.StoreEventEntity;
import Entity.Store.StoreProductEntity;
import Entity.Store.StoreRetailProductEntity;
import java.util.ArrayList;
import java.util.Calendar;
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
public class OCRMSalesForecastModule implements OCRMSalesForecastModuleLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<StoreProductEntity> listStoreProduct(Long storeId) {
        List<StoreProductEntity> storeProductList = new ArrayList<>();
        Query q = em.createQuery("SELECT po FROM StoreProductEntity po");
        if (storeId != null) {

            for (StoreProductEntity s : (List<StoreProductEntity>) q.getResultList()) {
                if (s.getStore().getStoreId().equals(storeId)) {
                    storeProductList.add(s);
                }
            }
            return storeProductList;
        } else {
            return (List<StoreProductEntity>) q.getResultList();
        }
    }

    @Override
    public List<StoreRetailProductEntity> listStoreRetailProduct(Long storeId) {
        List<StoreRetailProductEntity> storeRetailProductList = new ArrayList<>();
        Query q = em.createQuery("SELECT po FROM StoreRetailProductEntity po");
        if (storeId != null) {

            for (StoreRetailProductEntity s : (List<StoreRetailProductEntity>) q.getResultList()) {
                if (s.getStore().getStoreId().equals(storeId)) {
                    storeRetailProductList.add(s);
                }
            }
            return storeRetailProductList;
        } else {
            return (List<StoreRetailProductEntity>) q.getResultList();
        }
    }

    @Override
    public List<SalesRecordEntity> listSalesRecord(Long productId, String productType) {
        if (productType.equals("StoreProduct")) {
            Query q = em.createQuery("SELECT po FROM StoreProductEntity po");
            for (StoreProductEntity s : (List<StoreProductEntity>) q.getResultList()) {
                if (s.getStoreProductId().equals(productId)) {
                    return SortSalesRecordList(s.getSalesRecordList());
                }
            }
        } else {
            Query q = em.createQuery("SELECT po FROM StoreRetailProductEntity po");
            for (StoreRetailProductEntity s : (List<StoreRetailProductEntity>) q.getResultList()) {
                if (s.getStoreRetailProductId().equals(productId)) {
                    return SortSalesRecordList(s.getSalesRecordList());
                }
            }

        }
        return null;

    }

    @Override
    public List<ProductSalesForecastEntity> listProductSalesForecast(Long productId, String productType) {
        if (productType.equals("StoreProduct")) {
            Query q = em.createQuery("SELECT po FROM StoreProductEntity po");
            for (StoreProductEntity s : (List<StoreProductEntity>) q.getResultList()) {
                if (s.getStoreProductId().equals(productId)) {
                    return SortProductSalesForecastList(s.getProductSalesForecastList());
                }
            }
        } else {
            Query q = em.createQuery("SELECT po FROM StoreRetailProductEntity po");
            for (StoreRetailProductEntity s : (List<StoreRetailProductEntity>) q.getResultList()) {
                if (s.getStoreRetailProductId().equals(productId)) {
                    return SortProductSalesForecastList(s.getProductSalesForecastList());
                }
            }

        }
        return null;

    }

    @Override
    public String checkStoreProduct(Long productId) {
        StoreProductEntity storeProduct = em.find(StoreProductEntity.class, productId);
        if (!storeProduct.getProductSalesForecastList().isEmpty()) {
            ProductSalesForecastEntity productSalesForecast = storeProduct.getProductSalesForecastList().get(storeProduct.getProductSalesForecastList().size() - 1);
            Calendar targetPeriod = Calendar.getInstance();
            targetPeriod.add(Calendar.MONTH, 2);
            if (productSalesForecast.getTargetPeriod().get(Calendar.YEAR) == targetPeriod.get(Calendar.YEAR) && productSalesForecast.getTargetPeriod().get(Calendar.MONTH) == targetPeriod.get(Calendar.MONTH)) {
                return "Forecasted";
            } else {
                return "Unforecasted";
            }
        } else {
            return "Unforecasted";
        }
    }

    @Override
    public String checkStoreRetailProduct(Long productId) {
        StoreRetailProductEntity storeRetailProduct = em.find(StoreRetailProductEntity.class, productId);
        if (!storeRetailProduct.getProductSalesForecastList().isEmpty()) {

            ProductSalesForecastEntity productSalesForecast = storeRetailProduct.getProductSalesForecastList().get(storeRetailProduct.getProductSalesForecastList().size() - 1);
            Calendar targetPeriod = Calendar.getInstance();
            targetPeriod.add(Calendar.MONTH, 2);
            if (productSalesForecast.getTargetPeriod().get(Calendar.YEAR) == targetPeriod.get(Calendar.YEAR) && productSalesForecast.getTargetPeriod().get(Calendar.MONTH) == targetPeriod.get(Calendar.MONTH)) {
                return "Forecasted";
            } else {
                return "Unforecasted";
            }
        } else {
            return "Unforecasted";
        }
    }

    @Override
    public ProductSalesForecastEntity createProductSalesForecast(Long productId, String productType) {
        ProductSalesForecastEntity productSalesForecast = new ProductSalesForecastEntity();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 2);
        productSalesForecast.setTargetPeriod(cal);
        if (productType.equals("StoreProduct")) {
            StoreProductEntity temp = em.find(StoreProductEntity.class, productId);
            productSalesForecast.setStoreProduct(temp);
            productSalesForecast.setStore(temp.getStore());
            productSalesForecast.setAmount(0D);

            List<ProductSalesForecastEntity> productSalesForecastList = temp.getProductSalesForecastList();
            for (ProductSalesForecastEntity p : productSalesForecastList) {
                if (((p.getTargetPeriod().get(Calendar.YEAR) + 1) == cal.get(Calendar.YEAR)) && (p.getTargetPeriod().get(Calendar.MONTH) == cal.get(Calendar.MONTH))) {
                    productSalesForecast.setAmount(p.getAmount());
                }
            }
            return productSalesForecast;
        } else {
            StoreRetailProductEntity temp = em.find(StoreRetailProductEntity.class, productId);
            productSalesForecast.setStoreRetailProduct(temp);
            productSalesForecast.setStore(temp.getStore());
            productSalesForecast.setAmount(0D);

            List<ProductSalesForecastEntity> productSalesForecastList = temp.getProductSalesForecastList();
            for (ProductSalesForecastEntity p : productSalesForecastList) {
                if ((p.getTargetPeriod().get(Calendar.YEAR) == cal.get(Calendar.YEAR)) && (p.getTargetPeriod().get(Calendar.MONTH) == cal.get(Calendar.MONTH))) {
                    productSalesForecast.setAmount(p.getAmount());
                }
            }
            return productSalesForecast;
        }

    }

    @Override
    public ProductSalesForecastEntity confirmProductSalesForecast(Calendar period, Double NewAmount, Long storeId, String productType, Long productId) {
        ProductSalesForecastEntity productSalesForecast = new ProductSalesForecastEntity();
        productSalesForecast.setAmount(NewAmount);
        productSalesForecast.setTargetPeriod(period);
        productSalesForecast.setStore(em.find(StoreEntity.class, storeId));
        productSalesForecast.setStatus("Unconfirmed");
        if (productType.equals("StoreProduct")) {
            StoreProductEntity product = em.find(StoreProductEntity.class, productId);
            productSalesForecast.setStoreProduct(product);
            em.persist(productSalesForecast);
            product.getProductSalesForecastList().add(productSalesForecast);
            em.flush();
        } else {
            StoreRetailProductEntity product = em.find(StoreRetailProductEntity.class, productId);
            productSalesForecast.setStoreRetailProduct(product);
            em.persist(productSalesForecast);
            product.getProductSalesForecastList().add(productSalesForecast);
            em.flush();
        }

        return productSalesForecast;
    }

    @Override
    public Boolean checkAvailability(Long storeId) {
        boolean avalibility = true;
        ProductSalesForecastEntity productSalesForecast;
        Calendar targetPeriod = Calendar.getInstance();
        targetPeriod.add(Calendar.MONTH, 2);

        List<StoreProductEntity> storeProductList = new ArrayList<>();
        Query q = em.createQuery("SELECT ss FROM StoreProductEntity ss");
        for (StoreProductEntity s : (List<StoreProductEntity>) q.getResultList()) {
            if (s.getStore().getStoreId().equals(storeId)) {
                storeProductList.add(s);
                if (s.getProductSalesForecastList().isEmpty()) {
                    avalibility = false;
                } else {
                    productSalesForecast = s.getProductSalesForecastList().get(s.getProductSalesForecastList().size() - 1);
                    if ((productSalesForecast.getTargetPeriod().get(Calendar.YEAR) != targetPeriod.get(Calendar.YEAR)) || (productSalesForecast.getTargetPeriod().get(Calendar.MONTH) != targetPeriod.get(Calendar.MONTH))) {
                        avalibility = false;
                        return avalibility;
                    }
                }
            }
        }

        List<StoreRetailProductEntity> storeRetailProductList = new ArrayList<>();
        Query r = em.createQuery("SELECT r FROM StoreRetailProductEntity r");
        for (StoreRetailProductEntity sr : (List<StoreRetailProductEntity>) r.getResultList()) {
            if (sr.getStore().getStoreId().equals(storeId)) {
                storeRetailProductList.add(sr);
                if (sr.getProductSalesForecastList().isEmpty()) {
                    avalibility = false;
                } else {
                    productSalesForecast = sr.getProductSalesForecastList().get(sr.getProductSalesForecastList().size() - 1);
                    if ((productSalesForecast.getTargetPeriod().get(Calendar.YEAR) != targetPeriod.get(Calendar.YEAR)) || (productSalesForecast.getTargetPeriod().get(Calendar.MONTH) != targetPeriod.get(Calendar.MONTH))) {
                        avalibility = false;
                        return avalibility;
                    }
                }
            }
        }

        return avalibility;
    }

    @Override
    public List<SalesForecastEntity> createForecast(Long storeId) {
        Calendar targetPeriod = Calendar.getInstance();
        targetPeriod.add(Calendar.MONTH, 2);

        List<StoreProductEntity> storeProductList = new ArrayList<>();
        Query q = em.createQuery("SELECT ss FROM StoreProductEntity ss");
        for (StoreProductEntity s : (List<StoreProductEntity>) q.getResultList()) {
            if (s.getStore().getStoreId().equals(storeId)) {
                storeProductList.add(s);
            }
        }

        List<StoreRetailProductEntity> storeRetailProductList = new ArrayList<>();
        Query r = em.createQuery("SELECT r FROM StoreRetailProductEntity r");
        for (StoreRetailProductEntity sr : (List<StoreRetailProductEntity>) r.getResultList()) {
            if (sr.getStore().getStoreId().equals(storeId)) {
                storeRetailProductList.add(sr);
            }
        }

        StoreEntity store = em.find(StoreEntity.class, storeId);
        List<Long> factoryList = store.getFactoryList();
        List<SalesForecastEntity> salesForecastList = new ArrayList<>();
        for (Long factoryId : factoryList) {
            System.out.println(factoryId);
            SalesForecastEntity salesForecast = new SalesForecastEntity();
            salesForecast.setTargetPeriod(targetPeriod);
            salesForecast.setStore(store);
            salesForecast.setFactory(em.find(FactoryEntity.class, factoryId));

            for (StoreProductEntity s : storeProductList) {
                if (s.getFactoryProduct().getFactory().getFactoryId().equals(factoryId)) {
                    FactoryProductAmountEntity fpam = new FactoryProductAmountEntity();
                    fpam.setAmount(s.getProductSalesForecastList().get(s.getProductSalesForecastList().size() - 1).getAmount());
                    fpam.setFactoryProduct(s.getProductSalesForecastList().get(s.getProductSalesForecastList().size() - 1).getStoreProduct().getFactoryProduct());
                    fpam.setUnit(s.getProduct().getUnit());
                    s.getProductSalesForecastList().get(s.getProductSalesForecastList().size() - 1).setStatus("Confirmed");
                    storeProductList.remove(s);
                    em.persist(fpam);
                    salesForecast.getFactoryProductList().add(fpam);
                    if (storeProductList.isEmpty()) {
                        break;
                    }
                }
            }

            for (StoreRetailProductEntity sr : storeRetailProductList) {
                if (sr.getFactoryRetailProduct().getFactory().getFactoryId().equals(factoryId)) {
                    FactoryRetailProductAmountEntity frpam = new FactoryRetailProductAmountEntity();
                    frpam.setAmount(sr.getProductSalesForecastList().get(sr.getProductSalesForecastList().size() - 1).getAmount());
                    frpam.setFactoryRetailProduct(sr.getProductSalesForecastList().get(sr.getProductSalesForecastList().size() - 1).getStoreRetailProduct().getFactoryRetailProduct());
                    frpam.setUnit(sr.getRetailProduct().getUnit());
                    sr.getProductSalesForecastList().get(sr.getProductSalesForecastList().size() - 1).setStatus("Confirmed");
                    System.out.println("storeRetailProduct added: " + sr.getStoreRetailProductId() + " " + sr.getFactoryRetailProduct().getFactoryRetailProdctId());

                    em.persist(frpam);
                    salesForecast.getFactoryRetailProductList().add(frpam);
                }

            }

            em.persist(salesForecast);
            em.flush();

            salesForecastList.add(salesForecast);
        }
        return salesForecastList;
    }

    @Override
    public SalesForecastEntity getSalesForecast(Long salesForecastId) {

        return em.find(SalesForecastEntity.class, salesForecastId);

    }

    @Override
    public List<ProductSalesForecastEntity> SortProductSalesForecastList(List<ProductSalesForecastEntity> object) {
        List<ProductSalesForecastEntity> sorted = new ArrayList<>();
        int size = object.size();
        for (int a = 0; a < size; a++) {
            sorted.add(object.get(size - a - 1));
        }
        return sorted;
    }

    @Override
    public List<SalesRecordEntity> SortSalesRecordList(List<SalesRecordEntity> object) {
        List<SalesRecordEntity> sorted = new ArrayList<>();
        int size = object.size();
        for (int a = 0; a < size; a++) {
            sorted.add(object.get(size - a - 1));
        }
        return sorted;
    }

    @Override
    public Boolean CheckExistence(Long productId, String productType) {
        Calendar targetPeriod = Calendar.getInstance();
        targetPeriod.add(Calendar.MONTH, 2);

        if (productType.equals("StoreProduct")) {
            StoreProductEntity storeProduct = em.find(StoreProductEntity.class, productId);
            if (storeProduct.getProductSalesForecastList().isEmpty()) {
                return true;
            } else {
                ProductSalesForecastEntity productSalesForecast = storeProduct.getProductSalesForecastList().get(storeProduct.getProductSalesForecastList().size() - 1);
                return !(productSalesForecast.getTargetPeriod().get(Calendar.YEAR) == targetPeriod.get(Calendar.YEAR) && productSalesForecast.getTargetPeriod().get(Calendar.MONTH) == targetPeriod.get(Calendar.MONTH));
            }
        } else {
            StoreRetailProductEntity storeRetailProduct = em.find(StoreRetailProductEntity.class, productId);
            if (storeRetailProduct.getProductSalesForecastList().isEmpty()) {
                return true;
            } else {
                ProductSalesForecastEntity productSalesForecast = storeRetailProduct.getProductSalesForecastList().get(storeRetailProduct.getProductSalesForecastList().size() - 1);
                return !(productSalesForecast.getTargetPeriod().get(Calendar.YEAR) == targetPeriod.get(Calendar.YEAR) && productSalesForecast.getTargetPeriod().get(Calendar.MONTH) == targetPeriod.get(Calendar.MONTH));
            }
        }

    }

    @Override
    public ProductSalesForecastEntity getProductSalesForecast(Long productSalesForecastId) {

        return em.find(ProductSalesForecastEntity.class, productSalesForecastId);
    }

    @Override
    public void editProductSalesForecast(Long productSalesForecastId, Double NewAmount) {
        ProductSalesForecastEntity forecast = em.find(ProductSalesForecastEntity.class, productSalesForecastId);
        forecast.setAmount(NewAmount);
        em.persist(forecast);
        em.flush();
    }

    @Override
    public List<StoreEventEntity> getEventList(Long storeId) {
        Query q = em.createQuery("Select b from StoreEventEntity b where b.store.storeId =:sId");
        q.setParameter("sId", storeId);
        return (List<StoreEventEntity>) q.getResultList();
    }

    @Override
    public List<StoreEventEntity> getEventEffectList(Long storeEventId) {
        StoreEventEntity event = em.find(StoreEventEntity.class, storeEventId);
        Query q = em.createQuery("Select b from StoreEventEntity b where b.store.storeId =:sId and b.event.id =:eId ORDER BY b.startDate DESC");
        q.setParameter("sId", event.getStore().getStoreId());
        q.setParameter("eId", event.getEvent().getId());

        List<StoreEventEntity> result = (List<StoreEventEntity>) q.getResultList();
        System.out.println("111" + result.size());
        int size = result.size();
        Calendar today = Calendar.getInstance();
        for (int a = 0; a < size; a++) {
            if (result.get(a).getStartDate().after(today)) {
                result.remove(result.get(a));
                a--;
                size--;
            }
        }
        System.out.println("222" + result.size());

        return (List<StoreEventEntity>) result;

    }

}
