package pl.wipb.Controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class MainController {
    @FXML
    private StackPane mainStackPane;

    @FXML
    public void initialize() {

    }

    public void setScreen(Parent pane) {

        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(pane);

    }

    public void startGame() {
        System.out.println("stackGame");
    }

    public void exit(){
        System.out.println("exit");
    }

    public void openHighScore(){
        System.out.println("openHighScore");
    }

}