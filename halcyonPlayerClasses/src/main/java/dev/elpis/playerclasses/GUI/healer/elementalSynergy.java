package dev.elpis.playerclasses.GUI.healer;

import dev.elpis.playerclasses.GUI.nodeClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class elementalSynergy extends nodeClass {
    public elementalSynergy() {
        super("Elemental Synergy", "healer", "elemental_synergy", 2, Material.BREWING_STAND, 2, 5);
        prerequisites = new String[]{};
        conflicts = new String[]{};
        lore = new String[]{"You harness the element from your weapon to increase other's power"};
    }


    @Override
    public String[] dynamicDescription(int level){
        return new String[]{ChatColor.translateAlternateColorCodes('&', "&7When you land a elemental effective hit, increase"),
                ChatColor.translateAlternateColorCodes('&',"personal and allied damage by &c"+level*3 +"%&7 for &98s&7.")};
    }
}
