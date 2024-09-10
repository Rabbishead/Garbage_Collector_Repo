package com.mygdx.delay;

import java.util.HashMap;

public class DelayManager {
    private static HashMap<Object, Integer> originalDelays;
    private static HashMap<Object, Integer> currentDelays;

    public static void registerObject(Object o, Integer time){
        originalDelays.put(o, time);
        currentDelays.put(o, time);
    }
    public static void unregisterObject(Object o){
        originalDelays.remove(o);
        currentDelays.remove(o);
    }
    public static void resetDelay(Object o){
        currentDelays.replace(o, originalDelays.get(o));
    }
    public static void updateDelay(Object o){
        if(currentDelays.get(o) > 0) {
            currentDelays.replace(o, currentDelays.get(o) - 1);
        }
    }
    public static Integer getCurrentDelay(Object o){
        return currentDelays.get(o);
    }
}
