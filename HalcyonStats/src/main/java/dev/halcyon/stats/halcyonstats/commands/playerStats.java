package dev.halcyon.stats.halcyonstats.commands;

import dev.halcyon.stats.halcyonstats.HalcyonStats;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class playerStats implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player user = (Player) sender;

            if (sender.hasPermission("halcyon.playerstats")) {
                HashMap<String, Float> stats = HalcyonStats.playerTotalStats.get(user);

                if (stats != null) {
                    user.sendMessage(ChatColor.RED + "PLAYER STATS:");

                    for (String stat : stats.keySet()) {
                        user.sendMessage(ChatColor.WHITE + stat + ": " + ChatColor.GRAY + stats.get(stat));
                    }
                }
                else{
                    user.sendMessage(ChatColor.RED + "PLAYER STATS: null");
                }


            }


        }
        return true;
    }
}
