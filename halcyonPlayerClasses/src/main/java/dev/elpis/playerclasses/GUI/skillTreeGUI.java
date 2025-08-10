package dev.elpis.playerclasses.GUI;

import dev.elpis.core.ElpisCore;
import dev.elpis.playerclasses.PlayerClasses;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class skillTreeGUI {
    public static final nodeClass[] damageNodes = {
            //t1
            nodes.ADRENALINE,
            nodes.ATTACK_BUFF,
            nodes.APPRENTICE,
            nodes.OPPRESSOR,
            nodes.PUNISHING_BLOW

    };

    public static nodeClass[] tankNodes = {
            //t1
            nodes.THICK_PLATING,
            nodes.VETERAN_SAILOR,
            nodes.HOLY_KNIGHT, // needs t2 continuation
            nodes.DARK_KNIGHT, // ^^
            nodes.HORDE_RESISTANT,
    };

    public static nodeClass[] healerNodes = {
            //t1
            nodes.RESTED_BOOST,
            nodes.FIELD_MEDIC,
            nodes.GENEROUS,

            //t2
            nodes.ALCHEMIST,
            nodes.ELEMENTAL_SYNERGY,
            nodes.SELFLESS,

            //t3
            nodes.CLERIC,
            nodes.ELEMENTAL_CATALYST,
            nodes.ROBIN_HOOD,

            //t4
            nodes.MORALE_BOOSTER,
            nodes.PROTECTOR,
            nodes.RELEASED_POTENTIAL,

            //t5
            nodes.BLOOD_MAGIC,
            nodes.GUARDIAN_ANGEL,
            nodes.DOUBLE_DOWN,
    };


    public static Inventory inv;

    public static Inventory generateSkillGUI(Player user){
        inv = Bukkit.createInventory(null, 54, "Skill Tree");
        Material[] mats = {Material.WHITE_STAINED_GLASS_PANE, Material.LIME_STAINED_GLASS_PANE, Material.BLUE_STAINED_GLASS_PANE, Material.PURPLE_STAINED_GLASS_PANE, Material.RED_STAINED_GLASS_PANE};
        int ClassLevel = PlayerClasses.getClassLevel(user);

        for (int i = 0; i < 54; i++) {
            if (i >= 45){
                inv.setItem(i, createAestheticItem(Material.BLACK_STAINED_GLASS_PANE, "", user));
            }else{
                if (i % 9 == 0 || i % 9 == 8){
                    String confirmation = ChatColor.RED+"✕";

                    switch (i / 9){
                        case (0):
                            if (ClassLevel >= 1) confirmation = ChatColor.GREEN+"✔";
                            inv.setItem(i, createAestheticItem(mats[i/9], ChatColor.translateAlternateColorCodes('&', "&f&lTier "+((i/9)+1)), user, ChatColor.GRAY+"Requires Level 1 "+ confirmation));
                            break;
                        case(1):
                            if (ClassLevel >= 10) confirmation = ChatColor.GREEN+"✔";
                            inv.setItem(i, createAestheticItem(mats[i/9], ChatColor.translateAlternateColorCodes('&', "&a&lTier "+((i/9)+1)), user, ChatColor.GRAY+"Requires Level 10 "+ confirmation));
                            break;
                        case(2):
                            if (ClassLevel >= 25) confirmation = ChatColor.GREEN+"✔";
                            inv.setItem(i, createAestheticItem(mats[i/9], ChatColor.translateAlternateColorCodes('&', "&9&lTier "+((i/9)+1)), user, ChatColor.GRAY+"Requires Level 25 " + confirmation));
                            break;
                        case(3):
                            if (ClassLevel >= 40) confirmation = ChatColor.GREEN+"✔";
                            inv.setItem(i, createAestheticItem(mats[i/9], ChatColor.translateAlternateColorCodes('&', "&5&lTier "+((i/9)+1)), user, ChatColor.GRAY+"Requires Level 45 " + confirmation));
                            break;
                        case(4):
                            if (ClassLevel >= 70) confirmation = ChatColor.GREEN+"✔";
                            inv.setItem(i, createAestheticItem(mats[i/9], ChatColor.translateAlternateColorCodes('&', "&4&lTier "+((i/9)+1)), user, ChatColor.GRAY+"Requires Level 70 " + confirmation));
                            break;
                        default:
                            break;


                    }

                }
                else{
                    inv.setItem(i, createAestheticItem(Material.GRAY_STAINED_GLASS_PANE, " ", user));
                }
            }


        }
        HashMap<Integer, HashMap<Integer, Integer>> skills = PlayerClasses.deParseSkills(PlayerClasses.getClassSkills(user));

        refreshUI(user);

        inv.setItem(53,createAestheticItem(Material.NETHER_STAR, ChatColor.translateAlternateColorCodes('&',"&5&lReset Skill Points"), user, "You will receive 100% of spent skill points", "It costs [REDACTED]"));

        return inv;
    }

    public static ItemStack createAestheticItem(final Material material, final String name, Player user, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        assert meta != null;
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack skillTreeNode(nodeClass node, int level) {
        ItemStack item;
        if (level == 0){
            item = new ItemStack(node.getMat(), 1);
        }
        else{
            item = new ItemStack(node.getMat(), level);
        }
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        assert meta != null;
        meta.setDisplayName(node.getName());


        // Set the lore of the item
        List<String> finalisedLore = new ArrayList<>();
        finalisedLore.add(ChatColor.translateAlternateColorCodes('&',"&9Level:&f ")+level+"/"+node.maxLevel);

        finalisedLore.add("");
        for (String s : node.lore)
            finalisedLore.add(ChatColor.translateAlternateColorCodes('&', "&8&o"+s));

        if (level > 0) {
            finalisedLore.add("");
            finalisedLore.add(ChatColor.translateAlternateColorCodes('&', "&e&lCurrent:"));
            for (String l : node.dynamicDescription(level)){
                finalisedLore.add(ChatColor.GRAY+l);
            }
        }

        if (level < node.maxLevel) {
            finalisedLore.add("");
            finalisedLore.add(ChatColor.translateAlternateColorCodes('&', "&e&lNext level:"));
            for (String l : node.dynamicDescription(level + 1)) {
                finalisedLore.add(ChatColor.GRAY + l);
            }
        }

        if (node.needsNodes()){
            finalisedLore.add("");
            finalisedLore.add(ChatColor.DARK_GRAY+"Required Skills: "+ ChatColor.DARK_GREEN+PlayerClasses.fixString(node.formattedPreReq()));
        }

        if (node.hasConflicts()){
            if (!node.needsNodes()) finalisedLore.add("");
            finalisedLore.add(ChatColor.DARK_GRAY+"Conflicting Skills: "+ ChatColor.DARK_RED+PlayerClasses.fixString(node.formattedConflicts()));
        }

        finalisedLore.add("");
        finalisedLore.add(ChatColor.translateAlternateColorCodes('&',"&fCost: &9"+node.getTier()+" &r&7Skill point(s)"));

        if (level > 0) {
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            item.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
        }



        meta.setLore(finalisedLore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        item.setItemMeta(meta);

        return item;

    }

    public static int calcSpent(Player user){
        HashMap<Integer, HashMap<Integer, Integer>> skills = PlayerClasses.deParseSkills(PlayerClasses.getClassSkills(user));
        int total = 0;
        int tier = 1;
        for (HashMap<Integer, Integer> hm : skills.values()){
            for (Integer i : hm.values()){
                total += i*tier;
            }
            tier += 1;
        }
        return total;
    }


    public static void clickedShop(InventoryClickEvent event) {
        Player user = (Player) event.getWhoClicked();
        ClickType click = event.getClick();
        boolean slot = false;

        nodeClass[] usedNodes = getClassNodes(user);

        int classLevel = PlayerClasses.getClassLevel(user);

        for (nodeClass node : usedNodes){
            if (event.getSlot() == node.getSlot()){
                slot = true;

                int cost = node.getTier();
                int playerPoints = PlayerClasses.getSkillPoints(user);

                HashMap<Integer, HashMap<Integer, Integer>> skills = PlayerClasses.deParseSkills(PlayerClasses.getClassSkills(user));
                int value = skills.get(node.getTier()).get(node.position);

                // need to fetch the level to make sure it's not above max level.
                ArrayList<String> owned = getAllSkills(user);


                if (classLevel >= nodes.REQUIREMENTS[node.getTier()]) {
                    if (cost <= playerPoints) {
                        if (value < node.maxLevel) {
                            if (node.hasPrerequisites(owned)) {
                                if (!node.hasConflicted(owned)) {
                                    PlayerClasses.setSkillPoints(user, playerPoints - cost);

                                    // set the skill level +1.

                                    skills.get(node.getTier()).put(node.position, value + 1);

                                    PlayerClasses.setClassSkills(user, PlayerClasses.parseSkills(skills));
                                    user.playSound(user.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_HIT, 1.0f, 1.0f);

                                    inv.setItem(node.getSlot(), skillTreeNode(node, value + 1));
                                    inv.setItem(49, createAestheticItem(Material.SUNFLOWER, ChatColor.translateAlternateColorCodes('&', "&e&lSkill Points: " + PlayerClasses.getSkillPoints(user)), user));
                                    user.sendMessage(ElpisCore.MSG_PREFIX + node.getName() + ChatColor.GRAY + " Skill upgraded to level " + (value + 1) + ".");
                                } else
                                {
                                    user.sendMessage(ElpisCore.MSG_PREFIX + ChatColor.RED + "One of your current skills conflicts with " + node.getName() + ChatColor.GRAY + ".");
                                    user.playSound(user.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 0.6f);
                                }
                            } else
                            {
                                user.sendMessage(ElpisCore.MSG_PREFIX + ChatColor.RED + "You don't have the prerequisite skills to buy " + node.getName() + ChatColor.GRAY + ".");
                                user.playSound(user.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 0.6f);
                            }
                        }else
                        {
                            user.sendMessage(ElpisCore.MSG_PREFIX+node.getName() + ChatColor.RED + " is already max level!");
                            user.playSound(user.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 0.6f);
                        }
                    } else
                    {
                        user.sendMessage(ElpisCore.MSG_PREFIX + ChatColor.RED + "You cannot afford to buy "+node.getName());
                        user.playSound(user.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 0.6f);
                    }
                }
                else
                {
                    user.sendMessage(ElpisCore.MSG_PREFIX + ChatColor.RED + "You are not a high enough level to buy "+node.getName()+ChatColor.GRAY+".");
                    user.playSound(user.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 0.6f);
                }
            }
            if (event.getSlot() == 53){
                int toReturn = calcSpent(user);

                PlayerClasses.setClassSkills(user, PlayerClasses.classSkillsDefault);
                PlayerClasses.setSkillPoints(user,PlayerClasses.getSkillPoints(user) + toReturn);
                user.playSound(user.getLocation(), Sound.BLOCK_ANVIL_USE, 0.7f, 1.0f);


               refreshUI(user);
            }
        }

        event.setCancelled(true);

    }
    public static void refreshUI(Player user){
        nodeClass[] usedNodes = getClassNodes(user);
        for (nodeClass n : usedNodes){
            inv.setItem(n.getSlot(), skillTreeNode(n, PlayerClasses.deParseSkills(PlayerClasses.getClassSkills(user)).get(n.getTier()).get(n.position)));
        }
        inv.setItem(49, createAestheticItem(Material.SUNFLOWER, ChatColor.translateAlternateColorCodes('&', "&e&lSkill Points: "+PlayerClasses.getSkillPoints(user)), user));

    }

    public static ArrayList<String> getAllSkills(Player user)
    {
        nodeClass[] usedNodes = getClassNodes(user);
        ArrayList<String> str = new ArrayList<>();
        HashMap<Integer, HashMap<Integer, Integer>> stats = PlayerClasses.deParseSkills(PlayerClasses.getClassSkills(user));
        for (nodeClass n : usedNodes){
            if (stats.get(n.getTier()).get(n.position) > 0) str.add(n.id);
        }

        return str;
    }

    private static nodeClass[] getClassNodes(Player user){
        nodeClass[] usedNodes;
        switch (PlayerClasses.getCurrentPlayerClass(user)){
            case ("healer"):
                usedNodes = healerNodes;
                break;
            case ("tank"):
                usedNodes = tankNodes;
                break;
            default:
                usedNodes = damageNodes;

        }
        return usedNodes;
    }
}
