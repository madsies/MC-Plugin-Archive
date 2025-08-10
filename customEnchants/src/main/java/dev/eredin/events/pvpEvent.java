package dev.eredin.events;

import dev.eredin.functions.grimoireCode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class pvpEvent implements Listener {
    @EventHandler
    public void hurties(EntityDamageByEntityEvent event){
        if ((event.getDamager() instanceof Player) && (event.getEntity() instanceof Player)) {
            Player player = (Player) event.getDamager();
            Player target = (Player) event.getEntity();

            grimoireCode.enemyDebuff(player, target);
        }
    }
}
