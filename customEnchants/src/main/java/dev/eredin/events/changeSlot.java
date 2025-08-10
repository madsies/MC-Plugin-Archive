package dev.eredin.events;

import dev.eredin.enchantments.speed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class changeSlot implements Listener {
    @EventHandler
    public void onSwap(PlayerItemHeldEvent event){
        int slot = event.getNewSlot();
        speed.speedGive(event.getPlayer(), slot);
    }
}
