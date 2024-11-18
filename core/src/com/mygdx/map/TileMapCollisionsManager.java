package com.mygdx.map;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.mygdx.Utils;
import com.mygdx.screens.ScreensManager;

/**
 * manages collisions inside a tilemap
 */
public class TileMapCollisionsManager {
    public static TiledMapTileLayer layer;
    private static boolean transitioning = false;

    /**
     * @param incomingX
     * @param incomingY
     * @return true if player can move in the incoming position
     */
    public static boolean canMove(float incomingX, float incomingY){
        TiledMapTile tile = layer.getCell((int) (incomingX + Utils.getPlayer().getWidth()/2) / 32, (int) (incomingY) / 32).getTile();
        return tile instanceof AnimatedTiledMapTile ? ((AnimatedTiledMapTile) tile).getCurrentFrame().getProperties().get("blocked") == null : tile.getProperties().get("blocked") == null;
    }
    /**
     * @param x
     * @param y
     * @return CurrentTileProprieties set
     */
    public static MapProperties getCurrentTileProprieties(){
        TiledMapTile tile = layer.getCell((int) (Utils.getPlayer().getX() + Utils.getPlayer().getWidth()/2) / 32, (int) (Utils.getPlayer().getY() + Utils.getPlayer().getHeight()/2) / 32).getTile();
        return tile instanceof AnimatedTiledMapTile ? ((AnimatedTiledMapTile) tile).getCurrentFrame().getProperties() : tile.getProperties();
    }

    /** 
     * if player is on the tile of a door, changes current screen based on the properties of the tile
    */
    public static void changeScreenIfNecessary(){
        MapProperties properties = getCurrentTileProprieties();
        if(properties.get("changeScreen") == null){
            transitioning = false;
            return;
        }
        if(transitioning == true) return;
        
        Utils.setScreen(ScreensManager.getScreen(properties.get("changeScreen").toString()));
        transitioning = true;
    }
}
