package dev.halcyon.items.halcyonitems.commands;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class getItemData implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player user = (Player) sender;
            ItemStack hand = user.getInventory().getItemInMainHand();
            ItemMeta meta = hand.getItemMeta();

            if (meta == null) return false;

            PersistentDataContainer cont = meta.getPersistentDataContainer();
            String msg = ChatColor.BLUE+"Item Meta Info:\n"+ChatColor.GRAY;

            for (NamespacedKey key : cont.getKeys()){
                if (cont.has(key, PersistentDataType.STRING)) msg += ChatColor.WHITE+key.getKey()+": "+ChatColor.GRAY+cont.get(key, PersistentDataType.STRING)+ "\n";
                if (cont.has(key, PersistentDataType.INTEGER)) msg += ChatColor.WHITE+key.getKey()+": "+ChatColor.GRAY+cont.get(key, PersistentDataType.INTEGER)+"\n";
                if (cont.has(key, PersistentDataType.FLOAT)) msg += ChatColor.WHITE+key.getKey()+": "+ChatColor.GRAY+cont.get(key, PersistentDataType.FLOAT)+"\n";
                if (cont.has(key, PersistentDataType.LONG)) msg += ChatColor.WHITE+key.getKey()+": "+ChatColor.GRAY+cont.get(key, PersistentDataType.LONG)+"\n";
            }


            user.sendMessage(ChatColor.GRAY+msg);


        }

        return true;
    }
}