package dev.elpis.playerclasses.GUI.damage;

import dev.elpis.playerclasses.GUI.nodeClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class fakeSkillTest extends nodeClass {
    public fakeSkillTest() {
        super("Test Skill", "damage", "test_skill",5, Material.IRON_SWORD, 4, 5);
    }

    @Override
    public String[] dynamicDescription(int level){
        return new String[]{ChatColor.GRAY + "Receive a banana from monke on each hit."};
    }
}
