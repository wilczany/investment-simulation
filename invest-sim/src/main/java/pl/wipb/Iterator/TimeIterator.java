package pl.wipb.Iterator;

import pl.wipb.Investments.InvestmentCaretaker;

import java.util.ArrayList;

public class TimeIterator implements Iterator {

    // uzywany aby przejsc przez wszystkie inwestycje i zaktualizowac je

    // potencjalna zmiana nazwy, director uzywa w jednej funkcji
    // prawdopodobnie niepotrzeba tho

    int index = 0;

    ArrayList<InvestmentCaretaker> caretakers;

    public TimeIterator(ArrayList<InvestmentCaretaker> caretakers) {

        this.caretakers = caretakers;
    }

    public boolean hasNext() {
        if (this.index < caretakers.size())
            return true;
        else {
            return false;
        }
    }

    public InvestmentCaretaker next() {
        InvestmentCaretaker nxt = this.caretakers.get(index++);
        return nxt;
    }

    public int getIndex() {
        return this.index;
    }

}
