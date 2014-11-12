/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSetUp;

import Entity.CommonInfrastructure.FactoryUserEntity;
import Entity.CommonInfrastructure.HQUserEntity;
import Entity.CommonInfrastructure.IdNumberEntity;
import Entity.CommonInfrastructure.StoreUserEntity;
import Entity.CommonInfrastructure.UserEntity;
import Entity.Factory.BOMEntity;
import Entity.Factory.FactoryBin.FactoryBinEntity;
import Entity.Factory.FactoryBin.FactoryBinStoredProductEntity;
import Entity.Factory.FactoryEntity;
import Entity.Factory.FactoryProductAmountEntity;
import Entity.Factory.FactoryProductEntity;
import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.FactoryRetailProductAmountEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.InventoryRecordEntity;
import Entity.Factory.MRP.SalesForecastEntity;
import Entity.Factory.ProductEntity;
import Entity.Factory.RawMaterialEntity;
import Entity.Factory.RetailProductEntity;
import Entity.Factory.SCM.ContractEntity;
import Entity.Factory.SCM.OutboundMovementEntity;
import Entity.Factory.SCM.PurchaseOrderEntity;
import Entity.Factory.SCM.RawMaterialInFactoryUseMovementEntity;
import Entity.Factory.SCM.SupplierEntity;

import Entity.Factory.SetEntity;

import Entity.Kitchen.ComboEntity;
import Entity.Kitchen.ComboItemEntity;
import Entity.Kitchen.DailySalesEntity;
import Entity.Kitchen.DishEntity;
import Entity.Kitchen.DishItemEntity;
import Entity.Kitchen.IngredientEntity;
import Entity.Kitchen.IngredientItemEntity;
import Entity.Kitchen.IngredientSupplierEntity;
import Entity.Kitchen.KitchenEntity;
import Entity.Kitchen.KitchenOrderEntity;
import Entity.Kitchen.MenuItemForecastEntity;
import Entity.Kitchen.StoragePlaceEntity;

import Entity.Store.EventEntity;
import Entity.Store.OCRM.CountryProductEntity;
import Entity.Store.OCRM.CountryRetailProductEntity;
import Entity.Store.OCRM.CountrySetEntity;

import Entity.Store.IM.StoreWarehouseBinEntity;
import Entity.Store.OCRM.MemberCardIdMappingEntity;
import Entity.Store.OCRM.MemberEntity;

import Entity.Store.OCRM.MembershipLevelEntity;
import Entity.Store.OCRM.PickupListEntity;
import Entity.Store.OCRM.ProductSalesForecastEntity;
import Entity.Store.OCRM.SalesRecordEntity;
import Entity.Store.OCRM.ShoppingCartItemEntity;
import Entity.Store.OCRM.TransactionEntity;
import Entity.Store.OCRM.TransactionItemEntity;
import Entity.Store.StoreEntity;
import Entity.Store.StoreEventEntity;
import Entity.Store.StoreItemMappingEntity;
import Entity.Store.StoreProductEntity;
import Entity.Store.StoreRetailProductEntity;
import Entity.Store.StoreSetEntity;
import SessionBean.KM.CustomerOrderFulfillmentModuleLocal;
import SessionBean.SCM.PurchaseOrderManagementModuleLocal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.security.CryptographicHelper;

/**
 *
 * @author Yoky
 */
@Singleton
@LocalBean
@Startup
public class dataSetUp {
    @EJB
    private PurchaseOrderManagementModuleLocal pom;

    @EJB
    private CustomerOrderFulfillmentModuleLocal cof;

    @PersistenceContext
    private EntityManager em;

    private CryptographicHelper cryptographicHelper = CryptographicHelper.getInstanceOf();

    @PostConstruct
    public void init() {
        createDatabase();
    }

    public void createDatabase() {

        //idNumberEntity
        IdNumberEntity id = new IdNumberEntity();
        id.setId_F(1000003L);
        id.setId_H(1000001L);
        id.setId_S(1000002L);
        em.persist(id);
        em.flush();

        //HQUser
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String bir = "02-09-1993";
        Calendar birthday = Calendar.getInstance();
        try {
            birthday.setTime(df.parse(bir));
        } catch (ParseException ex) {
            Logger.getLogger(dataSetUp.class.getName()).log(Level.SEVERE, null, ex);
        }

        UserEntity u = new HQUserEntity("H", "1000001", 0, "Zheng", null, "Yuan", "Global Manager",
                birthday, "Female", "Ms", "Kent Ridge Crescent 15", "119215", "vicky.yuanzheng@gmail.com", 1L, cryptographicHelper.doMD5Hashing("123" + "H1000001"), false);
        em.persist(u);
        em.flush();

        //Factory
        FactoryEntity f1 = new FactoryEntity("Singapore", "Kent Ridge Crescent 1", "+6512345678", "F1000001", false);
        em.persist(f1);
        em.flush();
        FactoryEntity f2 = new FactoryEntity("United States", "4400 Shellmound St, Emeryville, CA", "+1888-888-4532", "F1000002", false);
        em.persist(f2);
        em.flush();

        //Factory Bin
        //for f1
        FactoryBinEntity fb1_1 = new FactoryBinEntity();
        fb1_1.setFactory(f1);
        em.persist(fb1_1);
        em.flush();
        FactoryBinEntity fb1_2 = new FactoryBinEntity();
        fb1_2.setFactory(f1);
        em.persist(fb1_2);
        em.flush();
        FactoryBinEntity fb1_3 = new FactoryBinEntity();
        fb1_3.setFactory(f1);
        em.persist(fb1_3);
        em.flush();
        ArrayList fb1 = new ArrayList();
        fb1.add(fb1_1);
        fb1.add(fb1_2);
        fb1.add(fb1_3);
        f1.setFactoryBins(fb1);
        em.flush();
        //for f2
        FactoryBinEntity fb2_1 = new FactoryBinEntity();
        fb2_1.setFactory(f2);
        em.persist(fb2_1);
        em.flush();
        FactoryBinEntity fb2_2 = new FactoryBinEntity();
        fb2_2.setFactory(f2);
        em.persist(fb2_2);
        em.flush();
        FactoryBinEntity fb2_3 = new FactoryBinEntity();
        fb2_3.setFactory(f2);
        em.persist(fb2_3);
        em.flush();
        ArrayList fb2 = new ArrayList();
        fb2.add(fb2_1);
        fb2.add(fb2_2);
        fb2.add(fb2_3);
        f2.setFactoryBins(fb2);
        em.flush();

        //FactoryUser(f1)
        UserEntity u1 = new FactoryUserEntity("F", "1000001", 1, "Zhang", null,
                "Shiyu", "Factory Manager", birthday, "Female",
                "Ms", "West Coast Road 20", "250620", "ms.z.summer@gmail.com", f1.getFactoryId(), cryptographicHelper.doMD5Hashing("123" + "F1000001"), false);
        em.persist(u1);
        em.flush();
        //FactoryUser(f1)
        UserEntity u2 = new FactoryUserEntity("F", "1000002", 3, "Zhang", null,
                "Yaowen", "Factory SCM Staff", birthday, "Female",
                "Ms", "New York Road 20", "250620", "z.yaowen@gmail.com", f1.getFactoryId(), cryptographicHelper.doMD5Hashing("123" + "F1000002"), false);
        em.persist(u2);
        em.flush();

        //FactoryUser(f1)
        UserEntity u3 = new FactoryUserEntity("F", "1000003", 4, "He", null,
                "Jinqiao", "Factory MRP Staff", birthday, "Male",
                "Mr", "West Coast Road 20", "250620", "hejinqiaoinsg@gmail.com", f1.getFactoryId(), cryptographicHelper.doMD5Hashing("123" + "F1000003"), false);
        em.persist(u3);
        em.flush();

        //Store
        StoreEntity s1 = new StoreEntity("60 Anson Road, #14-01 Mapletree Anson, Singapore 079914", "Singapore", "+65 6889 1000", "", false);
        em.persist(s1);
        em.flush();
        StoreEntity s2 = new StoreEntity("1 Raffles Link, 07-01 South Lobby, Singapore 039393", "Singapore", "+65 6889 1000", "", false);
        em.persist(s2);
        em.flush();

        f1.getStoreList().add(s1.getStoreId());
        f1.getStoreList().add(s2.getStoreId());
        s1.getFactoryList().add(f1.getFactoryId());
        s1.getFactoryList().add(f2.getFactoryId());

        em.flush();

        //StoreUser(s1)
        StoreUserEntity us1_1 = new StoreUserEntity("S", "1000001", 2, "Zhang", null,
                "Yaowen", "Store Manager", birthday, "Female",
                "Ms", "Woodlands Dr 14", "730504", "zhangyaowen@gmail.com", s1.getStoreId(), cryptographicHelper.doMD5Hashing("123" + "S1000001"), false);
        em.persist(us1_1);
        em.flush();

        //StoreUser(s2)
        StoreUserEntity u4 = new StoreUserEntity("S", "1000002", 2, "He", null,
                "Jinqiao", "Store Manager", birthday, "Male",
                "Mr", "West Coast Road 20", "250620", "hejinqiaoinsg@gmail.com", s1.getStoreId(), cryptographicHelper.doMD5Hashing("123" + "S1000002"), false);
        em.persist(u4);
        em.flush();
        
        //StoreUser Casher 
        StoreUserEntity casher1 = new StoreUserEntity("S", "1000003", 2, "He", null,
                "Jinqiao", "Store Manager", birthday, "Male",
                "Mr", "West Coast Road 20", "250620", "hejinqiaoinsg@gmail.com", s1.getStoreId(), cryptographicHelper.doMD5Hashing("123"+"S1000003"), false);
        casher1.setIsCasher(true);
        casher1.setBeginCash(1000D);
        casher1.setEndCash(1000D);
        em.persist(casher1);
        em.flush();
        
        StoreUserEntity casher2 = new StoreUserEntity("S", "1000004", 2, "Zhang", null,
                "Yaowen", "Store Manager", birthday, "Female",
                "Ms", "Woodlands Dr 14", "730504", "zhangyaowen@gmail.com", s1.getStoreId(), cryptographicHelper.doMD5Hashing("123"+"S1000004"), false);
        casher2.setIsCasher(true);
        casher1.setBeginCash(4000D);
        casher1.setEndCash(4000D);
        em.persist(casher2);
        em.flush();
        
        
        //Retail Product
        RetailProductEntity rp1 = new RetailProductEntity("Kellogg's Special K Cereal", "Red Berries 317G", 10.0, "box", false);
        em.persist(rp1);
        em.flush();
        RetailProductEntity rp2 = new RetailProductEntity("Nescafe Milk Coffee Canned Drink 6S", "Latte 240ML", 12.0, "set", false);
        em.persist(rp2);
        em.flush();
        RetailProductEntity rp3 = new RetailProductEntity("Hardys VR", "Shiraz 750ML", 3.0, "bottle", false);
        em.persist(rp3);
        em.flush();
        RetailProductEntity rp4 = new RetailProductEntity("Nature's Wonders", "Baked Cashew Nuts 240G", 7.8, "bag", false);
        em.persist(rp4);
        em.flush();
        RetailProductEntity rp5 = new RetailProductEntity("UIC Big Value Conc Liq Dtrgnt Rf", "Anti-Bac 1.8LT", 4.5, "bottle", false);
        em.persist(rp5);
        em.flush();

        //Raw Material
        RawMaterialEntity rm1 = new RawMaterialEntity("board", "wood", false, "square meter");
        em.persist(rm1);
        em.flush();
        RawMaterialEntity rm2 = new RawMaterialEntity("nail", "2mm, 50pcs/box", false, "box");
        em.persist(rm2);

        RawMaterialEntity rm3 = new RawMaterialEntity("glass", "sekken frosted glass", false, "square meter");
        em.persist(rm3);
        em.flush();
        RawMaterialEntity rm4 = new RawMaterialEntity("leather", "black", false, "square meter");
        em.persist(rm4);
        em.flush();
        RawMaterialEntity rm5 = new RawMaterialEntity("fabric", "blue", false, "square meter");
        em.persist(rm5);
        em.flush();
        RawMaterialEntity rm6 = new RawMaterialEntity("fibreboard", "white", false, "square meter");
        em.persist(rm6);
        em.flush();

        //Product
        ProductEntity p1 = new ProductEntity("Sofa", "Sofa and chaise lounge, Grann, Bomstad dark brown", 1499.0, 1480.0, "set", false);
        em.persist(p1);
        em.flush();
        ProductEntity p2 = new ProductEntity("TV Storage", "TV storage combination, black-brown", 499.0, 480.0, "set", false);
        em.persist(p2);
        em.flush();
        ProductEntity p3 = new ProductEntity("Coffee Table", "Coffee table, high gloss black", 199.0, 180.0, "one", false);
        em.persist(p3);
        em.flush();
        ProductEntity p4 = new ProductEntity("Ceiling Light", "LED chandelier, chrome plated", 59.99, 55.0, "set", false);
        em.persist(p4);
        em.flush();
        ProductEntity p5 = new ProductEntity("Wardrobe", "Wardrobe, black-brown, Sekken frosted glass", 884.0, 870.0, "one", false);
        em.persist(p5);
        em.flush();
        ProductEntity p6 = new ProductEntity("Bathroom Mirrors", "Bathroom mirror, Mirror cab 1 door/built-in lighting, white", 225.0, 220.0, "package", false);
        em.persist(p6);
        em.flush();

        ProductEntity p7 = new ProductEntity("Bed", "Wooden bed, Queen Size, light yellow", 600.0, 580.0, "one", false);
        em.persist(p7);
        em.flush();

        ProductEntity p8 = new ProductEntity("Bedside", "Wooden bedside, 2 drawers, light yellow", 200.0, 180.0, "one", false);
        em.persist(p8);
        em.flush();

        ProductEntity p9 = new ProductEntity("Closet", "Wooden closet, 200mm X 170mm,  light yellow", 500.0, 480.0, "one", false);
        em.persist(p9);
        em.flush();

        // Set Entity 
        SetEntity set1_1 = new SetEntity();
        set1_1.setName("Modern Set 4");
        set1_1.setDescription("best");
        set1_1.setPrice(2000D);
        set1_1.setMemberPrice(1900D);
        em.persist(set1_1);
        em.flush();

        SetEntity set1_2 = new SetEntity();
        set1_2.setName("Modern Set 5");
        set1_2.setDescription("nice");
        set1_2.setPrice(1000D);
        set1_2.setMemberPrice(900D);
        em.persist(set1_2);
        em.flush();

        SetEntity set1_3 = new SetEntity();
        set1_3.setName("Modern Set 6");
        set1_3.setDescription("good");
        set1_3.setPrice(1500D);
        set1_3.setMemberPrice(1400D);
        em.persist(set1_3);
        em.flush();

        //Factory Product
        //for f1
        FactoryProductEntity fp1_1 = new FactoryProductEntity(p1.getUnit(), f1, p1);
        em.persist(fp1_1);
        em.flush();
        f1.getFactoryProducts().add(fp1_1);
        p1.getFactoryProducts().add(fp1_1);
        FactoryProductEntity fp1_2 = new FactoryProductEntity(p2.getUnit(), f1, p2);
        em.persist(fp1_2);
        em.flush();
        f1.getFactoryProducts().add(fp1_2);
        p2.getFactoryProducts().add(fp1_2);
        FactoryProductEntity fp1_3 = new FactoryProductEntity(p3.getUnit(), f1, p3);
        em.persist(fp1_3);
        em.flush();
        f1.getFactoryProducts().add(fp1_3);
        p3.getFactoryProducts().add(fp1_3);
        FactoryProductEntity fp1_4 = new FactoryProductEntity(p4.getUnit(), f1, p4);
        em.persist(fp1_4);
        em.flush();
        f1.getFactoryProducts().add(fp1_4);
        p4.getFactoryProducts().add(fp1_4);
        FactoryProductEntity fp1_5 = new FactoryProductEntity(p5.getUnit(), f1, p5);
        em.persist(fp1_5);
        em.flush();
        f1.getFactoryProducts().add(fp1_5);
        p5.getFactoryProducts().add(fp1_5);
        em.flush();

        FactoryProductEntity fp1_6 = new FactoryProductEntity(p7.getUnit(), f1, p7);
        em.persist(fp1_6);
        em.flush();
        f1.getFactoryProducts().add(fp1_6);
        p5.getFactoryProducts().add(fp1_6);
        em.flush();

        FactoryProductEntity fp1_7 = new FactoryProductEntity(p8.getUnit(), f1, p8);
        em.persist(fp1_7);
        em.flush();
        f1.getFactoryProducts().add(fp1_7);
        p5.getFactoryProducts().add(fp1_7);
        em.flush();

        FactoryProductEntity fp1_8 = new FactoryProductEntity(p9.getUnit(), f1, p9);
        em.persist(fp1_8);
        em.flush();
        f1.getFactoryProducts().add(fp1_8);
        p5.getFactoryProducts().add(fp1_8);
        em.flush();

        //for f2
        FactoryProductEntity fp2_1 = new FactoryProductEntity(p1.getUnit(), f2, p1);
        em.persist(fp2_1);
        em.flush();
        f2.getFactoryProducts().add(fp2_1);
        p1.getFactoryProducts().add(fp2_1);
        FactoryProductEntity fp2_2 = new FactoryProductEntity(p2.getUnit(), f2, p2);
        em.persist(fp2_2);
        em.flush();
        f2.getFactoryProducts().add(fp2_2);
        p2.getFactoryProducts().add(fp2_2);
        em.flush();

        //Factory Retail Product
        //for f1
        FactoryRetailProductEntity frp1_1 = new FactoryRetailProductEntity(rp1.getUnit(), rp1.getName(), rp1.getDescription(), f1, rp1);
        em.persist(frp1_1);
        em.flush();
        f1.getFactoryRetailProducts().add(frp1_1);
        rp1.getFactoryRetailProducts().add(frp1_1);
        FactoryRetailProductEntity frp1_2 = new FactoryRetailProductEntity(rp2.getUnit(), rp2.getName(), rp2.getDescription(), f1, rp2);
        em.persist(frp1_2);
        em.flush();
        f1.getFactoryRetailProducts().add(frp1_2);
        rp2.getFactoryRetailProducts().add(frp1_2);
        FactoryRetailProductEntity frp1_3 = new FactoryRetailProductEntity(rp3.getUnit(), rp3.getName(), rp3.getDescription(), f1, rp3);
        em.persist(frp1_3);
        em.flush();
        f1.getFactoryRetailProducts().add(frp1_3);
        rp3.getFactoryRetailProducts().add(frp1_3);
        FactoryRetailProductEntity frp1_4 = new FactoryRetailProductEntity(rp4.getUnit(), rp4.getName(), rp4.getDescription(), f1, rp4);
        em.persist(frp1_4);
        em.flush();
        f1.getFactoryRetailProducts().add(frp1_4);
        rp4.getFactoryRetailProducts().add(frp1_4);
        FactoryRetailProductEntity frp1_5 = new FactoryRetailProductEntity(rp5.getUnit(), rp5.getName(), rp5.getDescription(), f1, rp5);
        em.persist(frp1_5);
        em.flush();
        f1.getFactoryRetailProducts().add(frp1_5);
        rp5.getFactoryRetailProducts().add(frp1_5);
        em.flush();
        //for f2
        FactoryRetailProductEntity frp2_1 = new FactoryRetailProductEntity(rp1.getUnit(), rp1.getName(), rp1.getDescription(), f2, rp1);
        em.persist(frp2_1);
        em.flush();
        f2.getFactoryRetailProducts().add(frp1_1);
        rp1.getFactoryRetailProducts().add(frp1_1);
        FactoryRetailProductEntity frp2_2 = new FactoryRetailProductEntity(rp2.getUnit(), rp2.getName(), rp2.getDescription(), f2, rp2);
        em.persist(frp2_2);
        em.flush();
        f2.getFactoryRetailProducts().add(frp1_2);
        rp2.getFactoryRetailProducts().add(frp1_2);
        em.flush();

        //StoreProduct      /* Further Modification*/
        //for s1
        //s1.storeProduct
        StoreProductEntity sp1_1 = new StoreProductEntity(fp1_1, s1, Boolean.TRUE, "good", p1);

        em.persist(sp1_1);
        fp1_1.getStoreProducts().add(sp1_1);
        s1.getStoreProducts().add(sp1_1);
        p1.getStoreProducts().add(sp1_1);
        em.persist(p1);
        em.flush();
        StoreProductEntity sp1_2 = new StoreProductEntity(fp1_2, s1, Boolean.FALSE, "good", p2);
        em.persist(sp1_2);
        fp2_1.getStoreProducts().add(sp1_2);
        s1.getStoreProducts().add(sp1_2);
        p2.getStoreProducts().add(sp1_2);
        em.flush();

        StoreProductEntity sp1_3 = new StoreProductEntity(fp1_6, s1, Boolean.FALSE, "good", p7);
        em.persist(sp1_3);
        fp2_2.getStoreProducts().add(sp1_3);
        s2.getStoreProducts().add(sp1_3);
        p7.getStoreProducts().add(sp1_3);
        em.flush();

        StoreProductEntity sp1_4 = new StoreProductEntity(fp1_7, s1, Boolean.FALSE, "good", p8);
        em.persist(sp1_4);
        fp2_2.getStoreProducts().add(sp1_4);
        s2.getStoreProducts().add(sp1_4);
        p8.getStoreProducts().add(sp1_4);
        em.flush();

        StoreProductEntity sp1_5 = new StoreProductEntity(fp1_8, s1, Boolean.FALSE, "good", p9);
        em.persist(sp1_5);
        fp2_2.getStoreProducts().add(sp1_5);
        s2.getStoreProducts().add(sp1_5);
        p9.getStoreProducts().add(sp1_5);
        em.flush();

        //s2.storeProduct
        StoreProductEntity sp2_1 = new StoreProductEntity(fp1_2, s2, Boolean.TRUE, "good", p2);
        em.persist(sp2_1);
        fp1_2.getStoreProducts().add(sp2_1);
        s2.getStoreProducts().add(sp2_1);
        p2.getStoreProducts().add(sp2_1);
        em.flush();
        StoreProductEntity sp2_2 = new StoreProductEntity(fp2_2, s2, Boolean.FALSE, "good", p2);
        em.persist(sp2_2);
        fp2_2.getStoreProducts().add(sp2_2);
        s2.getStoreProducts().add(sp2_2);
        p2.getStoreProducts().add(sp2_2);
        em.flush();

        StoreProductEntity sp2_3 = new StoreProductEntity(fp1_6, s2, Boolean.FALSE, "good", p7);
        em.persist(sp2_3);
        fp2_2.getStoreProducts().add(sp2_3);
        s2.getStoreProducts().add(sp2_3);
        p7.getStoreProducts().add(sp2_3);
        em.flush();

        StoreProductEntity sp2_4 = new StoreProductEntity(fp1_7, s2, Boolean.FALSE, "good", p8);
        em.persist(sp2_4);
        fp2_2.getStoreProducts().add(sp2_4);
        s2.getStoreProducts().add(sp2_4);
        p8.getStoreProducts().add(sp2_4);
        em.flush();

        StoreProductEntity sp2_5 = new StoreProductEntity(fp1_8, s2, Boolean.FALSE, "good", p9);
        em.persist(sp2_5);
        fp2_2.getStoreProducts().add(sp2_5);
        s2.getStoreProducts().add(sp2_5);
        p9.getStoreProducts().add(sp2_5);
        em.flush();

        sp1_1.setProduct(p1);
        sp1_2.setProduct(p2);
        sp1_3.setProduct(p7);
        sp1_4.setProduct(p8);
        sp1_5.setProduct(p9);
        sp2_1.setProduct(p2);
        sp2_2.setProduct(p2);
        sp2_3.setProduct(p7);
        sp2_4.setProduct(p8);
        sp2_5.setProduct(p9);
        em.persist(sp1_1);
        em.persist(sp1_2);
        em.persist(sp2_1);
        em.persist(sp2_2);
        em.flush();
        //StoreRetailProduct    /* Further Modification*/
        //for s1
        //s1.StoreRetailProduct
        StoreRetailProductEntity srp1_1 = new StoreRetailProductEntity(frp1_1, s1, "good");
        srp1_1.setRetailProduct(rp1);
        em.persist(srp1_1);
        frp1_1.getStoreRetailProducts().add(srp1_1);
        s1.getStoreRetailProducts().add(srp1_1);
        rp1.getStoreRetailProducts().add(srp1_1);
        em.flush();
        StoreRetailProductEntity srp1_2 = new StoreRetailProductEntity(frp1_2, s1, "good");
        srp1_2.setRetailProduct(rp2);
        em.persist(srp1_2);
        frp1_2.getStoreRetailProducts().add(srp1_2);
        s1.getStoreRetailProducts().add(srp1_2);
        rp2.getStoreRetailProducts().add(srp1_2);
        em.flush();
        //s2.StoreRetailProduct
        StoreRetailProductEntity srp2_1 = new StoreRetailProductEntity(frp2_1, s2, "good");
        srp2_1.setRetailProduct(rp3);
        em.persist(srp2_1);
        frp2_1.getStoreRetailProducts().add(srp2_1);
        s2.getStoreRetailProducts().add(srp2_1);
        rp3.getStoreRetailProducts().add(srp2_1);
        em.flush();

        StoreRetailProductEntity srp2_2 = new StoreRetailProductEntity(frp2_2, s2, "good");
        srp2_2.setRetailProduct(rp4);
        em.persist(srp2_2);
        frp2_2.getStoreRetailProducts().add(srp2_2);
        s2.getStoreRetailProducts().add(srp2_2);
        rp4.getStoreRetailProducts().add(srp2_2);
        em.flush();

        //Product.BOM
        //for p1
        BOMEntity bom1_1 = new BOMEntity(rm4, rm4.getUnit(), 3.0, p1);
        em.persist(bom1_1);
        em.flush();
        BOMEntity bom1_2 = new BOMEntity(rm2, rm2.getUnit(), 5.0, p1);
        em.persist(bom1_2);
        em.flush();
        BOMEntity bom1_3 = new BOMEntity(rm1, rm1.getUnit(), 5.0, p1);
        em.persist(bom1_3);
        em.flush();
        List bom1 = new ArrayList();
        bom1.add(bom1_1);
        bom1.add(bom1_2);
        bom1.add(bom1_3);
        p1.setBom(bom1);
        em.flush();
        //for p2
        BOMEntity bom2_1 = new BOMEntity(rm1, rm1.getUnit(), 5.0, p2);
        em.persist(bom2_1);
        em.flush();
        BOMEntity bom2_2 = new BOMEntity(rm3, rm3.getUnit(), 2.0, p2);
        em.persist(bom2_2);
        em.flush();
        BOMEntity bom2_3 = new BOMEntity(rm2, rm2.getUnit(), 3.0, p2);
        em.persist(bom2_3);
        em.flush();
        List bom2 = new ArrayList();
        bom2.add(bom2_1);
        bom2.add(bom2_2);
        bom2.add(bom2_3);
        p2.setBom(bom2);
        em.flush();
        //for p3
        BOMEntity bom3_1 = new BOMEntity(rm1, rm1.getUnit(), 3.0, p3);
        em.persist(bom3_1);
        em.flush();
        BOMEntity bom3_2 = new BOMEntity(rm2, rm2.getUnit(), 5.0, p3);
        em.persist(bom3_2);
        em.flush();
        BOMEntity bom3_3 = new BOMEntity(rm3, rm3.getUnit(), 2.0, p3);
        em.persist(bom3_3);
        em.flush();
        List bom3 = new ArrayList();
        bom3.add(bom3_1);
        bom3.add(bom3_2);
        bom3.add(bom3_3);
        p3.setBom(bom3);
        em.flush();
        //for p4
        BOMEntity bom4_1 = new BOMEntity(rm3, rm3.getUnit(), 2.0, p4);
        em.persist(bom4_1);
        em.flush();
        BOMEntity bom4_2 = new BOMEntity(rm2, rm2.getUnit(), 1.0, p4);
        em.persist(bom4_2);
        em.flush();
        List bom4 = new ArrayList();
        bom4.add(bom4_1);
        bom4.add(bom4_2);
        p4.setBom(bom4);
        em.flush();
        //for p5
        BOMEntity bom5_1 = new BOMEntity(rm1, rm1.getUnit(), 3.0, p5);
        em.persist(bom5_1);
        em.flush();
        BOMEntity bom5_2 = new BOMEntity(rm2, rm2.getUnit(), 6.0, p5);
        em.persist(bom5_2);
        em.flush();
        BOMEntity bom5_3 = new BOMEntity(rm3, rm3.getUnit(), 3.0, p5);
        em.persist(bom5_3);
        em.flush();
        List bom5 = new ArrayList();
        bom5.add(bom5_1);
        bom5.add(bom5_2);
        bom5.add(bom5_3);
        p5.setBom(bom5);
        em.flush();

        //set raw material to bom relationship
        rm1.getBomList().add(bom1_3);
        rm1.getBomList().add(bom2_1);
        rm1.getBomList().add(bom3_1);
        rm1.getBomList().add(bom5_1);
        em.persist(rm1);
        em.flush();

        rm2.getBomList().add(bom1_2);
        rm2.getBomList().add(bom2_3);
        rm2.getBomList().add(bom3_2);
        rm2.getBomList().add(bom4_2);
        rm2.getBomList().add(bom5_2);
        em.persist(rm2);
        em.flush();

        rm3.getBomList().add(bom2_2);
        rm3.getBomList().add(bom3_3);
        rm3.getBomList().add(bom4_1);
        rm3.getBomList().add(bom5_3);
        em.persist(rm3);
        em.flush();

        rm4.getBomList().add(bom1_1);
        em.persist(rm4);
        em.flush();

        //Factory Raw Material
        //for f1
        FactoryRawMaterialEntity frm1_1 = new FactoryRawMaterialEntity(rm1.getUnit(), rm1.getMaterialName(), rm1.getDescription(), false, f1, rm1);
        em.persist(frm1_1);
        em.flush();
        rm1.getFactoryRawMaterials().add(frm1_1);
        f1.getFactoryRawMaterials().add(frm1_1);
        FactoryRawMaterialEntity frm1_2 = new FactoryRawMaterialEntity(rm2.getUnit(), rm2.getMaterialName(), rm2.getDescription(), false, f1, rm2);
        em.persist(frm1_2);
        em.flush();
        rm2.getFactoryRawMaterials().add(frm1_2);
        f1.getFactoryRawMaterials().add(frm1_2);
        FactoryRawMaterialEntity frm1_3 = new FactoryRawMaterialEntity(rm3.getUnit(), rm3.getMaterialName(), rm3.getDescription(), false, f1, rm3);
        em.persist(frm1_3);
        em.flush();
        rm3.getFactoryRawMaterials().add(frm1_3);
        f1.getFactoryRawMaterials().add(frm1_3);
        FactoryRawMaterialEntity frm1_4 = new FactoryRawMaterialEntity(rm4.getUnit(), rm4.getMaterialName(), rm4.getDescription(), false, f1, rm4);
        em.persist(frm1_4);
        em.flush();
        rm4.getFactoryRawMaterials().add(frm1_4);
        f1.getFactoryRawMaterials().add(frm1_4);
        FactoryRawMaterialEntity frm1_5 = new FactoryRawMaterialEntity(rm5.getUnit(), rm5.getMaterialName(), rm5.getDescription(), false, f1, rm5);
        em.persist(frm1_5);
        em.flush();
        rm5.getFactoryRawMaterials().add(frm1_5);
        f1.getFactoryRawMaterials().add(frm1_5);
        em.flush();
        //for f2
        FactoryRawMaterialEntity frm2_1 = new FactoryRawMaterialEntity(rm1.getUnit(), rm1.getMaterialName(), rm1.getDescription(), false, f2, rm1);
        em.persist(frm2_1);
        em.flush();
        rm1.getFactoryRawMaterials().add(frm2_1);
        f2.getFactoryRawMaterials().add(frm2_1);
        FactoryRawMaterialEntity frm2_2 = new FactoryRawMaterialEntity(rm2.getUnit(), rm2.getMaterialName(), rm2.getDescription(), false, f2, rm2);
        em.persist(frm2_2);
        em.flush();
        rm2.getFactoryRawMaterials().add(frm2_2);
        f2.getFactoryRawMaterials().add(frm2_2);
        FactoryRawMaterialEntity frm2_3 = new FactoryRawMaterialEntity(rm3.getUnit(), rm3.getMaterialName(), rm3.getDescription(), false, f2, rm3);
        em.persist(frm2_3);
        em.flush();
        rm3.getFactoryRawMaterials().add(frm2_3);
        f2.getFactoryRawMaterials().add(frm2_3);
        FactoryRawMaterialEntity frm2_4 = new FactoryRawMaterialEntity(rm4.getUnit(), rm4.getMaterialName(), rm4.getDescription(), false, f2, rm4);
        em.persist(frm2_4);
        em.flush();
        rm4.getFactoryRawMaterials().add(frm2_4);
        f2.getFactoryRawMaterials().add(frm2_4);
        FactoryRawMaterialEntity frm2_5 = new FactoryRawMaterialEntity(rm5.getUnit(), rm5.getMaterialName(), rm5.getDescription(), false, f2, rm5);
        em.persist(frm2_5);
        em.flush();
        rm5.getFactoryRawMaterials().add(frm2_5);
        f2.getFactoryRawMaterials().add(frm2_5);
        em.flush();

        
        //Factory Bin Stored Product
        //for f1.factoryRawMaterial
        FactoryBinStoredProductEntity fbsp1_1_1 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_1_1);
        em.flush();
        fbsp1_1_1.createFactoryBinStoredProduct(frm1_1, fb1_1, "unrestricted");
        fbsp1_1_1.setAmount(1000.0);
        frm1_1.setUnrestrictedInventory(1000.0);
        fb1_1.getFactoryBinStoredProducts().add(fbsp1_1_1);
        frm1_1.getFactoryBinStoredProducts().add(fbsp1_1_1);
        FactoryBinStoredProductEntity fbsp1_1_2 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_1_2);
        em.flush();
        fbsp1_1_2.createFactoryBinStoredProduct(frm1_2, fb1_1, "unrestricted");
        fbsp1_1_2.setAmount(1000.0);
        frm1_2.setUnrestrictedInventory(1000.0);
        fb1_1.getFactoryBinStoredProducts().add(fbsp1_1_2);
        frm1_2.getFactoryBinStoredProducts().add(fbsp1_1_2);
        FactoryBinStoredProductEntity fbsp1_1_3 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_1_3);
        em.flush();
        fbsp1_1_3.createFactoryBinStoredProduct(frm1_3, fb1_1, "unrestricted");
        fbsp1_1_3.setAmount(1000.0);
        frm1_3.setUnrestrictedInventory(1000.0);
        fb1_1.getFactoryBinStoredProducts().add(fbsp1_1_3);
        frm1_3.getFactoryBinStoredProducts().add(fbsp1_1_3);
        FactoryBinStoredProductEntity fbsp1_1_4 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_1_4);
        em.flush();
        fbsp1_1_4.createFactoryBinStoredProduct(frm1_4, fb1_1, "unrestricted");
        fbsp1_1_4.setAmount(1000.0);
        frm1_4.setUnrestrictedInventory(1000.0);
        fb1_1.getFactoryBinStoredProducts().add(fbsp1_1_4);
        frm1_4.getFactoryBinStoredProducts().add(fbsp1_1_4);
        FactoryBinStoredProductEntity fbsp1_1_4_2 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_1_4_2);
        em.flush();
        fbsp1_1_4_2.createFactoryBinStoredProduct(frm1_4, fb1_1, "blocked");
        fbsp1_1_4_2.setAmount(100.0);
        frm1_4.setBlockedInventory(100.0);
        fb1_1.getFactoryBinStoredProducts().add(fbsp1_1_4_2);
        frm1_4.getFactoryBinStoredProducts().add(fbsp1_1_4_2);
        FactoryBinStoredProductEntity fbsp1_1_5 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_1_5);
        em.flush();
        fbsp1_1_5.createFactoryBinStoredProduct(frm1_5, fb1_1, "unrestricted");
        fbsp1_1_5.setAmount(1000.0);
        frm1_5.setUnrestrictedInventory(1000.0);
        fb1_1.getFactoryBinStoredProducts().add(fbsp1_1_5);
        frm1_5.getFactoryBinStoredProducts().add(fbsp1_1_5);
        em.flush();
        //for f1.factoryProduct
        FactoryBinStoredProductEntity fbsp1_2_1 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_2_1);
        em.flush();
        fbsp1_2_1.createFactoryBinStoredProduct(fp1_1, fb1_2, "unrestricted");
        fbsp1_2_1.setAmount(500.0);
        fp1_1.setUnrestrictedInventory(500.0);
        fb1_2.getFactoryBinStoredProducts().add(fbsp1_2_1);
        fp1_1.getFactoryBinStoredProducts().add(fbsp1_2_1);
        FactoryBinStoredProductEntity fbsp1_2_2 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_2_2);
        em.flush();
        fbsp1_2_2.createFactoryBinStoredProduct(fp1_2, fb1_2, "unrestricted");
        fbsp1_2_2.setAmount(500.0);
        fp1_2.setUnrestrictedInventory(500.0);
        fb1_2.getFactoryBinStoredProducts().add(fbsp1_2_2);
        fp1_2.getFactoryBinStoredProducts().add(fbsp1_2_2);
        FactoryBinStoredProductEntity fbsp1_2_3 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_2_3);
        em.flush();
        fbsp1_2_3.createFactoryBinStoredProduct(fp1_3, fb1_2, "unrestricted");
        fbsp1_2_3.setAmount(500.0);
        fp1_3.setUnrestrictedInventory(500.0);
        fb1_2.getFactoryBinStoredProducts().add(fbsp1_2_3);
        fp1_3.getFactoryBinStoredProducts().add(fbsp1_2_3);
        FactoryBinStoredProductEntity fbsp1_2_4 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_2_4);
        em.flush();
        fbsp1_2_4.createFactoryBinStoredProduct(fp1_4, fb1_2, "unrestricted");
        fbsp1_2_4.setAmount(500.0);
        fp1_4.setUnrestrictedInventory(500.0);
        fb1_2.getFactoryBinStoredProducts().add(fbsp1_2_4);
        fp1_4.getFactoryBinStoredProducts().add(fbsp1_2_4);
        FactoryBinStoredProductEntity fbsp1_2_4_2 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_2_4_2);
        em.flush();
        fbsp1_2_4_2.createFactoryBinStoredProduct(fp1_4, fb1_2, "blocked");
        fbsp1_2_4_2.setAmount(50.0);
        fp1_4.setBlockedInventory(50.0);
        fb1_2.getFactoryBinStoredProducts().add(fbsp1_2_4_2);
        fp1_4.getFactoryBinStoredProducts().add(fbsp1_2_4_2);
        FactoryBinStoredProductEntity fbsp1_2_5 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_2_5);
        em.flush();
        fbsp1_2_5.createFactoryBinStoredProduct(fp1_5, fb1_2, "unrestricted");
        fbsp1_2_5.setAmount(500.0);
        fp1_5.setUnrestrictedInventory(500.0);
        fb1_2.getFactoryBinStoredProducts().add(fbsp1_2_5);
        fp1_5.getFactoryBinStoredProducts().add(fbsp1_2_5);
        FactoryBinStoredProductEntity fbsp1_2_5_3 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_2_5_3);
        em.flush();
        fbsp1_2_5_3.createFactoryBinStoredProduct(fp1_5, fb1_2, "returned");
        fbsp1_2_5_3.setAmount(1.0);
        fp1_5.setReturnedInventory(1.0);
        fb1_2.getFactoryBinStoredProducts().add(fbsp1_2_5_3);
        fp1_5.getFactoryBinStoredProducts().add(fbsp1_2_5_3);
        em.flush();
        //for f1.factoryRetailProduct
        FactoryBinStoredProductEntity fbsp1_3_1 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_3_1);
        em.flush();
        fbsp1_3_1.createFactoryBinStoredProduct(frp1_1, fb1_3, "unrestricted");
        fbsp1_3_1.setAmount(300.0);
        frp1_1.setUnrestrictedInventory(300.0);
        fb1_3.getFactoryBinStoredProducts().add(fbsp1_3_1);
        frp1_1.getFactoryBinStoredProducts().add(fbsp1_3_1);
        FactoryBinStoredProductEntity fbsp1_3_2 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_3_2);
        em.flush();
        fbsp1_3_2.createFactoryBinStoredProduct(frp1_2, fb1_3, "unrestricted");
        fbsp1_3_2.setAmount(300.0);
        frp1_2.setUnrestrictedInventory(300.0);
        fb1_3.getFactoryBinStoredProducts().add(fbsp1_3_2);
        frp1_2.getFactoryBinStoredProducts().add(fbsp1_3_2);
        FactoryBinStoredProductEntity fbsp1_3_3 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_3_3);
        em.flush();
        fbsp1_3_3.createFactoryBinStoredProduct(frp1_3, fb1_3, "unrestricted");
        fbsp1_3_3.setAmount(300.0);
        frp1_3.setUnrestrictedInventory(300.0);
        fb1_3.getFactoryBinStoredProducts().add(fbsp1_3_3);
        frp1_3.getFactoryBinStoredProducts().add(fbsp1_3_3);
        FactoryBinStoredProductEntity fbsp1_3_4 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_3_4);
        em.flush();
        fbsp1_3_4.createFactoryBinStoredProduct(frp1_4, fb1_3, "unrestricted");
        fbsp1_3_4.setAmount(300.0);
        frp1_4.setUnrestrictedInventory(300.0);
        fb1_3.getFactoryBinStoredProducts().add(fbsp1_3_4);
        frp1_4.getFactoryBinStoredProducts().add(fbsp1_3_4);
        FactoryBinStoredProductEntity fbsp1_3_4_2 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_3_4_2);
        em.flush();
        fbsp1_3_4_2.createFactoryBinStoredProduct(frp1_4, fb1_3, "blocked");
        fbsp1_3_4_2.setAmount(30.0);
        frp1_4.setBlockedInventory(30.0);
        fb1_3.getFactoryBinStoredProducts().add(fbsp1_3_4_2);
        frp1_4.getFactoryBinStoredProducts().add(fbsp1_3_4_2);
        FactoryBinStoredProductEntity fbsp1_3_5 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_3_5);
        em.flush();
        fbsp1_3_5.createFactoryBinStoredProduct(frp1_5, fb1_3, "unrestricted");
        fbsp1_3_5.setAmount(300.0);
        frp1_5.setUnrestrictedInventory(300.0);
        fb1_3.getFactoryBinStoredProducts().add(fbsp1_3_5);
        frp1_5.getFactoryBinStoredProducts().add(fbsp1_3_5);
        FactoryBinStoredProductEntity fbsp1_3_5_3 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_3_5_3);
        em.flush();
        fbsp1_3_5_3.createFactoryBinStoredProduct(frp1_5, fb1_3, "returned");
        fbsp1_3_5_3.setAmount(3.0);
        frp1_5.setReturnedInventory(3.0);
        fb1_3.getFactoryBinStoredProducts().add(fbsp1_3_5_3);
        frp1_5.getFactoryBinStoredProducts().add(fbsp1_3_5_3);
        em.flush();

        //for f2.factoryRawMaterial
        FactoryBinStoredProductEntity fbsp2_1_1 = new FactoryBinStoredProductEntity();
        em.persist(fbsp2_1_1);
        em.flush();
        fbsp2_1_1.createFactoryBinStoredProduct(frm2_1, fb2_1, "unrestricted");
        fbsp2_1_1.setAmount(1000.0);
        frm2_1.setUnrestrictedInventory(1000.0);
        fb2_1.getFactoryBinStoredProducts().add(fbsp2_1_1);
        frm2_1.getFactoryBinStoredProducts().add(fbsp2_1_1);
        FactoryBinStoredProductEntity fbsp2_1_2 = new FactoryBinStoredProductEntity();
        em.persist(fbsp2_1_2);
        em.flush();
        fbsp2_1_2.createFactoryBinStoredProduct(frm2_2, fb2_1, "unrestricted");
        fbsp2_1_2.setAmount(1000.0);
        frm2_2.setUnrestrictedInventory(1000.0);
        fb2_1.getFactoryBinStoredProducts().add(fbsp2_1_2);
        frm2_2.getFactoryBinStoredProducts().add(fbsp2_1_2);
        FactoryBinStoredProductEntity fbsp2_1_3 = new FactoryBinStoredProductEntity();
        em.persist(fbsp2_1_3);
        em.flush();
        fbsp2_1_3.createFactoryBinStoredProduct(frm2_3, fb2_1, "unrestricted");
        fbsp2_1_3.setAmount(1000.0);
        frm2_3.setUnrestrictedInventory(1000.0);
        fb2_1.getFactoryBinStoredProducts().add(fbsp2_1_3);
        frm2_3.getFactoryBinStoredProducts().add(fbsp2_1_3);
        FactoryBinStoredProductEntity fbsp2_1_4 = new FactoryBinStoredProductEntity();
        em.persist(fbsp2_1_4);
        em.flush();
        fbsp2_1_4.createFactoryBinStoredProduct(frm2_4, fb2_1, "unrestricted");
        fbsp2_1_4.setAmount(1000.0);
        frm2_4.setUnrestrictedInventory(1000.0);
        fb2_1.getFactoryBinStoredProducts().add(fbsp2_1_4);
        frm2_4.getFactoryBinStoredProducts().add(fbsp2_1_4);
        FactoryBinStoredProductEntity fbsp2_1_5 = new FactoryBinStoredProductEntity();
        em.persist(fbsp2_1_5);
        em.flush();
        fbsp2_1_5.createFactoryBinStoredProduct(frm2_5, fb2_1, "unrestricted");
        fbsp2_1_5.setAmount(1000.0);
        frm2_5.setUnrestrictedInventory(1000.0);
        fb2_1.getFactoryBinStoredProducts().add(fbsp2_1_5);
        frm2_5.getFactoryBinStoredProducts().add(fbsp2_1_5);
        em.flush();
        //for f2.factoryProduct
        FactoryBinStoredProductEntity fbsp2_2_1 = new FactoryBinStoredProductEntity();
        em.persist(fbsp2_2_1);
        em.flush();
        fbsp2_2_1.createFactoryBinStoredProduct(fp2_1, fb2_2, "unrestricted");
        fbsp2_2_1.setAmount(500.0);
        fp2_1.setUnrestrictedInventory(500.0);
        fb2_2.getFactoryBinStoredProducts().add(fbsp2_2_1);
        fp2_1.getFactoryBinStoredProducts().add(fbsp2_2_1);
        FactoryBinStoredProductEntity fbsp2_2_2 = new FactoryBinStoredProductEntity();
        em.persist(fbsp2_2_2);
        em.flush();
        fbsp2_2_2.createFactoryBinStoredProduct(fp2_2, fb2_2, "unrestricted");
        fbsp2_2_2.setAmount(500.0);
        fp2_2.setUnrestrictedInventory(500.0);
        fb2_2.getFactoryBinStoredProducts().add(fbsp2_2_2);
        fp2_2.getFactoryBinStoredProducts().add(fbsp2_2_2);
        em.flush();
        //for f2.factoryRetailProduct
        FactoryBinStoredProductEntity fbsp2_3_1 = new FactoryBinStoredProductEntity();
        em.persist(fbsp2_3_1);
        em.flush();
        fbsp2_3_1.createFactoryBinStoredProduct(frp2_1, fb2_3, "unrestricted");
        fbsp2_3_1.setAmount(300.0);
        frp2_1.setUnrestrictedInventory(300.0);
        fb2_3.getFactoryBinStoredProducts().add(fbsp2_3_1);
        frp2_1.getFactoryBinStoredProducts().add(fbsp2_3_1);
        FactoryBinStoredProductEntity fbsp2_3_2 = new FactoryBinStoredProductEntity();
        em.persist(fbsp2_3_2);
        em.flush();
        fbsp2_3_2.createFactoryBinStoredProduct(frp2_2, fb2_3, "unrestricted");
        fbsp2_3_2.setAmount(300.0);
        frp2_2.setUnrestrictedInventory(300.0);
        fb2_3.getFactoryBinStoredProducts().add(fbsp2_3_2);
        frp2_2.getFactoryBinStoredProducts().add(fbsp2_3_2);
        em.flush();

        //Inventory Record
        //Inventory Record.Calendars
        Calendar ci1 = Calendar.getInstance();
        ci1.set(2014, 10, 1);
        Calendar ci2 = Calendar.getInstance();
        ci2.set(2014, 11, 1);
        Calendar ci3 = Calendar.getInstance();
        ci3.set(2014, 12, 1);
        //for f1.factoryRawMaterial
        InventoryRecordEntity ir1_1_1_1 = new InventoryRecordEntity(frm1_1, ci1, 1000.0);
        em.persist(ir1_1_1_1);
        em.flush();
        frm1_1.getInventoryRecord().add(ir1_1_1_1);
        InventoryRecordEntity ir1_1_1_2 = new InventoryRecordEntity(frm1_1, ci2, 1100.0);
        em.persist(ir1_1_1_2);
        em.flush();
        frm1_1.getInventoryRecord().add(ir1_1_1_2);
        InventoryRecordEntity ir1_1_1_3 = new InventoryRecordEntity(frm1_1, ci3, 900.0);
        em.persist(ir1_1_1_3);
        em.flush();
        frm1_1.getInventoryRecord().add(ir1_1_1_3);
        InventoryRecordEntity ir1_1_2_1 = new InventoryRecordEntity(frm1_2, ci1, 800.0);
        em.persist(ir1_1_2_1);
        em.flush();
        frm1_2.getInventoryRecord().add(ir1_1_2_1);
        InventoryRecordEntity ir1_1_2_2 = new InventoryRecordEntity(frm1_2, ci2, 1000.0);
        em.persist(ir1_1_2_2);
        em.flush();
        frm1_2.getInventoryRecord().add(ir1_1_2_2);
        InventoryRecordEntity ir1_1_2_3 = new InventoryRecordEntity(frm1_2, ci3, 1200.0);
        em.persist(ir1_1_2_3);
        em.flush();
        frm1_2.getInventoryRecord().add(ir1_1_2_3);
        InventoryRecordEntity ir1_1_3_1 = new InventoryRecordEntity(frm1_3, ci1, 1300.0);
        em.persist(ir1_1_3_1);
        em.flush();
        frm1_3.getInventoryRecord().add(ir1_1_3_1);
        InventoryRecordEntity ir1_1_3_2 = new InventoryRecordEntity(frm1_3, ci2, 1200.0);
        em.persist(ir1_1_3_2);
        em.flush();
        frm1_3.getInventoryRecord().add(ir1_1_3_2);
        InventoryRecordEntity ir1_1_3_3 = new InventoryRecordEntity(frm1_3, ci3, 1100.0);
        em.persist(ir1_1_3_3);
        em.flush();
        frm1_3.getInventoryRecord().add(ir1_1_3_3);
        InventoryRecordEntity ir1_1_4_1 = new InventoryRecordEntity(frm1_4, ci1, 1100.0);
        em.persist(ir1_1_4_1);
        em.flush();
        frm1_4.getInventoryRecord().add(ir1_1_4_1);
        InventoryRecordEntity ir1_1_4_2 = new InventoryRecordEntity(frm1_4, ci2, 1300.0);
        em.persist(ir1_1_4_2);
        em.flush();
        frm1_4.getInventoryRecord().add(ir1_1_4_2);
        InventoryRecordEntity ir1_1_4_3 = new InventoryRecordEntity(frm1_4, ci3, 1300.0);
        em.persist(ir1_1_4_3);
        em.flush();
        frm1_4.getInventoryRecord().add(ir1_1_4_3);
        InventoryRecordEntity ir1_1_5_1 = new InventoryRecordEntity(frm1_5, ci1, 900.0);
        em.persist(ir1_1_5_1);
        em.flush();
        frm1_5.getInventoryRecord().add(ir1_1_5_1);
        InventoryRecordEntity ir1_1_5_2 = new InventoryRecordEntity(frm1_5, ci2, 1100.0);
        em.persist(ir1_1_5_2);
        em.flush();
        frm1_5.getInventoryRecord().add(ir1_1_5_2);
        InventoryRecordEntity ir1_1_5_3 = new InventoryRecordEntity(frm1_5, ci3, 900.0);
        em.persist(ir1_1_5_3);
        em.flush();
        frm1_5.getInventoryRecord().add(ir1_1_5_3);
        em.flush();
        //for f1.factoryProduct
        InventoryRecordEntity ir1_2_1_1 = new InventoryRecordEntity(fp1_1, ci1, 300.0);
        em.persist(ir1_2_1_1);
        em.flush();
        fp1_1.getRecord().add(ir1_2_1_1);
        InventoryRecordEntity ir1_2_1_2 = new InventoryRecordEntity(fp1_1, ci2, 600.0);
        em.persist(ir1_2_1_2);
        em.flush();
        fp1_1.getRecord().add(ir1_2_1_2);
        InventoryRecordEntity ir1_2_1_3 = new InventoryRecordEntity(fp1_1, ci3, 500.0);
        em.persist(ir1_2_1_3);
        em.flush();
        fp1_1.getRecord().add(ir1_2_1_3);
        InventoryRecordEntity ir1_2_2_1 = new InventoryRecordEntity(fp1_2, ci1, 500.0);
        em.persist(ir1_2_2_1);
        em.flush();
        fp1_2.getRecord().add(ir1_2_2_1);
        InventoryRecordEntity ir1_2_2_2 = new InventoryRecordEntity(fp1_2, ci2, 500.0);
        em.persist(ir1_2_2_2);
        em.flush();
        fp1_2.getRecord().add(ir1_2_2_2);
        InventoryRecordEntity ir1_2_2_3 = new InventoryRecordEntity(fp1_2, ci3, 600.0);
        em.persist(ir1_2_2_3);
        em.flush();
        fp1_2.getRecord().add(ir1_2_2_3);
        InventoryRecordEntity ir1_2_3_1 = new InventoryRecordEntity(fp1_3, ci1, 550.0);
        em.persist(ir1_2_3_1);
        em.flush();
        fp1_3.getRecord().add(ir1_2_3_1);
        InventoryRecordEntity ir1_2_3_2 = new InventoryRecordEntity(fp1_3, ci2, 650.0);
        em.persist(ir1_2_3_2);
        em.flush();
        fp1_3.getRecord().add(ir1_2_3_2);
        InventoryRecordEntity ir1_2_3_3 = new InventoryRecordEntity(fp1_3, ci3, 300.0);
        em.persist(ir1_2_3_3);
        em.flush();
        fp1_3.getRecord().add(ir1_2_3_3);
        InventoryRecordEntity ir1_2_4_1 = new InventoryRecordEntity(fp1_4, ci1, 400.0);
        em.persist(ir1_2_4_1);
        em.flush();
        fp1_4.getRecord().add(ir1_2_4_1);
        InventoryRecordEntity ir1_2_4_2 = new InventoryRecordEntity(fp1_4, ci2, 450.0);
        em.persist(ir1_2_4_2);
        em.flush();
        fp1_4.getRecord().add(ir1_2_4_2);
        InventoryRecordEntity ir1_2_4_3 = new InventoryRecordEntity(fp1_4, ci3, 600.0);
        em.persist(ir1_2_4_3);
        em.flush();
        fp1_4.getRecord().add(ir1_2_4_3);
        InventoryRecordEntity ir1_2_5_1 = new InventoryRecordEntity(fp1_5, ci1, 400.0);
        em.persist(ir1_2_5_1);
        em.flush();
        fp1_5.getRecord().add(ir1_2_5_1);
        InventoryRecordEntity ir1_2_5_2 = new InventoryRecordEntity(fp1_5, ci2, 500.0);
        em.persist(ir1_2_5_2);
        em.flush();
        fp1_5.getRecord().add(ir1_2_5_2);
        InventoryRecordEntity ir1_2_5_3 = new InventoryRecordEntity(fp1_5, ci3, 560.0);
        em.persist(ir1_2_5_3);
        em.flush();
        fp1_5.getRecord().add(ir1_2_5_3);
        em.flush();
        //for f1.factoryRetailProduct
        InventoryRecordEntity ir1_3_1_1 = new InventoryRecordEntity(frp1_1, ci1, 300.0);
        em.persist(ir1_3_1_1);
        em.flush();
        frp1_1.getInventoryRecords().add(ir1_3_1_1);
        InventoryRecordEntity ir1_3_1_2 = new InventoryRecordEntity(frp1_1, ci2, 280.0);
        em.persist(ir1_3_1_2);
        em.flush();
        frp1_1.getInventoryRecords().add(ir1_3_1_2);
        InventoryRecordEntity ir1_3_1_3 = new InventoryRecordEntity(frp1_1, ci3, 320.0);
        em.persist(ir1_3_1_3);
        em.flush();
        frp1_1.getInventoryRecords().add(ir1_3_1_3);
        InventoryRecordEntity ir1_3_2_1 = new InventoryRecordEntity(frp1_2, ci1, 330.0);
        em.persist(ir1_3_2_1);
        em.flush();
        frp1_2.getInventoryRecords().add(ir1_3_2_1);
        InventoryRecordEntity ir1_3_2_2 = new InventoryRecordEntity(frp1_2, ci2, 240.0);
        em.persist(ir1_3_2_2);
        em.flush();
        frp1_2.getInventoryRecords().add(ir1_3_2_2);
        InventoryRecordEntity ir1_3_2_3 = new InventoryRecordEntity(frp1_2, ci3, 330.0);
        em.persist(ir1_3_2_3);
        em.flush();
        frp1_2.getInventoryRecords().add(ir1_3_2_3);
        InventoryRecordEntity ir1_3_3_1 = new InventoryRecordEntity(frp1_3, ci1, 230.0);
        em.persist(ir1_3_3_1);
        em.flush();
        frp1_3.getInventoryRecords().add(ir1_3_3_1);
        InventoryRecordEntity ir1_3_3_2 = new InventoryRecordEntity(frp1_3, ci2, 340.0);
        em.persist(ir1_3_3_2);
        em.flush();
        frp1_3.getInventoryRecords().add(ir1_3_3_2);
        InventoryRecordEntity ir1_3_3_3 = new InventoryRecordEntity(frp1_3, ci3, 310.0);
        em.persist(ir1_3_3_3);
        em.flush();
        frp1_3.getInventoryRecords().add(ir1_3_3_3);
        InventoryRecordEntity ir1_3_4_1 = new InventoryRecordEntity(frp1_4, ci1, 290.0);
        em.persist(ir1_3_4_1);
        em.flush();
        frp1_4.getInventoryRecords().add(ir1_3_4_1);
        InventoryRecordEntity ir1_3_4_2 = new InventoryRecordEntity(frp1_4, ci2, 340.0);
        em.persist(ir1_3_4_2);
        em.flush();
        frp1_4.getInventoryRecords().add(ir1_3_4_2);
        InventoryRecordEntity ir1_3_4_3 = new InventoryRecordEntity(frp1_4, ci3, 320.0);
        em.persist(ir1_3_4_3);
        em.flush();
        frp1_4.getInventoryRecords().add(ir1_3_4_3);
        InventoryRecordEntity ir1_3_5_1 = new InventoryRecordEntity(frp1_5, ci1, 300.0);
        em.persist(ir1_3_5_1);
        em.flush();
        frp1_5.getInventoryRecords().add(ir1_3_5_1);
        InventoryRecordEntity ir1_3_5_2 = new InventoryRecordEntity(frp1_5, ci2, 310.0);
        em.persist(ir1_3_5_2);
        em.flush();
        frp1_5.getInventoryRecords().add(ir1_3_5_2);
        InventoryRecordEntity ir1_3_5_3 = new InventoryRecordEntity(frp1_5, ci3, 320.0);
        em.persist(ir1_3_5_3);
        em.flush();
        frp1_5.getInventoryRecords().add(ir1_3_5_3);
        em.flush();

        //Supplier
        //for Raw Material
        SupplierEntity sp1_1_1 = new SupplierEntity("J.K. Adams", "1430 Route 30, Dorset, Vermont, US 05251", "(800) 451-6118", "(802) 362-5472", "J.K. Adams is located in Dorset, Vermont, 4 1/2 miles north of the intersection of Routes 11/30 and 7A in Manchester Center.");
        em.persist(sp1_1_1);
        em.flush();
        SupplierEntity sp1_1_2 = new SupplierEntity("The Wooden Board Company", "Springfield Farm, Wester Balgedie, Kinross, KY13 9HE, UK", " 07769 340524", " 07769 347492", "Handcrafted Cheese & Chopping Boards, Just For You");
        em.persist(sp1_1_2);
        em.flush();
        SupplierEntity sp1_2_1 = new SupplierEntity("Nail Co", "740 Broadway, Ste 400, South Portland, ME 04106, US", "+1-207-799-6245", "+1-207-799-7893", " Nail Salon");
        em.persist(sp1_2_1);
        em.flush();
        SupplierEntity sp1_2_2 = new SupplierEntity("Glasgow Steel Nail Co", "457 School Street, Mansfield, Massachusetts 02048, US", "+1 508-339-4500", "+1 508-339-0104", "This cut nail business started around 1870 and was the UK's last manufacturer of traditional square cut nails before transfering to the USA in 2013 to become a division of Acorn Manufacturing Co Inc");
        em.persist(sp1_2_2);
        em.flush();
        SupplierEntity sp1_3_1 = new SupplierEntity("Besglas", "2 Jurong East Street 21, 03-45 IMM Building, Singapore 609601", "65 6252 3533", "65 6899 3533", "HEAD OFFICE: Blk 2 Toa Payoh Industrial Park");
        em.persist(sp1_3_1);
        em.flush();
        SupplierEntity sp1_3_2 = new SupplierEntity("AGC Asia Pacific Pte Ltd", "460 Alexandra Road, #30-02 PSA Building, Singapore 119963", "+65 6273 5656", "+65 6271 3817", "AGC Asia Pacific Pte Ltd handles all sales & product enquiries for the Asia Pacific Region.");
        em.persist(sp1_3_2);
        em.flush();
        SupplierEntity sp1_4_1 = new SupplierEntity("Springfield Leather Co. Inc.", "1463 S. Glenstone, Springfield, MO 65808, US", "1-417-881-0223", "1-417-881-4953", "Free Kydex Samples will incur shipping charges!");
        em.persist(sp1_4_1);
        em.flush();
        SupplierEntity sp1_4_2 = new SupplierEntity("Maverick Leather Company", "1364 N McDowell Blvd #25, Petaluma, CA 94954, US", "707-792-2208", "707-792-2381", "Maverick Leather Company became a reality in June 2006.");
        em.persist(sp1_4_2);
        em.flush();
        SupplierEntity sp1_5_1 = new SupplierEntity("The Fabric Co.", "17702 Chesterfield Airport Rd, Chesterfield MO 63005, US", "1-855-530-0775", "1-636-530-0775", "Sunday: Closed");
        em.persist(sp1_5_1);
        em.flush();
        SupplierEntity sp1_5_2 = new SupplierEntity("Portsmouth Fabric Company", "112 Penhallow St, Portsmouth NH 03801, US", "(603) 436-6343", "(603) 430-2943", "Gift Cards now available for purchase. Order any amount and we will ship to any address.");
        em.persist(sp1_5_2);
        em.flush();

        //for Retail Product
        SupplierEntity sp3_1 = new SupplierEntity("KAMAKA Group Pte Ltd", "890 Upper Bukit Timah Road, #04-20, Singapore – 678 186", " 65 6465 5420", " 65 6465 3456", "can also email at info@orderonline.sg");
        em.persist(sp3_1);
        em.flush();
        SupplierEntity sp3_2 = new SupplierEntity("Nestlé Singapore (Pte) Ltd", "15A Changi Business Park Central 1, #05-02/03 Eightrium @ Changi Business Park, Singapore 486035", "+65 6836-7000", "+65 6588-4385", "Sponsorships/Milo Van Requests: sponsorships.sg@sg.nestle.com");
        em.persist(sp3_2);
        em.flush();
        SupplierEntity sp3_3 = new SupplierEntity("Hardys", "202 Main Road, McLaren Vale, South Australia, 5171 Australia", "+61 8 8329 4110", "+61 8 8329 4100", "Open Public Holidays excluding Good Friday and Christmas Day.");
        em.persist(sp3_3);
        em.flush();
        SupplierEntity sp3_4 = new SupplierEntity("Tai Sun (Lim Kee) Food Industries Pte Ltd", "255 Pandan Loop, Singapore 128433", "65 6779 6611", "65 6778 2477", "Goodness is everything");
        em.persist(sp3_4);
        em.flush();
        SupplierEntity sp3_5 = new SupplierEntity("Universal Integrated Corporation Consumer Products Pte Ltd", "No. 3 Jalan Besut, Jurong Town, Singapore 619556", "+65 6265 1648", "+65 6265 8462", "UICCP is Singapore’s largest manufacturer of Home Care and Fabric Care products");
        em.persist(sp3_5);
        em.flush();

        //Contract
        //Calendar for Contracts
        Calendar cl1 = Calendar.getInstance();
        cl1.set(2010, 8, 25);
        Calendar cl2 = Calendar.getInstance();
        cl2.set(2015, 8, 25);
        Calendar cl3 = Calendar.getInstance();
        cl3.set(2012, 6, 23);
        Calendar cl4 = Calendar.getInstance();
        cl4.set(2017, 6, 23);
        Calendar cl5 = Calendar.getInstance();
        cl5.set(2011, 3, 15);
        Calendar cl6 = Calendar.getInstance();
        cl6.set(2016, 3, 15);
        Calendar cl7 = Calendar.getInstance();
        cl7.set(2014, 4, 19);
        Calendar cl8 = Calendar.getInstance();
        cl8.set(2019, 4, 19);
        Calendar cl9 = Calendar.getInstance();
        cl9.set(2013, 5, 15);
        Calendar cl10 = Calendar.getInstance();
        cl10.set(2018, 5, 15);
        em.flush();

        //for f1.factoryRawMaterial
        ContractEntity ct1_1_1_1 = new ContractEntity(100.0, 2, frm1_1.getUnit(), 20.0, cl1, cl2, frm1_1, sp1_1_1);
        em.persist(ct1_1_1_1);
        frm1_1.getContracts().add(ct1_1_1_1);
        sp1_1_1.getContractList().add(ct1_1_1_1);
        ContractEntity ct1_1_1_2 = new ContractEntity(90.0, 3, frm1_1.getUnit(), 20.0, cl3, cl4, frm1_1, sp1_1_2);
        em.persist(ct1_1_1_2);
        em.flush();
        frm1_1.getContracts().add(ct1_1_1_2);
        sp1_1_2.getContractList().add(ct1_1_1_2);
        ContractEntity ct1_1_2_1 = new ContractEntity(150.0, 2, frm1_2.getUnit(), 100.0, cl5, cl6, frm1_2, sp1_2_1);
        em.persist(ct1_1_2_1);
        em.flush();
        frm1_2.getContracts().add(ct1_1_2_1);
        sp1_2_1.getContractList().add(ct1_1_2_1);
        ContractEntity ct1_1_2_2 = new ContractEntity(150.0, 2, frm1_2.getUnit(), 100.0, cl7, cl8, frm1_2, sp1_2_2);
        em.persist(ct1_1_2_2);
        em.flush();
        frm1_2.getContracts().add(ct1_1_2_2);
        sp1_2_2.getContractList().add(ct1_1_2_2);
        ContractEntity ct1_1_3_1 = new ContractEntity(100.0, 3, frm1_3.getUnit(), 10.0, cl9, cl10, frm1_3, sp1_3_1);
        em.persist(ct1_1_3_1);
        em.flush();
        frm1_3.getContracts().add(ct1_1_3_1);
        sp1_3_1.getContractList().add(ct1_1_3_1);
        ContractEntity ct1_1_3_2 = new ContractEntity(120.0, 2, frm1_3.getUnit(), 10.0, cl1, cl2, frm1_3, sp1_3_2);
        em.persist(ct1_1_3_2);
        em.flush();
        frm1_3.getContracts().add(ct1_1_3_2);
        sp1_3_2.getContractList().add(ct1_1_3_2);
        ContractEntity ct1_1_4_1 = new ContractEntity(300.0, 2, frm1_4.getUnit(), 5.0, cl3, cl4, frm1_4, sp1_4_1);
        em.persist(ct1_1_4_1);
        em.flush();
        frm1_4.getContracts().add(ct1_1_4_1);
        sp1_4_1.getContractList().add(ct1_1_4_1);
        ContractEntity ct1_1_4_2 = new ContractEntity(500.0, 1, frm1_4.getUnit(), 5.0, cl5, cl6, frm1_4, sp1_4_2);
        em.persist(ct1_1_4_2);
        em.flush();
        frm1_4.getContracts().add(ct1_1_4_2);
        sp1_4_2.getContractList().add(ct1_1_4_2);
        ContractEntity ct1_1_5_1 = new ContractEntity(100.0, 2, frm1_5.getUnit(), 5.0, cl7, cl8, frm1_5, sp1_5_1);
        em.persist(ct1_1_5_1);
        em.flush();
        frm1_5.getContracts().add(ct1_1_5_1);
        sp1_5_1.getContractList().add(ct1_1_5_1);
        ContractEntity ct1_1_5_2 = new ContractEntity(110.0, 2, frm1_5.getUnit(), 5.0, cl9, cl10, frm1_5, sp1_5_2);
        em.persist(ct1_1_5_2);
        em.flush();
        frm1_5.getContracts().add(ct1_1_5_2);
        sp1_5_2.getContractList().add(ct1_1_5_2);
        em.flush();

        //for f1.factoryRetailProduct
        ContractEntity ct1_3_1 = new ContractEntity(65.0, 2, frp1_1.getUnit(), 10.0, cl1, cl2, frp1_1, sp3_1);
        em.persist(ct1_3_1);
        em.flush();
        frp1_1.getContracts().add(ct1_3_1);
        sp3_1.getContractList().add(ct1_3_1);
        ContractEntity ct1_3_2 = new ContractEntity(50.0, 3, frp1_2.getUnit(), 10.0, cl3, cl4, frp1_2, sp3_2);
        em.persist(ct1_3_2);
        em.flush();
        frp1_2.getContracts().add(ct1_3_2);
        sp3_2.getContractList().add(ct1_3_2);
        ContractEntity ct1_3_3 = new ContractEntity(230.0, 2, frp1_3.getUnit(), 10.0, cl5, cl6, frp1_3, sp3_3);
        em.persist(ct1_3_3);
        em.flush();
        frp1_3.getContracts().add(ct1_3_3);
        sp3_3.getContractList().add(ct1_3_3);
        ContractEntity ct1_3_4 = new ContractEntity(55.0, 1, frp1_4.getUnit(), 10.0, cl7, cl8, frp1_4, sp3_4);
        em.persist(ct1_3_4);
        em.flush();
        frp1_4.getContracts().add(ct1_3_4);
        sp3_4.getContractList().add(ct1_3_4);
        ContractEntity ct1_3_5 = new ContractEntity(35.0, 2, frp1_5.getUnit(), 10.0, cl9, cl10, frp1_5, sp3_5);
        em.persist(ct1_3_5);
        em.flush();
        frp1_5.getContracts().add(ct1_3_5);
        sp3_5.getContractList().add(ct1_3_5);
        em.flush();

        //for f2.factoryRawMaterial
        ContractEntity ct2_1_1_1 = new ContractEntity(100.0, 2, frm2_1.getUnit(), 20.0, cl1, cl2, frm2_1, sp1_1_1);
        em.persist(ct2_1_1_1);
        em.flush();
        frm2_1.getContracts().add(ct2_1_1_1);
        sp1_1_1.getContractList().add(ct2_1_1_1);
        ContractEntity ct2_1_2_1 = new ContractEntity(150.0, 2, frm2_2.getUnit(), 100.0, cl5, cl6, frm2_2, sp1_2_1);
        em.persist(ct2_1_2_1);
        em.flush();
        frm2_2.getContracts().add(ct2_1_2_1);
        sp1_2_1.getContractList().add(ct2_1_2_1);
        ContractEntity ct2_1_3_1 = new ContractEntity(100.0, 3, frm2_3.getUnit(), 10.0, cl9, cl10, frm2_3, sp1_3_1);
        em.persist(ct2_1_3_1);
        em.flush();
        frm2_3.getContracts().add(ct2_1_3_1);
        sp1_3_1.getContractList().add(ct2_1_3_1);
        ContractEntity ct2_1_4_1 = new ContractEntity(300.0, 2, frm2_4.getUnit(), 5.0, cl3, cl4, frm2_4, sp1_4_1);
        em.persist(ct2_1_4_1);
        em.flush();
        frm2_4.getContracts().add(ct2_1_4_1);
        sp1_4_1.getContractList().add(ct2_1_4_1);
        ContractEntity ct2_1_5_1 = new ContractEntity(100.0, 2, frm2_5.getUnit(), 5.0, cl7, cl8, frm2_5, sp1_5_1);
        em.persist(ct2_1_5_1);
        em.flush();
        frm2_5.getContracts().add(ct2_1_5_1);
        sp1_5_1.getContractList().add(ct2_1_5_1);
        em.flush();
        //for f2.factoryRetailProductContractEntity ct1_3_1 = new ContractEntity(65.0, 2, frp1_1.getUnit(), 10.0, cl1, cl2, frp1_1, sp3_1);
        ContractEntity ct2_3_1 = new ContractEntity(65.0, 2, frp2_1.getUnit(), 10.0, cl1, cl2, frp2_1, sp3_1);
        em.persist(ct2_3_1);
        em.flush();
        frp2_1.getContracts().add(ct2_3_1);
        sp3_1.getContractList().add(ct2_3_1);
        ContractEntity ct2_3_2 = new ContractEntity(50.0, 3, frp2_2.getUnit(), 10.0, cl3, cl4, frp2_2, sp3_2);
        em.persist(ct2_3_2);
        em.flush();
        frp2_2.getContracts().add(ct2_3_2);
        sp3_2.getContractList().add(ct2_3_2);
        em.flush();
        
        
        //Factory Manually-created purchase order
        try {
            PurchaseOrderEntity po1_1 = pom.createPurchaseOrder(f1.getFactoryId(), ct1_1_1_1.getContractId(), 100.0, null, "", Calendar.getInstance(), Boolean.TRUE, Boolean.FALSE);
            pom.confirmPurchaseOrder(u1.getUserId(), po1_1.getId());
            pom.generateGoodsRecipt(po1_1.getId());
        } catch (Exception ex) {
            System.out.println("DataSetUp: Factory Manually-created purchase order: Unexpected error");
            ex.printStackTrace();
        }
                
                
                

        //Sales Forecast
        //Calendars for Sales Forecast
        Calendar c1 = Calendar.getInstance();
        c1.set(2014, 11, 2);
        Calendar c2 = Calendar.getInstance();
        c2.set(2014, 11, 2);
        Calendar c3 = Calendar.getInstance();
        c3.set(2014, 10, 2);
        Calendar c4 = Calendar.getInstance();
        c4.set(2014, 10, 2);
        //sf1_1
        SalesForecastEntity sf1_1 = new SalesForecastEntity(s1, c1);
        em.persist(sf1_1);
        em.flush();
        //sf1_1.FactoryProductAmount
        FactoryProductAmountEntity fpa1_1_2_1 = new FactoryProductAmountEntity(fp1_1.getUnit(), 50.0, fp1_1);
        em.persist(fpa1_1_2_1);
        em.flush();
        sf1_1.getFactoryProductList().add(fpa1_1_2_1);
        FactoryProductAmountEntity fpa1_1_2_2 = new FactoryProductAmountEntity(fp1_2.getUnit(), 60.0, fp1_2);
        em.persist(fpa1_1_2_2);
        em.flush();
        sf1_1.getFactoryProductList().add(fpa1_1_2_2);
        FactoryProductAmountEntity fpa1_1_2_3 = new FactoryProductAmountEntity(fp1_3.getUnit(), 50.0, fp1_3);
        em.persist(fpa1_1_2_3);
        em.flush();
        sf1_1.getFactoryProductList().add(fpa1_1_2_3);
        FactoryProductAmountEntity fpa1_1_2_4 = new FactoryProductAmountEntity(fp1_4.getUnit(), 70.0, fp1_4);
        em.persist(fpa1_1_2_4);
        em.flush();
        sf1_1.getFactoryProductList().add(fpa1_1_2_4);
        FactoryProductAmountEntity fpa1_1_2_5 = new FactoryProductAmountEntity(fp1_5.getUnit(), 50.0, fp1_5);
        em.persist(fpa1_1_2_5);
        em.flush();
        sf1_1.getFactoryProductList().add(fpa1_1_2_5);
        em.flush();
        //sf1_1.FactoryRetailProductAmount
        FactoryRetailProductAmountEntity frpa1_1_3_1 = new FactoryRetailProductAmountEntity(frp1_1.getUnit(), 200.0, frp1_1);
        em.persist(frpa1_1_3_1);
        em.flush();
        sf1_1.getFactoryRetailProductList().add(frpa1_1_3_1);
        FactoryRetailProductAmountEntity frpa1_1_3_2 = new FactoryRetailProductAmountEntity(frp1_2.getUnit(), 300.0, frp1_2);
        em.persist(frpa1_1_3_2);
        em.flush();
        sf1_1.getFactoryRetailProductList().add(frpa1_1_3_2);
        FactoryRetailProductAmountEntity frpa1_1_3_3 = new FactoryRetailProductAmountEntity(frp1_3.getUnit(), 100.0, frp1_3);
        em.persist(frpa1_1_3_3);
        em.flush();
        sf1_1.getFactoryRetailProductList().add(frpa1_1_3_3);
        FactoryRetailProductAmountEntity frpa1_1_3_4 = new FactoryRetailProductAmountEntity(frp1_4.getUnit(), 200.0, frp1_4);
        em.persist(frpa1_1_3_4);
        em.flush();
        sf1_1.getFactoryRetailProductList().add(frpa1_1_3_4);
        FactoryRetailProductAmountEntity frpa1_1_3_5 = new FactoryRetailProductAmountEntity(frp1_5.getUnit(), 100.0, frp1_5);
        em.persist(frpa1_1_3_5);
        em.flush();
        sf1_1.getFactoryRetailProductList().add(frpa1_1_3_5);
        em.flush();

        //sf1_2
        SalesForecastEntity sf1_2 = new SalesForecastEntity(s2, c1);
        em.persist(sf1_2);
        em.flush();
        //sf1_2.FactoryProductAmount
        FactoryProductAmountEntity fpa1_2_2_1 = new FactoryProductAmountEntity(fp1_1.getUnit(), 50.0, fp1_1);
        em.persist(fpa1_2_2_1);
        em.flush();
        sf1_2.getFactoryProductList().add(fpa1_2_2_1);
        FactoryProductAmountEntity fpa1_2_2_2 = new FactoryProductAmountEntity(fp1_2.getUnit(), 60.0, fp1_2);
        em.persist(fpa1_2_2_2);
        em.flush();
        sf1_2.getFactoryProductList().add(fpa1_2_2_2);
        FactoryProductAmountEntity fpa1_2_2_3 = new FactoryProductAmountEntity(fp1_3.getUnit(), 50.0, fp1_3);
        em.persist(fpa1_2_2_3);
        em.flush();
        sf1_2.getFactoryProductList().add(fpa1_2_2_3);
        FactoryProductAmountEntity fpa1_2_2_4 = new FactoryProductAmountEntity(fp1_4.getUnit(), 70.0, fp1_4);
        em.persist(fpa1_2_2_4);
        em.flush();
        sf1_2.getFactoryProductList().add(fpa1_2_2_4);
        FactoryProductAmountEntity fpa1_2_2_5 = new FactoryProductAmountEntity(fp1_5.getUnit(), 50.0, fp1_5);
        em.persist(fpa1_2_2_5);
        em.flush();
        sf1_2.getFactoryProductList().add(fpa1_2_2_5);
        em.flush();
        //sf1_2.FactoryRetailProductAmount
        FactoryRetailProductAmountEntity frpa1_2_3_1 = new FactoryRetailProductAmountEntity(frp1_1.getUnit(), 200.0, frp1_1);
        em.persist(frpa1_2_3_1);
        em.flush();
        sf1_2.getFactoryRetailProductList().add(frpa1_2_3_1);
        FactoryRetailProductAmountEntity frpa1_2_3_2 = new FactoryRetailProductAmountEntity(frp1_2.getUnit(), 300.0, frp1_2);
        em.persist(frpa1_2_3_2);
        em.flush();
        sf1_2.getFactoryRetailProductList().add(frpa1_2_3_2);
        FactoryRetailProductAmountEntity frpa1_2_3_3 = new FactoryRetailProductAmountEntity(frp1_3.getUnit(), 100.0, frp1_3);
        em.persist(frpa1_2_3_3);
        em.flush();
        sf1_2.getFactoryRetailProductList().add(frpa1_2_3_3);
        FactoryRetailProductAmountEntity frpa1_2_3_4 = new FactoryRetailProductAmountEntity(frp1_4.getUnit(), 200.0, frp1_4);
        em.persist(frpa1_2_3_4);
        em.flush();
        sf1_2.getFactoryRetailProductList().add(frpa1_2_3_4);
        FactoryRetailProductAmountEntity frpa1_2_3_5 = new FactoryRetailProductAmountEntity(frp1_5.getUnit(), 100.0, frp1_5);
        em.persist(frpa1_2_3_5);
        em.flush();
        sf1_2.getFactoryRetailProductList().add(frpa1_2_3_5);
        em.flush();

        //sf2_1
        SalesForecastEntity sf2_1 = new SalesForecastEntity(s1, c1);
        em.persist(sf2_1);
        em.flush();
        //sf2_1.FactoryProductAmount
        FactoryProductAmountEntity fpa2_1_2_1 = new FactoryProductAmountEntity(fp1_1.getUnit(), 90.0, fp1_1);
        em.persist(fpa2_1_2_1);
        em.flush();
        sf2_1.getFactoryProductList().add(fpa2_1_2_1);
        FactoryProductAmountEntity fpa2_1_2_2 = new FactoryProductAmountEntity(fp1_2.getUnit(), 70.0, fp1_2);
        em.persist(fpa2_1_2_2);
        em.flush();
        sf2_1.getFactoryProductList().add(fpa2_1_2_2);
        em.flush();
        //sf2_1.FactoryRetailProductAmount
        FactoryRetailProductAmountEntity frpa2_1_3_1 = new FactoryRetailProductAmountEntity(frp1_1.getUnit(), 500.0, frp1_1);
        em.persist(frpa2_1_3_1);
        em.flush();
        sf2_1.getFactoryRetailProductList().add(frpa2_1_3_1);
        FactoryRetailProductAmountEntity frpa2_1_3_2 = new FactoryRetailProductAmountEntity(frp1_2.getUnit(), 600.0, frp1_2);
        em.persist(frpa2_1_3_2);
        em.flush();
        sf2_1.getFactoryRetailProductList().add(frpa2_1_3_2);
        em.flush();

        //sf2_2
        SalesForecastEntity sf2_2 = new SalesForecastEntity(s2, c1);
        em.persist(sf2_2);
        em.flush();
        //sf2_2.FactoryProductAmount
        FactoryProductAmountEntity fpa2_2_2_1 = new FactoryProductAmountEntity(fp1_1.getUnit(), 60.0, fp1_1);
        em.persist(fpa2_2_2_1);
        em.flush();
        sf2_2.getFactoryProductList().add(fpa2_2_2_1);
        FactoryProductAmountEntity fpa2_2_2_2 = new FactoryProductAmountEntity(fp1_2.getUnit(), 70.0, fp1_2);
        em.persist(fpa2_2_2_2);
        em.flush();
        sf2_2.getFactoryProductList().add(fpa2_2_2_2);
        em.flush();
        //sf2_2.FactoryRetailProductAmount
        FactoryRetailProductAmountEntity frpa2_2_3_1 = new FactoryRetailProductAmountEntity(frp1_1.getUnit(), 600.0, frp1_1);
        em.persist(frpa2_2_3_1);
        em.flush();
        sf2_2.getFactoryRetailProductList().add(frpa2_2_3_1);
        FactoryRetailProductAmountEntity frpa2_2_3_2 = new FactoryRetailProductAmountEntity(frp1_2.getUnit(), 700.0, frp1_2);
        em.persist(frpa2_2_3_2);
        em.flush();
        sf2_2.getFactoryRetailProductList().add(frpa2_2_3_2);
        em.flush();

        //Outbound Movement
        //Outbound Movement.Calendar
        Calendar com1 = Calendar.getInstance();
        com1.set(2014, 8, 28);
        Calendar com2 = Calendar.getInstance();
        com2.set(2014, 8, 20);
        Calendar com3 = Calendar.getInstance();
        com3.set(2014, 7, 22);
        //for f1
        OutboundMovementEntity om1_2_1 = new OutboundMovementEntity();
        om1_2_1.recordFactoryProductOutboundMovement(fbsp1_2_1, s1, 50.0, com1);
        em.persist(om1_2_1);
        
        em.flush();
        OutboundMovementEntity om1_3_1 = new OutboundMovementEntity();
        om1_3_1.recordFactoryRetailProductOutboundMovement(fbsp1_3_1, s2, 100.0, com2);
        em.persist(om1_3_1);

        em.flush();
        //for f2
        OutboundMovementEntity om2_2_1 = new OutboundMovementEntity();
        om2_2_1.recordFactoryProductOutboundMovement(fbsp2_2_1, s2, 30.0, com1);
        em.persist(om2_2_1);
        em.flush();
        OutboundMovementEntity om2_3_1 = new OutboundMovementEntity();
        om2_3_1.recordFactoryRetailProductOutboundMovement(fbsp1_3_1, s1, 60.0, com3);
        em.persist(om2_3_1);
        em.flush();

        //Raw material In-Factory Use Movement
        //for f1
        RawMaterialInFactoryUseMovementEntity rmifu1_1 = new RawMaterialInFactoryUseMovementEntity();
        em.persist(rmifu1_1);
        em.flush();
        rmifu1_1.recordRawMaterialInFactoryUseMovement(fbsp1_1_1, 30.0, com1);
        RawMaterialInFactoryUseMovementEntity rmifu1_2 = new RawMaterialInFactoryUseMovementEntity();
        em.persist(rmifu1_2);
        em.flush();
        rmifu1_2.recordRawMaterialInFactoryUseMovement(fbsp1_1_2, 20.0, com2);
        em.flush();
        //for f2
        RawMaterialInFactoryUseMovementEntity rmifu2_1 = new RawMaterialInFactoryUseMovementEntity();
        em.persist(rmifu2_1);
        em.flush();
        rmifu2_1.recordRawMaterialInFactoryUseMovement(fbsp2_1_1, 20.0, com1);
        em.flush();

        //Kitchen of s1
        KitchenEntity k1 = new KitchenEntity(s1);
        em.persist(k1);
        s1.setKitchen(k1);
        em.flush();

        //Storage Places for k1
        StoragePlaceEntity stp1_1 = new StoragePlaceEntity(k1, "Refrigerator 1");
        em.persist(stp1_1);
        em.flush();
        k1.getStoragePlaces().add(stp1_1);
        em.flush();
        StoragePlaceEntity stp1_2 = new StoragePlaceEntity(k1, "Refrigerator 2");
        em.persist(stp1_2);
        em.flush();
        k1.getStoragePlaces().add(stp1_2);
        em.flush();
        StoragePlaceEntity stp1_3 = new StoragePlaceEntity(k1, "Refrigerator 3");
        em.persist(stp1_3);
        em.flush();
        k1.getStoragePlaces().add(stp1_3);
        em.flush();
        StoragePlaceEntity stp1_4 = new StoragePlaceEntity(k1, "Shelf 1");
        em.persist(stp1_4);
        em.flush();
        k1.getStoragePlaces().add(stp1_4);
        em.flush();
        StoragePlaceEntity stp1_5 = new StoragePlaceEntity(k1, "Shelf 2");
        em.persist(stp1_5);
        em.flush();
        k1.getStoragePlaces().add(stp1_5);
        em.flush();

        //IngredientSuppliers for k1
        IngredientSupplierEntity ks1_1 = new IngredientSupplierEntity("Q.B. Food Trading Pte Ltd", "8 Chin Bee Crescent, Jurong, Singapore 619893", "+65 6261 6410", "+65 6261 6120", "Other Enquiries: +65 6261 6120", k1);
        em.persist(ks1_1);
        em.flush();
        k1.getIngredientSuppliers().add(ks1_1);
        em.flush();
        IngredientSupplierEntity ks1_2 = new IngredientSupplierEntity("Fresh Direct Pte Ltd", "Blk 17, Pasir Panjang Wholesale Centre, #01-119/120, Singapore 110017", "+65 6775 4454", "+65 6872 1911", "Sales and Business Development: desmond@freshdirect.com.sg", k1);
        em.persist(ks1_2);
        em.flush();
        k1.getIngredientSuppliers().add(ks1_2);
        em.flush();
        IngredientSupplierEntity ks1_3 = new IngredientSupplierEntity("Boba Planet Private Limited", "11 Woodlands Close, #10-19, Woodlands 11, S(737853)", "+65 6458 0490", "+65 6458 4760", "Bubble Tea ingredient supplier", k1);
        em.persist(ks1_3);
        em.flush();
        k1.getIngredientSuppliers().add(ks1_3);
        em.flush();

        //Ingredients for k1
        IngredientEntity i1_1 = new IngredientEntity("Lettuce", 5.0, "kg", "bitterness will increase with extended storage", 10.0, k1, ks1_2);
        em.persist(i1_1);
        em.flush();
        ArrayList<StoragePlaceEntity> stps1_1 = new ArrayList<>();
        stps1_1.add(stp1_1);
        for (StoragePlaceEntity storagePlace : stps1_1) {
            i1_1.getStoragePlaces().add(storagePlace);
            storagePlace.getIngredients().add(i1_1);
        }
        i1_1.getKitchen().getIngredients().add(i1_1);
        i1_1.getSupplier().getIngredients().add(i1_1);
        em.flush();

        IngredientEntity i1_2 = new IngredientEntity("Chilled Beef", 32.0, "kg", "hunter valley australian grass fed ribeye", 10.0, k1, ks1_1);
        em.persist(i1_2);
        em.flush();
        ArrayList<StoragePlaceEntity> stps1_2 = new ArrayList<>();
        stps1_2.add(stp1_1);
        stps1_2.add(stp1_2);
        for (StoragePlaceEntity storagePlace : stps1_2) {
            i1_2.getStoragePlaces().add(storagePlace);
            storagePlace.getIngredients().add(i1_2);
        }
        i1_2.getKitchen().getIngredients().add(i1_2);
        i1_2.getSupplier().getIngredients().add(i1_2);
        em.flush();
        
        IngredientEntity i1_3 = new IngredientEntity("Blueberry Syrup", 15.2, "bottle (5kg)", "Free local delivery for orders more than S$ 150, Delivery surcharge of S$ 15 applicable, for orders below S$ 150", 1.0, k1, ks1_3);
        em.persist(i1_3);
        em.flush();
        ArrayList<StoragePlaceEntity> stps1_3 = new ArrayList<>();
        stps1_3.add(stp1_1);
        stps1_3.add(stp1_2);
        stps1_3.add(stp1_3);
        for (StoragePlaceEntity storagePlace : stps1_3) {
            i1_3.getStoragePlaces().add(storagePlace);
            storagePlace.getIngredients().add(i1_3);
        }
        i1_3.getKitchen().getIngredients().add(i1_3);
        i1_3.getSupplier().getIngredients().add(i1_3);
        em.flush();

        //Dishes for k1
        DishEntity d1_1 = new DishEntity("Chili Crab", 35.0, "1.5KG Crab", 5, k1);
        em.persist(d1_1);
        em.flush();
        d1_1.getKitchen().getDishes().add(d1_1);
        IngredientItemEntity ii1_1_1 = new IngredientItemEntity(i1_1, 1.0);
        em.persist(ii1_1_1);
        em.flush();
        ii1_1_1.getIngredient().getDishes().add(d1_1);
        d1_1.getRecipe().add(ii1_1_1);
        IngredientItemEntity ii1_1_2 = new IngredientItemEntity(i1_2, 2.0);
        em.persist(ii1_1_2);
        em.flush();
        ii1_1_2.getIngredient().getDishes().add(d1_1);
        d1_1.getRecipe().add(ii1_1_2);
        em.flush();

        DishEntity d1_2 = new DishEntity("Beef Salad", 12.0, "Beef Salad", 5, k1);
        em.persist(d1_2);
        em.flush();
        d1_2.getKitchen().getDishes().add(d1_2);
        IngredientItemEntity ii1_2_1 = new IngredientItemEntity(i1_1, 1.0);
        em.persist(ii1_2_1);
        em.flush();
        ii1_2_1.getIngredient().getDishes().add(d1_2);
        d1_2.getRecipe().add(ii1_2_1);
        IngredientItemEntity ii1_2_2 = new IngredientItemEntity(i1_2, 2.0);
        em.persist(ii1_2_2);
        em.flush();
        ii1_2_2.getIngredient().getDishes().add(d1_2);
        d1_2.getRecipe().add(ii1_2_2);
        em.flush();
        IngredientItemEntity ii1_2_3 = new IngredientItemEntity(i1_3, 0.5);
        em.persist(ii1_2_3);
        em.flush();
        ii1_2_3.getIngredient().getDishes().add(d1_2);
        d1_2.getRecipe().add(ii1_2_3);
        em.flush();

        // Combos for k1
        ComboEntity c1_1 = new ComboEntity("Beef Set", 40.0, "Beef Set 2px", k1);
        em.persist(c1_1);
        c1_1.getKitchen().getCombos().add(c1_1);
        em.flush();
        DishItemEntity di1_1_1 = new DishItemEntity(d1_1, 2);
        em.persist(di1_1_1);
        em.flush();
        di1_1_1.getDish().getCombos().add(c1_1);
        c1_1.getDishes().add(di1_1_1);
        em.flush();
        DishItemEntity di1_1_2 = new DishItemEntity(d1_2, 2);
        em.persist(di1_1_2);
        em.flush();
        di1_1_2.getDish().getCombos().add(c1_1);
        c1_1.getDishes().add(di1_1_2);

        // MenuItemForecast for k1
        Random rd = new Random();
        for (int i = 0; i < 1; i++) {
            Calendar curr = Calendar.getInstance();
            curr.add(Calendar.DAY_OF_MONTH, i);
            MenuItemForecastEntity mif = new MenuItemForecastEntity(curr, k1);
            em.persist(mif);
            em.flush();
            k1.getMenuItemForecasts().add(mif);

            for (DishEntity d : k1.getDishes()) {
                DishItemEntity dfi1 = new DishItemEntity(d, rd.nextInt(200));
                em.persist(dfi1);
                em.flush();
                mif.getDishForecastItems().add(dfi1);
                dfi1.getDish().getForecasts().add(mif);
                em.flush();
            }

            for (ComboEntity c : k1.getCombos()) {
                ComboItemEntity cfi1 = new ComboItemEntity(c, rd.nextInt(100));
                em.persist(cfi1);
                em.flush();
                mif.getComboForecastItems().add(cfi1);
                cfi1.getCombo().getForecasts().add(mif);
                em.flush();
            }

        }

        //DailySales for k1
        for (int i = -10; i < 0; i++) {
            DailySalesEntity ds = new DailySalesEntity(k1);
            em.persist(ds);
            em.flush();
            k1.getDailySales().add(ds);
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, i);
            ds.setSalesDate(cal);
            Double sales = 0.0;
            for (DishEntity d : k1.getDishes()) {
                DishItemEntity di = new DishItemEntity(d, 150 + rd.nextInt(50));
                em.persist(di);
                em.flush();
                sales += di.getDish().getPrice() * di.getQuantity();
                ds.getDishes().add(di);
                d.getDailySales().add(ds);
                em.flush();
            }
            for (ComboEntity c : k1.getCombos()) {
                ComboItemEntity ci = new ComboItemEntity(c, 50 + rd.nextInt(50));
                em.persist(ci);
                em.flush();
                sales += ci.getCombo().getPrice() * ci.getQuantity();
                ds.getCombos().add(ci);
                c.getDailySales().add(ds);
                em.flush();
            }
            ds.setSales(sales);
            ds.setSalesAfterDiscount(sales * (rd.nextInt(2) / 10 + 0.8));
        }
//
//        // kitchen orders
//        KitchenOrderEntity ko1_1 = cof.createOrder(k1.getId(), null, us1_1.getUserId());
//        cof.addDishItem(ko1_1.getId(), d1_1.getId(), 1);
//        cof.addDishItem(ko1_1.getId(), d1_2.getId(), 2);
//        cof.addComboItem(ko1_1.getId(), c1_1.getId(), 3);
//        cof.confirmOrder(ko1_1.getId(), 1000.0);
//        
//        KitchenOrderEntity ko1_2 = cof.createOrder(k1.getId(), null, us1_1.getUserId());
//        cof.addDishItem(ko1_2.getId(), d1_1.getId(), 5);
//        cof.addDishItem(ko1_2.getId(), d1_2.getId(), 6);
//        cof.addComboItem(ko1_2.getId(), c1_1.getId(), 7);
//        cof.confirmOrder(ko1_2.getId(), 10000.0);

        //MembershipLevel
        MembershipLevelEntity memlvl1 = new MembershipLevelEntity();
        memlvl1.setDiscount(1D);
        memlvl1.setPointsToUpgrade(0D);
        memlvl1.setLevelName("Basic");
        memlvl1.setCle(3);
        memlvl1.setExpressCheckout(Boolean.FALSE);
        memlvl1.setFreeDelivery(Boolean.FALSE);
        memlvl1.setInviteOnlyEvent(Boolean.FALSE);
        memlvl1.setFreeParking(Boolean.FALSE);
        em.persist(memlvl1);
        em.flush();

        MembershipLevelEntity memlvl2 = new MembershipLevelEntity();
        memlvl2.setDiscount(0.9);
        memlvl2.setPointsToUpgrade(2000D);
        memlvl2.setLevelName("Blue");
        memlvl2.setCle(12);
        memlvl2.setExpressCheckout(Boolean.FALSE);
        memlvl2.setFreeDelivery(Boolean.FALSE);
        memlvl2.setInviteOnlyEvent(Boolean.TRUE);
        memlvl2.setFreeParking(Boolean.FALSE);
        em.persist(memlvl2);
        em.flush();

        MembershipLevelEntity memlvl3 = new MembershipLevelEntity();
        memlvl3.setDiscount(0.85);
        memlvl3.setPointsToUpgrade(10000D);
        memlvl3.setLevelName("Sliver");
        memlvl3.setCle(36);
        memlvl3.setExpressCheckout(Boolean.FALSE);
        memlvl3.setFreeDelivery(Boolean.FALSE);
        memlvl3.setInviteOnlyEvent(Boolean.TRUE);
        memlvl3.setFreeParking(Boolean.FALSE);
        em.persist(memlvl3);
        em.flush();

        MembershipLevelEntity memlvl4 = new MembershipLevelEntity();
        memlvl4.setDiscount(0.8);
        memlvl4.setPointsToUpgrade(30000D);
        memlvl4.setLevelName("Gold");
        memlvl4.setCle(60);
        memlvl4.setExpressCheckout(Boolean.FALSE);
        memlvl4.setFreeDelivery(Boolean.FALSE);
        memlvl4.setInviteOnlyEvent(Boolean.TRUE);
        memlvl4.setFreeParking(Boolean.TRUE);
        em.persist(memlvl4);
        em.flush();

        MembershipLevelEntity memlvl5 = new MembershipLevelEntity();
        memlvl5.setDiscount(0.75);
        memlvl5.setPointsToUpgrade(50000D);
        memlvl5.setCle(120);
        memlvl5.setLevelName("Diamond");
        memlvl5.setExpressCheckout(Boolean.TRUE);
        memlvl5.setFreeDelivery(Boolean.TRUE);
        memlvl5.setInviteOnlyEvent(Boolean.TRUE);
        memlvl5.setFreeParking(Boolean.TRUE);
        em.persist(memlvl5);
        em.flush();

        //MemberKitchen Set uP
        Calendar MemberKitchenBirthday = Calendar.getInstance();
        MemberKitchenBirthday.set(1999, 9, 1);

        MemberEntity memberKitchen = new MemberEntity(cryptographicHelper.doMD5Hashing("123"), "Lim", "Loo", "James",
                MemberKitchenBirthday, "Male", "Mr", "5 Kent Ridge Crescent", "412352",
                "mser@gmail.com", Boolean.FALSE);

        memberKitchen.setTotalPoints(20000D);
        memberKitchen.setCurrentPoints(2200D);

        memberKitchen.setMemberlvl(memlvl2);
        em.persist(memberKitchen);
        em.flush();
        
        // kitchen orders
        KitchenOrderEntity ko1_1 = cof.createOrder(k1.getId(), memberKitchen.getMemberId(), us1_1.getUserId(), "K1");
        cof.addDishItem(ko1_1.getId(), d1_1.getId(), 1);
        cof.addDishItem(ko1_1.getId(), d1_2.getId(), 2);
        cof.addComboItem(ko1_1.getId(), c1_1.getId(), 3);
        cof.confirmOrder(ko1_1.getId());
        cof.checkout(ko1_1.getId(), 500.0);
        
        KitchenOrderEntity ko1_2 = cof.createOrder(k1.getId(), null, us1_1.getUserId(), "K1");
        cof.addDishItem(ko1_2.getId(), d1_1.getId(), 5);
        cof.addDishItem(ko1_2.getId(), d1_2.getId(), 6);
        cof.addComboItem(ko1_2.getId(), c1_1.getId(), 7);
        cof.confirmOrder(ko1_2.getId());
        cof.checkout(ko1_2.getId(), 600.0);
        
        //TransactionEntity
        TransactionEntity tr1 = new TransactionEntity();
        tr1.setStore(s1);
        tr1.setTotalPrice(200.0);
        tr1.setGenerateTime(Calendar.getInstance());
        em.persist(tr1);
        em.flush();

        TransactionEntity tr2 = new TransactionEntity();
        tr2.setStore(s1);
        tr2.setTotalPrice(180.0);
        tr2.setGenerateTime(Calendar.getInstance());
        em.persist(tr2);
        em.flush();

        s1.getTransactions().add(tr1);
        s1.getTransactions().add(tr2);

        //StoreItemMappingEntity
        StoreItemMappingEntity sm1 = new StoreItemMappingEntity();
        sm1.setProductId(sp1_1.getStoreProductId());
        sm1.setStore(s1);
        sm1.setId(233523352L);
        em.persist(sm1);
        em.flush();

        StoreItemMappingEntity sm2 = new StoreItemMappingEntity();
        sm2.setProductId(sp1_2.getStoreProductId());
        sm2.setStore(s1);
        sm2.setId(987654321L);
        em.persist(sm2);
        em.flush();

        StoreItemMappingEntity sm3 = new StoreItemMappingEntity();
        sm3.setRetailProductId(srp1_1.getStoreRetailProductId());
        sm3.setStore(s1);
        sm3.setId(876543210L);
        em.persist(sm3);
        em.flush();

        StoreItemMappingEntity sm4 = new StoreItemMappingEntity();
        sm4.setRetailProductId(srp1_2.getStoreRetailProductId());
        sm4.setStore(s1);
        sm4.setId(372845627L);
        em.persist(sm4);
        em.flush();

        //TransactionItem
        TransactionItemEntity ti1 = new TransactionItemEntity();
        ti1.setItemId(sm1.getId());
        StoreProductEntity temp = em.find(StoreProductEntity.class, sm1.getProductId());
        ti1.setItemName(temp.getProduct().getName());
        ti1.setAmount(1);
        ti1.setTransaction(tr1);
        em.persist(ti1);
        em.flush();

        List<TransactionItemEntity> items = new ArrayList();
        items.add(ti1);
        tr1.setTransactionItemList(items);
        em.persist(tr1);
        em.flush();

        TransactionItemEntity ti2 = new TransactionItemEntity();
        ti2.setItemId(sm1.getId());
        StoreProductEntity temp2 = em.find(StoreProductEntity.class, sm1.getProductId());
        ti2.setItemName(temp2.getProduct().getName());
        ti2.setAmount(1);
        ti2.setTransaction(tr2);
        em.persist(ti2);
        em.flush();

        List<TransactionItemEntity> items2 = new ArrayList();
        items2.add(ti2);
        tr2.setTransactionItemList(items2);
        em.persist(tr2);
        em.flush();

        //pickupList
        PickupListEntity pl1 = new PickupListEntity();

        pl1.setTransactoinItems(items);
        ti1.setPickupList(pl1);
        em.persist(pl1);
        em.persist(ti1);
        em.flush();

        PickupListEntity pl2 = new PickupListEntity();

        pl2.setTransactoinItems(items2);
        ti2.setPickupList(pl2);
        em.persist(pl2);
        em.persist(ti2);
        em.flush();

        //Member Set uP
        Calendar MemberBirthday = Calendar.getInstance();
        MemberBirthday.set(1990, 9, 1);

        MemberEntity member = new MemberEntity(cryptographicHelper.doMD5Hashing("123ms.z.summer@gmail.com"), "Lee", "", "James",
                MemberBirthday, "Male", "Mr", "5 Kent Ridge Drive", "412342",
                "ms.z.summer@gmail.com", Boolean.FALSE, "Singapore");

        member.setTotalPoints(50000D);
        member.setCurrentPoints(20000D);
        member.setPointsToUpgrade(0D);

        member.setMemberlvl(memlvl5);
        em.persist(member);
        em.flush();

        //MemberCard Set up
        MemberCardIdMappingEntity cardIdMapping = new MemberCardIdMappingEntity("722EA75D9000");
        cardIdMapping.setMember(member);
        em.persist(cardIdMapping);
        em.flush();
        member.setCardIdMapping(cardIdMapping);
        em.persist(member);
        em.flush();

        
        
        //Sales Record Set Up
        Calendar cal_2014_1 = Calendar.getInstance();
        cal_2014_1.set(2014, 0, 1);
        Calendar cal_2014_2 = Calendar.getInstance();
        cal_2014_2.set(2014, 1, 1);
        Calendar cal_2014_3 = Calendar.getInstance();
        cal_2014_3.set(2014, 2, 1);
        Calendar cal_2014_4 = Calendar.getInstance();
        cal_2014_4.set(2014, 3, 1);
        Calendar cal_2014_5 = Calendar.getInstance();
        cal_2014_5.set(2014, 4, 1);
        Calendar cal_2014_6 = Calendar.getInstance();
        cal_2014_6.set(2014, 5, 1);
        Calendar cal_2014_7 = Calendar.getInstance();
        cal_2014_7.set(2014, 6, 1);
        Calendar cal_2014_8 = Calendar.getInstance();
        cal_2014_8.set(2014, 7, 1);
        Calendar cal_2014_9 = Calendar.getInstance();
        cal_2014_9.set(2014, 8, 1);
        Calendar cal_2014_10 = Calendar.getInstance();
        cal_2014_10.set(2014, 9, 1);
        Calendar cal_2014_11 = Calendar.getInstance();
        cal_2014_11.set(2014, 10, 1);
        Calendar cal_2014_12 = Calendar.getInstance();
        cal_2014_12.set(2014, 11, 1);
        Calendar cal_2013_1 = Calendar.getInstance();
        cal_2013_1.set(2013, 0, 1);
        Calendar cal_2012_1 = Calendar.getInstance();
        cal_2012_1.set(2012, 0, 1);

        SalesRecordEntity sre13 = new SalesRecordEntity();
        sre13.setStore(s1);
        sre13.setStoreProduct(sp1_1);
        sre13.setRecordPeriod(cal_2012_1);
        sre13.setAmount(1500D);
        sre13.setRevenue(75000D);
        em.persist(sre13);
        em.flush();

        SalesRecordEntity sre12 = new SalesRecordEntity();
        sre12.setStore(s1);
        sre12.setStoreProduct(sp1_1);
        sre12.setRecordPeriod(cal_2013_1);
        sre12.setAmount(2100D);
        sre12.setRevenue(105000D);
        em.persist(sre12);
        em.flush();

        SalesRecordEntity sre1 = new SalesRecordEntity();
        sre1.setStore(s1);
        sre1.setStoreProduct(sp1_1);
        sre1.setRecordPeriod(cal_2014_1);
        sre1.setAmount(1000D);
        sre1.setRevenue(50000D);
        em.persist(sre1);
        em.flush();

        SalesRecordEntity sre2 = new SalesRecordEntity();
        sre2.setStore(s1);
        sre2.setStoreProduct(sp1_1);
        sre2.setRecordPeriod(cal_2014_2);
        sre2.setAmount(1500D);
        sre2.setRevenue(75000D);
        em.persist(sre2);
        em.flush();

        SalesRecordEntity sre3 = new SalesRecordEntity();
        sre3.setStore(s1);
        sre3.setStoreProduct(sp1_1);
        sre3.setRecordPeriod(cal_2014_3);
        sre3.setAmount(1200D);
        sre3.setRevenue(60000D);
        em.persist(sre3);
        em.flush();

        SalesRecordEntity sre4 = new SalesRecordEntity();
        sre4.setStore(s1);
        sre4.setStoreProduct(sp1_1);
        sre4.setRecordPeriod(cal_2014_4);
        sre4.setAmount(1600D);
        sre4.setRevenue(80000D);
        em.persist(sre4);
        em.flush();

        SalesRecordEntity sre5 = new SalesRecordEntity();
        sre5.setStore(s1);
        sre5.setStoreProduct(sp1_1);
        sre5.setRecordPeriod(cal_2014_5);
        sre5.setAmount(2100D);
        sre5.setRevenue(105000D);
        em.persist(sre5);
        em.flush();

        SalesRecordEntity sre6 = new SalesRecordEntity();
        sre6.setStore(s1);
        sre6.setStoreProduct(sp1_1);
        sre6.setRecordPeriod(cal_2014_6);
        sre6.setAmount(2200D);
        sre6.setRevenue(110000D);
        em.persist(sre6);
        em.flush();

        SalesRecordEntity sre7 = new SalesRecordEntity();
        sre7.setStore(s1);
        sre7.setStoreProduct(sp1_1);
        sre7.setRecordPeriod(cal_2014_7);
        sre7.setAmount(2300D);
        sre7.setRevenue(115000D);
        em.persist(sre7);
        em.flush();

        SalesRecordEntity sre8 = new SalesRecordEntity();
        sre8.setStore(s1);
        sre8.setStoreProduct(sp1_1);
        sre8.setRecordPeriod(cal_2014_8);
        sre8.setAmount(2400D);
        sre8.setRevenue(120000D);
        em.persist(sre8);
        em.flush();

        SalesRecordEntity sre9 = new SalesRecordEntity();
        sre9.setStore(s1);
        sre9.setStoreProduct(sp1_1);
        sre9.setRecordPeriod(cal_2014_9);
        sre9.setAmount(2500D);
        sre9.setRevenue(125000D);
        em.persist(sre9);
        em.flush();

        SalesRecordEntity sre10 = new SalesRecordEntity();
        sre10.setStore(s1);
        sre10.setStoreProduct(sp1_1);
        sre10.setRecordPeriod(cal_2014_10);
        sre10.setAmount(2300D);
        sre10.setRevenue(115000D);
        em.persist(sre10);
        em.flush();

        SalesRecordEntity sre11 = new SalesRecordEntity();
        sre11.setStore(s1);
        sre11.setStoreProduct(sp1_1);
        sre11.setRecordPeriod(cal_2014_11);
        sre11.setAmount(2300D);
        sre11.setRevenue(115000D);
        em.persist(sre11);
        em.flush();

        sp1_1.getSalesRecordList().add(sre13);
        sp1_1.getSalesRecordList().add(sre12);
        sp1_1.getSalesRecordList().add(sre1);
        sp1_1.getSalesRecordList().add(sre2);
        sp1_1.getSalesRecordList().add(sre3);
        sp1_1.getSalesRecordList().add(sre4);
        sp1_1.getSalesRecordList().add(sre5);
        sp1_1.getSalesRecordList().add(sre6);
        sp1_1.getSalesRecordList().add(sre7);
        sp1_1.getSalesRecordList().add(sre8);
        sp1_1.getSalesRecordList().add(sre9);
        sp1_1.getSalesRecordList().add(sre10);
        sp1_1.getSalesRecordList().add(sre11);

        em.persist(sp1_1);
        em.flush();

        //Product Sales Forecast Set up
        ProductSalesForecastEntity psfe_2012_1 = new ProductSalesForecastEntity();
        psfe_2012_1.setAmount(1300D);
        psfe_2012_1.setTargetPeriod(cal_2012_1);
        psfe_2012_1.setStore(s1);
        psfe_2012_1.setStoreProduct(sp1_1);
        psfe_2012_1.setStatus("Confirmed");
        em.persist(psfe_2012_1);
        em.flush();

        ProductSalesForecastEntity psfe_2013_1 = new ProductSalesForecastEntity();
        psfe_2013_1.setAmount(1300D);
        psfe_2013_1.setTargetPeriod(cal_2013_1);
        psfe_2013_1.setStore(s1);
        psfe_2013_1.setStoreProduct(sp1_1);
        psfe_2013_1.setStatus("Confirmed");
        em.persist(psfe_2013_1);
        em.flush();

        ProductSalesForecastEntity psfe_2014_1 = new ProductSalesForecastEntity();
        psfe_2014_1.setAmount(1600D);
        psfe_2014_1.setTargetPeriod(cal_2014_1);
        psfe_2014_1.setStore(s1);
        psfe_2014_1.setStoreProduct(sp1_1);
        psfe_2014_1.setStatus("Confirmed");
        em.persist(psfe_2014_1);
        em.flush();

        ProductSalesForecastEntity psfe_2014_6 = new ProductSalesForecastEntity();
        psfe_2014_6.setAmount(2300D);
        psfe_2014_6.setTargetPeriod(cal_2014_6);
        psfe_2014_6.setStore(s1);
        psfe_2014_6.setStoreProduct(sp1_1);
        psfe_2014_6.setStatus("psfe_2014_6");
        em.persist(psfe_2014_6);
        em.flush();
        
        ProductSalesForecastEntity psfe6 = new ProductSalesForecastEntity();
        psfe6.setAmount(2400D);
        psfe6.setTargetPeriod(cal_2014_7);
        psfe6.setStore(s1);
        psfe6.setStoreProduct(sp1_1);
        psfe6.setStatus("Confirmed");
        em.persist(psfe6);
        em.flush();

        ProductSalesForecastEntity psfe1 = new ProductSalesForecastEntity();
        psfe1.setAmount(1300D);
        psfe1.setTargetPeriod(cal_2014_8);
        psfe1.setStore(s1);
        psfe1.setStoreProduct(sp1_1);
        psfe1.setStatus("Confirmed");
        em.persist(psfe1);
        em.flush();

        ProductSalesForecastEntity psfe2 = new ProductSalesForecastEntity();
        psfe2.setAmount(1500D);
        psfe2.setTargetPeriod(cal_2014_9);
        psfe2.setStore(s1);
        psfe2.setStoreProduct(sp1_1);
        psfe2.setStatus("Confirmed");
        em.persist(psfe2);
        em.flush();

        ProductSalesForecastEntity psfe3 = new ProductSalesForecastEntity();
        psfe3.setAmount(2000D);
        psfe3.setTargetPeriod(cal_2014_10);
        psfe3.setStore(s1);
        psfe3.setStoreProduct(sp1_1);
        psfe3.setStatus("Confirmed");
        em.persist(psfe3);
        em.flush();

        ProductSalesForecastEntity psfe4 = new ProductSalesForecastEntity();
        psfe4.setAmount(2200D);
        psfe4.setTargetPeriod(cal_2014_11);
        psfe4.setStore(s1);
        psfe4.setStoreProduct(sp1_1);
        psfe4.setStatus("Confirmed");
        em.persist(psfe4);
        em.flush();

        ProductSalesForecastEntity psfe5 = new ProductSalesForecastEntity();
        psfe5.setAmount(2400D);
        psfe5.setTargetPeriod(cal_2014_12);
        psfe5.setStore(s1);
        psfe5.setStoreProduct(sp1_1);
        psfe5.setStatus("Confirmed");
        em.persist(psfe5);
        em.flush();

        sp1_1.getProductSalesForecastList().add(psfe_2014_1);
        sp1_1.getProductSalesForecastList().add(psfe_2013_1);
        sp1_1.getProductSalesForecastList().add(psfe_2012_1);
        sp1_1.getProductSalesForecastList().add(psfe_2014_6);
        sp1_1.getProductSalesForecastList().add(psfe1);
        sp1_1.getProductSalesForecastList().add(psfe2);
        sp1_1.getProductSalesForecastList().add(psfe3);
        sp1_1.getProductSalesForecastList().add(psfe4);
        sp1_1.getProductSalesForecastList().add(psfe5);

        em.persist(sp1_1);
        em.flush();

        //Set Up  CountryProductEntity
        CountryProductEntity item = new CountryProductEntity();
        item.setDescription(p7.getDescription());
        item.setProductName("Bed");
        item.setPrice(p7.getPrice());
        item.setMemberPrice(p7.getMemberPrice());
        item.setPicture("bed_set4.png");
        item.setAvailability("Avalible");
        item.setType("Bed");
        item.setWeb("Singapore");
        item.setProduct(p7);
        em.persist(item);
        em.flush();

        CountryProductEntity item1 = new CountryProductEntity();
        item1.setDescription("this is a good Bedside");
        item1.setProductName("Bedside");
        item1.setPrice(200D);
        item1.setMemberPrice(180D);
        item1.setPicture("bedside_set4.png");
        item1.setAvailability("Avalible");
        item1.setType("Desk");
        item1.setProduct(p8);
        item1.setWeb("Singapore");
        em.persist(item1);
        em.flush();

        CountryProductEntity item3 = new CountryProductEntity();
        item3.setDescription("this is a good closet");
        item3.setProductName("Closet");
        item3.setPrice(200D);
        item3.setMemberPrice(180D);
        item3.setPicture("closet_set4.png");
        item3.setAvailability("Avalible");
        item3.setType("Closet");
        item3.setWeb("Singapore");
        item3.setProduct(p9);
        em.persist(item3);
        em.flush();

        CountryProductEntity item4 = new CountryProductEntity();
        item4.setDescription("best toy ever");
        item4.setProductName("Captain America");
        item4.setPrice(200D);
        item4.setMemberPrice(180D);
        item4.setPicture("CaptainAmerica.jpg");
        item4.setAvailability("Avalible");
        item4.setType("Others");
        item4.setProduct(p9);
        item4.setWeb("Singapore");
        em.persist(item4);
        em.flush();

        CountryProductEntity item5 = new CountryProductEntity();
        item5.setDescription("各种歪");
        item5.setProductName("歪脖美国队长");
        item5.setPrice(200D);
        item5.setMemberPrice(180D);
        item5.setPicture("CaptainAmerica.jpg");
        item5.setAvailability("可购");
        item5.setType("Others");
        item5.setProduct(p9);
        item5.setWeb("China");
        em.persist(item5);
        em.flush();

        CountrySetEntity set1 = new CountrySetEntity();
        set1.setDescription("This set uses brown as its theme. Simple design, no extra decoration makes people feel comfortable and relaxed. It uses high quality wooden material and environment friendly oil paint.");
        set1.setPicture("set4.png");
        set1.setName("Modern Set 4");
        set1.getUnitList().add(item);
        set1.getUnitList().add(item1);
        set1.getUnitList().add(item3);
        set1.setWeb("Singapore");
        set1.setSet(set1_1);

        em.persist(set1);
        em.flush();

        ShoppingCartItemEntity scie = new ShoppingCartItemEntity();
        scie.setCustomerWebItem(item);
        scie.setType("product");
        scie.setQuantity(1);
        scie.setStoreId(1L);
        em.persist(scie);
        em.flush();

        ShoppingCartItemEntity scie1 = new ShoppingCartItemEntity();
        scie1.setCountrySet(set1);
        scie1.setType("set");
        scie1.setQuantity(1);
        scie1.setStoreId(1L);
        em.persist(scie1);
        em.flush();

        member.getShoppingCartList().add(scie);
        member.getShoppingCartList().add(scie1);

        // Store Set 
        System.out.println("set id=============================================================" + set1.getSet().getId());
        System.out.println("countrySET id=============================================================" + set1.getId());

        StoreSetEntity ss1_1 = new StoreSetEntity(set1_1, set1, s1);
        em.persist(ss1_1);
        em.flush();
        
        StoreItemMappingEntity sm5 = new StoreItemMappingEntity();
        sm5.setStoreSetId(ss1_1.getId());
        sm5.setStore(s1);
        sm5.setId(847562718L);
        em.persist(sm5);
        em.flush();
        

        // set relationship
        set1_1.getProductList().add(p7);
        set1_1.getProductList().add(p8);
        set1_1.getProductList().add(p9);
        set1_1.getStoreSetList().add(ss1_1);
        set1_1.getWebSetList().add(set1);
        set1.getSetList().add(ss1_1);

        set1_2.getProductList().add(p7);
        set1_2.getProductList().add(p8);
        set1_2.getProductList().add(p9);
        set1_2.getProductList().add(p1);
        set1_2.getProductList().add(p2);

        em.flush();

        // Shopping cart tiem set up
        ShoppingCartItemEntity sci1 = new ShoppingCartItemEntity();
        sci1.setCustomerWebItem(item);
        sci1.setQuantity(3);
        sci1.setStoreId(s1.getStoreId());
        sci1.setType("product");
        em.persist(sci1);
        em.flush();

        member.getShoppingCartList().add(sci1);
        em.persist(sci1);
        em.flush();

        // Retail Item set up
        CountryRetailProductEntity retailItem1 = new CountryRetailProductEntity();
        retailItem1.setDescription(rp1.getDescription());
        retailItem1.setProductName(rp1.getName());
        retailItem1.setPrice(rp1.getPrice());
        retailItem1.setPicture("SpecialK.jpg");
        retailItem1.setAvailability("Avalible");
        retailItem1.setProduct(rp1);
        retailItem1.setWeb("Singapore");
        em.persist(retailItem1);
        em.flush();

        CountryRetailProductEntity retailItem2 = new CountryRetailProductEntity();
        retailItem2.setDescription(rp2.getDescription());
        retailItem2.setProductName(rp2.getName());
        retailItem2.setPrice(rp2.getPrice());
        retailItem2.setPicture("nescafe.jpg");
        retailItem2.setAvailability("Avalible");
        retailItem2.setProduct(rp2);
        retailItem2.setWeb("Singapore");
        em.persist(retailItem2);
        em.flush();

        CountryRetailProductEntity retailItem3 = new CountryRetailProductEntity();
        retailItem3.setDescription(rp3.getDescription());
        retailItem3.setProductName(rp3.getName());
        retailItem3.setPrice(rp3.getPrice());
        retailItem3.setPicture("hardysVR.jpg");
        retailItem3.setAvailability("Avalible");
        retailItem3.setProduct(rp3);
        retailItem3.setWeb("Singapore");
        em.persist(retailItem3);
        em.flush();

        CountryRetailProductEntity retailItem4 = new CountryRetailProductEntity();
        retailItem4.setDescription("特别的K 只给特别的你");
        retailItem4.setProductName("特别的K");
        retailItem4.setPrice(rp1.getPrice());
        retailItem4.setPicture("SpecialK.jpg");
        retailItem4.setAvailability("可购");
        retailItem4.setProduct(rp1);
        retailItem4.setWeb("China");
        em.persist(retailItem4);
        em.flush();

        //Event 
        Calendar ec1 = Calendar.getInstance();
        ec1.set(2014, 10, 13);
        Calendar ec2 = Calendar.getInstance();
        ec2.set(2014, 10, 15);
        Calendar ec3 = Calendar.getInstance();
        ec3.set(2014, 7, 14);
        Calendar ec4 = Calendar.getInstance();
        ec4.set(2014, 7, 17);
        Calendar ec5 = Calendar.getInstance();
        ec5.set(2015, 0, 13);
        Calendar ec6 = Calendar.getInstance();
        ec6.set(2015, 0, 17);
        Calendar ec7 = Calendar.getInstance();
        ec7.set(2015, 10, 10);
        Calendar ec8 = Calendar.getInstance();
        ec8.set(2015, 10, 13);

        Calendar ec9 = Calendar.getInstance();
        ec9.set(2014, 0, 14);
        Calendar ec10 = Calendar.getInstance();
        ec10.set(2014, 0, 24);

        Calendar ec11 = Calendar.getInstance();
        ec11.set(2013, 0, 13);
        Calendar ec12 = Calendar.getInstance();
        ec12.set(2013, 0, 23);

        Calendar ec13 = Calendar.getInstance();
        ec13.set(2012, 0, 17);
        Calendar ec14 = Calendar.getInstance();
        ec14.set(2012, 0, 27);

        Calendar ec15 = Calendar.getInstance();
        ec15.set(2015, 0, 17);
        Calendar ec16 = Calendar.getInstance();
        ec16.set(2015, 0, 27);

        EventEntity ece1 = new EventEntity("National Day", "National Day Celebration, double points", ec1, ec2);
        em.persist(ece1);
        em.flush();
        EventEntity ece2 = new EventEntity("Labor Day", "Larbor Day Celebration, parts of items count 1.5x points", ec3, ec4);
        em.persist(ece2);
        em.flush();
        EventEntity ece3 = new EventEntity("Island Furniture Anniversary", "5th Anniversary Celebration, double points", ec5, ec6);
        em.persist(ece3);
        em.flush();

        EventEntity ece4 = new EventEntity("Double 11", "double 11, 1.1x", ec7, ec8);
        em.persist(ece4);
        em.flush();

        EventEntity ece5 = new EventEntity("Chinese New Year", "Chinese new year", ec9, ec10);
        em.persist(ece5);
        em.flush();

        StoreEventEntity event1 = new StoreEventEntity(ece1, "National Day Celebration, double points", ec1, ec2, s1, 2D
        );
        event1.setEvent(ece1);
        event1.setStore(s1);
        em.persist(event1);
        em.flush();

        StoreEventEntity event2 = new StoreEventEntity(ece2, "Larbor Day Celebration, parts of items count 1.5x points", ec3, ec4, s1, 1.5D
        );
        event2.setEvent(ece2);
        event2.setStore(s1);
        em.persist(event2);
        em.flush();

        StoreEventEntity event4 = new StoreEventEntity(ece5, "Chinese new year", ec9, ec10, s1, 2D
        );
        event4.setEvent(ece5);
        event4.setStore(s1);
        event4.setIncreaseSale(192.5);
        em.persist(event4);
        em.flush();

        StoreEventEntity event5 = new StoreEventEntity(ece5, "Chinese new year", ec11, ec12, s1, 2D
        );
        event5.setEvent(ece5);
        event5.setStore(s1);
        event5.setIncreaseSale(230D);
        em.persist(event5);
        em.flush();

        StoreEventEntity event6 = new StoreEventEntity(ece5, "Chinese new year", ec13, ec14, s1, 2D
        );
        event6.setEvent(ece5);
        event6.setStore(s1);
        event6.setIncreaseSale(300D);
        em.persist(event6);
        em.flush();

        StoreEventEntity event7 = new StoreEventEntity(ece5, "Chinese new year", ec15, ec16, s1, 2D);
        event7.setEvent(ece5);
        event7.setStore(s1);
        em.persist(event7);
        em.flush();

        //***************************************************************************************************
        //*********************************Data setup for ACRM -- Shiyu**************************************
        //***************************************************************************************************
        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@        
        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Member Setup @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        //===============================Member 2=====================================
        Calendar MemberBirthday2 = Calendar.getInstance();
        MemberBirthday2.set(1985, 9, 1);

        MemberEntity member2 = new MemberEntity(cryptographicHelper.doMD5Hashing("123zhaomengdan93@gmail.com"), "Hotchner", "", "Arron",
                MemberBirthday2, "Male", "Mr", "5 Kent Ridge Drive", "412342",
                "zhaomengdan93@gmail.com", Boolean.FALSE, "Singapore", memlvl1);
        em.persist(member2);
        em.flush();

        //===============================Member 3=====================================
        Calendar MemberBirthday3 = Calendar.getInstance();
        MemberBirthday3.set(1985, 9, 1);

        MemberEntity member3 = new MemberEntity(cryptographicHelper.doMD5Hashing("123sunhang36@gmail.com"), "Morgen", "", "Derek",
                MemberBirthday3, "Male", "Mr", "5 Kent Ridge Drive", "412342",
                "sunhang36@gmail.com", Boolean.FALSE, "Singapore", memlvl2);
        em.persist(member3);
        em.flush();

        //===============================Member 4=====================================
        Calendar MemberBirthday4 = Calendar.getInstance();
        MemberBirthday4.set(1975, 9, 1);

        MemberEntity member4 = new MemberEntity(cryptographicHelper.doMD5Hashing("123"+"vickey.yuanzheng@gmail.com"), "Prentiss", "", "Emily",
                MemberBirthday4, "Female", "Miss", "5 Kent Ridge Drive", "412342",
                "vickey.yuanzheng@gmail.com", Boolean.FALSE, "China", memlvl1);
        em.persist(member4);
        em.flush();

        //===============================Member 5=====================================
        Calendar MemberBirthday5 = Calendar.getInstance();
        MemberBirthday5.set(1975, 9, 1);

        MemberEntity member5 = new MemberEntity(cryptographicHelper.doMD5Hashing("123hejinqiaoinsg@gmail.com"), "Jareau", "", "Jennifer",
                MemberBirthday5, "Female", "Ms", "5 Kent Ridge Drive", "412342",
                "hejinqiaoinsg@gmail.com", Boolean.FALSE, "China", memlvl2);
        em.persist(member5);
        em.flush();

        //===============================Member 6=====================================
        Calendar MemberBirthday6 = Calendar.getInstance();
        MemberBirthday6.set(1965, 9, 1);

        MemberEntity member6 = new MemberEntity(cryptographicHelper.doMD5Hashing("123zhangyaowen0707@gmail.com"), "Rossi", "", "David",
                MemberBirthday6, "Male", "Mr", "5 Kent Ridge Drive", "412342",
                "zhangyaowen0707@gmail.com", Boolean.FALSE, "United States", memlvl1);
        em.persist(member6);
        em.flush();

        //===============================Member 7=====================================
        Calendar MemberBirthday7 = Calendar.getInstance();
        MemberBirthday7.set(1965, 9, 1);

        MemberEntity member7 = new MemberEntity(cryptographicHelper.doMD5Hashing("123aaa@gmail.com"), "Reid", "", "Spencer",
                MemberBirthday7, "Male", "Mr", "5 Kent Ridge Drive", "412342",
                "aaa@gmail.com", Boolean.FALSE, "United States", memlvl2);
        em.persist(member7);
        em.flush();

        //===============================Member 8=====================================
        Calendar MemberBirthday8 = Calendar.getInstance();
        MemberBirthday8.set(1955, 9, 1);

        MemberEntity member8 = new MemberEntity(cryptographicHelper.doMD5Hashing("123bbb@gmail.com"), "Penelope", "", "Garcia",
                MemberBirthday8, "Female", "Miss", "5 Kent Ridge Drive", "412342",
                "bbb@gmail.com", Boolean.FALSE, "South Korea", memlvl1);
        em.persist(member8);
        em.flush();

        //===============================Member 9=====================================
        Calendar MemberBirthday9 = Calendar.getInstance();
        MemberBirthday9.set(1955, 9, 1);

        MemberEntity member9 = new MemberEntity(cryptographicHelper.doMD5Hashing("123ccc@gmail.com"), "Gubler", "Gray", "Mattew",
                MemberBirthday9, "Male", "Mr", "5 Kent Ridge Drive", "412342",
                "ccc@gmail.com", Boolean.FALSE, "South Korea", memlvl3);
        em.persist(member9);
        em.flush();

        //===============================Member 10=====================================
        Calendar MemberBirthday10 = Calendar.getInstance();
        MemberBirthday10.set(1993, 6, 11);

        MemberEntity member10 = new MemberEntity(cryptographicHelper.doMD5Hashing("123"), "Zhang", "", "Shiyu",
                MemberBirthday10, "Female", "Mr", "5 Kent Ridge Drive", "412342",
                "ms.z.summer@gmail.com", Boolean.FALSE, "China", memlvl3);
        em.persist(member10);
        em.flush();

        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@        
        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Transaction Setup @@@@@@@@@@@@@@@@@@@@@@@@@@
        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        //===============================Transaction 3================================
        Calendar TranDate3 = Calendar.getInstance();
        TranDate3.set(2014, 5, 1);
        
        TransactionEntity tr3 = new TransactionEntity(TranDate3, 249.9, 1, s1, member2);
        tr3.setPOSid("F1");
        em.persist(tr3);

        s1.getTransactions().add(tr3);
        member2.getTransactionList().add(tr3);
        member2.setLastTransaction(tr3);
        em.flush();

        //===============================Transaction 4================================
        Calendar TranDate4 = Calendar.getInstance();
        TranDate4.set(2014, 7, 1);

        TransactionEntity tr4 = new TransactionEntity(TranDate4, 109.9, 1, s2, member3);
        tr4.setPOSid("F1");
        em.persist(tr4);

        s2.getTransactions().add(tr4);
        member3.getTransactionList().add(tr4);
        member3.setLastTransaction(tr4);
        em.flush();

        //===============================Transaction 5================================
        Calendar TranDate5 = Calendar.getInstance();
        TranDate5.set(2013, 12, 1);

        TransactionEntity tr5 = new TransactionEntity(TranDate5, 143.8, 1, s2, member3);
        tr5.setPOSid("F1");
        em.persist(tr5);

        s2.getTransactions().add(tr5);
        member3.getTransactionList().add(tr5);
        member3.setLastTransaction(tr5);
        em.flush();

        //===============================Transaction 6================================
        Calendar TranDate6 = Calendar.getInstance();
        TranDate6.set(2014, 5, 1);

        TransactionEntity tr6 = new TransactionEntity(TranDate6, 69.2, 2, s1, member4);
        tr6.setPOSid("R1");
        em.persist(tr6);

        s1.getTransactions().add(tr6);
        member4.getTransactionList().add(tr6);
        member4.setLastTransaction(tr6);
        em.flush();

        //===============================Transaction 7================================
        Calendar TranDate7 = Calendar.getInstance();
        TranDate7.set(2014, 6, 1);

        TransactionEntity tr7 = new TransactionEntity(TranDate7, 230.2, 2, s1, member4);
        tr7.setPOSid("R2");
        em.persist(tr7);

        s1.getTransactions().add(tr7);
        member4.getTransactionList().add(tr7);
        member4.setLastTransaction(tr7);
        em.flush();

        //===============================Transaction 8================================
        Calendar TranDate8 = Calendar.getInstance();
        TranDate8.set(2014, 7, 1);

        TransactionEntity tr8 = new TransactionEntity(TranDate8, 100.2, 2, s1, member5);
        tr8.setPOSid("R2");
        em.persist(tr8);

        s1.getTransactions().add(tr8);
        member5.getTransactionList().add(tr8);
        member5.setLastTransaction(tr8);
        em.flush();

        //===============================Transaction 9================================
        Calendar TranDate9 = Calendar.getInstance();
        TranDate9.set(2014, 4, 1);

        TransactionEntity tr9 = new TransactionEntity(TranDate9, 99.8, 2, s1, member5);
        tr9.setPOSid("R2");
        em.persist(tr9);

        s1.getTransactions().add(tr9);
        member5.getTransactionList().add(tr9);
        member5.setLastTransaction(tr9);
        em.flush();

        //===============================Transaction 10================================
        Calendar TranDate10 = Calendar.getInstance();
        TranDate10.set(2014, 8, 1);

        TransactionEntity tr10 = new TransactionEntity(TranDate10, 50.7, 3, s1, member6);
        em.persist(tr10);

        s1.getTransactions().add(tr10);
        member6.getTransactionList().add(tr10);
        member6.setLastTransaction(tr10);
        em.flush();

        //===============================Transaction 11================================
        Calendar TranDate11 = Calendar.getInstance();
        TranDate11.set(2014, 10, 1);

        TransactionEntity tr11 = new TransactionEntity(TranDate11, 98.7, 3, s1, member5);
        em.persist(tr11);

        s1.getTransactions().add(tr11);
        member5.getTransactionList().add(tr11);
        member5.setLastTransaction(tr11);
        em.flush();

        //===============================Transaction 12================================
        Calendar TranDate12 = Calendar.getInstance();
        TranDate12.set(2014, 10, 12);

        TransactionEntity tr12 = new TransactionEntity(TranDate12, 75.7, 3, s1, member8);
        em.persist(tr12);

        s1.getTransactions().add(tr12);
        member8.getTransactionList().add(tr12);
        member8.setLastTransaction(tr12);
        em.flush();

        //===============================Transaction 13================================
        Calendar TranDate2 = Calendar.getInstance();
        TranDate2.set(2014, 9, 1);

        TransactionEntity tr13 = new TransactionEntity(TranDate2, 300.2, 1, s1, member2);
        em.persist(tr13);

        s1.getTransactions().add(tr13);
        member2.getTransactionList().add(tr13);
        member2.setLastTransaction(tr13);
        em.flush();
        
        
        
        //=========Warehouse Bin=========
        StoreWarehouseBinEntity sb1 = new StoreWarehouseBinEntity("1-1","Living Room",true,false,false);
        em.persist(sb1);
        sb1.setStore(s1);
        StoreWarehouseBinEntity sb2 = new StoreWarehouseBinEntity("1-2","Bed Room",true,false,false);
        em.persist(sb2);
        sb2.setStore(s1);
        StoreWarehouseBinEntity sb3 = new StoreWarehouseBinEntity("1-1","Living Room",false,false,true);
        em.persist(sb3);
        sb3.setStore(s1);
        StoreWarehouseBinEntity sb4 = new StoreWarehouseBinEntity("1-3","Wash Room",true,false,false); 
        em.persist(sb4);
        sb4.setStore(s1);
        StoreWarehouseBinEntity sb5 = new StoreWarehouseBinEntity("1-1","Heavy Product",false,true,false); 
        em.persist(sb5);
        sb5.setStore(s1);
        StoreWarehouseBinEntity sb6 = new StoreWarehouseBinEntity("3-2","AAAAAA 22222",false,true,false); 
        em.persist(sb6);
        sb6.setStore(s1);
        StoreWarehouseBinEntity sb7 = new StoreWarehouseBinEntity("2-2","bbbbb 2222",false,false,true); 
        em.persist(sb7);
        sb7.setStore(s1);
        
        em.flush();



    }
    
    
    //===================ticket ========================
    

}
