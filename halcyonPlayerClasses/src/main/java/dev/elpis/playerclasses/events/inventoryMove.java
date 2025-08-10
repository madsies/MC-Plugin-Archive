package dev.elpis.playerclasses.events;
import dev.elpis.playerclasses.GUI.classSelect.classSelect;
import dev.elpis.playerclasses.GUI.skillTreeGUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class inventoryMove implements Listener {

    @EventHandler
    public void invMove(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Skill Tree")) {
            event.setCancelled(true);
            skillTreeGUI.clickedShop(event);  // Does Shop thingies
        }

        classSelect.clicked(event);
    }

}