package com.mygdx.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TileSetManager {
    private final TiledMapRenderer tiledMapRenderer;
    private final TiledMap map;

    public TileSetManager(String path){
        map = new TmxMapLoader().load(path);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
        AnimatedTileMapManager.load(map);
    }

    public void renderForeground(OrthographicCamera camera){
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render(new int[]{1});
    }

    public void renderBackground(OrthographicCamera camera){
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render(new int[]{0});
    }

    public TiledMap getMap() {
        return map;
    }
}
