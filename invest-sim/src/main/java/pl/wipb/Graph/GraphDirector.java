package pl.wipb.Graph;

import pl.wipb.Investments.InvestmentCaretaker;
import pl.wipb.Iterator.Iterator;
import pl.wipb.Iterator.TimeIterator;

import java.util.ArrayList;

import javafx.scene.chart.Chart;

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

    public Chart buildChart(InvestmentCaretaker investment) {
        Chart chart;
        chart = graphBuilders[currBuilder].drawGraph(investment);

        return chart;
    }

    public Chart buildMultiChart(ArrayList<InvestmentCaretaker> investments) {
        Chart chart;
        Iterator it = new TimeIterator(investments);

        chart = graphBuilders[currBuilder].drawGraph(it.next());
        while (it.hasNext()) {
            graphBuilders[currBuilder].populateGraph(it.next(), chart);
        }

        return chart;
    }

    public void appendChart(InvestmentCaretaker investment, Chart chart) {
        graphBuilders[currBuilder].populateGraph(investment, chart);
    }

    // public void appendCharts(){} ?
}