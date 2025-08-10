package dev.elpis.playerclasses.GUI.healer;

import dev.elpis.playerclasses.GUI.nodeClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class cleric extends nodeClass {
    public cleric() {
        super("Cleric", "healer", "cleric", 1, Material.IRON_SWORD, 3, 5);
        lore = new String[]{"You delve deeper into the art of healing, increasing your efficiency"};
        prerequisites = new String[]{"alchemist"};
        conflicts = new String[]{};
    }

    @Override
    public String[] dynamicDescription(int level){
        return new String[]{ChatColor.DARK_PURPLE  + "Healing Rate"+ChatColor.GRAY+" increased by "+ChatColor.RED + 10*level+"%"+ ChatColor.GRAY + "."};
    }
}
