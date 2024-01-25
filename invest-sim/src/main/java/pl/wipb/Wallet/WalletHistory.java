package pl.wipb.Wallet;

public class WalletHistory {
    private double net_worth;
    private double available_money;
    private int day;

    protected WalletHistory(double available_money, double net_worth) {
        this.net_worth = net_worth;
        this.available_money = available_money;
    }

    public double getNetWorth() {
        return net_worth;
    }

    public double getTotal() {
        return net_worth + available_money;
    }

    public void setNetWorth(double net_worth) {
        this.net_worth = net_worth;
    }

    public double getAvailableMoney() {
        return available_money;
    }

    public void setAvailableMoney(double available_money) {
        this.available_money = available_money;
    }

    public int next_day() {
        return day;
    }
}