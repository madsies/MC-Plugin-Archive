package dev.elpis.playerclasses.GUI.tank;

import dev.elpis.playerclasses.GUI.nodeClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class veteranSailor extends nodeClass {
    public veteranSailor() {
        super("Veteran Sailor", "tank", "veteran_sailor", 2, Material.OAK_BOAT, 1, 5);
        prerequisites = new String[]{};
        conflicts = new String[]{};
        lore = new String[]{"An old sailor has no issues against a mild gust or a burst of water"};
    }

    @Override
    public String[] dynamicDescription(int level){
        return new String[]{ChatColor.translateAlternateColorCodes('&', "&3Water&7 and &aWind&7 Defense increased by &c+"+level*50 +"&7.")};
    }
}
