package pl.wipb.Investments;

import java.util.ArrayList;

import pl.wipb.Investments.Investment.InvestmentMemento;

public class InvestmentCaretaker {
    
    ArrayList<Investment.InvestmentMemento> mementos = new ArrayList<Investment.InvestmentMemento>();

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

    public void saveToMemento(){
        mementos.add(originator.saveToMemento());
    }

    public Investment getInvestment(){
        return originator;
    }

    public Investment restoreFromMemento(int day){
        originator.restoreFromMemento(mementos.get(day));
        return originator;
    }


}
