package skyzen.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MsgCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cNon");
            return false;
        }
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("msg")) {
            if (args.length == 0) {
                p.sendMessage("§c/msg <pseudo> <message>");
            } else if (args.length >= 2) {
                if (cmd.getName().equalsIgnoreCase("msg")) {
                    if (args.length == 0) {
                        p.sendMessage("§c/msg <pseudo> <message>");
                    } else if (args.length >= 2) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target == null) {
                            p.sendMessage("§cDéconnectée");
                            return false;
                        } else {
                            String msg = "";
                            for (int i = 1; i != args.length; i++) {
                                msg += args[i] + " ";
                            }
                            p.sendMessage("§bEnvoyé à §e" + target.getName() + "§8: §f" + msg);
                            target.sendMessage("§bReçu par §e" + p.getName() + "§8: §f" + msg);
                        }
                    } else {
                        p.sendMessage("§c/msg <pseudo> <msg>");
                    }
                }
            }
        }
        return false;
    }
}