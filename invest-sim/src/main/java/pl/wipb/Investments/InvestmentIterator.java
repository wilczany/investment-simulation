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

public class InvestmentIterator {

    InvestmentCaretaker caretakers[];

    public InvestmentIterator() {

        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("resources/data/akcje.json")) {

            Object obj = parser.parse(reader);
            JSONArray empList = (JSONArray) obj;
            System.out.println(empList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();

        }
    }
}