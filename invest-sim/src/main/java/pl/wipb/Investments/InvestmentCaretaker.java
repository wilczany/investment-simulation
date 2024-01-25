package pl.wipb.Investments;

import java.util.ArrayList;

import pl.wipb.Investments.Investment.InvestmentMemento;

public class InvestmentCaretaker {

    ArrayList<Investment.InvestmentMemento> mementos = new ArrayList<Investment.InvestmentMemento>();
    Investment originator;

    public InvestmentCaretaker(Investment originator) {
        this.originator = originator;
    }

    public double[] getInvestmentHistory() {
        double[] history = new double[mementos.size()];
        for (InvestmentMemento memento : mementos) {
            history[memento.getDay()] = memento.getValue();
        }
        return history;
    }

    public int getDaysCount() {
        return mementos.size();
    }

    public void saveToMemento() {
        mementos.add(originator.saveToMemento());
    }

    public Investment getInvestment() {
        return originator;
    }

    public Investment restoreFromMemento(int day) {
        originator.restoreFromMemento(mementos.get(day));
        return originator;
    }

    public String toString() {
        String s = "";
        s += originator.name + " : " + originator.getValue() + "\n";
        return s;
    }

}
