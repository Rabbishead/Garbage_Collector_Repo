package com.mygdx.screens.generic.playable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.Data;
import com.mygdx.Utils;
import com.mygdx.entities.Player;
import com.mygdx.hitboxes.HitboxHandler;
import com.mygdx.hud.Hud;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.map.TileSetManager;
import com.mygdx.messages.MsgManager;
import com.mygdx.player.camera.CameraController;
import com.mygdx.savings.SavingsManager;
import com.mygdx.screens.ScreensManager;
import com.mygdx.screens.generic.GenericScreen;
import com.mygdx.states.StateManager;

/**
 * generic abstract class for every playable screen
 */
public abstract class PlayableScreen extends GenericScreen{
    protected boolean stopGame = false;

    protected Hud hud;

    protected TileSetManager tileSetManager;

    protected HitboxHandler hitboxHandler;
    
    protected Player player;

    protected PlayableScreen(){}

    protected PlayableScreen(String name, String mapPath){
        super();
        this.name = name;
        tileSetManager = new TileSetManager(mapPath, name);
        TileMapCollisionsManager.layer = ((TiledMapTileLayer) tileSetManager.getMap().getLayers().get("background"));

        hitboxHandler = new HitboxHandler();
        viewport = new FitViewport(Data.VIEWPORT_X, Data.VIEWPORT_Y, camera);
        stage.setViewport(viewport);

        if(StateManager.getState("isExiting").equals("true")){
            player = new Player(tileSetManager.getCoord().cpy().add(8, 8));
            player.moveTo(tileSetManager.getExitPoint().cpy().add(8, 8));
        }
        else player = new Player(SavingsManager.getPlayerCoordinates());

        stage.addActor(player);
        stage.setKeyboardFocus(player);

        hud = new Hud();
        Utils.setCurrentHud(hud);

        stageMsg.addListener(player, 0);
        stageMsg.addListener(tileSetManager, 2);
    }

    @Override
    public void show() {
        super.show();
        TileMapCollisionsManager.layer = ((TiledMapTileLayer) tileSetManager.getMap().getLayers().get("background"));

        //tileSetManager.debug();

        if(StateManager.getState("isExiting").equals("true") && !player.isAutoWalking()){
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
        if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
            Utils.setScreen(ScreensManager.getScreen("PAUSE_SCREEN"));
            return;
        } 
        if(Gdx.input.isKeyPressed(Keys.M)){
            SavingsManager.save();
        }
        if(Gdx.input.isKeyPressed(Keys.R)){
            stageMsg.dispatchMessage(0);
        } 
        
        if(Utils.getActiveScreen() != this) return;

        stage.getActors().sort((a, b) -> Float.compare(b.getY(), a.getY()));

        if(StateManager.getState("pause").equals("false"))
            stage.act(Gdx.graphics.getDeltaTime());

        CameraController.updateCamera();
        hitboxHandler.checkRegistered();

        tileSetManager.render(camera);
        stage.draw();
        
        hud.update();
        hud.draw();
    }

    protected void stopGame(){
        stopGame = true;
    }
    protected void resumeGame(){
        stopGame = false;
    }
    public Vector2 getPlayerCoordinates(){
        return new Vector2(player.getX(), player.getY());
    }
}
