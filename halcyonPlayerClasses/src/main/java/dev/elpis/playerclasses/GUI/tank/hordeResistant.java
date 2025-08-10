package dev.elpis.playerclasses.GUI.tank;

import dev.elpis.playerclasses.GUI.nodeClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class hordeResistant extends nodeClass {
    public hordeResistant() {
        super("Horde Resistant", "tank", "horde_Resistant", 5, Material.SHIELD, 1, 5);
        prerequisites = new String[]{};
        conflicts = new String[]{};
        lore = new String[]{"Weaklings don't hurt you as much anymore..."};
    }


    @Override
    public String[] dynamicDescription(int level){
        return new String[]{ChatColor.translateAlternateColorCodes('&', "&c"+(level*4)+"%&7 Damage Resistance against Normal and Brute enemies ")};
    }
}
