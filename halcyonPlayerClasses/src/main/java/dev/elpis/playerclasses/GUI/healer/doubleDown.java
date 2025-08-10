package dev.elpis.playerclasses.GUI.healer;

import dev.elpis.playerclasses.GUI.nodeClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class doubleDown extends nodeClass {
    public doubleDown() {
        super("Double Down", "healer", "double_down", 7, Material.NETHERITE_BLOCK, 5, 5);
        prerequisites = new String[]{"released_potential"};
        conflicts = new String[]{};
        lore = new String[]{"Are you crazy? Seriously, you're going to get one shot."};
    }


    @Override
    public String[] dynamicDescription(int level){
        return new String[]{ChatColor.translateAlternateColorCodes('&', "&7Health will no longer regenerate above &925%&7,"),
                ChatColor.translateAlternateColorCodes('&', "but Personal and ally damage is&c +"+(50+level*10)+"%&7.")};
    }
}
