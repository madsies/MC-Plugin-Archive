package dev.elpis.playerclasses.GUI;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public abstract class customGUI {

    public static Inventory inv;

    public static Inventory generateGUI(int rows, String title, Material bg, Player target) {
        return null;
    }


    public static ItemStack createAestheticItem(final Material material, final String name, Player user, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        assert meta != null;
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);

        return item;
    }

    public static void clicked(InventoryClickEvent event){
        if (event.getInventory() == inv) {
            event.setCancelled(true);
            int clickedSlot = event.getSlot();
            Player user = (Player) event.getWhoClicked();
        }
    }

}


