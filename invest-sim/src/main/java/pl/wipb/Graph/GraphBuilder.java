package pl.wipb.Graph;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.Chart;

public abstract class GraphBuilder {
    public abstract Chart drawGraph(ArrayList<Double> y);

    public abstract XYChart.Series parseValuesToSeries(ArrayList<Double> values);

    public abstract boolean populateGraph(ArrayList<Double> y, Chart chart);
}
