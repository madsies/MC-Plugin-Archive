package dev.eredin.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Random;

public class death implements Listener {
    static String[] deaths = new String[]{"got clapped", "got rolled", "got tickled", "went 'missing'", "committed", "got wasted"};
    static Random r = new Random();
    @EventHandler
    public void onCommitDie(EntityDamageEvent event){
        if (event.getEntity() instanceof Player){
            Player target = (Player) event.getEntity();
            if (target.getHealth() - event.getDamage() < 1){
                target.teleport(target.getWorld().getSpawnLocation());
                target.setHealth(20);
                Bukkit.broadcastMessage(ChatColor.RED+"[☠] "+target.getDisplayName()+ChatColor.GRAY+" "+deaths[r.nextInt(deaths.length)]+"!");
                event.setCancelled(true);
            }
        }
    }
}
