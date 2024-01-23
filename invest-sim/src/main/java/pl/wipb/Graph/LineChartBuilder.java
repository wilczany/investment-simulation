package pl.wipb.Graph;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class LineChartBuilder extends GraphBuilder {
    public Chart drawGraph(String name, ArrayList<Double> values) {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

        XYChart.Series<Number, Number> series = new XYChart.Series();
        for (int i = 0; i < values.size(); i++) {
            series.getData().add(new XYChart.Data(i + 1, values.get(i)));
        }

        // Scene scene = new Scene(lineChart, 800, 600);
        lineChart.getData().add(series);

        return lineChart;
    }

    public XYChart.Series parseValuesToSeries(String name, ArrayList<Double> values) {
        XYChart.Series<Number, Number> series = new XYChart.Series();
        series.setName(name); // dodatkowy parametr nazwy
        for (int i = 0; i < values.size(); i++) {
            series.getData().add(new XYChart.Data(i + 1, values.get(i)));
        }
        return series;
    }

    public boolean populateGraph(String name, ArrayList<Double> values, Chart chart) {
        LineChart<Number, Number> lineChart = (LineChart<Number, Number>) chart;
        XYChart.Series series = parseValuesToSeries(name, values);
        lineChart.getData().add(series);

        return true;
    }

}
