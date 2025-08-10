package dev.eredin.enchantments;

import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public class enchantManager {

    public static enchantClass[] enchants = {
        customEnchantment.SPEED,
        customEnchantment.EFFICIENCY,
        customEnchantment.TREASURE,
        customEnchantment.JACKPOT,
        customEnchantment.MINE_BOMB,
        customEnchantment.AUTO_SELL,
        customEnchantment.SHARD_FINDER,
        customEnchantment.GENEROUS,
        customEnchantment.LOOT_FINDER,
        customEnchantment.STONK
    };


    public static void register(){
        for (Enchantment enchant : enchants) {
            boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(enchant);

            if (!registered) {
                registerEnchantment(enchant);
            }
        }
    }

    public static void registerEnchantment(Enchantment enchantment){
        boolean registered = true;
        try{
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
            System.out.println("Printed"+enchantment);

        } catch (Exception e){
            registered = false;
        }
        if (registered){
            System.out.println("Enchantment registered");
        }
    }
}
