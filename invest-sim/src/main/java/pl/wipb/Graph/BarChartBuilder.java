package pl.wipb.Graph;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class BarChartBuilder extends GraphBuilder {
    public Chart drawGraph(ArrayList<Double> values) {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> barChart = new BarChart(xAxis, yAxis);

        XYChart.Series series = parseValuesToSeries(values);

        // Scene scene = new Scene(barChart, 800, 600);
        barChart.getData().add(series);

        return barChart;
    }

    public XYChart.Series parseValuesToSeries(ArrayList<Double> values) {
        XYChart.Series<String, Number> series = new XYChart.Series();
        for (int i = 0; i < values.size(); i++) {
            series.getData().add(new XYChart.Data((i + 1) + "", values.get(i)));
        }
        return series;
    }

    public boolean populateGraph(ArrayList<Double> values, Chart chart) {
        BarChart<String, Number> barChart = (BarChart<String, Number>) chart;
        XYChart.Series series = parseValuesToSeries(values);
        barChart.getData().add(series);

        return true;
    }

}
