package skyzen.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import skyzen.rank.SqlConnection;
import skyzen.utils.TeamsTagsManager;

public class DispoCMD implements CommandExecutor {
    private SqlConnection sql;

    public DispoCMD(SqlConnection sql) {
        this.sql = sql;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (sql.getRank(p).getPower() == 40 || sql.getRank(p).getPower() == 50 || sql.getRank(p).getPower() == 70) {
                if (cmd.getName().equalsIgnoreCase("disponibilite")) {
                    if (args.length == 0) {
                        p.sendMessage("§7[§aDisponibilité§7] §c/Dispo (On/Off)");
                    }
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("on")) {
                            if(sql.getRank(p).getPower() == 70)
                                TeamsTagsManager.setNameTag(p, "§370", "§a[Resp.Modérateur] ", " §8[§a✔§8]");
                            if(sql.getRank(p).getPower() == 50)
                                TeamsTagsManager.setNameTag(p, "§550", "§a[Modérateur] ", " §8[§a✔§8]");
                            if(sql.getRank(p).getPower() == 40)
                                TeamsTagsManager.setNameTag(p, "§640", "§3[Helper] ", " §8[§a✔§8]");
                            Bukkit.broadcastMessage("§7[§aDisponibilité§7] " + sql.getRank(p).getName() + " " + p.getDisplayName() + " §7est maintenant §aDisponible §7!");
                        }
                        if (args[0].equalsIgnoreCase("off")) {
                            if(sql.getRank(p).getPower() == 70)
                                TeamsTagsManager.setNameTag(p, "§370", "§a[Resp.Modérateur] ", " §8[§c✖§8]");
                            if(sql.getRank(p).getPower() == 50)
                                TeamsTagsManager.setNameTag(p, "§550", "§a[Modérateur] ", " §8[§c✖§8]");
                            if(sql.getRank(p).getPower() == 40)
                                TeamsTagsManager.setNameTag(p, "§640", "§3[Helper] ", " §8[§c✖§8]");
                            Bukkit.broadcastMessage("§7[§aDisponibilité§7] " + sql.getRank(p).getName() + " " + p.getDisplayName() + " §7est maintenant §cOccupé §7!");
                        }
                    }
                }
            } else {
                return true;
            }
        }
        return false;
    }
}