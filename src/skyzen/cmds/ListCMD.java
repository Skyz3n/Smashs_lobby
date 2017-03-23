package skyzen.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListCMD implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player p = (Player) sender;
        if(label.equalsIgnoreCase("list")){
            if(Bukkit.getOnlinePlayers().size() >= 2)
                p.sendMessage("§bIl y'a actuellement §e" + Bukkit.getOnlinePlayers().size() + " joueurs §bconnectés sur le serveur.");
            if(Bukkit.getOnlinePlayers().size() == 1)
            p.sendMessage("§bIl y'a actuellement §e" + Bukkit.getOnlinePlayers().size() + " joueur §bconnecté sur le serveur.");
        }
        return false;
    }
}