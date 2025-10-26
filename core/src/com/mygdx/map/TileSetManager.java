package com.mygdx.map;

import java.util.ArrayList;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.GCStage;
import com.mygdx.entities.map.Door;
import com.mygdx.entities.map.MapConstructor;
import com.mygdx.resources.RM;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.screens.Screens;
import com.mygdx.screens.ScreensManager;
import com.mygdx.states.StateEnum;
import com.mygdx.states.StateManager;

public class TileSetManager implements Telegraph {
    private final TiledMapRenderer tiledMapRenderer;
    private final TiledMap map;

    private ArrayList<Door> doors;

    private ArrayList<TileReplacementManager> tileReplace = new ArrayList<>();

    public TileSetManager(ResourceEnum e) {
        map = RM.get().getMap(e);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
        doors = new ArrayList<>();
        loadDoors();
        loadObjLayers();
        loadReplacers();
    }

    public void render(OrthographicCamera camera) {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        tryChangeRoom();
    }

    public TiledMap getMap() {
        return map;
    }

    public void loadObjLayers() {
        for (MapLayer layer : map.getLayers()) {
            if (layer instanceof TiledMapTileLayer)
                continue;
            MapObjects objects = layer.getObjects();
            if (objects.getCount() == 0)
                continue;

            // Check if atlas exists
            String layerName = layer.getName();
            ResourceEnum atlas = layerName.equals("doors") ? ResourceEnum.COMPONENTS
                    : ResourceEnum.valueOf(layerName.toUpperCase());
            if (atlas == null) {
                throw new RuntimeException("No atlas with: " + layerName + " found, check your spelling");
            }

            for (MapObject obj : layer.getObjects()) {
                //System.out.println(obj.getName());

                if (obj instanceof RectangleMapObject rectObj) {
                    Rectangle rect = rectObj.getRectangle();

                    ResourceEnum texture = ResourceEnum.valueOf(obj.getName());

                    TextureRegion region = RM.get().getAtlas(atlas).findRegion(texture.label);
                    if (region == null) {
                        throw new RuntimeException("\nERROR\n\nResourceEnum: " + texture +
                                " not found in region, \nthe .png file should be in the folder: assets/raw/" + atlas
                                + " \ncheck your spelling and pack all the assets");
                    }
                    if (atlas.equals(ResourceEnum.BUILDINGS)) {
                        GCStage.get().addActor(
                                MapConstructor.getBuilding(rect.getX(), rect.getY(), texture));
                    } else if(atlas.equals(ResourceEnum.COMPONENTS)){
                        GCStage.get().addActor(
                                MapConstructor.getComponent(rect.getX(), rect.getY(), texture));
                    }
                }
            }
        }

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
                                new Vector2((w) * 32, (h) * 32),
                                prop.get("side") == null));
            }
        }
    }

    public void loadReplacers() {

        for (TiledMapTile tile : map.getTileSets().getTileSet(0)) {

            var prop = tile.getProperties();

            if (prop.get("category") == null)
                continue;
            if (prop.get("blocker") == null)
                continue;

            tileReplace.add(new TileReplacementManager(map,
                    TileReplacementEnum.valueOf(prop.get("category").toString()), tile));
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
            if (door.getCenter().dst(GCStage.get().getPlayer().getCoords()) < 32) {
                intersectingDoor = door;
            }
        }
        if (intersectingDoor == null)
            return;

        getCenterDoor(intersectingDoor);

        if (!StateManager.getBoolState(StateEnum.IS_ENTERING) && !StateManager.getBoolState(StateEnum.IS_EXITING)) {
            GCStage.get().getPlayer().moveTo(intersectingDoor.getCenter().cpy().add(8, 8));
            StateManager.updateBoolState(StateEnum.IS_ENTERING, true);
            intersectingDoor.print();
        }
        if (!GCStage.get().getPlayer().isAutoWalking() && StateManager.getBoolState(StateEnum.IS_ENTERING)) {
            StateManager.updateBoolState(StateEnum.IS_ENTERING, false);
            StateManager.updateBoolState(StateEnum.IS_EXITING, true);
            String[] temp = intersectingDoor.getDestination().split("-");
            StateManager.updateStringState(StateEnum.DESTINATION, intersectingDoor.getDestination());
            ScreensManager.setScreen(Screens.valueOf(temp[0]));
        }
    }

    public Vector2 getCoord() {
        for (Door door : doors) {
            if (door.getName().equals(StateManager.getStringState(StateEnum.DESTINATION)) && door.isCenterDoor()) {
                return door.getCenter();
            }
        }
        return new Vector2();
    }

    public Vector2 getExitPoint() {
        for (Door door : doors) {
            if (door.getName().equals(StateManager.getStringState(StateEnum.DESTINATION)) && door.isCenterDoor()) {
                return door.getExitPoint();
            }
        }
        return new Vector2();
    }

    public Door getCenterDoor(Door side) {
        for (Door door : doors) {
            if (door.getName().equals(side.getName()) && door.isCenterDoor())
                return side;
        }
        return side;
    }

    public void debug() {
        doors.forEach(Door::print);
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        for (TileReplacementManager m : tileReplace) {
            if (msg.message == m.getCategory().getMsg().code) {
                m.handle();
                System.out.println("handled");
            }
        }

        return true;
    }

}
