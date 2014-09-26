/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

import Entity.Factory.FactoryEntity;
import Entity.Factory.FactoryProductAmountEntity;
import Entity.Factory.FactoryProductEntity;
import Entity.Factory.FactoryRetailProductAmountEntity;
import Entity.Factory.MRP.IntegratedSalesForecastEntity;
import Entity.Factory.MRP.SalesForecastEntity;
import java.util.ArrayList;
import java.util.Calendar;
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
public class SalesForecastModule implements SalesForecastModuleLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public SalesForecastEntity GetSalesForecast(Long salesForecastId) {
        return em.find(SalesForecastEntity.class, salesForecastId);
    }

    @Override
    public List<SalesForecastEntity> ListSalesForecast(Long storeId, Object product, Calendar period) {
        //List sales forecast based on 3 searching criteria: store, product(self-produced or retail product, forecast targetPeriod)
        List<SalesForecastEntity> list = new ArrayList<>();
        List<SalesForecastEntity> templist = new ArrayList<>();
        try {

            Query query = em.createQuery("SELECT t FROM SalesForecastEntity t");

            templist = (List<SalesForecastEntity>) query.getResultList();

            if (product == null) {

                //search criteria:   search all products for a certain targetPeriod in certain store
                if (storeId != null && period != null) {
                    while (!templist.isEmpty()) {
                        if (storeId.equals(templist.get(0).getStore().getStoreId()) && period.equals(templist.get(0).getTargetPeriod())) {
                            list.add(templist.get(0));
                        }
                        templist.remove(0);
                    }

                } //search criteria:  search all products for certain targetPeriod in all stores
                else if (storeId == null && period != null) {
                    while (!templist.isEmpty()) {
                        if (period.equals(templist.get(0).getTargetPeriod())) {
                            list.add(templist.get(0));
                        }
                        templist.remove(0);
                    }
                } //search criteria:  search all products for all targetPeriod in certain store
                else if (storeId != null && period == null) {
                    while (!templist.isEmpty()) {
                        if (storeId.equals(templist.get(0).getStore().getStoreId())) {
                            list.add(templist.get(0));
                        }
                        templist.remove(0);
                    }
                } //search criteria:  search all products for all targetPeriod in all stores  
                else if (storeId == null && period == null) {

                    return templist;
                }

                return list;
            } else if (product.getClass().equals(FactoryProductEntity.class)) {
                List<FactoryProductAmountEntity> tempFactoryProductAmountList = new ArrayList<>();
                //search criteria:  search certain self-produced product for certain targetPeriod in certain stores
                if (storeId != null && period != null) {
                    while (!templist.isEmpty()) {
                        if (storeId.equals(templist.get(0).getStore().getStoreId()) && period.equals(templist.get(0).getTargetPeriod())) {
                            tempFactoryProductAmountList = templist.get(0).getFactoryProductList();
                            while (!tempFactoryProductAmountList.isEmpty()) {
                                if (tempFactoryProductAmountList.get(0).getFactoryProduct().equals(product)) {
                                    list.add(templist.get(0));
                                }
                                tempFactoryProductAmountList.remove(0);
                            }
                        }
                        templist.remove(0);
                    }
                } //search criteria:  search certain self-produced product for certain targetPeriod in all stores
                else if (storeId == null && period != null) {
                    while (!templist.isEmpty()) {
                        if (period.equals(templist.get(0).getTargetPeriod())) {
                            tempFactoryProductAmountList = templist.get(0).getFactoryProductList();
                            while (!tempFactoryProductAmountList.isEmpty()) {
                                if (tempFactoryProductAmountList.get(0).getFactoryProduct().equals(product)) {
                                    list.add(templist.get(0));
                                }
                                tempFactoryProductAmountList.remove(0);
                            }
                        }
                        templist.remove(0);
                    }
                } //search criteria:  search certain self-produced product for all targetPeriod in certain stores
                else if (storeId != null && period == null) {
                    while (!templist.isEmpty()) {
                        if (storeId.equals(templist.get(0).getStore().getStoreId())) {
                            tempFactoryProductAmountList = templist.get(0).getFactoryProductList();
                            while (!tempFactoryProductAmountList.isEmpty()) {
                                if (tempFactoryProductAmountList.get(0).getFactoryProduct().equals(product)) {
                                    list.add(templist.get(0));
                                }
                                tempFactoryProductAmountList.remove(0);
                            }
                        }
                    }
                } //search criteria:  search certain self-produced products for all targetPeriod in all stores
                else if (storeId == null && period == null) {
                    while (!templist.isEmpty()) {
                        tempFactoryProductAmountList = templist.get(0).getFactoryProductList();
                        while (!tempFactoryProductAmountList.isEmpty()) {
                            if (tempFactoryProductAmountList.get(0).getFactoryProduct().equals(product)) {
                                list.add(templist.get(0));
                            }
                            tempFactoryProductAmountList.remove(0);
                        }
                    }
                    return templist;
                }

                return list;

            } else {

                List<FactoryRetailProductAmountEntity> tempFactoryRetailProductAmountList = new ArrayList<>();
                //search criteria:  search certain retail product for certain targetPeriod in certain stores
                if (storeId != null && period != null) {
                    while (!templist.isEmpty()) {
                        if (storeId.equals(templist.get(0).getStore().getStoreId()) && period.equals(templist.get(0).getTargetPeriod())) {
                            tempFactoryRetailProductAmountList = templist.get(0).getFactoryRetailProductList();
                            while (!tempFactoryRetailProductAmountList.isEmpty()) {
                                if (tempFactoryRetailProductAmountList.get(0).getFactoryRetailProduct().equals(product)) {
                                    list.add(templist.get(0));
                                }
                                tempFactoryRetailProductAmountList.remove(0);
                            }
                        }
                        templist.remove(0);
                    }
                } //search criteria:  search certain retail product for certain targetPeriod in all stores
                else if (storeId == null && period != null) {
                    while (!templist.isEmpty()) {
                        if (period.equals(templist.get(0).getTargetPeriod())) {
                            tempFactoryRetailProductAmountList = templist.get(0).getFactoryRetailProductList();
                            while (!tempFactoryRetailProductAmountList.isEmpty()) {
                                if (tempFactoryRetailProductAmountList.get(0).getFactoryRetailProduct().equals(product)) {
                                    list.add(templist.get(0));
                                }
                                tempFactoryRetailProductAmountList.remove(0);
                            }
                        }
                        templist.remove(0);
                    }
                } //search criteria:  search certain retail product for all targetPeriod in certain stores
                else if (storeId != null && period == null) {
                    while (!templist.isEmpty()) {
                        if (storeId.equals(templist.get(0).getStore().getStoreId())) {
                            tempFactoryRetailProductAmountList = templist.get(0).getFactoryRetailProductList();
                            while (!tempFactoryRetailProductAmountList.isEmpty()) {
                                if (tempFactoryRetailProductAmountList.get(0).getFactoryRetailProduct().equals(product)) {
                                    list.add(templist.get(0));
                                }
                                tempFactoryRetailProductAmountList.remove(0);
                            }
                        }
                    }
                } //search criteria:  search certain retail products for all targetPeriod in all stores
                else if (storeId == null && period == null) {
                    while (!templist.isEmpty()) {
                        tempFactoryRetailProductAmountList = templist.get(0).getFactoryRetailProductList();
                        while (!tempFactoryRetailProductAmountList.isEmpty()) {
                            if (tempFactoryRetailProductAmountList.get(0).getFactoryRetailProduct().equals(product)) {
                                list.add(templist.get(0));
                            }
                            tempFactoryRetailProductAmountList.remove(0);
                        }
                    }
                    return templist;
                }

                return list;

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    @Override
    public IntegratedSalesForecastEntity IntegrateSalesForecast(Long productId, Calendar period) {

        try {
            System.out.println("inegrateSalesForecast: 1");
            if (period == null) {
                Calendar targetPeriod = Calendar.getInstance();
                targetPeriod.set(targetPeriod.get(Calendar.YEAR), targetPeriod.get(Calendar.MONTH), 1, 0, 0, 0);
                targetPeriod.add(Calendar.MONTH, 2);
                FactoryProductEntity product = em.find(FactoryProductEntity.class, productId);

                List<SalesForecastEntity> templist ;

                IntegratedSalesForecastEntity integratedSalesForecast = new IntegratedSalesForecastEntity();
                integratedSalesForecast.setAmount(0D);
                integratedSalesForecast.setTargetPeriod(targetPeriod);
                integratedSalesForecast.setFactoryProduct(product);

                Query query = em.createQuery("SELECT t FROM SalesForecastEntity t ORDER BY t.targetPeriod DESC");
                templist = (List<SalesForecastEntity>) query.getResultList();
                System.out.println("inegrateSalesForecast: 2");
                // If it is an integrated sales forecast for self-produced product
                if (product.getClass().equals(FactoryProductEntity.class)) {
                    FactoryProductEntity factoryProduct = (FactoryProductEntity) product;
                    integratedSalesForecast.setFactory(factoryProduct.getFactory());

                    List<FactoryProductAmountEntity> tempFactoryProductAmountList = new ArrayList<>();
                    System.out.println("inegrateSalesForecast: 3");
                    while (!templist.isEmpty()) {
                        boolean hasIt = false;
                        if ((targetPeriod.get(Calendar.YEAR) == templist.get(0).getTargetPeriod().get(Calendar.YEAR) && (targetPeriod.get(Calendar.MONTH) == templist.get(0).getTargetPeriod().get(Calendar.MONTH)))) {
                            tempFactoryProductAmountList = templist.get(0).getFactoryProductList();
                            System.out.println(templist.get(0).getId());
                            int size=tempFactoryProductAmountList.size();
                            for(int a=0;a<size;a++){
                            if (tempFactoryProductAmountList.get(0).getFactoryProduct().getFactoryProductId().equals(productId)) {
                                    integratedSalesForecast.setAmount(integratedSalesForecast.getAmount() + tempFactoryProductAmountList.get(0).getAmount());
                                    hasIt = true;
                                }
                            a++;
                            }
 
                            if (hasIt == true) {
                                integratedSalesForecast.getSalesForecastList().add(templist.get(0));

                                System.out.println("inegrateSalesForecast: " + templist.get(0).getId());
                            }
                        }
                        templist.remove(0);
                    }
                    System.out.println("inegrateSalesForecast: 5");
                    return integratedSalesForecast;

                } // It is an integrated sales forecast for retail product
                else {
                    List<FactoryRetailProductAmountEntity> tempFactoryRetailProductAmountList = new ArrayList<>();
                    while (!templist.isEmpty()) {
                        boolean hasIt = false;
                        if (targetPeriod.equals(templist.get(0).getTargetPeriod())) {
                            tempFactoryRetailProductAmountList = templist.get(0).getFactoryRetailProductList();
                            while (!tempFactoryRetailProductAmountList.isEmpty()) {
                                if (tempFactoryRetailProductAmountList.get(0).getFactoryRetailProduct().equals(product)) {
                                    integratedSalesForecast.setAmount(integratedSalesForecast.getAmount() + tempFactoryRetailProductAmountList.get(0).getAmount());
                                    hasIt = true;
                                }
                                tempFactoryRetailProductAmountList.remove(0);
                            }
                            if (hasIt == true) {
                                integratedSalesForecast.getSalesForecastList().add(templist.get(0));
                            }
                        }
                        templist.remove(0);
                    }
                    return integratedSalesForecast;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return null;

    }

    @Override
    public List<IntegratedSalesForecastEntity> getIntegrateSalesForecastList(Calendar period, Long factoryProductId) {
        List<IntegratedSalesForecastEntity> integratedSalesForecastList = new ArrayList<>();
        List<IntegratedSalesForecastEntity> templist;

        if (period == null && factoryProductId == null) {
            Query query = em.createQuery("SELECT t FROM IntegratedSalesForecastEntity t ORDER BY t.targetPeriod DESC");
            templist = (List<IntegratedSalesForecastEntity>) query.getResultList();

            Calendar today = Calendar.getInstance();
            today.add(Calendar.MONTH, 2);

            while (!templist.isEmpty()) {
                if (templist.get(0).getTargetPeriod().get(Calendar.YEAR) == today.get(Calendar.YEAR) && templist.get(0).getTargetPeriod().get(Calendar.MONTH) == today.get(Calendar.MONTH)) {
                    integratedSalesForecastList.add(templist.get(0));
                }
                templist.remove(0);
            }
            return integratedSalesForecastList;
        }
        return null;
    }

    @Override
    // Now assume that all there is only 1 factory 
    public List<FactoryProductEntity> productListNeededTobeIntegrated(Long FactoryId) {

        FactoryEntity factory = em.find(FactoryEntity.class, FactoryId);

        List<FactoryProductEntity> factoryProductList = new ArrayList<>();
        Query q = em.createQuery("SELECT t FROM FactoryProductEntity t");
        List<FactoryProductEntity> templist = (List<FactoryProductEntity>) q.getResultList();

        while (!templist.isEmpty()) {
            if (templist.get(0).getFactory().getFactoryId() == factory.getFactoryId()) {
                System.out.println("productId: " + templist.get(0).getFactoryProductId());
                factoryProductList.add(templist.get(0));
            }
            templist.remove(0);
        }
        System.out.println("productListNeededTobeIntegrated(): 2");
        Calendar today = Calendar.getInstance();
        List<IntegratedSalesForecastEntity> integratedSalesForecastList = getIntegrateSalesForecastList(null, null);
        IntegratedSalesForecastEntity temp;
        System.out.println("productListNeededTobeIntegrated(): 3");
        while (!integratedSalesForecastList.isEmpty()) {
            temp = integratedSalesForecastList.get(0);
            int size = factoryProductList.size();
            for (int a = 0; a < size; a++) {
                if (factoryProductList.get(a).equals(temp.getFactoryProduct())) {
                    System.out.println("productListNeededTobeIntegrated(): " + factoryProductList.get(0).getFactoryProductId());
                    factoryProductList.remove(a);
                    break;
                }
            }
            integratedSalesForecastList.remove(0);
        }
        System.out.println("productListNeededTobeIntegrated(): 4");

        System.out.println("productListNeededTobeIntegrated(): 5");
        return factoryProductList;

    }

    @Override
    public void GenerateIntegratedSalesForecast(Long factoryProductId,Calendar period){
    

    IntegratedSalesForecastEntity integratedSalesForecast=IntegrateSalesForecast(factoryProductId,period);
    
    em.persist(integratedSalesForecast);
    em.flush();
    
    
    }
}

