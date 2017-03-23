package skyzen.menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import skyzen.utils.ItemModifier;

public class Jeux implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player p = (Player) sender;
        if(label.equalsIgnoreCase("jeux")){
            JeuxOpen(p);
        }
        return false;
    }

    public void JeuxOpen(Player player){
        Inventory inv = Bukkit.createInventory(null, (9*5), "Sélecteur de jeux");

        //Première ligne
        inv.setItem(0, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(1, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(2, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(3, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(4, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(5, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(6, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(7, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(8, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));

        //Jeux Ligne
        inv.setItem(22, ItemModifier.setText(new ItemStack(Material.IRON_AXE, 1), "§b§nBowDragon", "§8Genre: §aPvp en équipe", "", "§7Armé de vos kits, défendez votre", "§7Golem et attaquez celui ennemi.", "", "§8Développeur: §dSkyzen_FR", "","§8Joueur(s) dans le jeu: §c✖", "", "§6⋙ §eCliquez-ici pour accèder au §aMenu du jeu"));

        //Dernière ligne
        inv.setItem(36, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(37, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(38, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(39, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(40, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(41, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(42, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(43, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));
        inv.setItem(44, ItemModifier.setText(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11), " ", ""));

            player.openInventory(inv);
    }
}