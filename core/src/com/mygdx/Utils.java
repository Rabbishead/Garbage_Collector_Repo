package com.mygdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.entities.Player;
import com.mygdx.hitboxes.HitboxHandler;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.resources.ResourceManager;

public class Utils {

    private static Game game;

    private static Stage stage;

    private static final ResourceManager manager = new ResourceManager();
    private static HitboxHandler hitboxHandler;
    private static Player player;

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

    public static Game getGame() {
        return game;
    }
    public static void setGame(Game game) {
        Utils.game = game;
    }
    public static Player getPlayer() {
        return player;
    }
    public static void setPlayer(Player player) {
        Utils.player = player;
    }
}
