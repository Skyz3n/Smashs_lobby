package skyzen.menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import skyzen.rank.SqlConnection;
import skyzen.utils.ItemModifier;

public class Jeux implements Listener {

    private SqlConnection sql;

    public Jeux(SqlConnection sql) {
        this.sql = sql;
    }

    @EventHandler
    public void onClickArrow(InventoryClickEvent event) {
        if (!event.getInventory().getTitle().equalsIgnoreCase("Sélecteur de Jeux")) return;
        if (event.getCurrentItem().getType() == Material.ARROW) {
            event.getWhoClicked().closeInventory();
            return;
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) || event.getItem() == null) return;
        if (event.getItem().getType() == Material.NAME_TAG){
            final Inventory inv = Bukkit.createInventory(event.getPlayer(), 36, "Sélecteur de Jeux");
            Player player = event.getPlayer();

            //fermer l'inventaire
            inv.setItem(35, ItemModifier.setText(new ItemStack(Material.ARROW, 1), "§fFermer l'inventaire", ""));

            //ouvrir l'inventaire
            event.getPlayer().openInventory(inv);
        }
    }
}
