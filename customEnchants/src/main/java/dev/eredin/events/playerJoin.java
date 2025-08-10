package dev.eredin.events;

import dev.eredin.saving.dataManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;


public class playerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player user = event.getPlayer();

        if (user.getInventory().getItem(0) != null){
            ItemStack slotZero = user.getInventory().getItem(0);
            if (slotZero.getType() != Material.NETHERITE_PICKAXE){
                ItemStack item = new ItemStack(Material.NETHERITE_PICKAXE, 1);
                user.getInventory().setItem(0, item);
            }
        }else{
            ItemStack item = new ItemStack(Material.NETHERITE_PICKAXE, 1);
            user.getInventory().setItem(0, item);

        }

        dataManager.addPlayer(user.getUniqueId());

        dataManager.getData(user.getUniqueId()).updatePickLore(user);

    }
}
