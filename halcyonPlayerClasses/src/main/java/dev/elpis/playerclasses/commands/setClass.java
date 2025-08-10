package dev.elpis.playerclasses.commands;


import dev.elpis.core.ElpisCore;
import dev.elpis.playerclasses.PlayerClasses;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class setClass implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player user = (Player) sender;

            if (sender.hasPermission("elpis.setclass")){

                if (args.length > 1) {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    if (target == null) {
                        user.sendMessage(ElpisCore.MSG_PREFIX + "That player is not online!");
                    } else {

                        if (Arrays.asList(PlayerClasses.classes).contains(args[0].toLowerCase())) {
                            PlayerClasses.setCurrentPlayerClass(target, args[0]);
                            user.sendMessage(ElpisCore.MSG_PREFIX + ChatColor.GRAY + "Set " + target.getDisplayName() + ChatColor.GRAY+"'s class to " + ChatColor.RED + PlayerClasses.fixString(args[0].toLowerCase()));
                            if (user != target) {
                                target.sendMessage(ElpisCore.MSG_PREFIX + ChatColor.GRAY + "Your Class has been set to " + ChatColor.RED + PlayerClasses.fixString(args[0].toLowerCase()));
                            }
                        } else {
                            user.sendMessage(ElpisCore.MSG_PREFIX + ChatColor.RED + "That class does not exist.");
                        }

                    }
                }
            }else{
                user.sendMessage(ElpisCore.MSG_NO_PERM);
            }
        }
        else{
            return false;
        }
        return true;
    }
}
