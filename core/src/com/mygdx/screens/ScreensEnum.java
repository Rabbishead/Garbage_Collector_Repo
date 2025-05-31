package com.mygdx.screens;

import com.mygdx.screens.game.arenas.ReflectionArena;
import com.mygdx.screens.game.overworld.Park;
import com.mygdx.screens.game.overworld.RichDistrict;
import com.mygdx.screens.game.overworld.Slums;
import com.mygdx.screens.generic.GenericScreen;
import com.mygdx.screens.menus.MenuScreen;
import com.mygdx.screens.menus.PauseScreen;
import com.mygdx.screens.menus.SettingsScreen;

public enum ScreensEnum {
    MENU_SCREEN(new MenuScreen()),
    PAUSE_SCREEN(new PauseScreen()),
    SETTINGS(new SettingsScreen()),
    REFLECTION_ARENA(new ReflectionArena()),
    SLUMS(new Slums()),
    PARK(new Park()),
    RICH_DISTRICT(new RichDistrict());

    private GenericScreen screen;

    private ScreensEnum(GenericScreen screen){
        this.screen = screen;
    }

    public GenericScreen get(){
        return screen;
    }
}
