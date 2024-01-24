package pl.wipb.Graph;

import javafx.scene.chart.XYChart;
import pl.wipb.Investments.InvestmentCaretaker;
import javafx.scene.chart.Chart;

public abstract class GraphBuilder {
    public abstract Chart drawGraph(InvestmentCaretaker caretaker);

    public abstract XYChart.Series parseCaretakerToSeries(InvestmentCaretaker caretaker);

    public abstract boolean populateGraph(InvestmentCaretaker caretaker, Chart chart);
}
