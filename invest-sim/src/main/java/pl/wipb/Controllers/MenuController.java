package pl.wipb.Controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.application.Platform;

import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class MenuController{
    @FXML
    private Pane mainPane;
    private Stage stage;
    private Scene root;
    private MainController mainController;

    
    public void setScreen(Parent pane) {

        mainPane.getChildren().clear();
        mainPane.getChildren().add(pane);

    }

    @FXML
    public void startGame() throws IOException {
       
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/game.fxml"));
        Parent root = null;
        try {
            root = fxml.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameController game = fxml.getController();
        game.setMainController(mainController);
        mainController.setScreen(root);
        // stage = (Stage) mainPane.getScene().getWindow();
        
    }   

    @FXML
    public void exit() {
        try {
            Platform.exit();
        } catch (Exception e) {
        }
    }

    @FXML
    public void openHighScore() {
        System.out.println("openHighScore");
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}