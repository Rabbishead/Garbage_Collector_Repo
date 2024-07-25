package com.mygdx.map;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class TileMapCollisionDetector {
    public static TiledMapTileLayer layer;

    public static boolean canMove(float incomingX, float incomingY){
        return layer.getCell((int) (incomingX+8) / 32, (int) (incomingY+8) / 32).getTile().getProperties().get("blocked") == null;
    }

}
