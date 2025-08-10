package dev.halcyon.items.halcyonitems.commands;

import dev.halcyon.core.HalcyonCore;
import dev.halcyon.items.halcyonitems.HalcyonItems;
import dev.halcyon.items.halcyonitems.customItems;
import dev.halcyon.items.halcyonitems.customWeapon;
import dev.halcyon.items.halcyonitems.itemPerk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class giveWeapon implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player user = (Player) sender;
            HashMap<String, customWeapon> list = HalcyonItems.activeWeapons;
            if (list.size() > 0) {
                customWeapon e = list.get(args[0]);

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
                user.sendMessage(HalcyonCore.MSG_PREFIX+"No Weapon? tf?");
            }

        }

        return true;
    }
}
