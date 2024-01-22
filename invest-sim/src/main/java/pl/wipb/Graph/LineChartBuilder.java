package pl.wipb.Graph;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class LineChartBuilder extends GraphBuilder {
    @Override
    public Scene drawGraph(double[] y) {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio"); // dodatkowy parametr nazwy
        for (int i = 0; i < y.length; i++) {
            series.getData().add(new XYChart.Data(i + 1, y[i]));
        }

        Scene scene = new Scene(lineChart, 800, 600);
        lineChart.getData().add(series);

        return scene;
    }

}
