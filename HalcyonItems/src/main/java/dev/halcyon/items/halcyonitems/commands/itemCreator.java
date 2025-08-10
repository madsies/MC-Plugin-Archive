package dev.halcyon.items.halcyonitems.commands;

import dev.halcyon.core.HalcyonCore;
import dev.halcyon.core.objects.HalcyonDataStore;
import dev.halcyon.items.halcyonitems.HalcyonItems;
import dev.halcyon.items.halcyonitems.customItems;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class itemCreator implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        HalcyonCore ref = HalcyonItems.coreRef;
        if (sender instanceof Player){
            Player user = (Player) sender;
            if (user.hasPermission("elpis.itemcreator")){
                if (args.length > 1){
                    HalcyonDataStore prof;

                    switch(args[0].toLowerCase()){
                        case ("create"):
                            if (args.length > 2) {
                                prof = new HalcyonDataStore();
                                switch (args[2].toLowerCase()){
                                    case ("weapon"):
                                        prof.setString("item_type", "weapon");
                                        prof.setString("name", "Default Name");
                                        prof.setString("id", convertToIDNaming(args[1]));
                                        prof.setString("item_class", "damage");
                                        prof.setString("ability_id", "none");
                                        prof.setString("lore", "");

                                        prof.setInt("rarity", 0);
                                        prof.setInt("level_requirement", 1);
                                        prof.setInt("minimum_item_level", 1);
                                        prof.setInt("perk_slots", 0);
                                        prof.setInt("base_damage", 50);
                                        prof.setInt("elemental_damage", 25);

                                        prof.setString("material", Material.IRON_SWORD.name());

                                        prof.setArrayList("element_pool", new ArrayList<String>() {{
                                            add("normal");
                                        }});

                                        prof.setHashMap("bonus_stats", new HashMap<String, Float>());

                                        ref.upsertItemProfile(convertToIDNaming(args[1]), prof);

                                        user.sendMessage(HalcyonCore.MSG_PREFIX + "New item created: " + convertToIDNaming(args[1]));

                                        HalcyonItems.reloadItems();
                                        break;

                                    case ("armour"):
                                        prof.setString("item_type", "armour");
                                        prof.setString("id", convertToIDNaming(args[1]));
                                        prof.setString("name", "Default Name");
                                        prof.setString("item_class", "damage");
                                        prof.setString("lore", "");
                                        prof.setString("set", "none");
                                        prof.setString("armour_type", "chestplate");

                                        prof.setInt("rarity", 0);
                                        prof.setInt("level_requirement", 1);
                                        prof.setInt("minimum_item_level", 1);
                                        prof.setInt("perk_slots", 0);
                                        prof.setInt("defense", 10);

                                        prof.setString("material", Material.IRON_CHESTPLATE.name());

                                        prof.setHashMap("bonus_stats", new HashMap<String, Float>());

                                        ref.upsertItemProfile(convertToIDNaming(args[1]), prof);

                                        HalcyonItems.reloadItems();
                                        break;
                                    case("material"):
                                        prof.setString("item_type", "material");

                                        HalcyonItems.reloadItems();
                                        break;
                                    default:
                                        user.sendMessage(HalcyonCore.MSG_PREFIX+ChatColor.RED+"That is not a valid item type");

                                }

                            }

                            break;
                        case("delete"):
                            if (ref.getItemProfile(convertToIDNaming(args[1])) != null){
                                ref.deleteItemProfile(convertToIDNaming(args[1]));
                                user.sendMessage(HalcyonCore.MSG_PREFIX+ "Item Deleted: "+convertToIDNaming(args[1]));

                                HalcyonItems.reloadItems();
                            }
                            else{
                                user.sendMessage(HalcyonCore.MSG_PREFIX+ "Item does not exist.");
                            }

                            break;
                        case ("edit"):
                            prof = ref.getItemProfile(convertToIDNaming(args[1]));
                            if (prof != null){
                                if (args.length > 2) {
                                    if (HalcyonItems.activeWeapons.containsKey(args[1])) {
                                        if (args.length > 3) {
                                            switch (args[2].toLowerCase()) {
                                                case ("name"):
                                                    nameSetter(args, prof, user);
                                                    break;
                                                case ("item_class"):
                                                    itemClassSetter(args, prof, user);
                                                    break;
                                                case ("ability_id"):
                                                    prof.setString("ability_id", args[3].toLowerCase());
                                                    user.sendMessage(HalcyonCore.MSG_PREFIX + "Item Ability Changed");
                                                    break;
                                                case ("rarity"):
                                                    raritySetter(args, prof, user);
                                                    break;
                                                case ("level_requirement"):
                                                    levelReqSetter(args, prof, user);
                                                    break;
                                                case ("minimum_item_level"):
                                                    minItemLevelSetter(args, prof, user);
                                                    break;
                                                case ("perk_slots"):
                                                    perkSlotSetter(args, prof, user);
                                                    break;
                                                case ("base_damage"):
                                                    try {
                                                        int val = Integer.parseInt(args[3]);
                                                        if (val > 0){
                                                            prof.setInt("base_damage", val);
                                                            user.sendMessage(HalcyonCore.MSG_PREFIX + "Base Damage Changed");
                                                        }else{
                                                            user.sendMessage(HalcyonCore.MSG_PREFIX+ChatColor.RED+"Invalid Base Damage");
                                                        }

                                                    }catch (NumberFormatException e){
                                                        System.out.println(e.getMessage());
                                                        user.sendMessage(HalcyonCore.MSG_PREFIX+ChatColor.RED+"Input could not be parsed into an integer.");
                                                    }
                                                    break;
                                                case ("elemental_damage"):
                                                    try {
                                                        int val = Integer.parseInt(args[3]);
                                                        if (val > 0){
                                                            prof.setInt("elemental_damage", val);
                                                            user.sendMessage(HalcyonCore.MSG_PREFIX + "Elemental Damage Changed");
                                                        }else{
                                                            user.sendMessage(HalcyonCore.MSG_PREFIX+ChatColor.RED+"Invalid Elemental Damage");
                                                        }

                                                    }catch (NumberFormatException e){
                                                        System.out.println(e.getMessage());
                                                        user.sendMessage(HalcyonCore.MSG_PREFIX+ChatColor.RED+"Input could not be parsed into an integer.");
                                                    }
                                                    break;
                                                case ("material"):
                                                    Material handMaterial = user.getInventory().getItemInMainHand().getType();
                                                    if (handMaterial != Material.AIR) {
                                                        prof.setString("material", handMaterial.name());
                                                        user.sendMessage(HalcyonCore.MSG_PREFIX + "Item Material Changed");
                                                    }else{
                                                        user.sendMessage(HalcyonCore.MSG_PREFIX+"That's air dumbass.");
                                                    }
                                                    break;
                                                case ("element_pool"):
                                                    if (args.length > 4){
                                                        ArrayList<String> ar = prof.getArrayList("element_pool");
                                                        switch (args[3]){
                                                            case("add"):
                                                                if (HalcyonItems.ELEMENTS.contains(args[4].toLowerCase()) && !ar.contains(args[4].toLowerCase())){
                                                                    ar.add(args[4].toLowerCase());
                                                                    prof.setArrayList("element_pool", ar);
                                                                    user.sendMessage(HalcyonCore.MSG_PREFIX+"Element Pool Updated.");
                                                                }
                                                                else{
                                                                    user.sendMessage(HalcyonCore.MSG_PREFIX+"Invalid Element or it already exists in the pool.");
                                                                }
                                                                break;

                                                            case("remove"):
                                                                if (ar.contains(args[4].toLowerCase())){
                                                                    ar.remove(args[4].toLowerCase());
                                                                    prof.setArrayList("element_pool", ar);
                                                                    user.sendMessage(HalcyonCore.MSG_PREFIX+"Element Pool Updated.");
                                                                }
                                                                else{
                                                                    user.sendMessage(HalcyonCore.MSG_PREFIX+"Element does not exist in the pool");
                                                                }
                                                                break;
                                                            default:
                                                                user.sendMessage(HalcyonCore.MSG_PREFIX+"Not a valid elementPool operation.");
                                                                break;
                                                        }
                                                    }
                                                    break;
                                                case ("lore"):
                                                    loreSetter(args, prof, user);
                                                    break;
                                                case ("bonus_stats"):
                                                    bonusStatsSetter(args, prof, user);
                                                    break;
                                                default:
                                                    user.sendMessage(HalcyonCore.MSG_PREFIX + ChatColor.RED + "That is not an editable stat.");
                                            }
                                        }
                                        else
                                        {
                                            user.sendMessage(HalcyonCore.MSG_PREFIX + ChatColor.RED + "Incorrect Usage");
                                        }
                                    }
                                    else if (HalcyonItems.activeArmour.containsKey(args[1])) {
                                        if (args.length > 3) {
                                            switch (args[2].toLowerCase()) {
                                                case ("name"):
                                                    nameSetter(args, prof, user);
                                                    break;
                                                case ("item_class"):
                                                    itemClassSetter(args, prof, user);
                                                    break;
                                                case ("rarity"):
                                                    raritySetter(args, prof, user);
                                                    break;
                                                case ("level_requirement"):
                                                    levelReqSetter(args, prof, user);
                                                    break;
                                                case ("minimum_item_level"):
                                                    minItemLevelSetter(args, prof, user);
                                                    break;
                                                case ("perk_slots"):
                                                    perkSlotSetter(args, prof, user);
                                                    break;
                                                case ("material"):
                                                    Material handMaterial = user.getInventory().getItemInMainHand().getType();
                                                    if (handMaterial != Material.AIR) {
                                                        prof.setString("material", handMaterial.name());
                                                        user.sendMessage(HalcyonCore.MSG_PREFIX + "Item Material Changed");
                                                    } else {
                                                        user.sendMessage(HalcyonCore.MSG_PREFIX + "That's air dumbass.");
                                                    }
                                                    break;
                                                case ("lore"):
                                                    loreSetter(args, prof, user);
                                                    break;
                                                case ("bonus_stats"):
                                                    bonusStatsSetter(args, prof, user);
                                                    break;
                                                case("set"):
                                                    prof.setString("set", args[3]);
                                                    user.sendMessage(HalcyonCore.MSG_PREFIX + "Item Set Changed");
                                                    break;
                                                case("defense"):
                                                    try {
                                                        int val = Integer.parseInt(args[3]);
                                                        if (val >= 0){
                                                            prof.setInt("defense", val);
                                                            user.sendMessage(HalcyonCore.MSG_PREFIX + "Defense Changed");
                                                        }else{
                                                            user.sendMessage(HalcyonCore.MSG_PREFIX+ChatColor.RED+"Invalid Defense");
                                                        }
                                                    }catch (NumberFormatException e){
                                                        System.out.println(e.getMessage());
                                                        user.sendMessage(HalcyonCore.MSG_PREFIX+ChatColor.RED+"Input could not be parsed into an integer.");
                                                    }
                                                    break;
                                                case("armour_type"):
                                                    if (HalcyonItems.ARMOUR_TYPES.contains(args[3])){
                                                        prof.setString("armour_type", args[3]);
                                                        user.sendMessage(HalcyonCore.MSG_PREFIX + "Armour type changed.");

                                                    }else{
                                                        user.sendMessage(HalcyonCore.MSG_PREFIX+ChatColor.RED+"Not a valid armour type.");
                                                    }
                                                    break;

                                                default:
                                                    user.sendMessage(HalcyonCore.MSG_PREFIX + ChatColor.RED + "That is not an editable stat.");

                                            }
                                        } else {
                                            user.sendMessage(HalcyonCore.MSG_PREFIX + ChatColor.RED + "Incorrect Usage");
                                        }
                                    }
                                    else
                                    {
                                        user.sendMessage(HalcyonCore.MSG_PREFIX + ChatColor.RED + "That is not a valid item type");
                                    }

                                }
                                else
                                {
                                    user.sendMessage(HalcyonCore.MSG_PREFIX+ ChatColor.RED+"Incorrect Usage");
                                }

                                ref.upsertItemProfile(convertToIDNaming(args[1]), prof);
                            }
                            else
                            {
                                user.sendMessage(HalcyonCore.MSG_PREFIX+ChatColor.RED+"Item does not exist.");
                            }
                            break;
                        case ("view"):
                            prof = ref.getItemProfile(convertToIDNaming(args[1]));
                            if (prof != null) {
                                if (prof.getString("item_type", "none").equalsIgnoreCase("weapon")) {
                                    user.sendMessage("Name: " + prof.getString("name", "no_name"));
                                    user.sendMessage("ID: " + prof.getString("id", "no_id"));
                                    user.sendMessage("Class: " + prof.getString("item_class", "no_name"));
                                    user.sendMessage("Ability: " + prof.getString("ability_id", "no_name"));
                                    user.sendMessage("Item Material:" + prof.getString("material", "no_material"));
                                    user.sendMessage("lore: " + prof.getString("lore", "no_lore"));

                                    int rarity = prof.getInt("rarity", 0);
                                    user.sendMessage("Rarity: " + rarity + " (" + customItems.rarities[rarity] + ")");
                                    user.sendMessage("Lvl Req: " + prof.getInt("level_requirement", 0));
                                    user.sendMessage("Minimum iLevel: " + prof.getInt("minimum_item_level", 0));
                                    user.sendMessage("Perk Slots: " + prof.getInt("perk_slots", 0));
                                    user.sendMessage("Base Damage: " + prof.getInt("base_damage", 0));
                                    user.sendMessage("Elemental Damage: " + prof.getInt("elemental_damage", 0));

                                    user.sendMessage("Element Pool: " + prof.getArrayList("element_pool"));


                                    user.sendMessage("Secondary Stats: " + prof.getHashMap("bonus_stats"));
                                }
                                else if (prof.getString("item_type", "none").equalsIgnoreCase("weapon")){
                                    user.sendMessage("Name: " + prof.getString("name", "no_name"));
                                    user.sendMessage("ID: " + prof.getString("id", "no_id"));
                                    user.sendMessage("Class: " + prof.getString("item_class", "no_name"));

                                }
                            }else{
                                user.sendMessage(HalcyonCore.MSG_PREFIX+"Item does not exist.");
                            }
                            break;
                        case ("list"):
                            String toSend = "Items: ";
                            for (String id : HalcyonItems.itemIDs){
                                toSend += id+", ";
                            }
                            user.sendMessage(toSend.substring(0,toSend.length()-2));
                            break;

                        default:
                            user.sendMessage(HalcyonCore.MSG_PREFIX+ ChatColor.RED+"Incorrect Usage");
                    }

                }else{
                    if (args[0].equalsIgnoreCase("reload")){
                        HalcyonItems.reloadItems();
                        user.sendMessage(HalcyonCore.MSG_PREFIX+"Items reloaded.");
                    }else{
                        user.sendMessage(HalcyonCore.MSG_PREFIX+ ChatColor.RED+"Incorrect Usage");
                    }

                }

            }
            else {
                user.sendMessage(HalcyonCore.MSG_NO_PERM);
            }
        }
        return true;
    }

    public static void nameSetter(String[] args, HalcyonDataStore prof, Player user){
        String name = "";
        for (int i = 3; i < args.length; i++){
            name += args[i]+" ";
        }
        name = name.substring(0, name.length()-1);

        prof.setString("name", name);
        user.sendMessage(HalcyonCore.MSG_PREFIX + "Item Name Changed");

    }

    public static void itemClassSetter(String[] args, HalcyonDataStore prof, Player user){
        if (HalcyonItems.CLASSES.contains(args[3].toLowerCase())) {
            prof.setString("item_class", args[3].toLowerCase());
            user.sendMessage(HalcyonCore.MSG_PREFIX + "Item Class Changed");
        } else {
            user.sendMessage(HalcyonCore.MSG_PREFIX + ChatColor.RED + "You didn't specify a valid class.");
        }

    }

    public static void raritySetter(String[] args, HalcyonDataStore prof, Player user){
        try {
            int val = Integer.parseInt(args[3]);
            if (val > -1 && val < 6) {
                prof.setInt("rarity", val);
                user.sendMessage(HalcyonCore.MSG_PREFIX + "Item Rarity Changed");
            }else{
                user.sendMessage(HalcyonCore.MSG_PREFIX+ChatColor.RED+"Invalid Rarity.");
            }

        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
            user.sendMessage(HalcyonCore.MSG_PREFIX+ChatColor.RED+"Input could not be parsed into an integer.");
        }
    }

    public static void levelReqSetter(String[] args, HalcyonDataStore prof, Player user){
        try {
            int val = Integer.parseInt(args[3]);
            if (val > 0){
                prof.setInt("level_requirement", val);
                user.sendMessage(HalcyonCore.MSG_PREFIX + "Item Level Requirement Changed");
            }else{
                user.sendMessage(HalcyonCore.MSG_PREFIX+ChatColor.RED+"Invalid Level Requirement.");
            }

        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
            user.sendMessage(HalcyonCore.MSG_PREFIX+ChatColor.RED+"Input could not be parsed into an integer.");
        }
    }

    public static void minItemLevelSetter(String[] args, HalcyonDataStore prof, Player user){
        try {
            int val = Integer.parseInt(args[3]);
            if (val > 0){
                prof.setInt("minimum_item_level", val);
                user.sendMessage(HalcyonCore.MSG_PREFIX + "Item Minimum Item Level Changed");
            }else{
                user.sendMessage(HalcyonCore.MSG_PREFIX+ChatColor.RED+"Invalid Minimum Item Level.");
            }

        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
            user.sendMessage(HalcyonCore.MSG_PREFIX+ChatColor.RED+"Input could not be parsed into an integer.");
        }
    }

    public static void perkSlotSetter(String[] args, HalcyonDataStore prof, Player user){
        try {
            int val = Integer.parseInt(args[3]);
            if (val > -1 && val < 6){
                prof.setInt("perk_slots", val);
                user.sendMessage(HalcyonCore.MSG_PREFIX + "Item Perk Slots Changed");
            }else{
                user.sendMessage(HalcyonCore.MSG_PREFIX+ChatColor.RED+"Invalid Perk Slots.");
            }

        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
            user.sendMessage(HalcyonCore.MSG_PREFIX+ChatColor.RED+"Input could not be parsed into an integer.");
        }
    }

    public static void loreSetter(String[] args, HalcyonDataStore prof, Player user){
        String line = "";
        for (int i = 3; i < args.length; i++){
            line += args[i]+" ";
        }
        line = line.substring(0, line.length()-1);


        prof.setString("lore", line);
        user.sendMessage(HalcyonCore.MSG_PREFIX + "Item Name Changed");
    }

    public static void bonusStatsSetter(String[] args, HalcyonDataStore prof, Player user){
        if (args.length > 4){
            if (HalcyonItems.SECONDARY_STATS.contains(args[4])) {
                HashMap<String, Float> stats = prof.getHashMap("bonus_stats");
                switch (args[3]) {
                    case ("add"):
                        if (args.length > 5) {
                            try {
                                float val = Float.parseFloat(args[5]);
                                stats.put(args[4], val);
                                prof.setHashMap("bonus_stats", stats);
                                user.sendMessage(HalcyonCore.MSG_PREFIX + "Bonus stat added.");
                            }catch (NumberFormatException e){
                                user.sendMessage(HalcyonCore.MSG_PREFIX + ChatColor.RED + "Invalid Value Type (Float)");
                            }
                        }
                        else{
                            user.sendMessage(HalcyonCore.MSG_PREFIX + ChatColor.RED + "Invalid Syntax. ");
                        }
                        break;
                    case ("remove"):
                        if (stats.containsKey(args[4])){
                            stats.remove(args[4]);
                            prof.setHashMap("bonus_stats", stats);
                            user.sendMessage(HalcyonCore.MSG_PREFIX + "Bonus stat removed.");
                        }
                        break;
                    default:
                        user.sendMessage(HalcyonCore.MSG_PREFIX + ChatColor.RED + "Not a valid operation.");
                        break;
                }
            }
            else{
                user.sendMessage(HalcyonCore.MSG_PREFIX + ChatColor.RED + "Not a valid secondary stat. ");
            }
        }
        else{
            user.sendMessage(HalcyonCore.MSG_PREFIX + ChatColor.RED + "Invalid Syntax. ");
        }
    }

    public String convertToIDNaming(String rawID){
        return rawID.toLowerCase().replace(' ', '_');
    }
}
