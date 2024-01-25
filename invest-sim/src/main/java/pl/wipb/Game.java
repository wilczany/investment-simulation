package pl.wipb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import pl.wipb.Iterator.Iterator;
import pl.wipb.Iterator.MementoIterator;

import java.util.List;
import java.util.Map;

import pl.wipb.Investments.Investment;
import pl.wipb.Investments.InvestmentCaretaker;
import pl.wipb.Investments.JSONHandlers.CryptoData;
import pl.wipb.Investments.JSONHandlers.ObligationData;
import pl.wipb.Investments.JSONHandlers.StockData;
import pl.wipb.Iterator.TimeIterator;

public class Game {

    private List<Investment> Investment;
    private int day;
    private static Game instance = null;
    private Player player;
    private String player_name;
    private List<Double> high_score;
    private ArrayList<InvestmentCaretaker> caretakers = new ArrayList<InvestmentCaretaker>();
    private int game_length;

    public static Game getInstance() {
        if (Game.instance == null) {
            Game.instance = new Game();
        }
        return Game.instance;
    }

    public void startGame(String name) {
        this.player_name = name;
        this.player = new Player(name);
        initDatafromJSON();
    }

    public void restart() {
        Investment = null;
        day = 0;
        player = null;
        high_score = null;
        caretakers = new ArrayList<InvestmentCaretaker>();
        player_name = null;
    }

    public void endGame() {
        Double total = player.getTotal();

        File file = new File("resources/highscore.txt");
        // HashMap<String, Double> scores = new HashMap<>();
        List<Double> scores = new ArrayList<Double>();
        List<String> names = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" - ");
                scores.add(Double.parseDouble(parts[1]));
                names.add(parts[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        int i = 0; // index of names from new leaderboard
        int j = 0; // index of names from old leaderboard
        boolean updated = false;
        while (i < 3) {
            if (!updated && total > scores.get(i)) {
                sb.append(player_name);
                sb.append(" - ");
                sb.append(total);
                sb.append("\n");
                i++;
            } else {
                sb.append(names.get(j));
                sb.append(" - ");
                sb.append(scores.get(j));
                sb.append("\n");
                j++;
                i++;
            }
        }

        try {
            java.io.FileWriter fw = new java.io.FileWriter("resources/highscore.txt");
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addPlayer(Player player) {

    }

    public void nextDay() {
        Iterator ite = new TimeIterator(caretakers);
        day++;

        while (ite.hasNext()) {
            InvestmentCaretaker caretaker = null;
            caretaker = ite.next();
            caretaker.restoreFromMemento(day);
        }
        // day++;
    }

    public boolean isLastDay() {
        return day == game_length;
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
        CryptoData data3 = new CryptoData("resources/data/crypto.json");
        ArrayList<InvestmentCaretaker> tmp = data1.getCaretakers();
        tmp.addAll(data2.getCaretakers());
        tmp.addAll(data3.getCaretakers());
        this.game_length = tmp.get(0).getDaysCount();

        for (InvestmentCaretaker investmentCaretaker : tmp) {
            caretakers.add(investmentCaretaker);
        }
    }

    public Player getPlayer() {
        return this.player;
    }

}