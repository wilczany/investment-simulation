package pl.wipb;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import pl.wipb.Controllers.GameController;

public class App extends Application {

    GameController GameController;

    @Override
    public void start(Stage stage) {
        FXMLLoader fxml = null;
        fxml = new FXMLLoader(getClass().getResource("/fxml/game.fxml"));
        try {
            Scene scene = new Scene(fxml.load());

            stage.setTitle("Portfel inwestycyjny");
            stage.setResizable(false); // w razie w

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        launch(args);

        // tekstowy debug
        // Game g = Game.getInstance();
        // for (int i = 0; i < 15; i++) {
        // System.out.println("dzień " + i);
        // g.nextDay();
        // }
    }

}
