package skyzen.menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Teleportation implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player p = (Player) sender;
        if(label.equalsIgnoreCase("tp")){
            TpOpen(p);
        }
        return false;
    }

    public void TpOpen(Player player){
        Inventory inv = Bukkit.createInventory(null, (9*6), "Téléportation");
        for(Player playerOnline : Bukkit.getOnlinePlayers()){
            inv.addItem(getHead(playerOnline));
        }
        player.openInventory(inv);
    }

    public ItemStack getHead(Player player){
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwner(player.getName());
        meta.setDisplayName("§e" + player.getName());
        item.setItemMeta(meta);
        return item;
    }
}
