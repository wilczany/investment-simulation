package pl.wipb.Investments;
import java.util.ArrayList;


import pl.wipb.Investments.JSONHandlers.StockData;


public class InvestmentIterator {

    ArrayList <InvestmentCaretaker> caretakers = new ArrayList<InvestmentCaretaker>();

    public InvestmentIterator() {
        StockData data = new StockData("resources/data/akcje.json");
        ArrayList <InvestmentCaretaker> tmp = data.getCaretakers();

        for (InvestmentCaretaker investmentCaretaker : tmp) {
           caretakers.add(investmentCaretaker);
        }

        System.out.println(
            caretakers.get(0).getInvestment().value
            );

    }
}