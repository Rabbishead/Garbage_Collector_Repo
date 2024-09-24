package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.Utils;
import com.mygdx.entities.Player;
import com.mygdx.entities.TestActor;
import com.mygdx.game.GarbageCollection;
import com.mygdx.gunControls.GunController;
import com.mygdx.hitboxes.HitboxHandler;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.map.TileSetManager;

public class MainScreen extends ScreenAdapter {

    private GarbageCollection game;
    private Stage stage;
    private TileSetManager tileSetManager;
    HitboxHandler hitboxHandler = new HitboxHandler();
    Player player = new Player(160, 160);
    TestActor testActor = new TestActor(160, 160);

    private OrthographicCamera hudCamera;

    private BitmapFont font;


    public MainScreen(GarbageCollection game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Utils.setStage(stage);
        stage.setViewport(new ScreenViewport(new OrthographicCamera(1000, 1000)));
        stage.getCamera().translate(160, 160, 0);

        hudCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        hudCamera.position.set(hudCamera.viewportWidth / 2.0f, hudCamera.viewportHeight / 2.0f, 1.0f);

        GunController.get();

        Gdx.input.setInputProcessor(stage);

        tileSetManager = new TileSetManager();
        TileMapCollisionsManager.layer = ((TiledMapTileLayer) tileSetManager.getMap().getLayers().get(0));

        player.setMovementStyle(Player.Styles.REALTIME);
        stage.addActor(player);
        stage.setKeyboardFocus(player);
        stage.addActor(testActor);

        font = new BitmapFont();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 0);
        ScreenUtils.clear(1, 1, 1, 0);

        tileSetManager.render((OrthographicCamera) stage.getCamera());

        stage.act(Gdx.graphics.getDeltaTime());

        hitboxHandler.checkHitboxes();

        stage.draw();

        
        hudCamera.update();
        stage.getBatch().setProjectionMatrix(hudCamera.combined);
        stage.getBatch().begin();
        font.draw(stage.getBatch(), "Upper left, FPS=" + Gdx.graphics.getFramesPerSecond(), 0, hudCamera.viewportHeight);
        font.draw(stage.getBatch(), "Lower left", 0, font.getLineHeight());

        stage.getBatch().end();

        // sout fps
        //System.out.println("FPS: " + Gdx.graphics.getFramesPerSecond());
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
