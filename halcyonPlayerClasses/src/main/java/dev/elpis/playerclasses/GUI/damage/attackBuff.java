package dev.elpis.playerclasses.GUI.damage;

import dev.elpis.playerclasses.GUI.nodeClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class attackBuff extends nodeClass {
    public attackBuff() {
        super("Attack Boost", "damage", "attack_boost", 1, Material.IRON_SWORD, 1, 5);
    }

    @Override
    public String[] dynamicDescription(int level){
        return new String[]{ChatColor.GRAY + "Receive a " + ChatColor.RED + level +"%"+ ChatColor.GRAY + " damage increase on each hit."};
    }
}
