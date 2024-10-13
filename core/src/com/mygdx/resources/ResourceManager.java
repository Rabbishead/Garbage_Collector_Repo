package com.mygdx.resources;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

import java.util.EnumMap;

public class ResourceManager {

    private final AssetManager manager;
    private final EnumMap<ResourceEnum, String> pathMap;

    public ResourceManager() {
        manager = new AssetManager();
        pathMap = new EnumMap<ResourceEnum, String>(ResourceEnum.class);
        loadPathMap();
        loadTextures();
    }

    private void loadTextures() {
        for (ResourceEnum key : pathMap.keySet()) {
            manager.load(pathMap.get(key), Texture.class);
        }
    }

    private void loadPathMap() {
        pathMap.put(ResourceEnum.PLAYER, "playerSpritesheet.png");
        pathMap.put(ResourceEnum.STONE, "bullets/stone.png");
        pathMap.put(ResourceEnum.BULLET, "bullets/bullet.png");
        pathMap.put(ResourceEnum.DEFAULT, "weapons/default.png");
        pathMap.put(ResourceEnum.TESTACTOR, "testActor.png");
        pathMap.put(ResourceEnum.PLAYBUTTON, "menu/play.png");
        pathMap.put(ResourceEnum.ENGFLAG, "dialogues/images/engFlag.jpg");
        pathMap.put(ResourceEnum.ITAFLAG, "dialogues/images/itaFlag.png");
    }

    public Texture getTexture(ResourceEnum e) {
        manager.finishLoadingAsset(pathMap.get(e));
        return manager.get(pathMap.get(e));
    }

    public void dispose() {
        manager.dispose();
    }
}
