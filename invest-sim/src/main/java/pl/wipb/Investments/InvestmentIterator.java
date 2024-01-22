package pl.wipb.Investments;

// import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
// import java.io.InputStream;
// import java.nio.file.Paths;

import org.json.simple.JSONArray;
// import org.json.JSONObject;
// import org.json.JSONTokener;
import org.json.simple.parser.JSONParser;

import pl.wipb.Investments.JSONHandlers.InvestmentData;


public class InvestmentIterator {

    InvestmentCaretaker caretakers[];

    public InvestmentIterator() {
        InvestmentData data = new InvestmentData("resources/data/akcje.json");
        // InvestmentCaretaker caretaker = data.getCaretaker();

  

    }
}