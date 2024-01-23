package pl.wipb.Investments.JSONHandlers;

import pl.wipb.Investments.Stock;
import pl.wipb.Investments.InvestmentCaretaker;
import pl.wipb.Investments.Investment;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class CryptoData {

    // private ArrayList <InvestmentCaretaker> caretakers = new ArrayList<InvestmentCaretaker>();

    // public CryptoData(String pathToJSON) {

    //     JSONArray jsonList;

    //     try  {
    //         Files.readAllBytes(Paths.get(pathToJSON));
    //         String content = new String((Files.readAllBytes(Paths.get(pathToJSON))));
    //         jsonList = new JSONArray(content);
    //         jsonList.forEach(emp -> jsonToObjects((JSONObject) emp));
            
    //     } catch (FileNotFoundException e) {
    //         e.printStackTrace();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }

    // }

    // private void jsonToObjects(JSONObject json) {
    //     String name = (String) json.get("name");
    //     JSONArray values = json.getJSONArray("values");
    //     Investment inv = new Stock(name);
    //     InvestmentCaretaker caretaker = new InvestmentCaretaker(inv);
        
    //     double value = values.getDouble(i);
    //     inv.setValue(value, i);
    //     caretaker.saveToMemento();
        
    //     caretaker.restoreFromMemento(0);
    //     caretakers.add(caretaker);
    // }

    // public ArrayList<InvestmentCaretaker> getCaretakers() {
    //     return caretakers;
    // }

}
