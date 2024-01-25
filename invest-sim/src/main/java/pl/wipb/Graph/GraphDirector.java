package pl.wipb.Graph;

import pl.wipb.Investments.InvestmentCaretaker;
import pl.wipb.Iterator.Iterator;
import pl.wipb.Iterator.TimeIterator;

import java.util.ArrayList;

import javafx.scene.chart.XYChart;

// dane do wykresów powinny być przekazywane w formacie HashMap<String, ArrayList<Double>;
// String jako nazwa dla serii danych, ArrayList<double> jako wartości do przedstawienia na wykresie

public class GraphDirector {
    public GraphBuilder graphBuilders[] = { new LineChartBuilder(), new BarChartBuilder() };
    // private Chart chart = null;
    private int currBuilder = 0;

    public void setBuilder(int num) {
        if (num < 0)
            num = 0;
        if (num > 1)
            num = 1;
        this.currBuilder = num;
    }

    public XYChart buildChart(InvestmentCaretaker investment) {
        XYChart chart;
        chart = graphBuilders[currBuilder].drawGraph(investment);

        return chart;
    }

    public XYChart buildChart(ArrayList<Double> values) {
        XYChart chart;
        chart = graphBuilders[currBuilder].drawGraph(values);

        return chart;
    }

    public XYChart buildMultiChart(ArrayList<InvestmentCaretaker> investments) {
        XYChart chart;
        Iterator it = new TimeIterator(investments);

        chart = graphBuilders[currBuilder].drawGraph(it.next());
        while (it.hasNext()) {
            graphBuilders[currBuilder].populateGraph(it.next(), chart);
        }

        return chart;
    }

    public void appendChart(InvestmentCaretaker investment, XYChart chart) {
        graphBuilders[currBuilder].populateGraph(investment, chart);
    }

    public void appendChart(ArrayList<Double> values, XYChart chart) {
        graphBuilders[currBuilder].populateGraph(values, chart);
    }

    // public void appendCharts(){} ?
}