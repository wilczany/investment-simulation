package pl.wipb.Controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;

public abstract class Controller {
    MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void back() {
        mainController.goToMenu();
    }

    @FXML
    public void showHighScores() {

        

        ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Najlepsze wyniki");
        Map<String, Integer> scores = readScores();
        StringBuilder sb = new StringBuilder();
        scores.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(e -> sb.append(e.getKey() + " : " + e.getValue() + "\n"));
        
        // dialog.setContentText(sb.toString());
        GridPane content =  new GridPane();
        content.add(new Label("Wyniki:"), 0, 0);
        content.add(new Label(sb.toString()), 0, 1);

        content.setHgap(10);
        content.setVgap(10);
        content.setAlignment(Pos.CENTER);
        
        dialog.getDialogPane().setContent(content);
        dialog.getDialogPane().getButtonTypes().add(okButton);
        dialog.show();
        
    }

    public Map<String, Integer> readScores() {
        
        File file = new File("resources/highscores.txt");
        HashMap<String, Integer> scores = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" - ");
                scores.put(parts[0], Integer.parseInt(parts[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scores;
    }
}
