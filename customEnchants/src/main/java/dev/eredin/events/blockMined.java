package dev.eredin.events;

import dev.eredin.functions.blockDropCalc;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class blockMined implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
        Player user = event.getPlayer();

        if (itemInHand.getType() == Material.NETHERITE_PICKAXE){
            blockDropCalc.calc(user, itemInHand, event.getBlock());
        }
        event.setDropItems(false); // defends=-fds
    }



}



