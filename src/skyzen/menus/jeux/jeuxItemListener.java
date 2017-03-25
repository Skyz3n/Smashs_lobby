package skyzen.menus.jeux;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class jeuxItemListener implements Listener {

    @EventHandler
    public void onClickBowDragon(InventoryClickEvent e){
        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getType() == Material.IRON_AXE && e.getCurrentItem().getItemMeta().getDisplayName().equals("§b§nBowDragon")){
            ((Player) e.getWhoClicked()).performCommand("bowdragon");
        }
    }

    @EventHandler
    public void onClickNameTag(InventoryClickEvent e){
        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getType() == Material.NAME_TAG && e.getCurrentItem().getItemMeta().getDisplayName().equals("§bSélecteur de jeux")){
            ((Player) e.getWhoClicked()).performCommand("jeux");
        }
    }

    @EventHandler
    public void onClickNameTagHand(PlayerInteractEvent e){
        if (!(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) || e.getItem() == null) return;
        if (e.getItem().getType() == Material.NAME_TAG && e.getItem().getItemMeta().getDisplayName().equals("§bSélecteur de jeux")){
            e.getPlayer().performCommand("jeux");
        }
    }

}
