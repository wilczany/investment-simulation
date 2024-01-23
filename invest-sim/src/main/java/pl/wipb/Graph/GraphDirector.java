package pl.wipb.Graph;

import pl.wipb.Game;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.Chart;

public class GraphDirector extends Application {
    private ArrayList<Double> values;

    @Override
    public void start(Stage stage) {
        Game game = new Game();
        GraphBuilder graphBuilder[] = { new LineChartBuilder(), new BarChartBuilder() };

        game.StartGame();
        game.getStockValuesTillDay(1);
        // this.values = game.getStockValues();

        // Chart chart = graphBuilder[1].drawGraph(/* name, */values);
        // Scene charts = new Scene(chart, 800, 600);

        // game.NextDay();
        // this.values = game.getStockValues();
        // graphBuilder[1].populateGraph(/* name, */values, chart);

        // stage.setScene(charts);
        // stage.show();
    }

    // są 4 akcje i 10 dni, aktualnie rysuje na jeden wykres wartość czterech akcji
    // bruh
    public static void main(String[] args) {

        System.out.println("test");
        Game game = new Game();
        game.StartGame();
        System.out.println(game.getStockValuesTillDay(5));

        // launch(args);
    }
}