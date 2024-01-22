package pl.wipb.Investments;

import java.util.List;

import pl.wipb.Investments.Investment.InvestmentMemento;

public class InvestmentCaretaker {
    
    List<Investment.InvestmentMemento> mementos;

    Investment originator;


    public InvestmentCaretaker(Investment originator) {
        this.originator = originator;
    }

    public double[] getInvestmentHistory(){
        double [] history = new double[mementos.size()];
        for (InvestmentMemento memento : mementos) {
            System.out.println(memento.getValue());
            history[memento.getDay()] = memento.getValue();
        }
        return history;
    }

    public void saveInvestment(){
        mementos.add(originator.saveToMemento());
    }
}
