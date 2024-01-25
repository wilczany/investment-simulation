package pl.wipb.Iterator;

import pl.wipb.Investments.InvestmentCaretaker;

public class MementoIterator implements Iterator {

    // uzywaney do odczytu wartości inwestycji na przedziale poprzednuch dni

    private InvestmentCaretaker caretaker = null;
    private int day;
    private int index = 0;

    public MementoIterator(InvestmentCaretaker caretaker, int day) {
        this.caretaker = caretaker;
        this.day = day;
        if (day > caretaker.getDaysCount() - 1)
            this.day = caretaker.getDaysCount();
    }

    public boolean hasNext() {
        if (this.index <= this.day)
            return true;
        else {
            return false;
        }
    }

    public int getIndex() {
        return this.index;
    };

    public InvestmentCaretaker next() {
        this.caretaker.restoreFromMemento(index);
        this.index++;

        return this.caretaker;

        // wykres musi odczytać wartość z Investycji za pomocą metody
        // getInvestment().getValue()

    }
}