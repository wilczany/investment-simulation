package pl.wipb.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import pl.wipb.Game;
import pl.wipb.Player;
import pl.wipb.Graph.GraphDirector;
import pl.wipb.Investments.InvestmentCaretaker;

import java.util.ArrayList;

public class GameController {
    Game game = Game.getInstance();
    // TO-DO player ma byc uzywany z Game (spojnosc z UMLem)
    Player player = new Player("testowy"); // przekazywanie z menu? kontroler ekranu tworzenia nowego gracza?
    ArrayList<InvestmentCaretaker> investmentCaretakers;
    GraphDirector graphDirector = new GraphDirector();

    @FXML
    ListView<InvestmentCaretaker> caretakersList;
    @FXML
    Button nextDayBtn, showWalletBtn, lineChartBtn, barChartBtn, buyBtn, sellBtn;
    @FXML
    TextField networthField, amountOwnedField, availableMoneyField, investmentPriceField;
    @FXML
    MenuBar menuBar;
    @FXML
    LineChart<Double, Double> lineChart;
    @FXML
    BarChart<String, Double> barChart;
    @FXML
    StackPane stackPane;

    @FXML
    void initialize() {
        game.startGame();
        investmentCaretakers = game.getInvestments();
        System.out.println(investmentCaretakers);

        refreshText();

        for (InvestmentCaretaker iC : investmentCaretakers) {
            System.out.println(iC);
            caretakersList.getItems().add(iC);
        }
        System.out.println(caretakersList.getItems());

        // mouseclick gorsze, bo jedynie wybranie myszka uwzglednia
        // caretakersList.setOnMouseClicked(event -> {
        // InvestmentCaretaker selection =
        // caretakersList.getSelectionModel().getSelectedItem();
        // investmentPriceField.setText(selection.getInvestment().getValue() + "");
        // });

        // listener bezpośrednio na wybraną pozycję better, myszka i klawiatura działają
        caretakersList.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<InvestmentCaretaker>() {
                    public void changed(ObservableValue<? extends InvestmentCaretaker> changed,
                            InvestmentCaretaker oldInv,
                            InvestmentCaretaker newInv) {
                        InvestmentCaretaker selected = getSelection();
                        if (selected == null) {
                            investmentPriceField.setText("-----");
                            amountOwnedField.setText("-----");
                            buyBtn.setDisable(true);
                            sellBtn.setDisable(true);
                        } else {
                            investmentPriceField.setText(selected.getInvestment().getValue() + "");
                            amountOwnedField.setText(selected.getInvestment().getAmount() + "");
                            buyBtn.setDisable(false);
                            sellBtn.setDisable(false);
                        }
                    }
                });
    }

    private InvestmentCaretaker getSelection() {
        return caretakersList.getSelectionModel().getSelectedItem();
    }

    private void refreshText() {
        InvestmentCaretaker selection = getSelection();
        networthField.setText(player.getNetWorth() + "");
        amountOwnedField.setText(selection == null ? "-----" : selection.getInvestment().getAmount() + "");
        availableMoneyField.setText(player.getAvailableMoney() + "");
        investmentPriceField.setText(selection == null ? "-----" : selection.getInvestment().getValue() + "");
    }

    @FXML
    private void nextDayBtnHandler(ActionEvent event) {
        game.nextDay();
        refresh();
    }

    @FXML
    private void lineChartHandler(ActionEvent event) {
        barChart.setVisible(false);
        InvestmentCaretaker selected = getSelection();
        // graphDirector.appendChart(selected, lineChart);
        // lineChart.setVisible(true);
        stackPane.getChildren().add((LineChart<Double, Double>) graphDirector.buildChart(selected));
        // LineChart<Double, Double> lChart = (LineChart) chart;
        // lineChart.getData().clear();
        // lineChart.getData().add(lChart.getData().getFirst());

    }

    @FXML
    private void barChartHandler(ActionEvent event) {

    }

    @FXML // TO-DO zmiana na BuyCommand
    private void buyHandler(ActionEvent event) {
        InvestmentCaretaker selected = getSelection();
        if (selected == null) {
            // nie powinno sie wydarzyc przez wylaczanie przyciskow, ale no
            // System.out.println("buy null");
            return;
        }
        if (selected.getInvestment().getValue() > player.getAvailableMoney()) { // value * kupowana ilość
            // popup o niedoborze pieniedzy?
            // System.out.println("buy too poor");
            return;
        }
        // System.out.println("buy arrived at player");
        player.buy_invs(selected, 1);
        refreshText();
    }

    @FXML // TO-DO zmiana na SellCommand
    private void sellHandler(ActionEvent event) {
        InvestmentCaretaker selected = getSelection();
        if (selected == null) {
            // nie powinno sie wydarzyc przez wylaczanie przyciskow, ale no
            // System.out.println("sell null");
            return;
        }
        if (selected.getInvestment().getAmount() <= 0) {
            // popup o braku posiadanych aktywow?
            // System.out.println("sell too many");
            return;
        }
        // System.out.println("sell arrived at player");
        player.sell_invs(selected, 1);
        refreshText();
    }

    // context menu
    @FXML
    public void exitGameHandler(ActionEvent event) {
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void showWalletHandler(ActionEvent event) {
    }

    @FXML
    private void refresh() {
        caretakersList.getItems().clear();
        for (InvestmentCaretaker i : investmentCaretakers) {
            caretakersList.getItems().add(i);
        }
    }
}
