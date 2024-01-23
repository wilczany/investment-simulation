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

public class ObligationData {

    private ArrayList<InvestmentCaretaker> caretakers = new ArrayList<InvestmentCaretaker>();

    public ObligationData(String pathToJSON) {

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
        double intereset_rate = json.getDouble("interest_rate");
        int saving_rate = json.getInt("saving_rate");
        Investment inv = new Obligation(name);
        InvestmentCaretaker caretaker = new InvestmentCaretaker(inv);

        for (int i = 0; i < LIMIT; i++) {
            System.out.println((i + 1) + " " + saving_rate);
            if ((i + 1) % saving_rate == 0) {
                System.out.println(value * intereset_rate);
                value *= intereset_rate;
            }
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
