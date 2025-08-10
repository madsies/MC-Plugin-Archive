package dev.elpis.playerclasses;

import dev.elpis.playerclasses.GUI.damage.Glow;
import dev.elpis.playerclasses.commands.*;
import dev.elpis.playerclasses.events.ElpisPlayerLevelUpEvent;
import dev.elpis.playerclasses.events.ElpisPlayerSwitchClassEvent;
import dev.elpis.playerclasses.events.inventoryMove;
import dev.elpis.playerclasses.events.playerJoin;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import dev.elpis.core.ElpisCore;

import java.lang.reflect.Field;
import java.util.HashMap;

public  class PlayerClasses extends JavaPlugin {

    public static final int maxLevel = 100;
    public static final String[] classes = {"damage", "healer", "tank"};
    public static final String classSkillsDefault = "0/0/0/0/0/0/0#0/0/0/0/0/0/0#0/0/0/0/0/0/0#0/0/0/0/0/0/0#0/0/0/0/0/0/0";

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new playerJoin(), this);
        getServer().getPluginManager().registerEvents(new inventoryMove(), this);

        this.getCommand("classlevel").setExecutor(new classLevel());
        this.getCommand("classlevel").setTabCompleter(new onTabComplete());

        this.getCommand("classexperience").setExecutor(new classExperience());
        this.getCommand("classexperience").setTabCompleter(new onTabComplete());

        this.getCommand("setclass").setExecutor(new setClass());
        this.getCommand("setclass").setTabCompleter(new onTabComplete());

        this.getCommand("classinfo").setExecutor(new classInfo());
        this.getCommand("skilltree").setExecutor(new skillTree());
        this.getCommand("selectclass").setExecutor(new selectClass());


        System.out.println("Player Classes Loaded");

        registerGlow();

    }

    @Override
    public void onDisable()
    {
        // Plugin shutdown logic

        System.out.println("Player Classes unloaded");
    }

    // Important setters and getters
    public static int getSpecificClassLevel(Player player, String playerClass)
    {
        return ElpisCore.utils.getPlayerData(player).getInteger("level_"+playerClass, 1);

    }

    public static int getSpecificClassExperience(Player player, String playerClass)
    {
        return ElpisCore.utils.getPlayerData(player).getInteger("experience_"+playerClass, 100);
    }


    public static String getCurrentPlayerClass(Player player)
    {
        return ElpisCore.utils.getPlayerData(player).getString("class", "damage");
    }

    public static void setCurrentPlayerClass(Player player, String playerClass){
        String oldClass = getCurrentPlayerClass(player);
        ElpisCore.utils.getPlayerData(player).setString("class", playerClass);

        ElpisPlayerSwitchClassEvent swEvent = new ElpisPlayerSwitchClassEvent(oldClass, playerClass, player);
        Bukkit.getPluginManager().callEvent(swEvent);

        fixExpBar(player);
    }

    public static int getClassLevel(Player player)
    {
        String playerClass = getCurrentPlayerClass(player);
        int level = ElpisCore.utils.getPlayerData(player).getInteger("level_"+playerClass, 1);

        fixExpBar(player);
        return level;
    }

    public static void setClassLevel(Player player, int classLevel){
        String playerClass = getCurrentPlayerClass(player);
        int required = fetchExpFromLevel(classLevel);
        setClassExperience(player, required+1);
    }

    public static void addClassLevel(Player player){
        String playerClass = getCurrentPlayerClass(player);
        ElpisCore.utils.getPlayerData(player).setInteger("level_"+playerClass, getClassLevel(player)+1);
    }

    public static void addClassExperience(Player player, int experience){
        String playerClass = getCurrentPlayerClass(player);


        int newTotal = getClassExperience(player) + experience;
        ElpisCore.utils.getPlayerData(player).setInteger("experience_"+playerClass, newTotal);

        while (canLevelUp(player)) levelUp(player);
        fixExpBar(player);
    }


    public static int getClassExperience(Player player)
    {
        String playerClass = getCurrentPlayerClass(player);
        return ElpisCore.utils.getPlayerData(player).getInteger("experience_"+playerClass, 100);
    }

    public static void setClassExperience(Player player, int classExperience)
    {
        String playerClass = getCurrentPlayerClass(player);
        ElpisCore.utils.getPlayerData(player).setInteger("experience_"+playerClass, classExperience);
        while (canLevelUp(player)) levelUp(player);
        fixExpBar(player);

    }

    public static void setSkillPoints(Player player, int points)
    {
        String playerClass = getCurrentPlayerClass(player);
        ElpisCore.utils.getPlayerData(player).setInteger("skillpoints_"+playerClass, points);
    }

    public static int getSkillPoints(Player player){
        String playerClass = getCurrentPlayerClass(player);
        return ElpisCore.utils.getPlayerData(player).getInteger("skillpoints_"+playerClass, 1);
    }


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


    public static void setClassSkills(Player player, String parsedStats){
        String playerClass = getCurrentPlayerClass(player);
        ElpisCore.utils.getPlayerData(player).setString("skills_"+playerClass, parsedStats);
    }

    public static String getClassSkills(Player player){
        String playerClass = getCurrentPlayerClass(player);
        return ElpisCore.utils.getPlayerData(player).getString("skills_"+playerClass, classSkillsDefault);
    }

    /*

        Private methods below

     */

    public static HashMap<Integer, HashMap<Integer, Integer>> deParseSkills(String skills){
        String[] tiered = skills.split("#");

        HashMap<Integer, HashMap<Integer, Integer>> playerSkills = new HashMap<>();

        int counter = 1;
        for (String skill : tiered){
            int skillnum = 1;
            HashMap<Integer, Integer> temp = new HashMap<>();
            for(String s : skill.split("/")){
                temp.put(skillnum, Integer.valueOf(s));
                skillnum += 1;
            }
            playerSkills.put(counter, temp);
            counter += 1;
        }

        return playerSkills;
    }

    public static String parseSkills(HashMap<Integer, HashMap<Integer, Integer>> skills){
        StringBuilder returned = new StringBuilder();
        for (int counter = 1; counter <= skills.size(); counter++){
            for (Integer num : skills.get(counter).values()){
                returned.append(num).append("/");
            }
            returned = new StringBuilder(returned.substring(0, returned.length() - 1));
            returned.append("#");
        }
        return returned.toString();
    }

    private static void fixExpBar(Player player){
        int exp = getClassExperience(player);
        int level = fetchLevelFromExp(exp);
        int expForPre = fetchExpFromLevel(level);
        int expForPost = fetchExpFromLevel(level+1);

        float percentage = (exp - expForPre) / (float) (expForPost - expForPre);

        if (player != null) {
            player.setLevel(level);
            player.setExp(percentage);
        }

    }

    private static boolean isLevelCap(Player player){
        return getClassLevel(player) >= maxLevel;
    }

    private static boolean canLevelUp(Player player){
        int exp = getClassExperience(player);
        int playerLevel = getClassLevel(player);
        double oneThird = 1.0/3.0;

        int possibleLevel = (int) Math.pow((exp/100.0), oneThird);  // inverse of the function  (exp/100)^1/3

        return possibleLevel > playerLevel;
    }

    private static void levelUp(Player player){
        String playerClass = getCurrentPlayerClass(player);
        int old = getClassLevel(player);
        if (player != null) player.sendMessage(ElpisCore.MSG_PREFIX+"You levelled up "+getCurrentPlayerClass(player)+" to Level "+(old+1)+"!");
        addClassLevel(player);

        fixExpBar(player);
        ElpisPlayerLevelUpEvent lvlEvent = new ElpisPlayerLevelUpEvent(player, old, old+1);
        Bukkit.getPluginManager().callEvent(lvlEvent);

        setSkillPoints(player, getSkillPoints(player) + 1);
    }

    public static int fetchLevelFromExp(int exp){
        double oneThird = 1.0/3.0;
        return (int) Math.pow((exp/100.0), oneThird);
    }

    public static int fetchExpFromLevel(int level){
        return (int) (100 * Math.pow(level, 3));
    }

    public static void registerGlow(){
        boolean registered = true;
        try{
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(new Glow(NamespacedKey.minecraft("glow")));

        } catch (Exception e){
            registered = false;
        }
        if (registered){
            System.out.println("Enchantment registered");
        }
    }

}

