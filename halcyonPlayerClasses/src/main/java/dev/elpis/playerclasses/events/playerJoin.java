package dev.elpis.playerclasses.events;

import dev.elpis.core.customevents.ElpisPlayerLoadedEvent;
import dev.elpis.playerclasses.PlayerClasses;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class playerJoin implements Listener {


    @EventHandler
    public void HalcyonPlayerJoinEvent(ElpisPlayerLoadedEvent e){
        Player user = e.getPlayer();

        PlayerClasses.getCurrentPlayerClass(user);
        PlayerClasses.getClassExperience(user);
        PlayerClasses.getSkillPoints(user);
        PlayerClasses.getSkillPoints(user);
    }





}
