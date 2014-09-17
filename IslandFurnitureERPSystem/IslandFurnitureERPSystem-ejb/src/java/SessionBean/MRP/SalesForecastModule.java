/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

import Entity.Factory.FactoryProductAmountEntity;
import Entity.Factory.FactoryProductEntity;
import Entity.Factory.FactoryRetailProductAmountEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.MRP.IntegratedSalesForecastEntity;
import Entity.Factory.MRP.SalesForecastEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author apple
 */
@Stateful
public class SalesForecastModule implements SalesForecastModuleLocal {

    private EntityManager em;


    @Override
    public List<SalesForecastEntity> ListSalesForecast(Long storeId, Object product, Calendar period) {
        //List sales forecast based on 3 searching criteria: store, product(self-produced or retail product, forecast period)
        List<SalesForecastEntity> list = new ArrayList<>();
        List<SalesForecastEntity> templist = new ArrayList<>();
        try {

            Query query = em.createQuery("SELECT t FROM SalesForecastEntity t ORDER BY t.targetPeriod DESC");
            templist = (List<SalesForecastEntity>) query.getResultList();
            if (product == null) {
                //search criteria:   search all products for a certain period in certain store
                if (storeId != null && period != null) {
                    while (!templist.isEmpty()) {
                        if (storeId.equals(templist.get(0).getStore().getStoreId()) && period.equals(templist.get(0).getTargetPeriod())) {
                            list.add(templist.get(0));
                        }
                        templist.remove(0);
                    }

                } //search criteria:  search all products for certain period in all stores
                else if (storeId == null && period != null) {
                    while (!templist.isEmpty()) {
                        if (period.equals(templist.get(0).getTargetPeriod())) {
                            list.add(templist.get(0));
                        }
                        templist.remove(0);
                    }
                } //search criteria:  search all products for all period in certain store
                else if (storeId != null && period == null) {
                    while (!templist.isEmpty()) {
                        if (storeId.equals(templist.get(0).getStore().getStoreId())) {
                            list.add(templist.get(0));
                        }
                        templist.remove(0);
                    }
                } //search criteria:  search all products for all period in all stores
                else if (storeId == null && period == null) {
                    return templist;
                }

                return list;
            } else if (product.getClass().equals(FactoryProductEntity.class)) {
                List<FactoryProductAmountEntity> tempFactoryProductAmountList = new ArrayList<>();
                //search criteria:  search certain self-produced product for certain period in certain stores
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
                } //search criteria:  search certain self-produced product for certain period in all stores
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
                } //search criteria:  search certain self-produced product for all period in certain stores
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
                } //search criteria:  search certain self-produced products for all period in all stores
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
                //search criteria:  search certain retail product for certain period in certain stores
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
                } //search criteria:  search certain retail product for certain period in all stores
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
                } //search criteria:  search certain retail product for all period in certain stores
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
                } //search criteria:  search certain retail products for all period in all stores
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
    public IntegratedSalesForecastEntity IntegrateSalesForecast(Object product, Calendar period) {

        try {
            
            List<SalesForecastEntity> templist = new ArrayList<>();

            IntegratedSalesForecastEntity integratedSalesForecast = new IntegratedSalesForecastEntity();
            integratedSalesForecast.setAmount(0D);
            integratedSalesForecast.setTargetPeriod(period);

            Query query = em.createQuery("SELECT t FROM SalesForecastEntity t ORDER BY t.targetPeriod DESC");
            templist = (List<SalesForecastEntity>) query.getResultList();
            
            // If it is an integrated sales forecast for self-produced product
            if (product.getClass().equals(FactoryProductEntity.class)) {
                FactoryProductEntity factoryProduct=(FactoryProductEntity)product;
                integratedSalesForecast.setFactory(factoryProduct.getFactory());
                
                List<FactoryProductAmountEntity> tempFactoryProductAmountList = new ArrayList<>();
                while (!templist.isEmpty()) {
                    boolean hasIt=false;
                    if (period.equals(templist.get(0).getTargetPeriod())) {
                        tempFactoryProductAmountList = templist.get(0).getFactoryProductList();
                        while (!tempFactoryProductAmountList.isEmpty()) {
                            if (tempFactoryProductAmountList.get(0).getFactoryProduct().equals(product)) {
                                integratedSalesForecast.setAmount(integratedSalesForecast.getAmount()+tempFactoryProductAmountList.get(0).getAmount());
                                hasIt=true;
                            }
                            tempFactoryProductAmountList.remove(0);
                        }
                        if(hasIt==true)integratedSalesForecast.getSalesForecastList().add(templist.get(0));
                    }
                    templist.remove(0);
                }
                return integratedSalesForecast;

            } 
            
            // It is an integrated sales forecast for retail product
            else {               
                List<FactoryRetailProductAmountEntity> tempFactoryRetailProductAmountList = new ArrayList<>();
                while (!templist.isEmpty()) {
                    boolean hasIt=false;
                    if (period.equals(templist.get(0).getTargetPeriod())) {
                        tempFactoryRetailProductAmountList = templist.get(0).getFactoryRetailProductList();
                        while (!tempFactoryRetailProductAmountList.isEmpty()) {
                            if (tempFactoryRetailProductAmountList.get(0).getFactoryRetailProduct().equals(product)) {
                                integratedSalesForecast.setAmount(integratedSalesForecast.getAmount()+tempFactoryRetailProductAmountList.get(0).getAmount());
                                hasIt=true;
                            }
                            tempFactoryRetailProductAmountList.remove(0);
                        }
                        if(hasIt==true)integratedSalesForecast.getSalesForecastList().add(templist.get(0));
                    }
                    templist.remove(0);
                }
                return integratedSalesForecast;
            } 
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return null;

    }
}
