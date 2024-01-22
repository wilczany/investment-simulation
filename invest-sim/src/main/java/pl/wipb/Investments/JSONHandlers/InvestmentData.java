package pl.wipb.Investments.JSONHandlers;

import pl.wipb.Investments.Stock;
import pl.wipb.Investments.InvestmentCaretaker;
import pl.wipb.Investments.Investment;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class InvestmentData {

    private String name;
    private double[] values;
    private static int InvestmentCount;
    private int id;
    private InvestmentCaretaker caretaker;
    private Investment inv;

    @SuppressWarnings("unchecked")
    public InvestmentData(String pathToJSON) {

        this.inv = new Stock(id, name);
        this.caretaker = new InvestmentCaretaker(inv);
        JSONArray jsonList;
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(pathToJSON)) {

            Object obj = parser.parse(reader);
            jsonList = (JSONArray) obj;
            System.out.println(jsonList);

            jsonList.forEach(emp -> jsonToObjects((JSONObject) emp));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
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

    }

}
