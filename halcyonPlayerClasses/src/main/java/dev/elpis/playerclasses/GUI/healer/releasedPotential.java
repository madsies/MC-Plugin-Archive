package dev.elpis.playerclasses.GUI.healer;

import dev.elpis.playerclasses.GUI.nodeClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class releasedPotential extends nodeClass {
    public releasedPotential() {
        super("Released Potential", "healer", "released_potential", 7, Material.DIAMOND_BLOCK, 4, 5);
        prerequisites = new String[]{"robin_hood"};
        conflicts = new String[]{};
        lore = new String[]{"You were pushed to your limits, this is your reward."};
    }


    @Override
    public String[] dynamicDescription(int level){
        return new String[]{ChatColor.translateAlternateColorCodes('&', "&5Healing Rate&7 increased by &c"+(level*20)+"%&7.")};
    }
}
