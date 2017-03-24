package skyzen.event;

import net.minecraft.server.v1_11_R1.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import skyzen.utils.Particle;

public class Propulseur implements Listener {

    @SuppressWarnings("deprecation")
    @EventHandler
    public void Propulseur(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (p.getLocation().subtract(0D, 1D, 0D).getBlock().getType() == Material.SLIME_BLOCK) {
            p.setVelocity(p.getLocation().getDirection().multiply(1.5D).setY(1D));
            Particle particle = new Particle(EnumParticle.SLIME, p.getLocation().add(0,2.25,0), true, 0.75f, 0.75f, 0.75f, 0, 15);
            for (Player pl : Bukkit.getOnlinePlayers()){
                pl.playSound(p.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 1, 1);
                particle.sendPlayer(pl);
            }
            p.setFallDistance(-999F);
        }
    }
}