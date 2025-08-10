package code.core;

import code.saving.dataManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class customItem {
    ItemStack newItem;
    public UUID uniqueID;
    public UUID owner;
    String itemID;


    public customItem(Material type, String id, int amount, String name, String[] lore, int rarity, UUID uuid) {
        newItem = new ItemStack(type, amount);
        itemID = id;
        if (uuid != null) {
            uniqueID = uuid;
        }else{
            uniqueID = UUID.randomUUID();
        }

        updateLore(lore, rarity, name);
    }

    public void setOwner(UUID id){
        owner = id;
    }

    public void setItemID(UUID id){
        uniqueID = id;
    }

    public void updateItem(){
        dataManager.playerCustoms(owner).put(uniqueID, this);
    }

    public void giveToPlayer(Player player){
        player.getInventory().addItem(newItem);
        owner = player.getUniqueId();
        updateItem();
    }

    public void updateLore(String[] lore, int rarity, String name){
        ItemMeta meta = newItem.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setDisplayName(name);
        ArrayList<String> fullLore = new ArrayList<>(Arrays.asList(lore));
        fullLore.add("");
        for (Enchantment s: meta.getEnchants().keySet()){
            fullLore.add(s.toString()+meta.getEnchants().get(s));
        }
        if (meta.hasEnchants()) fullLore.add("");

        fullLore.add(rarityFormatting(rarity));
        fullLore.add(ChatColor.translateAlternateColorCodes('&',"&o&8"+itemID+":"+uniqueID));
        meta.setLore(fullLore);
        newItem.setItemMeta(meta);

    }


    public static String rarityFormatting(int rarity){
        /// 0 - common, 1 - uncommon, 2 - unique, 3 - rare, 4 - exquisite, 5 - Legendary, 6 - Godly, 7 - Mythical, 8 - Admin
        String[] RarityAliases = {
            ChatColor.translateAlternateColorCodes('&', "&f&lCOMMON"),
            ChatColor.translateAlternateColorCodes('&', "&7&lUNCOMMON"),
            ChatColor.translateAlternateColorCodes('&', "&a&lUNIQUE"),
            ChatColor.translateAlternateColorCodes('&', "&9&lRARE"),
            ChatColor.translateAlternateColorCodes('&', "&5&lEXQUISITE"),
            ChatColor.translateAlternateColorCodes('&', "&6&lLEGENDARY"),
            ChatColor.translateAlternateColorCodes('&', "&4&lGODLY"),
            ChatColor.translateAlternateColorCodes('&', "&d&lMYTHICAL"),
            ChatColor.translateAlternateColorCodes('&', "&kM&r&d&l ADMIN &kM"),
                ChatColor.translateAlternateColorCodes('&', "&d&lDIVINE")};
        if (rarity < RarityAliases.length) {
            return RarityAliases[rarity];
        }
        else {
            return RarityAliases[0];
        }
    }

    public static String convertToInvisibleString(String s) {
        StringBuilder hidden = new StringBuilder();
        for (char c : s.toCharArray()){
            hidden.append(ChatColor.COLOR_CHAR + "").append(c);
        }
        System.out.println(hidden);
        return hidden.toString();
    }
}
