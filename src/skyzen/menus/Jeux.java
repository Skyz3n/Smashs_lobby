package skyzen.menus;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Jeux implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player p = (Player) sender;
        if(label.equalsIgnoreCase("jeux")){
            JeuxOpen(p);
        }
        return false;
    }

    public void JeuxOpen(Player player){
        Inventory inv = Bukkit.createInventory(null, (9*6), "Sélecteur de jeux");

            player.openInventory(inv);
    }
}