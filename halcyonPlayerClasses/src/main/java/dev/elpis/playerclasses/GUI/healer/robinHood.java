package dev.elpis.playerclasses.GUI.healer;

import dev.elpis.playerclasses.GUI.nodeClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class robinHood extends nodeClass {
    public robinHood() {
        super("Robin Hood", "healer", "robin_hood", 7, Material.GOLD_BLOCK, 3, 5);
        prerequisites = new String[]{"selfless"};
        conflicts = new String[]{};
        lore = new String[]{"Living on the edge for others makes a person stronger."};
    }


    @Override
    public String[] dynamicDescription(int level){
        return new String[]{ChatColor.translateAlternateColorCodes('&', "&5Swiftness&7 &c+"+(level*20)+"&7 and Personal damage &c+"+(level*10)+"%&7.")};
    }
}
