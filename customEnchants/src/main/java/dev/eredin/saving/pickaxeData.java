package dev.eredin.saving;

import dev.eredin.enchantments.enchantClass;
import dev.eredin.enchantments.enchantManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class pickaxeData {
    public int blocksMined = 0;
    public UUID userUUID;
    public int pickLevel = 1;
    public int shards = 0;
    public HashMap<String, Integer> enchantments = new HashMap<>();


    public void addBlock(int amount){
        blocksMined = blocksMined + amount;
        levelUpPick();
    }

    public void applyEnchant(enchantClass ec, int level){
        enchantments.put(ec.getName(), level);
        Player user = Bukkit.getPlayer(userUUID);
        if (user == null) return;
        user.getInventory().getItem(0).addUnsafeEnchantment(ec, level);

        updatePickLore(user);
    }

    public enchantClass getEnchClass(String eName){
        for (enchantClass ench : enchantManager.enchants) {
            if (ench.getName().equals(eName)) {
                return ench;
            }
        }
        return null;
        }


    public void levelUpPick(){
        int currentExp = blocksMined;
        int runningLevel = 1;
        boolean Cont = true;
        while (Cont){
            if (currentExp >= Math.pow(runningLevel*5, 2)){
                runningLevel += 1;
            }else{
                Cont = false;
            }
        }
        if (runningLevel > pickLevel){
            Player user = Bukkit.getPlayer(userUUID);
            if (user == null) return;
            user.sendMessage(ChatColor.LIGHT_PURPLE+"Your Pickaxe Levelled up to level "+runningLevel+"!");
            pickLevel = runningLevel;
        }
    }

    public double calculateLevelPercent(){
        return (double) ((blocksMined - Math.pow((pickLevel-1)*5,2)) / (Math.pow(pickLevel*5, 2) - Math.pow((pickLevel-1)*5,2)));
    }

    public void createData(UUID uuid){
        blocksMined = 0;
        userUUID = uuid;
        pickLevel = 1;
        enchantments = new HashMap<>();

    }

    public void updatePickLore(Player user){
        if (user.getInventory().getItem(0).getType() == Material.NETHERITE_PICKAXE) {
            ItemMeta meta = user.getInventory().getItem(0).getItemMeta();
            String nameString = ChatColor.AQUA+user.getDisplayName() + "'s Pickaxe"+ChatColor.BLUE+" [" + blocksMined + "]";
            if (meta == null) return;
            meta.setDisplayName(nameString);
            List<String> lore = new ArrayList<String>();

            // This level progress bar code is very ugly but it works
            int Filled = (int) (calculateLevelPercent()*20);
            String top = ("[" + ChatColor.GOLD);
            String bottom = (ChatColor.GRAY + "");
            for (int i= 0; i < Filled; i++) {
                top = top + "|";
            }
            for (int i= 0; i < 20-Filled; i++) {
                bottom = bottom + "|";
            }
            bottom = bottom +ChatColor.WHITE+"]";
            String msg = top+bottom;
            // very ugly

            lore.add(ChatColor.GOLD+"Level: "+ChatColor.WHITE+pickLevel+" "+msg);
            lore.add("");
            if (enchantments != null) {
                enchantments.forEach((k, v) -> {
                    if (v != 0) lore.add(ChatColor.GRAY + k +" "+ChatColor.RED + v);
                });
            }
            lore.add("");
            lore.add(ChatColor.translateAlternateColorCodes('&', "&b&lPICKAXE"));

            meta.setLore(lore);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            user.getInventory().getItem(0).setItemMeta(meta);

        }
    }


}
