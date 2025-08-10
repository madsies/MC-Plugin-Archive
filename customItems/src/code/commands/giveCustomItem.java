package code.commands;

import code.customItems.customItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class giveCustomItem implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player user = (Player) sender;
            if (args.length > 0 && customItems.IDs.containsKey(args[0])) {
                if (args.length < 2) {
                    customItems.IDs.get(args[0]).giveToPlayer(user);
                    return true;
                } else {
                    Player target = (Bukkit.getPlayer(args[1]));
                    if (target != null) {
                        customItems.IDs.get(args[0]).giveToPlayer(target);
                        sender.sendMessage(ChatColor.LIGHT_PURPLE + "You have given " + args[0] + " to " + args[1] + ".");
                        target.sendMessage(ChatColor.LIGHT_PURPLE + sender.getName() + " has given " + args[0] + " to you.");
                        return true;
                    }

                }
            }else{
                sender.sendMessage(ChatColor.RED+"That ID does not exist.");
            }
        }
        return false;
    }
}
