package dev.eredin.events;
import dev.eredin.enchantments.timeBomb;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;


public class playerCrouch implements Listener {
    @EventHandler
    public void onCrouch(PlayerToggleSneakEvent event){
        timeBomb.runCommand(event);
    }
}

