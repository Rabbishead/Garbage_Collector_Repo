package com.mygdx.delay;

import java.util.HashMap;

import java.util.function.Consumer;

/**
 * Class ussed to subsribe objects to a delay and update delays
 */
public class DelayManager {
    private static HashMap<Object, Integer> originalDelays = new HashMap<>();
    private static HashMap<Object, Integer> currentDelays = new HashMap<>();
    private static HashMap<Object, Consumer<?>> actions = new HashMap<>();

    public static void registerObject(Object o, Integer time) {
        originalDelays.put(o, time);
        currentDelays.put(o, time);
    }

    public static void registerObject(Object o, Integer time, Consumer<?> action) {
        registerObject(o, time);
        actions.put(o, action);
    }

    public static void unregisterObject(Object o) {
        originalDelays.remove(o);
        currentDelays.remove(o);
        if (actions.get(o) != null)
            actions.remove(o);
    }

    public static void resetDelay(Object o) {
        currentDelays.replace(o, originalDelays.get(o));
    }

    public static void updateDelay(Object o) {
        Integer i = currentDelays.get(o);
        if (i == null) return;

        if (i > 0) {
            currentDelays.replace(o, i - 2);
            return;
        }

        Consumer<?> c = actions.get(o);
        if (c != null) {
            c.accept(null);
            unregisterObject(o);
        }
    }

    public static Integer getCurrentDelay(Object o) {
        return currentDelays.get(o);
    }

    public static boolean isDelayOver(Object o) {
        return currentDelays.get(o) <= 0;
    }
}
