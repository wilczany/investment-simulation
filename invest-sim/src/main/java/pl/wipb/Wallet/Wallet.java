package pl.wipb.Wallet;

import java.util.ArrayList;

import pl.wipb.Game;
import pl.wipb.Investments.Investment;
import pl.wipb.Investments.InvestmentCaretaker;

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

    public double getAvailableMoney() {
        return this.available_money;
    }

    public double getTotal() {
        return this.net_worth + this.available_money;
    }

    public void sell_invs(Investment inv, int amount) {
        this.available_money += inv.getValue() * amount;
        inv.substractAmount(amount);
        next_day();
    }

    public void buy_invs(Investment inv, int amount) {
        this.available_money -= inv.getValue() * amount;
        inv.addAmount(amount);
        next_day();
    }

    public void next_day() {
        double tmp = 0;
        ArrayList<InvestmentCaretaker> investments = Game.getInstance().getInvestments();

        for (InvestmentCaretaker inv : investments) {

;
            tmp += inv.getInvestment().getValue() * inv.getInvestment().getAmount();

        }

        this.net_worth = tmp;

    }
}
