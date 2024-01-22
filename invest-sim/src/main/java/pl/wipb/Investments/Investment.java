package pl.wipb.Investments;

import java.util.concurrent.atomic.AtomicLong;

public abstract class Investment {

    static final AtomicLong NEXT_ID = new AtomicLong(0);
    final long id = NEXT_ID.getAndIncrement();

    String name;
    double value;
    int day; // state

    public Investment(String name) {
       
        this.name = name;
        // this.value = value;
        // this.days = days;
    }

    public void setValue(double value, int day) {
        this.value = value;
        this.day = day;
    }

    public InvestmentMemento saveToMemento() {
        return new InvestmentMemento(this.day, this.value);
    }

    public void restoreFromMemento(InvestmentMemento memento) {
        this.value = memento.getValue();
        this.day = memento.getDay();
    }

    // TODO zrobienie klonowania do róznych typów inwestycji
    // trzeba rozpatrzyc na czym to bedzie polegac bo nie pameitam

    @Override
    protected Object clone() throws CloneNotSupportedException {

        return super.clone();
    }

    // memento
    class InvestmentMemento {
        
        private int day;
        private double value;

        public InvestmentMemento(int day, double value) {
            this.value = value;
            this.day = day;
        }

        protected double getValue() {
            return value;
        }
        protected int getDay() {
            return day;
        }

    }
}
