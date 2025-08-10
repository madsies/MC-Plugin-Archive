
package dev.elpis.playerclasses.GUI.tank;

        import dev.elpis.playerclasses.GUI.nodeClass;
        import org.bukkit.ChatColor;
        import org.bukkit.Material;

public class darkKnight extends nodeClass {
    public darkKnight() {
        super("Dark Knight", "tank", "dark_knight", 4, Material.NETHERITE_SWORD, 1, 5);
        prerequisites = new String[]{};
        conflicts = new String[]{"holy_knight"};
        lore = new String[]{"A servant of the underworld, truly attuned to the darkness,","close to hades and the lava of the depths"};
    }

    @Override
    public String[] dynamicDescription(int level){
        return new String[]{ChatColor.translateAlternateColorCodes('&', "&cFire&7 and &8Darkness&7 Defense increased by &c+"+level*50 +"&7.")};
    }
}