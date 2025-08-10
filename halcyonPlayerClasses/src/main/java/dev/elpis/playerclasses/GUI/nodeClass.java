package dev.elpis.playerclasses.GUI;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;

public abstract class nodeClass {
    public String name = "default";
    final public Material mat;
    public String id;
    private int tier = 1;
    public int maxLevel = 5;
    public int position;
    public char[] codes = {'f', 'a','9', '5', '4'};
    public String[] conflicts = {};
    public String[] prerequisites = {};
    public String[] lore = {"placeholder lore"};

    public nodeClass (String name, String classNode, String id, int position, Material mat, int tier, int maxLevel){
        this.name = ChatColor.translateAlternateColorCodes('&', "&"+codes[tier-1]+"&l"+name);
        this.mat = mat;
        this.id = id;
        this.maxLevel = maxLevel;
        this.tier = tier;
        this.position = position;
    }

    public String[] dynamicDescription(int level){
        return new String[]{"."};
    }

    public Material getMat(){
        return mat;
    }

    public String getName(){
        return name;
    }

    public int getTier(){
        return tier;
    }

    public int getSlot(){
        return (tier-1)*9 + position;
    }

    public boolean hasConflicts(){
        return (conflicts.length > 0);
    }

    public boolean hasPrerequisites(ArrayList<String> ownedSkills){
        if (!needsNodes()) return true;
        for (String req : prerequisites){
            if (ownedSkills.contains(req)){
                return true;
            }
        }
        return false;
    }

    public boolean hasConflicted(ArrayList<String> ownedSkills){
        if (!hasConflicts()) return false;
        for (String req : conflicts){
            if (ownedSkills.contains(req)){
                return true;
            }
        }
        return false;
    }

    public boolean needsNodes(){
        return (prerequisites.length > 0);
    }

    public String formattedPreReq(){
        String msg = "";
        for (String preReq : prerequisites){
            msg += preReq +", ";
        }
        return msg.substring(0, msg.length()-2);
    }

    public String formattedConflicts(){
        String msg = "";
        for (String preReq : conflicts){
            msg += preReq +", ";
        }
        return msg.substring(0, msg.length()-2);
    }


}
