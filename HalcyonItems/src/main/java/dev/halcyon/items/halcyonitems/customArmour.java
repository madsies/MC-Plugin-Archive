package dev.halcyon.items.halcyonitems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.time.Instant;
import java.util.*;

public class customArmour extends customItem{

    private final String name;
    private final String id;
    private final String itemType = "armour";
    private final String itemClass;
    private final String lore;
    private final String set;
    private final String armourType;

    private int rarity = 0;
    private int defense = 0;
    private int perkSlots = 1;
    private int levelRequirement;
    private int minimumItemLevel = 0;
    private int maximumItemLevel = 9999;

    private final Material mat;

    private ArrayList<itemPerk> perkPool;

    private final HashMap<String, Float> bonusStats;

    public customArmour(String name, String id, String itemClass, int rarity, Material mat, int lvlReq,
                         String set, int minimumItemLevel, int defense, int perkSlots,
                        String lore, HashMap<String, Float> bonusStats, String armourType ) {
        super();
        this.name = name;
        this.id = id;
        this.itemClass = itemClass;
        this.rarity = rarity;
        this.mat = mat;
        this.levelRequirement = lvlReq;
        this.set = set;
        this.defense = defense;
        this.armourType = armourType;
        this.bonusStats = bonusStats;
        this.minimumItemLevel = minimumItemLevel;
        this.maximumItemLevel = minimumItemLevel + (50 + 10*rarity);
        this.perkSlots = perkSlots;
        this.lore = lore;
    }

    public ItemStack createItemInstance(int iLevel, ArrayList<itemPerk> perks, Player user, ItemStack previous) {
        this.perkPool = perks;

        ItemStack item = new ItemStack(mat, 1);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return null;

        meta.getPersistentDataContainer().set(new NamespacedKey(HalcyonItems.getPlugin(), "item_id"), PersistentDataType.STRING, id);

        String prev = "";
        long ts = 0;

        if (previous == null){
            prev =  user.getUniqueId().toString();
            ts = Instant.now().getEpochSecond();
        }
        else{
            if (previous.getItemMeta() != null){
                prev = previous.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(HalcyonItems.getPlugin(), "original_owner"), PersistentDataType.STRING);
                ts = previous.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(HalcyonItems.getPlugin(), "drop_timestamp"), PersistentDataType.LONG);
            }
        }

        meta.getPersistentDataContainer().set(new NamespacedKey(HalcyonItems.getPlugin(), "original_owner"), PersistentDataType.STRING, prev);
        meta.getPersistentDataContainer().set(new NamespacedKey(HalcyonItems.getPlugin(), "drop_timestamp"), PersistentDataType.LONG, ts);

        List<String> finalisedLore = new ArrayList<>();

        // we do a little trimming, this should only apply when adding perks through commands.

        while (perkPool.size() > perkSlots) {
            perkPool.remove(perkPool.size()- 1);
        }

        if (iLevel < minimumItemLevel) iLevel = minimumItemLevel;
        if (iLevel > maximumItemLevel) iLevel = maximumItemLevel;
        int levelDifferential = iLevel - minimumItemLevel;
        float differentialMultiplier = 1f+(levelDifferential/200.0f);

        float finalDefense = roundOneDP(defense * differentialMultiplier);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', customItems.rarityColours[rarity]+name+" &9[&7"+iLevel+"&9]"));

        // Primary Stats

        meta.getPersistentDataContainer().set(new NamespacedKey(HalcyonItems.getPlugin(), "item_level"), PersistentDataType.INTEGER, iLevel);
        meta.getPersistentDataContainer().set(new NamespacedKey(HalcyonItems.getPlugin(), "defense"), PersistentDataType.FLOAT, finalDefense);
        meta.getPersistentDataContainer().set(new NamespacedKey(HalcyonItems.getPlugin(), "item_type"), PersistentDataType.STRING, "armour");
        // meta.getPersistentDataContainer().set(new NamespacedKey(HalcyonItems.getPlugin(), "elemental_damage"), PersistentDataType.FLOAT, finalElemental);


        finalisedLore.add(ChatColor.translateAlternateColorCodes('&', "&7Defense: &c+"+finalDefense));

        // Secondary Stats

        float bonusStatValue;

        ArrayList<String> stats = new ArrayList<String>(bonusStats.keySet());
        ArrayList<String> sorted = new ArrayList<>();

        for (String comp : HalcyonItems.SECONDARY_STATS){
            for (String stat : stats){
                if (stat.equalsIgnoreCase(comp)) sorted.add(comp);
            }
        }

        for (String stat : sorted) {
            bonusStatValue = roundOneDP(bonusStats.get(stat).floatValue()*differentialMultiplier);
            meta.getPersistentDataContainer().set(new NamespacedKey(HalcyonItems.getPlugin(), stat), PersistentDataType.FLOAT, bonusStatValue);
            String suffix = "";
            String prefix = "";
            if (HalcyonItems.SECONDARY_STATS_PERCENT.get(stat)) suffix = "%";
            if (bonusStatValue >= 0) prefix = "+";
            finalisedLore.add(ChatColor.translateAlternateColorCodes('&',"&7"+customItems.fixString(stat) +": &c"+prefix+bonusStatValue+suffix));
        }

        finalisedLore.add("");

        // Gems or Perks or whatever u wanna fecking call it.

        if (perkSlots > 0) finalisedLore.add(ChatColor.BLUE+"Perks"+ChatColor.DARK_GRAY+": ("+perkPool.size()+"/"+perkSlots+")");
        int usedSlots = 0;
        String toSend = "";

        for (itemPerk perk : perkPool){
            usedSlots += 1;
            toSend += perk.getPerkID()+"/";
            finalisedLore.add(ChatColor.translateAlternateColorCodes('&', "&7["+customItems.rarityColours[perk.getPerkRarity()]+"♦&7] "+customItems.rarityColours[perk.getPerkRarity()]+perk.getPerkName()));
        }
        meta.getPersistentDataContainer().set(new NamespacedKey(HalcyonItems.getPlugin(), "item_perks"), PersistentDataType.STRING, toSend);

        if ((perkSlots - usedSlots) > 0){
            for (int i = (perkSlots - usedSlots); i > 0; i--){
                finalisedLore.add(ChatColor.translateAlternateColorCodes('&', "&7[♦] &8Empty Slot"));
            }
        }


        if (HalcyonItems.armourSets.containsKey(set)){
            finalisedLore.add("");
            setBonus sb = HalcyonItems.armourSets.get(set);
            finalisedLore.add(ChatColor.translateAlternateColorCodes('&', "&e&lSet Bonus: &9"+sb.getName()));
            finalisedLore.add(ChatColor.translateAlternateColorCodes('&', "&f"+sb.getBonusDescription()));
            for (int i : sb.getTiers().keySet()){
                finalisedLore.add(ChatColor.translateAlternateColorCodes('&', "&7"+i+" Piece Bonus: "+sb.getTiers().get(i)));
            }

        }

        finalisedLore.add("");



        // Lore

        if (lore.length() > 0) {
            finalisedLore.add(ChatColor.translateAlternateColorCodes('&', "&8&o" + lore));
            finalisedLore.add("");
        }

        // Bottom info line
        finalisedLore.add(ChatColor.DARK_GRAY+"Requires Level "+levelRequirement);

        finalisedLore.add(""+customItems.rarityColours[rarity]+customItems.fixString(customItems.rarities[rarity])+" "+customItems.classColours.get(itemClass)+customItems.fixString(itemClass)+" "+customItems.fixString(armourType));

        meta.setLore(finalisedLore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_POTION_EFFECTS);
        item.setItemMeta(meta);

        return item;
    }

    public HashMap<String, Float> getBonusStats(){
        return bonusStats;
    }

    public int getMaximumItemLevel(){
        return maximumItemLevel;
    }

    public int getMinimumItemLevel(){
        return minimumItemLevel;
    }

    public int getPerkSlots(){
        return perkSlots;
    }


    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getFormattedName() {
        return null;
    }

    @Override
    public String getRarity() {
        return null;
    }

    @Override
    public String getItemType() {
        return null;
    }

    public float roundOneDP (float starting){
        return (Math.round(starting*10.0f))/10.0f;
    }
}
