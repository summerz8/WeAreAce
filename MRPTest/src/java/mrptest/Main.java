/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mrptest;

import Entity.Factory.FactoryRawMaterialAmountEntity;
import Entity.Factory.MRP.PlannedOrderEntity;
import Entity.Factory.MRP.ProductionPlanEntity;
import SessionBean.MRP.PlannedOrderManagementModuleLocal;
import SessionBean.MRP.ProductionPlanManagementModuleLocal;
import SessionBean.MRP.RetailProductPurchasePlanModuleLocal;
import SessionBean.MRP.SalesForecastModuleLocal;
import SessionBean.MRP.SalesOperationPlanLocal;
import SessionBean.MRP.WeeklyProductionPlanLocal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;

/**
 *
 * @author hangsun
 */
public class Main {
    
    @EJB
    private static PlannedOrderManagementModuleLocal PlannedOrder; 
    private static ProductionPlanManagementModuleLocal ProductionPlan;
    private static RetailProductPurchasePlanModuleLocal RetailProductPurchasePlan;
    private static SalesForecastModuleLocal SalesForecast;
    private static SalesOperationPlanLocal SalesOperationPlan;
    private static WeeklyProductionPlanLocal WeeklyProductionPlan;
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Main MRPTest = new Main();
        MRPTest.displayMenu();
        MRPTest.doFunctions();
    }
    
    private void displayMenu(){
        System.out.println("Choose the action number below if you want to perform an action");
        System.out.println("A1. Create Planned Order");
        System.out.println("B1. Create Production Plan");
        System.out.println("C1. Create Retail Product Purchase Plan");
        System.out.println("D1. Create Sales Forecast");
        System.out.println("E1. Create Sales Operation");
        System.out.println("F1. Create Weekly Production Plan");
        
        System.out.println("A2. Edit Planned Order");
        System.out.println("B2. Edit Production Plan");
        System.out.println("C2. Edit Retail Product Purchase Plan");
        System.out.println("D2. Edit Sales Forecast");
        System.out.println("E2. Edit Sales Operation");
        System.out.println("F2. Edit Weekly Production Plan");
        
     
      System.out.println("\n\nPlease select <'Q' or 'q' to exit>:");
    
    }
    
    private void doFunctions(){
        Scanner sc = new Scanner(System.in);
        Main client = new Main();

        String choice = sc.nextLine();

        while (!(choice.equals("Q") || choice.equals("q"))) {
            switch (choice) {
                case "A1":
                    createPlannedOrder(sc);
                    break;
                case "A2":
                    editPlannedOrder(sc);
                    break;
                case "B1":
                    createProductionPlan(sc);
                    break;
                case "B2":
                    editProductionPlan(sc);
                    break;
                case "C1":
                    createRetailProductPurchasePlan(sc);
                    break;
                case "C2":
                    editRetailProductPurchasePlan(sc);
                    break;
                case "D1":
                    createSalesForecast(sc);
                    break;
                case "D2":
                    editSalesForecast(sc);
                    break;
                case "E1":
                    createSalesOperation(sc);
                    break;
                case "E2":
                    editSalesOperation(sc);
                    break;
                case "F1":
                    createWeeklyProductionPlan(sc);
                    break;
                case "F2":
                    editWeeklyProductionPlan(sc);
                    break;
            }
            
            client.displayMenu();
            choice = sc.nextLine();
        }
        
        System.out.println("Exiting...");
    }
    
    private void createPlannedOrder(Scanner sc){
        System.out.println("Please enter Product ID:");
        String ProductId = sc.nextLine();
        System.out.println("Please enter amount:");
        String amount = sc.nextLine();
        PlannedOrder.CreatePlannedOrder(Long.parseLong(ProductId), Double.parseDouble(amount));
    
    }
    private void createProductionPlan(Scanner sc){
        try {
            System.out.println("Please enter Target Period:");
            String targetPeriod = sc.nextLine();
            System.out.println("Please enter output:");
            String output = sc.nextLine();
            System.out.println("Please enter product ID:");
            String productId = sc.nextLine();
            System.out.println("Please enter remark:");
            String remark = sc.nextLine();
            
            DateFormat df = new SimpleDateFormat("MM/yyyy");
            DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
            
            Calendar TargetPeriod  = Calendar.getInstance();
            TargetPeriod.setTime(df.parse(targetPeriod));
            
            Calendar GenerateDate = Calendar.getInstance();
            df2.format(GenerateDate.getTime());
            
            ProductionPlan.generateProductionPlan("unconfirmed", GenerateDate, TargetPeriod, Double.parseDouble(output), Long.parseLong(productId), remark);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void createRetailProductPurchasePlan(Scanner sc){
        try {
            System.out.println("Please enter Target Period:");
            String targetPeriod = sc.nextLine();
            System.out.println("Please enter output:");
            String output = sc.nextLine();
            System.out.println("Please enter product ID:");
            String productId = sc.nextLine();
            System.out.println("Please enter remark:");
            String remark = sc.nextLine();
            
            DateFormat df = new SimpleDateFormat("MM/yyyy");
            DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
            
            Calendar TargetPeriod  = Calendar.getInstance();
            TargetPeriod.setTime(df.parse(targetPeriod));
            
            Calendar GenerateDate = Calendar.getInstance();
            df2.format(GenerateDate.getTime());
            
            //RetailProductPurchasePlan.generateRetailProductPurchasePlan("unconfirmed", GenerateDate, TargetPeriod, Double.parseDouble(output), Long.parseLong(productId), remark);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void createSalesForecast(Scanner sc){
    
    }
    private void createSalesOperation(Scanner sc){
    
    }
    private void createWeeklyProductionPlan(Scanner sc){
    
    }
    
    private void editPlannedOrder(Scanner sc){
        viewPlannedOrder();
    
    }
    private void editProductionPlan(Scanner sc){
        viewProductionPlan();
    
    }
    private void editRetailProductPurchasePlan(Scanner sc){
        viewRetailProductPurchasePlan();
    
    }
    private void editSalesForecast(Scanner sc){
        viewSalesForecast();
    
    }
    private void editSalesOperation(Scanner sc){
        viewSalesOperation();
    
    }
    private void editWeeklyProductionPlan(Scanner sc){
        viewWeeklyProductionPlan();
    
    }
    
    private void viewPlannedOrder(){
        List<PlannedOrderEntity> plannedOrder = PlannedOrder.getPlannedOrder();
        int i =0;
        while(plannedOrder.get(i)!= null){
            System.out.println("Planned Order Id: " + plannedOrder.get(i).getPlannedOrderId());
            System.out.println("Status: "+ plannedOrder.get(i).getStatus());
            System.out.println("Generate Date: " + plannedOrder.get(i).getGeneratedDate());
            System.out.println("Target Period Date: " + plannedOrder.get(i).getTargetPeriod());
            System.out.println("Productin Plan Id: " + plannedOrder.get(i).getProductionPlan().getProductionPlanId());
            
            System.out.println("Factory Retail Product Amount Id: " + plannedOrder.get(i).getFactoryRetailProductAmount().getFactoryRetailProductAmountId());
            System.out.println("Unit: " + plannedOrder.get(i).getFactoryRetailProductAmount().getUnit());
            System.out.println("Amount: " + plannedOrder.get(i).getFactoryRetailProductAmount().getAmount());
            
            List<FactoryRawMaterialAmountEntity> factoryRawMaterialAmount = plannedOrder.get(i).getFactoryRawMaterialAmountList();
            int j =0;
            while(factoryRawMaterialAmount.get(j) != null){
                System.out.println("Raw Material Id: " + factoryRawMaterialAmount.get(j).getRawMaterialAmountId());
                System.out.println("Unit: " + factoryRawMaterialAmount.get(j).getUnit());
                System.out.println("Amount: " + factoryRawMaterialAmount.get(j).getAmount());
                j++;
            }
            i++;
        }
    }
    private void viewProductionPlan(){
        List<ProductionPlanEntity> productionPlan = ProductionPlan.getProductionPlanUnconfirmed();
        int i =0;
        while(productionPlan.get(i)!= null){
            System.out.println("Production Plan Id: " + productionPlan.get(i).getProductionPlanId());
            System.out.println("Status: "+ productionPlan.get(i).getStatus());
            System.out.println("Generate Date: " + productionPlan.get(i).getGeneratedDate());
            System.out.println("Target Period Date: " + productionPlan.get(i).getTargetPeriod());
            System.out.println("Factory Product Id: " + productionPlan.get(i).getFactoryProduct().getFactoryProductId());
            System.out.println("Quantity: " + productionPlan.get(i).getQuantity());
            if(productionPlan.get(i).getStatus().equals("confirmed")){
                System.out.println("ConfirmDate: " + productionPlan.get(i).getConfirmDate());
            }
            System.out.println("Remark: " + productionPlan.get(i).getRemark());
            i++;
        }
        
    }
    private void viewRetailProductPurchasePlan(){
        
    }
    private void viewSalesForecast(){
    
    }
    private void viewSalesOperation(){
    
    }
    private void viewWeeklyProductionPlan(){
    
    }
    
    
}
