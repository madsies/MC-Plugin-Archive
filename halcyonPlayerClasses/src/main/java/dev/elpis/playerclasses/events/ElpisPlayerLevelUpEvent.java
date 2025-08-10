package dev.elpis.playerclasses.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ElpisPlayerLevelUpEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    int oldLevel, newLevel;

    public static HandlerList getHandlerList() {return HANDLERS; }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public ElpisPlayerLevelUpEvent(Player player, int oldLevel, int newLevel){

        this.oldLevel = oldLevel;
        this.newLevel = newLevel;
    }

    public int getNewLevel(){
        return newLevel;
    }

    public int getOldLevel(){
        return oldLevel;
    }


}
