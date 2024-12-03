package com.mygdx.screens.generic.playable;

import java.util.Comparator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
import com.mygdx.screens.ScreensManager;
import com.mygdx.screens.generic.GenericScreen;

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

    protected PlayableScreen(String name){
        super();
        this.name = name;
        hitboxHandler = new HitboxHandler();
        viewport = new FitViewport(Data.VIEWPORT_X, Data.VIEWPORT_Y, camera);
        stage.setViewport(viewport);
        player = new Player(SavingsManager.getPlayerCoordinates(name));
        Utils.setPlayer(player);
        stage.addActor(player);
        stage.setKeyboardFocus(player);

        hud = new Hud();
    }

    @Override
    public void show() {
        super.show();
        Utils.setPlayer(player);
        Utils.setHitboxHandler(hitboxHandler);
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
            CameraController.applyShakeEffect();
        } 
        TileMapCollisionsManager.changeScreenIfNecessary();
        if(Utils.getActiveScreen() != this) return;

        stage.getActors().sort((a, b) -> Float.compare(b.getY(), a.getY()));
        stage.act(Gdx.graphics.getDeltaTime());

        CameraController.updateCamera();
        hitboxHandler.checkHitboxes();

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
