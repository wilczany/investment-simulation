package pl.wipb.Controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class MainController {
    @FXML
    private Pane mainPane;

    @FXML
    public void initialize() {

    }

    @FXML
    public void setScreen(Parent pane) {

        mainPane.getChildren().clear();
        mainPane.getChildren().add(pane);

    }

    @FXML
    public void startGame() {
        FXMLLoader fxml = new FXMLLoader(this.getClass().getResource("/fxml/ObslugaKlientow.fxml"));
        Parent root = null;
        GameController gameController = null;
        try {
            root = fxml.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameController = fxml.getController();

        // gameController.setMainController(mainController);
        // mainController.setScreen(root);
    }

    @FXML
    public void exit() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void openHighScore() {
        System.out.println("openHighScore");
    }

}