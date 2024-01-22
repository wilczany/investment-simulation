package pl.wipb.Graph;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class BarChartBuilder extends GraphBuilder {
    @Override
    public Scene drawGraph(double[] y) {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> barChart = new BarChart(xAxis, yAxis);

        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio"); // dodatkowy parametr nazwy
        for (int i = 0; i < y.length; i++) {
            series.getData().add(new XYChart.Data((i + 1) + "", y[i]));
        }

        Scene scene = new Scene(barChart, 800, 600);
        barChart.getData().add(series);

        return scene;
    }
}
