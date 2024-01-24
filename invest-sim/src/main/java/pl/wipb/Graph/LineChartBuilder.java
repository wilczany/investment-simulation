package pl.wipb.Graph;

import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import pl.wipb.Investments.InvestmentCaretaker;
import pl.wipb.Iterator.Iterator;
import pl.wipb.Iterator.MementoIterator;

//potencjalna zmiana z <Double, Double> na <Integer, Double>; nie zapomnieć w kontrolerze

public class LineChartBuilder extends GraphBuilder {
    public Chart drawGraph(InvestmentCaretaker caretaker) {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<Double, Double> lineChart = new LineChart(xAxis, yAxis);

        lineChart.getData().add(parseCaretakerToSeries(caretaker));

        return lineChart;
    }

    public XYChart.Series parseCaretakerToSeries(InvestmentCaretaker caretaker) {
        XYChart.Series<Double, Double> series = new XYChart.Series();
        series.setName(caretaker.getInvestment().getName());
        Iterator it = new MementoIterator(caretaker, caretaker.getInvestment().getDay());
        while (it.hasNext()) {
            InvestmentCaretaker nextCt = it.next();
            series.getData()
                    .add(new XYChart.Data((double) nextCt.getInvestment().getDay(), nextCt.getInvestment().getValue()));
        }

        return series;
    }

    public boolean populateGraph(InvestmentCaretaker caretaker, Chart chart) {
        LineChart<Double, Double> lineChart = (LineChart<Double, Double>) chart;
        XYChart.Series series = parseCaretakerToSeries(caretaker);
        lineChart.getData().add(series);

        return true;
    }

}
