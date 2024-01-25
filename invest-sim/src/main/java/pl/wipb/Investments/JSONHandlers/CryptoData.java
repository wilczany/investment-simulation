package pl.wipb.Investments.JSONHandlers;

import pl.wipb.Investments.InvestmentCaretaker;
import pl.wipb.Investments.Obligation;
import pl.wipb.Investments.Investment;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class CryptoData {

    private ArrayList<InvestmentCaretaker> caretakers = new ArrayList<InvestmentCaretaker>();

    public CryptoData(String pathToJSON) {

        JSONArray jsonList;

        try {
            Files.readAllBytes(Paths.get(pathToJSON));
            String content = new String((Files.readAllBytes(Paths.get(pathToJSON))));
            jsonList = new JSONArray(content);
            jsonList.forEach(emp -> jsonToObjects((JSONObject) emp));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void jsonToObjects(JSONObject json) {
        int LIMIT = 10; // na to trzeba jakos zaradzic, pobrac skads czy cokolwiek

        String name = (String) json.get("name");
        double value = json.getDouble("value");
        double price_fluctuations = json.getDouble("price_fluctuations");
        Investment inv = new Obligation(name);
        InvestmentCaretaker caretaker = new InvestmentCaretaker(inv);
        Random rand = new Random();

        for (int i = 0; i < LIMIT; i++) {
            double fluctuation = 1 - price_fluctuations
                    + ((1 + price_fluctuations) - (1 - price_fluctuations)) * rand.nextDouble();
            value *= fluctuation;
            inv.setValue(value, i);
            caretaker.saveToMemento();
        }
        caretaker.restoreFromMemento(0);
        caretakers.add(caretaker);
    }

    public ArrayList<InvestmentCaretaker> getCaretakers() {
        return caretakers;
    }

}
