package pl.wipb.Wallet;

import java.util.ArrayList;

import pl.wipb.Game;
import pl.wipb.Investments.InvestmentCaretaker;

public class Wallet implements IWallet {

    static final double START_MONEY = 10000.0;

    // TODO net_worth = available_money+investments_value

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
        next_day();
    }

    public void buy_invs(InvestmentCaretaker inv, int amount) {
        this.available_money -= inv.getInvestment().getValue() * amount;
        inv.getInvestment().addAmount(amount);
        next_day();
    }

    public void next_day() {
        double tmp = 0;
        ArrayList<InvestmentCaretaker> investments = Game.getInstance().getInvestments();

        for (InvestmentCaretaker inv : investments) {

            System.out.println("pętla w wallet");
            tmp += inv.getInvestment().getValue() * inv.getInvestment().getAmount();
            System.out.println("tmp: " + tmp);
        }

        this.net_worth = tmp;
        System.out.println("net_worth: " + this.net_worth);
    }
}
