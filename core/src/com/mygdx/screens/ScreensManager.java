package com.mygdx.screens;

import java.util.EnumMap;

import com.badlogic.gdx.Screen;
import com.mygdx.resources.ResourceEnum;
public class ScreensManager {

    public static enum ScreenEnum{
        MAIN_SCREEN,
        MENU_SCREEN,
        PAUSE_SCREEN
    }

    private static EnumMap<ScreenEnum, Screen> map = new EnumMap<>(ScreenEnum.class);
        

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
            }
        }
        return map.get(e);
    }
}
