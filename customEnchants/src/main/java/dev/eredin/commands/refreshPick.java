package dev.eredin.commands;

import dev.eredin.saving.dataManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class refreshPick implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player){
            Player user = (Player) sender;
            if (user.getInventory().getItem(0) != null){
                ItemStack slotZero = user.getInventory().getItem(0);
                if (slotZero.getType() != Material.NETHERITE_PICKAXE){
                    ItemStack item = new ItemStack(Material.NETHERITE_PICKAXE, 1);
                    user.getInventory().setItem(0, item);
                }
            }else{
                ItemStack item = new ItemStack(Material.NETHERITE_PICKAXE, 1);
                user.getInventory().setItem(0, item);

            }
            HashMap<String, Integer> p = dataManager.getData(user.getUniqueId()).enchantments;
            for (String ec : p.keySet()){
                user.getInventory().getItem(0).addUnsafeEnchantment(dataManager.getData(user.getUniqueId()).getEnchClass(ec), p.get(ec));
            }
            dataManager.getData(user.getUniqueId()).updatePickLore(user);
            user.sendMessage(ChatColor.LIGHT_PURPLE+"Your Pickaxe has been fixed!");
        }
        return true;
    }
}
