package code.customItems;

import org.bukkit.Material;
import code.core.customItem;

import java.util.HashMap;

public class customItems {
    final public static HashMap<String, customItem> IDs = new HashMap<String, customItem>() {
        {
            put("ADMIN_SPOON", new customItem(Material.IRON_SHOVEL, "ADMIN_SPOON", 1, "&cAdmin Spoon", new String[]{"This isnt a spork!", "Made for testing!"}, 9, null));
            put("KATANA", new customItem(Material.NETHERITE_SWORD, "KATANA", 1, "Katana", new String[]{"pog"}, 6, null));
        }};
}
