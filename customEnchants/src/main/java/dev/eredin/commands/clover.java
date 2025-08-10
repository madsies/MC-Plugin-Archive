package dev.eredin.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class clover implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player){
            Player user = (Player) sender;
            ItemStack item = new ItemStack(Material.BOOK, 1);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.BLACK+"5 Leaf Clover Grimoire");
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.DARK_GRAY+"a dirty ancient grimoire, fit for a peasant.");
            lore.add("");
            lore.add("");
            lore.add(ChatColor.DARK_GRAY+"Magicless... Talentless...");
            meta.setLore(lore);
            item.setItemMeta(meta);

            user.getInventory().addItem(item);
            user.sendMessage(ChatColor.BLACK+" You cant even use magic?");

        }
        return true;
    }
}
