package pl.wipb.Wallet;

import java.util.ArrayList;
import pl.wipb.Investments.Investment;

public class WalletHistoryProxy implements IWallet {

    // private implementation wallet; zmienic
    private Wallet implementation;
    private ArrayList<WalletHistory> history_List;

    public WalletHistoryProxy(Wallet implementation) {

        this.implementation = implementation;
        history_List = new ArrayList<WalletHistory>();
        history_List.add(new WalletHistory(
                implementation.getAvailableMoney(),
                implementation.getNetWorth()));
    }

    public ArrayList<WalletHistory> getHistory() {
        return this.history_List;
    }

    public double getNetWorth() {
        return this.implementation.getNetWorth();
    }

    public void sell_invs(Investment inv) {
        implementation.sell_invs(inv);

        update_history();
    }

    public void buy_invs(Investment inv, int amount) {

        implementation.buy_invs(inv, amount);

        update_history();
    }

    public void update_history() {

        WalletHistory wh = new WalletHistory(
                implementation.getAvailableMoney(),
                implementation.getNetWorth());

        history_List.add(wh);
    }
}
