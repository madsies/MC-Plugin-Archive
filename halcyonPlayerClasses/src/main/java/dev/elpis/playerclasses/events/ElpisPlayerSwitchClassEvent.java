package dev.elpis.playerclasses.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ElpisPlayerSwitchClassEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    String oldClass, newClass;

    public static HandlerList getHandlerList() {return HANDLERS; }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public ElpisPlayerSwitchClassEvent(String oldClass, String newClass, Player user){
        this.oldClass = oldClass;
        this.newClass = newClass;
    }

    public String getNewClass(){
        return newClass;
    }

    public String getOldClass(){
        return oldClass;
    }


}
