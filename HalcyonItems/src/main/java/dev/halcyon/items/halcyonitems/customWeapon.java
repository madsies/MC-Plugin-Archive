package dev.halcyon.items.halcyonitems;

import dev.halcyon.core.HalcyonCore;
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

public class customWeapon extends customItem {

    private final String name;
    private final String id;
    private int rarity = 0;
    private Material mat = Material.WOODEN_SWORD;
    private final ArrayList<String> elementPool;
    private ArrayList<itemPerk> perkPool;
    private final String itemType = "weapon";
    private String itemClass = "all";
    private final String lore;
    private final HashMap<String, Float> bonusStats;

    private boolean moddable;
    private int baseDamage= 1;
    private String element = "normal";
    private int elementalDamage = 0;
    private int minimumItemLevel = 0;
    private int maximumItemLevel = 9999;

    private int perkSlots = 1;
    private String abilityID = "null";
    private int levelRequirement;

    public customWeapon(String name, String id, String itemClass, int rarity, Material mat, ArrayList<String> elementPool,
                        int lvlReq, HashMap<String, Float> bonusStats, String ability, int minimumItemLevel, int perkSlots,
                        int baseDamage, int elementalDamage, String lore) {
        super();
        this.name = name;
        this.id = id;
        this.itemClass = itemClass;
        this.rarity = rarity;
        this.mat = mat;
        this.elementPool = elementPool;
        this.levelRequirement = lvlReq;
        this.bonusStats = bonusStats;
        this.abilityID = ability;
        this.minimumItemLevel = minimumItemLevel;
        this.maximumItemLevel = minimumItemLevel + (50 + 10*rarity);
        this.perkSlots = perkSlots;
        this.baseDamage = baseDamage;
        this.elementalDamage = elementalDamage;
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
        String element = "normal";

        if (previous == null){
            prev =  user.getUniqueId().toString();
            ts = Instant.now().getEpochSecond();
            int rnd = new Random().nextInt(elementPool.size());
            element = elementPool.get(rnd);
        }
        else{
            if (previous.getItemMeta() != null){
                prev = previous.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(HalcyonItems.getPlugin(), "original_owner"), PersistentDataType.STRING);
                ts = previous.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(HalcyonItems.getPlugin(), "drop_timestamp"), PersistentDataType.LONG);
                element = previous.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(HalcyonItems.getPlugin(), "element"), PersistentDataType.STRING);
            }
        }
        meta.getPersistentDataContainer().set(new NamespacedKey(HalcyonItems.getPlugin(), "original_owner"), PersistentDataType.STRING, prev);
        meta.getPersistentDataContainer().set(new NamespacedKey(HalcyonItems.getPlugin(), "drop_timestamp"), PersistentDataType.LONG, ts);

        // Damage, Elemental Damage, Amount of Perks, ability.
        List<String> finalisedLore = new ArrayList<>();

        // we do a little trimming, this should only apply when adding perks through commands.

        while (perkPool.size() > perkSlots) {
            perkPool.remove(perkPool.size()- 1);
        }

        if (iLevel < minimumItemLevel) iLevel = minimumItemLevel;
        if (iLevel > maximumItemLevel) iLevel = maximumItemLevel;
        int levelDifferential = iLevel - minimumItemLevel;
        float differentialMultiplier = 1f+(levelDifferential/200.0f);

        float finalPhysical = roundOneDP(baseDamage * differentialMultiplier);
        float finalElemental = roundOneDP(elementalDamage * differentialMultiplier);
        if (Objects.equals(element, "normal")) finalElemental = 0;

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', customItems.rarityColours[rarity]+name+customItems.elementalSymbols.get(element)+" &9[&7"+iLevel+"&9]"));

        // Primary Stats

        meta.getPersistentDataContainer().set(new NamespacedKey(HalcyonItems.getPlugin(), "item_level"), PersistentDataType.INTEGER, iLevel);
        meta.getPersistentDataContainer().set(new NamespacedKey(HalcyonItems.getPlugin(), "element"), PersistentDataType.STRING, element);
        meta.getPersistentDataContainer().set(new NamespacedKey(HalcyonItems.getPlugin(), "physical_damage"), PersistentDataType.FLOAT, finalPhysical);
        meta.getPersistentDataContainer().set(new NamespacedKey(HalcyonItems.getPlugin(), "elemental_damage"), PersistentDataType.FLOAT, finalElemental);
        meta.getPersistentDataContainer().set(new NamespacedKey(HalcyonItems.getPlugin(), "item_type"), PersistentDataType.STRING, "weapon");


        finalisedLore.add(ChatColor.translateAlternateColorCodes('&', "&4Physical &7Damage: &c+"+finalPhysical));
        if (!Objects.equals(element, "normal")) {
            finalisedLore.add(ChatColor.translateAlternateColorCodes('&', customItems.elementalColours.get(element)+customItems.fixString(element)+" &7Damage: &c+" + finalElemental )); // &7
        }

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

        // Ability

        if (customItems.itemAbility.containsKey(abilityID)){
            finalisedLore.add("");
            itemAbility abil = customItems.itemAbility.get(abilityID);
            finalisedLore.add(ChatColor.translateAlternateColorCodes('&', "&e&lItem Ability: &9"+abil.getAbilityName()));
            finalisedLore.add(ChatColor.translateAlternateColorCodes('&', "&f"+abil.getAbilityDescription()));
            finalisedLore.add(ChatColor.translateAlternateColorCodes('&', "&r&8("+abil.getCooldown()+"s cooldown)"));
        }

        finalisedLore.add("");

        // Lore

        if (lore.length() > 0) {
            finalisedLore.add(ChatColor.translateAlternateColorCodes('&', "&8&o" + lore));
            finalisedLore.add("");
        }

        // Bottom info line
        finalisedLore.add(ChatColor.DARK_GRAY+"Requires Level "+levelRequirement);

        finalisedLore.add(""+customItems.rarityColours[rarity]+customItems.fixString(customItems.rarities[rarity])+" "+customItems.classColours.get(itemClass)+customItems.fixString(itemClass)+" Weapon");

        meta.setLore(finalisedLore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_POTION_EFFECTS);
        item.setItemMeta(meta);

        return item;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getFormattedName() {
        return customItems.rarityColours[rarity]+name;
    }

    @Override
    public String getRarity() {
        return customItems.rarities[rarity];
    }

    @Override
    public String getItemType() {
        return itemType;
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

    public ArrayList<String> getElementPool(){
        return elementPool;
    }

    public HashMap<String, Float> getBonusStats(){
        return bonusStats;
    }


    // Setters

    public boolean addPerk(itemPerk perk){
        if (this.perkPool.size() < perkSlots) {
            this.perkPool.add(perk);
            return true;
        }
        else{
            return false;
        }
    }

    public void removePerk(itemPerk perk){
        this.perkPool.remove(perk);
    }

    public float roundOneDP (float starting){
        return (Math.round(starting*10.0f))/10.0f;
    }
}
