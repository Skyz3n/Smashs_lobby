package skyzen.others;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import skyzen.Lobby;
import skyzen.utils.Title;

public class ActionBarMessage implements Listener {

    public final Lobby main;

    public ActionBarMessage(Lobby main) {
        this.main = main;
    }

    @EventHandler
    public void onJoinDetectActionBar(PlayerJoinEvent e) {
        final Player p = e.getPlayer();

        new Runnable()
        {
            final int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, this, 0L, 100L);

            @Override
            public void run()
            {
                if (!p.isOnline())
                    Bukkit.getScheduler().cancelTask(taskID);
                else {
                    switch (1 + (int) (Math.random() * 4))
                    {
                        case 1:
                            Title.sendActionBar(p, "§cLe serveur est cours de développement !");
                            break;
                        case 2:
                            Title.sendActionBar(p, "§e|§d|§6| §bBienvenue sur §eSmashs §6|§d|§e|");
                            break;
                        case 3:
                            Title.sendActionBar(p, "§7Le serveur est en §6Pre-Alpha §7!");
                            break;
                        case 4:
                            Title.sendActionBar(p, "§aBoutique: §ehttps://shop.smashs.fr/");
                            break;
                    }
                }
            }
        };
    }
}
