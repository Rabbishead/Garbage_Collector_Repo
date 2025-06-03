package com.mygdx.screens;

import java.util.HashMap;
import com.mygdx.screens.game.arenas.ReflectionArena;
import com.mygdx.screens.game.overworld.Slums;
import com.mygdx.screens.game.overworld.Park;
import com.mygdx.screens.game.overworld.RichDistrict;
import com.mygdx.screens.generic.GenericScreen;
import com.mygdx.screens.generic.playable.PlayableScreen;
import com.mygdx.screens.menus.MenuScreen;
import com.mygdx.screens.menus.PauseScreen;
import com.mygdx.screens.menus.SettingsScreen;

public class ScreensManager {

    private static final HashMap<String, GenericScreen> map = new HashMap<>();
    private static String lastPlayableActiveScreen;

    public static GenericScreen getScreen(String screenName) {
        if (map.get(screenName) == null) {
            switch (screenName) {
                case "MENU_SCREEN" -> {
                    map.put("MENU_SCREEN", new MenuScreen());
                }
                case "PAUSE_SCREEN" -> {
                    map.put("PAUSE_SCREEN", new PauseScreen());
                }
                case "SETTINGS" -> {
                    map.put("SETTINGS", new SettingsScreen());
                }
                case "REFLECTION_ARENA" -> {
                    map.put("REFLECTION_ARENA", new ReflectionArena());
                }
                case "SLUMS" -> {
                    map.put("SLUMS", new Slums());
                }
                case "PARK" -> {
                    map.put("PARK", new Park());
                }
                case "RICH_DISTRICT" -> {
                    map.put("RICH_DISTRICT", new RichDistrict());
                }
            }
        }
        if (map.get(screenName) instanceof PlayableScreen)
            lastPlayableActiveScreen = screenName;
        return map.get(screenName);
    }

    public static PlayableScreen getPlayableScreen(String screenName) {
        return (PlayableScreen) getScreen(screenName);
    }

    public static boolean isNull(String screenName) {
        return map.get(screenName) == null;
    }

    public static String getLastPlayableActiveScreen() {
        return lastPlayableActiveScreen;
    }
}
