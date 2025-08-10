package dev.halcyon.stats.halcyonstats.commands;

import dev.halcyon.stats.halcyonstats.HalcyonStats;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;

public class playerJoin implements Listener {

    public void onPlayerJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();
        HalcyonStats.playerTotalStats.put(p, new HashMap<String, Float>()); // Make sure there isnt an instance because fuck you

        HalcyonStats.calculateHandStats(p);
        HalcyonStats.calculateArmourStats(p);
    }
}
