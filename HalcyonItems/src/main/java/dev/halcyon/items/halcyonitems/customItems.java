package dev.halcyon.items.halcyonitems;

import dev.halcyon.items.halcyonitems.itemAbilities.testAbility;
import dev.halcyon.items.halcyonitems.itemAbilities.waterAttuned;
import dev.halcyon.items.halcyonitems.itemPerks.testPerk;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;

public class customItems {

    public static final ChatColor[] rarityColours = {ChatColor.WHITE, ChatColor.GREEN, ChatColor.BLUE, ChatColor.DARK_PURPLE, ChatColor.GOLD, ChatColor.RED};

    public static final HashMap<String, ChatColor> classColours = new HashMap<String, ChatColor>(){{
        put("healer", ChatColor.GREEN);
        put("damage", ChatColor.RED);
        put("tank", ChatColor.BLUE);
    }};

    public static final HashMap<String, ChatColor> elementalColours = new HashMap<String, ChatColor>(){{
        put("fire", ChatColor.RED);
        put("water", ChatColor.AQUA);
        put("earth", ChatColor.DARK_GREEN);
        put("wind", ChatColor.GREEN);
        put("light", ChatColor.YELLOW);
        put("darkness", ChatColor.DARK_GRAY);
        put("normal", ChatColor.GRAY);
    }};

    public static final HashMap<String, String> elementalSymbols = new HashMap<String, String>(){{
        put("fire", ChatColor.RED+" ✦");
        put("water", ChatColor.AQUA+" ☂");
        put("earth", ChatColor.DARK_GREEN+" ✿");
        put("wind", ChatColor.GREEN+" ≈");
        put("light", ChatColor.YELLOW+" ☀");
        put("darkness", ChatColor.DARK_GRAY+" ❉");
        put("normal", ChatColor.GRAY+"");
    }};


    public static final HashMap<String, itemAbility> itemAbility = new HashMap<String, itemAbility>(){{
        put("test_ability", new testAbility());
        put("water_attuned", new waterAttuned());
    }};

    public static final HashMap<String, itemPerk> itemPerks = new HashMap<String, itemPerk>(){{
        put("test_perk", new testPerk());
    }};

    public static final String[] rarities = {"common", "uncommon", "rare", "epic", "legendary", "mythic"};


    public static String fixString(String str){
        if (str.length() < 2) return str;
        String[] spce = str.split("_");
        String finalStr = "";
        for (String sub : spce){
            finalStr += sub.substring(0,1).toUpperCase() + sub.substring(1) + " ";
        }
        finalStr = finalStr.substring(0, finalStr.length()-1);

        return finalStr;
    }


}
