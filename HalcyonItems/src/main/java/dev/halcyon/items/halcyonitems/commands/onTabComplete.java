package dev.halcyon.items.halcyonitems.commands;

import dev.halcyon.items.halcyonitems.HalcyonItems;
import dev.halcyon.items.halcyonitems.customItems;
import dev.halcyon.items.halcyonitems.customWeapon;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class onTabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if ((command.getName().equalsIgnoreCase("giveweapon"))){
            if (sender instanceof Player) {
                List<String> fills = new ArrayList<>();
                switch (args.length) {
                    case (1):
                        if (HalcyonItems.activeWeapons.size() > 0) fills.addAll(HalcyonItems.activeWeapons.keySet());
                        return fills;
                    case(2):
                        if (HalcyonItems.activeWeapons.containsKey(args[0])) {
                            customWeapon wep = HalcyonItems.activeWeapons.get(args[0]);
                            int minimumILevel = wep.getMinimumItemLevel();
                            int maximumILevel = wep.getMaximumItemLevel();
                            fills.add("<Item Level: "+minimumILevel+"-"+maximumILevel+">");
                        }
                        else{
                            fills.add("<NOT VALID ITEM ID>");
                        }
                        return fills;
                    case(3):
                    case(4):
                    case(5):
                        if (HalcyonItems.activeWeapons.containsKey(args[0])){
                            if (HalcyonItems.activeWeapons.get(args[0]).getPerkSlots() > args.length-3){
                                fills.addAll(customItems.itemPerks.keySet());
                            }
                            else{
                                fills.add("<EXCEEDED PERK LIMIT>");
                            }
                        }
                        return fills;
                    default:
                        return fills;

                }


            }
        }

        if ((command.getName().equalsIgnoreCase("itemcreator"))){
            if (sender instanceof Player) {
                List<String> fills = new ArrayList<>();
                switch(args.length){
                    case (1):
                        fills.add("create");
                        fills.add("edit");
                        fills.add("delete");
                        fills.add("view");
                        fills.add("list");
                        fills.add("reload");
                        return fills;
                    case(2):
                        switch(args[0].toLowerCase()) {
                            case("list"):
                                fills.add("weapon");
                                fills.add("armour");
                                fills.add("material");
                                break;
                            case("create"):
                                fills.add("<New Item ID>");
                                break;
                            case("delete"):
                            case("edit"):
                            case("view"):
                                fills.addAll(HalcyonItems.itemIDs);
                            default:
                                break;

                        }
                        return fills;
                    case(3):
                        switch(args[0]){
                            case("create"):
                                fills.add("weapon");
                                fills.add("armour");
                                fills.add("material");
                                break;
                            case("edit"):
                                if (HalcyonItems.activeWeapons.containsKey(args[1])) {
                                    fills.addAll(HalcyonItems.WEAPON_ATTRIBUTES);
                                }else if(HalcyonItems.activeArmour.containsKey(args[1])) {
                                    fills.addAll(HalcyonItems.ARMOUR_ATTRIBUTES);
                                }
                                break;
                            default:
                                break;
                        }
                        return fills;
                    case(4):
                        if(args[0].equalsIgnoreCase("edit")) {
                            switch (args[2].toLowerCase()){
                                case("name"):
                                    fills.add("<STRING, MULTI ARGUMENT ALLOWED>");
                                    break;
                                case("item_class"):
                                    fills.addAll(HalcyonItems.CLASSES);
                                    break;
                                case("ability_id"):
                                    fills.addAll(customItems.itemAbility.keySet());
                                    break;
                                case("rarity"):
                                case("perk_slots"):
                                    fills.add("<INTEGER, 0-5>");
                                    break;
                                case("level_requirement"):
                                case("minimum_item_level"):
                                case("base_damage"):
                                case("elemental_damage"):
                                case("defense"):
                                    fills.add("<INTEGER, MORE THAN 0>");
                                    break;
                                case("material"):
                                    fills.add("hand");
                                    break;
                                case("element_pool"):
                                case("bonus_stats"):
                                    fills.add("add");
                                    fills.add("remove");
                                    break;
                                case("set"):
                                    fills.addAll(HalcyonItems.armourSets.keySet());
                                    break;
                                case("armour_type"):
                                    fills.addAll(HalcyonItems.ARMOUR_TYPES);
                                    break;
                                default:
                                    break;
                            }
                        }
                        return fills;
                    case(5):
                        if(args[0].equalsIgnoreCase("edit")){
                            switch (args[2].toLowerCase()) {
                                case("element_pool"):
                                    switch (args[3].toLowerCase()){
                                        case ("add"):
                                            fills.addAll(HalcyonItems.ELEMENTS);
                                            break;
                                        case("remove"):
                                            fills.addAll(HalcyonItems.activeWeapons.get(args[1]).getElementPool());
                                        default:
                                            break;
                                    }
                                    break;
                                case("bonus_stats"):
                                    switch(args[3].toLowerCase()){
                                        case("add"):
                                            fills.addAll(HalcyonItems.SECONDARY_STATS);
                                            break;
                                        case("remove"):
                                            if (HalcyonItems.activeWeapons.containsKey(args[1])) {
                                                fills.addAll(HalcyonItems.activeWeapons.get(args[1]).getBonusStats().keySet());
                                            }
                                            else if (HalcyonItems.activeArmour.containsKey(args[1])){
                                                fills.addAll(HalcyonItems.activeArmour.get(args[1]).getBonusStats().keySet());
                                            }
                                            break;
                                        default:
                                            break;
                                    }

                                    break;
                                default:
                                    break;
                            }
                        }
                    case(6):
                        if (args[0].equalsIgnoreCase("edit")) {
                            if ("add".equalsIgnoreCase(args[3])) {
                                fills.add("<FLOAT>");
                            }
                        }
                    default:
                        return fills;

                }
            }
        }
        return null;
    }
}
