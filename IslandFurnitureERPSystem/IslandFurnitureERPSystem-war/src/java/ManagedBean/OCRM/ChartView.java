/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.OCRM;

import Entity.Store.OCRM.ProductSalesForecastEntity;
import Entity.Store.OCRM.SalesRecordEntity;
import Entity.Store.StoreEventEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author apple
 */
@Named(value = "chartView")
@ViewScoped
public class ChartView {

    private LineChartModel animatedModel1;

    public ChartView() {
    }

    public LineChartModel createRecent(List<ProductSalesForecastEntity> salesForecast, List<SalesRecordEntity> salesRecord) {
        
        List<ProductSalesForecastEntity> selectSalesForecast = new ArrayList<>();
        List<SalesRecordEntity> selectSalesRecord = new ArrayList<>();
        int size = salesForecast.size();
        int size1 = salesRecord.size();

        System.out.println("size: " + size + "...size1:" + size1);
        int a;
        int length;
        if (size1 < 6) {
            length = size1;
        } else {
            length = 6;
        }
        for (a = 0; a < length; a++) {
            selectSalesForecast.add(salesForecast.get(a));
            selectSalesRecord.add(salesRecord.get(a));
            if (a == length - 1) {
                selectSalesForecast.add(salesForecast.get(a + 1));
            }
        }

        animatedModel1 = initRecentData(selectSalesForecast, selectSalesRecord);
        animatedModel1.setTitle("Month-on-Month");
        animatedModel1.setAnimate(true);
        animatedModel1.setLegendPosition("se");
        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
        yAxis.setLabel("Sales");
        Axis xAxis = animatedModel1.getAxis(AxisType.X);
        xAxis.setTickCount(12);
        xAxis.setLabel("Month");
        xAxis.setMin(1);
        xAxis.setMax(12);
        yAxis.setMin(0);
        yAxis.setMax(5000);
        return animatedModel1;

    }

    public LineChartModel EventEffect(List<StoreEventEntity> storeEventList) {

        System.out.println(storeEventList.size());
        animatedModel1 = initEventEffectData(storeEventList);
        animatedModel1.setTitle("Event Effect");
        animatedModel1.setAnimate(true);
        animatedModel1.setLegendPosition("se");
        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
        yAxis.setLabel("Increase Rate %");
        Axis xAxis = animatedModel1.getAxis(AxisType.X);
        xAxis.setLabel("Year");
        xAxis.setMin(2011);
        xAxis.setMax(storeEventList.get(0).getStartDate().get(Calendar.YEAR) + 1);
        xAxis.setTickCount(storeEventList.get(0).getStartDate().get(Calendar.YEAR) - 2011 + 2);

        yAxis.setMin(0);
        yAxis.setMax(500);
        return animatedModel1;

    }

    public LineChartModel compareYear(List<ProductSalesForecastEntity> salesForecast, List<SalesRecordEntity> salesRecord) {

        List<ProductSalesForecastEntity> selectSalesForecast = new ArrayList<>();
        List<SalesRecordEntity> selectSalesRecord = new ArrayList<>();
        Calendar today = Calendar.getInstance();
        today.add(Calendar.MONTH, 2);
        for (ProductSalesForecastEntity p : salesForecast) {
            if (p.getTargetPeriod().get(Calendar.MONTH) == today.get(Calendar.MONTH)) {
                selectSalesForecast.add(p);
            }
        }

        for (SalesRecordEntity p : salesRecord) {
            if (p.getRecordPeriod().get(Calendar.MONTH) == today.get(Calendar.MONTH)) {
                selectSalesRecord.add(p);
            }
        }

        animatedModel1 = initYearData(selectSalesForecast, selectSalesRecord);
        animatedModel1.setTitle("Same Period in Previous Years");
        animatedModel1.setAnimate(true);
        animatedModel1.setLegendPosition("se");
        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
        Axis xAxis = animatedModel1.getAxis(AxisType.X);
        yAxis.setLabel("Sales");
        xAxis.setMin(2011);
        xAxis.setLabel("Year");
        xAxis.setMax(salesForecast.get(0).getTargetPeriod().get(Calendar.YEAR) + 1);
        xAxis.setTickCount(salesForecast.get(0).getTargetPeriod().get(Calendar.YEAR) - 2011 + 2);
        yAxis.setMin(0);
        yAxis.setMax(5000);
        return animatedModel1;

    }

    private LineChartModel initYearData(List<ProductSalesForecastEntity> salesForecast, List<SalesRecordEntity> salesRecord) {
        LineChartModel model = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Sales Forecast");

        for (ProductSalesForecastEntity p : salesForecast) {
            series1.set(p.getTargetPeriod().get(Calendar.YEAR), p.getAmount());
        }

        model.addSeries(series1);

        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Sales Record");

        for (SalesRecordEntity p : salesRecord) {
            series2.set(p.getRecordPeriod().get(Calendar.YEAR), p.getAmount());
        }

        model.addSeries(series2);

        return model;
    }

    private LineChartModel initRecentData(List<ProductSalesForecastEntity> salesForecast, List<SalesRecordEntity> salesRecord) {
        LineChartModel model = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Sales Forecast");

        for (ProductSalesForecastEntity p : salesForecast) {
            series1.set(p.getTargetPeriod().get(Calendar.MONTH) + 1, p.getAmount());
        }

        model.addSeries(series1);

        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Sales Record");

        for (SalesRecordEntity p : salesRecord) {
            series2.set(p.getRecordPeriod().get(Calendar.MONTH) + 1, p.getAmount());
        }

        model.addSeries(series2);

        return model;
    }

    private LineChartModel initEventEffectData(List<StoreEventEntity> storeEventList) {
        LineChartModel model = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Event Effect");

        for (StoreEventEntity p : storeEventList) {
            series1.set(p.getStartDate().get(Calendar.YEAR), p.getIncreaseSale());
        }

        model.addSeries(series1);

        return model;
    }

    public LineChartModel getAnimatedModel1() {
        return animatedModel1;
    }

    public void setAnimatedModel1(LineChartModel animatedModel1) {
        this.animatedModel1 = animatedModel1;
    }

}
