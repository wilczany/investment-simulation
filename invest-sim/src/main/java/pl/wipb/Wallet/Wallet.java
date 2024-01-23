package pl.wipb.Wallet;

import pl.wipb.Investments.Investment;

public class Wallet implements IWallet {

    static final double START_MONEY = 10000.0;

    private double net_worth;
    private double available_money;

    public Wallet() {
        this.net_worth = 0;
        this.available_money = START_MONEY;

    }

    public double getNetWorth() {
        return this.net_worth;
    }

    double getAvailableMoney() {
        return this.available_money;
    }

    public void sell_invs(Investment inv) {
        inv.getValue();

    }

    public void buy_invs(Investment inv, int amount) {

    }

}
