package skyzen.event;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import skyzen.lobby.Lobby;
import skyzen.playercache.PlayerData;
import skyzen.playercache.PlayerDataManager;
import skyzen.rank.Rank;
import skyzen.rank.SqlConnection;
import skyzen.utils.ItemModifier;
import skyzen.utils.Title;

public class PlayerListener implements Listener {

    public SqlConnection sql;
    public PlayerDataManager dataManager = new PlayerDataManager(this);
    public Map<Player, PlayerData> dataPlayers = new HashMap<>();
    public final Lobby main;
    private final ScoreboardManager manager;
    private final Scoreboard board;
    private final Team admin;
    private final Team modo;
    private final Team premium;
    private final Team vip;
    private final Team ami;
    private final Team joueur;

    public PlayerListener(Lobby main) {
        this.main = main;
        this.manager = Bukkit.getScoreboardManager();
        this.board = manager.getNewScoreboard();
        this.admin = board.registerNewTeam("admin");
        this.modo = board.registerNewTeam("modo");
        this.premium = board.registerNewTeam("premium");
        this.vip = board.registerNewTeam("vip");
        this.ami = board.registerNewTeam("ami");
        this.joueur = board.registerNewTeam("joueur");
    }

    @EventHandler
    public void onJoinAvant(PlayerLoginEvent e) {
        final Player p = e.getPlayer();
        sql.createAccount(p);
        dataManager.loadPlayerData(p);
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        Rank r = sql.getRank(p);

        for (Player p1 : Bukkit.getOnlinePlayers()) {
            p1.setLevel(Bukkit.getOnlinePlayers().size());
            p1.setScoreboard(board);
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
        for (int x = 0; x < 9; x++) {
            p.sendMessage("");
        }
        p.sendMessage("§6-----------------------------------------------------");
        p.sendMessage("§cNous vous rappelons que le serveur est en version §bBêta");
        p.sendMessage("§cMerci d'accepter les certains bugs liés au sereur.");
        p.sendMessage("§cN'hésitez pas à nous signaler un bug sur §eforum.smashs.fr");
        p.sendMessage(" ");
        p.sendMessage("§aLe discord est disponible ici: §bhttps://discord.app/ §c(Test)");
        p.sendMessage("§6-----------------------------------------------------");

        Title.sendTabTitle(p, "&7Bienvenue sur §eSmashs§7, " + p.getDisplayName(), "§7Vous pouvez nous suivre sur twitter: §b@SmashsFR");

        if (r.getPower() == 100) {
            e.setJoinMessage(r.getName() + " " + p.getName() + " §7a rejoint le hub !");
            p.setPlayerListName(" " + r.getName() + " " + p.getName());
            admin.addPlayer(p);
        }
        if (r.getPower() == 50) {
            e.setJoinMessage(r.getName() + " " + p.getName() + " §7a rejoint le hub !");
            p.setPlayerListName(" " + r.getName() + " " + p.getName());
            modo.addPlayer(p);
        }
        if (r.getPower() == 40) {
            e.setJoinMessage(r.getName() + " " + p.getName() + " §7a rejoint le hub !");
            p.setPlayerListName(" " + r.getName() + " " + p.getName());
            premium.addPlayer(p);
        }
        if (r.getPower() == 30) {
            e.setJoinMessage(r.getName() + " " + p.getName() + " §7a rejoint le hub !");
            p.setPlayerListName(" " + r.getName() + " " + p.getName());
            vip.addPlayer(p);
        }
        if (r.getPower() == 10) {
            e.setJoinMessage(r.getName() + " " + p.getName() + " §7a rejoint le hub !");
            p.setPlayerListName(" " + r.getName() + " " + p.getName());
            ami.addPlayer(p);
        }
        if (r.getPower() == 0) {
            e.setJoinMessage(null);
            p.setPlayerListName("" + r.getName() + " " + p.getName());
            joueur.addPlayer(p);
        }

        //Inventaire du joueur quand il rejoint
        PlayerInventory inv = p.getInventory();
        inv.clear();
        inv.setItem(0, ItemModifier.setText(new ItemStack(Material.NAME_TAG, 1), "§bSélecteur de jeux"));
        inv.setItem(1, ItemModifier.setText(ItemModifier.giveSkull(p.getName()), "§eProfil §c(SOON)"));
        inv.setItem(4, ItemModifier.setText(new ItemStack(Material.CHEST, 1), "§aSmashsChest"));
        inv.setItem(7, ItemModifier.setText(new ItemStack(Material.EMERALD, 1), "§6Boutique"));
        inv.setItem(8, ItemModifier.setText(new ItemStack(Material.NETHER_STAR, 1), "§6Sélecteur de Hubs"));

        if (p.isOp())
            inv.setItem(13, ItemModifier.setText(new ItemStack(Material.BOOK_AND_QUILL, 1), "§aGestion du serveur"));
    }

    @EventHandler
    public void Chat(AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        Rank r = sql.getRank(p);

        if (r.getPower() > 10)
            e.setFormat(r.getName() + " " + p.getName() + " §8➽§7 " + e.getMessage());
        if (r.getPower() <= 10)
            e.setFormat(r.getName() + p.getName() + " §8➽§f " + e.getMessage());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        final Player p = e.getPlayer();
        dataManager.savePlayerData(p);

        for (Player p1 : Bukkit.getOnlinePlayers())
            p1.setLevel(Bukkit.getOnlinePlayers().size() - 1);
    }

}
