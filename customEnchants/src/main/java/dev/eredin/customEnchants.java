package dev.eredin;

import dev.eredin.api.API;
import dev.eredin.commands.*;
import dev.eredin.enchantments.enchantManager;
import dev.eredin.events.*;
import dev.eredin.saving.dataManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class customEnchants extends JavaPlugin {

    @Override
    public void onEnable(){
        dataManager.api = (API) getServer().getPluginManager().getPlugin("API");

        getServer().getPluginManager().registerEvents(new inventoryMove(), this);
        getServer().getPluginManager().registerEvents(new playerDrop(), this);
        getServer().getPluginManager().registerEvents(new playerInteract(), this);
        getServer().getPluginManager().registerEvents(new playerJoin(), this);
        getServer().getPluginManager().registerEvents(new blockMined(), this);
        getServer().getPluginManager().registerEvents(new durabilityLost(), this);
        getServer().getPluginManager().registerEvents(new message(), this);
        getServer().getPluginManager().registerEvents(new playerCrouch(), this);
        getServer().getPluginManager().registerEvents(new death(), this);
        getServer().getPluginManager().registerEvents(new pvpEvent(), this);
        getServer().getPluginManager().registerEvents(new changeSlot(), this);
        enchantManager.register();

        this.getCommand("trash").setExecutor(new trash());
        this.getCommand("blocksMined").setExecutor(new blockMinedCommand());
        this.getCommand("shards").setExecutor(new shardCommand());
        this.getCommand("fixPick").setExecutor(new refreshPick());
        this.getCommand("grimoire").setExecutor(new clover());
        this.getCommand("bmLeaderboard").setExecutor(new bmLeaderboard());
        this.getCommand("shard").setExecutor(new shardChangeCommand());
        this.getCommand("messageToggles").setExecutor(new messageToggles());

        for (Player p : this.getServer().getOnlinePlayers()){  // Loads every online player's pickaxe data.
            UUID id = p.getUniqueId();
            dataManager.addPlayer(id);
            dataManager.getData(p.getUniqueId()).updatePickLore(p);
        }

        HashMap<String, Integer> bv = new HashMap<String, Integer>();
        // cobble, stone, coal, iron, gold, coalB, lapis, ironB, redstone, goldB, diamond, lapisB + redstoneB, emerald, diamondB, emeraldB, obsidian
        bv.put(Material.COBBLESTONE.name(), 5);
        bv.put(Material.STONE.name(), 10);
        bv.put(Material.COAL.name(), 20);
        bv.put(Material.IRON_INGOT.name(), 50);
        bv.put(Material.GOLD_INGOT.name(), 100);
        bv.put(Material.COAL_BLOCK.name(), 180);
        bv.put(Material.LAPIS_LAZULI.name(), 50);
        bv.put(Material.IRON_BLOCK.name(), 450);
        bv.put(Material.REDSTONE.name(), 150);
        bv.put(Material.GOLD_BLOCK.name(), 900);
        bv.put(Material.DIAMOND.name(), 1200);
        bv.put(Material.LAPIS_BLOCK.name(), 450);
        bv.put(Material.REDSTONE_BLOCK.name(), 1350);
        bv.put(Material.EMERALD.name(), 2000);
        bv.put(Material.DIAMOND_BLOCK.name(), 10800);
        bv.put(Material.EMERALD_BLOCK.name(), 18000);
        bv.put(Material.OBSIDIAN.name(), 25000);

        dataManager.api.setGlobalData("blockValues", bv);

        System.out.println("Block Values have been set.");
        dataManager.blockValues = bv;


    }

    @Override
    public void onDisable(){
        // dataManager.saveAll();
    }

}
