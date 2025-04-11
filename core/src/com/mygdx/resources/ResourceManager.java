package com.mygdx.resources;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import java.util.stream.Stream;

public class ResourceManager {

    private final AssetManager manager;

    public ResourceManager() {
        manager = new AssetManager();
        loadManager();
    }

    private void loadManager() {
        Stream.of(ResourceEnum.values()).forEach(e -> {
            switch (e.type) {
                case TEXTURE -> {
                    manager.load(e.label, Texture.class);
                }
                case AUDIO -> {
                    manager.load(e.label, Music.class);
                }
                default -> {}
            }
        });
    }

    public Texture getTexture(ResourceEnum e) {
        manager.finishLoadingAsset(e.label);
        return manager.get(e.label);
    }

    public Music getAudio(ResourceEnum e) {
        manager.finishLoadingAsset(e.label);
        return manager.get(e.label);
    }

    public Stream<Music> getAllAudio() {
        return Stream.of(ResourceEnum.values())
            .filter(e -> e.type.equals(TypeEnum.AUDIO))
            .map(this::getAudio);
    }

    public void dispose() {
        manager.dispose();
    }
}
