package skyzen.event;

import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

public class DoubleJump implements Listener {

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        final Player p = e.getPlayer();
        if (!p.isOnGround())
            return;
        p.setAllowFlight(true);
        if (!p.isOp())
            p.setAllowFlight(false);
    }

    @EventHandler
    public void onDoubleJump(PlayerToggleFlightEvent e) {
        final Player p = e.getPlayer();
        if (!e.isFlying() && !p.isOp())
            return;
        if (p.getGameMode() == GameMode.CREATIVE && !p.isOp())
            return;
        e.setCancelled(true);
        p.setAllowFlight(false);
        Vector v = p.getLocation().getDirection().normalize();
        v = v.setY(Math.max(0.4F, v.getY())).multiply(2f);
        p.setVelocity(v);
        p.setFlying(false);
        p.playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_FLAP, 1, 1);
    }
}
