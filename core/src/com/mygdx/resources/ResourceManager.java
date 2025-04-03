package com.mygdx.resources;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

import java.util.EnumMap;

public class ResourceManager {

    private final AssetManager manager;
    private final EnumMap<ResourceEnum, String> pathMap;

    public ResourceManager() {
        manager = new AssetManager();
        pathMap = new EnumMap<>(ResourceEnum.class);
        loadPathMap();
        loadTextures();
    }

    private void loadTextures() {
        for (ResourceEnum key : pathMap.keySet()) {
            manager.load(pathMap.get(key), Texture.class);
        }
    }

    private void loadPathMap() {
        pathMap.put(ResourceEnum.PLAYER, "player/playerSpritesheet.png");
        pathMap.put(ResourceEnum.STONE, "bullets/stone.png");
        pathMap.put(ResourceEnum.BULLET, "bullets/bullet.png");
        pathMap.put(ResourceEnum.DEFAULT, "weapons/default.png");
        pathMap.put(ResourceEnum.SNIPER, "weapons/sniper.png");
        pathMap.put(ResourceEnum.PLAYBUTTON, "menu/play.png");
        pathMap.put(ResourceEnum.ENGFLAG, "dialogues/images/engFlag.jpg");
        pathMap.put(ResourceEnum.ITAFLAG, "dialogues/images/itaFlag.png");
        pathMap.put(ResourceEnum.HEALTH_BAR, "hud/health.png");
        pathMap.put(ResourceEnum.LAMP, "map/city/lamp.png");
        pathMap.put(ResourceEnum.LONG_LAMP, "map/city/long_lamp.png");
        pathMap.put(ResourceEnum.PALACE, "map/city/palace.png");
        pathMap.put(ResourceEnum.COMPLEX_DIALOGUE, "dialogues/images/bossDialogueBox.png");
        pathMap.put(ResourceEnum.SIMPLE_DIALOGUE, "dialogues/images/dialogueBox.jpg");
        pathMap.put(ResourceEnum.CHOICE, "dialogues/images/choice.png");
        pathMap.put(ResourceEnum.JERKINS, "npcs/jenkins_spritesheet.png");
        pathMap.put(ResourceEnum.BLACKMARKETEER, "npcs/blackmarketeer_spritesheet.png");
    }

    public Texture getTexture(ResourceEnum e) {
        manager.finishLoadingAsset(pathMap.get(e));
        return manager.get(pathMap.get(e));
    }

    public void dispose() {
        manager.dispose();
    }
}
