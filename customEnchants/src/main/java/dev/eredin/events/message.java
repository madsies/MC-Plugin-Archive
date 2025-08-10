package dev.eredin.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class message implements Listener {
    @EventHandler
    public void onMsg(AsyncPlayerChatEvent event){
        Player user = event.getPlayer();
        String format = "<title> <name> "+ChatColor.BLACK+"»"+ChatColor.WHITE+" <message>";
        if (event.getPlayer().getDisplayName().equals("noellefan123")) {
            format = format.replace("<title>", ChatColor.WHITE+"["+ChatColor.RED+"Gamer"+ChatColor.WHITE+"]");
            format = format.replace("<name>", ChatColor.LIGHT_PURPLE+"%1$s");

        }else if(event.getPlayer().getDisplayName().equals("jjosh900")){
            format = format.replace("<title>", ChatColor.YELLOW+"["+ChatColor.DARK_PURPLE+"Spekal"+ChatColor.YELLOW+"]");
            format = format.replace("<name>", ChatColor.BLUE+"%1$s");

        }else if(event.getPlayer().getDisplayName().equals("TheSeptonix")){
            format = format.replace("<title>", ChatColor.RED+"["+ChatColor.BLUE+"Nerd"+ChatColor.RED+"]");
            format = format.replace("<name>", ChatColor.WHITE+"%1$s");

        }
        else {
            format = format.replace("<title>", "[Non]");
            format = format.replace("<name>", "%1$s");
        }

        format = format.replace("<message>", "%2$s");
        event.setFormat(format);
        event.setMessage(event.getMessage());
    }
}
