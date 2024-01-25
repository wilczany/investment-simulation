package pl.wipb.Graph;

import java.util.ArrayList;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import pl.wipb.Investments.InvestmentCaretaker;
import pl.wipb.Iterator.Iterator;
import pl.wipb.Iterator.MementoIterator;

//potencjalna zmiana z <Double, Double> na <Integer, Double>; nie zapomnieć w kontrolerze

public class LineChartBuilder extends GraphBuilder {
    public XYChart drawGraph(InvestmentCaretaker caretaker) {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<Double, Double> lineChart = new LineChart(xAxis, yAxis);

        lineChart.getData().add(parseCaretakerToSeries(caretaker));

        return lineChart;
    }

    public XYChart drawGraph(ArrayList<Double> values) {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<Double, Double> lineChart = new LineChart(xAxis, yAxis);

        lineChart.getData().add(parseArrayListDoubleToSeries(values));

        return lineChart;
    }

    public XYChart.Series parseCaretakerToSeries(InvestmentCaretaker caretaker) {
        XYChart.Series<Double, Double> series = new XYChart.Series();
        series.setName("Wartość aktywów gracza");
        series.setName(caretaker.getInvestment().getName());
        Iterator it = new MementoIterator(caretaker, caretaker.getInvestment().getDay());
        while (it.hasNext()) {
            InvestmentCaretaker nextCt = it.next();
            series.getData()
                    .add(new XYChart.Data((double) nextCt.getInvestment().getDay(), nextCt.getInvestment().getValue()));
        }

        return series;
    }

    public XYChart.Series parseArrayListDoubleToSeries(ArrayList<Double> values) {
        XYChart.Series<Double, Double> series = new XYChart.Series();
        series.setName("Wartość aktywów gracza");
        for (int i = 0; i < values.size(); i++) {
            series.getData().add(new XYChart.Data((double) i, values.get(i)));
        }
        return series;
    }

    public boolean populateGraph(InvestmentCaretaker caretaker, XYChart chart) {
        LineChart<Double, Double> lineChart = (LineChart<Double, Double>) chart;
        XYChart.Series series = parseCaretakerToSeries(caretaker);
        lineChart.getData().add(series);

        return true;
    }

    public boolean populateGraph(ArrayList<Double> values, XYChart chart) {
        LineChart<Double, Double> lineChart = (LineChart<Double, Double>) chart;
        XYChart.Series series = parseArrayListDoubleToSeries(values);
        lineChart.getData().add(series);

        return true;
    }

}
