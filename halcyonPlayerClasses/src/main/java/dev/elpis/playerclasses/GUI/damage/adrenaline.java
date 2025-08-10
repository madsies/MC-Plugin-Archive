package dev.elpis.playerclasses.GUI.damage;

import dev.elpis.playerclasses.GUI.nodeClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class adrenaline extends nodeClass {
    public adrenaline() {
        super("Adrenaline", "attack", "adrenaline", 2, Material.POTION, 1, 5 );
    }

    @Override
    public String[] dynamicDescription(int level){
        return new String[]{ChatColor.RED+""+2*level+"%"+ChatColor.GRAY+" Chance to get"+ChatColor.BLUE+" Adrenaline"+ChatColor.GRAY+" per hit, this increases",
                "personal damage by"+ChatColor.RED+" 10%"+ChatColor.GRAY+" for"+ChatColor.BLUE+" 10s"};
    }
}
