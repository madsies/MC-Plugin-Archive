package code;

import code.commands.giveCustomItem;
import code.events.playerJoin;
import code.events.playerQuit;
import code.saving.dataManager;
import dev.eredin.api.API;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class customItemCore extends JavaPlugin {

    @Override
    public void onEnable(){
        dataManager.api = (API) getServer().getPluginManager().getPlugin("API");

        for (Player p : this.getServer().getOnlinePlayers()){  // Loads every online player's pickaxe data.
            UUID id = p.getUniqueId();
            dataManager.removeAllItems(p);
            dataManager.loadAllItems(p);
            dataManager.addPlayer(id);
        }


        getServer().getPluginManager().registerEvents(new playerJoin(), this);
        getServer().getPluginManager().registerEvents(new playerQuit(), this);

        this.getCommand("giveCustomItem").setExecutor(new giveCustomItem());
    }

}
