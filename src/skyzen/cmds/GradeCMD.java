package skyzen.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import skyzen.rank.Rank;
import skyzen.rank.SqlConnection;

public class GradeCMD implements CommandExecutor {

    private SqlConnection sql;

    public GradeCMD(SqlConnection sql) {
        this.sql = sql;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length == 0) {
                if(sql.getRank(p).getPower() == 0)
                p.sendMessage("§7[§6Grade§7] §7Vous avez le grade: Joueur");
                else{
                    p.sendMessage("§7[§6Grade§7] §7Vous avez le grade: " + sql.getRank(p).getName());
                }
            }
            if(sql.getRank(p).getPower() >= 80){
                if (args.length >= 1) {
                    if (args[0].equalsIgnoreCase("set")) {
                        if (args.length == 1 || args.length == 2) {
                            p.sendMessage("§7[§6Grade§7] §c/Grade set (grade [0 à 100]) (Joueur)");
                        }
                        if (args.length == 3) {
                            Player cible = Bukkit.getPlayer(args[2]);
                            if (cible != null) {
                                if (Integer.valueOf(args[1]) == 100)
                                    sql.setRank(cible, Rank.ADMINISTRATEUR);
                                if (Integer.valueOf(args[1]) == 80)
                                    sql.setRank(cible, Rank.DEVELOPPEUR);
                                if (Integer.valueOf(args[1]) == 70)
                                    sql.setRank(cible, Rank.RESPMODO);
                                if (Integer.valueOf(args[1]) == 60)
                                    sql.setRank(cible, Rank.YOUTUBEUR);
                                if (Integer.valueOf(args[1]) == 50)
                                    sql.setRank(cible, Rank.MODERATEUR);
                                if (Integer.valueOf(args[1]) == 40)
                                    sql.setRank(cible, Rank.HELPER);
                                if (Integer.valueOf(args[1]) == 30)
                                    sql.setRank(cible, Rank.PREMIUM);
                                if (Integer.valueOf(args[1]) == 20)
                                    sql.setRank(cible, Rank.VIP);
                                if (Integer.valueOf(args[1]) == 10)
                                    sql.setRank(cible, Rank.AMI);
                                if (Integer.valueOf(args[1]) == 0)
                                    sql.setRank(cible, Rank.JOUEUR);
                            }
                        }
                    }
                }else{
                    return true;
                }
            }
        }
        return false;
    }
}
