package pl.wipb.Graph;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GraphDirector extends Application {

    @Override
    public void start(Stage stage) {
        double[] y = { 1, 2, 1, 4, 3, 2 };

        GraphBuilder graphBuilder = new LineChartBuilder();
        Scene scene = graphBuilder.drawGraph(y);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}