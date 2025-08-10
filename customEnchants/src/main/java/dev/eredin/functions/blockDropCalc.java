package dev.eredin.functions;

import dev.eredin.enchantments.*;
import dev.eredin.saving.dataManager;
import dev.eredin.saving.pickaxeData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class blockDropCalc {

    public static void calc(Player player, ItemStack tool, Block block) {

        pickaxeData data = dataManager.getData(player.getUniqueId());
        data.addBlock(1);
        data.updatePickLore(player);
        Collection<ItemStack> temp = block.getDrops(tool);
        if (!temp.isEmpty()) {
            ArrayList<ItemStack> drops = (ArrayList<ItemStack>) block.getDrops(tool);
            addBlocks(player, tool, drops);
            if (block.hasMetadata("isMineBlock")) {
                lootFinder.activate(player, tool);
                jackpot.runCommand(tool, player, drops);
                treasure.activate(drops, player, tool);
                generous.calc(tool, player);
                boolean luckyProc = luckyDrops.shardLucky(player);
                if (!luckyProc) shardDrop.shardCalc(player);
            }

        }
    }

    public static void addBlocks(Player player, ItemStack tool, ArrayList<ItemStack> drops){
        for (ItemStack drop : drops) {
            smelt(drop);
            if (!autoSell.check(tool)) {
                player.getInventory().addItem(drop);
            }else{

                int val = 0;
                if (dataManager.blockValues.containsKey(drop.getType().name())) val = dataManager.blockValues.get(drop.getType().name());

                Long money = (long) (val * stonk.check(tool) * drop.getAmount());

                dataManager.addMoney(player.getUniqueId(), money);
            }

        }
    }

    public static void smelt(ItemStack drop){
        HashMap<Material, Material> smeltList = new HashMap<Material, Material>(){{
            put(Material.IRON_ORE, Material.IRON_INGOT);
            put(Material.GOLD_ORE, Material.GOLD_INGOT);
        }};

        if (smeltList.containsKey(drop.getType())){
            drop.setType(smeltList.get(drop.getType()));
        }

    }
}
