package code.events;

import code.saving.dataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class playerQuit implements Listener {
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){
        Player user = e.getPlayer();
        for (UUID i: dataManager.playerCustoms(user.getUniqueId()).keySet()){
            dataManager.playerCustoms(user.getUniqueId()).get(i).updateItem();
        }
    }
}
