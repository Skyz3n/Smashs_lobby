package skyzen.menus;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onClickBed(InventoryClickEvent e){
        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getType() == Material.BED){
            Player p = (Player) e.getWhoClicked();
            e.getWhoClicked().teleport(new Location(e.getWhoClicked().getWorld(), 1.599, 83, -0.532, -90.2f, -5.2f));
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_HARP, 1, 1);
            e.getWhoClicked().closeInventory();
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onClickBoots(InventoryClickEvent e){
        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getType() == Material.GOLD_BOOTS){
            Player p = (Player) e.getWhoClicked();
            e.getWhoClicked().teleport(new Location(e.getWhoClicked().getWorld(), 40.504, 111, 33.343, -44f, -6f));
            p.playSound(p.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1, 1);
            e.getWhoClicked().closeInventory();
        }
    }

    @EventHandler
    public void onClickNameTag(InventoryClickEvent e){
        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getType() == Material.NAME_TAG){
            ((Player) e.getWhoClicked()).performCommand("jeux");
        }
    }

    @EventHandler
    public void onClickNameTagHand(PlayerInteractEvent e){
        if (!(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) || e.getItem() == null) return;
        if (e.getItem().getType() == Material.NAME_TAG){
            e.getPlayer().performCommand("jeux");
        }
    }

    @EventHandler
    public void onTpMenu(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getCurrentItem() == null) return;
        if(e.getClickedInventory().getName().equalsIgnoreCase("Téléportation")){
            switch (e.getCurrentItem().getType()){
                case SKULL_ITEM:
                    p.sendMessage("§7Téléportation vers: §e" + e.getCurrentItem().getItemMeta().getDisplayName());
                    Player target = Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName().replace("§e", ""));
                    p.teleport(target.getLocation());
                    p.closeInventory();
            }
        }
    }
}
