package dev.eredin.menuCode;

import dev.eredin.enchantments.enchantClass;
import dev.eredin.enchantments.enchantManager;
import dev.eredin.saving.dataManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class settingTogglesMenu {
    public static ItemStack createGuiItem(final Material material, final String name, String setting, Player user, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        assert meta != null;
        meta.setDisplayName(ChatColor.LIGHT_PURPLE+name);

        // Set the lore of the item

        List<String> list = new ArrayList<>(Arrays.asList(lore));
        list.add(" ");
        HashMap<String, Boolean> toggles =dataManager.getToggles(user.getUniqueId()).toggles;
        if (!setting.equals("")) {
            if (toggles.get(setting)) {
                list.add("Current Toggle: " + ChatColor.GREEN + "ON");
            } else if (!toggles.get(setting)) {
                list.add("Current Toggle: " + ChatColor.RED + "OFF");
            }
        }

        meta.setLore(list);
        item.setItemMeta(meta);

        return item;
    }

    public static Inventory createGUI (Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, "Enchantments");
        for (int i = 0; i < 27; i++) {
            inv.setItem(i, createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "",  player));
        }

        int slot = 10;
        for (String s : dataManager.getToggles(player.getUniqueId()).toggles.keySet()) {
            inv.setItem(slot, createGuiItem(Material.LIME_STAINED_GLASS_PANE, s, s, player, "Test"));
            slot +=1;

        }
        return inv;
    }
}
