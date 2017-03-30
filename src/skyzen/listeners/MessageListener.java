package skyzen.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import skyzen.rank.Rank;
import skyzen.rank.SqlConnection;
import skyzen.utils.TeamsTagsManager;

public class MessageListener implements Listener {

    private SqlConnection sql;

    public MessageListener(SqlConnection sql) {
        this.sql = sql;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        final Player p = e.getPlayer();

        if (sql.getRank(p).getPower() == 100) {
            e.setJoinMessage(sql.getRank(p).getName() + " " + p.getName() + " §7a rejoint le hub !");
            TeamsTagsManager.setNameTag(p, "§1100", "§c[Admin] ", "");
        } else if (sql.getRank(p).getPower() == 80) {
            e.setJoinMessage(sql.getRank(p).getName() + " " + p.getName() + " §7a rejoint le hub !");
            TeamsTagsManager.setNameTag(p, "§280", "§9[Développeur] ", "");
        } else if (sql.getRank(p).getPower() == 70) {
            e.setJoinMessage(sql.getRank(p).getName() + " " + p.getName() + " §7a rejoint le hub !");
            TeamsTagsManager.setNameTag(p, "§370", "§a[Resp.Modérateur] ", " §8[§a✔§8]");
        } else if (sql.getRank(p).getPower() == 60) {
            e.setJoinMessage(sql.getRank(p).getName() + " " + p.getName() + " §7a rejoint le hub !");
            TeamsTagsManager.setNameTag(p, "§460", "§d[Youtubeur] ", "");
        } else if (sql.getRank(p).getPower() == 50) {
            e.setJoinMessage(sql.getRank(p).getName() + " " + p.getName() + " §7a rejoint le hub !");
            TeamsTagsManager.setNameTag(p, "§550", "§a[Modérateur] ", " §8[§a✔§8]");
        } else if (sql.getRank(p).getPower() == 40) {
            e.setJoinMessage(sql.getRank(p).getName() + " " + p.getName() + " §7a rejoint le hub !");
            TeamsTagsManager.setNameTag(p, "§640", "§3[Helper] ", " §8[§a✔§8]");
        } else if (sql.getRank(p).getPower() == 30) {
            e.setJoinMessage(sql.getRank(p).getName() + " " + p.getName() + " §7a rejoint le hub !");
            TeamsTagsManager.setNameTag(p, "§730", "§6[Premium] ", "");
        } else if (sql.getRank(p).getPower() == 20) {
            e.setJoinMessage(sql.getRank(p).getName() + " " + p.getName() + " §7a rejoint le hub !");
            TeamsTagsManager.setNameTag(p, "§820", "§e[Vip] ", "");
        } else if (sql.getRank(p).getPower() == 10) {
            e.setJoinMessage(sql.getRank(p).getName() + " " + p.getName() + " §7a rejoint le hub !");
            TeamsTagsManager.setNameTag(p, "§910", "§f[Ami] ", "");
        } else if (sql.getRank(p).getPower() == 0) {
            e.setJoinMessage(null);
            TeamsTagsManager.setNameTag(p, "§a", "§7", "");
        }

        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("§eConnexion sur PixelsPalace en cours...");
        for (int x = 0; x < 10; x++)
            p.sendMessage("");
        p.sendMessage("§6-----------------------------------------------------");
        p.sendMessage("§cNous vous rappelons que le serveur est en version §bBêta");
        p.sendMessage("§cMerci d'accepter les certains bugs liés au sereur.");
        p.sendMessage("§cN'hésitez pas à nous signaler un bug sur §eforum.pixelspalace.fr");
        p.sendMessage(" ");
        p.sendMessage("§aLe discord est disponible ici: §bhttps://discord.app/ §c(Test)");
        p.sendMessage("§6-----------------------------------------------------");

    }

    @EventHandler
    public void Chat(AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        Rank r = sql.getRank(p);

        if (r.getPower() >= 10)
            e.setFormat(r.getName() + " " + p.getName() + " §8➽§f " + e.getMessage());
        if (r.getPower() == 0)
            e.setFormat(r.getName() + p.getName() + " §8➽§7 " + e.getMessage());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        final Player p = e.getPlayer();

        if (sql.getRank(p).getPower() >= 10)
            e.setQuitMessage(sql.getRank(p).getName() + " " + p.getName() + " §7a quitté le hub !");
        else if (sql.getRank(p).getPower() == 0)
            e.setQuitMessage(null);
    }
}