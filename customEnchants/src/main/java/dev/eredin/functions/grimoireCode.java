package dev.eredin.functions;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.RayTraceResult;

public class grimoireCode {
    public static void selfBoost(Player player){
        PotionEffect pot = new PotionEffect(PotionEffectType.SPEED, 200, 10);
        player.addPotionEffect(pot);
        pot = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 10);
        player.addPotionEffect(pot);
        RayTraceResult x = player.rayTraceBlocks(10);

        Block b = player.getTargetBlock(null, 5);
        Location l = b.getLocation();
        player.getWorld().strikeLightningEffect(l);
        player.setCustomName(ChatColor.BLACK + "Asta");
    }

    public static void enemyDebuff(Player player, Player target){
        if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.BLACK + "5 Leaf Clover Grimoire")){
            target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 5));
            target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 5));
            target.sendMessage(ChatColor.BLACK+" You feel your magic drain from you...");
        }
    }
}
