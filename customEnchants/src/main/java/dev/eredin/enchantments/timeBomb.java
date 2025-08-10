package dev.eredin.enchantments;

import dev.eredin.functions.blockDropCalc;
import dev.eredin.functions.blockManipulation;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class timeBomb {
    public static HashMap<UUID, Long> cooldowns = new HashMap<>();

    public static void setCooldown(UUID player){
        cooldowns.put(player, System.currentTimeMillis());
    }

    public static long getCooldown(UUID player){
        try{
            return cooldowns.get(player);
        } catch (Exception e){
            return 0;
        }
    }

    public static void runCommand(PlayerToggleSneakEvent event){
        Player user = event.getPlayer();
        if (event.isSneaking()) {
            ItemStack item = user.getInventory().getItemInMainHand();
            if (item.getType() == Material.NETHERITE_PICKAXE) {
                if (item.getItemMeta().hasEnchant(customEnchantment.MINE_BOMB)) {
                    if (item.getItemMeta().getEnchantLevel(customEnchantment.MINE_BOMB) <= 0) return;
                    long timeleft = System.currentTimeMillis() - timeBomb.getCooldown(user.getUniqueId());

                    if (TimeUnit.MILLISECONDS.toSeconds(timeleft) > 120-(item.getItemMeta().getEnchantLevel(customEnchantment.MINE_BOMB))) {
                        user.sendMessage(ChatColor.LIGHT_PURPLE+"Mine Bomb Used!");
                        int level = item.getItemMeta().getEnchantLevel(customEnchantment.MINE_BOMB);
                        int scalar = Math.floorDiv(level, 5);
                        Location loc = user.getLocation();
                        World w = loc.getWorld();
                        Location start = new Location(w, loc.getBlockX()-(5+scalar), loc.getBlockY()-1,loc.getBlockZ()-(5+scalar));
                        Location end = new Location(w, loc.getBlockX()+(5+scalar), loc.getBlockY()-1,loc.getBlockZ()+(5+scalar));
                        ArrayList<Location> blocks = blockManipulation.getLocationsBetweenLocations(start, end);
                        for (Location l : blocks){
                            List<MetadataValue> temp = l.getBlock().getMetadata("isMineBlock");
                            if (temp.size() == 0) continue;

                            if (l.getBlock().getMetadata("isMineBlock").get(0).asInt() == 1) {
                                blockDropCalc.calc(user, user.getInventory().getItem(0), l.getBlock());
                                l.getBlock().setType(Material.AIR);
                            }
                        }
                        timeBomb.setCooldown(user.getUniqueId());
                    } else {
                        user.sendMessage(ChatColor.RED + "Mine Bomb"+ChatColor.GRAY+"'s cooldown has " +ChatColor.RED+(120-(item.getItemMeta().getEnchantLevel(customEnchantment.MINE_BOMB)) - (TimeUnit.MILLISECONDS.toSeconds(timeleft)))+ChatColor.GRAY+" seconds left");
                    }
                }
            }
        }
    }
}
