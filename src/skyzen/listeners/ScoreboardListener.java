package skyzen.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import skyzen.rank.SqlConnection;
import skyzen.utils.ScoreboardSign;

import java.util.HashMap;
import java.util.Map;

public class ScoreboardListener implements Listener {

    private SqlConnection sql;
    public ScoreboardListener(SqlConnection sql) {
        this.sql = sql;
    }
    public Map<Player, ScoreboardSign> boards = new HashMap<>();

    @EventHandler
    public void onJoinSetboard(PlayerJoinEvent e){
        Player p = e.getPlayer();

        ScoreboardSign scoreboard = new ScoreboardSign(p, "§6§nPixelsPalace");
        scoreboard.create();

        scoreboard.setLine(0, "   ");
        scoreboard.setLine(1, "§7Compte: §a" + p.getDisplayName());

        if(sql.getRank(p).getPower() == 0)
        scoreboard.setLine(2, "§7Grade: Joueur");
        else if(sql.getRank(p).getPower() >= 10)
        scoreboard.setLine(2, "§7Grade: " + sql.getRank(p).getName());

        scoreboard.setLine(3, "§7PixelsCoins: §b0");
        scoreboard.setLine(4,"§7Coins: §e" + sql.getBalance(p));
        scoreboard.setLine(5, "  ");
        scoreboard.setLine(6, "§7Hub: §3Bientôt");
        scoreboard.setLine(7, "§7Joueurs: §a" + Bukkit.getOnlinePlayers().size());
        scoreboard.setLine(8, " ");
        scoreboard.setLine(9, "§6play.pixelspalace.fr");
    }
}
