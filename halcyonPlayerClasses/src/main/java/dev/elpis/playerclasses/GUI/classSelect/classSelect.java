package dev.elpis.playerclasses.GUI.classSelect;

import dev.elpis.core.ElpisCore;
import dev.elpis.playerclasses.PlayerClasses;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class classSelect{

    public static Inventory inv;

    public static Inventory generateGUI(Player target) {
        inv = Bukkit.createInventory(null, 27, "Select Class");
        for (int i = 0; i < 27; i++) {
            if (i % 9 < 3) {
                inv.setItem(i, createAestheticItem(Material.BLUE_STAINED_GLASS_PANE, " ", target));
            }
            else if  ((i % 9) < 6){
                inv.setItem(i, createAestheticItem(Material.RED_STAINED_GLASS_PANE, " ", target));
            }
            else{
                inv.setItem(i, createAestheticItem(Material.LIME_STAINED_GLASS_PANE, " ", target));
            }
        }

        inv.setItem(10, createItemDisplay(Material.IRON_CHESTPLATE, ChatColor.translateAlternateColorCodes('&', "&9&lTank"), target, "tank",
                ChatColor.translateAlternateColorCodes('&', "&8&oTanks have increased survivability which"),
                ChatColor.translateAlternateColorCodes('&', "&8&oallow them to protect others")));

        inv.setItem(13, createItemDisplay(Material.IRON_SWORD, ChatColor.translateAlternateColorCodes('&', "&4&lDamage"), target, "damage",
                ChatColor.translateAlternateColorCodes('&', "&8&oDamage dealers have increased power which allows"),
                ChatColor.translateAlternateColorCodes('&', "&8&othem to quickly defeat enemies, with low survivability")));

        inv.setItem(16, createItemDisplay(Material.SPLASH_POTION, ChatColor.translateAlternateColorCodes('&', "&a&lHealer"), target, "healer",
                ChatColor.translateAlternateColorCodes('&', "&8&oHealers have powers to keep their allies alive"),
                ChatColor.translateAlternateColorCodes('&', "&8&oand buff their offensive attacks")));


        return inv;
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
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack createItemDisplay(final Material material, final String name, Player user, String playerClass, String... lore){
        ItemStack item = createAestheticItem(material, name, user);
        ItemMeta meta = item.getItemMeta();

        List<String> finalisedLore = new ArrayList<>();

        int level = PlayerClasses.getSpecificClassLevel(user, playerClass);

        finalisedLore.add("");
        Collections.addAll(finalisedLore, lore);
        finalisedLore.add("");

        int exp = PlayerClasses.getSpecificClassExperience(user, playerClass);

        int expForPre = PlayerClasses.fetchExpFromLevel(level);
        int expForPost = PlayerClasses.fetchExpFromLevel(level + 1);
        float percentage = (exp - expForPre) / (float) (expForPost - expForPre);

        int Filled = (int) (percentage * 30);
        String top = (ChatColor.WHITE+" [" + ChatColor.GOLD);
        String bottom = (ChatColor.GRAY + "");
        for (int i = 0; i < Filled; i++) {
            top = top + "|";
        }
        for (int i = 0; i < 30 - Filled; i++) {
            bottom = bottom + "|";
        }
        bottom = bottom + ChatColor.WHITE + "]";
        String msg = top + bottom;

        finalisedLore.add(ChatColor.GRAY+"Level: "+ChatColor.BLUE+level + msg + ChatColor.GRAY + " (" + ChatColor.RED + Math.round(percentage * 100f) + "%" + ChatColor.GRAY + ")");

        meta.setLore(finalisedLore);
        item.setItemMeta(meta);

        return item;

    }

    public static void clicked(InventoryClickEvent event){
        if (event.getInventory() == inv) {
            event.setCancelled(true);
            int clickedSlot = event.getSlot();
            Player user = (Player) event.getWhoClicked();

            switch (clickedSlot) {
                case 10:
                    // Tank
                    PlayerClasses.setCurrentPlayerClass(user, "tank");
                    user.closeInventory();
                    user.sendMessage(ElpisCore.MSG_PREFIX+ChatColor.translateAlternateColorCodes('&', "&7Class set to &9Tank"));
                    break;
                case 13:
                    // Damage
                    PlayerClasses.setCurrentPlayerClass(user, "damage");
                    user.closeInventory();
                    user.sendMessage(ElpisCore.MSG_PREFIX+ ChatColor.translateAlternateColorCodes('&', "&7Class set to &4Damage"));
                    break;
                case 16:
                    // Healer
                    PlayerClasses.setCurrentPlayerClass(user, "healer");
                    user.closeInventory();
                    user.sendMessage(ElpisCore.MSG_PREFIX+ChatColor.translateAlternateColorCodes('&', "&7Class set to &aHealer"));
                    break;
                default:
                    break;
            }
        }
    }
}
