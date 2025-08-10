package dev.eredin.saving;

import java.util.HashMap;
import java.util.UUID;

public class playerMineToggles {
    public UUID userUUID;
    public HashMap<String, Boolean> toggles = new HashMap<String, Boolean>(){{
        put("shardMessage", false);
        put("enchantMessage", true);
    }};
}
