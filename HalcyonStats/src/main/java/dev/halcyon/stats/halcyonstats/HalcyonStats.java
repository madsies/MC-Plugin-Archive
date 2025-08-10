package dev.halcyon.stats.halcyonstats;

import dev.halcyon.stats.halcyonstats.commands.playerJoin;
import dev.halcyon.stats.halcyonstats.commands.playerStats;
import dev.halcyon.stats.halcyonstats.events.itemSwap;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public final class HalcyonStats extends JavaPlugin {

    public static HashMap<Player, HashMap<String, Float>> playerTotalStats = new HashMap<>();  // Sum of the rest
    public static HashMap<Player, HashMap<String, Float>> playerHandStats = new HashMap<>();
    public static HashMap<Player, HashMap<String, Float>> playerArmourStats = new HashMap<>();
    public static HashMap<Player, HashMap<String, Float>> playerBuffStats = new HashMap<>();

    public static final ArrayList<String> ELEMENTS = new ArrayList<String>(){{
        add("normal");add("fire");add("earth");add("water");add("wind");add("light");add("darkness");
    }};

    public static final ArrayList<String> USER_STATS = new ArrayList<String>(){{
        for (String element : ELEMENTS) add(element+"_defense");for (String element : ELEMENTS) add(element+"_damage");
        remove("normal_defense");remove("normal_damage");add("defense");add("physical_damage");
        add("vitality");add("luck");add("swiftness");add("damage_multiplier");add("healing_rate");add("healing_affinity");
        add("damage_reduction");add("damage_absorption");
    }};


    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getServer().getPluginManager().registerEvents(new itemSwap(), this);
        this.getServer().getPluginManager().registerEvents(new playerJoin(), this);

        this.getCommand("playerstats").setExecutor(new playerStats());

        for (Player p : getServer().getOnlinePlayers()){
            // Do all of the stats gathering shite yes yes.
            playerTotalStats.put(p, new HashMap<String, Float>());
            calculateHandStats(p);
            calculateArmourStats(p);
        }

        System.out.println("Halcyon Stats loaded.");

    }

    public static void calculateHandStats(Player user){
        HashMap<String, Float> stats = new HashMap<>();
        ItemStack item = user.getInventory().getItem(user.getInventory().getHeldItemSlot());

        if (item.hasItemMeta()){
            ItemMeta metadata = item.getItemMeta();
            if (metadata != null) {
                PersistentDataContainer statsContainer = metadata.getPersistentDataContainer();
                    for (NamespacedKey key : statsContainer.getKeys()) {
                        if (statsContainer.has(key, PersistentDataType.FLOAT) && HalcyonStats.USER_STATS.contains(key.getKey())) {
                            stats.put(key.getKey(), statsContainer.get(key, PersistentDataType.FLOAT));
                        }
                        if (key.getKey().equalsIgnoreCase("item_type")){
                            if (!Objects.equals(statsContainer.get(key, PersistentDataType.STRING), "weapon")) {
                                stats = new HashMap<>();
                                playerHandStats.put(user, stats);
                                return;
                            }
                        }
                    }
            }
        }

        playerHandStats.put(user, stats);
    }

    public static void calculateBuffStats(Player user){
        HashMap<String, Float> stats = new HashMap<>();

        // Fix later lol

        playerBuffStats.put(user, stats);
    }

    public static void calculateArmourStats(Player user){
        HashMap<String, Float> stats = new HashMap<>();

        for (ItemStack armour :  user.getInventory().getArmorContents()){
            if (armour != null){
                if (armour.hasItemMeta()) {
                    ItemMeta metadata = armour.getItemMeta();
                    if (metadata != null) {
                        PersistentDataContainer statsContainer = metadata.getPersistentDataContainer();
                        for (NamespacedKey key : statsContainer.getKeys()) {
                            if (statsContainer.has(key, PersistentDataType.FLOAT) && HalcyonStats.USER_STATS.contains(key.getKey())) {
                                stats.put(key.getKey(), statsContainer.get(key, PersistentDataType.FLOAT));
                            }
                        }
                    }
                }
            }
        }
        playerArmourStats.put(user, stats);
    }

    public static void totalPlayerStats(Player user){
        HashMap<String, Float> stats = new HashMap<>();

        for (String s : USER_STATS){
            float total = 0.0f;
            if (playerArmourStats.get(user).containsKey(s)) total += playerArmourStats.get(user).get(s);
            if (playerHandStats.get(user).containsKey(s)) total += playerHandStats.get(user).get(s);
            // if (playerBuffStats.get(user).containsKey(s)) total += playerBuffStats.get(user).get(s);
            stats.put(s, total);

        }

        playerTotalStats.put(user, stats);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        System.out.println("Halcyon Stats disabled.");
    }
}
