package pl.wipb.Iterator;

import pl.wipb.Investments.InvestmentCaretaker;

import java.util.ArrayList;

public class TimeIterator implements Iterator {

    // uzywany aby przejsc przez wszystkie inwestycje i zaktualizowac je

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
        index++;
        InvestmentCaretaker nxt = this.caretakers.get(index);
        return nxt;
    }

}
