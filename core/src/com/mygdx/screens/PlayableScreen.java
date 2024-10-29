package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
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

public class PlayableScreen extends GenericScreen{
    protected boolean stopGame = false;

    protected Hud hud;

    protected TileSetManager tileSetManager;

    protected HitboxHandler hitboxHandler;
    
    protected Player player;

    protected PlayableScreen(){
        hitboxHandler = new HitboxHandler();
        viewport = new FitViewport(Data.VIEWPORT_X, Data.VIEWPORT_Y, camera);
        stage.setViewport(viewport);
        GunController.get();

        player = new Player(Settings.playerX, Settings.playerY);
        Utils.setPlayer(player);
        SavingsManager.load();
        stage.addActor(player);
        stage.setKeyboardFocus(player);
        player.setMovementStyle(Player.Styles.REALTIME);

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

    protected void stopGame(){
        stopGame = true;
    }
    protected void resumeGame(){
        stopGame = false;
    }
}
