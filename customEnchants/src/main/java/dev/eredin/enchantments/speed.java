package dev.eredin.enchantments;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class speed {
    public static void speedGive(Player player, int slot){

        if (player.getInventory().getItem(slot) == null) return;
        ItemStack pick = player.getInventory().getItem(slot);

        if (pick.getType() == Material.NETHERITE_PICKAXE){
            if (pick.getItemMeta().hasEnchant(customEnchantment.SPEED)){
                if (pick.getItemMeta().getEnchantLevel(customEnchantment.SPEED) != 0)
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200000, pick.getItemMeta().getEnchantLevel(customEnchantment.SPEED)-1));
            }else{
                player.removePotionEffect(PotionEffectType.SPEED);
            }
        }else {
            player.removePotionEffect(PotionEffectType.SPEED);
        }
    }
}
