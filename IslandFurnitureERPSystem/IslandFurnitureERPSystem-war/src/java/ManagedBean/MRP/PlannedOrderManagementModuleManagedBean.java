/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.MRP;

//import Entity.Factory.MRP.ProductionPlanEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author apple
 */
//@Named(value = "plannedOrderManagementModule")
@Dependent
    @ManagedBean
    @ViewScoped
    public class PlannedOrderManagementModuleManagedBean implements Serializable {
         @PersistenceContext
        private EntityManager em;
     //   private List<ProductionPlanEntity> productionPlan;
        private List<String> test;
        
        public List<String> getProductionPlanEntity() {
            
//             Query query = em.createQuery("SELECT t FROM ProductionPlanEntity t ORDER BY t.generateDate DESC");
//             productionPlan = (List<ProductionPlanEntity>) query.getResultList();
//            return productionPlan;
            test.add("aaaaaaa");
            test.add("bbbbbbb");
            test.add("cccccc");
            test.add("dddddd");
            test.add("eeeeee");
            test.add("fffffff");
            test.add("ggggggg");
            System.out.println("aaaaaadsf");
            return test;
        }
       
 
    }
