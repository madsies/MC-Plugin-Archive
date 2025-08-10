package dev.elpis.playerclasses.GUI.healer;

import dev.elpis.playerclasses.GUI.nodeClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class restedBoost extends nodeClass {
    public restedBoost() {
        super("Rested Boost", "healer", "rested_boost", 1, Material.GOLDEN_APPLE, 1, 5);
    }

    @Override
    public String[] dynamicDescription(int level){
        return new String[]{ChatColor.DARK_PURPLE  + "Healing Rate "+ChatColor.GRAY+"Increased by "+ChatColor.BLUE+"20% "+ChatColor.GRAY+"When you're at " + ChatColor.RED + (100-(level-1)*5) +"% "+ ChatColor.GRAY + "or above health."};
    }
}
