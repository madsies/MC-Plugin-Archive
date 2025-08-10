package dev.eredin.enchantments;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class enchantClass extends Enchantment {
    public final String namespace;
    private final String name;
    private final int maxLevel;
    private final String[] description;
    private final int slot;
    private final Material icon;
    private final int enchID;
    private final int[] cost;

    public enchantClass(String namespace, String name, int maxLevel, String[] description, int slot, Material icon, int enchID, int[] cost) {
        super(NamespacedKey.minecraft(namespace));
        this.namespace = namespace;
        this.name = name;
        this.maxLevel = maxLevel;
        this.description = description;
        this.slot = slot;
        this.icon = icon;
        this.enchID = enchID;
        this.cost = cost;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    @Override
    public int getStartLevel() {
        return 0;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.TOOL;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack itemStack) {
        return false;
    }

    public String[] getDescription() {
        return description;
    }

    public int getSlot() {
        return slot;
    }

    public Material getIcon() {
        return icon;
    }

    public int getEnchID() {
        return enchID;
    }

    public int getCost(int level){
        int baseCost = cost[0];
        int scalar = cost[1];
        int difference = cost[2];
        if (scalar == 0){
            return baseCost + ((level-1)*difference);
        }else{
            return (int) (baseCost + (Math.pow((level-1)*difference, 2)));
        }
    }
}
