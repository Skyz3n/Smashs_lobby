package skyzen.event;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import skyzen.lobby.Lobby;
import skyzen.rank.Rank;
import skyzen.rank.SqlConnection;
import skyzen.utils.ItemModifier;
import skyzen.utils.TeamsTagsManager;
import skyzen.utils.Title;

public class PlayerListener implements Listener {

    private SqlConnection sql;
    public PlayerListener(SqlConnection sql) {
        this.sql = sql;
    }

    @Deprecated
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        final Player p = e.getPlayer();

        for (Player p1 : Bukkit.getOnlinePlayers())
            p1.setLevel(Bukkit.getOnlinePlayers().size());

        p.teleport((new Location(p.getWorld(), 1.599, 83, -0.532, -90.2f, -5.2f)));
        p.setGameMode(GameMode.ADVENTURE);
        p.getInventory().clear();
        p.getEquipment().clear();
        p.setMaxHealth(10);
        p.setFoodLevel(20);
        p.setHealth(p.getMaxHealth());

        Title.sendTitle(p, 20, 50, 20, "§bBienvenue sur le serveur §eSmashs", "§7Le serveur est en §6Beta");
        Title.sendTabTitle(p, "&7Bienvenue sur §eSmashs§7, " + p.getDisplayName(), "§7Vous pouvez nous suivre sur twitter: §b@SmashsFR");

        //Inventaire
        PlayerInventory inv = p.getInventory();
        inv.clear();

        inv.setItem(0, ItemModifier.setText(new ItemStack(Material.NAME_TAG, 1), "§bSélecteur de jeux"));
        inv.setItem(1, ItemModifier.setText(new ItemStack(Material.CHEST, 1), "§aSmashsChest"));
        inv.setItem(7, ItemModifier.setText(new ItemStack(Material.EMERALD, 1), "§6Boutique"));
        inv.setItem(8, ItemModifier.setText(new ItemStack(Material.NETHER_STAR, 1), "§6Sélecteur de Hubs"));

        inv.setItem(9, ItemModifier.setText(new ItemStack(Material.GOLD_BOOTS, 1), "§eJump", "§7Accéder au jump"));
        inv.setItem(12, ItemModifier.setText(new ItemStack(Material.BOOK, 1), "§6Succés"));
        inv.setItem(13, ItemModifier.setText(new ItemStack(Material.BED, 1), "§6Retour au spawn"));
        inv.setItem(14, ItemModifier.setText(new ItemStack(Material.PAPER, 1), "§6Informations", "§7Forum: §dforum.smashs.fr", "§7Boutique: §ashop.smashs.fr", "§7Teamspeak: §ets.smashs.fr"));
        inv.setItem(17, ItemModifier.setText(new ItemStack(Material.COOKED_FISH, 1), "§eAmis", "§7Voir ses amis"));

        if(sql.getRank(p).getPower() >= 10)
        inv.setItem(22, ItemModifier.setText(ItemModifier.giveSkull(p.getName()), "§d§n" + p.getDisplayName(), "§7Grade: " + sql.getRank(p).getName(), "§7Coins: §e" + sql.getBalance(p), "§7Booster: §cBientôt"));
        else{
            inv.setItem(22, ItemModifier.setText(ItemModifier.giveSkull(p.getName()), "§d§n" + p.getDisplayName(), "§7Grade: Joueur", "§7Coins: §e" + sql.getBalance(p), "§7Booster: §cBientôt"));
        }
    }
}
