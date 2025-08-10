package dev.eredin.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class playerDrop implements Listener {
    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent event){
        // event.getPlayer().sendMessage("You cannot drop items");
        event.setCancelled(true);
    }
}
