package com.mygdx.map;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class TileMapCollisionsManager {
    public static TiledMapTileLayer layer;

    public static boolean canMove(float incomingX, float incomingY){
        return layer.getCell((int) (incomingX+8) / 32, (int) (incomingY+8) / 32).getTile().getProperties().get("blocked") == null;
    }
    public static MapProperties getCurrentTileProprieties(float x, float y){
        return layer.getCell((int) (x) / 32, (int) (y) / 32).getTile().getProperties();
    }
}
