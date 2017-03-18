package skyzen.rank;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;

public enum Rank {

    ADMINISTRATEUR(100, "§c[Administrateur]", ChatColor.RED),
    MODERATEUR(50, "§a[Modérateur]", ChatColor.GREEN),
    PREMIUM(40, "§6[Premium]", ChatColor.GOLD),
    VIP(30, "§e[Vip]", ChatColor.YELLOW),
    AMI(10, "§3[Ami]", ChatColor.AQUA),
    JOUEUR(0, "§7", ChatColor.GRAY);

    private int power;
    private String displayName;
    private ChatColor colorTag;
    public static Map<Integer, Rank> grade = new HashMap<>();

    Rank(int power, String displayName, ChatColor tag) {
        this.power = power;
        this.displayName = displayName;
        this.colorTag = tag;
    }

    static {
        for (Rank r : Rank.values()) {
            grade.put(r.getPower(), r);
        }
    }

    public int getPower() {
        return power;
    }

    public String getName() {
        return displayName;
    }

    public ChatColor getTag() {
        return colorTag;
    }

    public static Rank powerToRank(int power) {
        return grade.get(power);
    }

}