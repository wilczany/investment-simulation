package pl.wipb;

import java.util.ArrayList;

import pl.wipb.Investments.InvestmentCaretaker;
import pl.wipb.Wallet.*;

public class Player {
    private String name;
    private IWallet wallet;

    private Wallet realWallet;

    public Player(String name) {
        this.name = name;
        realWallet = new Wallet();
        wallet = new WalletHistoryProxy(realWallet);
    }

    public double getNetWorth() {
        return this.wallet.getNetWorth();
    }

    public double getAvailableMoney() {
        return this.wallet.getAvailableMoney();
    }

    public IWallet getWallet() {
        return this.wallet;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<WalletHistory> getHistory() {
        // nie miałem pomysłu jak inaczej się do tego dostać...
        WalletHistoryProxy w = (WalletHistoryProxy) this.wallet;
        return w.getHistory();
    }

    public void SkipDays(int time) /// SkipTime() ?
    {

    }

    public double getTotal() {
        return this.wallet.getTotal();
    }

    public void sell_invs(InvestmentCaretaker inv, int amount) {
        this.wallet.sell_invs(inv, amount);
    }

    public void buy_invs(InvestmentCaretaker inv, int amount) {
        this.wallet.buy_invs(inv, amount);
    }

    public void next_day() {
        this.wallet.next_day();
    }
}
