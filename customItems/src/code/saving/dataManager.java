package code.saving;

import code.customItems.customItems;
import dev.eredin.api.API;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import code.core.customItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class dataManager {
    public static API api;

    public static void addPlayer(UUID id){
        HashMap<UUID, customItem> item = (HashMap<UUID, customItem>) api.getPlayerData(id, "customItems");
        if (item == null){
            item = new HashMap<UUID, customItem>();
            api.setPlayerData(id, "customItems", item);
        }
    }

    public static HashMap<UUID, customItem> playerCustoms(UUID id){
        return (HashMap<UUID, customItem>) api.getPlayerData(id, "customItems");
    }

    /*public static void saveAllItems(Player user){
        HashMap<String, customItem> hMap = new HashMap<String, customItem>();
        Inventory inv = user.getInventory();
        UUID id = user.getUniqueId();

        for (int i = 0; i < 37; i++) {
            ItemStack currentItem = inv.getItem(i);
            if (checkIfCustom(currentItem)) {
                String[] itemLore = currentItem.getItemMeta().getLore().toArray(new String[0]);
                String Info = itemLore[itemLore.length - 1];
                String[] details = Info.split(":");
                customItem itemClass = customItems.IDs.get(ChatColor.stripColor(details[0]));
                itemClass.setItemID(UUID.fromString(details[1]));
                hMap.put(ChatColor.stripColor(details[0]), itemClass.uniqueID.toString());
            }
        }

        System.out.println(id+ hMap.keySet().toString());
        api.setPlayerData(id, "customItems", hMap);
    } */

    public static void loadAllItems(Player p){
        HashMap<String, customItem> data = (HashMap<String, customItem>) api.getPlayerData(p.getUniqueId(), "customItems");
        if (data != null){
            for (String id : data.keySet()) {
                data.get(id).giveToPlayer(p);
            }
        }
    }

    public static void removeAllItems(Player p){
        for (ItemStack i: p.getInventory()){
            if(checkIfCustom(i)){
                p.getInventory().removeItem(i);
            }
        }
    }

    public static boolean checkIfCustom(ItemStack i){
        if (i != null && i.hasItemMeta() && i.getItemMeta().hasLore()){
            ArrayList<String> lore = (ArrayList<String>) i.getItemMeta().getLore();
            if ((customItems.IDs.keySet().contains(ChatColor.stripColor(lore.get(lore.size() - 1).split(":")[0])))){
                System.out.println("woah byebye");
                return true;
            }
        }
        return false;
    }

}
