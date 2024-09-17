package com.mygdx;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.hitboxes.HitboxHandler;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.resources.ResourceManager;

public class Utils {

    private static final ResourceManager manager = new ResourceManager();
    private static HitboxHandler hitboxHandler;

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
}
