package pl.wipb.Investments;


public abstract class Investment {
    int id;
    String name;
    double value;
    int day; // state

    public Investment(int id, String name) {
        this.id = id;
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
