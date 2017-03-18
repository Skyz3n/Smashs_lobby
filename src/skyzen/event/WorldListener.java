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

import skyzen.utils.Title;

public class WorldListener implements Listener {

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
    public void onPlayerFall(PlayerMoveEvent event) {
        final Player player = event.getPlayer();

        if (event.isCancelled())
            return;
        if (!player.isOp()) {
            if (event.getTo().getBlockX() == 141) {
                player.teleport((new Location(player.getWorld(), 1.599, 83, -0.532, -90.2f, -5.2f)));
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                player.sendMessage("§6-----------------------------------------------------");
                player.sendMessage("§bVous devez être au moins §aVIP §bpour rejoindre la Zone VIP");
                player.sendMessage("§cBoutique: §d§nshop.smashs.fr");
                player.sendMessage("§6-----------------------------------------------------");
            }
        }
        if (event.getTo().getBlockY() <= 1) {
            player.teleport((new Location(player.getWorld(), 1.599, 83, -0.532, -90.2f, -5.2f)));
            Title.sendTitle(player, 20, 20, 20, "", "§7Ne vous éloignez pas trop, c'est dangereux");
            player.sendMessage("§7Ne vous éloignez pas trop, c'est dangereux !");
        }
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        String msg = e.getMessage();
        String[] args = msg.split(" ");
        if ((args[0].equalsIgnoreCase("/pl")) || (args[0].equalsIgnoreCase("/plugins"))) {
            e.setCancelled(true);
        } else if (((args[0].equalsIgnoreCase("/help")) || (args[0].equalsIgnoreCase("/?")))) {
            e.setCancelled(true);
        }
    }
}