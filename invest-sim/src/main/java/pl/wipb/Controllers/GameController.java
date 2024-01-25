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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import pl.wipb.Game;
import pl.wipb.Player;
import pl.wipb.Command.Command;
import pl.wipb.Graph.GraphDirector;
import pl.wipb.Investments.InvestmentCaretaker;
import pl.wipb.Wallet.WalletHistory;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class GameController extends Controller {
    Game game = Game.getInstance();
    // TODO player ma byc uzywany z Game (spojnosc z UMLem), przejrzyc wszystkie
    // uzycia
    Player player; // przekazywanie z menu? kontroler ekranu tworzenia nowego gracza?
    String name;
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

    // TODO refresh po ponownym wejściu


    @FXML
    void initialize() {

        // xdd
        String tmp;
        TextInputDialog dialog = new TextInputDialog();    
        dialog.setTitle("Wprowadź nazwę gracza");
        

        while (true) {
        Optional<String> result = dialog.showAndWait();
        TextField input = dialog.getEditor();
        
        

        if (!result.isPresent() || input.getText() == null || input.getText().trim().isEmpty()) {
            
            Alert al = new Alert(AlertType.ERROR);
            al.setTitle("Błąd");
            al.setContentText("Należy podać nazwę gracza");
            al.showAndWait();
            
        }
        else {
            tmp = input.getText();
            System.out.println("Nazwa gracza: " + tmp);
            // startGame(name);
            break;
        }
        }
        
        player = new Player("testowy");
        game.startGame();

        investmentCaretakers = game.getInvestments();

        refreshText();

        for (InvestmentCaretaker iC : investmentCaretakers) {
            caretakersList.getItems().add(iC);
        }

        // listener na wybraną inwestycję
        caretakersList.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<InvestmentCaretaker>() {
                    public void changed(ObservableValue<? extends InvestmentCaretaker> changed,
                            InvestmentCaretaker oldInv,
                            InvestmentCaretaker newInv) {
                        InvestmentCaretaker selected = getSelection();
                        if (selected == null) {
                            barChart.setVisible(false);
                            lineChart.setVisible(false);
                            investmentLabel.setText("");
                            investmentPriceField.setText("-----");
                            amountOwnedField.setText("-----");
                            buyBtn.setDisable(true);
                            sellBtn.setDisable(true);
                        } else {
                            updateCurrentChart();
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
        player.next_day();

        refreshList();
        refreshText();
    }

    @FXML
    private void lineChartHandler(ActionEvent event) {
        if (lineChart.isVisible()) {
            lineChart.setVisible(false);
        } else {
            barChart.setVisible(false);
            graphDirector.setBuilder(0);
            InvestmentCaretaker selected = getSelection();
            lineChart.getData().clear();
            graphDirector.appendChart(selected, lineChart);
            lineChart.setVisible(true);
        }
    }

    @FXML
    private void barChartHandler(ActionEvent event) {
        if (barChart.isVisible()) {
            barChart.setVisible(false);
        } else {
            lineChart.setVisible(false);
            graphDirector.setBuilder(1);
            InvestmentCaretaker selected = getSelection();
            barChart.getData().clear();
            graphDirector.appendChart(selected, barChart);
            barChart.setVisible(true);
        }
    }

    private void updateCurrentChart() {
        XYChart currChart = null;
        InvestmentCaretaker selected = getSelection();
        if (selected == null)
            return;
        if (barChart.isVisible()) {
            graphDirector.setBuilder(1);
            currChart = barChart;
        } else if (lineChart.isVisible()) {
            graphDirector.setBuilder(0);
            currChart = lineChart;
        } else {
            return;
        }
        currChart.getData().clear();
        graphDirector.appendChart(getSelection(), currChart);
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
        Game.getInstance().restart();
        back();
    }

    // wykres historii portfela
    @FXML
    private void showWalletHandler(ActionEvent event) {
        ArrayList<WalletHistory> history = player.getHistory();
        System.out.println("historia");
        // TODO iterator dla historii
        ArrayList<Double> sums = new ArrayList<Double>();

        for (int i = 0; i < history.size(); i++) {
            sums.add(history.get(i).getAvailableMoney() + history.get(i).getNetWorth());
            // sums.add(player.) dostac sie do portfela(proxy) w graczu i sciagnac aktualny
            // stan historii
        }

        XYChart chart = lineChart;
        graphDirector.setBuilder(0);
        if (barChart.isVisible()) {
            graphDirector.setBuilder(1);
            chart = barChart;
        }

        graphDirector.appendChart(sums, chart);
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
                // TODO informacja dla gracza o niedoborze pieniędzy
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
                // TODO informacja dla gracza o braku aktywów
                return;
            }
            player.sell_invs(selected, 1);
            refreshText();
        }
    }

}
