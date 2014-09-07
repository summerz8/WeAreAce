/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.MRP;

import Entity.CommonInfrastructure.IdNumberEntity;
import Entity.CommonInfrastructure.UserEntity;
import Entity.Factory.BOMEntity;
import Entity.Factory.MRP.PlannedOrderEntity;
import Entity.Factory.ProductEntity;
import Entity.Factory.RawMaterialAmountEntity;
import Entity.Factory.RawMaterialEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author apple
 */
@Stateful
public class PlannedOrderManagementModule implements PlannedOrderManagementModuleRemote {
    
        @PersistenceContext
        private EntityManager em;
        
        public PlannedOrderManagementModule() {
    }
        public BOMEntity CheckBOM(Long ProductID) {
            Long productID=ProductID;
            ProductEntity product = em.find(ProductEntity.class, productID);
            return product.getBom();
    }
       
        //Click the CreatePlannedOrder button when viewing production plan list
        public void CreatePlannedOrder(Long productID, Integer amount){
            
               
        }
        
        //Modify the data in the CreatePlannedOrder page//
        public PlannedOrderEntity GeneratePlannedOrder(Date dateInput,Date targetStartInput,Date targetEndInput,String statusInput,Long productIdInput,Integer AmountInput, List<Long> rawMaterialList, List<Integer> RawAmount, List<String> Unit){
            
            //Create RawMaterialAmount Entity for each material in the plannedOrder//
            
            Date date=dateInput;
            Date targetStart=targetStartInput;
            Date targetEnd=targetEndInput;
            String status=statusInput;
            Long productId=productIdInput;
            List<RawMaterialAmountEntity> MaterialList=new ArrayList<RawMaterialAmountEntity>();
            
            Long MaterialId;
            String unit;
            Integer amount;
            
            while (!MaterialList.isEmpty()) {
            MaterialId=rawMaterialList.get(0);
            unit=Unit.get(0);
            amount=RawAmount.get(0);
            String[] data = thisDay.split("-");
            if (data[0].equals(year + "") && data[1].equals(month + "")) {
                result.add("Transaction ID: " + list.get(0).getId());
                result.add("Transaction Date: " + df.format(list.get(0).getDate().getTime()));
                result.add("Transaction Total Price: " + list.get(0).getPrice() + "$\n");
                sales += list.get(0).getPrice();
                amount++;
            }
            MaterialList.remove(0);
        }
            
            
            
            PlannedOrderEntity plannedOrder=new PlannedOrderEntity();
            
            plannedOrder. 
    
            return 
        }

    @Override
    public void GeneratePlannedOrder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
}
