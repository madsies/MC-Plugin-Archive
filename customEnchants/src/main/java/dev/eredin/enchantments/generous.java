package dev.eredin.enchantments;

import dev.eredin.saving.dataManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class generous {
    static Random r = new Random();
    public static void calc(ItemStack tool, Player ply){
        if (tool.getItemMeta().hasEnchant(customEnchantment.GENEROUS)){
            int enchLevel = tool.getItemMeta().getEnchantLevel(customEnchantment.GENEROUS);
            if(1 >= r.nextInt(10000)){ // 1/100k -? 50/100k
                for (Player p : Bukkit.getOnlinePlayers()){
                    int amnt = 1000 + (100*enchLevel);
                    dataManager.getData(p.getUniqueId()).shards += amnt;
                    p.sendMessage(ChatColor.RED+ply.getDisplayName()+ ChatColor.GRAY+"'s generous enchant gave everyone "+ChatColor.RED+amnt+ChatColor.GRAY+" shards!");
                }
            }
        }

    }
}
