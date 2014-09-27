    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

import Entity.CommonInfrastructure.IdNumberEntity;
import Entity.Factory.FactoryEntity;
import Entity.Factory.FactoryProductEntity;
import Entity.Factory.FactoryRawMaterialAmountEntity;
import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.MRP.PlannedOrderEntity;
import Entity.Factory.MRP.ProductionPlanEntity;
import Entity.Factory.ProductEntity;
import Entity.Factory.RawMaterialEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    public void init(){
        createDatabase();
    }
    
    public void createDatabase(){
        //Set up idNumberEntity
        
      
    
        Query q = em.createQuery("SELECT idno FROM IdNumberEntity idno");
        List<IdNumberEntity> IdNumberList = new ArrayList();
        for(Object o : q.getResultList()){
            IdNumberEntity idno = (IdNumberEntity) o;
                IdNumberList.add(idno);
        }  
        
        if(IdNumberList.isEmpty()){
            IdNumberEntity id = new IdNumberEntity();
            id.setId_F(1000000L);
            id.setId_H(1000000L);
            id.setId_S(1000000L);
            em.persist(id);
            em.flush();
        }
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
        Calendar c11=Calendar.getInstance();  c11.set(2014, 9, 12);
        
        pp1.setGeneratedDate(c1);pp2.setGeneratedDate(c2);pp3.setGeneratedDate(c3);pp4.setGeneratedDate(c4);pp5.setGeneratedDate(c5);
        pp6.setGeneratedDate(c6);pp7.setGeneratedDate(c7);pp8.setGeneratedDate(c8);pp9.setGeneratedDate(c9);pp10.setGeneratedDate(c10);
        pp11.setGeneratedDate(c1);pp12.setGeneratedDate(c2);pp13.setGeneratedDate(c3);pp14.setGeneratedDate(c4);pp15.setGeneratedDate(c5);
        pp16.setGeneratedDate(c6);pp17.setGeneratedDate(c7);pp18.setGeneratedDate(c8);pp19.setGeneratedDate(c9);pp20.setGeneratedDate(c10);
        
        pp1.setTargetPeriod(c11);pp2.setTargetPeriod(c11);pp3.setTargetPeriod(c11);pp4.setTargetPeriod(c11);pp5.setTargetPeriod(c11);
        pp6.setTargetPeriod(c11);pp7.setTargetPeriod(c11);pp8.setTargetPeriod(c11);pp9.setTargetPeriod(c11);pp10.setTargetPeriod(c11);
        pp11.setTargetPeriod(c11);pp12.setTargetPeriod(c11);pp13.setTargetPeriod(c11);pp14.setTargetPeriod(c11);pp15.setTargetPeriod(c11);
        pp16.setTargetPeriod(c11);pp17.setTargetPeriod(c11);pp18.setTargetPeriod(c11);pp19.setTargetPeriod(c11);pp20.setTargetPeriod(c11);
        
        pp1.setProduct(fp1);pp2.setProduct(fp2);pp3.setProduct(fp3);pp4.setProduct(fp4);pp5.setProduct(fp5);
        pp6.setProduct(fp6);pp7.setProduct(fp7);pp8.setProduct(fp8);pp9.setProduct(fp9);pp10.setProduct(fp10);
        pp11.setProduct(fp11);pp12.setProduct(fp12);pp13.setProduct(fp13);pp14.setProduct(fp14);pp15.setProduct(fp15);
        pp16.setProduct(fp16);pp17.setProduct(fp17);pp18.setProduct(fp18);pp1.setProduct(fp19);pp20.setProduct(fp20);
        
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
        
        rm1.setMaterialName("board");rm1.setDescription("hehe");rm1.setUnit("piece");rm1.setDeleteFlag(Boolean.FALSE);
        rm2.setMaterialName("metal");rm2.setDescription("hehe");rm2.setUnit("piece");rm2.setDeleteFlag(Boolean.FALSE);
        rm3.setMaterialName("nail");rm3.setDescription("hehe");rm3.setUnit("box");rm3.setDeleteFlag(Boolean.FALSE);
        rm4.setMaterialName("nut");rm4.setDescription("hehe");rm4.setUnit("box");rm4.setDeleteFlag(Boolean.FALSE);
        rm5.setMaterialName("stick");rm5.setDescription("hehe");rm5.setUnit("box");rm5.setDeleteFlag(Boolean.FALSE);
        
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

        
    }
}
