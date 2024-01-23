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
import pl.wipb.Investments.JSONHandlers.StockData;
import pl.wipb.Iterator.TimeIterator;

public class Game {

    private List<Investment> Investment;
    private int day;
    private static Game instance = null;
    private Player player;
    private List<Double> high_score;
    private ArrayList<InvestmentCaretaker> caretakers = new ArrayList<InvestmentCaretaker>();

    public Game GetInstance() {
        if (Game.instance == null) {
            Game.instance = new Game();
        }
        return Game.instance;
    }

    public void StartGame() {
        initDatafromJSON();
    }

    public void EndGame() {

    }

    public void AddPlayer(Player player) {

    }

    public void NextDay() {
        Iterator ite = new TimeIterator(caretakers);

        while (ite.hasNext()) {
            InvestmentCaretaker caretaker = ite.next();
            caretaker.restoreFromMemento(day + 1);
        }

        day++;

    }

    public void skipDays(int number) {
        day += number;
    }
    // debug

    public void printStockValues() {
        for (InvestmentCaretaker investmentCaretaker : caretakers) {
            System.out.println(investmentCaretaker.getInvestment().getValue());
        }
    }

    public Map<String, ArrayList<Double>> getStockValuesTillDay(int day) {
        Map<String, ArrayList<Double>> map = new HashMap<>();
        for (InvestmentCaretaker investmentCaretaker : caretakers) {
            MementoIterator it = new MementoIterator(investmentCaretaker, day);
            ArrayList<Double> values = new ArrayList<Double>();

            InvestmentCaretaker currentCaretaker;
            while (it.hasNext()) {
                currentCaretaker = it.next();
                values.add(currentCaretaker.getInvestment().getValue());
            }
            map.put(currentCaretaker.getInvestment().getName(), values); // chuj tam to debugowe
        }
        return map;
    }

    private void initDatafromJSON() {
        StockData data = new StockData("resources/data/akcje.json");
        ArrayList<InvestmentCaretaker> tmp = data.getCaretakers();

        for (InvestmentCaretaker investmentCaretaker : tmp) {
            caretakers.add(investmentCaretaker);
        }

        // TODO zrob to samo dla reszty inwestycji
    }

}