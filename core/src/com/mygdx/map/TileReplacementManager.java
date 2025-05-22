package com.mygdx.map;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;

public class TileReplacementManager {
    private TiledMapTile blockerTile = null;
    private TiledMapTile blockableTile = null;
    private boolean isBlocked = false;
    private TileReplacementEnum category; 
    private TiledMap map;

    private TileReplacementManager(TiledMap map, TileReplacementEnum category){
        this.map = map;
        this.category = category;    
    }

    public void blockTiles() {

        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("background");
        for (int x = 0; x < layer.getWidth(); x++) {
            for (int y = 0; y < layer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x, y);

                if (cell == null)
                    continue;
                if(cell.getTile() == null)
                    continue;

                MapProperties properties = cell.getTile().getProperties();

                if (properties.get("blockable") != null){
                    blockableTile = cell.getTile();
                    cell.setTile(blockerTile);
                    
                }
            }
        }
    }
    public void unBlockTiles() {

        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("background");
        for (int x = 0; x < layer.getWidth(); x++) {
            for (int y = 0; y < layer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x, y);

                if (cell == null)
                    continue;
                if(cell.getTile() == null)
                    continue;

                MapProperties properties = cell.getTile().getProperties();

                if (properties.get("blocker") != null){
                    cell.setTile(blockableTile);
                }
            }
        }
    }

    private void loadBlockerTile(){
        TiledMapTileSet set = map.getTileSets().getTileSet(0);
        for (TiledMapTile tiledMapTile : set) {
            if(tiledMapTile.getProperties().get("blocker") != null){
                blockerTile = tiledMapTile;
            }
        }
    }
}
