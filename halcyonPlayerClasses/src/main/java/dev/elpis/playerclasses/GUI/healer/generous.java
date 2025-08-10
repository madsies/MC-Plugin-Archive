package dev.elpis.playerclasses.GUI.healer;

import dev.elpis.playerclasses.GUI.nodeClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class generous extends nodeClass {
    public generous() {
        super("Generous", "healer", "generous", 7, Material.COPPER_BLOCK, 1, 5);
        prerequisites = new String[]{};
        conflicts = new String[]{};
        lore = new String[]{"You risk yourself to help others"};
    }


    @Override
    public String[] dynamicDescription(int level){
        return new String[]{ChatColor.translateAlternateColorCodes('&', "&7Healing Received &9-50%&7, but &5Healing Rate&c +"+(5+level*5)+"%&7.")};
    }
}
