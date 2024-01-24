package pl.wipb.Controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import pl.wipb.Game;
import pl.wipb.Player;
import pl.wipb.Command.Command;
import pl.wipb.Graph.GraphDirector;
import pl.wipb.Investments.InvestmentCaretaker;

import java.util.ArrayList;
import java.util.Random;

public class GameController {
    Game game = Game.getInstance();
    // TO-DO player ma byc uzywany z Game (spojnosc z UMLem)
    Player player = new Player("testowy"); // przekazywanie z menu? kontroler ekranu tworzenia nowego gracza?
    ArrayList<InvestmentCaretaker> investmentCaretakers;
    GraphDirector graphDirector = new GraphDirector();

    int numberOfPoints = 0;

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
    Label investmentLabel;

    @FXML
    void initialize() {

        // lineChart.getData().add(
        // new XYChart.Series<Double, Double>(FXCollections.observableArrayList(new
        // XYChart.Data<>(0.0, 1.0))));

        lineChart.setVisible(true);
        System.out.println(lineChart);

        game.startGame();
        // graphDirector.appendChart(game.getInvestments().getFirst(), lineChart);

        XYChart.Series<Double, Double> s = graphDirector.graphBuilders[0]
                .parseCaretakerToSeries(game.getInvestments().get(3));

        lineChart.getData().add(
                s);

        investmentCaretakers = game.getInvestments();
        System.out.println(investmentCaretakers);

        refreshText();

        for (InvestmentCaretaker iC : investmentCaretakers) {
            System.out.println(iC);
            caretakersList.getItems().add(iC);
        }
        System.out.println(caretakersList.getItems());

        // // debug wykres
        // Timeline updateChart = new Timeline(new KeyFrame(Duration.seconds(1),
        // chartUpdater));
        // updateChart.setCycleCount(Timeline.INDEFINITE);
        // updateChart.play();

        // listener na wybraną inwestycję
        caretakersList.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<InvestmentCaretaker>() {
                    public void changed(ObservableValue<? extends InvestmentCaretaker> changed,
                            InvestmentCaretaker oldInv,
                            InvestmentCaretaker newInv) {
                        InvestmentCaretaker selected = getSelection();
                        if (selected == null) {
                            investmentLabel.setText("");
                            investmentPriceField.setText("-----");
                            amountOwnedField.setText("-----");
                            buyBtn.setDisable(true);
                            sellBtn.setDisable(true);
                        } else {
                            investmentLabel.setText(selected.getInvestment().getName());
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

    @FXML
    private void nextDayBtnHandler(ActionEvent event) {
        game.nextDay();
        refreshList();
    }

    @FXML
    private void lineChartHandler(ActionEvent event) {
        barChart.setVisible(false);
        lineChart.setVisible(true);
        InvestmentCaretaker selected = getSelection();
        graphDirector.appendChart(selected, lineChart);
        // stackPane.getChildren().add((LineChart<Double, Double>)
        // graphDirector.buildChart(selected));
        // LineChart<Double, Double> lChart = (LineChart) chart;
        // lineChart.getData().clear();
        // lineChart.getData().add(lChart.getData().getFirst());
    }

    EventHandler<ActionEvent> chartUpdater = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Random random = new Random();
            // add a new point to the chart
            lineChart.getData().get(0).getData().add(
                    new XYChart.Data<>((double) numberOfPoints++, random.nextDouble() * 1e8));

            // remove the first point, because that's the left-most.
            // lineChart.getData().getFirst().getData().remove(0);
        }
    };

    @FXML
    private void barChartHandler(ActionEvent event) {

    }

    @FXML
    private void buyHandler(ActionEvent event) {
        Command buyCmd = new BuyCommand();
        buyCmd.execute(1);
    }

    @FXML
    private void sellHandler(ActionEvent event) {
        Command sellCmd = new SellCommand();
        sellCmd.execute(1);
    }

    // context menu
    @FXML
    public void exitGameHandler(ActionEvent event) {
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.close();
    }

    // wykres historii portfela
    @FXML
    private void showWalletHandler(ActionEvent event) {
    }

    private void refreshList() {
        caretakersList.getItems().clear();
        for (InvestmentCaretaker i : investmentCaretakers) {
            caretakersList.getItems().add(i);
        }
    }

    private void refreshText() {
        InvestmentCaretaker selection = getSelection();
        investmentLabel.setText(selection == null ? "" : selection.getInvestment().getName());
        networthField.setText(player.getNetWorth() + "");
        amountOwnedField.setText(selection == null ? "-----" : selection.getInvestment().getAmount() + "");
        availableMoneyField.setText(player.getAvailableMoney() + "");
        investmentPriceField.setText(selection == null ? "-----" : selection.getInvestment().getValue() + "");
    }

    public class BuyCommand implements Command {
        @Override
        public void execute(int amount) {
            InvestmentCaretaker selected = getSelection();
            if (selected == null) {
                return;
            }
            if (selected.getInvestment().getValue() > player.getAvailableMoney()) { // value * kupowana ilość
                // popup o niedoborze pieniedzy?
                return;
            }
            player.buy_invs(selected, 1);
            refreshText();
        }
    }

    public class SellCommand implements Command {
        @Override
        public void execute(int amount) {
            InvestmentCaretaker selected = getSelection();
            if (selected == null) {
                return;
            }
            if (selected.getInvestment().getAmount() <= 0) { // value * kupowana ilość
                // popup o braku aktywów?
                return;
            }
            player.sell_invs(selected, 1);
            refreshText();
        }
    }

}
