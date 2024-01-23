package pl.wipb;

import pl.wipb.Wallet.*;

public class Player {
    private String Name;
    private IWallet Wallet;

    private Wallet realWallet;

    public Player(String name) {
        this.Name = name;
        realWallet = new Wallet();

        this.Wallet = new WalletHistoryProxy(realWallet);
    }

    public void ShowWallet() {

    }

    public void SkipDays(int time) /// SkipTime() ?
    {

    }

    public void UpdateWallet() {

    }
}
