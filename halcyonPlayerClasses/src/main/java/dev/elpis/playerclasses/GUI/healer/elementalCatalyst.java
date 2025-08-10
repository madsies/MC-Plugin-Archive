package dev.elpis.playerclasses.GUI.healer;

import dev.elpis.playerclasses.GUI.nodeClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class elementalCatalyst extends nodeClass {
    public elementalCatalyst() {
        super("Elemental Catalyst", "healer", "elemental_catalyst", 2, Material.TNT, 3, 5);
        prerequisites = new String[]{"elemental_synergy"};
        conflicts = new String[]{};
        lore = new String[]{"'What doesn't kill you makes you stronger'"};
    }


    @Override
    public String[] dynamicDescription(int level){
        return new String[]{ChatColor.translateAlternateColorCodes('&', "&7When you get hit by your most resistant elemental type, increase"),
                ChatColor.translateAlternateColorCodes('&',"&5Healing Rate&7 by &c"+level*4 +"%&7 and &5Swiftness&7 by &c+"+level*10+"&7 for &98s&7.")};
    }
}
