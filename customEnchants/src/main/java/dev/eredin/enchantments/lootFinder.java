package dev.eredin.enchantments;

import dev.eredin.saving.dataManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class lootFinder {
    static Random r = new Random();

    public static void activate(Player player, ItemStack tool){
        if (tool.getItemMeta().hasEnchant(customEnchantment.LOOT_FINDER)) {
            int enchLevel = tool.getItemMeta().getEnchantLevel(customEnchantment.LOOT_FINDER);
            if (enchLevel <= 0) return;
            int roll = r.nextInt(5000);
            if(Math.floorDiv(enchLevel, 5)+1 >= roll){
                if (Math.floorMod(roll, 2) == 0){
                    int amount = 2500 + 100*enchLevel;
                    dataManager.getData(player.getUniqueId()).shards += amount;
                    player.sendMessage(ChatColor.WHITE+"("+ChatColor.LIGHT_PURPLE+"LootFinder"+ChatColor.WHITE+") "+ChatColor.GRAY+"You found "+ChatColor.RED+amount+ChatColor.GRAY+" shards!");
                }else{
                    //Add Moni
                    int amount = 20000000;
                    player.sendMessage(ChatColor.WHITE+"("+ChatColor.LIGHT_PURPLE+"LootFinder"+ChatColor.WHITE+") "+ChatColor.GRAY+"You found $"+ChatColor.RED+amount+ChatColor.GRAY+"!");
                }

            }
        }
    }
}
