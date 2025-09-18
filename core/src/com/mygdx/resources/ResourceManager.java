package com.mygdx.resources;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.bladecoder.ink.runtime.Story;
import com.mygdx.Utils;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.EnumMap;
import java.util.Objects;
import java.util.stream.Stream;

public class ResourceManager {

    private final AssetManager manager;
    private final EnumMap<ResourceEnum, Story> dialogueMap;
    private final EnumMap<ResourceEnum, TiledMap> maps;

    public ResourceManager() {
        manager = new AssetManager();
        dialogueMap = new EnumMap<>(ResourceEnum.class);
        maps = new EnumMap<>(ResourceEnum.class);
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
                case DIALOGUE -> {
                    loadDialogue(e);
                }
                case MAP -> {
                    maps.put(e, new TmxMapLoader().load(e.label));
                }
                default -> {
                }
            }
        });
    }

    public void update(){
        manager.update();
    }

    public Texture getTexture(ResourceEnum e) {
        manager.finishLoadingAsset(e.label);
        return manager.get(e.label);
    }

    public Music getAudio(ResourceEnum e) {
        manager.finishLoadingAsset(e.label);
        return manager.get(e.label);
    }

    public TiledMap getMap(ResourceEnum e){
        return maps.get(e);
    }

    public Story getDialogueStory(ResourceEnum e) {
        return dialogueMap.get(e);
    }

    public Stream<Music> getAllAudio() {
        return Stream.of(ResourceEnum.values())
                .filter(e -> e.type.equals(TypeEnum.AUDIO))
                .map(this::getAudio);
    }

    public void dispose() {
        manager.dispose();
    }

    //helper for dialogue loading
    public void loadDialogue(ResourceEnum dialogue) {

        InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream("dialogues/entities/"+ Utils.getActiveLanguage() + "/" + dialogue.label);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(systemResourceAsStream), StandardCharsets.UTF_8))) {
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            String json = sb.toString().replace('\uFEFF', ' ');
            dialogueMap.put(dialogue, new Story(json));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateLang(){
        dialogueMap.clear();
        Stream.of(ResourceEnum.values())
            .filter(e -> e.type.equals(TypeEnum.DIALOGUE))
            .forEach(this::loadDialogue);
    }
}
