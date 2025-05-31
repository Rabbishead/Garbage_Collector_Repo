package com.mygdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.bladecoder.ink.runtime.Story;
import com.mygdx.controllers.hitboxes.HitboxHandler;
import com.mygdx.controllers.messages.MSG;
import com.mygdx.entities.GameActor;
import com.mygdx.entities.Player;
import com.mygdx.hud.Hud;
import com.mygdx.resources.LangEnum;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.resources.ResourceManager;
import com.mygdx.screens.ScreensEnum;
import com.mygdx.screens.ScreensManager;
import com.mygdx.screens.generic.GenericScreen;

public class Utils {

    private static Game game;

    private static Stage stage;

    private static ResourceManager manager;
    private static HitboxHandler hitboxHandler;
    private static Player player;
    
    private static Hud currentHud; // Current HUD

    private static LangEnum activeLanguage = LangEnum.ITA;

    public static LangEnum getActiveLanguage() {
        return activeLanguage;
    }
    public static void setActiveLanguage(LangEnum activeLanguage) {
        if(manager == null) manager = new ResourceManager();
        Utils.activeLanguage = activeLanguage;
        manager.updateLang();
    }

    /**
     * @Texture already loaded
     */
    public static Texture getTexture(ResourceEnum e) {
        return manager.getTexture(e);
    }

    public static Story getStory(ResourceEnum e){
        return manager.getDialogueStory(e);
    }

    public static TiledMap getMap(ResourceEnum e){
        return manager.getMap(e);
    }

    /**
     * @return static hitboxHandler
     */
    public static HitboxHandler getHitboxHandler() {
        return hitboxHandler;
    }

    /**
     * @param newHitboxHandler
     */
    public static void setHitboxHandler(HitboxHandler newHitboxHandler) {
        hitboxHandler = newHitboxHandler;
        hitboxHandler.clearContacts();
    }

    /**
     * @return the current stage
     */
    public static Stage getStage() {
        return stage;
    }

    /**
     * TOBE called every time you change screen
     * 
     * @param stage new stage
     */
    public static void setStage(Stage stage) {
        Utils.stage = stage;
    }

    /**
     * @return current player object
     */
    public static Player getPlayer() {
        return player;
    }

    /**
     * sets current player object
     * 
     * @param player
     */
    public static void setPlayer(Player player) {
        Utils.player = player;
    }


    /**
     * sets game object
     * 
     * @param game
     */
    public static void setGame(Game game) {
        Utils.game = game;
    }

    /**
     * correctly disposes resources
     */
    public static void dispose() {
        manager.dispose();
    }

    public static Hud getCurrentHud() {
        return currentHud;
    }

    public static void setCurrentHud(Hud currentHud) {
        Utils.currentHud = currentHud;
    }

    public static void playAudio(ResourceEnum e) {
        if (!manager.getAudio(e).isPlaying()) {
            manager.getAudio(e).play();
            manager.getAudio(e).setLooping(true);
        }
    }

    public static void stopAudio(ResourceEnum e) {
        if (manager.getAudio(e).isPlaying())
            manager.getAudio(e).stop();
    }

    public static void stopAllAudio() {
        manager.getAllAudio()
            .filter(Music::isPlaying)
            .forEach(Music::stop);
    }

    public static void setDebugString(String debugString) {
        currentHud.setDebugSting(debugString);
    }

    public static void subscribeToStageMsg(GameActor a, MSG msg){
        ScreensManager.getActiveScreen().subscribe(a, msg);
    }

    public static void toggleFullScreen() {
        Graphics.DisplayMode displayMode = Gdx.graphics.getDisplayMode();
        if (Gdx.graphics.getHeight() == displayMode.height)
            Gdx.graphics.setWindowedMode(Data.VIEWPORT_X, Data.VIEWPORT_Y);
        else
            Gdx.graphics.setFullscreenMode(displayMode);

    }
}
