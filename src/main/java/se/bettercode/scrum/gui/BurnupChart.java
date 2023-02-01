package se.bettercode.scrum.gui;

import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.collections.ListChangeListener.Change;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import se.bettercode.scrum.backlog.BurnupDay;


public class BurnupChart extends AreaChart<Number, Number> {

    XYChart.Series totalSeries;
    XYChart.Series doneSeries;
    static final double lowerBound = 0;
    static final double yUpperBound = 26; //TODO: Make to backlog size in points?
    static final double tickUnit = 1;

    public BurnupChart(int xUpperBound) {
        super(new NumberAxis(lowerBound, xUpperBound, tickUnit), new NumberAxis(lowerBound, yUpperBound, tickUnit));
        getYAxis().setLabel("Points");
        getXAxis().setLabel("Days");
        setTitle("Burnup");

        initChart();
        totalSeries.setName("Total");
        doneSeries.setName("Done");

    }

    public void bindBurnupDaysProperty(ListProperty<BurnupDay> burnupDays) {
        burnupDays.get().addListener((Change<? extends BurnupDay> c) -> addBurnupData(c));
    }

    private void addBurnupData(Change<? extends BurnupDay> c) {
        Platform.runLater(() -> {
            while (c.next()) {
                for (BurnupDay burnupDay : c.getAddedSubList()) {
                    getData().get(0).getData().add(makeDoneSeriesData(burnupDay));
                    getData().get(1).getData().add(makeTotalSeriesData(burnupDay));
                }
            }
        });
    }

    public void removeAllData() {
        System.out.println("Removing all chart data...");
        initChart();
    }

    private void initChart() {
        getData().clear();
        totalSeries = new Series();
        doneSeries = new Series();
        getData().addAll(doneSeries, totalSeries);
    }

    private Data makeTotalSeriesData(BurnupDay burnupDay) {
        return new Data(burnupDay.getDay(), burnupDay.getTotal());
    }

    private Data makeDoneSeriesData(BurnupDay burnupDay) {
        return new Data(burnupDay.getDay(), burnupDay.getDone());
    }

}
