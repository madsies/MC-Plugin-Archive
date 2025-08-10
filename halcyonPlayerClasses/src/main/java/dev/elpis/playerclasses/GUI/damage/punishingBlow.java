package dev.elpis.playerclasses.GUI.damage;

import dev.elpis.playerclasses.GUI.nodeClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class punishingBlow extends nodeClass {
    public punishingBlow() {
        super("Punishing Blow", "damage", "punishing_blow",5, Material.COBWEB, 1, 5);
    }

    @Override
    public String[] dynamicDescription(int level){
        return new String[]{""+ChatColor.RED + level +"%" +ChatColor.GRAY+" to apply a slowness debuff to an enemy for"+ChatColor.BLUE+ " 10s"};
    }
}
