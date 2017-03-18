package skyzen.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import skyzen.rank.SqlConnection;

public class CmdCoins implements CommandExecutor {

    private SqlConnection sql;

    public CmdCoins(SqlConnection sql) {
        this.sql = sql;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length == 0) {
                int balance = sql.getBalance(p);
                p.sendMessage("§7[§6Coins§7] §7Vous avez actuellement §e" + balance + " §7Coins.");
            }

            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("add")) {
                    if (args.length == 1 || args.length == 2) {
                        p.sendMessage("§7[§6Coins§7] §c/Coins add (montant) (Joueur)");
                    }
                    if (args.length == 3) {
                        Player cible = Bukkit.getPlayer(args[2]);
                        if (cible != null) {
                            int montant = Integer.valueOf(args[1]);
                            sql.addMoney(cible, montant);
                            cible.sendMessage("§7[§6Coins§7] §7Vous avez reçu §e" + montant + " §7Coins de la part de §b" + p.getName());
                            cible.sendMessage("§7[§6Coins§7] §7Vous venez d'envoyer §e" + montant + " §7Coins à §b" + cible.getName());
                        }
                    }
                }

                if (args[0].equalsIgnoreCase("remove")) {
                    if (args.length == 1 || args.length == 2) {
                        p.sendMessage("§7[§6Coins§7] §c/Coins remove (montant) (Joueur)");
                    }

                    if (args.length == 3) {
                        Player cible = Bukkit.getPlayer(args[2]);
                        if (cible != null) {
                            int montant = Integer.valueOf(args[1]);
                            sql.removeMoney(cible, montant);
                        }
                    }
                }
            }
        }
        return false;
    }
}