package dev.halcyon.items.halcyonitems;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class customItem {
    private String name;
    private String id;
    private int rarity = 0;
    private Material mat = Material.WOODEN_SWORD;
    private String[] elementPool;
    private String itemType = "abstract";

    public customItem() {}


    public abstract String getName();

    public abstract String getFormattedName();

    public abstract String getRarity();

    public abstract String getItemType();
}
