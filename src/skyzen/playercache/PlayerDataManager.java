package skyzen.playercache;

import org.bukkit.entity.Player;

import skyzen.event.PlayerListener;

public class PlayerDataManager {

    private PlayerListener pl;

    public PlayerDataManager(PlayerListener pl) {
        this.pl = pl;
    }

    public void savePlayerData(Player p) {
        if (pl.dataPlayers.containsKey(p)) {
            pl.sql.updatePlayerData(p);
        }
    }

    public void loadPlayerData(Player p) {
        if (!pl.dataPlayers.containsKey(p)) {
            PlayerData pData = pl.sql.getPlayerData(p);
            pl.dataPlayers.put(p, pData);
        }
    }
}
