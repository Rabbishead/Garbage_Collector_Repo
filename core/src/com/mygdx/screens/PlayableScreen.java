package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.Data;
import com.mygdx.Utils;
import com.mygdx.entities.Player;
import com.mygdx.hitboxes.HitboxHandler;
import com.mygdx.hud.Hud;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.map.TileSetManager;
import com.mygdx.player.camera.CameraController;
import com.mygdx.player.gunControls.GunController;
import com.mygdx.savings.SavingsManager;
import com.mygdx.savings.Settings;
import com.mygdx.screens.ScreensManager.ScreenEnum;

public class PlayableScreen extends GenericScreen{
    protected boolean stopGame = false;

    protected Hud hud;

    protected TileSetManager tileSetManager;

    protected HitboxHandler hitboxHandler;
    
    protected Player player;
    protected PlayableScreen(){}

    protected PlayableScreen(String name){
        this.name = name;
        hitboxHandler = new HitboxHandler();
        viewport = new FitViewport(Data.VIEWPORT_X, Data.VIEWPORT_Y, camera);
        stage.setViewport(viewport);
        player = new Player(SavingsManager.getPlayerCoordinates(name));
        Utils.setPlayer(player);
        stage.addActor(player);
        stage.setKeyboardFocus(player);

        GunController.get();

        hud = new Hud();
    }

    @Override
    public void show() {
        Utils.setStage(stage);
        Utils.setPlayer(player);
        Utils.setHitboxHandler(hitboxHandler);
        
        CameraController.initCamera();

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 0);
        ScreenUtils.clear(1, 1, 1, 0);
        if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
            Utils.setScreen(ScreensManager.getScreen(ScreenEnum.PAUSE_SCREEN));
            return;
        } 
        if(Gdx.input.isKeyPressed(Keys.M)){
            SavingsManager.save();
        }
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
