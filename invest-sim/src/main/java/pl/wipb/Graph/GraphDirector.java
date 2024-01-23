package pl.wipb.Graph;

import pl.wipb.Game;

import java.util.ArrayList;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.Chart;

// dane do wykresów powinny być przekazywane w formacie HashMap<String, ArrayList<Double>;
// String jako nazwa dla serii danych, ArrayList<double> jako wartości do przedstawienia na wykresie

public class GraphDirector extends Application {
    private Map<String, ArrayList<Double>> stockData;
    private Chart chart = null;
    private int builder = 0;

    @Override
    public void start(Stage stage) {
        Game game = new Game();
        GraphBuilder graphBuilders[] = { new LineChartBuilder(), new BarChartBuilder() };

        // game.StartGame();

        this.stockData = game.getStockValuesTillDay(10);

        stockData.forEach((key, values) -> {
            System.out.println(key + values);
            if (chart == null) {
                this.chart = graphBuilders[builder].drawGraph(key, values);
            } else {
                graphBuilders[builder].populateGraph(key, values, chart);
            }
        });

        Scene charts = new Scene(chart, 800, 600);

        stage.setScene(charts);
        stage.show();
    }

    public static void main(String[] args) {
        // System.out.println("test");
        // Game game = new Game();
        // game.StartGame();

        launch(args);
    }
}