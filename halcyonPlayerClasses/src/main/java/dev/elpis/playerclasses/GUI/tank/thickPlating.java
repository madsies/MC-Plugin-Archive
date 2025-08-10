package dev.elpis.playerclasses.GUI.tank;

import dev.elpis.playerclasses.GUI.nodeClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class thickPlating extends nodeClass {
    public thickPlating() {
        super("Thick Plating", "tank", "thick_plating", 1, Material.IRON_CHESTPLATE, 1, 5);
    }

    @Override
    public String[] dynamicDescription(int level){
        return new String[]{ChatColor.GRAY + "Defense is increased by " + ChatColor.RED + level*2 +"%"+ ChatColor.GRAY + "."};
    }
}
