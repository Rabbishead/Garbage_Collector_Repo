package com.mygdx.screens;

import java.util.ArrayList;
import java.util.EnumMap;

import com.badlogic.gdx.Screen;
import com.mygdx.screens.game.arenas.SandstoneArena;
import com.mygdx.screens.game.overworld.MainScreen;
import com.mygdx.screens.game.overworld.SecondRoomTest;
import com.mygdx.screens.menus.MenuScreen;
import com.mygdx.screens.menus.PauseScreen;
public class ScreensManager {

    public static enum ScreenEnum{
        MAIN_SCREEN,
        MENU_SCREEN,
        PAUSE_SCREEN,
        SECOND_SCREEN,
        SANDSTONE_ARENA
    }

    private static EnumMap<ScreenEnum, GenericScreen> map = new EnumMap<>(ScreenEnum.class);
        

    public static Screen getScreen(ScreenEnum e){
        if(map.get(e) == null){
            switch (e) {
                case MAIN_SCREEN -> {
                    map.put(ScreenEnum.MAIN_SCREEN, new MainScreen());
                }
                case MENU_SCREEN -> {
                    map.put(ScreenEnum.MENU_SCREEN, new MenuScreen());
                }
                case PAUSE_SCREEN -> {
                    map.put(ScreenEnum.PAUSE_SCREEN, new PauseScreen());
                }
                case SECOND_SCREEN -> {
                    map.put(ScreenEnum.SECOND_SCREEN, new SecondRoomTest());
                }
                case SANDSTONE_ARENA -> {
                    map.put(ScreenEnum.SANDSTONE_ARENA, new SandstoneArena());
                }
            }
        }
        return map.get(e);
    }
}
