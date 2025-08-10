package dev.elpis.playerclasses.GUI.damage;

import dev.elpis.playerclasses.GUI.nodeClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class oppressor extends nodeClass {
    public oppressor() {
        super("Oppressor", "damage", "oppressor",4, Material.IRON_AXE, 1, 5);
    }

    @Override
    public String[] dynamicDescription(int level){
        return new String[]{""+ChatColor.RED + level*3 +"%"+ChatColor.GRAY+ " Damage to Elite and lower tier enemies"};
    }
}