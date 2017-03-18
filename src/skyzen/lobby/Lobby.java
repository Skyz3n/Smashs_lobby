package skyzen.lobby;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import skyzen.cmds.CmdCoins;
import skyzen.event.DoubleJump;
import skyzen.event.PlayerListener;
import skyzen.event.WorldListener;
import skyzen.rank.SqlConnection;

public class Lobby extends JavaPlugin implements Listener {

    public SqlConnection sql;

    ConsoleCommandSender consoleSender = getServer().getConsoleSender();

    private PlayerListener PlayerListener;

    public void onEnable() {
        sql = new SqlConnection(PlayerListener, this, "jdbc:mysql://", "localhost", "smashs", "root", "");
        sql.connection();

        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new DoubleJump(), this);
        getServer().getPluginManager().registerEvents(new WorldListener(), this);

        getCommand("money").setExecutor(new CmdCoins(sql));

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
}
