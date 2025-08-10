package dev.elpis.playerclasses.GUI.healer;

import dev.elpis.playerclasses.GUI.nodeClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class moraleBooster extends nodeClass {
    public moraleBooster() {
        super("Morale Booster", "healer", "morale_booster", 1, Material.FIREWORK_ROCKET, 4, 5);
        prerequisites = new String[]{};
        conflicts = new String[]{};
        lore = new String[]{"You increase your team's morale with great charisma"};
    }

    @Override
    public String[] dynamicDescription(int level){
        return new String[]{ChatColor.GRAY + "Damage dealt by you and nearby allies increased by "+ChatColor.RED + 5*level+"%"+ ChatColor.GRAY + "."};
    }
}
