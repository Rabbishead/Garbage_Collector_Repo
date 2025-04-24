package com.mygdx.states;

import java.util.EnumMap;

public class StateManager {
    private static final EnumMap<StateEnum, Boolean> boolStates = new EnumMap<>(StateEnum.class);
    private static final EnumMap<StateEnum, String> stringStates = new EnumMap<>(StateEnum.class);

    public static void updateBoolState(StateEnum stateName, boolean value) {
        boolStates.put(stateName, value);
    }
    public static void updateStringState(StateEnum stateName, String value) {
        stringStates.put(stateName, value);
    }

    public static boolean getBoolState(StateEnum stateName) {
        return boolStates.get(stateName);
    }
    public static String getStringState(StateEnum stateName) {
        return stringStates.get(stateName);
    }

}
