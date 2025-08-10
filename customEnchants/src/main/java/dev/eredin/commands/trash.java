package dev.eredin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class trash implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (sender instanceof Player){
            Inventory inv = Bukkit.createInventory(null, 36, "Trash Can");
            ((Player) sender).openInventory(inv);
            return true;
        }
        return false;
    }

}
