/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

import Entity.CommonInfrastructure.HQUserEntity;
import Entity.CommonInfrastructure.IdNumberEntity;
import Entity.CommonInfrastructure.UserEntity;
import Entity.Factory.BOMEntity;
import Entity.Factory.FactoryEntity;
import Entity.Factory.FactoryProductAmountEntity;
import Entity.Factory.FactoryProductEntity;
import Entity.Factory.FactoryRawMaterialAmountEntity;
import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.FactoryRetailProductAmountEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.MRP.IntegratedSalesForecastEntity;
import Entity.Factory.MRP.SalesForecastEntity;
import Entity.Factory.ProductEntity;
import Entity.Factory.RawMaterialEntity;
import Entity.Factory.RetailProductEntity;
import Entity.Store.StoreEntity;
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
 * @author hangsun
 */
@Singleton
@LocalBean
@Startup
public class Test {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void init() {
        createDatabase();
    }

    public void createDatabase() {
        //Set up idNumberEntity
        IdNumberEntity id = new IdNumberEntity();
        id.setId_F(1000000L);
        id.setId_H(1000001L);
        id.setId_S(1000000L);
        em.persist(id);

        //Set up SuperUser
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String bir = "02-09-1993";
        Calendar birthday = Calendar.getInstance();
        try {
            birthday.setTime(df.parse(bir));
        } catch (ParseException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        UserEntity u = new HQUserEntity("H", "1000001", 0,
                "Zheng", null, "Yuan", "Global Manager", birthday, "Female", null, null, null, null, false, 1000000L);
        u.setPwd("123");
        em.persist(u);
        em.flush();

        //Set up ProductEntity
        ProductEntity p1 = new ProductEntity();
        ProductEntity p2 = new ProductEntity();
        ProductEntity p3 = new ProductEntity();
        ProductEntity p4 = new ProductEntity();
        ProductEntity p5 = new ProductEntity();
        p1.setName("p1");
        p2.setName("p2");
        p3.setName("p3");
        p4.setName("p4");
        p5.setName("p5");
        p1.setDescription("a");
        p2.setDescription("b");
        p3.setDescription("c");
        p4.setDescription("d");
        p5.setDescription("e");
        p1.setPrice(5D);
        p2.setPrice(6D);
        p3.setPrice(3.4);
        p4.setPrice(1D);
        p5.setPrice(3243.1);
        p1.setUnit("kg");
        p2.setUnit("kg");
        p3.setUnit("kg");
        p4.setUnit("t");
        p5.setUnit("mg");
        p1.setDeleteFlag(Boolean.FALSE);
        p2.setDeleteFlag(Boolean.FALSE);
        p3.setDeleteFlag(Boolean.FALSE);
        p4.setDeleteFlag(Boolean.FALSE);
        p5.setDeleteFlag(Boolean.FALSE);
        em.persist(p1);
        em.flush();
        em.persist(p2);
        em.flush();
        em.persist(p3);
        em.flush();
        em.persist(p4);
        em.flush();
        em.persist(p5);
        em.flush();

        //Set up factory entity
        FactoryEntity f1 = new FactoryEntity();
        FactoryEntity f2 = new FactoryEntity();
        f1.setCountry("Singapore");
        f2.setCountry("China");
        f1.setAddress("Sky");
        f2.setAddress("hell");
        f1.setContact("1234567");
        f2.setContact("7654321");
        f1.setManager("Meng Dan");
        f2.setManager("MD");
        f1.setDeleteFlag(Boolean.FALSE);
        f2.setDeleteFlag(Boolean.FALSE);
        em.persist(f1);
        em.flush();
        em.persist(f2);
        em.flush();

        //set up factory product entity
        FactoryProductEntity fp1 = new FactoryProductEntity();
        FactoryProductEntity fp2 = new FactoryProductEntity();
        FactoryProductEntity fp3 = new FactoryProductEntity();
        FactoryProductEntity fp4 = new FactoryProductEntity();
        FactoryProductEntity fp5 = new FactoryProductEntity();
        FactoryProductEntity fp6 = new FactoryProductEntity();
        FactoryProductEntity fp7 = new FactoryProductEntity();
        fp1.setFactory(f1);
        fp2.setFactory(f1);
        fp3.setFactory(f1);
        fp4.setFactory(f1);
        fp5.setFactory(f1);
        fp6.setFactory(f2);
        fp7.setFactory(f2);

        fp1.setProduct(p1);
        fp2.setProduct(p2);
        fp3.setProduct(p3);
        fp4.setProduct(p4);
        fp5.setProduct(p5);
        fp6.setProduct(p1);
        fp7.setProduct(p2);
        em.persist(fp1);
        em.flush();
        em.persist(fp2);
        em.flush();
        em.persist(fp3);
        em.flush();
        em.persist(fp4);
        em.flush();
        em.persist(fp5);
        em.flush();
        em.persist(fp6);
        em.flush();
        em.persist(fp7);
        em.flush();

        //link factory product entity with product entity
        List<FactoryProductEntity> fpl1 = new ArrayList();
        List<FactoryProductEntity> fpl2 = new ArrayList();
        fpl1.add(fp1);
        fpl1.add(fp2);
        fpl1.add(fp3);
        fpl1.add(fp4);
        fpl1.add(fp5);
        fpl2.add(fp6);
        fpl2.add(fp7);
        f1.setFactoryProducts(fpl1);
        f2.setFactoryProducts(fpl2);
        em.persist(f1);
        em.flush();
        em.persist(f2);
        em.flush();

        // Set up calendar
        Calendar c1 = Calendar.getInstance();
        c1.set(2014, 10, 2);
        Calendar c2 = Calendar.getInstance();
        c2.set(2014, 10, 2);
        Calendar c3 = Calendar.getInstance();
        c3.set(2014, 9, 2);
        Calendar c4 = Calendar.getInstance();
        c4.set(2014, 9, 2);

        //set up raw material entity
        RawMaterialEntity rm1 = new RawMaterialEntity();
        RawMaterialEntity rm2 = new RawMaterialEntity();
        RawMaterialEntity rm3 = new RawMaterialEntity();
        RawMaterialEntity rm4 = new RawMaterialEntity();
        RawMaterialEntity rm5 = new RawMaterialEntity();

        rm1.setMaterialName("board");
        rm1.setDescription("hehe");
        rm1.setUnit("piece");
        rm1.setIsDeleted(Boolean.FALSE);
        rm2.setMaterialName("metal");
        rm2.setDescription("hehe");
        rm2.setUnit("piece");
        rm2.setIsDeleted(Boolean.FALSE);
        rm3.setMaterialName("nail");
        rm3.setDescription("hehe");
        rm3.setUnit("box");
        rm3.setIsDeleted(Boolean.FALSE);
        rm4.setMaterialName("nut");
        rm4.setDescription("hehe");
        rm4.setUnit("box");
        rm4.setIsDeleted(Boolean.FALSE);
        rm5.setMaterialName("stick");
        rm5.setDescription("hehe");
        rm5.setUnit("box");
        rm5.setIsDeleted(Boolean.FALSE);

        em.persist(rm1);
        em.flush();
        em.persist(rm2);
        em.flush();
        em.persist(rm3);
        em.flush();
        em.persist(rm4);
        em.flush();
        em.persist(rm5);
        em.flush();

        //set up factory raw material entity and link with raw material entity
        FactoryRawMaterialEntity frm1 = new FactoryRawMaterialEntity();
        FactoryRawMaterialEntity frm2 = new FactoryRawMaterialEntity();
        FactoryRawMaterialEntity frm3 = new FactoryRawMaterialEntity();
        FactoryRawMaterialEntity frm4 = new FactoryRawMaterialEntity();
        FactoryRawMaterialEntity frm5 = new FactoryRawMaterialEntity();
        FactoryRawMaterialEntity frm6 = new FactoryRawMaterialEntity();
        FactoryRawMaterialEntity frm7 = new FactoryRawMaterialEntity();

        frm1.setFactory(f1);
        frm2.setFactory(f1);
        frm3.setFactory(f1);
        frm4.setFactory(f1);
        frm5.setFactory(f1);
        frm6.setFactory(f2);
        frm7.setFactory(f2);

        frm1.setRawMaterial(rm1);
        frm2.setRawMaterial(rm2);
        frm3.setRawMaterial(rm3);
        frm4.setRawMaterial(rm4);
        frm5.setRawMaterial(rm5);
        frm6.setRawMaterial(rm1);
        frm7.setRawMaterial(rm2);

        em.persist(frm1);
        em.flush();
        em.persist(frm2);
        em.flush();
        em.persist(frm3);
        em.flush();
        em.persist(frm4);
        em.flush();
        em.persist(frm5);
        em.flush();
        em.persist(frm6);
        em.flush();
        em.persist(frm7);
        em.flush();

        //set up factory raa material amount entity
        FactoryRawMaterialAmountEntity frma1 = new FactoryRawMaterialAmountEntity();
        FactoryRawMaterialAmountEntity frma2 = new FactoryRawMaterialAmountEntity();
        FactoryRawMaterialAmountEntity frma3 = new FactoryRawMaterialAmountEntity();
        FactoryRawMaterialAmountEntity frma4 = new FactoryRawMaterialAmountEntity();
        FactoryRawMaterialAmountEntity frma5 = new FactoryRawMaterialAmountEntity();
        FactoryRawMaterialAmountEntity frma6 = new FactoryRawMaterialAmountEntity();
        FactoryRawMaterialAmountEntity frma7 = new FactoryRawMaterialAmountEntity();
        FactoryRawMaterialAmountEntity frma8 = new FactoryRawMaterialAmountEntity();
        FactoryRawMaterialAmountEntity frma9 = new FactoryRawMaterialAmountEntity();
        FactoryRawMaterialAmountEntity frma10 = new FactoryRawMaterialAmountEntity();

        frma1.setFactoryRawMaterial(frm1);
        frma1.setAmount(4D);
        frma1.setUnit("piece");
        frma2.setFactoryRawMaterial(frm2);
        frma2.setAmount(4D);
        frma2.setUnit("piece");
        frma3.setFactoryRawMaterial(frm3);
        frma3.setAmount(4D);
        frma3.setUnit("box");
        frma4.setFactoryRawMaterial(frm4);
        frma4.setAmount(4D);
        frma4.setUnit("box");
        frma5.setFactoryRawMaterial(frm5);
        frma5.setAmount(4D);
        frma5.setUnit("box");
        frma6.setFactoryRawMaterial(frm1);
        frma6.setAmount(4D);
        frma6.setUnit("piece");
        frma7.setFactoryRawMaterial(frm2);
        frma7.setAmount(4D);
        frma7.setUnit("piece");
        frma8.setFactoryRawMaterial(frm3);
        frma8.setAmount(4D);
        frma8.setUnit("box");
        frma9.setFactoryRawMaterial(frm4);
        frma9.setAmount(4D);
        frma9.setUnit("box");
        frma10.setFactoryRawMaterial(frm5);
        frma10.setAmount(4D);
        frma10.setUnit("box");

        em.persist(frma1);
        em.flush();
        em.persist(frma2);
        em.flush();
        em.persist(frma3);
        em.flush();
        em.persist(frma4);
        em.flush();
        em.persist(frma5);
        em.flush();
        em.persist(frma6);
        em.flush();
        em.persist(frma7);
        em.flush();
        em.persist(frma8);
        em.flush();
        em.persist(frma9);
        em.flush();
        em.persist(frma10);
        em.flush();

        //create list factory raw material amount
        List<FactoryRawMaterialAmountEntity> frmal1 = new ArrayList();
        List<FactoryRawMaterialAmountEntity> frmal2 = new ArrayList();
        List<FactoryRawMaterialAmountEntity> frmal3 = new ArrayList();
        List<FactoryRawMaterialAmountEntity> frmal4 = new ArrayList();
        List<FactoryRawMaterialAmountEntity> frmal5 = new ArrayList();
        frmal1.add(frma1);
        frmal1.add(frma2);
        frmal2.add(frma3);
        frmal2.add(frma4);
        frmal3.add(frma5);
        frmal3.add(frma6);
        frmal4.add(frma7);
        frmal4.add(frma8);
        frmal5.add(frma9);
        frmal5.add(frma10);

        /*      
        
        
         我有一只大猫猫 它从来都不喵
        
        
        
         */
        //BOM
        BOMEntity bom1 = new BOMEntity();
        BOMEntity bom2 = new BOMEntity();
        BOMEntity bom3 = new BOMEntity();
        BOMEntity bom4 = new BOMEntity();
        BOMEntity bom5 = new BOMEntity();
        BOMEntity bom6 = new BOMEntity();
        BOMEntity bom7 = new BOMEntity();
        BOMEntity bom8 = new BOMEntity();
        BOMEntity bom9 = new BOMEntity();

        bom1.setAmount(10D);
        bom1.setProduct(p1);
        bom1.setRawMaterial(rm1);
        bom1.setUnit("piece");
        bom2.setAmount(20D);
        bom2.setProduct(p1);
        bom2.setRawMaterial(rm2);
        bom1.setUnit("piece");
        bom3.setAmount(30D);
        bom3.setProduct(p2);
        bom3.setRawMaterial(rm3);
        bom1.setUnit("box");
        bom4.setAmount(40D);
        bom4.setProduct(p2);
        bom4.setRawMaterial(rm4);
        bom1.setUnit("box");
        bom5.setAmount(50D);
        bom5.setProduct(p3);
        bom5.setRawMaterial(rm5);
        bom1.setUnit("box");
        bom6.setAmount(60D);
        bom6.setProduct(p3);
        bom6.setRawMaterial(rm1);
        bom1.setUnit("piece");
        bom7.setAmount(70D);
        bom7.setProduct(p4);
        bom7.setRawMaterial(rm2);
        bom1.setUnit("piece");
        bom8.setAmount(80D);
        bom8.setProduct(p4);
        bom8.setRawMaterial(rm3);
        bom1.setUnit("box");
        bom9.setAmount(90D);
        bom9.setProduct(p5);
        bom9.setRawMaterial(rm4);
        bom1.setUnit("box");

        em.persist(bom1);
        em.flush();
        em.persist(bom2);
        em.flush();
        em.persist(bom3);
        em.flush();
        em.persist(bom4);
        em.flush();
        em.persist(bom5);
        em.flush();
        em.persist(bom6);
        em.flush();
        em.persist(bom7);
        em.flush();
        em.persist(bom8);
        em.flush();
        em.persist(bom9);
        em.flush();

        // Factory Product 
        FactoryProductAmountEntity fpa1 = new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa2 = new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa3 = new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa4 = new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa5 = new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa6 = new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa7 = new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa8 = new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa9 = new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa10 = new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa11 = new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa12 = new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa13 = new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa14 = new FactoryProductAmountEntity();

        fpa1.setAmount(1000D);
        fpa1.setFactoryProduct(fp1);
        fpa1.setUnit("unit");
        fpa2.setAmount(2000D);
        fpa2.setFactoryProduct(fp2);
        fpa2.setUnit("unit");
        fpa3.setAmount(3000D);
        fpa3.setFactoryProduct(fp3);
        fpa3.setUnit("unit");
        fpa4.setAmount(4000D);
        fpa4.setFactoryProduct(fp4);
        fpa4.setUnit("unit");
        fpa5.setAmount(5000D);
        fpa5.setFactoryProduct(fp5);
        fpa5.setUnit("unit");
        fpa6.setAmount(6000D);
        fpa6.setFactoryProduct(fp6);
        fpa6.setUnit("unit");
        fpa7.setAmount(7000D);
        fpa7.setFactoryProduct(fp7);
        fpa7.setUnit("unit");
        fpa8.setAmount(8000D);
        fpa8.setFactoryProduct(fp1);
        fpa8.setUnit("unit");
        fpa9.setAmount(9000D);
        fpa9.setFactoryProduct(fp2);
        fpa9.setUnit("unit");
        fpa10.setAmount(4000D);
        fpa10.setFactoryProduct(fp3);
        fpa10.setUnit("unit");
        fpa11.setAmount(5000D);
        fpa11.setFactoryProduct(fp4);
        fpa11.setUnit("unit");
        fpa12.setAmount(6000D);
        fpa12.setFactoryProduct(fp5);
        fpa12.setUnit("unit");
        fpa13.setAmount(7000D);
        fpa13.setFactoryProduct(fp6);
        fpa13.setUnit("unit");
        fpa14.setAmount(8000D);
        fpa14.setFactoryProduct(fp7);
        fpa14.setUnit("unit");

        em.persist(fpa1);
        em.flush();
        em.persist(fpa2);
        em.flush();
        em.persist(fpa3);
        em.flush();
        em.persist(fpa4);
        em.flush();
        em.persist(fpa5);
        em.flush();
        em.persist(fpa6);
        em.flush();
        em.persist(fpa7);
        em.flush();
        em.persist(fpa8);
        em.flush();
        em.persist(fpa9);
        em.flush();
        em.persist(fpa10);
        em.flush();
        em.persist(fpa11);
        em.flush();
        em.persist(fpa12);
        em.flush();
        em.persist(fpa13);
        em.flush();
        em.persist(fpa14);
        em.flush();

        // Integrated Sales Forecast     /* NOT FINISHED */
        IntegratedSalesForecastEntity isf1 = new IntegratedSalesForecastEntity();
        IntegratedSalesForecastEntity isf2 = new IntegratedSalesForecastEntity();
        IntegratedSalesForecastEntity isf3 = new IntegratedSalesForecastEntity();

        isf1.setAmount(5000D);
        isf1.setFactory(f1);
        isf1.setFactoryProduct(fp1);
        isf1.setTargetPeriod(c1);
        
        isf2.setAmount(5500D);
        isf2.setFactory(f1);
        isf2.setFactoryProduct(fp2);
        isf2.setTargetPeriod(c1);
        
        isf3.setAmount(6000D);
        isf3.setFactory(f2);
        isf3.setTargetPeriod(c2);

        ///isf 4-5 will use factory retail product   so they will set factory retail product after the factory retail product entity is set up   line 1290 

        em.persist(isf1);
        em.flush();
        em.persist(isf2);
        em.flush();
        em.persist(isf3);
        em.flush();

        //Retail Product Entity          /* NOT FINISHED */
        RetailProductEntity rpe1 = new RetailProductEntity();
        
        rpe1.setDeleteFlag(false);
        rpe1.setDescription("I dont know");
        rpe1.setName("A");
        rpe1.setUnit("unit");
      
        em.persist(rpe1);
        em.flush();
        

        //Sales Forecast
        SalesForecastEntity sfe1 = new SalesForecastEntity();
        SalesForecastEntity sfe2 = new SalesForecastEntity();
        SalesForecastEntity sfe3 = new SalesForecastEntity();
        SalesForecastEntity sfe4 = new SalesForecastEntity();
        SalesForecastEntity sfe5 = new SalesForecastEntity();

        //For factory 1
        List<FactoryProductAmountEntity> listfpe1 = new ArrayList<>();
        listfpe1.add(fpa1);
        listfpe1.add(fpa2);
        List<FactoryProductAmountEntity> listfpe2 = new ArrayList<>();
        listfpe2.add(fpa4);
        listfpe2.add(fpa5);
        List<FactoryProductAmountEntity> listfpe3 = new ArrayList<>();
        listfpe3.add(fpa8);
        listfpe3.add(fpa9);
        listfpe3.add(fpa10);
        listfpe3.add(fpa11);
        
        
        // For factory 2
        List<FactoryProductAmountEntity> listfpe4 = new ArrayList<>();
        listfpe4.add(fpa6);
        listfpe4.add(fpa7);
        List<FactoryProductAmountEntity> listfpe5 = new ArrayList<>();
        listfpe5.add(fpa13);
        listfpe5.add(fpa14);

        //Factory retail product entity
        FactoryRetailProductEntity frpe1 = new FactoryRetailProductEntity();
        frpe1.setBlockedInventory(0d);
        frpe1.setFactory(f1);
        frpe1.setName("RetailA");
        frpe1.setUnit("unit");
        frpe1.setRetailProduct(rpe1);
        FactoryRetailProductEntity frpe2 = new FactoryRetailProductEntity();
        frpe2.setBlockedInventory(0d);
        frpe2.setFactory(f2);
        frpe2.setName("RetailB");
        frpe2.setUnit("unit");
        frpe2.setRetailProduct(rpe1);

        em.persist(frpe1);
        em.flush();
        em.persist(frpe2);
        em.flush();
 
        isf3.setFactoryRetailProduct(frpe2);
        em.persist(isf2);
        em.flush();
        em.persist(isf3);
        em.flush();
        
        FactoryRetailProductAmountEntity rpa1 = new FactoryRetailProductAmountEntity();
        rpa1.setAmount(100D);
        rpa1.setFactoryRetailProduct(frpe1);
        FactoryRetailProductAmountEntity rpa2 = new FactoryRetailProductAmountEntity();
        rpa2.setAmount(200D);
        rpa2.setFactoryRetailProduct(frpe2);

        em.persist(rpa1);
        em.flush();
        em.persist(rpa2);
        em.flush();

        List<FactoryRetailProductAmountEntity> listrpe1 = new ArrayList<>();
        listrpe1.add(rpa1);
        List<FactoryRetailProductAmountEntity> listrpe2 = new ArrayList<>();
        listrpe1.add(rpa2);

        StoreEntity store1 = new StoreEntity();
        store1.setAddress("Singapore");
        store1.setContact("88888888");
        store1.setCountry("Singapore");
        store1.setManager("Zhen Yuan");
        StoreEntity store2 = new StoreEntity();

        em.persist(store1);
        em.flush();
        em.persist(store2);
        
        sfe1.setFactoryProductList(listfpe1);
        sfe1.setFactoryRetailProductList(listrpe1);
        sfe1.setStatus("Unconfirmed");
        sfe1.setStore(store1);
        sfe1.setTargetPeriod(c1);
        sfe2.setFactoryProductList(listfpe2);
        sfe2.setFactoryRetailProductList(listrpe2);
        sfe2.setStatus("Unconfirmed");
        sfe2.setStore(store1);
        sfe2.setTargetPeriod(c1);
        sfe3.setFactoryProductList(listfpe3);
        sfe3.setStatus("Unconfirmed");
        sfe3.setStore(store2);
        sfe3.setTargetPeriod(c1);
        sfe4.setFactoryProductList(listfpe4);
        sfe4.setStatus("Unconfirmed");
        sfe4.setStore(store1);
        sfe4.setTargetPeriod(c1);
        sfe5.setFactoryProductList(listfpe5);
        sfe5.setStatus("Unconfirmed");
        sfe5.setStore(store2);
        sfe5.setTargetPeriod(c1);

        em.persist(sfe1);
        em.flush();
        em.persist(sfe2);
        em.flush();
        em.persist(sfe3);
        em.flush();
        em.persist(sfe4);
        em.flush();
        em.persist(sfe5);
        em.flush();


    }
}
