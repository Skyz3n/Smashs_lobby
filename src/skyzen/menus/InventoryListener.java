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
import skyzen.rank.SqlConnection;

public class InventoryListener implements Listener {

    private SqlConnection sql;

    public InventoryListener(SqlConnection sql) {
        this.sql = sql;
    }

    @EventHandler
    public void onClickVIP(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getType() == Material.EXP_BOTTLE && e.getCurrentItem().getItemMeta().getDisplayName().equals("§eZone VIP")){
            if(sql.getRank(p).getPower() >= 10){
                e.getWhoClicked().teleport(new Location(e.getWhoClicked().getWorld(), 210.139, 78, -0.411, -90.4f, -2.6f));
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_HARP, 1, 1);
                e.getWhoClicked().closeInventory();
            }else{
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                e.getWhoClicked().closeInventory();
                p.sendMessage("§6-----------------------------------------------------");
                p.sendMessage("§bVous devez être au moins §aVIP §bpour rejoindre la Zone VIP");
                p.sendMessage("§cBoutique: §d§nshop.smashs.fr");
                p.sendMessage("§6-----------------------------------------------------");
            }
        }
    }

    @EventHandler
    public void onClickTP(InventoryClickEvent e){
        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getType() == Material.COMPASS && e.getCurrentItem().getItemMeta().getDisplayName().equals("§eTéléportation")){
            ((Player) e.getWhoClicked()).performCommand("tp");
        }
    }

    @EventHandler
    public void onClickBarrier(InventoryClickEvent e){
        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getType() == Material.BARRIER && e.getCurrentItem().getItemMeta().getDisplayName().equals("§cFermer le menu")){
            e.getWhoClicked().closeInventory();
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onClickBed(InventoryClickEvent e){
        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getType() == Material.BED && e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Retour au spawn")){
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
        if (e.getCurrentItem().getType() == Material.GOLD_BOOTS && e.getCurrentItem().getItemMeta().getDisplayName().equals("§eJump")){
            Player p = (Player) e.getWhoClicked();
            e.getWhoClicked().teleport(new Location(e.getWhoClicked().getWorld(), 40.504, 111, 33.343, -44f, -6f));
            p.playSound(p.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1, 1);
            e.getWhoClicked().closeInventory();
        }
    }

    @EventHandler
    public void onTpMenu(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getCurrentItem() == null) return;
        if(e.getClickedInventory().getName().equalsIgnoreCase("Téléportation")){
            switch (e.getCurrentItem().getType()){
                case SKULL_ITEM:
                    Player target = Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName().replace("§e", ""));
                    if(target == e.getWhoClicked())
                        p.sendMessage("§cVous ne pouvez pas vous téléporter à vous même!");
                    else{
                        p.sendMessage("§7Téléportation vers: §e" + e.getCurrentItem().getItemMeta().getDisplayName());
                        p.teleport(target.getLocation());
                    }
                    p.closeInventory();
            }
        }
    }
}
