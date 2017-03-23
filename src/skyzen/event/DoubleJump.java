package skyzen.event;

import net.minecraft.server.v1_11_R1.EnumParticle;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;
import skyzen.rank.SqlConnection;
import skyzen.utils.Particle;

public class DoubleJump implements Listener {

    private SqlConnection sql;

    public DoubleJump(SqlConnection sql) {
        this.sql = sql;
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        final Player p = e.getPlayer();
        if (!p.isOnGround())
            return;
        p.setAllowFlight(true);
        if (sql.getRank(p).getPower() == 0)
            p.setAllowFlight(false);
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onDoubleJump(PlayerToggleFlightEvent e) {
        final Player p = e.getPlayer();
        if (!e.isFlying() && sql.getRank(p).getPower() == 0)
            return;
        if (p.getGameMode() == GameMode.CREATIVE && sql.getRank(p).getPower() == 0)
            return;
        e.setCancelled(true);
        p.setAllowFlight(false);
        Vector v = p.getLocation().getDirection().normalize();
        v = v.setY(Math.max(0.4F, v.getY())).multiply(2f);
        p.setVelocity(v);
        p.setFlying(false);
        p.playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_FLAP, 1, 1);
        Particle particle = new Particle(EnumParticle.CLOUD, p.getLocation().add(0,2.25,0), true, 0.75f, 0.75f, 0.75f, 0, 35);
        particle.sendPlayer(p);
    }
}
