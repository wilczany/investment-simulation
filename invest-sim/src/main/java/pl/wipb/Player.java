package pl.wipb;

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

    public void SkipDays(int time) /// SkipTime() ?
    {

    }

    public void sell_invs(InvestmentCaretaker inv, int amount) {
        this.wallet.sell_invs(inv, amount);
    }

    public void buy_invs(InvestmentCaretaker inv, int amount) {
        this.wallet.buy_invs(inv, amount);
    }

    // public void UpdateWallet() { ^ zamiast tego?

    // }
}
