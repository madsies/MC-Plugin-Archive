package code.events;

import code.core.customItem;
import code.customItems.customItems;
import code.saving.dataManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class playerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player user = e.getPlayer();
        Inventory inv = user.getInventory();

        dataManager.addPlayer(user.getUniqueId());

        dataManager.removeAllItems(user);
        dataManager.loadAllItems(user);

        customItems.IDs.get("ADMIN_SPOON").giveToPlayer(user);
        customItems.IDs.get("KATANA").giveToPlayer(user);
    }

}

