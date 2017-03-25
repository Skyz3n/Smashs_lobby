package skyzen.event;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import skyzen.rank.SqlConnection;
import skyzen.utils.Title;

public class WorldListener implements Listener {

    private SqlConnection sql;

    public WorldListener(SqlConnection sql) {
        this.sql = sql;
    }

    @EventHandler
    public void cancelPickup(PlayerPickupItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void blockPlaced(BlockPlaceEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onInventoryEvent(InventoryClickEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void cancelDrop(PlayerDropItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        e.setFoodLevel(20);
    }

    @EventHandler
    public void onWeatherChangeEvent(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerFall(PlayerMoveEvent e) {
        final Player p = e.getPlayer();

        if (e.isCancelled())
            return;
        if (e.getTo().getBlockY() <= 1) {
            p.teleport((new Location(p.getWorld(), 1.599, 83, -0.532, -90.2f, -5.2f)));
            Title.sendTitle(p, 20, 20, 20, "", "§7Ne vous éloignez pas trop, c'est dangereux");
            p.sendMessage("§7Ne vous éloignez pas trop, c'est dangereux !");
        }
        if (sql.getRank(p).getPower() == 0) {
            if (e.getTo().getBlockX() == 141 && e.getTo().getBlockY() < 200) {
                p.teleport((new Location(p.getWorld(), 1.599, 83, -0.532, -90.2f, -5.2f)));
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                p.sendMessage("§6-----------------------------------------------------");
                p.sendMessage("§bVous devez être au moins §aVIP §bpour rejoindre la Zone VIP");
                p.sendMessage("§cBoutique: §d§nshop.smashs.fr");
                p.sendMessage("§6-----------------------------------------------------");
            }
        }
    }
}