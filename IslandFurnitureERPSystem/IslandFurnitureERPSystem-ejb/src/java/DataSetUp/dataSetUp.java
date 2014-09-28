/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSetUp;

import Entity.CommonInfrastructure.FactoryUserEntity;
import Entity.CommonInfrastructure.HQUserEntity;
import Entity.CommonInfrastructure.IdNumberEntity;
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
import Entity.Factory.SCM.RawMaterialInFactoryUseMovementEntity;
import Entity.Factory.SCM.SupplierEntity;
import Entity.Store.StoreEntity;
import Entity.Store.StoreProductEntity;
import Entity.Store.StoreRetailProductEntity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Yoky
 */
@Singleton
@LocalBean
@Startup
public class dataSetUp {

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void init() {
        createDatabase();
    }

    public void createDatabase() {

        //idNumberEntity
        IdNumberEntity id = new IdNumberEntity();
        id.setId_F(1000001L);
        id.setId_H(1000001L);
        id.setId_S(1000000L);
        em.persist(id);

        //HQUser
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String bir = "02-09-1993";
        Calendar birthday = Calendar.getInstance();
        try {
            birthday.setTime(df.parse(bir));
        } catch (ParseException ex) {
            Logger.getLogger(dataSetUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        UserEntity u = new HQUserEntity("H", "1000001", 0,
                "Zheng", null, "Yuan", "Global Manager", birthday, "Female", "Ms", "Kent Ridge Crescent 15", "119215", "vicky.yuanzheng@gmail.com", false, 1000000L);
        u.setPwd("123");
        em.persist(u);
        em.flush();

        //Factory
        FactoryEntity f1 = new FactoryEntity("Singapore", "Kent Ridge Crescent 1", "+6512345678", "Zhang Shiyu", false);
        em.persist(f1);
        FactoryEntity f2 = new FactoryEntity("United States", "4400 Shellmound St, Emeryville, CA", "+1888-888-4532", "Jeremy Bowen", false);
        em.persist(f2);

        //Factory Bin
        //for f1
        FactoryBinEntity fb1_1 = new FactoryBinEntity();
        fb1_1.setFactory(f1);
        em.persist(fb1_1);
        FactoryBinEntity fb1_2 = new FactoryBinEntity();
        fb1_2.setFactory(f1);
        em.persist(fb1_2);
        FactoryBinEntity fb1_3 = new FactoryBinEntity();
        fb1_3.setFactory(f1);
        em.persist(fb1_3);
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
        FactoryBinEntity fb2_2 = new FactoryBinEntity();
        fb2_2.setFactory(f2);
        em.persist(fb2_2);
        FactoryBinEntity fb2_3 = new FactoryBinEntity();
        fb2_3.setFactory(f2);
        em.persist(fb2_3);
        ArrayList fb2 = new ArrayList();
        fb2.add(fb2_1);
        fb2.add(fb2_2);
        fb2.add(fb2_3);
        f2.setFactoryBins(fb2);
        em.flush();

        //FactoryUser(f1)
        UserEntity u1 = new FactoryUserEntity("F", "1000001", 1, "Zhang", null,
                "Shiyu", "Factory Manager", birthday, "Female",
                "Ms", "West Coast Road 20", "250620", "ms.z.summer@gmail.com", f1.getFactoryId(), false);
        u1.setPwd("123");
        em.persist(u1);
        em.flush();
        //FactoryUser(f2)
        UserEntity u2 = new FactoryUserEntity("F", "1000002", 1, "Bowen", null,
                "Jeremy", "Factory Manager", birthday, "Male",
                "Mr", "New York Road 20", "250620", "jeremy.bowen@gmail.com", f2.getFactoryId(), false);
        u2.setPwd("123");
        em.persist(u2);
        em.flush();

        //Store
        StoreEntity s1 = new StoreEntity("60 Anson Road, #14-01 Mapletree Anson, Singapore 079914", "Singapore", "+65 6889 1000", "Mark Reckless", false);
        em.persist(s1);
        StoreEntity s2 = new StoreEntity("1 Raffles Link, 07-01 South Lobby, Singapore 039393", "Singapore", "+65 6889 1000", "Brooks Newmark", false);
        em.persist(s2);

        //StoreProduct      /* Further Modification*/
        //for s1
        //s1.factoryProduct
        StoreProductEntity sp1_1 = new StoreProductEntity(f1, s1);
        em.persist(sp1_1);
        StoreProductEntity sp1_2 = new StoreProductEntity(f2, s1);
        em.persist(sp1_2);
        //s2.factoryProduct
        StoreProductEntity sp2_1 = new StoreProductEntity(f1, s2);
        em.persist(sp2_1);
        StoreProductEntity sp2_2 = new StoreProductEntity(f2, s2);
        em.persist(sp2_2);
        
        //StoreRetailProduct    /* Further Modification*/
        //for s1
        //s1.StoreRetailProduct
        StoreRetailProductEntity srp1_1 = new StoreRetailProductEntity(f1, s1);
        em.persist(srp1_1);
        StoreRetailProductEntity srp1_2 = new StoreRetailProductEntity(f2, s1);
        em.persist(srp1_2);
        //s2.StoreRetailProduct
        StoreRetailProductEntity srp2_1 = new StoreRetailProductEntity(f1, s2);
        em.persist(srp2_1);
        StoreRetailProductEntity srp2_2 = new StoreRetailProductEntity(f2, s2);
        em.persist(srp2_2);
        
        
        //Raw Material
        RawMaterialEntity rm1 = new RawMaterialEntity("board", "wood", false, "square meter");
        em.persist(rm1);
        RawMaterialEntity rm2 = new RawMaterialEntity("nail", "2mm, 50pcs/box", false, "box");
        em.persist(rm2);
        RawMaterialEntity rm3 = new RawMaterialEntity("glass", "sekken frosted glass", false, "square meter");
        em.persist(rm3);
        RawMaterialEntity rm4 = new RawMaterialEntity("leather", "black", false, "square meter");
        em.persist(rm4);
        RawMaterialEntity rm5 = new RawMaterialEntity("fabric", "blue", false, "square meter");
        em.persist(rm5);

        //Product
        ProductEntity p1 = new ProductEntity("Sofa", "Sofa and chaise lounge, Grann, Bomstad dark brown", 1499.0, "set", false);
        em.persist(p1);
        ProductEntity p2 = new ProductEntity("TV Storage", "TV storage combination, black-brown", 499.0, "set", false);
        em.persist(p2);
        ProductEntity p3 = new ProductEntity("Coffee Table", "Coffee table, high gloss black", 199.0, "one", false);
        em.persist(p3);
        ProductEntity p4 = new ProductEntity("Ceiling Light", "LED chandelier, chrome plated", 59.99, "set", false);
        em.persist(p4);
        ProductEntity p5 = new ProductEntity("Wardrobe", "Wardrobe, black-brown, Sekken frosted glass", 884.0, "one", false);
        em.persist(p5);

        //Product.BOM
        //for p1
        BOMEntity bom1_1 = new BOMEntity(rm4, rm4.getUnit(), 3.0, p1);
        em.persist(bom1_1);
        BOMEntity bom1_2 = new BOMEntity(rm2, rm2.getUnit(), 5.0, p1);
        em.persist(bom1_2);
        BOMEntity bom1_3 = new BOMEntity(rm1, rm1.getUnit(), 5.0, p1);
        em.persist(bom1_3);
        List bom1 = new ArrayList();
        bom1.add(bom1_1);
        bom1.add(bom1_2);
        bom1.add(bom1_3);
        p1.setBom(bom1);
        em.flush();
        //for p2
        BOMEntity bom2_1 = new BOMEntity(rm1, rm1.getUnit(), 5.0, p2);
        em.persist(bom2_1);
        BOMEntity bom2_2 = new BOMEntity(rm3, rm3.getUnit(), 2.0, p2);
        em.persist(bom2_2);
        BOMEntity bom2_3 = new BOMEntity(rm2, rm2.getUnit(), 3.0, p2);
        em.persist(bom2_3);
        List bom2 = new ArrayList();
        bom2.add(bom2_1);
        bom2.add(bom2_2);
        bom2.add(bom2_3);
        p2.setBom(bom2);
        em.flush();
        //for p3
        BOMEntity bom3_1 = new BOMEntity(rm1, rm1.getUnit(), 3.0, p3);
        em.persist(bom3_1);
        BOMEntity bom3_2 = new BOMEntity(rm2, rm2.getUnit(), 5.0, p3);
        em.persist(bom3_2);
        BOMEntity bom3_3 = new BOMEntity(rm3, rm3.getUnit(), 2.0, p3);
        em.persist(bom3_3);
        List bom3 = new ArrayList();
        bom3.add(bom3_1);
        bom3.add(bom3_2);
        bom3.add(bom3_3);
        p3.setBom(bom3);
        em.flush();
        //for p4
        BOMEntity bom4_1 = new BOMEntity(rm3, rm3.getUnit(), 2.0, p4);
        em.persist(bom4_1);
        BOMEntity bom4_2 = new BOMEntity(rm2, rm2.getUnit(), 1.0, p4);
        em.persist(bom4_2);
        List bom4 = new ArrayList();
        bom4.add(bom4_1);
        bom4.add(bom4_2);
        p4.setBom(bom4);
        em.flush();
        //for p5
        BOMEntity bom5_1 = new BOMEntity(rm1, rm1.getUnit(), 3.0, p5);
        em.persist(bom5_1);
        BOMEntity bom5_2 = new BOMEntity(rm2, rm2.getUnit(), 6.0, p5);
        em.persist(bom5_2);
        BOMEntity bom5_3 = new BOMEntity(rm3, rm3.getUnit(), 3.0, p5);
        em.persist(bom5_3);
        List bom5 = new ArrayList();
        bom5.add(bom5_1);
        bom5.add(bom5_2);
        bom5.add(bom5_3);
        p5.setBom(bom5);
        em.flush();

        //Retail Product
        RetailProductEntity rp1 = new RetailProductEntity("Kellogg's Special K Cereal", "Red Berries 317G", "box", false);
        em.persist(rp1);
        RetailProductEntity rp2 = new RetailProductEntity("Nescafe Milk Coffee Canned Drink 6S", "Latte 240ML", "set", false);
        em.persist(rp2);
        RetailProductEntity rp3 = new RetailProductEntity("Hardys VR", "Shiraz 750ML", "bottle", false);
        em.persist(rp3);
        RetailProductEntity rp4 = new RetailProductEntity("Nature's Wonders", "Baked Cashew Nuts 240G", "bag", false);
        em.persist(rp4);
        RetailProductEntity rp5 = new RetailProductEntity("UIC Big Value Conc Liq Dtrgnt Rf", "Anti-Bac 1.8LT", "bottle", false);
        em.persist(rp5);

        //Factory Raw Material
        //for f1
        FactoryRawMaterialEntity frm1_1 = new FactoryRawMaterialEntity(rm1.getUnit(), rm1.getMaterialName(), rm1.getDescription(), false, f1, rm1);
        em.persist(frm1_1);
        rm1.getFactoryRawMaterials().add(frm1_1);
        f1.getFactoryRawMaterials().add(frm1_1);
        FactoryRawMaterialEntity frm1_2 = new FactoryRawMaterialEntity(rm2.getUnit(), rm2.getMaterialName(), rm2.getDescription(), false, f1, rm2);
        em.persist(frm1_2);
        rm2.getFactoryRawMaterials().add(frm1_2);
        f1.getFactoryRawMaterials().add(frm1_2);
        FactoryRawMaterialEntity frm1_3 = new FactoryRawMaterialEntity(rm3.getUnit(), rm3.getMaterialName(), rm3.getDescription(), false, f1, rm3);
        em.persist(frm1_3);
        rm3.getFactoryRawMaterials().add(frm1_3);
        f1.getFactoryRawMaterials().add(frm1_3);
        FactoryRawMaterialEntity frm1_4 = new FactoryRawMaterialEntity(rm4.getUnit(), rm4.getMaterialName(), rm4.getDescription(), false, f1, rm4);
        em.persist(frm1_4);
        rm4.getFactoryRawMaterials().add(frm1_4);
        f1.getFactoryRawMaterials().add(frm1_4);
        FactoryRawMaterialEntity frm1_5 = new FactoryRawMaterialEntity(rm5.getUnit(), rm5.getMaterialName(), rm5.getDescription(), false, f1, rm5);
        em.persist(frm1_5);
        rm5.getFactoryRawMaterials().add(frm1_5);
        f1.getFactoryRawMaterials().add(frm1_5);
        em.flush();
        //for f2
        FactoryRawMaterialEntity frm2_1 = new FactoryRawMaterialEntity(rm1.getUnit(), rm1.getMaterialName(), rm1.getDescription(), false, f2, rm1);
        em.persist(frm2_1);
        rm1.getFactoryRawMaterials().add(frm2_1);
        f2.getFactoryRawMaterials().add(frm2_1);
        FactoryRawMaterialEntity frm2_2 = new FactoryRawMaterialEntity(rm2.getUnit(), rm2.getMaterialName(), rm2.getDescription(), false, f2, rm2);
        em.persist(frm2_2);
        rm2.getFactoryRawMaterials().add(frm2_2);
        f2.getFactoryRawMaterials().add(frm2_2);
        FactoryRawMaterialEntity frm2_3 = new FactoryRawMaterialEntity(rm3.getUnit(), rm3.getMaterialName(), rm3.getDescription(), false, f2, rm3);
        em.persist(frm2_3);
        rm3.getFactoryRawMaterials().add(frm2_3);
        f2.getFactoryRawMaterials().add(frm2_3);
        FactoryRawMaterialEntity frm2_4 = new FactoryRawMaterialEntity(rm4.getUnit(), rm4.getMaterialName(), rm4.getDescription(), false, f2, rm4);
        em.persist(frm2_4);
        rm4.getFactoryRawMaterials().add(frm2_4);
        f2.getFactoryRawMaterials().add(frm2_4);
        FactoryRawMaterialEntity frm2_5 = new FactoryRawMaterialEntity(rm5.getUnit(), rm5.getMaterialName(), rm5.getDescription(), false, f2, rm5);
        em.persist(frm2_5);
        rm5.getFactoryRawMaterials().add(frm2_5);
        f2.getFactoryRawMaterials().add(frm2_5);
        em.flush();

        //Factory Product
        //for f1
        FactoryProductEntity fp1_1 = new FactoryProductEntity(p1.getUnit(), f1, p1);
        em.persist(fp1_1);
        f1.getFactoryProducts().add(fp1_1);
        p1.getFactoryProduct().add(fp1_1);
        FactoryProductEntity fp1_2 = new FactoryProductEntity(p2.getUnit(), f1, p2);
        em.persist(fp1_1);
        f1.getFactoryProducts().add(fp1_2);
        p2.getFactoryProduct().add(fp1_2);
        FactoryProductEntity fp1_3 = new FactoryProductEntity(p3.getUnit(), f1, p3);
        em.persist(fp1_1);
        f1.getFactoryProducts().add(fp1_3);
        p3.getFactoryProduct().add(fp1_3);
        FactoryProductEntity fp1_4 = new FactoryProductEntity(p4.getUnit(), f1, p4);
        em.persist(fp1_1);
        f1.getFactoryProducts().add(fp1_4);
        p4.getFactoryProduct().add(fp1_4);
        FactoryProductEntity fp1_5 = new FactoryProductEntity(p5.getUnit(), f1, p5);
        em.persist(fp1_1);
        f1.getFactoryProducts().add(fp1_5);
        p5.getFactoryProduct().add(fp1_5);
        em.flush();
        //for f2
        FactoryProductEntity fp2_1 = new FactoryProductEntity(p1.getUnit(), f2, p1);
        em.persist(fp2_1);
        f2.getFactoryProducts().add(fp2_1);
        p1.getFactoryProduct().add(fp2_1);
        FactoryProductEntity fp2_2 = new FactoryProductEntity(p2.getUnit(), f2, p2);
        em.persist(fp2_1);
        f2.getFactoryProducts().add(fp2_2);
        p2.getFactoryProduct().add(fp2_2);
        em.flush();

        //Factory Retail Product
        //for f1
        FactoryRetailProductEntity frp1_1 = new FactoryRetailProductEntity(rp1.getUnit(), rp1.getName(), rp1.getDescription(), f1, rp1);
        em.persist(frp1_1);
        f1.getFactoryRetailProducts().add(frp1_1);
        rp1.getFactoryRetailProducts().add(frp1_1);
        FactoryRetailProductEntity frp1_2 = new FactoryRetailProductEntity(rp2.getUnit(), rp2.getName(), rp2.getDescription(), f1, rp2);
        em.persist(frp1_2);
        f1.getFactoryRetailProducts().add(frp1_2);
        rp2.getFactoryRetailProducts().add(frp1_2);
        FactoryRetailProductEntity frp1_3 = new FactoryRetailProductEntity(rp3.getUnit(), rp3.getName(), rp3.getDescription(), f1, rp3);
        em.persist(frp1_3);
        f1.getFactoryRetailProducts().add(frp1_3);
        rp3.getFactoryRetailProducts().add(frp1_3);
        FactoryRetailProductEntity frp1_4 = new FactoryRetailProductEntity(rp4.getUnit(), rp4.getName(), rp4.getDescription(), f1, rp4);
        em.persist(frp1_4);
        f1.getFactoryRetailProducts().add(frp1_4);
        rp4.getFactoryRetailProducts().add(frp1_4);
        FactoryRetailProductEntity frp1_5 = new FactoryRetailProductEntity(rp5.getUnit(), rp5.getName(), rp5.getDescription(), f1, rp5);
        em.persist(frp1_5);
        f1.getFactoryRetailProducts().add(frp1_5);
        rp5.getFactoryRetailProducts().add(frp1_5);
        em.flush();
        //for f2
        FactoryRetailProductEntity frp2_1 = new FactoryRetailProductEntity(rp1.getUnit(), rp1.getName(), rp1.getDescription(), f2, rp1);
        em.persist(frp2_1);
        f2.getFactoryRetailProducts().add(frp1_1);
        rp1.getFactoryRetailProducts().add(frp1_1);
        FactoryRetailProductEntity frp2_2 = new FactoryRetailProductEntity(rp2.getUnit(), rp2.getName(), rp2.getDescription(), f2, rp2);
        em.persist(frp2_2);
        f2.getFactoryRetailProducts().add(frp1_2);
        rp2.getFactoryRetailProducts().add(frp1_2);
        em.flush();

        //Factory Bin Stored Product
        //for f1.factoryRawMaterial
        FactoryBinStoredProductEntity fbsp1_1_1 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_1_1);
        fbsp1_1_1.createFactoryBinStoredProduct(frm1_1, fb1_1, "unrestricted");
        fbsp1_1_1.setAmount(1000.0);
        frm1_1.setUnrestrictedInventory(1000.0);
        fb1_1.getFactoryBinStoredProducts().add(fbsp1_1_1);
        frm1_1.getFactoryBinStoredProducts().add(fbsp1_1_1);
        FactoryBinStoredProductEntity fbsp1_1_2 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_1_2);
        fbsp1_1_2.createFactoryBinStoredProduct(frm1_2, fb1_1, "unrestricted");
        fbsp1_1_2.setAmount(1000.0);
        frm1_2.setUnrestrictedInventory(1000.0);
        fb1_1.getFactoryBinStoredProducts().add(fbsp1_1_2);
        frm1_2.getFactoryBinStoredProducts().add(fbsp1_1_2);
        FactoryBinStoredProductEntity fbsp1_1_3 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_1_3);
        fbsp1_1_3.createFactoryBinStoredProduct(frm1_3, fb1_1, "unrestricted");
        fbsp1_1_3.setAmount(1000.0);
        frm1_3.setUnrestrictedInventory(1000.0);
        fb1_1.getFactoryBinStoredProducts().add(fbsp1_1_3);
        frm1_3.getFactoryBinStoredProducts().add(fbsp1_1_3);
        FactoryBinStoredProductEntity fbsp1_1_4 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_1_4);
        fbsp1_1_4.createFactoryBinStoredProduct(frm1_4, fb1_1, "unrestricted");
        fbsp1_1_4.setAmount(1000.0);
        frm1_4.setUnrestrictedInventory(1000.0);
        fb1_1.getFactoryBinStoredProducts().add(fbsp1_1_4);
        frm1_4.getFactoryBinStoredProducts().add(fbsp1_1_4);
        FactoryBinStoredProductEntity fbsp1_1_4_2 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_1_4_2);
        fbsp1_1_4_2.createFactoryBinStoredProduct(frm1_4, fb1_1, "blocked");
        fbsp1_1_4_2.setAmount(100.0);
        frm1_4.setBlockedInventory(100.0);
        fb1_1.getFactoryBinStoredProducts().add(fbsp1_1_4_2);
        frm1_4.getFactoryBinStoredProducts().add(fbsp1_1_4_2);
        FactoryBinStoredProductEntity fbsp1_1_5 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_1_5);
        fbsp1_1_5.createFactoryBinStoredProduct(frm1_5, fb1_1, "unrestricted");
        fbsp1_1_5.setAmount(1000.0);
        frm1_5.setUnrestrictedInventory(1000.0);
        fb1_1.getFactoryBinStoredProducts().add(fbsp1_1_5);
        frm1_5.getFactoryBinStoredProducts().add(fbsp1_1_5);
        em.flush();
        //for f1.factoryProduct
        FactoryBinStoredProductEntity fbsp1_2_1 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_2_1);
        fbsp1_2_1.createFactoryBinStoredProduct(fp1_1, fb1_2, "unrestricted");
        fbsp1_2_1.setAmount(500.0);
        fp1_1.setUnrestrictedInventory(500.0);
        fb1_2.getFactoryBinStoredProducts().add(fbsp1_2_1);
        fp1_1.getFactoryBinStoredProducts().add(fbsp1_2_1);
        FactoryBinStoredProductEntity fbsp1_2_2 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_2_2);
        fbsp1_2_2.createFactoryBinStoredProduct(fp1_2, fb1_2, "unrestricted");
        fbsp1_2_2.setAmount(500.0);
        fp1_2.setUnrestrictedInventory(500.0);
        fb1_2.getFactoryBinStoredProducts().add(fbsp1_2_2);
        fp1_2.getFactoryBinStoredProducts().add(fbsp1_2_2);
        FactoryBinStoredProductEntity fbsp1_2_3 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_2_3);
        fbsp1_2_3.createFactoryBinStoredProduct(fp1_3, fb1_2, "unrestricted");
        fbsp1_2_3.setAmount(500.0);
        fp1_3.setUnrestrictedInventory(500.0);
        fb1_2.getFactoryBinStoredProducts().add(fbsp1_2_3);
        fp1_3.getFactoryBinStoredProducts().add(fbsp1_2_3);
        FactoryBinStoredProductEntity fbsp1_2_4 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_2_4);
        fbsp1_2_4.createFactoryBinStoredProduct(fp1_4, fb1_2, "unrestricted");
        fbsp1_2_4.setAmount(500.0);
        fp1_4.setUnrestrictedInventory(500.0);
        fb1_2.getFactoryBinStoredProducts().add(fbsp1_2_4);
        fp1_4.getFactoryBinStoredProducts().add(fbsp1_2_4);
        FactoryBinStoredProductEntity fbsp1_2_4_2 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_2_4_2);
        fbsp1_2_4_2.createFactoryBinStoredProduct(fp1_4, fb1_2, "blocked");
        fbsp1_2_4_2.setAmount(50.0);
        fp1_4.setBlockedInventory(50.0);
        fb1_2.getFactoryBinStoredProducts().add(fbsp1_2_4_2);
        fp1_4.getFactoryBinStoredProducts().add(fbsp1_2_4_2);
        FactoryBinStoredProductEntity fbsp1_2_5 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_2_5);
        fbsp1_2_5.createFactoryBinStoredProduct(fp1_5, fb1_2, "unrestricted");
        fbsp1_2_5.setAmount(500.0);
        fp1_5.setUnrestrictedInventory(500.0);
        fb1_2.getFactoryBinStoredProducts().add(fbsp1_2_5);
        fp1_5.getFactoryBinStoredProducts().add(fbsp1_2_5);
        FactoryBinStoredProductEntity fbsp1_2_5_3 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_2_5_3);
        fbsp1_2_5_3.createFactoryBinStoredProduct(fp1_5, fb1_2, "returned");
        fbsp1_2_5_3.setAmount(1.0);
        fp1_5.setReturnedInventory(1.0);
        fb1_2.getFactoryBinStoredProducts().add(fbsp1_2_5_3);
        fp1_5.getFactoryBinStoredProducts().add(fbsp1_2_5_3);
        em.flush();
        //for f1.factoryRetailProduct
        FactoryBinStoredProductEntity fbsp1_3_1 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_3_1);
        fbsp1_3_1.createFactoryBinStoredProduct(frp1_1, fb1_3, "unrestricted");
        fbsp1_3_1.setAmount(300.0);
        frp1_1.setUnrestrictedInventory(300.0);
        fb1_3.getFactoryBinStoredProducts().add(fbsp1_3_1);
        frp1_1.getFactoryBinStoredProducts().add(fbsp1_3_1);
        FactoryBinStoredProductEntity fbsp1_3_2 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_3_2);
        fbsp1_3_2.createFactoryBinStoredProduct(frp1_2, fb1_3, "unrestricted");
        fbsp1_3_2.setAmount(300.0);
        frp1_2.setUnrestrictedInventory(300.0);
        fb1_3.getFactoryBinStoredProducts().add(fbsp1_3_2);
        frp1_2.getFactoryBinStoredProducts().add(fbsp1_3_2);
        FactoryBinStoredProductEntity fbsp1_3_3 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_3_3);
        fbsp1_3_3.createFactoryBinStoredProduct(frp1_3, fb1_3, "unrestricted");
        fbsp1_3_3.setAmount(300.0);
        frp1_3.setUnrestrictedInventory(300.0);
        fb1_3.getFactoryBinStoredProducts().add(fbsp1_3_3);
        frp1_3.getFactoryBinStoredProducts().add(fbsp1_3_3);
        FactoryBinStoredProductEntity fbsp1_3_4 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_3_4);
        fbsp1_3_4.createFactoryBinStoredProduct(frp1_4, fb1_3, "unrestricted");
        fbsp1_3_4.setAmount(300.0);
        frp1_4.setUnrestrictedInventory(300.0);
        fb1_3.getFactoryBinStoredProducts().add(fbsp1_3_4);
        frp1_4.getFactoryBinStoredProducts().add(fbsp1_3_4);
        FactoryBinStoredProductEntity fbsp1_3_4_2 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_3_4_2);
        fbsp1_3_4_2.createFactoryBinStoredProduct(frp1_4, fb1_3, "blocked");
        fbsp1_3_4_2.setAmount(30.0);
        frp1_4.setBlockedInventory(30.0);
        fb1_3.getFactoryBinStoredProducts().add(fbsp1_3_4_2);
        frp1_4.getFactoryBinStoredProducts().add(fbsp1_3_4_2);
        FactoryBinStoredProductEntity fbsp1_3_5 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_3_5);
        fbsp1_3_5.createFactoryBinStoredProduct(frp1_5, fb1_3, "unrestricted");
        fbsp1_3_5.setAmount(300.0);
        frp1_5.setUnrestrictedInventory(300.0);
        fb1_3.getFactoryBinStoredProducts().add(fbsp1_3_5);
        frp1_5.getFactoryBinStoredProducts().add(fbsp1_3_5);
        FactoryBinStoredProductEntity fbsp1_3_5_3 = new FactoryBinStoredProductEntity();
        em.persist(fbsp1_3_5_3);
        fbsp1_3_5_3.createFactoryBinStoredProduct(frp1_5, fb1_3, "returned");
        fbsp1_3_5_3.setAmount(3.0);
        frp1_5.setReturnedInventory(3.0);
        fb1_3.getFactoryBinStoredProducts().add(fbsp1_3_5_3);
        frp1_5.getFactoryBinStoredProducts().add(fbsp1_3_5_3);
        em.flush();

        //for f2.factoryRawMaterial
        FactoryBinStoredProductEntity fbsp2_1_1 = new FactoryBinStoredProductEntity();
        em.persist(fbsp2_1_1);
        fbsp2_1_1.createFactoryBinStoredProduct(frm2_1, fb2_1, "unrestricted");
        fbsp2_1_1.setAmount(1000.0);
        frm2_1.setUnrestrictedInventory(1000.0);
        fb2_1.getFactoryBinStoredProducts().add(fbsp2_1_1);
        frm2_1.getFactoryBinStoredProducts().add(fbsp2_1_1);
        FactoryBinStoredProductEntity fbsp2_1_2 = new FactoryBinStoredProductEntity();
        em.persist(fbsp2_1_2);
        fbsp2_1_2.createFactoryBinStoredProduct(frm2_2, fb2_1, "unrestricted");
        fbsp2_1_2.setAmount(1000.0);
        frm2_2.setUnrestrictedInventory(1000.0);
        fb2_1.getFactoryBinStoredProducts().add(fbsp2_1_2);
        frm2_2.getFactoryBinStoredProducts().add(fbsp2_1_2);
        FactoryBinStoredProductEntity fbsp2_1_3 = new FactoryBinStoredProductEntity();
        em.persist(fbsp2_1_3);
        fbsp2_1_3.createFactoryBinStoredProduct(frm2_3, fb2_1, "unrestricted");
        fbsp2_1_3.setAmount(1000.0);
        frm2_3.setUnrestrictedInventory(1000.0);
        fb2_1.getFactoryBinStoredProducts().add(fbsp2_1_3);
        frm2_3.getFactoryBinStoredProducts().add(fbsp2_1_3);
        FactoryBinStoredProductEntity fbsp2_1_4 = new FactoryBinStoredProductEntity();
        em.persist(fbsp2_1_4);
        fbsp2_1_4.createFactoryBinStoredProduct(frm2_4, fb2_1, "unrestricted");
        fbsp2_1_4.setAmount(1000.0);
        frm2_4.setUnrestrictedInventory(1000.0);
        fb2_1.getFactoryBinStoredProducts().add(fbsp2_1_4);
        frm2_4.getFactoryBinStoredProducts().add(fbsp2_1_4);
        FactoryBinStoredProductEntity fbsp2_1_5 = new FactoryBinStoredProductEntity();
        em.persist(fbsp2_1_5);
        fbsp2_1_5.createFactoryBinStoredProduct(frm2_5, fb2_1, "unrestricted");
        fbsp2_1_5.setAmount(1000.0);
        frm2_5.setUnrestrictedInventory(1000.0);
        fb2_1.getFactoryBinStoredProducts().add(fbsp2_1_5);
        frm2_5.getFactoryBinStoredProducts().add(fbsp2_1_5);
        em.flush();
        //for f2.factoryProduct
        FactoryBinStoredProductEntity fbsp2_2_1 = new FactoryBinStoredProductEntity();
        em.persist(fbsp2_2_1);
        fbsp2_2_1.createFactoryBinStoredProduct(fp2_1, fb2_2, "unrestricted");
        fbsp2_2_1.setAmount(500.0);
        fp2_1.setUnrestrictedInventory(500.0);
        fb2_2.getFactoryBinStoredProducts().add(fbsp2_2_1);
        fp2_1.getFactoryBinStoredProducts().add(fbsp2_2_1);
        FactoryBinStoredProductEntity fbsp2_2_2 = new FactoryBinStoredProductEntity();
        em.persist(fbsp2_2_2);
        fbsp2_2_2.createFactoryBinStoredProduct(fp2_2, fb2_2, "unrestricted");
        fbsp2_2_2.setAmount(500.0);
        fp2_2.setUnrestrictedInventory(500.0);
        fb2_2.getFactoryBinStoredProducts().add(fbsp2_2_2);
        fp2_2.getFactoryBinStoredProducts().add(fbsp2_2_2);
        em.flush();
        //for f2.factoryRetailProduct
        FactoryBinStoredProductEntity fbsp2_3_1 = new FactoryBinStoredProductEntity();
        em.persist(fbsp2_3_1);
        fbsp2_3_1.createFactoryBinStoredProduct(frp2_1, fb2_3, "unrestricted");
        fbsp2_3_1.setAmount(300.0);
        frp2_1.setUnrestrictedInventory(300.0);
        fb2_3.getFactoryBinStoredProducts().add(fbsp2_3_1);
        frp2_1.getFactoryBinStoredProducts().add(fbsp2_3_1);
        FactoryBinStoredProductEntity fbsp2_3_2 = new FactoryBinStoredProductEntity();
        em.persist(fbsp2_3_2);
        fbsp2_3_2.createFactoryBinStoredProduct(frp2_2, fb2_3, "unrestricted");
        fbsp2_3_2.setAmount(300.0);
        frp2_2.setUnrestrictedInventory(300.0);
        fb2_3.getFactoryBinStoredProducts().add(fbsp2_3_2);
        frp2_2.getFactoryBinStoredProducts().add(fbsp2_3_2);
        em.flush();

        
        //Inventory Record
        //Inventory Record.Calendars
        Calendar ci1 = Calendar.getInstance();
        ci1.set(2014, 6, 1);
        Calendar ci2 = Calendar.getInstance();
        ci2.set(2014, 7, 1);
        Calendar ci3 = Calendar.getInstance();
        ci3.set(2014, 8, 1);
        //for f1.factoryRawMaterial
        InventoryRecordEntity ir1_1_1_1 = new InventoryRecordEntity(frm1_1, ci1, 1000.0);
        em.persist(ir1_1_1_1);
        frm1_1.getInventoryRecord().add(ir1_1_1_1);
        InventoryRecordEntity ir1_1_1_2 = new InventoryRecordEntity(frm1_1, ci2, 1100.0);
        em.persist(ir1_1_1_2);
        frm1_1.getInventoryRecord().add(ir1_1_1_2);
        InventoryRecordEntity ir1_1_1_3 = new InventoryRecordEntity(frm1_1, ci3, 900.0);
        em.persist(ir1_1_1_3);
        frm1_1.getInventoryRecord().add(ir1_1_1_3);
        InventoryRecordEntity ir1_1_2_1 = new InventoryRecordEntity(frm1_2, ci1, 800.0);
        em.persist(ir1_1_2_1);
        frm1_2.getInventoryRecord().add(ir1_1_2_1);
        InventoryRecordEntity ir1_1_2_2 = new InventoryRecordEntity(frm1_2, ci2, 1000.0);
        em.persist(ir1_1_2_2);
        frm1_2.getInventoryRecord().add(ir1_1_2_2);
        InventoryRecordEntity ir1_1_2_3 = new InventoryRecordEntity(frm1_2, ci3, 1200.0);
        em.persist(ir1_1_2_3);
        frm1_2.getInventoryRecord().add(ir1_1_2_3);
        InventoryRecordEntity ir1_1_3_1 = new InventoryRecordEntity(frm1_3, ci1, 1300.0);
        em.persist(ir1_1_3_1);
        frm1_3.getInventoryRecord().add(ir1_1_3_1);
        InventoryRecordEntity ir1_1_3_2 = new InventoryRecordEntity(frm1_3, ci2, 1200.0);
        em.persist(ir1_1_3_2);
        frm1_3.getInventoryRecord().add(ir1_1_3_2);
        InventoryRecordEntity ir1_1_3_3 = new InventoryRecordEntity(frm1_3, ci3, 1100.0);
        em.persist(ir1_1_3_3);
        frm1_3.getInventoryRecord().add(ir1_1_3_3);
        InventoryRecordEntity ir1_1_4_1 = new InventoryRecordEntity(frm1_4, ci1, 1100.0);
        em.persist(ir1_1_4_1);
        frm1_4.getInventoryRecord().add(ir1_1_4_1);
        InventoryRecordEntity ir1_1_4_2 = new InventoryRecordEntity(frm1_4, ci2, 1300.0);
        em.persist(ir1_1_4_2);
        frm1_4.getInventoryRecord().add(ir1_1_4_2);
        InventoryRecordEntity ir1_1_4_3 = new InventoryRecordEntity(frm1_4, ci3, 1300.0);
        em.persist(ir1_1_4_3);
        frm1_4.getInventoryRecord().add(ir1_1_4_3);
        InventoryRecordEntity ir1_1_5_1 = new InventoryRecordEntity(frm1_5, ci1, 900.0);
        em.persist(ir1_1_5_1);
        frm1_5.getInventoryRecord().add(ir1_1_5_1);
        InventoryRecordEntity ir1_1_5_2 = new InventoryRecordEntity(frm1_5, ci2, 1100.0);
        em.persist(ir1_1_5_2);
        frm1_5.getInventoryRecord().add(ir1_1_5_2);
        InventoryRecordEntity ir1_1_5_3 = new InventoryRecordEntity(frm1_5, ci3, 900.0);
        em.persist(ir1_1_5_3);
        frm1_5.getInventoryRecord().add(ir1_1_5_3);
        em.flush();
        //for f1.factoryProduct
        InventoryRecordEntity ir1_2_1_1 = new InventoryRecordEntity(fp1_1, ci1, 300.0);
        em.persist(ir1_2_1_1);
        fp1_1.getRecord().add(ir1_2_1_1);
        InventoryRecordEntity ir1_2_1_2 = new InventoryRecordEntity(fp1_1, ci2, 600.0);
        em.persist(ir1_2_1_2);
        fp1_1.getRecord().add(ir1_2_1_2);
        InventoryRecordEntity ir1_2_1_3 = new InventoryRecordEntity(fp1_1, ci3, 500.0);
        em.persist(ir1_2_1_3);
        fp1_1.getRecord().add(ir1_2_1_3);
        InventoryRecordEntity ir1_2_2_1 = new InventoryRecordEntity(fp1_2, ci1, 500.0);
        em.persist(ir1_2_2_1);
        fp1_2.getRecord().add(ir1_2_2_1);
        InventoryRecordEntity ir1_2_2_2 = new InventoryRecordEntity(fp1_2, ci2, 500.0);
        em.persist(ir1_2_2_2);
        fp1_2.getRecord().add(ir1_2_2_2);
        InventoryRecordEntity ir1_2_2_3 = new InventoryRecordEntity(fp1_2, ci3, 600.0);
        em.persist(ir1_2_2_3);
        fp1_2.getRecord().add(ir1_2_2_3);
        InventoryRecordEntity ir1_2_3_1 = new InventoryRecordEntity(fp1_3, ci1, 550.0);
        em.persist(ir1_2_3_1);
        fp1_3.getRecord().add(ir1_2_3_1);
        InventoryRecordEntity ir1_2_3_2 = new InventoryRecordEntity(fp1_3, ci2, 650.0);
        em.persist(ir1_2_3_2);
        fp1_3.getRecord().add(ir1_2_3_2);
        InventoryRecordEntity ir1_2_3_3 = new InventoryRecordEntity(fp1_3, ci3, 300.0);
        em.persist(ir1_2_3_3);
        fp1_3.getRecord().add(ir1_2_3_3);
        InventoryRecordEntity ir1_2_4_1 = new InventoryRecordEntity(fp1_4, ci1, 400.0);
        em.persist(ir1_2_4_1);
        fp1_4.getRecord().add(ir1_2_4_1);
        InventoryRecordEntity ir1_2_4_2 = new InventoryRecordEntity(fp1_4, ci2, 450.0);
        em.persist(ir1_2_4_2);
        fp1_4.getRecord().add(ir1_2_4_2);
        InventoryRecordEntity ir1_2_4_3 = new InventoryRecordEntity(fp1_4, ci3, 600.0);
        em.persist(ir1_2_4_3);
        fp1_4.getRecord().add(ir1_2_4_3);
        InventoryRecordEntity ir1_2_5_1 = new InventoryRecordEntity(fp1_5, ci1, 400.0);
        em.persist(ir1_2_5_1);
        fp1_5.getRecord().add(ir1_2_5_1);
        InventoryRecordEntity ir1_2_5_2 = new InventoryRecordEntity(fp1_5, ci2, 500.0);
        em.persist(ir1_2_5_2);
        fp1_5.getRecord().add(ir1_2_5_2);
        InventoryRecordEntity ir1_2_5_3 = new InventoryRecordEntity(fp1_5, ci3, 560.0);
        em.persist(ir1_2_5_3);
        fp1_5.getRecord().add(ir1_2_5_3);
        em.flush();
        //for f1.factoryRetailProduct
        InventoryRecordEntity ir1_3_1_1 = new InventoryRecordEntity(frp1_1, ci1, 300.0);
        em.persist(ir1_3_1_1);
        frp1_1.getInventoryRecords().add(ir1_3_1_1);
        InventoryRecordEntity ir1_3_1_2 = new InventoryRecordEntity(frp1_1, ci2, 280.0);
        em.persist(ir1_3_1_2);
        frp1_1.getInventoryRecords().add(ir1_3_1_2);
        InventoryRecordEntity ir1_3_1_3 = new InventoryRecordEntity(frp1_1, ci3, 320.0);
        em.persist(ir1_3_1_3);
        frp1_1.getInventoryRecords().add(ir1_3_1_3);
        InventoryRecordEntity ir1_3_2_1 = new InventoryRecordEntity(frp1_2, ci1, 330.0);
        em.persist(ir1_3_2_1);
        frp1_2.getInventoryRecords().add(ir1_3_2_1);
        InventoryRecordEntity ir1_3_2_2 = new InventoryRecordEntity(frp1_2, ci2, 240.0);
        em.persist(ir1_3_2_2);
        frp1_2.getInventoryRecords().add(ir1_3_2_2);
        InventoryRecordEntity ir1_3_2_3 = new InventoryRecordEntity(frp1_2, ci3, 330.0);
        em.persist(ir1_3_2_3);
        frp1_2.getInventoryRecords().add(ir1_3_2_3);
        InventoryRecordEntity ir1_3_3_1 = new InventoryRecordEntity(frp1_3, ci1, 230.0);
        em.persist(ir1_3_3_1);
        frp1_3.getInventoryRecords().add(ir1_3_3_1);
        InventoryRecordEntity ir1_3_3_2 = new InventoryRecordEntity(frp1_3, ci2, 340.0);
        em.persist(ir1_3_3_2);
        frp1_3.getInventoryRecords().add(ir1_3_3_2);
        InventoryRecordEntity ir1_3_3_3 = new InventoryRecordEntity(frp1_3, ci3, 310.0);
        em.persist(ir1_3_3_3);
        frp1_3.getInventoryRecords().add(ir1_3_3_3);
        InventoryRecordEntity ir1_3_4_1 = new InventoryRecordEntity(frp1_4, ci1, 290.0);
        em.persist(ir1_3_4_1);
        frp1_4.getInventoryRecords().add(ir1_3_4_1);
        InventoryRecordEntity ir1_3_4_2 = new InventoryRecordEntity(frp1_4, ci2, 340.0);
        em.persist(ir1_3_4_2);
        frp1_4.getInventoryRecords().add(ir1_3_4_2);
        InventoryRecordEntity ir1_3_4_3 = new InventoryRecordEntity(frp1_4, ci3, 320.0);
        em.persist(ir1_3_4_3);
        frp1_4.getInventoryRecords().add(ir1_3_4_3);
        InventoryRecordEntity ir1_3_5_1 = new InventoryRecordEntity(frp1_5, ci1, 300.0);
        em.persist(ir1_3_5_1);
        frp1_5.getInventoryRecords().add(ir1_3_5_1);
        InventoryRecordEntity ir1_3_5_2 = new InventoryRecordEntity(frp1_5, ci2, 310.0);
        em.persist(ir1_3_5_2);
        frp1_5.getInventoryRecords().add(ir1_3_5_2);
        InventoryRecordEntity ir1_3_5_3 = new InventoryRecordEntity(frp1_5, ci3, 320.0);
        em.persist(ir1_3_5_3);
        frp1_5.getInventoryRecords().add(ir1_3_5_3);
        em.flush();

        //Supplier
        //for Raw Material
        SupplierEntity sp1_1_1 = new SupplierEntity("J.K. Adams", "1430 Route 30, Dorset, Vermont, US 05251", "(800) 451-6118", "(802) 362-5472", "J.K. Adams is located in Dorset, Vermont, 4 1/2 miles north of the intersection of Routes 11/30 and 7A in Manchester Center.");
        em.persist(sp1_1_1);
        SupplierEntity sp1_1_2 = new SupplierEntity("The Wooden Board Company", "Springfield Farm, Wester Balgedie, Kinross, KY13 9HE, UK", " 07769 340524", " 07769 347492", "Handcrafted Cheese & Chopping Boards, Just For You");
        em.persist(sp1_1_2);
        SupplierEntity sp1_2_1 = new SupplierEntity("Nail Co", "740 Broadway, Ste 400, South Portland, ME 04106, US", "+1-207-799-6245", "+1-207-799-7893", " Nail Salon");
        em.persist(sp1_2_1);
        SupplierEntity sp1_2_2 = new SupplierEntity("Glasgow Steel Nail Co", "457 School Street, Mansfield, Massachusetts 02048, US", "+1 508-339-4500", "+1 508-339-0104", "This cut nail business started around 1870 and was the UK's last manufacturer of traditional square cut nails before transfering to the USA in 2013 to become a division of Acorn Manufacturing Co Inc");
        em.persist(sp1_2_2);
        SupplierEntity sp1_3_1 = new SupplierEntity("Besglas", "2 Jurong East Street 21, 03-45 IMM Building, Singapore 609601", "65 6252 3533", "65 6899 3533", "HEAD OFFICE: Blk 2 Toa Payoh Industrial Park");
        em.persist(sp1_3_1);
        SupplierEntity sp1_3_2 = new SupplierEntity("AGC Asia Pacific Pte Ltd", "460 Alexandra Road, #30-02 PSA Building, Singapore 119963", "+65 6273 5656", "+65 6271 3817", "AGC Asia Pacific Pte Ltd handles all sales & product enquiries for the Asia Pacific Region.");
        em.persist(sp1_3_2);
        SupplierEntity sp1_4_1 = new SupplierEntity("Springfield Leather Co. Inc.", "1463 S. Glenstone, Springfield, MO 65808, US", "1-417-881-0223", "1-417-881-4953", "Free Kydex Samples will incur shipping charges!");
        em.persist(sp1_4_1);
        SupplierEntity sp1_4_2 = new SupplierEntity("Maverick Leather Company", "1364 N McDowell Blvd #25, Petaluma, CA 94954, US", "707-792-2208", "707-792-2381", "Maverick Leather Company became a reality in June 2006.");
        em.persist(sp1_4_2);
        SupplierEntity sp1_5_1 = new SupplierEntity("The Fabric Co.", "17702 Chesterfield Airport Rd, Chesterfield MO 63005, US", "1-855-530-0775", "1-636-530-0775", "Sunday: Closed");
        em.persist(sp1_5_1);
        SupplierEntity sp1_5_2 = new SupplierEntity("Portsmouth Fabric Company", "112 Penhallow St, Portsmouth NH 03801, US", "(603) 436-6343", "(603) 430-2943", "Gift Cards now available for purchase. Order any amount and we will ship to any address.");
        em.persist(sp1_5_2);

        //for Retail Product
        SupplierEntity sp3_1 = new SupplierEntity("KAMAKA Group Pte Ltd", "890 Upper Bukit Timah Road, #04-20, Singapore – 678 186", " 65 6465 5420", " 65 6465 3456", "can also email at info@orderonline.sg");
        em.persist(sp3_1);
        SupplierEntity sp3_2 = new SupplierEntity("Nestlé Singapore (Pte) Ltd", "15A Changi Business Park Central 1, #05-02/03 Eightrium @ Changi Business Park, Singapore 486035", "+65 6836-7000", "+65 6588-4385", "Sponsorships/Milo Van Requests: sponsorships.sg@sg.nestle.com");
        em.persist(sp3_2);
        SupplierEntity sp3_3 = new SupplierEntity("Hardys", "202 Main Road, McLaren Vale, South Australia, 5171 Australia", "+61 8 8329 4110", "+61 8 8329 4100", "Open Public Holidays excluding Good Friday and Christmas Day.");
        em.persist(sp3_3);
        SupplierEntity sp3_4 = new SupplierEntity("Tai Sun (Lim Kee) Food Industries Pte Ltd", "255 Pandan Loop, Singapore 128433", "65 6779 6611", "65 6778 2477", "Goodness is everything");
        em.persist(sp3_4);
        SupplierEntity sp3_5 = new SupplierEntity("Universal Integrated Corporation Consumer Products Pte Ltd", "No. 3 Jalan Besut, Jurong Town, Singapore 619556", "+65 6265 1648", "+65 6265 8462", "UICCP is Singapore’s largest manufacturer of Home Care and Fabric Care products");
        em.persist(sp3_5);

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
        frm1_1.getContracts().add(ct1_1_1_2);
        sp1_1_2.getContractList().add(ct1_1_1_2);
        ContractEntity ct1_1_2_1 = new ContractEntity(150.0, 2, frm1_2.getUnit(), 100.0, cl5, cl6, frm1_2, sp1_2_1);
        em.persist(ct1_1_2_1);
        frm1_2.getContracts().add(ct1_1_2_1);
        sp1_2_1.getContractList().add(ct1_1_2_1);
        ContractEntity ct1_1_2_2 = new ContractEntity(150.0, 2, frm1_2.getUnit(), 100.0, cl7, cl8, frm1_2, sp1_2_2);
        em.persist(ct1_1_2_2);
        frm1_2.getContracts().add(ct1_1_2_2);
        sp1_2_2.getContractList().add(ct1_1_2_2);
        ContractEntity ct1_1_3_1 = new ContractEntity(100.0, 3, frm1_3.getUnit(), 10.0, cl9, cl10, frm1_3, sp1_3_1);
        em.persist(ct1_1_3_1);
        frm1_3.getContracts().add(ct1_1_3_1);
        sp1_3_1.getContractList().add(ct1_1_3_1);
        ContractEntity ct1_1_3_2 = new ContractEntity(120.0, 2, frm1_3.getUnit(), 10.0, cl1, cl2, frm1_3, sp1_3_2);
        em.persist(ct1_1_3_2);
        frm1_3.getContracts().add(ct1_1_3_2);
        sp1_3_2.getContractList().add(ct1_1_3_2);
        ContractEntity ct1_1_4_1 = new ContractEntity(300.0, 2, frm1_4.getUnit(), 5.0, cl3, cl4, frm1_4, sp1_4_1);
        em.persist(ct1_1_4_1);
        frm1_4.getContracts().add(ct1_1_4_1);
        sp1_4_1.getContractList().add(ct1_1_4_1);
        ContractEntity ct1_1_4_2 = new ContractEntity(500.0, 1, frm1_4.getUnit(), 5.0, cl5, cl6, frm1_4, sp1_4_2);
        em.persist(ct1_1_4_2);
        frm1_4.getContracts().add(ct1_1_4_2);
        sp1_4_2.getContractList().add(ct1_1_4_2);
        ContractEntity ct1_1_5_1 = new ContractEntity(100.0, 2, frm1_5.getUnit(), 5.0, cl7, cl8, frm1_5, sp1_5_1);
        em.persist(ct1_1_5_1);
        frm1_5.getContracts().add(ct1_1_5_1);
        sp1_5_1.getContractList().add(ct1_1_5_1);
        ContractEntity ct1_1_5_2 = new ContractEntity(110.0, 2, frm1_5.getUnit(), 5.0, cl9, cl10, frm1_5, sp1_5_2);
        em.persist(ct1_1_5_2);
        frm1_5.getContracts().add(ct1_1_5_2);
        sp1_5_2.getContractList().add(ct1_1_5_2);
        em.flush();

        //for f1.factoryRetailProduct
        ContractEntity ct1_3_1 = new ContractEntity(65.0, 2, frp1_1.getUnit(), 10.0, cl1, cl2, frp1_1, sp3_1);
        em.persist(ct1_3_1);
        frp1_1.getContracts().add(ct1_3_1);
        sp3_1.getContractList().add(ct1_3_1);
        ContractEntity ct1_3_2 = new ContractEntity(50.0, 3, frp1_2.getUnit(), 10.0, cl3, cl4, frp1_2, sp3_2);
        em.persist(ct1_3_2);
        frp1_2.getContracts().add(ct1_3_2);
        sp3_2.getContractList().add(ct1_3_2);
        ContractEntity ct1_3_3 = new ContractEntity(230.0, 2, frp1_3.getUnit(), 10.0, cl5, cl6, frp1_3, sp3_3);
        em.persist(ct1_3_3);
        frp1_3.getContracts().add(ct1_3_3);
        sp3_3.getContractList().add(ct1_3_3);
        ContractEntity ct1_3_4 = new ContractEntity(55.0, 1, frp1_4.getUnit(), 10.0, cl7, cl8, frp1_4, sp3_4);
        em.persist(ct1_3_4);
        frp1_4.getContracts().add(ct1_3_4);
        sp3_4.getContractList().add(ct1_3_4);
        ContractEntity ct1_3_5 = new ContractEntity(35.0, 2, frp1_5.getUnit(), 10.0, cl9, cl10, frp1_5, sp3_5);
        em.persist(ct1_3_5);
        frp1_5.getContracts().add(ct1_3_5);
        sp3_5.getContractList().add(ct1_3_5);
        em.flush();

        //for f2.factoryRawMaterial
        ContractEntity ct2_1_1_1 = new ContractEntity(100.0, 2, frm2_1.getUnit(), 20.0, cl1, cl2, frm2_1, sp1_1_1);
        em.persist(ct2_1_1_1);
        frm2_1.getContracts().add(ct2_1_1_1);
        sp1_1_1.getContractList().add(ct2_1_1_1);
        ContractEntity ct2_1_2_1 = new ContractEntity(150.0, 2, frm2_2.getUnit(), 100.0, cl5, cl6, frm2_2, sp1_2_1);
        em.persist(ct2_1_2_1);
        frm2_2.getContracts().add(ct2_1_2_1);
        sp1_2_1.getContractList().add(ct2_1_2_1);
        ContractEntity ct2_1_3_1 = new ContractEntity(100.0, 3, frm2_3.getUnit(), 10.0, cl9, cl10, frm2_3, sp1_3_1);
        em.persist(ct2_1_3_1);
        frm2_3.getContracts().add(ct2_1_3_1);
        sp1_3_1.getContractList().add(ct2_1_3_1);
        ContractEntity ct2_1_4_1 = new ContractEntity(300.0, 2, frm2_4.getUnit(), 5.0, cl3, cl4, frm2_4, sp1_4_1);
        em.persist(ct2_1_4_1);
        frm2_4.getContracts().add(ct2_1_4_1);
        sp1_4_1.getContractList().add(ct2_1_4_1);
        ContractEntity ct2_1_5_1 = new ContractEntity(100.0, 2, frm2_5.getUnit(), 5.0, cl7, cl8, frm2_5, sp1_5_1);
        em.persist(ct2_1_5_1);
        frm2_5.getContracts().add(ct2_1_5_1);
        sp1_5_1.getContractList().add(ct2_1_5_1);
        em.flush();
        //for f2.factoryRetailProductContractEntity ct1_3_1 = new ContractEntity(65.0, 2, frp1_1.getUnit(), 10.0, cl1, cl2, frp1_1, sp3_1);
        ContractEntity ct2_3_1 = new ContractEntity(65.0, 2, frp2_1.getUnit(), 10.0, cl1, cl2, frp2_1, sp3_1);
        em.persist(ct2_3_1);
        frp2_1.getContracts().add(ct2_3_1);
        sp3_1.getContractList().add(ct2_3_1);
        ContractEntity ct2_3_2 = new ContractEntity(50.0, 3, frp2_2.getUnit(), 10.0, cl3, cl4, frp2_2, sp3_2);
        em.persist(ct2_3_2);
        frp2_2.getContracts().add(ct2_3_2);
        sp3_2.getContractList().add(ct2_3_2);
        em.flush();

        //Sales Forecast
        //Calendars for Sales Forecast
        Calendar c1 = Calendar.getInstance();
        c1.set(2014, 10, 2);
        Calendar c2 = Calendar.getInstance();
        c2.set(2014, 10, 2);
        Calendar c3 = Calendar.getInstance();
        c3.set(2014, 9, 2);
        Calendar c4 = Calendar.getInstance();
        c4.set(2014, 9, 2);
        //sf1_1
        SalesForecastEntity sf1_1 = new SalesForecastEntity(s1, c1);
        em.persist(sf1_1);
        //sf1_1.FactoryProductAmount
        FactoryProductAmountEntity fpa1_1_2_1 = new FactoryProductAmountEntity(fp1_1.getUnit(), 50.0, fp1_1);
        em.persist(fpa1_1_2_1);
        sf1_1.getFactoryProductList().add(fpa1_1_2_1);
        FactoryProductAmountEntity fpa1_1_2_2 = new FactoryProductAmountEntity(fp1_2.getUnit(), 60.0, fp1_2);
        em.persist(fpa1_1_2_2);
        sf1_1.getFactoryProductList().add(fpa1_1_2_2);
        FactoryProductAmountEntity fpa1_1_2_3 = new FactoryProductAmountEntity(fp1_3.getUnit(), 50.0, fp1_3);
        em.persist(fpa1_1_2_3);
        sf1_1.getFactoryProductList().add(fpa1_1_2_3);
        FactoryProductAmountEntity fpa1_1_2_4 = new FactoryProductAmountEntity(fp1_4.getUnit(), 70.0, fp1_4);
        em.persist(fpa1_1_2_4);
        sf1_1.getFactoryProductList().add(fpa1_1_2_4);
        FactoryProductAmountEntity fpa1_1_2_5 = new FactoryProductAmountEntity(fp1_5.getUnit(), 50.0, fp1_5);
        em.persist(fpa1_1_2_5);
        sf1_1.getFactoryProductList().add(fpa1_1_2_5);
        em.flush();
        //sf1_1.FactoryRetailProductAmount
        FactoryRetailProductAmountEntity frpa1_1_3_1 = new FactoryRetailProductAmountEntity(frp1_1.getUnit(), 200.0, frp1_1);
        em.persist(frpa1_1_3_1);
        sf1_1.getFactoryRetailProductList().add(frpa1_1_3_1);
        FactoryRetailProductAmountEntity frpa1_1_3_2 = new FactoryRetailProductAmountEntity(frp1_2.getUnit(), 300.0, frp1_2);
        em.persist(frpa1_1_3_2);
        sf1_1.getFactoryRetailProductList().add(frpa1_1_3_2);
        FactoryRetailProductAmountEntity frpa1_1_3_3 = new FactoryRetailProductAmountEntity(frp1_3.getUnit(), 100.0, frp1_3);
        em.persist(frpa1_1_3_3);
        sf1_1.getFactoryRetailProductList().add(frpa1_1_3_3);
        FactoryRetailProductAmountEntity frpa1_1_3_4 = new FactoryRetailProductAmountEntity(frp1_4.getUnit(), 200.0, frp1_4);
        em.persist(frpa1_1_3_4);
        sf1_1.getFactoryRetailProductList().add(frpa1_1_3_4);
        FactoryRetailProductAmountEntity frpa1_1_3_5 = new FactoryRetailProductAmountEntity(frp1_5.getUnit(), 100.0, frp1_5);
        em.persist(frpa1_1_3_5);
        sf1_1.getFactoryRetailProductList().add(frpa1_1_3_5);
        em.flush();
        
        //sf1_2
        SalesForecastEntity sf1_2 = new SalesForecastEntity(s2, c1);
        em.persist(sf1_2);
        //sf1_2.FactoryProductAmount
        FactoryProductAmountEntity fpa1_2_2_1 = new FactoryProductAmountEntity(fp1_1.getUnit(), 50.0, fp1_1);
        em.persist(fpa1_2_2_1);
        sf1_2.getFactoryProductList().add(fpa1_2_2_1);
        FactoryProductAmountEntity fpa1_2_2_2 = new FactoryProductAmountEntity(fp1_2.getUnit(), 60.0, fp1_2);
        em.persist(fpa1_2_2_2);
        sf1_2.getFactoryProductList().add(fpa1_2_2_2);
        FactoryProductAmountEntity fpa1_2_2_3 = new FactoryProductAmountEntity(fp1_3.getUnit(), 50.0, fp1_3);
        em.persist(fpa1_2_2_3);
        sf1_2.getFactoryProductList().add(fpa1_2_2_3);
        FactoryProductAmountEntity fpa1_2_2_4 = new FactoryProductAmountEntity(fp1_4.getUnit(), 70.0, fp1_4);
        em.persist(fpa1_2_2_4);
        sf1_2.getFactoryProductList().add(fpa1_2_2_4);
        FactoryProductAmountEntity fpa1_2_2_5 = new FactoryProductAmountEntity(fp1_5.getUnit(), 50.0, fp1_5);
        em.persist(fpa1_2_2_5);
        sf1_2.getFactoryProductList().add(fpa1_2_2_5);
        em.flush();
        //sf1_2.FactoryRetailProductAmount
        FactoryRetailProductAmountEntity frpa1_2_3_1 = new FactoryRetailProductAmountEntity(frp1_1.getUnit(), 200.0, frp1_1);
        em.persist(frpa1_2_3_1);
        sf1_2.getFactoryRetailProductList().add(frpa1_2_3_1);
        FactoryRetailProductAmountEntity frpa1_2_3_2 = new FactoryRetailProductAmountEntity(frp1_2.getUnit(), 300.0, frp1_2);
        em.persist(frpa1_2_3_2);
        sf1_2.getFactoryRetailProductList().add(frpa1_2_3_2);
        FactoryRetailProductAmountEntity frpa1_2_3_3 = new FactoryRetailProductAmountEntity(frp1_3.getUnit(), 100.0, frp1_3);
        em.persist(frpa1_2_3_3);
        sf1_2.getFactoryRetailProductList().add(frpa1_2_3_3);
        FactoryRetailProductAmountEntity frpa1_2_3_4 = new FactoryRetailProductAmountEntity(frp1_4.getUnit(), 200.0, frp1_4);
        em.persist(frpa1_2_3_4);
        sf1_2.getFactoryRetailProductList().add(frpa1_2_3_4);
        FactoryRetailProductAmountEntity frpa1_2_3_5 = new FactoryRetailProductAmountEntity(frp1_5.getUnit(), 100.0, frp1_5);
        em.persist(frpa1_2_3_5);
        sf1_2.getFactoryRetailProductList().add(frpa1_2_3_5);
        em.flush();
        
        //sf2_1
        SalesForecastEntity sf2_1 = new SalesForecastEntity(s1, c1);
        em.persist(sf2_1);
        //sf2_1.FactoryProductAmount
        FactoryProductAmountEntity fpa2_1_2_1 = new FactoryProductAmountEntity(fp1_1.getUnit(), 90.0, fp1_1);
        em.persist(fpa2_1_2_1);
        sf2_1.getFactoryProductList().add(fpa2_1_2_1);
        FactoryProductAmountEntity fpa2_1_2_2 = new FactoryProductAmountEntity(fp1_2.getUnit(), 70.0, fp1_2);
        em.persist(fpa2_1_2_2);
        sf2_1.getFactoryProductList().add(fpa2_1_2_2);
        em.flush();
        //sf2_1.FactoryRetailProductAmount
        FactoryRetailProductAmountEntity frpa2_1_3_1 = new FactoryRetailProductAmountEntity(frp1_1.getUnit(), 500.0, frp1_1);
        em.persist(frpa2_1_3_1);
        sf2_1.getFactoryRetailProductList().add(frpa2_1_3_1);
        FactoryRetailProductAmountEntity frpa2_1_3_2 = new FactoryRetailProductAmountEntity(frp1_2.getUnit(), 600.0, frp1_2);
        em.persist(frpa2_1_3_2);
        sf2_1.getFactoryRetailProductList().add(frpa2_1_3_2);
        em.flush();
        
        //sf2_2
        SalesForecastEntity sf2_2 = new SalesForecastEntity(s2, c1);
        em.persist(sf2_2);
        //sf2_2.FactoryProductAmount
        FactoryProductAmountEntity fpa2_2_2_1 = new FactoryProductAmountEntity(fp1_1.getUnit(), 60.0, fp1_1);
        em.persist(fpa2_2_2_1);
        sf2_2.getFactoryProductList().add(fpa2_2_2_1);
        FactoryProductAmountEntity fpa2_2_2_2 = new FactoryProductAmountEntity(fp1_2.getUnit(), 70.0, fp1_2);
        em.persist(fpa2_2_2_2);
        sf2_2.getFactoryProductList().add(fpa2_2_2_2);
        em.flush();
        //sf2_2.FactoryRetailProductAmount
        FactoryRetailProductAmountEntity frpa2_2_3_1 = new FactoryRetailProductAmountEntity(frp1_1.getUnit(), 600.0, frp1_1);
        em.persist(frpa2_2_3_1);
        sf2_2.getFactoryRetailProductList().add(frpa2_2_3_1);
        FactoryRetailProductAmountEntity frpa2_2_3_2 = new FactoryRetailProductAmountEntity(frp1_2.getUnit(), 700.0, frp1_2);
        em.persist(frpa2_2_3_2);
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
        OutboundMovementEntity om1_3_1 = new OutboundMovementEntity();
        om1_3_1.recordFactoryRetailProductOutboundMovement(fbsp1_3_1, s2, 100.0, com2);
        em.persist(om1_3_1);
        em.flush();
        //for f2
        OutboundMovementEntity om2_2_1 = new OutboundMovementEntity();
        om2_2_1.recordFactoryProductOutboundMovement(fbsp2_2_1, s2, 30.0, com1);
        em.persist(om2_2_1);
        OutboundMovementEntity om2_3_1 = new OutboundMovementEntity();
        om2_3_1.recordFactoryRetailProductOutboundMovement(fbsp1_3_1, s1, 60.0, com3);
        em.persist(om2_3_1);
        em.flush();
        
        //Raw material In-Factory Use Movement
        //for f1
        RawMaterialInFactoryUseMovementEntity rmifu1_1 = new RawMaterialInFactoryUseMovementEntity();
        em.persist(rmifu1_1);
        rmifu1_1.recordRawMaterialInFactoryUseMovement(fbsp1_1_1, 30.0, com1);
        RawMaterialInFactoryUseMovementEntity rmifu1_2 = new RawMaterialInFactoryUseMovementEntity();
        em.persist(rmifu1_2);
        rmifu1_2.recordRawMaterialInFactoryUseMovement(fbsp1_1_2, 20.0, com2);
        em.flush();
        //for f2
        RawMaterialInFactoryUseMovementEntity rmifu2_1 = new RawMaterialInFactoryUseMovementEntity();
        em.persist(rmifu2_1);
        rmifu2_1.recordRawMaterialInFactoryUseMovement(fbsp2_1_1, 20.0, com1);
        em.flush();
    }
}
