package dev.elpis.playerclasses.GUI.healer;

import dev.elpis.playerclasses.GUI.nodeClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class alchemist extends nodeClass {
    public alchemist() {
        super("Alchemist", "healer", "alchemist", 1, Material.POTION, 2, 5);
        lore = new String[]{"Your knowledge of healing magic grows stronger"};
    }

    @Override
    public String[] dynamicDescription(int level){
        return new String[]{ChatColor.DARK_PURPLE + "Healing Rate"+ChatColor.GRAY+" increased by "+ChatColor.RED + (5*level)+"%"+ ChatColor.GRAY + "."};
    }
}
