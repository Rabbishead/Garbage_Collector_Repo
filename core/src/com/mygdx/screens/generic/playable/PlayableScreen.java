package com.mygdx.screens.generic.playable;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.Data;
import com.mygdx.Utils;
import com.mygdx.controllers.camera.CameraController;
import com.mygdx.entities.ForegroundMapComponent;
import com.mygdx.entities.Player;
import com.mygdx.entities.npcs.NPC;
import com.mygdx.hitboxes.HitboxHandler;
import com.mygdx.hud.Hud;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.map.TileSetManager;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.savings.SavingsManager;
import com.mygdx.screens.ScreensManager;
import com.mygdx.screens.generic.GenericScreen;
import com.mygdx.states.StateEnum;
import com.mygdx.states.StateManager;

/**
 * generic abstract class for every playable screen
 */
public abstract class PlayableScreen extends GenericScreen {
    protected boolean stopGame = false;

    protected Hud hud;

    protected TileSetManager tileSetManager;

    protected HitboxHandler hitboxHandler;

    protected Player player;

    protected ArrayList<ForegroundMapComponent> mapComponents;
    protected ArrayList<NPC> npcs;

    protected PlayableScreen(ResourceEnum map) {
        super();
        tileSetManager = new TileSetManager(map);
        TileMapCollisionsManager.layer = ((TiledMapTileLayer) tileSetManager.getMap().getLayers().get("background"));

        hitboxHandler = new HitboxHandler();
        viewport = new FitViewport(Data.VIEWPORT_X, Data.VIEWPORT_Y, camera);
        stage.setViewport(viewport);

        if (StateManager.getBoolState(StateEnum.IS_EXITING)) {
            player = new Player(tileSetManager.getCoord().cpy().add(8, 8));
            player.moveTo(tileSetManager.getExitPoint().cpy().add(8, 8));
        } else
            player = new Player(SavingsManager.getPlayerCoordinates());

        stage.addActor(player);
        stage.setKeyboardFocus(player);

        hud = new Hud();
        Utils.setCurrentHud(hud);

        mapComponents = new ArrayList<>();
        npcs = new ArrayList<>();
    }

    @Override
    public void show() {
        super.show();
        TileMapCollisionsManager.layer = ((TiledMapTileLayer) tileSetManager.getMap().getLayers().get("background"));

        // tileSetManager.debug();paolopaolo

        if (StateManager.getBoolState(StateEnum.IS_EXITING) && !player.isAutoWalking()) {
            player.setCoords(tileSetManager.getCoord().cpy().add(8, 8));
            player.moveTo(tileSetManager.getExitPoint().cpy().add(8, 8));
        }

        Utils.setPlayer(player);
        Utils.setHitboxHandler(hitboxHandler);

        stage.getCamera().position.set(getPlayerCoordinates(), 0);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            Utils.setScreen(ScreensManager.getScreen("PAUSE_SCREEN"));
            return;
        }
        if (Gdx.input.isKeyPressed(Keys.M)) {
            SavingsManager.save();
        }
        if (Gdx.input.isKeyPressed(Keys.R)) {
            stageMsg.dispatchMessage(0);
        }

        if (Utils.getActiveScreen() != this)
            return;

        stage.getActors().sort((a, b) -> Float.compare(b.getY(), a.getY()));

        if (!StateManager.getBoolState(StateEnum.PAUSE))
            stage.act(Gdx.graphics.getDeltaTime());

        CameraController.updateCamera();
        hitboxHandler.checkRegistered();

        tileSetManager.render(camera);
        stage.draw();

        hud.update();
        hud.draw();
    }

    protected void stopGame() {
        stopGame = true;
    }

    protected void resumeGame() {
        stopGame = false;
    }

    public Vector2 getPlayerCoordinates() {
        return new Vector2(player.getX(), player.getY());
    }

    public void updateStage(){
        mapComponents.forEach(comp -> stage.addActor(comp));
        npcs.forEach(npc -> stage.addActor(npc));
    }
}
