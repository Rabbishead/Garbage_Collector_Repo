package com.mygdx.map;

import java.util.ArrayList;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.Utils;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.screens.ScreensManager;
import com.mygdx.states.StateEnum;
import com.mygdx.states.StateManager;

public class TileSetManager implements Telegraph {
    private final TiledMapRenderer tiledMapRenderer;
    private final TiledMap map;
    private ArrayList<Door> doors;

    public TileSetManager(ResourceEnum e) {
        map = Utils.getMap(e);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
        doors = new ArrayList<>();
        loadDoors();
    }

    public void render(OrthographicCamera camera) {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        tryChangeRoom();
    }

    public TiledMap getMap() {
        return map;
    }

    private void loadDoors() {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("background");
        for (int w = 0; w < layer.getWidth(); w++) {
            for (int h = 0; h < layer.getHeight(); h++) {
                TiledMapTile tile = layer.getCell(w, h).getTile();
                MapProperties prop = tile instanceof AnimatedTiledMapTile
                        ? ((AnimatedTiledMapTile) tile).getCurrentFrame().getProperties()
                        : tile.getProperties();
                if (prop.get("name") == null)
                    continue;
                doors.add(
                        new Door(
                                prop.get("name").toString(),
                                prop.get("destination").toString(),
                                prop.get("orientation").toString(),
                                new Vector2((w) * 32, (h) * 32)));
            }
        }
    }

    public void replaceAllWallables() {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("background");
        for (int x = 0; x < layer.getWidth(); x++) {
            for (int y = 0; y < layer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x, y);

                if (cell == null)
                    continue;

                MapProperties properties = cell.getTile().getProperties();
                if (properties.get("blockable") == null)
                    continue;

                cell.setTile(map.getTileSets().getTile(7));
            }
        }
    }

    /**
     * if player is on the tile of a door, changes current screen based on the
     * properties of the tile
     */
    public void tryChangeRoom() {
        MapProperties properties = TileMapCollisionsManager.getCurrentTileProprieties();
        if (properties.get("name") == null) {
            StateManager.updateBoolState(StateEnum.IS_EXITING, false);
            return;
        }

        Door intersectingDoor = null;

        for (Door door : doors) {
            if (door.getCenter().dst(Utils.getPlayer().getCoords()) < 32) {
                intersectingDoor = door;
            }
        }
        if (intersectingDoor == null)
            return;

        if (!StateManager.getBoolState(StateEnum.IS_ENTERING) && !StateManager.getBoolState(StateEnum.IS_EXITING)) {
            Utils.getPlayer().moveTo(intersectingDoor.getCenter().cpy().add(8, 8));
            StateManager.updateBoolState(StateEnum.IS_ENTERING, true);
            intersectingDoor.print();
        }
        if (!Utils.getPlayer().isAutoWalking() && StateManager.getBoolState(StateEnum.IS_ENTERING)) {
            StateManager.updateBoolState(StateEnum.IS_ENTERING, false);
            StateManager.updateBoolState(StateEnum.IS_EXITING, true);
            String[] temp = intersectingDoor.getDestination().split("-");
            StateManager.updateStringState(StateEnum.DESTINATION, intersectingDoor.getDestination());
            System.out.println(temp[0]);
            Utils.setScreen(ScreensManager.getPlayableScreen(temp[0]));
        }
    }

    public Vector2 getCoord() {
        for (Door door : doors) {
            if (door.getName().equals(StateManager.getStringState(StateEnum.DESTINATION))) {
                return door.getCenter();
            }
        }
        return new Vector2();
    }

    public Vector2 getExitPoint() {
        for (Door door : doors) {
            if (door.getName().equals(StateManager.getStringState(StateEnum.DESTINATION))) {
                return door.getExitPoint();
            }
        }
        return new Vector2();
    }

    public void debug() {
        doors.forEach(Door::print);
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        if (2 == msg.message) {
            replaceAllWallables();
        }
        return true;
    }
}
