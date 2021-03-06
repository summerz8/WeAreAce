/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.ACRM;

import Entity.Store.ACRM.CLVEntity;
import Entity.Store.OCRM.MemberEntity;
import SessionBean.ACRM.AnalyticalCRMSessionBeanLocal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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

    private Collection<CLVEntity> femaleFurniture;
    private Collection<CLVEntity> femaleRetail;
    private Collection<CLVEntity> femaleKitchen;
    private Collection<CLVEntity> femaleAllPlace;

    private Collection<CLVEntity> maleFurniture;
    private Collection<CLVEntity> maleRetail;
    private Collection<CLVEntity> maleKitchen;
    private Collection<CLVEntity> maleAllPlace;

    private Collection<CLVEntity> othersFurniture;
    private Collection<CLVEntity> othersRetail;
    private Collection<CLVEntity> othersKitchen;
    private Collection<CLVEntity> othersAllPlace;

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

    private Collection<CLVEntity> allMembersAllPlace;
    private CLVEntity[] selectedCLVs;
    private MemberEntity selectedMember;

    @PostConstruct
    public void getChart() {
        try {
            storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
            getAllData();
            getAveValues();

            createBarModel();
        } catch (Exception ex) {
            Logger.getLogger(ViewCLV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getAllData() throws Exception {
//        
//        Long storeId, Calendar time, 
//            Double grossProfitMargin, Double aveAcquisitionCost, Integer location,
//            Boolean isForAllPlace, Boolean isFemale, Boolean isMale, Boolean isOthers,
//            Boolean checkMemberlvl, Integer memberlvl

        grossProfitMargin = (Double) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grossProfitMargin");
        aveAcquisitionCost = (Double) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("aveAcquisitionCost");

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

        allMembersAllPlace = acrm.getCLV(storeId, time, grossProfitMargin, aveAcquisitionCost,
                0, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, 0);

        System.out.println("allMembersAllPlace: " + allMembersAllPlace.toString());
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

//        System.out.println("aveFemaleFurniture: " + aveFemaleFurniture);
//        System.out.println("aveFemaleRetail: " + aveFemaleRetail);
//        System.out.println("aveFemaleKitchen: " + aveFemaleKitchen);
//        System.out.println("aveFemaleAllPlace: " + aveFemaleAllPlace);
//
//        System.out.println("aveMaleFurniture: " + aveMaleFurniture);
//        System.out.println("aveMaleRetail: " + aveMaleRetail);
//        System.out.println("aveMaleKitchen: " + aveMaleKitchen);
//        System.out.println("aveMaleAllPlace: " + aveMaleAllPlace);
//
//        System.out.println("aveOthersFurniture: " + aveOthersFurniture);
//        System.out.println("aveOthersRetail: " + aveOthersRetail);
//        System.out.println("aveOthersRetail: " + aveOthersRetail);
//        System.out.println("aveOthersRetail: " + aveOthersRetail);
    }

    public Double getAveValue(Collection<CLVEntity> clvList) {
        Double aveValue = null;
        Double totalCLV = 0.0;
        Integer size = clvList.size();
        for (CLVEntity clvEntity : clvList) {
            Double clv = clvEntity.getClv();
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
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("grossProfitMargin", grossProfitMargin);
    }

    public Double getAveAcquisitionCost() {
        return aveAcquisitionCost;
    }

    public void setAveAcquisitionCost(Double aveAcquisitionCost) {
        this.aveAcquisitionCost = aveAcquisitionCost;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("aveAcquisitionCost", aveAcquisitionCost);

    }

    public Collection<CLVEntity> getFemaleFurniture() {
        return femaleFurniture;
    }

    public void setFemaleFurniture(Collection<CLVEntity> femaleFurniture) {
        this.femaleFurniture = femaleFurniture;
    }

    public Collection<CLVEntity> getFemaleRetail() {
        return femaleRetail;
    }

    public void setFemaleRetail(Collection<CLVEntity> femaleRetail) {
        this.femaleRetail = femaleRetail;
    }

    public Collection<CLVEntity> getFemaleKitchen() {
        return femaleKitchen;
    }

    public void setFemaleKitchen(Collection<CLVEntity> femaleKitchen) {
        this.femaleKitchen = femaleKitchen;
    }

    public Collection<CLVEntity> getFemaleAllPlace() {
        return femaleAllPlace;
    }

    public void setFemaleAllPlace(Collection<CLVEntity> femaleAllPlace) {
        this.femaleAllPlace = femaleAllPlace;
    }

    public Collection<CLVEntity> getMaleFurniture() {
        return maleFurniture;
    }

    public void setMaleFurniture(Collection<CLVEntity> maleFurniture) {
        this.maleFurniture = maleFurniture;
    }

    public Collection<CLVEntity> getMaleRetail() {
        return maleRetail;
    }

    public void setMaleRetail(Collection<CLVEntity> maleRetail) {
        this.maleRetail = maleRetail;
    }

    public Collection<CLVEntity> getMaleKitchen() {
        return maleKitchen;
    }

    public void setMaleKitchen(Collection<CLVEntity> maleKitchen) {
        this.maleKitchen = maleKitchen;
    }

    public Collection<CLVEntity> getMaleAllPlace() {
        return maleAllPlace;
    }

    public void setMaleAllPlace(Collection<CLVEntity> maleAllPlace) {
        this.maleAllPlace = maleAllPlace;
    }

    public Collection<CLVEntity> getOthersFurniture() {
        return othersFurniture;
    }

    public void setOthersFurniture(Collection<CLVEntity> othersFurniture) {
        this.othersFurniture = othersFurniture;
    }

    public Collection<CLVEntity> getOthersRetail() {
        return othersRetail;
    }

    public void setOthersRetail(Collection<CLVEntity> othersRetail) {
        this.othersRetail = othersRetail;
    }

    public Collection<CLVEntity> getOthersKitchen() {
        return othersKitchen;
    }

    public void setOthersKitchen(Collection<CLVEntity> othersKitchen) {
        this.othersKitchen = othersKitchen;
    }

    public Collection<CLVEntity> getOthersAllPlace() {
        return othersAllPlace;
    }

    public void setOthersAllPlace(Collection<CLVEntity> othersAllPlace) {
        this.othersAllPlace = othersAllPlace;
    }

    public Collection<CLVEntity> getAllMembersAllPlace() {
        return allMembersAllPlace;
    }

    public void setAllMembersAllPlace(Collection<CLVEntity> allMembersAllPlace) {
        this.allMembersAllPlace = allMembersAllPlace;
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

    public CLVEntity[] getSelectedCLVs() {
        return selectedCLVs;
    }

    public void setSelectedCLVs(CLVEntity[] selectedCLVs) {
        this.selectedCLVs = selectedCLVs;
    }

    public MemberEntity getSelectedMember() {
        return selectedMember;
    }

    public void setSelectedMember(MemberEntity selectedMember) {
        this.selectedMember = selectedMember;
    }

    public String sendEmail() {

        System.out.println("ViewCLVBean: sendEmail:");

        Collection<MemberEntity> members = new ArrayList<>();
        for (CLVEntity clv : selectedCLVs) {
            members.add(clv.getMember());
        }

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("customers", members);

        return "SendCustomerEmail?faces-redirect=true";
    }
}
