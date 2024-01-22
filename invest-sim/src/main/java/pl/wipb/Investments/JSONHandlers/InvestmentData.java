package pl.wipb.Investments.JSONHandlers;

import pl.wipb.Investments.Stock;
import pl.wipb.Investments.InvestmentCaretaker;
import pl.wipb.Investments.Investment;

public class InvestmentData {

    private String name;
    private double[] values;
    private static int InvestmentCount;
    private int id;
    private InvestmentCaretaker caretaker;
    private Investment inv;

    public InvestmentData(int ID, String name, double[] values) {
        this.id = ID;
        this.name = name;
        this.values = values;

        this.inv = new Stock(id, name);
        this.caretaker = new InvestmentCaretaker(inv);

        // for (double value : values) {
        // inv.setValue(value, );
        // caretaker.saveInvestment();
        // }

        System.out.println(values);
    }

    public Investment getInvestment() {
        // returns investment on first day
        // this.caretaker.re
        return inv;
    }

    public InvestmentCaretaker getCaretaker() {
        return caretaker;
    }
}
