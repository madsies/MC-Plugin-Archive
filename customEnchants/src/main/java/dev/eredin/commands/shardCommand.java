package dev.eredin.commands;

import dev.eredin.saving.dataManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class shardCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player){
            if (args.length >= 1){
                if (Bukkit.getPlayer(args[0]) != null && Bukkit.getPlayer(args[0]).isOnline()) {
                    Player targetUser = Bukkit.getPlayer(args[0]);
                    sender.sendMessage(ChatColor.RED+targetUser.getDisplayName()+ChatColor.GRAY+" has "+ChatColor.RED+dataManager.getData(targetUser.getUniqueId()).shards+ChatColor.GRAY+" shards.");
                    return true;
                }else{
                    sender.sendMessage(ChatColor.RED+"That Player is not online!");
                }

            }else{
                Player user = (Player) sender;
                sender.sendMessage(ChatColor.RED+user.getDisplayName()+ChatColor.GRAY+" has "+ChatColor.RED+dataManager.getData(user.getUniqueId()).shards+ChatColor.GRAY+" shards.");
                return true;
            }
        }
        return false;
    }
}
