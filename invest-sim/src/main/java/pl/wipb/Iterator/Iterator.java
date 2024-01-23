package pl.wipb.Iterator;

import pl.wipb.Investments.InvestmentCaretaker;

public interface Iterator {
    public boolean hasNext();

    public InvestmentCaretaker next();

    public int getIndex();
}