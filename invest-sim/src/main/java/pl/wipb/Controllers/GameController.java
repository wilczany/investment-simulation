package pl.wipb.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import pl.wipb.Game;
import pl.wipb.Player;
import pl.wipb.Investments.Investment;
import pl.wipb.Investments.InvestmentCaretaker;
import pl.wipb.Iterator.Iterator;
import pl.wipb.Iterator.TimeIterator;

import java.util.ArrayList;

public class GameController {
    Game game = Game.getInstance();
    Player player = new Player("testowy"); // przekazywanie z menu? kontroler ekranu tworzenia nowego gracza?
    ArrayList<InvestmentCaretaker> investmentCaretakers;

    @FXML
    ListView<InvestmentCaretaker> caretakersList;
    @FXML
    Button nextDayBtn, showWalletBtn;
    @FXML
    TextField networthField, amountOwnedField, avaiableMoneyField, investmentPriceField;

    @FXML
    void initialize() {
        game.startGame();
        investmentCaretakers = game.getInvestments();
        System.out.println(investmentCaretakers);

        networthField.setText("networth");
        amountOwnedField.setText("amount");
        avaiableMoneyField.setText("avaiable");
        investmentPriceField.setText("invest");

        for (InvestmentCaretaker iC : investmentCaretakers) {
            System.out.println(iC);
            caretakersList.getItems().add(iC);
        }
        System.out.println(caretakersList.getItems());
    }

    @FXML
    private void nextDayBtnHandler(ActionEvent event) {
        game.nextDay();
        refresh();
    }

    @FXML
    private void refresh() {
        caretakersList.getItems().clear();
        for (InvestmentCaretaker i : investmentCaretakers) {
            caretakersList.getItems().add(i);
        }
    }
}
