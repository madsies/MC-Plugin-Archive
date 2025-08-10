package dev.eredin.events;

import dev.eredin.functions.grimoireCode;
import dev.eredin.menuCode.pickaxeEnchantShop;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class playerInteract implements Listener {

    @EventHandler
    public void onPickaxeClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();

        if (event.getHand() == EquipmentSlot.OFF_HAND)
            return;

        if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack hand = player.getInventory().getItemInMainHand();
            if (hand.getType() == Material.NETHERITE_PICKAXE) {
                player.sendMessage(ChatColor.LIGHT_PURPLE + "Opening Enchantment Menu...");
                Inventory inv = pickaxeEnchantShop.createShopGUI(player);
                player.openInventory(inv);
            }
            if (hand.getType() == Material.BOOK && hand.getItemMeta().getDisplayName().equals(ChatColor.BLACK + "5 Leaf Clover Grimoire")) {
                grimoireCode.selfBoost(player);
            }
        }
    }
}

