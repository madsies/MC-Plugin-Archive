package dev.elpis.playerclasses.GUI.tank;

import dev.elpis.playerclasses.GUI.nodeClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class holyKnight extends nodeClass {
    public holyKnight() {
        super("Holy Knight", "tank", "holy_knight", 3, Material.DIAMOND_SWORD, 1, 5);
        prerequisites = new String[]{};
        conflicts = new String[]{"dark_knight"};
        lore = new String[]{"A servant of God, truly attuned to the light, and the earth"};
    }

    @Override
    public String[] dynamicDescription(int level){
        return new String[]{ChatColor.translateAlternateColorCodes('&', "&2Earth&7 and &eLight&7 Defense increased by &c+"+level*50 +"&7.")};
    }
}