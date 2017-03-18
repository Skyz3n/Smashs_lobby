package skyzen.playercache;

import skyzen.rank.Rank;

public class PlayerData {

    private int coins;
    private Rank rank;

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }
}
