package dev.eredin.saving;


import dev.eredin.api.API;

import java.util.HashMap;
import java.util.UUID;


public class dataManager {
    public static HashMap<String, Integer> blockValues;
    public static API api;


    public static void addPlayer(UUID id){

        pickaxeData data = (pickaxeData) api.getPlayerData(id, "pickaxeData");
        if (data == null){
            data = new pickaxeData();
            data.pickLevel = 1;
            data.blocksMined = 0;
            data.shards = 0;
            data.userUUID = id;
            api.setPlayerData(id, "pickaxeData", data);

        }

        playerMineToggles toggles = (playerMineToggles) api.getPlayerData(id, "playerMineToggles");
        if (toggles == null){
            toggles = new playerMineToggles();
            api.setPlayerData(id, "playerMineToggles", toggles);
        }


        Long money = (Long) api.getPlayerData(id, "money");
        if (money == null){
            money = 0L;
            api.setPlayerData(id, "money", money);
        }

        }


    public static void addMoney(UUID id, Long amount){
        Long bal = (long) api.getPlayerData(id, "money");
        api.setPlayerData(id, "money", bal+amount);
    }

    public static int getMoney(UUID id){
        return (int) api.getPlayerData(id, "money");
    }

    public static pickaxeData getData(UUID id){
        return (pickaxeData) api.getPlayerData(id, "pickaxeData");
    }

    public static playerMineToggles getToggles(UUID id){
        return (playerMineToggles) api.getPlayerData(id, "playerMineToggles");
    }


}
