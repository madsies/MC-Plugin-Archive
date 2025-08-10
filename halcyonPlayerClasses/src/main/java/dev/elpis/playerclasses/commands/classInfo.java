package dev.elpis.playerclasses.commands;

import dev.elpis.core.ElpisCore;
import dev.elpis.playerclasses.PlayerClasses;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class classInfo implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args){
        if (sender instanceof Player){
            Player user = (Player) sender;
            if (sender.hasPermission("halcyon.classinfo")){

                if (args.length != 0) {

                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target == null) {
                        user.sendMessage(ElpisCore.MSG_PREFIX + "That player is not online!");
                    } else {

                        String pClass = PlayerClasses.getCurrentPlayerClass(target);
                        int exp = PlayerClasses.getClassExperience(target);
                        int level = PlayerClasses.getClassLevel(target);

                        int expForPre = PlayerClasses.fetchExpFromLevel(level);
                        int expForPost = PlayerClasses.fetchExpFromLevel(level + 1);
                        float percentage = (exp - expForPre) / (float) (expForPost - expForPre);
                        user.sendMessage("");
                        user.sendMessage(ElpisCore.MSG_PREFIX + "Class info for " + target.getName());
                        user.sendMessage("Class: " + ChatColor.GRAY + PlayerClasses.fixString(pClass));
                        user.sendMessage("Current Exp: " + ChatColor.GRAY + exp);
                        user.sendMessage("Current Level: " + ChatColor.GRAY + level);


                        int Filled = (int) (percentage * 50);
                        String top = ("[" + ChatColor.GOLD);
                        String bottom = (ChatColor.GRAY + "");
                        for (int i = 0; i < Filled; i++) {
                            top = top + "|";
                        }
                        for (int i = 0; i < 50 - Filled; i++) {
                            bottom = bottom + "|";
                        }
                        bottom = bottom + ChatColor.WHITE + "]";
                        String msg = top + bottom;

                        user.sendMessage("Level Progress: " + msg + ChatColor.GRAY + " (" + ChatColor.RED + Math.round(percentage * 100f) + "%" + ChatColor.GRAY + ")");
                        user.sendMessage("");

                    }
                }

                else{
                    user.sendMessage(ElpisCore.MSG_PREFIX+ChatColor.RED+"You didn't specify a player.");
                }
            }
            else{
                user.sendMessage(ElpisCore.MSG_NO_PERM);
            }
        }
        return true;
    }
}
