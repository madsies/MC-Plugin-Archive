package dev.halcyon.items.halcyonitems;

import dev.elpis.core.objects.ElpisCallback;
import dev.elpis.core.objects.ElpisDatabase;
import dev.halcyon.core.HalcyonCore;
import dev.elpis.core.ElpisCore;
import dev.halcyon.core.objects.HalcyonDataStore;
import dev.halcyon.items.halcyonitems.commands.*;
import dev.halcyon.items.halcyonitems.setBonuses.testSetBonus;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.HashMap;

public final class HalcyonItems extends JavaPlugin {

    private static Plugin instance;
    public static HashMap<String, customWeapon> activeWeapons = new HashMap<String, customWeapon>();
    public static HashMap<String, customArmour> activeArmour = new HashMap<>();
    public static HashMap<String, setBonus> armourSets = new HashMap<>();

    public static ArrayList<String> itemIDs;

    public static final ArrayList<String> CLASSES = new ArrayList<String>() {{
        add("damage"); add("tank"); add("healer");}};
    
    public static final ArrayList<String> WEAPON_ATTRIBUTES = new  ArrayList<String>() {{
        add("name");add("item_class");add("ability_id");add("rarity");add("level_requirement");add("minimum_item_level");
        add("perk_slots");add("base_damage");add("elemental_damage");add("material");add("element_pool");add("lore");
        add("bonus_stats");}};

    public static final ArrayList<String> ARMOUR_ATTRIBUTES = new  ArrayList<String>() {{
        add("name");add("item_class");add("rarity");add("level_requirement");add("minimum_item_level");add("perk_slots");
        add("material");add("lore");add("bonus_stats");add("set");add("defense");add("armour_type");
    }};

    public static final ArrayList<String> ELEMENTS = new ArrayList<String>(){{
        add("normal");add("fire");add("earth");add("water");add("wind");add("light");add("darkness");
    }};

    public static final ArrayList<String> SECONDARY_STATS = new ArrayList<String>(){{
        for (String element : ELEMENTS) add(element+"_defense"); remove("normal_defense");add("defense");
        add("vitality");add("luck");add("swiftness");add("damage_multiplier");add("healing_rate");add("healing_affinity");
        add("damage_reduction");add("damage_absorption");
    }};

    public static final ArrayList<String> ARMOUR_TYPES = new ArrayList<String>(){{
        add("helmet");add("chestplate");add("leggings");add("boots");
    }};
    
    public static final HashMap<String, Boolean> SECONDARY_STATS_PERCENT = new HashMap<String, Boolean>(){{
    put("vitality", false);put("swiftness", false);put("damage_multiplier", true);put("defense", false);
    put("healing_rate", true);put("healing_affinity", true);put("fire_defense", false);put("water_defense", false);
    put("wind_defense", false);put("earth_defense", false);put("light_defense", false);put("darkness_defense", false);
    put("damage_reduction", true);put("damage_absorption", true);put("luck", false);
    }};

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        reloadItems();

        registerSetBonuses();

        this.getCommand("giveweapon").setExecutor(new giveWeapon());
        this.getCommand("giveweapon").setTabCompleter(new onTabComplete());

        this.getCommand("givearmour").setExecutor(new giveArmour());

        this.getCommand("getitemdata").setExecutor(new getItemData());

        this.getCommand("itemcreator").setExecutor(new itemCreator());
        this.getCommand("itemcreator").setTabCompleter(new onTabComplete());



        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getServer().getOnlinePlayers()){
                    for (int slot = 0; slot < 36; slot++){
                        ItemMeta meta = null;
                        if (p.getInventory().getItem(slot) != null) {
                            if (p.getInventory().getItem(slot).getItemMeta() != null)
                                meta = p.getInventory().getItem(slot).getItemMeta();
                                if (meta.getPersistentDataContainer().has(new NamespacedKey(getPlugin(), "item_id"), PersistentDataType.STRING)) {
                                    String item_id = meta.getPersistentDataContainer().get(new NamespacedKey(getPlugin(), "item_id"), PersistentDataType.STRING);
                                    if (activeWeapons.containsKey(meta.getPersistentDataContainer().get(new NamespacedKey(getPlugin(), "item_id"), PersistentDataType.STRING))) {

                                        int iLevel = meta.getPersistentDataContainer().get(new NamespacedKey(getPlugin(), "item_level"), PersistentDataType.INTEGER);
                                        String perks = meta.getPersistentDataContainer().get(new NamespacedKey(getPlugin(), "item_perks"), PersistentDataType.STRING);
                                        if (perks == null) {
                                            perks = "";
                                        }
                                        p.getInventory().setItem(slot, activeWeapons.get(item_id).createItemInstance(iLevel, translatePerks(perks), p, p.getInventory().getItem(slot)));

                                }
                                    else if  (activeArmour.containsKey(meta.getPersistentDataContainer().get(new NamespacedKey(getPlugin(), "item_id"), PersistentDataType.STRING))){
                                        int iLevel = meta.getPersistentDataContainer().get(new NamespacedKey(getPlugin(), "item_level"), PersistentDataType.INTEGER);
                                        String perks = meta.getPersistentDataContainer().get(new NamespacedKey(getPlugin(), "item_perks"), PersistentDataType.STRING);
                                        if (perks == null) {
                                            perks = "";
                                        }

                                        p.getInventory().setItem(slot, activeArmour.get(item_id).createItemInstance(iLevel, translatePerks(perks), p, p.getInventory().getItem(slot)));
                                    }
                            }
                        }

                    }
                }
                System.out.println("[HalcyonItems] Custom Items Refreshed.");
            }
        }, 20, 200);


    }

    public static ArrayList<itemPerk> translatePerks (String perkString){
        String[] split = perkString.split("/");
        ArrayList<itemPerk> toReturn = new ArrayList<>();
        for (String s : split){
            if (customItems.itemPerks.containsKey(s)) {
                toReturn.add(customItems.itemPerks.get(s));
            }
        }

        return toReturn;
    }

    public static void registerSetBonuses(){
        armourSets.put("test_set_bonus", new testSetBonus());
    }


    public static void reloadItems(){
        ElpisDatabase itemDatabase = ElpisCore.utils.getDatabase("CustomItemData");
        ArrayList<String> IDs = itemDatabase.loadSingle("idList", new ArrayList<>());
        itemIDs = IDs;
        for (String ID : IDs){
            HalcyonDataStore store = coreRef.getItemProfile(ID);

            if (store.getString("item_type", "none").equalsIgnoreCase("weapon")) {

                activeWeapons.put(ID, new customWeapon(store.getString("name", "name"),
                        store.getString("id", "item_id"),
                        store.getString("item_class", "damage"),
                        store.getInt("rarity", 0),
                        Material.matchMaterial((store.getString("material", Material.IRON_SWORD.toString()))),
                        store.getArrayList("element_pool"),
                        store.getInt("level_requirement", 1),
                        fixHashMap(store.getHashMap("bonus_stats")),
                        store.getString("ability_id", "none"),
                        store.getInt("minimum_item_level", 1),
                        store.getInt("perk_slots", 0),
                        store.getInt("base_damage", 0),
                        store.getInt("elemental_damage", 0),
                        store.getString("lore", "")
                ));
            }
            else if (store.getString("item_type", "none").equalsIgnoreCase("armour")) {

                activeArmour.put(ID, new customArmour(
                        store.getString("name", "name"),
                        store.getString("id", "item_id"),
                        store.getString("item_class", "damage"),
                        store.getInt("rarity", 0),
                        Material.matchMaterial((store.getString("material", Material.IRON_SWORD.toString()))),
                        store.getInt("level_requirement", 1),
                        store.getString("set", "none"),
                        store.getInt("minimum_item_level", 1),
                        store.getInt("defense", 0),
                        store.getInt("perk_slots", 0),
                        store.getString("lore", ""),
                        fixHashMap(store.getHashMap("bonus_stats")),
                        store.getString("armour_type", "chestplate")));

            }
        }
    }

    public static HashMap<String, Float> fixHashMap(HashMap<String, Double> hm){
        HashMap<String, Float> fixed = new HashMap<>();
        for (String key : hm.keySet()) fixed.put(key, hm.get(key).floatValue());
        return fixed;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getPlugin(){
        return instance;
    }


}