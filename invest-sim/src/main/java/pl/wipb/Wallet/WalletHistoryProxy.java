package pl.wipb.Wallet;

import java.util.List;

public class WalletHistoryProxy {

    //private  implementation wallet;        zmienic
    private WalletHistory history;
    private List<WalletHistory> historyList;
    public List<WalletHistory> getHistory() 
    {
        return historyList;
    }  
}


