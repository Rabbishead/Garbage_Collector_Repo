package com.mygdx.screens.game.overworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.dialogues.BossDialogue;
import com.mygdx.entities.Player;
import com.mygdx.entities.TestActor;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.map.TileSetManager;
import com.mygdx.player.camera.CameraController;
import com.mygdx.screens.PlayableScreen;


public class MainScreen extends PlayableScreen {
    TestActor testActor = new TestActor(160, 160);
    BossDialogue bossDialogue;

    public MainScreen(){
        super("MAIN_SCREEN");
        stage.addActor(testActor);

        stage.getCamera().translate(player.getX(),player.getY(), 0);
        player.setMovementStyle(Player.Styles.REALTIME);
    }

    @Override
    public void show() {
        super.show();
        tileSetManager = new TileSetManager("map/map.tmx");
        TileMapCollisionsManager.layer = ((TiledMapTileLayer) tileSetManager.getMap().getLayers().get(0));
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if(Gdx.input.isKeyPressed(Keys.Y)){
            stopGame();
        }
        if(Gdx.input.isKeyPressed(Keys.U)){
            resumeGame();
            if(bossDialogue != null){
                bossDialogue.remove();
                bossDialogue = null;
            } 
        }
        if(stopGame){
            
            if(bossDialogue == null){
                bossDialogue = new BossDialogue("Ciao");
                stage.addActor(bossDialogue);
            } 
            tileSetManager.render((OrthographicCamera) stage.getCamera());
            stage.draw();
            hud.draw();
            return;
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
