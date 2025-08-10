package dev.elpis.playerclasses.GUI.healer;

import dev.elpis.playerclasses.GUI.nodeClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class guardianAngel extends nodeClass {
    public guardianAngel() {
        super("Guardian Angel", "healer", "guardian_angel", 2, Material.TOTEM_OF_UNDYING, 5, 5);
        prerequisites = new String[]{"protector"};
        conflicts = new String[]{};

        lore = new String[]{"The gods grant you a divine power to become the ultimate support",
                "You transfer your personal life force to save a soul."};
    }

    @Override
    public String[] dynamicDescription(int level){
        return new String[]{ChatColor.GRAY + "When an ally takes fatal damage, instead of dying, they are set to "+ChatColor.RED + (25+5*level)+"%"+ ChatColor.GRAY + " health",
                "You sacrifice your own health and take "+ChatColor.RED+(75-5*level)+"%"+ChatColor.GRAY+" damage."+ChatColor.DARK_GRAY+" (30s cooldown)",

        };
    }
}
