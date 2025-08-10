package dev.eredin.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;


public class durabilityLost implements Listener {
    @EventHandler
    public void onLost(PlayerItemDamageEvent event){
        event.setCancelled(true);
        }
    }

