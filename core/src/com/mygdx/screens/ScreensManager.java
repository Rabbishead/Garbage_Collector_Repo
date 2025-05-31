package com.mygdx.screens;

import com.mygdx.Utils;
import com.mygdx.screens.generic.GenericScreen;
import com.mygdx.screens.generic.playable.PlayableScreen;

public class ScreensManager {
    private static ScreensEnum lastPlayableActiveScreen;

    public static GenericScreen getScreen(ScreensEnum screen) {
        if (screen.get() instanceof PlayableScreen)
            lastPlayableActiveScreen = screen;

        return screen.get();
    }

    public static PlayableScreen getPlayableScreen(ScreensEnum screen) {
        return (PlayableScreen) screen.get();
    }

    public static ScreensEnum getLastPlayableActiveScreen() {
        return lastPlayableActiveScreen;
    }

    /**
     * sets active screen
     * 
     * @param se ScreensEnum
     */
    public static void setScreen(ScreensEnum se) {
        Utils.stopAllAudio();
        GenericScreen newScreen = ScreensManager.getScreen(se);
        game.setScreen(newScreen);
    }
}
