package com.mygdx.screens;

import java.util.HashMap;
import com.mygdx.screens.game.arenas.SandstoneArena;
import com.mygdx.screens.game.overworld.CityRoom1;
import com.mygdx.screens.game.overworld.CityRoom2;
import com.mygdx.screens.game.overworld.CityRoom3;
import com.mygdx.screens.generic.GenericScreen;
import com.mygdx.screens.generic.playable.PlayableScreen;
import com.mygdx.screens.menus.MenuScreen;
import com.mygdx.screens.menus.PauseScreen;
public class ScreensManager {

    private static final HashMap<String, GenericScreen> map = new HashMap<>();
    private static String lastPlayableActiveScreen;

    public static GenericScreen getScreen(String screenName){
        if(map.get(screenName) == null){
            switch (screenName) {
                case "MENU_SCREEN" -> {
                    map.put("MENU_SCREEN", new MenuScreen());
                }
                case "PAUSE_SCREEN" -> {
                    map.put("PAUSE_SCREEN", new PauseScreen());
                }
                case "ARENA_ROOM_1" -> {
                    map.put("ARENA_ROOM_1", new SandstoneArena());
                }
                case "CITY_ROOM_1" -> {
                    map.put("CITY_ROOM_1", new CityRoom1());
                }
                case "CITY_ROOM_2" -> {
                    map.put("CITY_ROOM_2", new CityRoom2());
                }
                case "CITY_ROOM_3" -> {
                    map.put("CITY_ROOM_3", new CityRoom3());
                }
            }
        }
        if(map.get(screenName) instanceof PlayableScreen) lastPlayableActiveScreen = screenName;
        return map.get(screenName);
    }
    public static PlayableScreen getPlayableScreen(String screenName){
        return (PlayableScreen) getScreen(screenName);
    }

    public static boolean isNull(String screenName){
        return map.get(screenName) == null;

    }
    public static String getLastPlayableActiveScreen() {
        return lastPlayableActiveScreen;
    }
}
