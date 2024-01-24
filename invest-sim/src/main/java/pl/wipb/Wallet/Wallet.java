package pl.wipb.Wallet;

import pl.wipb.Investments.InvestmentCaretaker;

public class Wallet implements IWallet {

    static final double START_MONEY = 10000.0;

    // TO-DO? net_worth = available_money+investments_value

    private double net_worth;
    private double available_money;

    public Wallet() {
        this.net_worth = 0;
        this.available_money = START_MONEY;
    }

    public double getNetWorth() {
        return this.net_worth;
    }

    public double getAvailableMoney() {
        return this.available_money;
    }

    public void sell_invs(InvestmentCaretaker inv, int amount) {
        this.available_money += inv.getInvestment().getValue() * amount;
        inv.getInvestment().substractAmount(amount);
    }

    public void buy_invs(InvestmentCaretaker inv, int amount) {
        this.available_money -= inv.getInvestment().getValue() * amount;
        inv.getInvestment().addAmount(amount);
    }

}
