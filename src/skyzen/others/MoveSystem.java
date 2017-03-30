package skyzen.others;

import net.minecraft.server.v1_11_R1.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;
import skyzen.rank.SqlConnection;
import skyzen.utils.Particle;

public class MoveSystem implements Listener {

    private SqlConnection sql;

    public MoveSystem(SqlConnection sql) {
        this.sql = sql;
    }

    /*
    DOUBLE SAUT
     */
    @SuppressWarnings("deprecation")
    @EventHandler
    public void ActiveLeFly(PlayerMoveEvent e) {
        final Player p = e.getPlayer();
        if (!p.isOnGround())
            return;
        p.setAllowFlight(true);
        if (sql.getRank(p).getPower() == 0)
            p.setAllowFlight(false);
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void DoubleSaut(PlayerToggleFlightEvent e) {
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
        Particle particle = new Particle(EnumParticle.CLOUD, p.getLocation().add(0, 2.25, 0), true, 0.75f, 0.75f, 0.75f, 0, 35);
        Particle particle1 = new Particle(EnumParticle.VILLAGER_HAPPY, p.getLocation().add(0, 2.25, 0), true, 0.75f, 0.75f, 0.75f, 0, 35);
        for (Player pl : Bukkit.getOnlinePlayers()) {
            pl.playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_FLAP, 1, 1);
            particle.sendPlayer(pl);
            particle1.sendPlayer(pl);
        }
    }

    /*
    PROPULSEUR
     */
    @SuppressWarnings("deprecation")
    @EventHandler
    public void Propulseur(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (p.getLocation().subtract(0D, 1D, 0D).getBlock().getType() == Material.SLIME_BLOCK) {
            p.setVelocity(p.getLocation().getDirection().multiply(1.5D).setY(1D));
            Particle particle = new Particle(EnumParticle.SLIME, p.getLocation().add(0, 2.25, 0), true, 0.75f, 0.75f, 0.75f, 0, 15);
            for (Player pl : Bukkit.getOnlinePlayers()) {
                pl.playSound(p.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 1, 1);
                particle.sendPlayer(pl);
            }
            p.setFallDistance(-999F);
        }
    }
}