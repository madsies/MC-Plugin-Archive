package dev.eredin.enchantments;

import dev.eredin.functions.blockDropCalc;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public class treasure {
    static Random  r = new Random();
    public static void activate(ArrayList<ItemStack> drops, Player player, ItemStack tool){
        if (tool.getItemMeta().hasEnchant(customEnchantment.TREASURE)) {
            int enchLevel = tool.getItemMeta().getEnchantLevel(customEnchantment.TREASURE);
            if (enchLevel <= 0) return;
            int dropAmnt = r.nextInt(enchLevel);
            for (ItemStack d : drops){
                d.setAmount(d.getAmount() * dropAmnt);
            }
            blockDropCalc.addBlocks(player, tool, drops);
        }
    }
}
