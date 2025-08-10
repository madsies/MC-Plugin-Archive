package dev.eredin.enchantments;

import org.bukkit.inventory.ItemStack;

public class autoSell {
    public static boolean check(ItemStack tool){
        if (tool.getItemMeta().hasEnchant(customEnchantment.AUTO_SELL)){
            if (tool.getItemMeta().getEnchantLevel(customEnchantment.AUTO_SELL) > 0){
                return true;
            }
        }
        return false;
    }
}
