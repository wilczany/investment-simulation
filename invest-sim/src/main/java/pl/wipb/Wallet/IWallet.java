package pl.wipb.Wallet;

import pl.wipb.Investments.Investment;

public interface IWallet {
    double getNetWorth();

    double getAvailableMoney();

    double getTotal();

    void sell_invs(Investment inv, int amount);

    void buy_invs(Investment inv, int amount);

    void next_day();
}
