package dev.elpis.playerclasses.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class onTabComplete implements TabCompleter {


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if ((command.getName().equalsIgnoreCase("classlevel") || command.getName().equalsIgnoreCase("classExperience"))){
            if (sender instanceof Player){
                if (args.length < 2) {
                    Player user = (Player) sender;

                    List<String> fills = new ArrayList<>();

                    fills.add("add");
                    fills.add("remove");
                    fills.add("set");

                    return fills;
                }
                else if (args.length == 2) {
                    List<String> empty = new ArrayList<>();
                    empty.add("<value>");
                    return empty;

                }
                else if (args.length > 3){
                    return new ArrayList<>();
                }
            }
        }
        else if (command.getName().equalsIgnoreCase("setclass")){
            if (sender instanceof Player){
                if (args.length == 1){
                    List<String> fills = new ArrayList<>();

                    fills.add("damage");
                    fills.add("tank");
                    fills.add("healer");
                    return fills;
                }
            }
        }
        return null;
    }
}
