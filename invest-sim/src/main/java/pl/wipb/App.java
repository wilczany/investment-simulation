package pl.wipb;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import pl.wipb.Controllers.GameController;

public class App extends Application {

    GameController GameController;

    @Override
    public void start(Stage stage) {
        FXMLLoader fxml = null;
        // fxml = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
        fxml = new FXMLLoader(getClass().getResource("/fxml/main-screen.fxml"));
        try {

            Scene scene = new Scene(fxml.load());

            stage.setTitle("Portfel inwestycyjny");
            stage.setResizable(false); // w razie w

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getCause());
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
