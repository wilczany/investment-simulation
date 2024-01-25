package pl.wipb.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import pl.wipb.Game;
import pl.wipb.Graph.GraphDirector;
import pl.wipb.Investments.InvestmentCaretaker;
import pl.wipb.Wallet.WalletHistory;
import pl.wipb.Wallet.WalletHistoryProxy;
import pl.wipb.Command.Command;

import java.util.ArrayList;
import java.util.Optional;

public class GameController extends Controller {
    private static final String ButtonBar = null;
    Game game = Game.getInstance();
    boolean walletChart = false;
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
    Text nameText, dayText;
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
        String name;
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Wprowadź nazwę gracza");
        dialog.setHeaderText("Wprowadź nazwę gracza:");

        while (true) {
            Optional<String> result = dialog.showAndWait();
            TextField input = dialog.getEditor();

            if (!result.isPresent() || input.getText() == null || input.getText().trim().isEmpty()
                    || input.getText().length() > 17) {

                Alert al = new Alert(AlertType.ERROR);
                al.setTitle("Błąd");
                al.setHeaderText("Nieprawidłowa nazwa gracza");
                al.setContentText("Nazwa gracza powinna mieć od 1 do 17 znaków");
                al.showAndWait();

            } else {
                name = input.getText();
                System.out.println("Nazwa gracza: " + name);
                // startGame(name);
                break;
            }
        }

        nameText.setText("Gracz: " + name);
        game.startGame(name);

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
        if (game.isLastDay()) {
            dayText.setText("Koniec symulacji");
            game.endGame();
            showHighScores();
            Game.getInstance().restart();
            back();
            // TODO boolean dla zakonczonej gry, zmiana nazwy przycisku nastepny dzien na
            // koniec gry, dodac if dla tego booleana na poczatku tego handlera

        } else {
            game.nextDay();
            game.getPlayer().next_day();
            dayText.setText("Dzień: " + game.getInvestments().get(0).getInvestment().getDay());

            refreshList();
            refreshText();
            hideCharts();
        }
    }

    @FXML
    private void lineChartHandler(ActionEvent event) {
        if (getSelection() == null)
            return;
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
        if (getSelection() == null)
            return;
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
        buyCmd.execute();
    }

    @FXML
    private void sellHandler(ActionEvent event) {
        Command sellCmd = new SellCommand();
        sellCmd.execute();
    }

    private int getAmount(int max) {
        Dialog<Integer> dialog = new Dialog<>();
        dialog.setTitle("Ilość");
        dialog.setHeaderText("Podaj ilość aktywów");
        ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
        Spinner<Integer> spinner = new Spinner<>(1, max, 1);
        dialog.getDialogPane().setContent(spinner);
        dialog.getDialogPane().getButtonTypes().add(okButton);
        dialog.showAndWait();
        return spinner.getValue();
        // return
    }

    // context menu
    @FXML
    public void exitGameHandler(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Wyjście z gry");
        alert.setHeaderText("Czy na pewno chcesz wyjść z gry?");
        alert.setContentText("Wszystkie dane zostaną utracone");

        if (alert.showAndWait().get() == ButtonType.OK) {
            Game.getInstance().restart();
            back();
        }

    }

    @FXML
    private void showWalletHandler(ActionEvent event) {
        ArrayList<WalletHistory> history = game.getPlayer().getHistory();
        System.out.println("historia");
        // TODO iterator dla historii
        ArrayList<Double> sums = new ArrayList<Double>();

        for (int i = 0; i < history.size(); i++) {
            sums.add(history.get(i).getAvailableMoney() + history.get(i).getNetWorth());
        }

        // brudny kod !!!
        WalletHistoryProxy w = (WalletHistoryProxy) game.getPlayer().getWallet();
        sums.add(w.getCurrentHistory().getTotal());

        // caretakersList.getSelectionModel().clearSelection();

        XYChart chart = lineChart;
        graphDirector.setBuilder(0);
        if (barChart.isVisible()) {
            graphDirector.setBuilder(1);
            chart = barChart;
        }

        chart.getData().clear();
        graphDirector.appendChart(sums, chart);
        chart.setVisible(true);
    }

    private void infoDialog(String text) {
        Alert al = new Alert(AlertType.INFORMATION);
        al.setTitle("Nieprawidłowa akcja");
        al.setContentText(text);
        al.showAndWait();
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
        networthField.setText(game.getPlayer().getNetWorth() + "");
        amountOwnedField.setText(selection == null ? "-----" : selection.getInvestment().getAmount() + "");
        availableMoneyField.setText(game.getPlayer().getAvailableMoney() + "");
        investmentPriceField.setText(selection == null ? "-----" : selection.getInvestment().getValue() + "");
    }

    private void hideCharts() {
        lineChart.setVisible(false);
        barChart.setVisible(false);
    }

    public class BuyCommand implements Command {
        @Override
        public void execute() {
            InvestmentCaretaker selected = getSelection();
            if (selected == null) {
                return;
            }
            if (selected.getInvestment().getValue() > game.getPlayer().getAvailableMoney()) { 
                infoDialog("Nie posiadasz wystarczająco gotówki");
                return;
            }
            int max_amount = Math.floorDiv(
                    (int) game.getPlayer().getAvailableMoney(),
                    (int) selected.getInvestment().getValue());
            

            int amount_to_buy = getAmount(max_amount);

            game.getPlayer().buy_invs(selected, amount_to_buy);

            refreshText();
        }
    }

    public class SellCommand implements Command {
        @Override
        public void execute() {
            InvestmentCaretaker selected = getSelection();
            if (selected == null) {
                return;
            }
            if (selected.getInvestment().getAmount() <= 0) { // value * kupowana ilość
                infoDialog("Nie posiadasz danych aktywów");
                return;
            }
            int max_amount = selected.getInvestment().getAmount();
            int amount_to_sell = getAmount(max_amount);
            game.getPlayer().sell_invs(selected, amount_to_sell);
            refreshText();
        }
    }

}
