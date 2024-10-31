package com.mygdx.screens.game.arenas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.Utils;
import com.mygdx.entities.Player;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.map.TileSetManager;
import com.mygdx.player.camera.CameraController;
import com.mygdx.screens.PlayableScreen;
import com.mygdx.screens.ScreensManager;

public class SandstoneArena extends PlayableScreen{

    public SandstoneArena(){
        super("SANDSTONE_ARENA");
        stage.getCamera().translate(512, 288, 0);
        player.setMovementStyle(Player.Styles.TILED);
        player.setX(player.getX()-16);
        player.setY(player.getY()-16);
    }

    @Override
    public void show() {
        super.show();
        tileSetManager = new TileSetManager("map/sandstone/sandstone_map.tmx");
        TileMapCollisionsManager.layer = ((TiledMapTileLayer) tileSetManager.getMap().getLayers().get(0));
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
            Utils.setScreen(ScreensManager.getScreen("PAUSE_SCREEN"));
            return;
        } 
        if(Gdx.input.isKeyPressed(Keys.R)){
            CameraController.applyShakeEffect();
        } 
        TileMapCollisionsManager.changeScreenIfNecessary();
        tileSetManager.render((OrthographicCamera) stage.getCamera());

        stage.act(Gdx.graphics.getDeltaTime());
        CameraController.updateCamera();
        hitboxHandler.checkHitboxes();

        stage.draw();
        hud.update();
        hud.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height); 
    }
}
