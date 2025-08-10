package dev.eredin.enchantments;

import dev.eredin.functions.blockDropCalc;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class jackpot {
    public static void runCommand(ItemStack itemInHand, Player user, ArrayList<ItemStack> drops) {
        float doubleChance = (Objects.requireNonNull(itemInHand.getItemMeta()).getEnchantLevel(customEnchantment.JACKPOT) * 0.0025f);
        Random rand = new Random();
        float random = rand.nextFloat();
        if (doubleChance >= random) {
            // user.sendMessage(ChatColor.LIGHT_PURPLE + "Jackpot has activated!");
            blockDropCalc.addBlocks(user, itemInHand, drops);
        }
    }
}
