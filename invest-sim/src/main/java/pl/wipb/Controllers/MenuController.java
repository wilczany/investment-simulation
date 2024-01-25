package pl.wipb.Controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.application.Platform;

import javafx.fxml.FXMLLoader;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class MenuController extends Controller{
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
    public void startGame() throws Exception {
        
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/game.fxml"));
        try{System.out.println(new File("/fxml/game.fxml").getCanonicalPath());}catch   (Exception e) {
            throw e;
        }
        
       
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

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}