package dev.eredin.functions;

import dev.eredin.enchantments.customEnchantment;
import dev.eredin.saving.dataManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class luckyDrops {

    static Random r = new Random();
    public static boolean shardLucky (Player user){
        int roll = r.nextInt(10000);
        int chance = 10;
        ItemStack pick = user.getInventory().getItem(0);
        if (pick != null && pick.containsEnchantment(customEnchantment.SHARD_FINDER)){
            chance += pick.getEnchantmentLevel(customEnchantment.SHARD_FINDER);
        }
        if (chance >= roll){
            int drop = 0;
            while (drop < 100){
                drop = r.nextInt(1000);
            }
            dataManager.getData(user.getUniqueId()).shards += drop;
            user.sendMessage(ChatColor.WHITE+"("+ChatColor.LIGHT_PURPLE+"Lucky!"+ChatColor.WHITE+") "+ChatColor.GRAY+"You found "+ChatColor.RED+drop+ChatColor.GRAY+" shards!");
            return true;
        }else{
            return false;
        }

    }
}
