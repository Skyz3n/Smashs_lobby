package skyzen.menus.jeux;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import skyzen.utils.ItemModifier;

public class JeuxBowDragon implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player p = (Player) sender;
        if(label.equalsIgnoreCase("bowdragon")){
            JeuxOpen(p);
        }
        return false;
    }

    public void JeuxOpen(Player player){
        Inventory inv = Bukkit.createInventory(null, (9*6), "Sélecteur de jeux > BowDragon");

        //Première ligne
        inv.setItem(0, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(1, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(2, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(3, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(4, ItemModifier.setText(new ItemStack(Material.IRON_AXE, 1), "§b§nBowDragon", "§8Genre: §aPvp en équipe", "", "§7Armé de vos kits, défendez votre", "§7Golem et attaquez celui ennemi.", "", "§8Développeur: §dSkyzen_FR", "","§8Joueur(s) dans le jeu: §c✖", "", "§6⋙ §eCliquez-ici pour accèder au §aMenu du jeu"));
        inv.setItem(5, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(6, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(7, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(8, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));

        //Contenu Ligne 2
        inv.setItem(9, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(10, ItemModifier.setText(new ItemStack(Material.SIGN, 1), "§eLobby du jeu", "§7Téléportez vous au lobby du jeu!"));
        inv.setItem(16, ItemModifier.setText(new ItemStack(Material.STORAGE_MINECART, 1), "§eListe des serveurs", "§7Rejoignez un serveur en particulier!"));
        inv.setItem(17, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));

        //Contenu Ligne 3
        inv.setItem(18, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(22, ItemModifier.setText(new ItemStack(Material.EYE_OF_ENDER, 1), "§f♻ §aJouer rapidement §f♻", "§7Jouez sur une carte ou mode aléatoire!"));
        inv.setItem(26, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));

        //Contenu Ligne 4
        inv.setItem(27, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(29, ItemModifier.setText(new ItemStack(Material.MAP, 1), "§bMap 1", ""));
        inv.setItem(30, ItemModifier.setText(new ItemStack(Material.MAP, 1), "§bMap 2", ""));
        inv.setItem(31, ItemModifier.setText(new ItemStack(Material.MAP, 1), "§bMap 3", ""));
        inv.setItem(32, ItemModifier.setText(new ItemStack(Material.MAP, 1), "§bMap 4", ""));
        inv.setItem(33, ItemModifier.setText(new ItemStack(Material.MAP, 1), "§bMap 5", ""));
        inv.setItem(35, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));

        //Contenu Ligne 5
        inv.setItem(36, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(44, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));

        //Dernière ligne
        inv.setItem(45, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(46, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(47, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(48, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(49, ItemModifier.setText(new ItemStack(Material.BARRIER, 1), "§cFermer le menu"));
        inv.setItem(50, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(51, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(52, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(53, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));

        player.openInventory(inv);
    }
}