/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.KM.DailyDemandForecastingModule;

import Entity.Kitchen.ComboEntity;
import Entity.Kitchen.ComboItemEntity;
import Entity.Kitchen.DishEntity;
import Entity.Kitchen.DishItemEntity;
import Entity.Kitchen.IngredientForecastEntity;
import Entity.Kitchen.KitchenEntity;
import Entity.Kitchen.MenuItemForecastEntity;
import SessionBean.KM.DailyDemandForecastingModuleLocal;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author Yoky
 */
@ManagedBean(name = "menuItemForecastBean")
@ViewScoped
public class MenuItemForecastBean implements Serializable {

    @EJB
    private DailyDemandForecastingModuleLocal ddf;

    private KitchenEntity kitchen;
    private Date selectedTargetDate;
    private MenuItemForecastEntity selectedMif;
    private List<DishItemEntity> filteredDishItems;
    private List<ComboItemEntity> filteredComboItems;
    private String message;
//    private DishItemEntity dishItem;
//    private ComboItemEntity comboItem;
    private HashMap<Long, CartesianChartModel> dishModels = new HashMap<>();
    private HashMap<Long, CartesianChartModel> comboModels = new HashMap<>();
    private CartesianChartModel selectedModel;
    private Calendar cal;

    public MenuItemForecastBean() {
    }

    public KitchenEntity getKitchen() {
        return kitchen;
    }

    public void setKitchen(KitchenEntity kitchen) {
        this.kitchen = kitchen;
    }

    public Date getSelectedTargetDate() {
        return selectedTargetDate;
    }

    public void setSelectedTargetDate(Date selectedTargetDate) {
        this.selectedTargetDate = selectedTargetDate;
    }

    public MenuItemForecastEntity getSelectedMif() {
        return selectedMif;
    }

    public void setSelectedMif(MenuItemForecastEntity selectedMif) {
        this.selectedMif = selectedMif;
    }

    public List<DishItemEntity> getFilteredDishItems() {
        return filteredDishItems;
    }

    public void setFilteredDishItems(List<DishItemEntity> filteredDishItems) {
        this.filteredDishItems = filteredDishItems;
    }

    public List<ComboItemEntity> getFilteredComboItems() {
        return filteredComboItems;
    }

    public void setFilteredComboItems(List<ComboItemEntity> filteredComboItems) {
        this.filteredComboItems = filteredComboItems;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HashMap<Long, CartesianChartModel> getDishModels() {
        return dishModels;
    }

    public void setDishModels(HashMap<Long, CartesianChartModel> dishModels) {
        this.dishModels = dishModels;
    }

    public HashMap<Long, CartesianChartModel> getComboModels() {
        return comboModels;
    }

    public void setComboModels(HashMap<Long, CartesianChartModel> comboModels) {
        this.comboModels = comboModels;
    }

    public CartesianChartModel getSelectedModel() {
        return selectedModel;
    }

    public void setSelectedModel(CartesianChartModel selectedModel) {
        this.selectedModel = selectedModel;
    }

    public Calendar getCal() {
        return cal;
    }

    public void setCal(Calendar cal) {
        this.cal = cal;
    }

//    public DishItemEntity getDishItem() {
//        return dishItem;
//    }
//
//    public void setDishItem(DishItemEntity dishItem) {
//        this.dishItem = dishItem;
//    }
//
//    public ComboItemEntity getComboItem() {
//        return comboItem;
//    }
//
//    public void setComboItem(ComboItemEntity comboItem) {
//        this.comboItem = comboItem;
//    }
//
//    public LineChartModel getDishModel() {
//        return dishModel;
//    }
//
//    public void setDishModel(LineChartModel dishModel) {
//        this.dishModel = dishModel;
//    }
//
//    public LineChartModel getComboModel() {
//        return comboModel;
//    }
//
//    public void setComboModel(LineChartModel comboModel) {
//        this.comboModel = comboModel;
//    }
    @PostConstruct
    public void init() {
        try {
            kitchen = (KitchenEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("kitchen");
            cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, 2);
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.MenuManagementModule.MenuItemForecastBean: init(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }

    public void findRequiredMenuItemForecast(ActionEvent event) {
        try {
            selectedMif = ddf.findMenuItemForecast(kitchen.getId(), selectedTargetDate);
            if (selectedMif == null) {
                message = "The Menu Item Forecast for the selected target date is not generated yet";
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('message').show();");
            } else {
//                createModels();
                filteredDishItems = ddf.getDishForecastItems(selectedMif.getId());
                filteredComboItems = ddf.getComboForecastItems(selectedMif.getId());
            }

        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.MenuManagementModule.MenuItemForecastBean: findRequiredMenuItemForecast(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        filteredDishItems = ddf.getDishForecastItems(selectedMif.getId());
        filteredComboItems = ddf.getComboForecastItems(selectedMif.getId());
    }

    public void onRowEditDish(RowEditEvent event) {
        try {
            DishItemEntity di = (DishItemEntity) event.getObject();
            if (di.getQuantity() < 0) {
                FacesMessage msg = new FacesMessage("Edition Faild", "Quantity cannot be negative");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            } else {
                Long diId = ddf.editDishForecastItem(di.getId(), di.getQuantity());
                if (diId == -1L) {
                    FacesMessage msg = new FacesMessage("Edition Faild", "Unexpected Exception Occurred");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                } else {
                    FacesMessage msg = new FacesMessage("Successful", "Dish Forecast of " + di.getDish().getName() + " is Edited");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            }
            filteredDishItems = ddf.getDishForecastItems(selectedMif.getId());
        } catch (Exception ex) {
            FacesMessage msg = new FacesMessage("Edition Faild", "Unexpected Exception Occurred");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            System.err.println("ManagedBean.KM.MenuManagementModule.MenuItemForecastBean: onRowEditDish(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }

    public void onRowEditCombo(RowEditEvent event) {
        try {
            ComboItemEntity ci = (ComboItemEntity) event.getObject();
            if (ci.getQuantity() < 0) {
                FacesMessage msg = new FacesMessage("Edition Faild", "Quantity cannot be negative");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            } else {
                Long ciId = ddf.editComboForecastItem(ci.getId(), ci.getQuantity());
                if (ciId == -1L) {
                    FacesMessage msg = new FacesMessage("Edition Faild", "Unexpected Exception Occurred");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                } else {
                    FacesMessage msg = new FacesMessage("Successful", "Combo Forecast of " + ci.getCombo().getName() + " is Edited");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            }
            filteredComboItems = ddf.getComboForecastItems(selectedMif.getId());
        } catch (Exception ex) {
            FacesMessage msg = new FacesMessage("Edition Faild", "Unexpected Exception Occurred");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            System.err.println("ManagedBean.KM.MenuManagementModule.MenuItemForecastBean: onRowEditCombo(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }

    public void generateIngredientForecast(ActionEvent event) {
        try {
            IngredientForecastEntity ingf = ddf.generateIngredientForecast(selectedMif.getId());
            if (ingf == null) {
                FacesMessage msg = new FacesMessage("Generation Faild", "Unexpected Exception Occurred");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage("Successful", "Ingredient Forecast " + ingf.getId() + " is genereted");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ingredientForecastFromMIF", ingf);
            }
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.MenuManagementModule.MenuItemForecastBean: generateIngredientForecast(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }

    public void viewIngredientForecast(ActionEvent event) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ingredientForecastFromMIF", selectedMif.getIngredientForecast());
    }

//    private void createModels() {
//        try {
//            System.out.println("createModels::::::::::::");
//            for (DishEntity d : kitchen.getDishes()) {
//                dishModels.put(d.getId(), createDishModel(d));
//            }
//            for (ComboEntity c : kitchen.getCombos()) {
//                dishModels.put(c.getId(), createComboModel(c));
//            }
//        } catch (Exception ex) {
//            System.err.println("ManagedBean.KM.MenuManagementModule.MenuItemForecastBean: createModels(): Failed. Caught an unexpected exception.");
//            ex.printStackTrace();
//        }
//    }
//
//    private CartesianChartModel createDishModel(DishEntity dish) {
//        try {
//            System.out.println("createDishModel::::::::::::");
//            CartesianChartModel dishModel = new CartesianChartModel();
//            LinkedHashMap<Calendar, Integer> weeklyDishSales = ddf.getWeeklyDishSales(kitchen.getId(), dish.getId());
////            LineChartSeries s = new LineChartSeries();
//            LineChartSeries s = new LineChartSeries();
//            s.setLabel("DishSales");
//            Integer max = 0;
//            for (Map.Entry<Calendar, Integer> e : weeklyDishSales.entrySet()) {
//                String dateString = e.getKey().get(Calendar.YEAR) + "-" + (e.getKey().get(Calendar.MONTH) + 1) + "-" + e.getKey().get(Calendar.DAY_OF_MONTH);
//                s.set("2014-10-20", e.getValue());
//                System.out.println("createDishModel: ------------" + dish.getName() + "::" + e.getKey().getTime() + "::" + e.getValue());
//                if (e.getValue() > max) {
//                    max = e.getValue();
//                }
//            }
//            dishModel.addSeries(s);
//            dishModel.setTitle("Weekly Sales of " + dish.getName());
//            dishModel.setZoom(true);
//            dishModel.getAxis(AxisType.Y).setLabel("Daily Sales Quantity");
//            dishModel.getAxis(AxisType.Y).setMin(0);
//            dishModel.getAxis(AxisType.Y).setMax(max + 10);
//            DateAxis axis = new DateAxis("Dates");
//            axis.setTickAngle(-50);
//            Calendar currDate = Calendar.getInstance();
//            String currDateString = currDate.get(Calendar.YEAR) + "-" + (currDate.get(Calendar.MONTH) + 1) + "-" + currDate.get(Calendar.DAY_OF_MONTH);
////            axis.setMax(currDateString);
//            axis.setTickFormat("%b %#d, %y");
//            dishModel.getAxes().put(AxisType.X, axis);
//            return dishModel;
//        } catch (Exception ex) {
//            System.err.println("ManagedBean.KM.MenuManagementModule.MenuItemForecastBean: createDishModel(): Failed. Caught an unexpected exception.");
//            ex.printStackTrace();
//            return null;
//        }
//    }
//
//    private CartesianChartModel createComboModel(ComboEntity combo) {
//        try {
//            System.out.println("createComboModel::::::::::::");
//            CartesianChartModel comboModel = new CartesianChartModel();
//            LinkedHashMap<Calendar, Integer> weeklyComboSales = ddf.getWeeklyComboSales(kitchen.getId(), combo.getId());
////            LineChartSeries s = new LineChartSeries();
//            LineChartSeries s = new LineChartSeries();
//            s.setLabel("ComboSales");
//            Integer max = 0;
//            for (Map.Entry<Calendar, Integer> e : weeklyComboSales.entrySet()) {
//                String dateString = e.getKey().get(Calendar.YEAR) + "-" + (e.getKey().get(Calendar.MONTH) + 1) + "-" + e.getKey().get(Calendar.DAY_OF_MONTH);
//                s.set("2014-10-20", e.getValue());
//                System.out.println("createDishModel: ------------" + combo.getName() + "::" + e.getKey().getTime() + "::" + e.getValue());
//                if (e.getValue() > max) {
//                    max = e.getValue();
//                }
//            }
//            comboModel.addSeries(s);
//            comboModel.setTitle("Weekly Sales of " + combo.getName());
//            comboModel.setZoom(true);
//            comboModel.getAxis(AxisType.Y).setLabel("Daily Sales Quantity");
//            comboModel.getAxis(AxisType.Y).setMin(0);
//            comboModel.getAxis(AxisType.Y).setMax(max + 10);
//            DateAxis axis = new DateAxis("Dates");
//            axis.setTickAngle(-50);
//            Calendar currDate = Calendar.getInstance();
//            String currDateString = currDate.get(Calendar.YEAR) + "-" + (currDate.get(Calendar.MONTH) + 1) + "-" + currDate.get(Calendar.DAY_OF_MONTH);
////            axis.setMax(currDateString);
//            axis.setTickFormat("%b %#d, %y");
//            comboModel.getAxes().put(AxisType.X, axis);
//            return comboModel;
//        } catch (Exception ex) {
//            System.err.println("ManagedBean.KM.MenuManagementModule.MenuItemForecastBean: createComboModel(): Failed. Caught an unexpected exception.");
//            ex.printStackTrace();
//            return null;
//        }
//    }
//    public void onRowToggleDish(ToggleEvent event) {
//        DishItemEntity di = (DishItemEntity) event.getData();
//        System.out.println("onRowToggleDish: " + di.getDish().getName());
//        selectedModel = dishModels.get(di.getDish().getId());
//        for(Map.Entry<Object, Number> e : selectedModel.getSeries().get(0).getData().entrySet()) {
//            System.out.println(e.getKey() + "&&&" + e.getValue());
//        }
//        
//    }
//    
//    public void onRowToggleCombo(ToggleEvent event) {
//        ComboItemEntity ci = (ComboItemEntity) event.getData();
//        selectedModel = dishModels.get(ci.getCombo().getId());
//        for(Map.Entry<Object, Number> e : selectedModel.getSeries().get(0).getData().entrySet()) {
//            System.out.println(e.getKey() + "&&&" + e.getValue());
//        }
//        
//    }
//    public void setSelectedDishModel(DishEntity dish) {
//        selectedModel = dishModels.get(dish.getId());
//        for (Map.Entry<Object, Number> e : selectedModel.getSeries().get(0).getData().entrySet()) {
//            System.out.println(e.getKey() + " &&& " + e.getValue());
//        }
//    }
//
//    public void setSelectedComboModel(ComboEntity combo) {
//        selectedModel = comboModels.get(combo.getId());
//        for (Map.Entry<Object, Number> e : selectedModel.getSeries().get(0).getData().entrySet()) {
//            System.out.println(e.getKey() + " &&& " + e.getValue());
//        }
//    }
//    public void getDishModel(DishEntity dish) {
////        System.out.println("Dish: " + dish.getName());
////        RequestContext context = RequestContext.getCurrentInstance();
////        System.out.println("getDishModel::::::::::::");
////        System.out.println("getDishModel: " + context.toString());
////        return dishModels.get(dish.getId());
//        selectedModel = dishModels.get(dish.getId());
//    }
//
//    public LineChartModel getComboModel(ComboEntity combo) {
//        System.out.println("getComboModel::::::::::::");
//        return comboModels.get(combo.getId());
//    }
}
