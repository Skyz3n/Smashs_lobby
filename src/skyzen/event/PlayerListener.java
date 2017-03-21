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
        Player p = e.getPlayer();

        for (Player p1 : Bukkit.getOnlinePlayers())
            p1.setLevel(Bukkit.getOnlinePlayers().size());

        //Setup des messages et TabList
        if (sql.getRank(p).getPower() == 100) {
            e.setJoinMessage(sql.getRank(p).getName() + " " + p.getName() + " §7a rejoint le hub !");
            TeamsTagsManager.setNameTag(p, "§1100", "§c[Admin] ", "");
        } else if (sql.getRank(p).getPower() == 80) {
            e.setJoinMessage(sql.getRank(p).getName() + " " + p.getName() + " §7a rejoint le hub !");
            TeamsTagsManager.setNameTag(p, "§280", "§9[Développeur] ", "");
        } else if (sql.getRank(p).getPower() == 70) {
            e.setJoinMessage(sql.getRank(p).getName() + " " + p.getName() + " §7a rejoint le hub !");
            TeamsTagsManager.setNameTag(p, "§370", "§a[Resp.Modérateur] ", "");
        } else if (sql.getRank(p).getPower() == 60) {
            e.setJoinMessage(sql.getRank(p).getName() + " " + p.getName() + " §7a rejoint le hub !");
            TeamsTagsManager.setNameTag(p, "§460", "§d[Youtubeur] ", "");
        } else if (sql.getRank(p).getPower() == 50) {
            e.setJoinMessage(sql.getRank(p).getName() + " " + p.getName() + " §7a rejoint le hub !");
            TeamsTagsManager.setNameTag(p, "§550", "§a[Modérateur] ", "");
        } else if (sql.getRank(p).getPower() == 40) {
            e.setJoinMessage(sql.getRank(p).getName() + " " + p.getName() + " §7a rejoint le hub !");
            TeamsTagsManager.setNameTag(p, "§640", "§f[Helper] ", "");
        } else if (sql.getRank(p).getPower() == 30) {
            e.setJoinMessage(sql.getRank(p).getName() + " " + p.getName() + " §7a rejoint le hub !");
            TeamsTagsManager.setNameTag(p, "§730", "§6[Premium] ", "");
        } else if (sql.getRank(p).getPower() == 20) {
            e.setJoinMessage(sql.getRank(p).getName() + " " + p.getName() + " §7a rejoint le hub !");
            TeamsTagsManager.setNameTag(p, "§820", "§e[Vip] ", "");
        } else if (sql.getRank(p).getPower() == 10) {
            e.setJoinMessage(sql.getRank(p).getName() + " " + p.getName() + " §7a rejoint le hub !");
            TeamsTagsManager.setNameTag(p, "§910", "§3[Ami] ", "");
        } else if (sql.getRank(p).getPower() == 0) {
            e.setJoinMessage(null);
            TeamsTagsManager.setNameTag(p, "§a", "§7", "");
        }

        //Setup du joueur quand il rejoint
        p.teleport((new Location(p.getWorld(), 1.599, 83, -0.532, -90.2f, -5.2f)));
        p.setGameMode(GameMode.ADVENTURE);
        p.getInventory().clear();
        p.getEquipment().clear();
        p.setMaxHealth(10);
        p.setFoodLevel(20);
        p.setHealth(p.getMaxHealth());

        //Message de bienvenue
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("§eConnexion sur Smashs en cours...");
        for (int x = 0; x < 10; x++)
            p.sendMessage("");
        p.sendMessage("§6-----------------------------------------------------");
        p.sendMessage("§cNous vous rappelons que le serveur est en version §bBêta");
        p.sendMessage("§cMerci d'accepter les certains bugs liés au sereur.");
        p.sendMessage("§cN'hésitez pas à nous signaler un bug sur §eforum.smashs.fr");
        p.sendMessage(" ");
        p.sendMessage("§aLe discord est disponible ici: §bhttps://discord.app/ §c(Test)");
        p.sendMessage("§6-----------------------------------------------------");

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

    @EventHandler
    public void Chat(AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        Rank r = sql.getRank(p);

        if (r.getPower() >= 10)
            e.setFormat(r.getName() + " " + p.getName() + " §8➽§f " + e.getMessage());
        if (r.getPower() == 0)
            e.setFormat(r.getName() + p.getName() + " §8➽§7 " + e.getMessage());
    }
}
