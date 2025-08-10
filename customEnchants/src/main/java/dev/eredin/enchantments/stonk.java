package dev.eredin.enchantments;

import org.bukkit.inventory.ItemStack;

public class stonk {
    public static float check(ItemStack tool){
        if (tool.getItemMeta().hasEnchant(customEnchantment.STONK)){
            if (tool.getItemMeta().getEnchantLevel(customEnchantment.STONK) > 0){
                return 1 + tool.getItemMeta().getEnchantLevel(customEnchantment.STONK)/100f;
            }
        }
        return 1f;
    }
}
