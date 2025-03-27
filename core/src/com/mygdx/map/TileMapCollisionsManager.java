package com.mygdx.map;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.Utils;

/**
 * manages collisions inside a tilemap
 */
public class TileMapCollisionsManager {
    public static TiledMapTileLayer layer;
    public static boolean transitionInCooldown = false;

    /**
     * @param incomingX
     * @param incomingY
     * @return true if player can move in the incoming position
     */
    public static boolean canMove(float incomingX, float incomingY) {
        TiledMapTileLayer.Cell cell = layer.getCell(
                (int) (incomingX + Utils.getPlayer().getWidth() / 2) / 32,
                (int) (incomingY + Utils.getPlayer().getHeight() / 2 - 16) / 32);
        if (cell == null)
            return false;

        TiledMapTile tile = cell.getTile();
        return tile.getProperties().get("blocked") == null;
    }

    public static MapProperties getCurrentTileProprieties() {
        TiledMapTile tile = layer.getCell((int) (Utils.getPlayer().getX() + Utils.getPlayer().getWidth() / 2) / 32,
                (int) (Utils.getPlayer().getY() + Utils.getPlayer().getHeight() / 2) / 32).getTile();
        return tile.getProperties();
    }

    public static boolean changeMovementStyle() {
        if (!transitionInCooldown && getCurrentTileProprieties().containsKey("transition")) {
            transitionInCooldown = true;
            return true;
        }

        if (!getCurrentTileProprieties().containsKey("transition") && transitionInCooldown) {
            transitionInCooldown = false;
        }
        return false;
    }
}
