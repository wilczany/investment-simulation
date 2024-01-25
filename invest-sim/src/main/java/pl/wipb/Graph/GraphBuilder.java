package pl.wipb.Graph;

import javafx.scene.chart.XYChart;
import pl.wipb.Investments.InvestmentCaretaker;

import java.util.ArrayList;

import javafx.scene.chart.XYChart;

public abstract class GraphBuilder {
    public abstract XYChart drawGraph(InvestmentCaretaker caretaker);

    public abstract XYChart drawGraph(ArrayList<Double> values);

    public abstract XYChart.Series parseArrayListDoubleToSeries(ArrayList<Double> values);

    public abstract XYChart.Series parseCaretakerToSeries(InvestmentCaretaker caretaker);

    public abstract boolean populateGraph(InvestmentCaretaker caretaker, XYChart chart);

    public abstract boolean populateGraph(ArrayList<Double> values, XYChart chart);
}
