package com.mygdx.map;

import java.util.HashMap;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Array;

public class AnimatedTileMapManager {
    //map containing arrays of static tile map tiles: frames for the animatedTiles
    private static HashMap<String, Array<StaticTiledMapTile>> staticTiledMapTiles = new HashMap<>();

    //map containing all the animated tiles
    private static HashMap<String, AnimatedTiledMapTile> animatedTiledMapTiles = new HashMap<>();

    //map containing all animationRates in seconds for the animatedTiles
    private static HashMap<String, Float> animationRate = new HashMap<>();

    /**
     * loads all animated tiles
     * @param map
     */
    public static void load(TiledMap map){
        for (TiledMapTile tile : map.getTileSets().getTileSet(0)) {
            MapProperties tileProp = tile.getProperties();
            if(tileProp.containsKey("animation")){
                String value = tileProp.get("animation").toString();
                if(tile.getProperties().containsKey("animationRate")) animationRate.put(value, (Float) tileProp.get("animationRate"));
                staticTiledMapTiles.putIfAbsent(value, new Array<>());
                staticTiledMapTiles.get(value).add((StaticTiledMapTile) tile);
            }
        }
        for (String s : staticTiledMapTiles.keySet()) {
            animatedTiledMapTiles.put(s, new AnimatedTiledMapTile(animationRate.get(s), staticTiledMapTiles.get(s)));
        }

        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("foreground");

        for (int x = 0; x < layer.getWidth(); x++) {
            for (int y = 0; y < layer.getHeight(); y++) {
                Cell cell = layer.getCell(x, y);   
                if(cell == null) continue; 
                if(cell.getTile().getProperties().containsKey("animation")){
                    cell.setTile(animatedTiledMapTiles.get(cell.getTile().getProperties().get("animation")));
                }
            }
        }
    }
}
