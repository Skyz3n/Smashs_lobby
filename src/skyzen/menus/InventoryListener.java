package skyzen.menus;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onClickBed(InventoryClickEvent event){
        if (event.getCurrentItem().getType() != null && event.getCurrentItem().getType() == Material.BED){

            Player player = (Player) event.getWhoClicked();

            //Actions
            event.getWhoClicked().teleport(new Location(event.getWhoClicked().getWorld(), 1.599, 83, -0.532, -90.2f, -5.2f));
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_HARP, 1, 1);

            //close l'inventaire
            event.getWhoClicked().closeInventory();
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onClickAxe(InventoryClickEvent event) {
        if (event.getCurrentItem().getType() != null && event.getCurrentItem().getType() == Material.IRON_AXE){

            Player player = (Player) event.getWhoClicked();

            //Actions
            event.getWhoClicked().teleport(new Location(event.getWhoClicked().getWorld(), 136.767, 72, -0.491, -90.3f, -2.0f));
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);

            //close l'inventaire
            event.getWhoClicked().closeInventory();
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onClickBoots(InventoryClickEvent event) {
        if (event.getCurrentItem().getType() != null && event.getCurrentItem().getType() == Material.GOLD_BOOTS) {

            Player player = (Player) event.getWhoClicked();

            //Actions
            event.getWhoClicked().teleport(new Location(event.getWhoClicked().getWorld(), 40.504, 111, 33.343, -44f, -6f));
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1, 1);

            //close l'inventaire
            event.getWhoClicked().closeInventory();
        }
    }
}
