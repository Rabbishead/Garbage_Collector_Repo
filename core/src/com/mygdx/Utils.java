package com.mygdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.entities.Player;
import com.mygdx.hitboxes.HitboxHandler;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.resources.ResourceManager;
import com.mygdx.screens.GenericScreen;

public class Utils {

    private static Game game;

    private static Stage stage;

    private static final ResourceManager manager = new ResourceManager();
    private static HitboxHandler hitboxHandler;
    private static Player player;
    private static Screen screen;

    /**
     * use this class in order to load a texture
     */
    public static Texture getTexture(ResourceEnum e) {
        return manager.getTexture(e);
    }

    public static HitboxHandler getHitboxHandler() {
        return hitboxHandler;
    }

    public static void setHitboxHandler(HitboxHandler newHitboxHandler) {
        hitboxHandler = newHitboxHandler;
    }

    public static void dispose() {
        manager.dispose();
    }

    public static Stage getStage() {
        return stage;
    }
    public static void setStage(Stage stage) {
        Utils.stage = stage;
    }
    public static Player getPlayer() {
        return player;
    }
    public static void setPlayer(Player player) {
        Utils.player = player;
    }
    public static void setScreen(Screen screen2){
        screen = screen2;
       game.setScreen(screen2); 
    }
    public static void setGame(Game game) {
        Utils.game = game;
    }
    public static Screen getScreen() {
        return screen;
    }
}
