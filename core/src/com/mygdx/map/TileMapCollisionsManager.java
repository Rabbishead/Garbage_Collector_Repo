package com.mygdx.map;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.Utils;
import com.mygdx.screens.ScreensManager;
import com.mygdx.screens.ScreensManager.ScreenEnum;

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
        return layer.getCell((int) (incomingX + Utils.getPlayer().getWidth()/2) / 32, (int) (incomingY) / 32).getTile().getProperties().get("blocked") == null;
    }
    /**
     * @param x
     * @param y
     * @return CurrentTileProprieties set
     */
    public static MapProperties getCurrentTileProprieties(){
        return layer.getCell((int) (Utils.getPlayer().getX() + Utils.getPlayer().getWidth()/2) / 32, (int) (Utils.getPlayer().getY() + Utils.getPlayer().getHeight()/2) / 32).getTile().getProperties();
    }

    public static void changeScreenIfNecessary(){
        MapProperties properties = getCurrentTileProprieties();
        if(properties.get("changeScreen") == null){
            transitioning = false;
            return;
        }
        if(transitioning == true) return;
        switch (properties.get("changeScreen").toString()) {
            case "MAP2" -> Utils.setScreen(ScreensManager.getScreen(ScreenEnum.SECOND_SCREEN));
            case "MAP1" -> Utils.setScreen(ScreensManager.getScreen(ScreenEnum.MAIN_SCREEN));
            case "MAP3" -> Utils.setScreen(ScreensManager.getScreen(ScreenEnum.SANDSTONE_ARENA));
        }
        transitioning = true;
    }
}
