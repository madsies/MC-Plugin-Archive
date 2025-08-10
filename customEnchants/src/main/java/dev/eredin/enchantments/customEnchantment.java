package dev.eredin.enchantments;

import org.bukkit.Material;

public class customEnchantment {
    final public static enchantClass SPEED = new enchantClass("speed", "Speed", 5, new String[]{"Gives you increased speed."}, 19, Material.FEATHER, 0, new int[]{100, 0, 50});
    final public static enchantClass EFFICIENCY = new enchantClass("efficiency", "Efficiency", 25, new String[]{"Allows you to break blocks faster."}, 20, Material.REDSTONE, 1, new int[]{50, 0, 25});
    final public static enchantClass TREASURE = new enchantClass("treasure", "Treasure", 1000, new String[]{"Increases the drops that you receive from mining"}, 21, Material.DIAMOND, 2, new int[]{100, 0, 50});
    final public static enchantClass JACKPOT = new enchantClass("jackpot", "Jackpot", 50, new String[]{"Has a 0.25*Level chance of doubling your drops"}, 22, Material.GOLD_INGOT, 3, new int[]{500, 0, 100});
    final public static enchantClass MINE_BOMB = new enchantClass("mine_bomb", "Mine Bomb", 60, new String[]{"Gives a bomb charge, Activate using shift!"}, 23, Material.TNT, 4, new int[]{5000, 0, 250});
    final public static enchantClass AUTO_SELL = new enchantClass("auto_sell", "Auto Sell", 1, new String[]{"Automatically sells blocks mined"}, 24, Material.DIAMOND_BLOCK, 5, new int[]{5000, 0, 0});
    final public static enchantClass SHARD_FINDER = new enchantClass("shard_finder", "Shard Finder", 70, new String[]{"Increases the chance of finding shards"}, 25, Material.COMPASS, 6, new int[]{100, 1, 4});
    final public static enchantClass GENEROUS = new enchantClass("generous", "Generous", 50, new String[]{"Has a small chance of giving all players an amount of shards."}, 28, Material.EMERALD, 7, new int[]{10000, 0, 1000});
    final public static enchantClass LOOT_FINDER = new enchantClass("loot_finder", "Loot Finder", 50, new String[]{"Gives a small chance to get rare drops!"}, 29, Material.CHEST, 8, new int[]{1000, 1, 5});
    final public static enchantClass STONK = new enchantClass("stonk", "Stonk", 100, new String[]{"Increases the amount of money earned!"}, 30, Material.SUNFLOWER, 9, new int[]{1000, 0, 500});
}
