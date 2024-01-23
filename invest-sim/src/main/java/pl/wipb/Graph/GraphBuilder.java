package pl.wipb.Graph;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.Chart;

public abstract class GraphBuilder {
    public abstract Chart drawGraph(String name, ArrayList<Double> y);

    public abstract XYChart.Series parseValuesToSeries(String name, ArrayList<Double> values);

    public abstract boolean populateGraph(String name, ArrayList<Double> y, Chart chart);
}
