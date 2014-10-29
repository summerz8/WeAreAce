/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.ACRM;

import SessionBean.ACRM.AnalyticalCRMSessionBeanLocal;
import java.util.Calendar;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author summer
 */
@Named(value = "viewCLV")
@ViewScoped
public class ViewCLV {

    @EJB
    private AnalyticalCRMSessionBeanLocal acrm;

    private Long storeId;
    private Calendar time = Calendar.getInstance();
    private Double grossProfitMargin;
    private Double aveAcquisitionCost;

    private BarChartModel barModel;

    private Collection<Double> femaleFurniture;
    private Collection<Double> femaleRetail;
    private Collection<Double> femaleKitchen;
    private Collection<Double> femaleAllPlace;

    private Collection<Double> maleFurniture;
    private Collection<Double> maleRetail;
    private Collection<Double> maleKitchen;
    private Collection<Double> maleAllPlace;

    private Collection<Double> othersFurniture;
    private Collection<Double> othersRetail;
    private Collection<Double> othersKitchen;
    private Collection<Double> othersAllPlace;

    private Double aveFemaleFurniture;
    private Double aveFemaleRetail;
    private Double aveFemaleKitchen;
    private Double aveFemaleAllPlace;

    private Double aveMaleFurniture;
    private Double aveMaleRetail;
    private Double aveMaleKitchen;
    private Double aveMaleAllPlace;

    private Double aveOthersFurniture;
    private Double aveOthersRetail;
    private Double aveOthersKitchen;
    private Double aveOthersAllPlace;

    public String getChart() {
        try {
            storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
            getAllData();
            getAveValues();

            createBarModel();
        } catch (Exception ex) {
            Logger.getLogger(ViewCLV.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "ViewCLVChart?faces-redirect=true";
    }

    public void getAllData() throws Exception {
//        
//        Long storeId, Calendar time, 
//            Double grossProfitMargin, Double aveAcquisitionCost, Integer location,
//            Boolean isForAllPlace, Boolean isFemale, Boolean isMale, Boolean isOthers,
//            Boolean checkMemberlvl, Integer memberlvl

        femaleFurniture = acrm.getCLV(storeId, time, grossProfitMargin, aveAcquisitionCost,
                1, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, 0);
        femaleRetail = acrm.getCLV(storeId, time, grossProfitMargin, aveAcquisitionCost,
                2, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, 0);
        femaleKitchen = acrm.getCLV(storeId, time, grossProfitMargin, aveAcquisitionCost,
                3, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, 0);
        femaleAllPlace = acrm.getCLV(storeId, time, grossProfitMargin, aveAcquisitionCost,
                0, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, 0);

        maleFurniture = acrm.getCLV(storeId, time, grossProfitMargin, aveAcquisitionCost,
                1, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, 0);
        maleRetail = acrm.getCLV(storeId, time, grossProfitMargin, aveAcquisitionCost,
                2, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, 0);
        maleKitchen = acrm.getCLV(storeId, time, grossProfitMargin, aveAcquisitionCost,
                3, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, 0);
        maleAllPlace = acrm.getCLV(storeId, time, grossProfitMargin, aveAcquisitionCost,
                0, Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, 0);

        othersFurniture = acrm.getCLV(storeId, time, grossProfitMargin, aveAcquisitionCost,
                1, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, 0);
        othersRetail = acrm.getCLV(storeId, time, grossProfitMargin, aveAcquisitionCost,
                2, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, 0);
        othersKitchen = acrm.getCLV(storeId, time, grossProfitMargin, aveAcquisitionCost,
                3, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, 0);
        othersAllPlace = acrm.getCLV(storeId, time, grossProfitMargin, aveAcquisitionCost,
                0, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, 0);
    }

    public void getAveValues() {
        aveFemaleFurniture = getAveValue(femaleFurniture);
        aveFemaleRetail = getAveValue(femaleRetail);
        aveFemaleKitchen = getAveValue(femaleKitchen);
        aveFemaleAllPlace = getAveValue(femaleAllPlace);

        aveMaleFurniture = getAveValue(maleFurniture);
        aveMaleRetail = getAveValue(maleRetail);
        aveMaleKitchen = getAveValue(maleKitchen);
        aveMaleAllPlace = getAveValue(maleAllPlace);

        aveOthersFurniture = getAveValue(othersFurniture);
        aveOthersRetail = getAveValue(othersRetail);
        aveOthersKitchen = getAveValue(othersKitchen);
        aveOthersAllPlace = getAveValue(othersAllPlace);
        
        System.out.println("aveFemaleFurniture: " + aveFemaleFurniture);
        System.out.println("aveFemaleRetail: " + aveFemaleRetail);
        System.out.println("aveFemaleKitchen: " + aveFemaleKitchen);
        System.out.println("aveFemaleAllPlace: " + aveFemaleAllPlace);
        
        System.out.println("aveMaleFurniture: " + aveMaleFurniture);
        System.out.println("aveMaleRetail: " + aveMaleRetail);
        System.out.println("aveMaleKitchen: " + aveMaleKitchen);
        System.out.println("aveMaleAllPlace: " + aveMaleAllPlace);
        
        System.out.println("aveOthersFurniture: " + aveOthersFurniture);
        System.out.println("aveOthersRetail: " + aveOthersRetail);
        System.out.println("aveOthersRetail: " + aveOthersRetail);
        System.out.println("aveOthersRetail: " + aveOthersRetail);
        
    }

    public Double getAveValue(Collection<Double> clvList) {
        Double aveValue = null;
        Double totalCLV = 0.0;
        Integer size = clvList.size();
        for (Double clv : clvList) {
            totalCLV += clv;
        }
        if (size == 0) {
            aveValue = 0.0;
        } else {
            aveValue = totalCLV / size;
        }
        return aveValue;

    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries female = new ChartSeries();
        female.setLabel("Female");
        female.set("Furniture Marketplace", aveFemaleFurniture);
        female.set("Retail Product Marketplace", aveFemaleRetail);
        female.set("Kitchen", aveFemaleKitchen);
        female.set("All", aveFemaleAllPlace);

        ChartSeries male = new ChartSeries();
        male.setLabel("Male");
        male.set("Furniture Marketplace", aveMaleFurniture);
        male.set("Retail Product Marketplace", aveMaleRetail);
        male.set("Kitchen", aveMaleKitchen);
        male.set("All", aveMaleAllPlace);

        ChartSeries others = new ChartSeries();
        others.setLabel("Others");
        others.set("Furniture Marketplace", aveOthersFurniture);
        others.set("Retail Product Marketplace", aveOthersRetail);
        others.set("Kitchen", aveOthersKitchen);
        others.set("All", aveOthersAllPlace);

        model.addSeries(female);
        model.addSeries(male);
        model.addSeries(others);

        return model;
    }

    private void createBarModel() {
        barModel = initBarModel();

        barModel.setTitle("Customer Lifespan Value (For different Marketplaces)");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Places");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("CLV");
        yAxis.setMin(0);
        yAxis.setMax(8000);

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("barModel", barModel);

    }

    public BarChartModel getBarModel() {
        barModel = (BarChartModel) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("barModel");
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public ViewCLV() {
    }

    public AnalyticalCRMSessionBeanLocal getAcrm() {
        return acrm;
    }

    public void setAcrm(AnalyticalCRMSessionBeanLocal acrm) {
        this.acrm = acrm;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public Double getGrossProfitMargin() {
        return grossProfitMargin;
    }

    public void setGrossProfitMargin(Double grossProfitMargin) {
        this.grossProfitMargin = grossProfitMargin;
    }

    public Double getAveAcquisitionCost() {
        return aveAcquisitionCost;
    }

    public void setAveAcquisitionCost(Double aveAcquisitionCost) {
        this.aveAcquisitionCost = aveAcquisitionCost;
    }

    public Collection<Double> getFemaleFurniture() {
        return femaleFurniture;
    }

    public void setFemaleFurniture(Collection<Double> femaleFurniture) {
        this.femaleFurniture = femaleFurniture;
    }

    public Collection<Double> getFemaleRetail() {
        return femaleRetail;
    }

    public void setFemaleRetail(Collection<Double> femaleRetail) {
        this.femaleRetail = femaleRetail;
    }

    public Collection<Double> getFemaleKitchen() {
        return femaleKitchen;
    }

    public void setFemaleKitchen(Collection<Double> femaleKitchen) {
        this.femaleKitchen = femaleKitchen;
    }

    public Collection<Double> getFemaleAllPlace() {
        return femaleAllPlace;
    }

    public void setFemaleAllPlace(Collection<Double> femaleAllPlace) {
        this.femaleAllPlace = femaleAllPlace;
    }

    public Collection<Double> getMaleFurniture() {
        return maleFurniture;
    }

    public void setMaleFurniture(Collection<Double> maleFurniture) {
        this.maleFurniture = maleFurniture;
    }

    public Collection<Double> getMaleRetail() {
        return maleRetail;
    }

    public void setMaleRetail(Collection<Double> maleRetail) {
        this.maleRetail = maleRetail;
    }

    public Collection<Double> getMaleKitchen() {
        return maleKitchen;
    }

    public void setMaleKitchen(Collection<Double> maleKitchen) {
        this.maleKitchen = maleKitchen;
    }

    public Collection<Double> getMaleAllPlace() {
        return maleAllPlace;
    }

    public void setMaleAllPlace(Collection<Double> maleAllPlace) {
        this.maleAllPlace = maleAllPlace;
    }

    public Collection<Double> getOthersFurniture() {
        return othersFurniture;
    }

    public void setOthersFurniture(Collection<Double> othersFurniture) {
        this.othersFurniture = othersFurniture;
    }

    public Collection<Double> getOthersRetail() {
        return othersRetail;
    }

    public void setOthersRetail(Collection<Double> othersRetail) {
        this.othersRetail = othersRetail;
    }

    public Collection<Double> getOthersKitchen() {
        return othersKitchen;
    }

    public void setOthersKitchen(Collection<Double> othersKitchen) {
        this.othersKitchen = othersKitchen;
    }

    public Collection<Double> getOthersAllPlace() {
        return othersAllPlace;
    }

    public void setOthersAllPlace(Collection<Double> othersAllPlace) {
        this.othersAllPlace = othersAllPlace;
    }

    public Double getAveFemaleFurniture() {
        return aveFemaleFurniture;
    }

    public void setAveFemaleFurniture(Double aveFemaleFurniture) {
        this.aveFemaleFurniture = aveFemaleFurniture;
    }

    public Double getAveFemaleRetail() {
        return aveFemaleRetail;
    }

    public void setAveFemaleRetail(Double aveFemaleRetail) {
        this.aveFemaleRetail = aveFemaleRetail;
    }

    public Double getAveFemaleKitchen() {
        return aveFemaleKitchen;
    }

    public void setAveFemaleKitchen(Double aveFemaleKitchen) {
        this.aveFemaleKitchen = aveFemaleKitchen;
    }

    public Double getAveFemaleAllPlace() {
        return aveFemaleAllPlace;
    }

    public void setAveFemaleAllPlace(Double aveFemaleAllPlace) {
        this.aveFemaleAllPlace = aveFemaleAllPlace;
    }

    public Double getAveMaleFurniture() {
        return aveMaleFurniture;
    }

    public void setAveMaleFurniture(Double aveMaleFurniture) {
        this.aveMaleFurniture = aveMaleFurniture;
    }

    public Double getAveMaleRetail() {
        return aveMaleRetail;
    }

    public void setAveMaleRetail(Double aveMaleRetail) {
        this.aveMaleRetail = aveMaleRetail;
    }

    public Double getAveMaleKitchen() {
        return aveMaleKitchen;
    }

    public void setAveMaleKitchen(Double aveMaleKitchen) {
        this.aveMaleKitchen = aveMaleKitchen;
    }

    public Double getAveMaleAllPlace() {
        return aveMaleAllPlace;
    }

    public void setAveMaleAllPlace(Double aveMaleAllPlace) {
        this.aveMaleAllPlace = aveMaleAllPlace;
    }

    public Double getAveOthersFurniture() {
        return aveOthersFurniture;
    }

    public void setAveOthersFurniture(Double aveOthersFurniture) {
        this.aveOthersFurniture = aveOthersFurniture;
    }

    public Double getAveOthersRetail() {
        return aveOthersRetail;
    }

    public void setAveOthersRetail(Double aveOthersRetail) {
        this.aveOthersRetail = aveOthersRetail;
    }

    public Double getAveOthersKitchen() {
        return aveOthersKitchen;
    }

    public void setAveOthersKitchen(Double aveOthersKitchen) {
        this.aveOthersKitchen = aveOthersKitchen;
    }

    public Double getAveOthersAllPlace() {
        return aveOthersAllPlace;
    }

    public void setAveOthersAllPlace(Double aveOthersAllPlace) {
        this.aveOthersAllPlace = aveOthersAllPlace;
    }

}
