package skyzen.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandBlockerListener implements Listener {

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        String[] args = e.getMessage().split(" ");
        if ((args[0].equalsIgnoreCase("/pl")) || (args[0].equalsIgnoreCase("/plugins"))) {
            e.setCancelled(true);
        } else if (((args[0].equalsIgnoreCase("/help")) || (args[0].equalsIgnoreCase("/?")))) {
            e.setCancelled(true);
        }
    }
}