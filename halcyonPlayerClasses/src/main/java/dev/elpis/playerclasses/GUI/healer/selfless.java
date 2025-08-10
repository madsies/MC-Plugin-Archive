package dev.elpis.playerclasses.GUI.healer;

import dev.elpis.playerclasses.GUI.nodeClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class selfless extends nodeClass {
    public selfless() {
        super("Selfless", "healer", "selfless", 7, Material.IRON_BLOCK, 2, 5);
        prerequisites = new String[]{"generous"};
        conflicts = new String[]{};
        lore = new String[]{"You no longer care for your own safety."};
    }


    @Override
    public String[] dynamicDescription(int level){
        return new String[]{ChatColor.translateAlternateColorCodes('&', "&7Health will no longer regenerate above &950%&7, but &5Healing Rate&c +"+(5+level*5)+"%&7.")};
    }
}
