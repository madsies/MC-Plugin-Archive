package dev.eredin.commands;

import dev.eredin.saving.dataManager;
import dev.eredin.saving.pickaxeData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class blockMinedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player){
            if (args.length >= 3){
                if (Bukkit.getPlayer(args[1]) != null && Bukkit.getPlayer(args[1]).isOnline()) {
                    if (!sender.hasPermission("eredin.enchants.modify")) return true;
                    Player targetUser = Bukkit.getPlayer(args[1]);
                    if (args[0].equalsIgnoreCase("add")) {
                        pickaxeData x = dataManager.getData(targetUser.getUniqueId());
                        try {
                            x.blocksMined += Integer.parseInt(args[2]);
                            sender.sendMessage(ChatColor.LIGHT_PURPLE+"Successfully added "+args[2]+" Blocks mined to "+ args[1]);
                            dataManager.getData(targetUser.getUniqueId()).updatePickLore(targetUser);
                            return true;
                        } catch (Exception e) {
                            sender.sendMessage(ChatColor.RED + "You did not enter a valid number!");
                        }
                    }
                    if (args[0].equalsIgnoreCase("remove")) {
                        pickaxeData x = dataManager.getData(targetUser.getUniqueId());
                        try {
                            x.blocksMined -= Integer.parseInt(args[2]);
                            sender.sendMessage(ChatColor.LIGHT_PURPLE+"Successfully removed "+args[2]+" Blocks mined from "+ args[1]);
                            dataManager.getData(targetUser.getUniqueId()).updatePickLore(targetUser);
                            return true;
                        } catch (Exception e) {
                            sender.sendMessage(ChatColor.RED + "You did not enter a valid number!");
                        }
                    }
                    if (args[0].equalsIgnoreCase("set")) {
                        pickaxeData x = dataManager.getData(targetUser.getUniqueId());
                        try {
                            x.blocksMined = Integer.parseInt(args[2]);
                            sender.sendMessage(ChatColor.LIGHT_PURPLE+"Successfully set "+args[2]+" Blocks mined to "+ args[1]);
                            dataManager.getData(targetUser.getUniqueId()).updatePickLore(targetUser);
                            return true;
                        } catch (Exception e) {
                            sender.sendMessage(ChatColor.RED + "You did not enter a valid number!");
                        }
                    }
                }
            }
        }
        return false;
    }
}
