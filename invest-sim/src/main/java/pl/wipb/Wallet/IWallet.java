package pl.wipb.Wallet;

import pl.wipb.Investments.InvestmentCaretaker;

public interface IWallet {
    double getNetWorth();

    double getAvailableMoney();

    void sell_invs(InvestmentCaretaker inv, int amount);

    void buy_invs(InvestmentCaretaker inv, int amount);
}
