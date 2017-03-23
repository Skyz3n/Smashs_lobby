package skyzen.lobby;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.plugin.java.JavaPlugin;
import skyzen.cmds.*;
import skyzen.event.ActionBarMessage;
import skyzen.event.DoubleJump;
import skyzen.event.PlayerListener;
import skyzen.event.WorldListener;
import skyzen.menus.InventoryListener;
import skyzen.menus.Jeux;
import skyzen.menus.Teleportation;
import skyzen.playercache.PlayerData;
import skyzen.playercache.PlayerDataManager;
import skyzen.rank.SqlConnection;

import java.util.HashMap;
import java.util.Map;

public class Lobby extends JavaPlugin implements Listener {

    public SqlConnection sql;
    public PlayerDataManager dataManager = new PlayerDataManager(this);
    public Map<Player, PlayerData> dataPlayers = new HashMap<>();

    ConsoleCommandSender consoleSender = getServer().getConsoleSender();

    public void onEnable() {
        sql = new SqlConnection(this, "jdbc:mysql://", "localhost", "smashs", "root", "");
        sql.connection();

        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new WorldListener(sql), this);
        getServer().getPluginManager().registerEvents(new DoubleJump(sql), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(sql), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        getServer().getPluginManager().registerEvents(new ActionBarMessage(this), this);

        getCommand("coins").setExecutor(new CoinsCMD(sql));
        getCommand("grade").setExecutor(new GradeCMD(sql));
        getCommand("msg").setExecutor(new MsgCMD());
        getCommand("jeux").setExecutor(new Jeux());
        getCommand("tp").setExecutor(new Teleportation());
        getCommand("list").setExecutor(new ListCMD());
        getCommand("ban").setExecutor(new BanCMD(sql));

        consoleSender.sendMessage(" ");
        consoleSender.sendMessage(ChatColor.GREEN + "==================================================");
        consoleSender.sendMessage(ChatColor.GREEN + "------------------ SMASHSLOBBY -------------------");
        consoleSender.sendMessage(" ");
        consoleSender.sendMessage(ChatColor.RED + "License:");
        consoleSender.sendMessage(ChatColor.RED + "   - Ne pas modifier le plugin!");
        consoleSender.sendMessage(" ");
        consoleSender.sendMessage(ChatColor.YELLOW + "Plugin par Skyzen:");
        consoleSender.sendMessage(ChatColor.YELLOW + "> https://skyzen.fr/");
        consoleSender.sendMessage(" ");
        consoleSender.sendMessage(ChatColor.GREEN + "==================================================" + ChatColor.RESET);
    }

    public void onDisable() {
        consoleSender.sendMessage(ChatColor.RED + "Plugin de Skyzen > OFF");
        sql.disconnect();
    }

    @EventHandler
    public void onJoinAvant(PlayerLoginEvent e) {
        final Player p = e.getPlayer();
        sql.createAccount(p);
        dataManager.loadPlayerData(p);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        final Player p = e.getPlayer();
        dataManager.savePlayerData(p);

        for (Player p1 : Bukkit.getOnlinePlayers())
            p1.setLevel(Bukkit.getOnlinePlayers().size() - 1);
    }
}
