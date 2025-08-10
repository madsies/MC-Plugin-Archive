package dev.elpis.playerclasses.GUI.healer;

import dev.elpis.playerclasses.GUI.nodeClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class protector extends nodeClass {
    public protector() {
        super("Protector", "healer", "protector", 2, Material.IRON_CHESTPLATE, 4, 5);
        prerequisites = new String[]{};
        conflicts = new String[]{};
        lore = new String[]{"You learn how to surpass the limits of healing by breaking past",
        "the basic health bounds."};
    }

    @Override
    public String[] dynamicDescription(int level){
        return new String[]{ChatColor.GRAY + "Gain the ability to overheal, at a reduced "+ChatColor.DARK_PURPLE+"Healing Rate"+ChatColor.GRAY+", up to "+ChatColor.RED + (5+(5*level))+"%"+ ChatColor.GRAY + " extra health."};
    }
}
