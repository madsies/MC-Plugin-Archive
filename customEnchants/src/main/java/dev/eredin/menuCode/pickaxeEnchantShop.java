package dev.eredin.menuCode;

import dev.eredin.enchantments.enchantClass;
import dev.eredin.enchantments.enchantManager;
import dev.eredin.saving.dataManager;
import dev.eredin.saving.pickaxeData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class pickaxeEnchantShop {

    public static ItemStack createGuiItem(final Material material, final String name, final int enchID, Player user, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        assert meta != null;
        meta.setDisplayName(ChatColor.RED+name);

        // Set the lore of the item

        List<String> list = new ArrayList<>();
        if (enchID > -1) {
            int level = 0;
            for (String l : lore){
                list.add(ChatColor.GRAY+l);
            }
            enchantClass ench = enchantManager.enchants[enchID];
            if (dataManager.getData(user.getUniqueId()).enchantments.get(ench.getName()) != null){
                level = dataManager.getData(user.getUniqueId()).enchantments.get(ench.getName());
            }
            String costShift = "";
            if (level + 10 <= ench.getMaxLevel()){
                int runningTotal = 0;
                for (int i = 0; i < 10; i++){
                    runningTotal += ench.getCost(level+i);
                }
                costShift = ", "+ChatColor.RED+runningTotal+ChatColor.GRAY+" (x10)";
            }

            String cost = ChatColor.GRAY+"Cost: "+ChatColor.RED+ench.getCost(level+1)+ChatColor.GRAY+" (x1)"+costShift;
            list.add(cost);
            String lvl = ChatColor.GRAY+"Current Level: "+ChatColor.RED+ level;
            list.add(lvl);
            String max = ChatColor.GRAY+"Maximum Level: "+ChatColor.RED+ench.getMaxLevel();
            list.add(max);
            item.addUnsafeEnchantment(ench, ench.getMaxLevel());
        }else {
            list.addAll(Arrays.asList(lore));
        }
        meta.setLore(list);
        item.setItemMeta(meta);

        return item;
    }

    public static Inventory createShopGUI (Player player) {
        Inventory test = Bukkit.createInventory(null, 45, "Enchantments");
        for (int i = 0; i < 45; i++) {
            test.setItem(i, createGuiItem(Material.GRAY_STAINED_GLASS_PANE, " ", -1, player));
        }
        test.setItem(3, createGuiItem(Material.LIME_STAINED_GLASS_PANE, "", -1, player, ChatColor.LIGHT_PURPLE+"Left-Click to buy x1", ChatColor.LIGHT_PURPLE+"Shift-Left-Click to buy x10", ""));
        test.setItem(4, createGuiItem(Material.PRISMARINE_SHARD, ChatColor.GRAY+"Shards: "+ChatColor.RED+dataManager.getData(player.getUniqueId()).shards, -1, player));
        test.setItem(5, createGuiItem(Material.RED_STAINED_GLASS_PANE, "", -1, player, ChatColor.LIGHT_PURPLE+"Right-Click to sell x1", ChatColor.LIGHT_PURPLE+"Shift-Right-Click to sell x10", ""));

        for (enchantClass ench : enchantManager.enchants) {
            test.setItem(ench.getSlot(), createGuiItem(ench.getIcon(), ench.getName(), ench.getEnchID(), player, ench.getDescription()));

        }
        return test;
    }

    public static void updateElements(InventoryView inv, Player player){
        inv.setItem(4, createGuiItem(Material.PRISMARINE_SHARD, ChatColor.GRAY+"Shards: "+ChatColor.RED+dataManager.getData(player.getUniqueId()).shards, -1, player));
        for (enchantClass ench : enchantManager.enchants) {
            inv.setItem(ench.getSlot(), createGuiItem(ench.getIcon(), ench.getName(), ench.getEnchID(), player, ench.getDescription()));
        }
    }

    public static void buyCalc(int level, int amount, int shards, enchantClass ench, Player user){
        if (level + amount <= ench.getMaxLevel()) {
            int totalCost = 0;
            for (int i = 1; i <= amount; i++){
                totalCost = totalCost + ench.getCost(level+ i);
            }
            if (shards >= totalCost) {
                pickaxeData data = dataManager.getData(user.getUniqueId());
                data.shards -= totalCost;
                data.applyEnchant(ench, level + amount);
                String multi = "s";
                if (amount == 1) multi = "";
                if (dataManager.getToggles(user.getUniqueId()).toggles.get("shardMessage"))
                    user.sendMessage(ChatColor.GRAY + "You have added " + ChatColor.RED + amount + " " + ChatColor.GRAY + "level" + multi + " to " + ChatColor.RED + ench.getName());

                user.sendMessage(ChatColor.RED+"-"+totalCost+ChatColor.GRAY+" shards!");
            }else{
                user.sendMessage(ChatColor.RED+"You do not have enough shards to afford this enchantment.");
            }

        }
    }

    public static void clickedShop(InventoryClickEvent event) {
        Player user = (Player) event.getWhoClicked();
        ClickType click = event.getClick();
        boolean slot = false;
        for (enchantClass ench : enchantManager.enchants) {
            if (event.getSlot() == ench.getSlot()) {
                slot = true;
                pickaxeData pickaxeClass = dataManager.getData(user.getUniqueId());
                int userShards = pickaxeClass.shards;
                int level = 0;
                if (pickaxeClass.enchantments.get(ench.getName()) != null) {
                    level = (pickaxeClass.enchantments.get(ench.getName()));
                }else{
                    pickaxeClass.enchantments.put(ench.getName(), 0);
                }

                if (pickaxeClass.enchantments.get(ench.getName()) != null) {
                    if (click == ClickType.LEFT) {
                        buyCalc(level, 1, userShards, ench, user);

                    }if (click == ClickType.DOUBLE_CLICK) {
                        buyCalc(level, 2, userShards, ench, user);

                    }if (click == ClickType.SHIFT_LEFT){

                        if (pickaxeClass.enchantments.get(ench.getName()) + 10 <= ench.getMaxLevel()) {
                            buyCalc(level, 10, userShards, ench, user);

                        }else{
                            if (pickaxeClass.enchantments.get(ench.getName()) + 1 <= ench.getMaxLevel()) {

                                int diff = ench.getMaxLevel() - pickaxeClass.enchantments.get(ench.getName());
                                buyCalc(level, diff, userShards, ench, user);
                            }
                        }
                    }
                    if(click == ClickType.RIGHT) {
                        if (level - 1 >= 0) {
                            pickaxeClass.applyEnchant(ench, pickaxeClass.enchantments.get(ench.getName()) - 1);
                            dataManager.getData(user.getUniqueId()).shards += ench.getCost(level);
                            user.sendMessage(ChatColor.GRAY+"You have removed "+ChatColor.RED+1+" "+ChatColor.GRAY+"level from "+ChatColor.RED+ench.getName());
                            user.sendMessage(ChatColor.GREEN+"+"+ench.getCost(level)+ChatColor.GRAY+" shards!");
                        }
                    }if(click == ClickType.SHIFT_RIGHT){
                        if (pickaxeClass.enchantments.get(ench.getName()) - 10 >= 0) {
                            int totalCost = 0;
                            for (int i = 1; i <= 10; i++){
                                totalCost = totalCost + ench.getCost(level+ i);
                            }
                            dataManager.getData(user.getUniqueId()).shards += totalCost;
                            pickaxeClass.applyEnchant(ench, pickaxeClass.enchantments.get(ench.getName()) - 10);
                            user.sendMessage(ChatColor.GRAY+"You have removed "+ChatColor.RED+"10 "+ChatColor.GRAY+"levels from "+ChatColor.RED+ench.getName());
                            user.sendMessage(ChatColor.GREEN+"+"+totalCost+ChatColor.GRAY+" shards!");
                        }else{
                            if (pickaxeClass.enchantments.get(ench.getName()) - 1 >= 0) {
                                int lvl = pickaxeClass.enchantments.get(ench.getName());
                                int totalCost = 0;
                                for (int i = 1; i <= lvl; i++){
                                    totalCost = totalCost + ench.getCost(level+ i);
                                }
                                dataManager.getData(user.getUniqueId()).shards += totalCost;
                                pickaxeClass.applyEnchant(ench, 0);
                                user.sendMessage(ChatColor.GRAY+"You have removed "+ChatColor.RED+lvl+" "+ChatColor.GRAY+"levels from "+ChatColor.RED+ench.getName());
                                user.sendMessage(ChatColor.GREEN+"+"+totalCost+ChatColor.GRAY+" shards!");
                            }
                        }
                    }
                    InventoryView inv = user.getOpenInventory();
                    updateElements(inv, user);
                }else {
                    pickaxeClass.enchantments.put(ench.getName(), 1);
                    pickaxeClass.applyEnchant(ench, 1);
                }



            }
        }
        if (!slot){
            event.setCancelled(true);
        }
    }


}
