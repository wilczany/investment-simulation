package pl.wipb.Controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.wipb.Game;
import pl.wipb.Investments.Investment;
import pl.wipb.Investments.InvestmentCaretaker;
import java.util.ArrayList;

public class GameController {
    Game game = Game.getInstance();
    ArrayList<InvestmentCaretaker> investmentCaretakers;

    @FXML
    ListView<InvestmentCaretaker> caretakersList;

    @FXML
    Button nextDayBtn;

    @FXML
    void initialize() {
        game.startGame();
        System.out.println(game.getInvestments());
        // game.nextDay();
        System.out.println("HALO URWA");
        System.out.println(game.getInvestments());

        ArrayList<InvestmentCaretaker> investmentCaretakers = game.getInvestments();
        for (InvestmentCaretaker iC : investmentCaretakers) {
            System.out.println(iC);
            caretakersList.getItems().add(iC);
        }
        System.out.println(caretakersList.getItems());

        nextDayBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //caretakersList.getItems().clear();
                nextDay();
                System.out.println("klik");
            }
        });

        // while (true) {
        // try {
        // wait(1000);
        // game.nextDay();
        // } catch (InterruptedException e) {
        // System.err.println(e);
        // }
        // }
    }

    @FXML
    void nextDay() {
        game.nextDay();
    }

}
