package dev.halcyon.stats.halcyonstats.events;

import dev.halcyon.stats.halcyonstats.HalcyonStats;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class itemSwap implements Listener {

    @EventHandler
    public void onPlayerItemSwap(PlayerItemHeldEvent event){
        Player user = event.getPlayer();
        int itemSlot = event.getNewSlot();

        HalcyonStats.calculateHandStats(user);
        HalcyonStats.calculateArmourStats(user);

        HalcyonStats.totalPlayerStats(user);
    }
}
