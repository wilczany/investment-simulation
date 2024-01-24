package pl.wipb.Wallet;

public class WalletHistory {
    private double net_worth;
    private double available_money;

    protected WalletHistory(double available_money, double net_worth) {
        this.net_worth = net_worth;
        this.available_money = available_money;
    }

    public double getValue() {
        return net_worth;
    }

    public double getAvailableMoney() {
        return available_money;
    }

}