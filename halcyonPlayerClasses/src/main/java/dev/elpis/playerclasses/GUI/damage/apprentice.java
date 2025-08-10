package dev.elpis.playerclasses.GUI.damage;

import dev.elpis.playerclasses.GUI.nodeClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class apprentice extends nodeClass {
    public apprentice() {
        super("Apprentice", "damage", "apprentice",3, Material.BLAZE_ROD, 1, 5);
    }

    @Override
    public String[] dynamicDescription(int level){
        return new String[]{ChatColor.GRAY + "Elemental damage is increased by " + ChatColor.RED + level*5 +"%"};
    }
}
