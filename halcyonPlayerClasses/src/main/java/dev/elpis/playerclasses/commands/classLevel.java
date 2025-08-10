package dev.elpis.playerclasses.commands;

import dev.elpis.core.ElpisCore;
import dev.elpis.playerclasses.PlayerClasses;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class classLevel implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args){
        if (sender instanceof Player){
            Player user = (Player) sender;
            if (sender.hasPermission("halcyon.classlevel")){

                if (args.length >= 3) {
                    Player target = Bukkit.getPlayerExact(args[2]);
                    if (target == null) {
                        user.sendMessage(ElpisCore.MSG_PREFIX + "That player is not online!");
                    } else {
                        if (isInt(args[1])) {

                            String mode = args[0];
                            int increase = Integer.parseInt(args[1]);

                            if (mode.equalsIgnoreCase("add")) {
                                int current = (int) PlayerClasses.getClassLevel(target);
                                PlayerClasses.setClassLevel(target, current + increase);
                                user.sendMessage(ElpisCore.MSG_PREFIX + ChatColor.GRAY+"Added "+ ChatColor.RED+args[1]+ChatColor.GRAY+" Level(s) to "+target.getDisplayName()+".");

                            } else if (mode.equalsIgnoreCase("remove")) {
                                int current = (int) PlayerClasses.getClassLevel(target);
                                PlayerClasses.setClassLevel(target, current + increase);
                                user.sendMessage(ElpisCore.MSG_PREFIX + ChatColor.GRAY+"Removed "+ ChatColor.RED+args[1]+ChatColor.GRAY+" Level(s) from "+target.getDisplayName()+".");

                            } else if (mode.equalsIgnoreCase("set")) {
                                PlayerClasses.setClassLevel(target, increase);
                                user.sendMessage(ElpisCore.MSG_PREFIX + ChatColor.GRAY+"Set "+target.getDisplayName()+"'s Level to "+ ChatColor.RED+args[1]+ChatColor.GRAY+".");


                            } else {
                                user.sendMessage(ElpisCore.MSG_PREFIX +ChatColor.RED+ args[0] + " was not a valid operator");
                                user.sendMessage(ChatColor.RED+"/classLevel <add/remove/set> {value} [player]");
                            }
                        } else {
                            user.sendMessage(ElpisCore.MSG_PREFIX +ChatColor.RED+ args[1] + " is not an integer");
                            user.sendMessage(ChatColor.RED+"/classLevel <add/remove/set> {value} [player]");
                        }

                    }
                }


            }
            else {
                user.sendMessage(ElpisCore.MSG_NO_PERM);
            }
        }
        return true;
    }


    private boolean isInt(String checkable){
        try {
            Integer.parseInt(checkable);
            return true;
        }
        catch(NumberFormatException nfe) {
            return false;
        }
    }
}