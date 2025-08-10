package dev.halcyon.items.halcyonitems.itemPerks;

import dev.halcyon.items.halcyonitems.itemPerk;

public class testPerk extends itemPerk {
    @Override
    public String getPerkDescription() {
        return "Perk Desc";
    }

    @Override
    public String getPerkName() {
        return "Test Perk";
    }

    @Override
    public String getPerkID() {
        return "test_perk";
    }

    @Override
    public int getPerkRarity() {
        return 3;
    }
}
