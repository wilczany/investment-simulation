package pl.wipb.Investments.JSONHandlers;

import pl.wipb.Investments.Stock;
import pl.wipb.Investments.InvestmentCaretaker;
import pl.wipb.Investments.Investment;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

public class InvestmentData {

    private String name;
    private double[] values;
    private static int InvestmentCount;
    private int id;
    private InvestmentCaretaker caretaker;
    private Investment inv;

    public InvestmentData(String pathToJSON) {

        this.inv = new Stock(id, name);
        this.caretaker = new InvestmentCaretaker(inv);
        JSONArray jsonList;

       try  {
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

    public Investment getInvestment() {
        // returns investment on first day
        // this.caretaker.re
        return inv;
    }

    public InvestmentCaretaker getCaretaker() {
        return caretaker;
    }

    private void jsonToObjects(JSONObject json) {
        String name = (String) json.get("name");
        JSONArray values = json.getJSONArray("values");
        for (int i = 0; i < values.length(); i++) {
            
        }
    }

}
