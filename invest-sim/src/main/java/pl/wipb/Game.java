package pl.wipb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

import pl.wipb.Iterator.Iterator;
import pl.wipb.Iterator.MementoIterator;

import java.util.List;
import java.util.Map;

import pl.wipb.Investments.Investment;
import pl.wipb.Investments.InvestmentCaretaker;
import pl.wipb.Investments.JSONHandlers.ObligationData;
import pl.wipb.Investments.JSONHandlers.StockData;
import pl.wipb.Iterator.TimeIterator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.fxml.FXML;

public class Game {

    private List<Investment> Investment;
    private int day;
    private static Game instance = null;
    private Player player;
    private List<Double> high_score;
    private ArrayList<InvestmentCaretaker> caretakers = new ArrayList<InvestmentCaretaker>();

    public static Game getInstance() {
        if (Game.instance == null) {
            Game.instance = new Game();
        }
        return Game.instance;
    }

    public void startGame() {
        initDatafromJSON();
    }

    public void endGame() {

    }

    public void addPlayer(Player player) {

    }

    public void nextDay() {
        Iterator ite = new TimeIterator(caretakers);
        System.out.println("start");

        while (ite.hasNext()) {
            System.out.println("pętla");
            InvestmentCareta    ker caretaker = ite.next();
            caretaker.restoreFromMemento(day++);
        }
        System.out.println("koniec");
    }

    public void skipDays(int number) {
        day += number;
    }

    public void printStockValues() {
        for (InvestmentCaretaker investmentCaretaker : caretakers) {
            System.out.println(investmentCaretaker.getInvestment().getValue());
        }
    }

    public ArrayList<InvestmentCaretaker> getInvestments() {
        return caretakers;
    }

    public Map<String, ArrayList<Double>> getStockValuesTillDay(int day) {
        if (day < 1)
            return new HashMap<>();
        Map<String, ArrayList<Double>> map = new HashMap<>();
        for (InvestmentCaretaker investmentCaretaker : caretakers) {
            MementoIterator it = new MementoIterator(investmentCaretaker, day);
            ArrayList<Double> values = new ArrayList<Double>();

            InvestmentCaretaker currentCaretaker;
            do {
                currentCaretaker = it.next();
                values.add(currentCaretaker.getInvestment().getValue());
            } while (it.hasNext());
            map.put(currentCaretaker.getInvestment().getName(), values);
        }
        return map;
    }

    private void initDatafromJSON() {
        StockData data1 = new StockData("resources/data/akcje.json");
        ObligationData data2 = new ObligationData("resources/data/obligacje.json");
        ArrayList<InvestmentCaretaker> tmp = data1.getCaretakers();
        tmp.addAll(data2.getCaretakers());

        for (InvestmentCaretaker investmentCaretaker : tmp) {
            caretakers.add(investmentCaretaker);
        }

        // TODO zrob to samo dla reszty inwestycji
    }

}