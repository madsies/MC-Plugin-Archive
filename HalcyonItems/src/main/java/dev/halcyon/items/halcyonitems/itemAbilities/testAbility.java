package dev.halcyon.items.halcyonitems.itemAbilities;

import dev.halcyon.items.halcyonitems.itemAbility;

public class testAbility extends itemAbility {
    @Override

    public String getAbilityDescription() {
        return "Description for test ability";
    }

    @Override
    public String getAbilityName() {
        return "Test Ability";
    }

    @Override
    public String getAbilityID() {
        return "test_ability";
    }

    @Override
    public int getCooldown() {
        return 15;
    }
}
