/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

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
import Entity.Factory.FactoryRawMaterialAmountEntity;
import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.FactoryRetailProductAmountEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.InventoryRecordEntity;
import Entity.Factory.MRP.IntegratedSalesForecastEntity;
import Entity.Factory.MRP.PlannedOrderEntity;
import Entity.Factory.MRP.ProductionPlanEntity;
import Entity.Factory.MRP.SalesForecastEntity;
import Entity.Factory.MRP.SalesOperationPlanEntity;
import Entity.Factory.ProductEntity;
import Entity.Factory.RawMaterialEntity;
import Entity.Factory.RetailProductEntity;
import Entity.Factory.SCM.ContractEntity;
import Entity.Factory.SCM.InFactoryMovementEntity;
import Entity.Factory.SCM.OutboundMovementEntity;
import Entity.Factory.SCM.PurchaseOrderEntity;
import Entity.Factory.SCM.SupplierEntity;
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
import util.security.CryptographicHelper;

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
    
    private CryptographicHelper cryptographicHelper = CryptographicHelper.getInstanceOf();
    @PostConstruct
    public void init(){
        createDatabase();
    }
    
    public void createDatabase(){
        //Set up idNumberEntity
        IdNumberEntity id = new IdNumberEntity();
        id.setId_F(1000001L);
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
                            "Zheng", null, "Yuan", "Global Manager", birthday, "Female", null, null, null, null, 1L, cryptographicHelper.doMD5Hashing("123"), false);
                      
        em.persist(u);
        em.flush();
        
        UserEntity u2 = new FactoryUserEntity("F", "1000001", 1, "Zhang", null,
            "Shiyu", "Factory Manager",  birthday, "Female", 
            "Ms", null, null, null,1L, cryptographicHelper.doMD5Hashing("123"), false);
        
        em.persist(u2);
        em.flush();
        
        //Set up ProductEntity
        
        ProductEntity p1 = new ProductEntity();
        ProductEntity p2 = new ProductEntity();
        ProductEntity p3 = new ProductEntity();
        ProductEntity p4 = new ProductEntity();
        ProductEntity p5 = new ProductEntity();
        ProductEntity p6 = new ProductEntity();
        ProductEntity p7 = new ProductEntity();
        ProductEntity p8 = new ProductEntity();
        ProductEntity p9 = new ProductEntity();
        ProductEntity p10 = new ProductEntity();
        
       
        p1.setName("p1");p2.setName("p2");p3.setName("p3");
        p4.setName("p4");p5.setName("p5");p6.setName("p6");
        p7.setName("p7");p8.setName("p8");p9.setName("p9");
        p10.setName("p10");
        
        p1.setDescription("a");p2.setDescription("b");
        p3.setDescription("c");p4.setDescription("d");
        p5.setDescription("e");p6.setDescription("f");
        p7.setDescription("g");p8.setDescription("h");
        p9.setDescription("i");p10.setDescription("j");
        
        p1.setPrice(5D);p2.setPrice(6D);p3.setPrice(3.4);
        p4.setPrice(1D);p5.setPrice(3243.1);p6.setPrice(90.2);
        p7.setPrice(555.213);p8.setPrice(90.3);p9.setPrice(234.2);
        p10.setPrice(213.213);
        
        p1.setUnit("kg");p2.setUnit("kg");p3.setUnit("kg");p4.setUnit("t");
        p5.setUnit("mg");p6.setUnit("kg");p7.setUnit("kg");p8.setUnit("kg");
        p9.setUnit("m");p10.setUnit("J");
        
        p1.setDeleteFlag(Boolean.FALSE);p2.setDeleteFlag(Boolean.FALSE);
        p3.setDeleteFlag(Boolean.FALSE);p4.setDeleteFlag(Boolean.FALSE);
        p5.setDeleteFlag(Boolean.FALSE);p6.setDeleteFlag(Boolean.FALSE);
        p7.setDeleteFlag(Boolean.FALSE);p8.setDeleteFlag(Boolean.FALSE);
        p9.setDeleteFlag(Boolean.FALSE);p10.setDeleteFlag(Boolean.FALSE);
        
        
       
        em.persist(p1);em.flush();em.persist(p2);em.flush();em.persist(p3);em.flush();em.persist(p4);em.flush();em.persist(p5);em.flush();
        em.persist(p6);em.flush();em.persist(p7);em.flush();em.persist(p8);em.flush();em.persist(p9);em.flush();em.persist(p10);em.flush();
       
        
        //Set up factory entity
        FactoryEntity f1 = new FactoryEntity();
        FactoryEntity f2 = new FactoryEntity();
        FactoryEntity f3 = new FactoryEntity();
        
        f1.setCountry("Singapore"); f2.setCountry("China"); f3.setCountry("United States");
        f1.setAddress("Sky");f2.setAddress("hell");f3.setAddress("heaven");
        f1.setContact("1234567");f2.setContact("7654321");f3.setContact("9876543");
        f1.setManager("Meng Dan");f2.setManager("MD");f3.setManager("ZMD");
        f1.setDeleteFlag(Boolean.FALSE);f2.setDeleteFlag(Boolean.FALSE);f3.setDeleteFlag(Boolean.FALSE);
        
        em.persist(f1);em.flush();em.persist(f2);em.flush();em.persist(f3);em.flush();
        
        //set up factory product entity
        FactoryProductEntity fp1 = new FactoryProductEntity();
        FactoryProductEntity fp2 = new FactoryProductEntity();
        FactoryProductEntity fp3 = new FactoryProductEntity();
        FactoryProductEntity fp4 = new FactoryProductEntity();
        FactoryProductEntity fp5 = new FactoryProductEntity();
        FactoryProductEntity fp6 = new FactoryProductEntity();
        FactoryProductEntity fp7 = new FactoryProductEntity();
        FactoryProductEntity fp8 = new FactoryProductEntity();
        FactoryProductEntity fp9 = new FactoryProductEntity();
        FactoryProductEntity fp10 = new FactoryProductEntity();
        FactoryProductEntity fp11 = new FactoryProductEntity();
        FactoryProductEntity fp12 = new FactoryProductEntity();
        FactoryProductEntity fp13 = new FactoryProductEntity();
        FactoryProductEntity fp14 = new FactoryProductEntity();
        FactoryProductEntity fp15 = new FactoryProductEntity();
        FactoryProductEntity fp16 = new FactoryProductEntity();
        FactoryProductEntity fp17 = new FactoryProductEntity();
        FactoryProductEntity fp18 = new FactoryProductEntity();
        FactoryProductEntity fp19 = new FactoryProductEntity();
        FactoryProductEntity fp20 = new FactoryProductEntity();
        
        fp1.setFactory(f1);fp2.setFactory(f1);fp3.setFactory(f1);fp4.setFactory(f1);fp5.setFactory(f1);fp6.setFactory(f1);
        fp7.setFactory(f2);fp8.setFactory(f2);fp9.setFactory(f2);fp10.setFactory(f2);fp11.setFactory(f2);fp12.setFactory(f2);
        fp13.setFactory(f3);fp14.setFactory(f3);fp15.setFactory(f3);fp16.setFactory(f3);fp17.setFactory(f3);fp18.setFactory(f3);fp19.setFactory(f3);fp20.setFactory(f3);
        
        fp1.setProduct(p1);fp2.setProduct(p2);
        fp3.setProduct(p3);fp4.setProduct(p4);
        fp5.setProduct(p5);fp6.setProduct(p6);
        
        fp7.setProduct(p7);fp8.setProduct(p8);
        fp9.setProduct(p9);fp10.setProduct(p10);
        fp11.setProduct(p1);fp12.setProduct(p2);
        
        fp13.setProduct(p3);fp14.setProduct(p4);
        fp15.setProduct(p5);fp16.setProduct(p6);
        fp17.setProduct(p7);fp18.setProduct(p8);
        fp19.setProduct(p9);fp20.setProduct(p10);
        
        em.persist(fp1);em.flush();em.persist(fp2);em.flush();em.persist(fp3);em.flush();em.persist(fp4);em.flush();
        em.persist(fp5);em.flush();em.persist(fp6);em.flush();em.persist(fp7);em.flush();em.persist(fp8);em.flush();
        em.persist(fp9);em.flush();em.persist(fp10);em.flush();em.persist(fp11);em.flush();em.persist(fp12);em.flush();
        em.persist(fp13);em.flush();em.persist(fp14);em.flush();em.persist(fp15);em.flush();em.persist(fp16);em.flush();
        em.persist(fp17);em.flush();em.persist(fp18);em.flush();em.persist(fp19);em.flush();em.persist(fp20);em.flush();
        
        //link factory product entity with product entity
        List<FactoryProductEntity> fpl1 = new ArrayList();
        List<FactoryProductEntity> fpl2 = new ArrayList();
        List<FactoryProductEntity> fpl3 = new ArrayList();
        
        fpl1.add(fp1);fpl1.add(fp2);fpl1.add(fp3);fpl1.add(fp4);fpl1.add(fp5);fpl1.add(fp6);
        fpl2.add(fp7);fpl2.add(fp8);fpl2.add(fp9);fpl2.add(fp10);fpl2.add(fp11);fpl2.add(fp12);
        fpl3.add(fp13);fpl3.add(fp14);fpl3.add(fp15);fpl3.add(fp16);fpl3.add(fp17);fpl3.add(fp18);fpl3.add(fp19);fpl3.add(fp20);
        
        f1.setFactoryProducts(fpl1);f2.setFactoryProducts(fpl2);f3.setFactoryProducts(fpl3);
        
        em.persist(f1);em.flush();em.persist(f2);em.flush();em.persist(f3);em.flush();
        
        //set up production plan entity
        ProductionPlanEntity pp1 = new ProductionPlanEntity();ProductionPlanEntity pp2 = new ProductionPlanEntity();
        ProductionPlanEntity pp3 = new ProductionPlanEntity();ProductionPlanEntity pp4 = new ProductionPlanEntity();
        ProductionPlanEntity pp5 = new ProductionPlanEntity();ProductionPlanEntity pp6 = new ProductionPlanEntity();
        ProductionPlanEntity pp7 = new ProductionPlanEntity();ProductionPlanEntity pp8 = new ProductionPlanEntity();
        ProductionPlanEntity pp9 = new ProductionPlanEntity();ProductionPlanEntity pp10 = new ProductionPlanEntity();
        ProductionPlanEntity pp11 = new ProductionPlanEntity();ProductionPlanEntity pp12 = new ProductionPlanEntity();
        ProductionPlanEntity pp13 = new ProductionPlanEntity();ProductionPlanEntity pp14 = new ProductionPlanEntity();
        ProductionPlanEntity pp15 = new ProductionPlanEntity();ProductionPlanEntity pp16 = new ProductionPlanEntity();
        ProductionPlanEntity pp17 = new ProductionPlanEntity();ProductionPlanEntity pp18 = new ProductionPlanEntity();
        ProductionPlanEntity pp19 = new ProductionPlanEntity();ProductionPlanEntity pp20 = new ProductionPlanEntity();
        
        pp1.setStatus("unconfirmed");pp2.setStatus("unconfirmed");pp3.setStatus("unconfirmed");pp4.setStatus("unconfirmed");pp5.setStatus("unconfirmed");
        pp6.setStatus("unconfirmed");pp7.setStatus("unconfirmed");pp8.setStatus("unconfirmed");pp9.setStatus("unconfirmed");pp10.setStatus("unconfirmed");
        pp11.setStatus("unconfirmed");pp12.setStatus("unconfirmed");pp13.setStatus("unconfirmed");pp14.setStatus("unconfirmed");pp15.setStatus("unconfirmed");
        pp16.setStatus("unconfirmed");pp17.setStatus("unconfirmed");pp18.setStatus("unconfirmed");pp19.setStatus("unconfirmed");pp20.setStatus("unconfirmed");
        
        pp1.setRemark("haha");pp2.setRemark("haha");pp3.setRemark("haha");pp4.setRemark("haha");pp5.setRemark("haha");
        pp6.setRemark("haha");pp7.setRemark("haha");pp8.setRemark("haha");pp9.setRemark("haha");pp10.setRemark("haha");
        pp11.setRemark("haha");pp12.setRemark("haha");pp13.setRemark("haha");pp14.setRemark("haha");pp15.setRemark("haha");
        pp16.setRemark("haha");pp17.setRemark("haha");pp18.setRemark("haha");pp19.setRemark("haha");pp20.setRemark("haha");
        
        pp1.setQuantity(5D);pp2.setQuantity(5D);pp3.setQuantity(5D);pp4.setQuantity(5D);pp5.setQuantity(5D);
        pp6.setQuantity(5D);pp7.setQuantity(5D);pp8.setQuantity(5D);pp9.setQuantity(5D);pp10.setQuantity(5D);
        pp11.setQuantity(5D);pp12.setQuantity(5D);pp13.setQuantity(5D);pp14.setQuantity(5D);pp15.setQuantity(5D);
        pp16.setQuantity(5D);pp17.setQuantity(5D);pp18.setQuantity(5D);pp19.setQuantity(5D);pp20.setQuantity(5D);
        
        Calendar c1=Calendar.getInstance();  c1.set(2014, 8, 25);
        Calendar c2=Calendar.getInstance();  c2.set(2014, 7, 31);
        Calendar c3=Calendar.getInstance();  c3.set(2014, 6, 23);
        Calendar c4=Calendar.getInstance();  c4.set(2014, 5, 15);
        Calendar c5=Calendar.getInstance();  c5.set(2014, 4, 19);
        Calendar c6=Calendar.getInstance();  c6.set(2014, 3, 20);
        Calendar c7=Calendar.getInstance();  c7.set(2014, 2, 11);
        Calendar c8=Calendar.getInstance();  c8.set(2014, 1, 22);
        Calendar c9=Calendar.getInstance();  c9.set(2014, 6, 6);
        Calendar c10=Calendar.getInstance();  c10.set(2014, 2, 12);
        Calendar c11=Calendar.getInstance();  c11.set(2014, 9, 25);
        
        pp1.setGeneratedDate(c1);pp2.setGeneratedDate(c2);pp3.setGeneratedDate(c3);pp4.setGeneratedDate(c4);pp5.setGeneratedDate(c5);
        pp6.setGeneratedDate(c6);pp7.setGeneratedDate(c7);pp8.setGeneratedDate(c8);pp9.setGeneratedDate(c9);pp10.setGeneratedDate(c10);
        pp11.setGeneratedDate(c1);pp12.setGeneratedDate(c2);pp13.setGeneratedDate(c3);pp14.setGeneratedDate(c4);pp15.setGeneratedDate(c5);
        pp16.setGeneratedDate(c6);pp17.setGeneratedDate(c7);pp18.setGeneratedDate(c8);pp19.setGeneratedDate(c9);pp20.setGeneratedDate(c10);
        
        
        pp1.setTargetPeriod(c11);pp2.setTargetPeriod(c11);pp3.setTargetPeriod(c11);pp4.setTargetPeriod(c11);pp5.setTargetPeriod(c11);
        pp6.setTargetPeriod(c11);pp7.setTargetPeriod(c11);pp8.setTargetPeriod(c11);pp9.setTargetPeriod(c11);pp10.setTargetPeriod(c11);
        pp11.setTargetPeriod(c11);pp12.setTargetPeriod(c11);pp13.setTargetPeriod(c11);pp14.setTargetPeriod(c11);pp15.setTargetPeriod(c11);
        pp16.setTargetPeriod(c11);pp17.setTargetPeriod(c11);pp18.setTargetPeriod(c11);pp19.setTargetPeriod(c11);pp20.setTargetPeriod(c11);
        
        
//        
//        
        pp1.setFactoryProduct(fp1);pp2.setFactoryProduct(fp2);pp3.setFactoryProduct(fp3);pp4.setFactoryProduct(fp4);pp5.setFactoryProduct(fp5);
        pp6.setFactoryProduct(fp6);pp7.setFactoryProduct(fp7);pp8.setFactoryProduct(fp8);pp9.setFactoryProduct(fp9);pp10.setFactoryProduct(fp10);
        pp11.setFactoryProduct(fp11);pp12.setFactoryProduct(fp12);pp13.setFactoryProduct(fp13);pp14.setFactoryProduct(fp14);pp15.setFactoryProduct(fp15);
        pp16.setFactoryProduct(fp16);pp17.setFactoryProduct(fp17);pp18.setFactoryProduct(fp18);pp19.setFactoryProduct(fp19);pp20.setFactoryProduct(fp20);
      
        em.persist(pp1);em.flush();em.persist(pp2);em.flush();em.persist(pp3);em.flush();em.persist(pp4);em.flush();em.persist(pp5);em.flush();
        em.persist(pp6);em.flush();em.persist(pp7);em.flush();em.persist(pp8);em.flush();em.persist(pp9);em.flush();em.persist(pp10);em.flush();
        em.persist(pp11);em.flush();em.persist(pp12);em.flush();em.persist(pp13);em.flush();em.persist(pp14);em.flush();em.persist(pp15);em.flush();
        em.persist(pp16);em.flush();em.persist(pp17);em.flush();em.persist(pp18);em.flush();em.persist(pp19);em.flush();em.persist(pp20);em.flush();
        
        //set up raw material entity
        RawMaterialEntity rm1 = new RawMaterialEntity();
        RawMaterialEntity rm2 = new RawMaterialEntity();
        RawMaterialEntity rm3 = new RawMaterialEntity();
        RawMaterialEntity rm4 = new RawMaterialEntity();
        RawMaterialEntity rm5 = new RawMaterialEntity();
        
        rm1.setMaterialName("board");rm1.setDescription("hehe");rm1.setUnit("piece");rm1.setIsDeleted(Boolean.FALSE);
        rm2.setMaterialName("metal");rm2.setDescription("hehe");rm2.setUnit("piece");rm2.setIsDeleted(Boolean.FALSE);
        rm3.setMaterialName("nail");rm3.setDescription("hehe");rm3.setUnit("box");rm3.setIsDeleted(Boolean.FALSE);
        rm4.setMaterialName("nut");rm4.setDescription("hehe");rm4.setUnit("box");rm4.setIsDeleted(Boolean.FALSE);
        rm5.setMaterialName("stick");rm5.setDescription("hehe");rm5.setUnit("box");rm5.setIsDeleted(Boolean.FALSE);
        
        em.persist(rm1);em.flush();em.persist(rm2);em.flush();em.persist(rm3);em.flush();em.persist(rm4);em.flush();em.persist(rm5);em.flush();
        
        //set up factory raw material entity and link with raw material entity
        FactoryRawMaterialEntity frm1 = new FactoryRawMaterialEntity();
        FactoryRawMaterialEntity frm2 = new FactoryRawMaterialEntity();
        FactoryRawMaterialEntity frm3 = new FactoryRawMaterialEntity();
        FactoryRawMaterialEntity frm4 = new FactoryRawMaterialEntity();
        FactoryRawMaterialEntity frm5 = new FactoryRawMaterialEntity();
        FactoryRawMaterialEntity frm6 = new FactoryRawMaterialEntity();
        FactoryRawMaterialEntity frm7 = new FactoryRawMaterialEntity();
        FactoryRawMaterialEntity frm8 = new FactoryRawMaterialEntity();
        FactoryRawMaterialEntity frm9 = new FactoryRawMaterialEntity();
        FactoryRawMaterialEntity frm10 = new FactoryRawMaterialEntity();
        FactoryRawMaterialEntity frm11 = new FactoryRawMaterialEntity();
        FactoryRawMaterialEntity frm12 = new FactoryRawMaterialEntity();
        FactoryRawMaterialEntity frm13 = new FactoryRawMaterialEntity();
        FactoryRawMaterialEntity frm14 = new FactoryRawMaterialEntity();
        FactoryRawMaterialEntity frm15 = new FactoryRawMaterialEntity();
        
        frm1.setFactory(f1);frm2.setFactory(f1);frm3.setFactory(f1);frm4.setFactory(f1);frm5.setFactory(f1);
        frm6.setFactory(f2);frm7.setFactory(f2);frm8.setFactory(f2);frm9.setFactory(f2);frm10.setFactory(f2);
        frm11.setFactory(f3);frm12.setFactory(f3);frm13.setFactory(f3);frm14.setFactory(f3);frm15.setFactory(f3);
        
        frm1.setRawMaterial(rm1);frm2.setRawMaterial(rm2);frm3.setRawMaterial(rm3);frm4.setRawMaterial(rm4);frm5.setRawMaterial(rm5);
        frm6.setRawMaterial(rm1);frm7.setRawMaterial(rm2);frm8.setRawMaterial(rm3);frm9.setRawMaterial(rm4);frm10.setRawMaterial(rm5);
        frm11.setRawMaterial(rm1);frm12.setRawMaterial(rm2);frm13.setRawMaterial(rm3);frm14.setRawMaterial(rm4);frm15.setRawMaterial(rm5);
        
        em.persist(frm1);em.flush();em.persist(frm2);em.flush();em.persist(frm3);em.flush();em.persist(frm4);em.flush();em.persist(frm5);em.flush();
        em.persist(frm6);em.flush();em.persist(frm7);em.flush();em.persist(frm8);em.flush();em.persist(frm9);em.flush();em.persist(frm10);em.flush();
        em.persist(frm11);em.flush();em.persist(frm12);em.flush();em.persist(frm13);em.flush();em.persist(frm14);em.flush();em.persist(frm15);em.flush();
        
        //Shiyu: 27 Sep add frm to factory
        f1.getFactoryRawMaterials().add(frm1);
        f1.getFactoryRawMaterials().add(frm2);
        f1.getFactoryRawMaterials().add(frm3);
        f1.getFactoryRawMaterials().add(frm4);
        f1.getFactoryRawMaterials().add(frm5);
        f2.getFactoryRawMaterials().add(frm6);
        f2.getFactoryRawMaterials().add(frm7);
        
        //Shiyu: 27 Sep add frm to factory
        rm1.getFactoryRawMaterials().add(frm1);
        rm1.getFactoryRawMaterials().add(frm6);
        rm1.getFactoryRawMaterials().add(frm11);
        rm2.getFactoryRawMaterials().add(frm2);
        rm2.getFactoryRawMaterials().add(frm7);
        rm2.getFactoryRawMaterials().add(frm12);
        rm3.getFactoryRawMaterials().add(frm3);
        rm3.getFactoryRawMaterials().add(frm8);
        rm3.getFactoryRawMaterials().add(frm13);
        rm4.getFactoryRawMaterials().add(frm4);
        rm4.getFactoryRawMaterials().add(frm9);
        rm4.getFactoryRawMaterials().add(frm14);
        rm5.getFactoryRawMaterials().add(frm5);
        rm5.getFactoryRawMaterials().add(frm10);
        rm5.getFactoryRawMaterials().add(frm15);
        
        
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
        FactoryRawMaterialAmountEntity frma11 = new FactoryRawMaterialAmountEntity();
        FactoryRawMaterialAmountEntity frma12 = new FactoryRawMaterialAmountEntity();
        FactoryRawMaterialAmountEntity frma13 = new FactoryRawMaterialAmountEntity();
        FactoryRawMaterialAmountEntity frma14 = new FactoryRawMaterialAmountEntity();
        FactoryRawMaterialAmountEntity frma15 = new FactoryRawMaterialAmountEntity();
        
        frma1.setFactoryRawMaterial(frm1);frma1.setAmount(4D);frma1.setUnit("piece");
        frma2.setFactoryRawMaterial(frm2);frma2.setAmount(4D);frma2.setUnit("piece");
        frma3.setFactoryRawMaterial(frm3);frma3.setAmount(4D);frma3.setUnit("box");
        frma4.setFactoryRawMaterial(frm4);frma4.setAmount(4D);frma4.setUnit("box");
        frma5.setFactoryRawMaterial(frm5);frma5.setAmount(4D);frma5.setUnit("box");
        frma6.setFactoryRawMaterial(frm6);frma6.setAmount(4D);frma6.setUnit("piece");
        frma7.setFactoryRawMaterial(frm7);frma7.setAmount(4D);frma7.setUnit("piece");
        frma8.setFactoryRawMaterial(frm8);frma8.setAmount(4D);frma8.setUnit("box");
        frma9.setFactoryRawMaterial(frm9);frma9.setAmount(4D);frma9.setUnit("box");
        frma10.setFactoryRawMaterial(frm10);frma10.setAmount(4D);frma10.setUnit("box");
        frma11.setFactoryRawMaterial(frm11);frma11.setAmount(4D);frma11.setUnit("piece");
        frma12.setFactoryRawMaterial(frm12);frma12.setAmount(4D);frma12.setUnit("piece");
        frma13.setFactoryRawMaterial(frm13);frma13.setAmount(4D);frma13.setUnit("box");
        frma14.setFactoryRawMaterial(frm14);frma14.setAmount(4D);frma14.setUnit("box");
        frma15.setFactoryRawMaterial(frm15);frma15.setAmount(4D);frma15.setUnit("box");
        
        em.persist(frma1);em.flush();em.persist(frma2);em.flush();em.persist(frma3);em.flush();em.persist(frma4);em.flush();em.persist(frma5);em.flush();
        em.persist(frma6);em.flush();em.persist(frma7);em.flush();em.persist(frma8);em.flush();em.persist(frma9);em.flush();em.persist(frma10);em.flush();
        em.persist(frma11);em.flush();em.persist(frma12);em.flush();em.persist(frma13);em.flush();em.persist(frma14);em.flush();em.persist(frma15);em.flush();
        
        //set up planned order entity(raw material)
        PlannedOrderEntity po1 = new PlannedOrderEntity();PlannedOrderEntity po2 = new PlannedOrderEntity();
        PlannedOrderEntity po3 = new PlannedOrderEntity();PlannedOrderEntity po4 = new PlannedOrderEntity();
        PlannedOrderEntity po5 = new PlannedOrderEntity();PlannedOrderEntity po6 = new PlannedOrderEntity();
        PlannedOrderEntity po7 = new PlannedOrderEntity();PlannedOrderEntity po8 = new PlannedOrderEntity();
        PlannedOrderEntity po9 = new PlannedOrderEntity();PlannedOrderEntity po10 = new PlannedOrderEntity();
        PlannedOrderEntity po11 = new PlannedOrderEntity();PlannedOrderEntity po12 = new PlannedOrderEntity();
        PlannedOrderEntity po13 = new PlannedOrderEntity();PlannedOrderEntity po14 = new PlannedOrderEntity();PlannedOrderEntity po15 = new PlannedOrderEntity();
        
        po1.setGeneratedDate(c1);po2.setGeneratedDate(c2);po3.setGeneratedDate(c3);po4.setGeneratedDate(c1);po5.setGeneratedDate(c1);
        po6.setGeneratedDate(c6);po7.setGeneratedDate(c7);po8.setGeneratedDate(c8);po9.setGeneratedDate(c9);po10.setGeneratedDate(c10);
        po11.setGeneratedDate(c1);po12.setGeneratedDate(c2);po13.setGeneratedDate(c3);po14.setGeneratedDate(c4);po15.setGeneratedDate(c5);
        
        po1.setTargetPeriod(c11);po2.setTargetPeriod(c11);po3.setTargetPeriod(c11);po4.setTargetPeriod(c11);po5.setTargetPeriod(c11);
        po6.setTargetPeriod(c11);po7.setTargetPeriod(c11);po8.setTargetPeriod(c5);po9.setTargetPeriod(c6);po10.setTargetPeriod(c8);
        po11.setTargetPeriod(c11);po12.setTargetPeriod(c5);po13.setTargetPeriod(c3);po14.setTargetPeriod(c2);po15.setTargetPeriod(c10);
        
        po1.setStatus("unconfirmed");po2.setStatus("unconfirmed");po3.setStatus("unconfirmed");po4.setStatus("unconfirmed");po5.setStatus("unconfirmed");
        po6.setStatus("unconfirmed");po7.setStatus("unconfirmed");po8.setStatus("unconfirmed");po9.setStatus("unconfirmed");po10.setStatus("unconfirmed");
        po11.setStatus("unconfirmed");po12.setStatus("unconfirmed");po13.setStatus("unconfirmed");po14.setStatus("unconfirmed");po15.setStatus("unconfirmed");
        
        po1.setFactory(f1);po2.setFactory(f1);po3.setFactory(f1);po4.setFactory(f1);po5.setFactory(f1);
        po6.setFactory(f2);po7.setFactory(f2);po8.setFactory(f2);po9.setFactory(f2);po10.setFactory(f2);
        po11.setFactory(f3);po12.setFactory(f3);po13.setFactory(f3);po14.setFactory(f3);po15.setFactory(f3);
        
        //create list factory raw material amount
        List<FactoryRawMaterialAmountEntity> frmal1 = new ArrayList();
        List<FactoryRawMaterialAmountEntity> frmal2 = new ArrayList();
        List<FactoryRawMaterialAmountEntity> frmal3 = new ArrayList();
        List<FactoryRawMaterialAmountEntity> frmal4 = new ArrayList();
        List<FactoryRawMaterialAmountEntity> frmal5 = new ArrayList();
        List<FactoryRawMaterialAmountEntity> frmal6 = new ArrayList();
        List<FactoryRawMaterialAmountEntity> frmal7 = new ArrayList();
        List<FactoryRawMaterialAmountEntity> frmal8 = new ArrayList();
        List<FactoryRawMaterialAmountEntity> frmal9 = new ArrayList();
        List<FactoryRawMaterialAmountEntity> frmal10 = new ArrayList();
        List<FactoryRawMaterialAmountEntity> frmal11 = new ArrayList();
        List<FactoryRawMaterialAmountEntity> frmal12 = new ArrayList();
        List<FactoryRawMaterialAmountEntity> frmal13 = new ArrayList();
        List<FactoryRawMaterialAmountEntity> frmal14 = new ArrayList();
        List<FactoryRawMaterialAmountEntity> frmal15 = new ArrayList();
        
        
        frmal1.add(frma1);frmal1.add(frma2);frmal1.add(frma3);frmal1.add(frma4);frmal1.add(frma5);
        frmal2.add(frma2);frmal2.add(frma3);frmal2.add(frma4);frmal2.add(frma5);
        frmal3.add(frma3);frmal3.add(frma4);frmal3.add(frma5);
        frmal4.add(frma4);frmal4.add(frma5);
        frmal5.add(frma5);
        
        frmal6.add(frma1);frmal6.add(frma2);frmal6.add(frma3);frmal6.add(frma4);frmal6.add(frma5);
        frmal7.add(frma2);frmal7.add(frma3);frmal7.add(frma4);frmal7.add(frma5);
        frmal8.add(frma3);frmal8.add(frma4);frmal8.add(frma5);
        frmal9.add(frma4);frmal9.add(frma5);
        frmal10.add(frma5);
        
        frmal11.add(frma1);frmal11.add(frma2);frmal11.add(frma3);frmal11.add(frma4);frmal11.add(frma5);
        frmal12.add(frma2);frmal12.add(frma3);frmal12.add(frma4);frmal12.add(frma5);
        frmal13.add(frma3);frmal13.add(frma4);frmal13.add(frma5);
        frmal14.add(frma4);frmal14.add(frma5);
        frmal15.add(frma5);
        
        po1.setFactoryRawMaterialAmountList(frmal1);
        po2.setFactoryRawMaterialAmountList(frmal2);
        po3.setFactoryRawMaterialAmountList(frmal3);
        po4.setFactoryRawMaterialAmountList(frmal4);
        po5.setFactoryRawMaterialAmountList(frmal5);
        po6.setFactoryRawMaterialAmountList(frmal6);
        po7.setFactoryRawMaterialAmountList(frmal7);
        po8.setFactoryRawMaterialAmountList(frmal8);
        po9.setFactoryRawMaterialAmountList(frmal9);
        po10.setFactoryRawMaterialAmountList(frmal10);
        po11.setFactoryRawMaterialAmountList(frmal11);
        po12.setFactoryRawMaterialAmountList(frmal12);
        po13.setFactoryRawMaterialAmountList(frmal13);
        po14.setFactoryRawMaterialAmountList(frmal14);
        po15.setFactoryRawMaterialAmountList(frmal15);
        
        em.persist(po1);em.flush();em.persist(po2);em.flush();em.persist(po3);em.flush();em.persist(po4);em.flush();em.persist(po5);em.flush();
        em.persist(po6);em.flush();em.persist(po7);em.flush();em.persist(po8);em.flush();em.persist(po9);em.flush();em.persist(po10);em.flush();
        em.persist(po11);em.flush();em.persist(po12);em.flush();em.persist(po13);em.flush();em.persist(po14);em.flush();em.persist(po15);em.flush();

         /*      
        
        
             我有一只大猫猫 它从来都不喵
        
        
        
        */
        
        
        //BOM
        BOMEntity bom1=new BOMEntity();BOMEntity bom2=new BOMEntity();BOMEntity bom3=new BOMEntity();
        BOMEntity bom4=new BOMEntity();BOMEntity bom5=new BOMEntity();BOMEntity bom6=new BOMEntity();
        BOMEntity bom7=new BOMEntity();BOMEntity bom8=new BOMEntity();BOMEntity bom9=new BOMEntity();
        
        bom1.setAmount(10D);bom1.setProduct(p1);bom1.setRawMaterial(rm1);bom1.setUnit("piece");
        bom2.setAmount(20D);bom2.setProduct(p2);bom2.setRawMaterial(rm2);bom1.setUnit("piece");
        bom3.setAmount(30D);bom3.setProduct(p3);bom3.setRawMaterial(rm3);bom1.setUnit("box");
        bom4.setAmount(40D);bom4.setProduct(p4);bom4.setRawMaterial(rm4);bom1.setUnit("box");
        bom5.setAmount(50D);bom5.setProduct(p5);bom5.setRawMaterial(rm5);bom1.setUnit("box");
        bom6.setAmount(60D);bom6.setProduct(p6);bom6.setRawMaterial(rm1);bom1.setUnit("piece");
        bom7.setAmount(70D);bom7.setProduct(p7);bom7.setRawMaterial(rm2);bom1.setUnit("piece");
        bom8.setAmount(80D);bom8.setProduct(p8);bom8.setRawMaterial(rm3);bom1.setUnit("box");
        bom9.setAmount(90D);bom9.setProduct(p9);bom9.setRawMaterial(rm4);bom1.setUnit("box");
        
        
        em.persist(bom1);em.flush();em.persist(bom2);em.flush();em.persist(bom3);em.flush();
        em.persist(bom4);em.flush();em.persist(bom5);em.flush();em.persist(bom6);em.flush();
        em.persist(bom7);em.flush();em.persist(bom8);em.flush();em.persist(bom9);em.flush();
      
        
        
        // Factory Product
        FactoryProductAmountEntity fpa1 =new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa2 =new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa3 =new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa4 =new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa5 =new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa6 =new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa7 =new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa8 =new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa9 =new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa10 =new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa11 =new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa12 =new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa13 =new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa14 =new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa15 =new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa16 =new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa17 =new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa18 =new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa19 =new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa20 =new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa21 =new FactoryProductAmountEntity();
        FactoryProductAmountEntity fpa22 =new FactoryProductAmountEntity();
        
        
        fpa1.setAmount(1000D);fpa1.setFactoryProduct(fp1);fpa1.setUnit("unit");
        fpa2.setAmount(2000D);fpa2.setFactoryProduct(fp2);fpa2.setUnit("unit"); 
        fpa3.setAmount(3000D);fpa3.setFactoryProduct(fp3);fpa3.setUnit("unit");
        fpa4.setAmount(4000D);fpa4.setFactoryProduct(fp4);fpa4.setUnit("unit");
        fpa5.setAmount(5000D);fpa5.setFactoryProduct(fp5);fpa5.setUnit("unit");
        fpa6.setAmount(6000D);fpa6.setFactoryProduct(fp6);fpa6.setUnit("unit");
        fpa7.setAmount(7000D);fpa7.setFactoryProduct(fp7);fpa7.setUnit("unit");
        fpa8.setAmount(8000D);fpa8.setFactoryProduct(fp8);fpa8.setUnit("unit");
        fpa9.setAmount(9000D);fpa9.setFactoryProduct(fp9);fpa9.setUnit("unit");
        fpa10.setAmount(4000D);fpa4.setFactoryProduct(fp4);fpa4.setUnit("unit");
        fpa11.setAmount(5000D);fpa5.setFactoryProduct(fp5);fpa5.setUnit("unit");
        fpa12.setAmount(6000D);fpa6.setFactoryProduct(fp6);fpa6.setUnit("unit");
        fpa13.setAmount(7000D);fpa7.setFactoryProduct(fp7);fpa7.setUnit("unit");
        fpa14.setAmount(8000D);fpa8.setFactoryProduct(fp8);fpa8.setUnit("unit");
        fpa15.setAmount(9000D);fpa9.setFactoryProduct(fp9);fpa9.setUnit("unit");
        fpa16.setAmount(9000D);fpa9.setFactoryProduct(fp6);fpa9.setUnit("unit");
        fpa17.setAmount(4000D);fpa4.setFactoryProduct(fp4);fpa4.setUnit("unit");
        fpa18.setAmount(5000D);fpa5.setFactoryProduct(fp5);fpa5.setUnit("unit");
        fpa19.setAmount(6000D);fpa6.setFactoryProduct(fp4);fpa6.setUnit("unit");
        fpa20.setAmount(7000D);fpa7.setFactoryProduct(fp3);fpa7.setUnit("unit");
        fpa21.setAmount(8000D);fpa8.setFactoryProduct(fp2);fpa8.setUnit("unit");
        fpa22.setAmount(9000D);fpa9.setFactoryProduct(fp1);fpa9.setUnit("unit");
       
        
        
        em.persist(fpa1);em.flush();
        em.persist(fpa2);em.flush();
        em.persist(fpa3);em.flush();
        em.persist(fpa4);em.flush();
        em.persist(fpa5);em.flush();
        em.persist(fpa6);em.flush();
        em.persist(fpa7);em.flush();
        em.persist(fpa8);em.flush();
        em.persist(fpa9);em.flush();
        em.persist(fpa10);em.flush();
        em.persist(fpa11);em.flush();
        em.persist(fpa12);em.flush();
        em.persist(fpa13);em.flush();
        em.persist(fpa14);em.flush();
        em.persist(fpa15);em.flush();
        em.persist(fpa16);em.flush();
        em.persist(fpa17);em.flush();
        em.persist(fpa18);em.flush();
        em.persist(fpa19);em.flush();
        em.persist(fpa20);em.flush();
        em.persist(fpa21);em.flush();
        em.persist(fpa22);em.flush();
        
        
        
        // Integrated Sales Forecast     /* NOT FINISHED */
        IntegratedSalesForecastEntity isf1=new IntegratedSalesForecastEntity();
        IntegratedSalesForecastEntity isf2=new IntegratedSalesForecastEntity();
        IntegratedSalesForecastEntity isf3=new IntegratedSalesForecastEntity();

    
        isf1.setAmount(5000D);isf1.setFactory(f1);isf1.setFactoryProduct(fp1);isf1.setTargetPeriod(c9);
        isf2.setAmount(5500D);isf2.setFactory(f2);isf2.setFactoryProduct(fp2);isf1.setTargetPeriod(c8);
        isf3.setAmount(6000D);isf3.setFactory(f3);isf3.setFactoryProduct(fp3);isf1.setTargetPeriod(c7);
        
        em.persist(isf1);em.flush();
        em.persist(isf2);em.flush();
        em.persist(isf3);em.flush();
        
        
        //Retail Product Entity          /* NOT FINISHED */
        RetailProductEntity rpe1=new RetailProductEntity();
        RetailProductEntity rpe2=new RetailProductEntity();
        RetailProductEntity rpe3=new RetailProductEntity();
        RetailProductEntity rpe4=new RetailProductEntity();
        RetailProductEntity rpe5=new RetailProductEntity();
        RetailProductEntity rpe6=new RetailProductEntity();
        RetailProductEntity rpe7=new RetailProductEntity();
        RetailProductEntity rpe8=new RetailProductEntity();
        RetailProductEntity rpe9=new RetailProductEntity();
        
        rpe1.setDeleteFlag(false);rpe1.setDescription("I dont know");rpe1.setName("A");rpe1.setUnit("unit");
        rpe2.setDeleteFlag(false);rpe2.setDescription("I dont know");rpe2.setName("B");rpe2.setUnit("unit");
        rpe3.setDeleteFlag(false);rpe3.setDescription("I dont know");rpe3.setName("C");rpe3.setUnit("unit");
        rpe4.setDeleteFlag(false);rpe4.setDescription("I dont know");rpe4.setName("D");rpe4.setUnit("unit");
        rpe5.setDeleteFlag(false);rpe5.setDescription("I dont know");rpe5.setName("E");rpe5.setUnit("unit");
        rpe6.setDeleteFlag(false);rpe6.setDescription("I dont know");rpe6.setName("F");rpe6.setUnit("unit");
        rpe7.setDeleteFlag(false);rpe7.setDescription("I dont know");rpe7.setName("G");rpe7.setUnit("unit");
        rpe8.setDeleteFlag(false);rpe8.setDescription("I dont know");rpe8.setName("H");rpe8.setUnit("unit");
        rpe9.setDeleteFlag(false);rpe9.setDescription("I dont know");rpe9.setName("I");rpe9.setUnit("unit");
        
        em.persist(rpe1);em.flush();
        em.persist(rpe2);em.flush();
        em.persist(rpe3);em.flush();
        em.persist(rpe4);em.flush();
        em.persist(rpe5);em.flush();
        em.persist(rpe6);em.flush();
        em.persist(rpe7);em.flush();
        em.persist(rpe8);em.flush();
        em.persist(rpe9);em.flush();
        
        
        //Sales Forecast
        SalesForecastEntity sfe1=new SalesForecastEntity();
        SalesForecastEntity sfe2=new SalesForecastEntity();
        SalesForecastEntity sfe3=new SalesForecastEntity();
        SalesForecastEntity sfe4=new SalesForecastEntity();
        SalesForecastEntity sfe5=new SalesForecastEntity();
   
        
        List<FactoryProductAmountEntity> listfpe1=new ArrayList<>();listfpe1.add(fpa1);listfpe1.add(fpa2);listfpe1.add(fpa3);
        List<FactoryProductAmountEntity> listfpe2=new ArrayList<>();listfpe2.add(fpa4);listfpe2.add(fpa5);listfpe2.add(fpa6);listfpe2.add(fpa7);
        List<FactoryProductAmountEntity> listfpe3=new ArrayList<>();listfpe3.add(fpa8);listfpe3.add(fpa9);listfpe3.add(fpa10);listfpe3.add(fpa11);listfpe3.add(fpa12);
        List<FactoryProductAmountEntity> listfpe4=new ArrayList<>();listfpe4.add(fpa13);listfpe4.add(fpa14);listfpe4.add(fpa15);listfpe4.add(fpa16);listfpe4.add(fpa17);
        List<FactoryProductAmountEntity> listfpe5=new ArrayList<>();listfpe5.add(fpa18);listfpe5.add(fpa19);listfpe5.add(fpa20);listfpe5.add(fpa21);listfpe5.add(fpa22);

   
               
        FactoryRetailProductEntity frpe1=new FactoryRetailProductEntity();frpe1.setBlockedInventory(0d);frpe1.setFactory(f1);frpe1.setName("RetailA");frpe1.setUnit("unit");frpe1.setRetailProduct(rpe1);
        FactoryRetailProductEntity frpe2=new FactoryRetailProductEntity();frpe2.setBlockedInventory(0d);frpe2.setFactory(f2);frpe2.setName("RetailB");frpe2.setUnit("unit");frpe2.setRetailProduct(rpe2);
        FactoryRetailProductEntity frpe3=new FactoryRetailProductEntity();frpe3.setBlockedInventory(0d);frpe3.setFactory(f3);frpe3.setName("RetailC");frpe3.setUnit("unit");frpe3.setRetailProduct(rpe3);
        FactoryRetailProductEntity frpe4=new FactoryRetailProductEntity();frpe4.setBlockedInventory(0d);frpe4.setFactory(f1);frpe4.setName("RetailD");frpe4.setUnit("unit");frpe4.setRetailProduct(rpe4);
        FactoryRetailProductEntity frpe5=new FactoryRetailProductEntity();frpe5.setBlockedInventory(0d);frpe5.setFactory(f2);frpe5.setName("RetailE");frpe5.setUnit("unit");frpe5.setRetailProduct(rpe5);
        FactoryRetailProductEntity frpe6=new FactoryRetailProductEntity();frpe6.setBlockedInventory(0d);frpe6.setFactory(f3);frpe6.setName("RetailF");frpe6.setUnit("unit");frpe6.setRetailProduct(rpe6);
        FactoryRetailProductEntity frpe7=new FactoryRetailProductEntity();frpe7.setBlockedInventory(0d);frpe7.setFactory(f1);frpe7.setName("RetailG");frpe7.setUnit("unit");frpe7.setRetailProduct(rpe7);
        FactoryRetailProductEntity frpe8=new FactoryRetailProductEntity();frpe8.setBlockedInventory(0d);frpe8.setFactory(f2);frpe8.setName("RetailH");frpe8.setUnit("unit");frpe8.setRetailProduct(rpe8);
        FactoryRetailProductEntity frpe9=new FactoryRetailProductEntity();frpe9.setBlockedInventory(0d);frpe9.setFactory(f3);frpe9.setName("RetailI");frpe9.setUnit("unit");frpe9.setRetailProduct(rpe9);
        
        em.persist(frpe1);em.flush();
        em.persist(frpe2);em.flush();
        em.persist(frpe3);em.flush();
        em.persist(frpe4);em.flush();
        em.persist(frpe5);em.flush();
        em.persist(frpe6);em.flush();
        em.persist(frpe7);em.flush();
        em.persist(frpe8);em.flush();
        em.persist(frpe9);em.flush();
        
        f1.getFactoryRetailProducts().add(frpe1);
        f1.getFactoryRetailProducts().add(frpe4);
        f1.getFactoryRetailProducts().add(frpe7);
        f2.getFactoryRetailProducts().add(frpe2);
        f2.getFactoryRetailProducts().add(frpe5);
        f2.getFactoryRetailProducts().add(frpe8);
        f3.getFactoryRetailProducts().add(frpe3);
        f3.getFactoryRetailProducts().add(frpe6);
        f3.getFactoryRetailProducts().add(frpe9);

        
        
        FactoryRetailProductAmountEntity rpa1=new FactoryRetailProductAmountEntity();rpa1.setAmount(100D);rpa1.setFactoryRetailProduct(frpe1);
        FactoryRetailProductAmountEntity rpa2=new FactoryRetailProductAmountEntity();rpa2.setAmount(200D);rpa1.setFactoryRetailProduct(frpe2);
        FactoryRetailProductAmountEntity rpa3=new FactoryRetailProductAmountEntity();rpa3.setAmount(300D);rpa1.setFactoryRetailProduct(frpe3);
        FactoryRetailProductAmountEntity rpa4=new FactoryRetailProductAmountEntity();rpa4.setAmount(400D);rpa1.setFactoryRetailProduct(frpe4);
        FactoryRetailProductAmountEntity rpa5=new FactoryRetailProductAmountEntity();rpa5.setAmount(500D);rpa1.setFactoryRetailProduct(frpe5);
        FactoryRetailProductAmountEntity rpa6=new FactoryRetailProductAmountEntity();rpa6.setAmount(600D);rpa1.setFactoryRetailProduct(frpe6);
        FactoryRetailProductAmountEntity rpa7=new FactoryRetailProductAmountEntity();rpa7.setAmount(700D);rpa1.setFactoryRetailProduct(frpe7);
        FactoryRetailProductAmountEntity rpa8=new FactoryRetailProductAmountEntity();rpa8.setAmount(800D);rpa1.setFactoryRetailProduct(frpe8);
        FactoryRetailProductAmountEntity rpa9=new FactoryRetailProductAmountEntity();rpa9.setAmount(900D);rpa1.setFactoryRetailProduct(frpe9);
        FactoryRetailProductAmountEntity rpa10=new FactoryRetailProductAmountEntity();rpa7.setAmount(700D);rpa1.setFactoryRetailProduct(frpe1);
        FactoryRetailProductAmountEntity rpa11=new FactoryRetailProductAmountEntity();rpa8.setAmount(800D);rpa1.setFactoryRetailProduct(frpe2);
        FactoryRetailProductAmountEntity rpa12=new FactoryRetailProductAmountEntity();rpa9.setAmount(900D);rpa1.setFactoryRetailProduct(frpe3);
        FactoryRetailProductAmountEntity rpa13=new FactoryRetailProductAmountEntity();rpa7.setAmount(700D);rpa1.setFactoryRetailProduct(frpe3);
        FactoryRetailProductAmountEntity rpa14=new FactoryRetailProductAmountEntity();rpa8.setAmount(800D);rpa1.setFactoryRetailProduct(frpe4);
        FactoryRetailProductAmountEntity rpa15=new FactoryRetailProductAmountEntity();rpa9.setAmount(900D);rpa1.setFactoryRetailProduct(frpe5);
        
        em.persist(rpa1);em.flush();
        em.persist(rpa2);em.flush();
        em.persist(rpa3);em.flush();
        em.persist(rpa4);em.flush();
        em.persist(rpa5);em.flush();
        em.persist(rpa6);em.flush();
        em.persist(rpa7);em.flush();
        em.persist(rpa8);em.flush();
        em.persist(rpa9);em.flush();
        
        
        List<FactoryRetailProductAmountEntity> listrpe1=new ArrayList<>();listrpe1.add(rpa1);listrpe1.add(rpa2);listrpe1.add(rpa3);
        List<FactoryRetailProductAmountEntity> listrpe2=new ArrayList<>();listrpe1.add(rpa4);listrpe1.add(rpa5);listrpe1.add(rpa6);
        List<FactoryRetailProductAmountEntity> listrpe3=new ArrayList<>();listrpe1.add(rpa7);listrpe1.add(rpa8);listrpe1.add(rpa9);
        List<FactoryRetailProductAmountEntity> listrpe4=new ArrayList<>();listrpe1.add(rpa10);listrpe1.add(rpa11);listrpe1.add(rpa12);
        List<FactoryRetailProductAmountEntity> listrpe5=new ArrayList<>();listrpe1.add(rpa13);listrpe1.add(rpa14);listrpe1.add(rpa15);
     
   
        
        
        StoreEntity store1=new StoreEntity();store1.setAddress("Singapore");store1.setContact("88888888");store1.setCountry("Singapore");store1.setManager("Zhen Yuan");
        StoreEntity store2=new StoreEntity();store1.setAddress("Singapore");store1.setContact("66666666");store1.setCountry("Singapore");store1.setManager("Zhang Shiyu");
        StoreEntity store3=new StoreEntity();store1.setAddress("Singapore");store1.setContact("11111111");store1.setCountry("Singapore");store1.setManager("Zhao Mengdan");
        
        em.persist(store1);em.flush();
        em.persist(store2);em.flush();
        em.persist(store3);em.flush();
        
        sfe1.setFactoryProductList(listfpe1);sfe1.setFactoryRetailProductList(listrpe1);sfe1.setStatus("unconfirmed");sfe1.setStore(store1);sfe1.setTargetPeriod(c10);
        sfe2.setFactoryProductList(listfpe2);sfe2.setFactoryRetailProductList(listrpe2);sfe2.setStatus("unconfirmed");sfe2.setStore(store1);sfe2.setTargetPeriod(c9);
        sfe3.setFactoryProductList(listfpe3);sfe3.setFactoryRetailProductList(listrpe3);sfe3.setStatus("unconfirmed");sfe3.setStore(store1);sfe3.setTargetPeriod(c10);
        sfe4.setFactoryProductList(listfpe4);sfe4.setFactoryRetailProductList(listrpe4);sfe4.setStatus("unconfirmed");sfe4.setStore(store2);sfe4.setTargetPeriod(c9);
        sfe5.setFactoryProductList(listfpe5);sfe5.setFactoryRetailProductList(listrpe5);sfe5.setStatus("unconfirmed");sfe5.setStore(store2);sfe5.setTargetPeriod(c10);

        em.persist(sfe1);em.flush();
        em.persist(sfe2);em.flush();
        em.persist(sfe3);em.flush();
        em.persist(sfe4);em.flush();
        em.persist(sfe5);em.flush();

        
        // Sales Operation Plan
        SalesOperationPlanEntity sop1=new SalesOperationPlanEntity();sop1.setFactoryProduct(fp1);sop1.setIntegratedSalesForecast(isf1);sop1.setPlannedEndMonthInventory(500D);sop1.setTargetPeriod(c8);sop1.setWorkingDay(22);

        em.persist(sop1);em.flush();
        
        
        
         // Supplier
        SupplierEntity s1 = new SupplierEntity("ABC", "Address1", "+6512345678", "+6587654321", "Remark1");
        SupplierEntity s2 = new SupplierEntity("BCD", "Address2", "+6523456789", "+6525253421", "Remark2");
        SupplierEntity s3 = new SupplierEntity("CDE", "Address3", "+8634734567890", "+8658984336346", "Remark3");
        SupplierEntity s4 = new SupplierEntity("DEF", "Address4", "+6545678901", "+6583849394", "Remark4");
        SupplierEntity s5 = new SupplierEntity("EFG", "Address5", "+6556789012", "+6585433446", "Remark5");
        SupplierEntity s6 = new SupplierEntity("FGH", "Address6", "+8667890453123", "+8689087654321", "Remark6");
        SupplierEntity s7 = new SupplierEntity("GHI", "Address7", "+8578901234", "+8546363321", "Remark7");
        SupplierEntity s8 = new SupplierEntity("HIJ", "Address8", "+6589012345", "+6512434321", "Remark8");
        SupplierEntity s9 = new SupplierEntity("IJK", "Address9", "+8590123456", "+8584366321", "Remark9");
        SupplierEntity s10 = new SupplierEntity("JKL", "Address10", "+6501234567", "+658264321", "Remark10");
        
        em.persist(s1);em.persist(s2);em.persist(s3);em.persist(s4);em.persist(s5);
        em.persist(s6);em.persist(s7);em.persist(s8);em.persist(s9);em.persist(s10);
        
        
        
        // Contract
        Calendar cl1=Calendar.getInstance();  cl1.set(2010, 8, 25);
        Calendar cl2=Calendar.getInstance();  cl2.set(2011, 7, 31);
        Calendar cl3=Calendar.getInstance();  cl3.set(2012, 6, 23);
        Calendar cl4=Calendar.getInstance();  cl4.set(2013, 5, 15);
        Calendar cl5=Calendar.getInstance();  cl5.set(2014, 4, 19);
        Calendar cl6=Calendar.getInstance();  cl6.set(2020, 3, 20);
        Calendar cl7=Calendar.getInstance();  cl7.set(2021, 2, 11);

        Calendar cl8=Calendar.getInstance();  cl8.set(2020, 1, 22);
        Calendar cl9=Calendar.getInstance();  cl9.set(2023, 6, 6);
        Calendar cl10=Calendar.getInstance();  cl10.set(2022, 2, 12);
        Calendar cl11=Calendar.getInstance();  cl11.set(2023, 9, 12);
        
        // Contract.Factory1
        // Contract.Factory1.FactoryRawmaterial
        ContractEntity ct1 = new ContractEntity(100.0, 2, frm1.getUnit(), 10.0, cl1, cl7, frm1, s1);
        ContractEntity ct2 = new ContractEntity(200.0, 1, frm2.getUnit(), 1.0, cl2, cl8, frm2, s3);
        ContractEntity ct3 = new ContractEntity(10.0, 3, frm3.getUnit(), 1.0, cl3, cl6, frm3, s5);
        ContractEntity ct4 = new ContractEntity(500.0, 5, frm4.getUnit(), 5.0, cl4, cl9, frm4, s7);
        ContractEntity ct5 = new ContractEntity(1000.0, 3, frm5.getUnit(), 1.0, cl1, cl8, frm5, s9);
        
        //Shiyu. add contract and supplier info
        frm1.getContracts().add(ct1);
        frm2.getContracts().add(ct2);
        frm3.getContracts().add(ct3);
        frm4.getContracts().add(ct4);
        frm5.getContracts().add(ct5);
        //Shiyu. add contract and supplier info
        s1.getContractList().add(ct1);
        s3.getContractList().add(ct2);
        s5.getContractList().add(ct3);
        s7.getContractList().add(ct4);
        s9.getContractList().add(ct5);
        
        // Contract.Factory1.FactoryRetailProduct
        ContractEntity ct16 = new ContractEntity(100.0, 3, frpe1.getUnit(), 5.0, cl2, cl6, frpe1, s4);
        ContractEntity ct17 = new ContractEntity(400.0, 7, frpe4.getUnit(), 10.0, cl4, cl11, frpe4, s6);
        ContractEntity ct18 = new ContractEntity(1300.0, 2, frpe7.getUnit(), 5.0, cl5, cl9, frpe7, s8);
        
        //Shiyu. add contract and supplier info
        frpe1.getContracts().add(ct16);
        frpe4.getContracts().add(ct17);
        frpe7.getContracts().add(ct18);
        //Shiyu. add contract and supplier info
        s4.getContractList().add(ct16);
        s6.getContractList().add(ct17);
        s8.getContractList().add(ct18);
    
        
        // Contract.Factory2.FactoryRawmaterial
        ContractEntity ct6 = new ContractEntity(20.0, 5, frm6.getUnit(), 10.0, cl1, cl7, frm6, s2);
        ContractEntity ct7 = new ContractEntity(100.0, 3, frm7.getUnit(), 5.0, cl2, cl6, frm7, s4);
        ContractEntity ct8 = new ContractEntity(400.0, 7, frm8.getUnit(), 10.0, cl4, cl11, frm8, s6);
        ContractEntity ct9 = new ContractEntity(1300.0, 2, frm9.getUnit(), 5.0, cl5, cl9, frm9, s8);
        ContractEntity ct10 = new ContractEntity(700.0, 3, frm10.getUnit(), 10.0, cl5, c10, frm10, s10);
        
        //Shiyu. add contract and supplier info
        frm6.getContracts().add(ct6);
        frm7.getContracts().add(ct7);
        frm8.getContracts().add(ct8);
        frm9.getContracts().add(ct9);
        frm10.getContracts().add(ct10);
        //Shiyu. add contract and supplier info
        s2.getContractList().add(ct6);
        s4.getContractList().add(ct7);
        s6.getContractList().add(ct8);
        s8.getContractList().add(ct9);
        s10.getContractList().add(ct10);
        
        // Contract.Factory2.FactoryRetailProduct
        ContractEntity ct19 = new ContractEntity(100.0, 3, frpe2.getUnit(), 5.0, cl2, cl6, frpe2, s4);
        ContractEntity ct20 = new ContractEntity(200.0, 7, frpe5.getUnit(), 10.0, cl4, cl11, frpe5, s6);
        ContractEntity ct21 = new ContractEntity(20.0, 2, frpe8.getUnit(), 5.0, cl5, cl9, frpe8, s8);
        
        //Shiyu. add contract and supplier info
        frpe2.getContracts().add(ct19);
        frpe5.getContracts().add(ct20);
        frpe8.getContracts().add(ct21);
        //Shiyu. add contract and supplier info
        s4.getContractList().add(ct19);
        s6.getContractList().add(ct20);
        s8.getContractList().add(ct21);
        
        em.flush();
        
        // Contract.Factory3.FactoryRawmaterial
        ContractEntity ct11 = new ContractEntity(800.0, 2, frm11.getUnit(), 10.0, cl1, cl7, frm11, s1);
        ContractEntity ct12 = new ContractEntity(900.0, 1, frm12.getUnit(), 5.0, cl3, cl9, frm12, s2);
        ContractEntity ct13 = new ContractEntity(110.0, 2, frm13.getUnit(), 10.0, cl2, cl10, frm13, s5);
        ContractEntity ct14 = new ContractEntity(180.0, 5, frm14.getUnit(), 50.0, cl5, cl8, frm14, s6);
        ContractEntity ct15 = new ContractEntity(150.0, 3, frm15.getUnit(), 1.0, cl1, cl11, frm15, s9);
        // Contract.Factory3.FactoryRetailProduct
        ContractEntity ct22 = new ContractEntity(10.0, 3, frpe3.getUnit(), 5.0, cl2, cl6, frpe3, s4);
        ContractEntity ct23 = new ContractEntity(230.0, 7, frpe6.getUnit(), 10.0, cl4, cl11, frpe6, s6);
        ContractEntity ct24 = new ContractEntity(70.0, 2, frpe9.getUnit(), 5.0, cl5, cl9, frpe9, s8);

        em.persist(ct1);em.persist(ct2);em.persist(ct3);em.persist(ct4);em.persist(ct5);em.persist(ct6);
        em.persist(ct7);em.persist(ct8);em.persist(ct9);em.persist(ct10);em.persist(ct11);em.persist(ct12);
        em.persist(ct13);em.persist(ct14);em.persist(ct15);em.persist(ct16);em.persist(ct17);em.persist(ct18);
        em.persist(ct19);em.persist(ct20);em.persist(ct21);em.persist(ct22);em.persist(ct23);em.persist(ct24);
        
        
        // Factory Bin
        FactoryBinEntity b1 = new FactoryBinEntity();
        FactoryBinEntity b2 = new FactoryBinEntity();
        FactoryBinEntity b3 = new FactoryBinEntity();
        FactoryBinEntity b4 = new FactoryBinEntity();
        FactoryBinEntity b5 = new FactoryBinEntity();
        FactoryBinEntity b6 = new FactoryBinEntity();
        FactoryBinEntity b7 = new FactoryBinEntity();
        FactoryBinEntity b8 = new FactoryBinEntity();
        FactoryBinEntity b9 = new FactoryBinEntity();
        FactoryBinEntity b10 = new FactoryBinEntity();
        FactoryBinEntity b11 = new FactoryBinEntity();
        FactoryBinEntity b12 = new FactoryBinEntity();
        FactoryBinEntity b13 = new FactoryBinEntity();
        FactoryBinEntity b14 = new FactoryBinEntity();
        FactoryBinEntity b15 = new FactoryBinEntity();
        
        b1.setFactory(f1);b2.setFactory(f1);b3.setFactory(f1);b4.setFactory(f1);b5.setFactory(f1);
        b6.setFactory(f2);b7.setFactory(f2);b8.setFactory(f2);b9.setFactory(f2);b10.setFactory(f2);
        b11.setFactory(f3);b12.setFactory(f3);b13.setFactory(f3);b14.setFactory(f3);b15.setFactory(f3);
        
        

        // Factory Stored Product
        FactoryBinStoredProductEntity fbsp1 = new FactoryBinStoredProductEntity();em.persist(fbsp1);
        FactoryBinStoredProductEntity fbsp2 = new FactoryBinStoredProductEntity();em.persist(fbsp2);
        FactoryBinStoredProductEntity fbsp3 = new FactoryBinStoredProductEntity();em.persist(fbsp3);
        FactoryBinStoredProductEntity fbsp4 = new FactoryBinStoredProductEntity();em.persist(fbsp4);
        FactoryBinStoredProductEntity fbsp4_1 = new FactoryBinStoredProductEntity();em.persist(fbsp4_1);
        FactoryBinStoredProductEntity fbsp5 = new FactoryBinStoredProductEntity();em.persist(fbsp5);
        FactoryBinStoredProductEntity fbsp6 = new FactoryBinStoredProductEntity();em.persist(fbsp6);
        FactoryBinStoredProductEntity fbsp7 = new FactoryBinStoredProductEntity();em.persist(fbsp7);
        FactoryBinStoredProductEntity fbsp8 = new FactoryBinStoredProductEntity();em.persist(fbsp8);
        FactoryBinStoredProductEntity fbsp9 = new FactoryBinStoredProductEntity();em.persist(fbsp9);
        FactoryBinStoredProductEntity fbsp10 = new FactoryBinStoredProductEntity();em.persist(fbsp10);
        FactoryBinStoredProductEntity fbsp10_1 = new FactoryBinStoredProductEntity();em.persist(fbsp10_1);
        FactoryBinStoredProductEntity fbsp11 = new FactoryBinStoredProductEntity();em.persist(fbsp11);
        FactoryBinStoredProductEntity fbsp12 = new FactoryBinStoredProductEntity();em.persist(fbsp12);
        FactoryBinStoredProductEntity fbsp13 = new FactoryBinStoredProductEntity();em.persist(fbsp13);
        FactoryBinStoredProductEntity fbsp13_1 = new FactoryBinStoredProductEntity();em.persist(fbsp13_1);
        FactoryBinStoredProductEntity fbsp14 = new FactoryBinStoredProductEntity();em.persist(fbsp14);
        FactoryBinStoredProductEntity fbsp15 = new FactoryBinStoredProductEntity();em.persist(fbsp15);
        FactoryBinStoredProductEntity fbsp16 = new FactoryBinStoredProductEntity();em.persist(fbsp16);
        FactoryBinStoredProductEntity fbsp17 = new FactoryBinStoredProductEntity();em.persist(fbsp17);
        FactoryBinStoredProductEntity fbsp18 = new FactoryBinStoredProductEntity();em.persist(fbsp18);
        FactoryBinStoredProductEntity fbsp18_1 = new FactoryBinStoredProductEntity();em.persist(fbsp18_1);
        FactoryBinStoredProductEntity fbsp19 = new FactoryBinStoredProductEntity();em.persist(fbsp19);
        FactoryBinStoredProductEntity fbsp19_2 = new FactoryBinStoredProductEntity();em.persist(fbsp19_2);
        FactoryBinStoredProductEntity fbsp20 = new FactoryBinStoredProductEntity();em.persist(fbsp20);
        FactoryBinStoredProductEntity fbsp21 = new FactoryBinStoredProductEntity();em.persist(fbsp21);
        FactoryBinStoredProductEntity fbsp22 = new FactoryBinStoredProductEntity();em.persist(fbsp22);
        FactoryBinStoredProductEntity fbsp23 = new FactoryBinStoredProductEntity();em.persist(fbsp23);
        FactoryBinStoredProductEntity fbsp24 = new FactoryBinStoredProductEntity();em.persist(fbsp24);
        FactoryBinStoredProductEntity fbsp24_1 = new FactoryBinStoredProductEntity();em.persist(fbsp24_1);
        FactoryBinStoredProductEntity fbsp25 = new FactoryBinStoredProductEntity();em.persist(fbsp25);
        FactoryBinStoredProductEntity fbsp25_2 = new FactoryBinStoredProductEntity();em.persist(fbsp25_2);
        FactoryBinStoredProductEntity fbsp26 = new FactoryBinStoredProductEntity();em.persist(fbsp26);
        FactoryBinStoredProductEntity fbsp27 = new FactoryBinStoredProductEntity();em.persist(fbsp27);
        FactoryBinStoredProductEntity fbsp28 = new FactoryBinStoredProductEntity();em.persist(fbsp28);
        FactoryBinStoredProductEntity fbsp29 = new FactoryBinStoredProductEntity();em.persist(fbsp29);
        FactoryBinStoredProductEntity fbsp30 = new FactoryBinStoredProductEntity();em.persist(fbsp30);
        FactoryBinStoredProductEntity fbsp31 = new FactoryBinStoredProductEntity();em.persist(fbsp31);
        FactoryBinStoredProductEntity fbsp31_1 = new FactoryBinStoredProductEntity();em.persist(fbsp31_1);
        FactoryBinStoredProductEntity fbsp32 = new FactoryBinStoredProductEntity();em.persist(fbsp32);
        FactoryBinStoredProductEntity fbsp33 = new FactoryBinStoredProductEntity();em.persist(fbsp33);
        FactoryBinStoredProductEntity fbsp34 = new FactoryBinStoredProductEntity();em.persist(fbsp34);
        FactoryBinStoredProductEntity fbsp34_2 = new FactoryBinStoredProductEntity();em.persist(fbsp34_2);
        FactoryBinStoredProductEntity fbsp35 = new FactoryBinStoredProductEntity();em.persist(fbsp35);
        FactoryBinStoredProductEntity fbsp36 = new FactoryBinStoredProductEntity();em.persist(fbsp36);
        FactoryBinStoredProductEntity fbsp37 = new FactoryBinStoredProductEntity();em.persist(fbsp37);
        FactoryBinStoredProductEntity fbsp37_2 = new FactoryBinStoredProductEntity();em.persist(fbsp37_2);
        FactoryBinStoredProductEntity fbsp38 = new FactoryBinStoredProductEntity();em.persist(fbsp38);
        FactoryBinStoredProductEntity fbsp39 = new FactoryBinStoredProductEntity();em.persist(fbsp39);
        FactoryBinStoredProductEntity fbsp40 = new FactoryBinStoredProductEntity();em.persist(fbsp40);
        FactoryBinStoredProductEntity fbsp40_2 = new FactoryBinStoredProductEntity();em.persist(fbsp40_2);
        FactoryBinStoredProductEntity fbsp41 = new FactoryBinStoredProductEntity();em.persist(fbsp41);
        FactoryBinStoredProductEntity fbsp42 = new FactoryBinStoredProductEntity();em.persist(fbsp42);
        FactoryBinStoredProductEntity fbsp43 = new FactoryBinStoredProductEntity();em.persist(fbsp43);
        FactoryBinStoredProductEntity fbsp44 = new FactoryBinStoredProductEntity();em.persist(fbsp44);
        FactoryBinStoredProductEntity fbsp44_2 = new FactoryBinStoredProductEntity();em.persist(fbsp44_2);
        
        // Factory Stored Product. factory Raw Material
        fbsp1.createFactoryBinStoredProduct(frm1, b1, "unrestricted");fbsp1.setAmount(1000.0);
        frm1.setUnrestrictedInventory(1000.0);
        fbsp2.createFactoryBinStoredProduct(frm2, b2, "unrestricted");fbsp2.setAmount(1000.0);
        frm2.setUnrestrictedInventory(1000.0);
        fbsp3.createFactoryBinStoredProduct(frm3, b3, "unrestricted");fbsp3.setAmount(1000.0);
        frm3.setUnrestrictedInventory(1000.0);
        fbsp4.createFactoryBinStoredProduct(frm4, b4, "unrestricted");fbsp4.setAmount(1000.0);
        frm4.setUnrestrictedInventory(1000.0);
        fbsp4_1.createFactoryBinStoredProduct(frm4, b4, "blocked");fbsp4_1.setAmount(100.0);
        frm4.setBlockedInventory(100.0);
        fbsp5.createFactoryBinStoredProduct(frm5, b5, "unrestricted");fbsp5.setAmount(1000.0);
        frm5.setUnrestrictedInventory(1000.0);
        
        fbsp6.createFactoryBinStoredProduct(frm6, b6, "unrestricted");fbsp6.setAmount(1000.0);
        frm6.setUnrestrictedInventory(1000.0);
        fbsp7.createFactoryBinStoredProduct(frm7, b7, "unrestricted");fbsp7.setAmount(1000.0);
        frm7.setUnrestrictedInventory(1000.0);
        fbsp8.createFactoryBinStoredProduct(frm8, b8, "unrestricted");fbsp8.setAmount(1000.0);
        frm8.setUnrestrictedInventory(1000.0);
        fbsp9.createFactoryBinStoredProduct(frm9, b9, "unrestricted");fbsp9.setAmount(1000.0);
        frm9.setUnrestrictedInventory(1000.0);
        fbsp10.createFactoryBinStoredProduct(frm10, b10, "unrestricted");fbsp10.setAmount(1000.0);
        frm10.setUnrestrictedInventory(1000.0);
        fbsp10_1.createFactoryBinStoredProduct(frm10, b10, "blocked");fbsp10_1.setAmount(10.0);
        frm10.setBlockedInventory(10.0);
        
        fbsp11.createFactoryBinStoredProduct(frm11, b11, "unrestricted");fbsp11.setAmount(1000.0);
        frm11.setUnrestrictedInventory(1000.0);
        fbsp12.createFactoryBinStoredProduct(frm12, b12, "unrestricted");fbsp12.setAmount(1000.0);
        frm12.setUnrestrictedInventory(1000.0);
        fbsp13.createFactoryBinStoredProduct(frm13, b13, "unrestricted");fbsp13.setAmount(1000.0);
        frm13.setUnrestrictedInventory(1000.0);
        fbsp13_1.createFactoryBinStoredProduct(frm13, b13, "blocked");fbsp13_1.setAmount(50.0);
        frm13.setBlockedInventory(50.0);
        fbsp14.createFactoryBinStoredProduct(frm14, b14, "unrestricted");fbsp14.setAmount(1000.0);
        frm14.setUnrestrictedInventory(1000.0);
        fbsp15.createFactoryBinStoredProduct(frm15, b15, "unrestricted");fbsp15.setAmount(1000.0);
        frm15.setUnrestrictedInventory(1000.0);
        
        // Factory Stored Product. factory Product
        fbsp16.createFactoryBinStoredProduct(fp1, b1, "unrestricted");fbsp16.setAmount(500.0);
        fp1.setUnrestrictedInventory(500.0);
        fbsp17.createFactoryBinStoredProduct(fp2, b2, "unrestricted");fbsp17.setAmount(500.0);
        fp2.setUnrestrictedInventory(500.0);
        fbsp18.createFactoryBinStoredProduct(fp3, b3, "unrestricted");fbsp18.setAmount(500.0);
        fp3.setUnrestrictedInventory(500.0);
        fbsp18_1.createFactoryBinStoredProduct(fp3, b4, "blocked");fbsp18_1.setAmount(50.0);
        fp3.setBlockedInventory(50.0);
        fbsp19.createFactoryBinStoredProduct(fp4, b5, "unrestricted");fbsp19.setAmount(500.0);
        fp5.setUnrestrictedInventory(500.0);
        fbsp19_2.createFactoryBinStoredProduct(fp4, b4, "returned");fbsp19_2.setAmount(10.0);
        fp4.setReturnedInventory(10.0);
        fbsp20.createFactoryBinStoredProduct(fp5, b3, "unrestricted");fbsp20.setAmount(500.0);
        fp5.setUnrestrictedInventory(500.0);
        fbsp21.createFactoryBinStoredProduct(fp6, b2, "unrestricted");fbsp21.setAmount(500.0);
        fp6.setUnrestrictedInventory(500.0);
        
        fbsp22.createFactoryBinStoredProduct(fp7, b6, "unrestricted");fbsp22.setAmount(500.0);
        fp7.setUnrestrictedInventory(500.0);
        fbsp23.createFactoryBinStoredProduct(fp8, b7, "unrestricted");fbsp23.setAmount(500.0);
        fp8.setUnrestrictedInventory(500.0);
        fbsp24.createFactoryBinStoredProduct(fp9, b8, "unrestricted");fbsp24.setAmount(500.0);
        fp9.setUnrestrictedInventory(500.0);
        fbsp24_1.createFactoryBinStoredProduct(fp9, b9, "blocked");fbsp24_1.setAmount(50.0);
        fp9.setBlockedInventory(50.0);
        fbsp25.createFactoryBinStoredProduct(fp10, b10, "unrestricted");fbsp25.setAmount(500.0);
        fp10.setUnrestrictedInventory(500.0);
        fbsp25_2.createFactoryBinStoredProduct(fp10, b9, "returned");fbsp25_2.setAmount(1.0);
        fp10.setReturnedInventory(1.0);
        fbsp26.createFactoryBinStoredProduct(fp11, b8, "unrestricted");fbsp26.setAmount(500.0);
        fp11.setUnrestrictedInventory(500.0);
        fbsp27.createFactoryBinStoredProduct(fp12, b7, "unrestricted");fbsp27.setAmount(500.0);
        fp12.setUnrestrictedInventory(500.0);
        
        fbsp28.createFactoryBinStoredProduct(fp13, b11, "unrestricted");fbsp28.setAmount(500.0);
        fp13.setUnrestrictedInventory(500.0);
        fbsp29.createFactoryBinStoredProduct(fp14, b12, "unrestricted");fbsp29.setAmount(500.0);
        fp14.setUnrestrictedInventory(500.0);
        fbsp30.createFactoryBinStoredProduct(fp15, b13, "unrestricted");fbsp30.setAmount(500.0);
        fp15.setUnrestrictedInventory(500.0);
        fbsp31.createFactoryBinStoredProduct(fp16, b14, "unrestricted");fbsp31.setAmount(500.0);
        fp16.setUnrestrictedInventory(500.0);
        fbsp31_1.createFactoryBinStoredProduct(fp16, b15, "blocked");fbsp31_1.setAmount(50.0);
        fp16.setBlockedInventory(50.0);
        fbsp32.createFactoryBinStoredProduct(fp17, b14, "unrestricted");fbsp32.setAmount(500.0);
        fp17.setUnrestrictedInventory(500.0);
        fbsp33.createFactoryBinStoredProduct(fp18, b13, "unrestricted");fbsp33.setAmount(500.0);
        fp18.setUnrestrictedInventory(500.0);
        fbsp34.createFactoryBinStoredProduct(fp19, b12, "unrestricted");fbsp34.setAmount(500.0);
        fp19.setUnrestrictedInventory(500.0);
        fbsp34_2.createFactoryBinStoredProduct(fp19, b11, "returned");fbsp34_2.setAmount(4.0);
        fp19.setReturnedInventory(4.0);
        fbsp35.createFactoryBinStoredProduct(fp20, b12, "unrestricted");fbsp35.setAmount(500.0);
        fp20.setUnrestrictedInventory(500.0);

        
        // Factory Stored Product. factory Retail product
        fbsp36.createFactoryBinStoredProduct(frpe1, b1, "unrestricted");fbsp36.setAmount(300.0);
        frpe1.setUnrestrictedInventory(300.0);
        fbsp37.createFactoryBinStoredProduct(frpe4, b2, "unrestricted");fbsp37.setAmount(300.0);
        frpe4.setUnrestrictedInventory(300.0);
        fbsp37_2.createFactoryBinStoredProduct(frpe4, b3, "returned");fbsp37_2.setAmount(3.0);
        frpe5.setReturnedInventory(300.0);
        fbsp38.createFactoryBinStoredProduct(frpe7, b4, "unrestricted");fbsp38.setAmount(300.0);
        frpe7.setUnrestrictedInventory(300.0);
        
        fbsp39.createFactoryBinStoredProduct(frpe2, b6, "unrestricted");fbsp39.setAmount(300.0);
        frpe2.setUnrestrictedInventory(300.0);
        fbsp40.createFactoryBinStoredProduct(frpe5, b7, "unrestricted");fbsp40.setAmount(300.0);
        frpe5.setUnrestrictedInventory(300.0);
        fbsp40_2.createFactoryBinStoredProduct(frpe5, b8, "returned");fbsp40_2.setAmount(1.0);
        frpe5.setReturnedInventory(300.0);
        fbsp41.createFactoryBinStoredProduct(frpe8, b9, "unrestricted");fbsp41.setAmount(300.0);
        frpe8.setUnrestrictedInventory(300.0);
        
        fbsp42.createFactoryBinStoredProduct(frpe3, b11, "unrestricted");fbsp42.setAmount(300.0);
        frpe3.setUnrestrictedInventory(300.0);
        fbsp43.createFactoryBinStoredProduct(frpe6, b12, "unrestricted");fbsp43.setAmount(300.0);
        frpe6.setUnrestrictedInventory(300.0);
        fbsp44.createFactoryBinStoredProduct(frpe9, b13, "unrestricted");fbsp44.setAmount(300.0);
        frpe9.setUnrestrictedInventory(300.0);
        fbsp44_2.createFactoryBinStoredProduct(frpe9, b14, "returned");fbsp44_2.setAmount(4.0);
        frpe9.setReturnedInventory(300.0);
        
        em.flush();
        
        // Outbound Movement
        OutboundMovementEntity obm1 = new OutboundMovementEntity();em.persist(obm1);
        OutboundMovementEntity obm2 = new OutboundMovementEntity();em.persist(obm2);
        OutboundMovementEntity obm3 = new OutboundMovementEntity();em.persist(obm3);
        OutboundMovementEntity obm4 = new OutboundMovementEntity();em.persist(obm4);
        OutboundMovementEntity obm5 = new OutboundMovementEntity();em.persist(obm5);
        OutboundMovementEntity obm6 = new OutboundMovementEntity();em.persist(obm6);
        OutboundMovementEntity obm7 = new OutboundMovementEntity();em.persist(obm7);
        OutboundMovementEntity obm8 = new OutboundMovementEntity();em.persist(obm8);
        OutboundMovementEntity obm9 = new OutboundMovementEntity();em.persist(obm9);
        
        obm1.recordFactoryProductOutboundMovement(fbsp17, store1, 20.0, c1);
        obm2.recordFactoryProductOutboundMovement(fbsp20, store2, 50, c3);
        obm3.recordFactoryRetailProductOutboundMovement(fbsp37, store3, 30, c5);
        
        obm4.recordFactoryProductOutboundMovement(fbsp23, store1, 20.0, c3);
        obm5.recordFactoryProductOutboundMovement(fbsp26, store2, 50, c4);
        obm6.recordFactoryRetailProductOutboundMovement(fbsp39, store3, 30, c5);
        
        obm7.recordFactoryProductOutboundMovement(fbsp30, store1, 20.0, c1);
        obm8.recordFactoryProductOutboundMovement(fbsp33, store2, 50, c3);
        obm9.recordFactoryRetailProductOutboundMovement(fbsp42, store3, 30, c5);
        
        // In-Factory Movement
        InFactoryMovementEntity ifm1 = new InFactoryMovementEntity();em.persist(ifm1);
        InFactoryMovementEntity ifm2 = new InFactoryMovementEntity();em.persist(ifm2);
        InFactoryMovementEntity ifm3 = new InFactoryMovementEntity();em.persist(ifm3);
        
        ifm1.recordInFactoryRawMaterialMovement(fbsp3, fbsp44_2, 100.0, c11);
        ifm2.recordInFactoryProductMovement(fbsp22, fbsp44_2, 100.0, c5);
        ifm3.recordInFactoryRetailProductMovement(fbsp43, fbsp44_2, 100.0, c3);
        
        em.flush();

        
        //Shiyu Purchase Order
        PurchaseOrderEntity purchaseOrder1;
      
        purchaseOrder1 = new PurchaseOrderEntity("unconfirmed" , 1D , "unit", Calendar.getInstance(),
                "factory", 1L, 1, 1D, f1, ct1, Calendar.getInstance());
        em.persist(purchaseOrder1);
        f1.getPurchaseOrders().add(purchaseOrder1);
        em.flush();
        PurchaseOrderEntity purchaseOrder2;
        purchaseOrder2 = new PurchaseOrderEntity("confirmed" , 2D , "unit", Calendar.getInstance(),
                "factory", 2L, 2, 2D, f1, ct1, Calendar.getInstance());
        em.persist(purchaseOrder2);
        f1.getPurchaseOrders().add(purchaseOrder2);
        em.flush(); 
        
        //Shiyu Inventory Record 
        Calendar irCal = Calendar.getInstance();
        irCal.set(2014, 8, 1);
        InventoryRecordEntity ir1 = new InventoryRecordEntity(irCal, 10D, frm1, null, null);
        frm1.getInventoryRecord().add(ir1);
        
        Calendar irCal2 = Calendar.getInstance();
        irCal2.set(2014, 7, 1);
        InventoryRecordEntity ir2 = new InventoryRecordEntity(irCal2, 10D, frm1, null, null);
        frm1.getInventoryRecord().add(ir2);
        
    }
}
