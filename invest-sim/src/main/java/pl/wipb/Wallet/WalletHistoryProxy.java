package pl.wipb.Wallet;

import java.util.ArrayList;
import pl.wipb.Investments.InvestmentCaretaker;

public class WalletHistoryProxy implements IWallet {

    // private implementation wallet; zmienic
    private Wallet implementation;
    private ArrayList<WalletHistory> history_List;

    private WalletHistory wh;

    public WalletHistoryProxy(Wallet implementation) {

        this.implementation = implementation;
        history_List = new ArrayList<WalletHistory>();

        this.wh = new WalletHistory(
                implementation.getAvailableMoney(),
                implementation.getNetWorth());
    }

    public ArrayList<WalletHistory> getHistory() {
        return this.history_List;
    }

    public double getNetWorth() {
        return this.implementation.getNetWorth();
    }

    public double getAvailableMoney() {
        return this.implementation.getAvailableMoney();
    }

    public void sell_invs(InvestmentCaretaker inv, int amount) {
        implementation.sell_invs(inv, amount);

        update_wallet_history();
    }

    public void buy_invs(InvestmentCaretaker inv, int amount) {

        implementation.buy_invs(inv, amount);

        update_wallet_history();
    }

    public void update_wallet_history() {

        wh.setNetWorth(implementation.getNetWorth());
        wh.setAvailableMoney(implementation.getAvailableMoney());
    }

    public void next_day() { // aka next_day
        history_List.add(wh);

        implementation.next_day();

        wh = new WalletHistory(
                implementation.getAvailableMoney(),
                implementation.getNetWorth());

    }
}
