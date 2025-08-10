package dev.halcyon.items.halcyonitems.setBonuses;

import dev.halcyon.items.halcyonitems.setBonus;

import java.util.HashMap;

public class testSetBonus extends setBonus {

    HashMap<Integer, String> list = new HashMap<Integer,String>(){{put(2, "50% of that shit.");put(4, "100% of that shit.");}};

    @Override
    public String getBonusDescription() {
        return "This is a test Set Bonus";
    }

    @Override
    public HashMap<Integer, String> getTiers() {
        return list;
    }

    @Override
    public String getName() {
        return "Epic Bonus";
    }

    @Override
    public String getSetID() {
        return "test_set_bonus";
    }

    @Override
    public int getTierAmount() {
        return list.size();
    }
}
