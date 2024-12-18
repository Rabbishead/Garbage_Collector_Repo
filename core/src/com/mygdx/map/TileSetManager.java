package com.mygdx.map;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.Utils;
import com.mygdx.screens.ScreensManager;
import com.mygdx.states.StateManager;

public class TileSetManager {
    private final TiledMapRenderer tiledMapRenderer;
    private final TiledMap map;
    private ArrayList<Door> doors;

    public TileSetManager(String path, String roomName){
        map = new TmxMapLoader().load(path);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
        doors = new ArrayList<>();
        loadDoors();
    }

    public void render(OrthographicCamera camera){
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        tryChangeRoom();
    }

    public TiledMap getMap() {
        return map;
    }

    private void loadDoors(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("background");
        for (int w = 0; w < layer.getWidth(); w++) {
            for (int h = 0; h < layer.getHeight(); h++) {
                TiledMapTile tile = layer.getCell(w, h).getTile();
                MapProperties prop = 
                    tile instanceof AnimatedTiledMapTile ? 
                        ((AnimatedTiledMapTile) tile).getCurrentFrame().getProperties() : 
                        tile.getProperties();
                if(prop.get("name") == null) continue;
                doors.add(
                    new Door(
                        prop.get("name").toString(), 
                        prop.get("destination").toString(), 
                        prop.get("orientation").toString(), 
                        new Vector2((w) * 32, (h) * 32)));
            }
        }
    }

    /** 
     * if player is on the tile of a door, changes current screen based on the properties of the tile
    */
    public void tryChangeRoom(){
        MapProperties properties = TileMapCollisionsManager.getCurrentTileProprieties();
        if(properties.get("name") == null){
            StateManager.updateState("isExiting", "false");
            return;
        }

        Door intersectingDoor = null;

        for (Door door : doors) {
            if(door.getCenter().dst(Utils.getPlayer().getCoordinates()) < 32){
                intersectingDoor = door;
            }
        }
        if(intersectingDoor == null) return;

        if(!StateManager.getState("isEntering").equals("true") && !StateManager.getState("isExiting").equals("true")){
            Utils.getPlayer().moveTo(intersectingDoor.getCenter().cpy().add(8, 8));
            StateManager.updateState("isEntering", "true");
        }
        if(!Utils.getPlayer().isAutoWalking() && StateManager.getState("isEntering").equals("true")){
            StateManager.updateState("isEntering", "false");
            StateManager.updateState("isExiting", "true");
            String[] temp = intersectingDoor.getDestination().split("_");
            StateManager.updateState("destination", intersectingDoor.getDestination());
            Utils.setScreen(ScreensManager.getPlayableScreen(temp[0] + "_" + temp[1] + "_" + temp[2]));
        }
    }
    public Vector2 getCoord(){
        for (Door door : doors) {
            if(door.getName().equals(StateManager.getState("destination"))){
                return door.getCenter();
            }
        }
        return new Vector2();
    }

    public Vector2 getExitPoint(){
        for (Door door : doors) {
            if(door.getName().equals(StateManager.getState("destination"))){
                return door.getExitPoint();
            }
        }
        return new Vector2();
    }

    public void debug(){
        doors.forEach(Door::print);
    }
}
