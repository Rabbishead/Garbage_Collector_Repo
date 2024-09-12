package com.mygdx.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TileSetManager {
    private final TiledMapRenderer tiledMapRenderer;
    private final TiledMap map;

    public TileSetManager(){
        map = new TmxMapLoader().load("map/prova.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
    }

    public void render(OrthographicCamera camera){
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }

    public TiledMap getMap() {
        return map;
    }
}
