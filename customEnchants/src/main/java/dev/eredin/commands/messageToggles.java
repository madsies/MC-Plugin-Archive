package dev.eredin.commands;

import dev.eredin.menuCode.settingTogglesMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class messageToggles implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player){
            Player user = (Player) sender;
            user.openInventory(settingTogglesMenu.createGUI(user));
            return true;
        }
        return false;
    }
}
