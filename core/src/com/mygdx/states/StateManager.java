package com.mygdx.states;

import java.util.HashMap;

public class StateManager {
    private static final HashMap<String, String> stateMap = new HashMap<>();


    public static void updateState(String stateName, String stateValue){
        stateMap.put(stateName, stateValue);
    }

    public static String getState(String stateName){
        return stateMap.get(stateName);
    }
}
