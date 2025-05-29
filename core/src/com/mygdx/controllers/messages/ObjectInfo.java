package com.mygdx.controllers.messages;

import java.util.HashMap;

public class ObjectInfo {
    public HashMap<String, Boolean> boolInfo;
    public HashMap<String, Integer> integerInfo;
    public HashMap<String, Float> floatInfo;
    public HashMap<String, String> stringInfo;

    /**
     * Create message info specifying maps to initialize.
     * 
     * @param selector array of 4 booleans.
     */
    public ObjectInfo(int... selector) {
        for (int i : selector) {
            switch (i) {
                case 1 -> {
                    boolInfo = new HashMap<>();
                    break;
                }
                case 2 -> {
                    integerInfo = new HashMap<>();
                    break;
                }
                case 3 -> {
                    floatInfo = new HashMap<>();
                    break;
                }
                case 4 -> {
                    stringInfo = new HashMap<>();
                    break;
                }
                default -> {
                    break;
                }
            }
        }
    }
}
