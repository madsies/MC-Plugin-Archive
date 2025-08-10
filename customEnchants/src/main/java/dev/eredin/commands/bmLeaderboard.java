package dev.eredin.commands;

import dev.eredin.saving.dataManager;
import dev.eredin.saving.pickaxeData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

public class bmLeaderboard implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            HashMap<Integer, UUID> stats = new HashMap<>();
            ArrayList<String> values = new ArrayList<>();
            values.add(ChatColor.WHITE+"============================");
            for (Player p : Bukkit.getOnlinePlayers()) {
                pickaxeData data = dataManager.getData(p.getUniqueId());
                stats.put(data.blocksMined, p.getUniqueId());
            }

            ArrayList<Integer> keys = new ArrayList<>(stats.keySet());

            Collections.sort(keys);
            int iter = 0;
            for (int i : keys) {
                iter += 1;
                values.add(ChatColor.RED + "" + iter + ". " + ChatColor.GRAY + Bukkit.getPlayer(stats.get(i)).getDisplayName() + ": " + ChatColor.RED + i);
            }
            values.add(ChatColor.WHITE+"============================");

            for (String str : values)
                sender.sendMessage(str);
            return true;
        }
        return false;
    }
}
