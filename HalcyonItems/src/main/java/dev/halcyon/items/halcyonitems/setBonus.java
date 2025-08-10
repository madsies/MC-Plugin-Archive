package dev.halcyon.items.halcyonitems;

import java.util.HashMap;

    public abstract class setBonus {
        public abstract String getBonusDescription();

        public abstract HashMap<Integer, String> getTiers();

        public abstract String getName();

        public abstract String getSetID();

        public abstract int getTierAmount();

}
