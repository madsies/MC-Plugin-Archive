package dev.halcyon.items.halcyonitems.commands;

import dev.halcyon.core.HalcyonCore;
import dev.halcyon.items.halcyonitems.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class giveArmour implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player user = (Player) sender;
            HashMap<String, customArmour> list = HalcyonItems.activeArmour;
            if (list.size() > 0) {
                customArmour e = list.get(args[0]);

                ArrayList<itemPerk> perkList = new ArrayList<itemPerk>();
                int indexCounter = 0;

                for (int i = e.getPerkSlots(); i > 0; i--){
                    if (args.length >= 3+indexCounter) {
                        perkList.add(customItems.itemPerks.get(args[2 + indexCounter]));
                        indexCounter++;
                    }
                }

                user.getInventory().addItem(e.createItemInstance(Integer.parseInt(args[1]), perkList, user, null));

            }
            else{
                user.sendMessage(HalcyonCore.MSG_PREFIX+"No Armour? tf?");
            }

        }

        return true;
    }
}
