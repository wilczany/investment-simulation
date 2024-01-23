package pl.wipb.Wallet;

public class WalletHistory {
    private double net_worth;
    private double avaiable_money;

    protected WalletHistory(double avaiable_money, double net_worth) {
        this.net_worth = net_worth;
        this.avaiable_money = avaiable_money;

    }

    public double getValue() {
        return net_worth;
    }

    public double getAvaibleMoney() {
        return avaiable_money;
    }

}