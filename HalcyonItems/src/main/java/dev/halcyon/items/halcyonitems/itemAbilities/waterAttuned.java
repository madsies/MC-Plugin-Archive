package dev.halcyon.items.halcyonitems.itemAbilities;

import dev.halcyon.items.halcyonitems.itemAbility;

public class waterAttuned extends itemAbility {
    @Override

    public String getAbilityDescription() {
        return "Increases Water Elemental Damage by 100% for 20 seconds";
    }

    @Override
    public String getAbilityName() {
        return "Water Attuned";
    }

    @Override
    public String getAbilityID() {
        return "water_attuned";
    }

    @Override
    public int getCooldown() {
        return 30;
    }
}
