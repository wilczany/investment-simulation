package pl.wipb.Wallet;

import pl.wipb.Investments.Investment;

public interface IWallet {
    double getNetWorth();

    void sell_invs(Investment inv);

    void buy_invs(Investment inv, int amount);
}
