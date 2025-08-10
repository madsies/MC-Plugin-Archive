package dev.elpis.playerclasses.commands;

import dev.elpis.playerclasses.GUI.classSelect.classSelect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class selectClass implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player user = (Player) sender;
            user.openInventory(classSelect.generateGUI(user));
        }

        return true;
    }
}
