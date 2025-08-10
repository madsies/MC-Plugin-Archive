package dev.elpis.playerclasses.GUI.healer;

import dev.elpis.playerclasses.GUI.nodeClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class fieldMedic extends nodeClass {
    public fieldMedic() {
        super("Field Medic", "healer", "field_medic", 2, Material.FEATHER, 1, 5);
        prerequisites = new String[]{};
        conflicts = new String[]{};
        lore = new String[]{"fast run = less hit = less death = more heal"};
    }


    @Override
    public String[] dynamicDescription(int level){
        return new String[]{ChatColor.DARK_PURPLE  + "Swiftness"+ChatColor.GRAY+" increased by "+ChatColor.RED + "+"+(5*level) + ChatColor.GRAY + "."};
    }
}
