package pl.wipb.Graph;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import pl.wipb.Investments.InvestmentCaretaker;
import pl.wipb.Iterator.Iterator;
import pl.wipb.Iterator.MementoIterator;

public class BarChartBuilder extends GraphBuilder {
    public Chart drawGraph(InvestmentCaretaker caretaker) {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);

        barChart.getData().add(parseCaretakerToSeries(caretaker));

        return barChart;
    }

    public XYChart.Series parseCaretakerToSeries(InvestmentCaretaker caretaker) {
        XYChart.Series<String, Number> series = new XYChart.Series();
        series.setName(caretaker.getInvestment().getName());
        Iterator it = new MementoIterator(caretaker, caretaker.getInvestment().getDay());
        while (it.hasNext()) {
            InvestmentCaretaker nextCt = it.next();
            series.getData()
                    .add(new XYChart.Data(nextCt.getInvestment().getDay() + "", nextCt.getInvestment().getValue()));
        }

        return series;
    }

    public boolean populateGraph(InvestmentCaretaker caretaker, Chart chart) {
        BarChart<String, Number> barChart = (BarChart<String, Number>) chart;
        XYChart.Series series = parseCaretakerToSeries(caretaker);
        barChart.getData().add(series);

        return true;
    }

}
