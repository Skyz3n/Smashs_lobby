package skyzen.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import skyzen.rank.SqlConnection;

public class BanCMD implements CommandExecutor {

    private SqlConnection sql;
    public BanCMD(SqlConnection sql) {
        this.sql = sql;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args){
        if (cmd.getName().equalsIgnoreCase("ban")) {
            Player p = (Player) sender;
            if (sql.getRank(p).getPower() >= 70 || sql.getRank(p).getPower() == 50) {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.DARK_RED + "» " + ChatColor.RED + "Mettre le joueur!");
                    return true;
                }
                if (args.length == 1) {
                    sender.sendMessage(ChatColor.DARK_RED + "» " + ChatColor.RED + "Veuillez renseigner une raison!");
                }
                Player target = Bukkit.getServer().getPlayer(args[0]);
                OfflinePlayer offline = sender.getServer().getOfflinePlayer(args[0]);
                if (target == null) {
                    offline.setBanned(true);
                    Bukkit.broadcastMessage("§7[§9" + sender.getName() + "§7] §e" + offline.getName() + "§7 a été banni pour: §c" + args[1]);
                    return true;
                }
                if (target.isOp()){
                    sender.sendMessage(ChatColor.DARK_RED + "» " + ChatColor.RED + "Vous ne pouvez pas bannir un administrateur");
                    return true;
                }
                target.kickPlayer("§7Vous avez été banni par §e" + sender.getName() + " §7pour: §c" + args[1] + " §7!");
                target.setBanned(true);
                Bukkit.broadcastMessage("§7[§9" + sender.getName() + "§7] §e" + offline.getName() + "§7 a été banni pour: §c" + args[1]);
            }
        }
        return false;
    }
}
