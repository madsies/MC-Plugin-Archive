package dev.elpis.playerclasses.GUI.healer;

import dev.elpis.playerclasses.GUI.nodeClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class bloodMagic extends nodeClass {
    public bloodMagic() {
        super("Blood Magic", "healer", "blood_magic", 1, Material.REDSTONE, 5, 5);
        prerequisites = new String[]{"morale_booster"};
        conflicts = new String[]{};

        lore = new String[]{"After years of intense training, your offensive support",
                "magic has allowed you to control the blood of your enemies."};
    }

    @Override
    public String[] dynamicDescription(int level){
        return new String[]{ChatColor.GRAY + "Gives you and nearby allies a "+ChatColor.RED + 5*level+"%"+ ChatColor.GRAY + " chance to", "lifesteal for "+ChatColor.BLUE+"25% "+ChatColor.GRAY+"of their hit."};
    }
}
