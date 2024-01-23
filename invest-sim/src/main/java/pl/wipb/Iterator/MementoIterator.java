package pl.wipb.Iterator;

import pl.wipb.Investments.InvestmentCaretaker;
import pl.wipb.Investments.Investment;

public class MementoIterator implements Iterator {
    private boolean looped = false;
    private InvestmentCaretaker caretaker;
    private int day;
    private int index = 0;

    public MementoIterator(InvestmentCaretaker caretaker, int day) {
        this.caretaker = caretaker;
        this.day = day;
        this.caretaker.restoreFromMemento(day);
    }

    public boolean hasNext() {
        if (this.index >= this.day)
            return true;
        else {
            return false;
        }
    }

    public InvestmentCaretaker next() {
        this.caretaker.restoreFromMemento(index);

        
        return this.caretaker;
        
        // wykres musi odczytać wartość z Investycji za pomocą metody getInvestment().getValue()

    }
}