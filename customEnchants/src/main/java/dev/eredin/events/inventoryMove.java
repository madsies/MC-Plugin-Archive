package dev.eredin.events;

import dev.eredin.menuCode.pickaxeEnchantShop;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;


public class inventoryMove implements Listener {

    @EventHandler
    public void invMove(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Enchantments")) {
            event.setCancelled(true);
            pickaxeEnchantShop.clickedShop(event);  // Does Shop thingies

        }
        if (event.getSlot() == 0 && event.getCurrentItem().getType() == Material.NETHERITE_PICKAXE){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void switchHands(PlayerSwapHandItemsEvent event){
        if (event.getOffHandItem() != null){
            if (event.getOffHandItem().getType() == Material.NETHERITE_PICKAXE) {
                event.setCancelled(true);
            }
        }
    }

}
