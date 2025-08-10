package dev.eredin.functions;

import dev.eredin.enchantments.customEnchantment;
import dev.eredin.saving.dataManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class shardDrop {

    public static void shardCalc(Player user){
        Random rand = new Random();
        ItemStack pick = user.getInventory().getItem(0);
        if (pick == null) return;

        float chance;
        int bound = 10;
        if (pick.getItemMeta().hasEnchant(customEnchantment.SHARD_FINDER)){
            chance = 0.2f + (pick.getItemMeta().getEnchantLevel(customEnchantment.SHARD_FINDER)/100f);
            bound += (pick.getItemMeta().getEnchantLevel(customEnchantment.SHARD_FINDER));
        }else{
            chance = 0.2f;
        }
        if (rand.nextFloat() <= chance){
            int amount = rand.nextInt(bound) +1;
            dataManager.getData(user.getUniqueId()).shards += amount;
            if (dataManager.getToggles(user.getUniqueId()).toggles.get("shardMessage"))
                user.sendMessage(ChatColor.GRAY+"You have found "+ChatColor.RED+amount+" shards!");
        }
    }
}
