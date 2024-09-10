package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.dialogues.NPCDialogue;
import com.mygdx.entities.Player;
import com.mygdx.game.GarbageCollection;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.map.TileSetManager;

public class MainScreen extends ScreenAdapter {

    private GarbageCollection game;
    private Stage stage;
    private TileSetManager tileSetManager;
    Player player = new Player(160, 160);
    private NPCDialogue npcDialogue;

    public MainScreen(GarbageCollection game){
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        stage.setViewport(new ScreenViewport(new OrthographicCamera(320,320)));
        stage.getCamera().translate(160,160,0);

        Gdx.input.setInputProcessor(stage);

        tileSetManager = new TileSetManager();
        TileMapCollisionsManager.layer = ((TiledMapTileLayer) tileSetManager.getMap().getLayers().get(0));

        player.setMovementStyle(Player.Styles.REALTIME);
        stage.addActor(player);
        stage.setKeyboardFocus(player);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,0);
        ScreenUtils.clear(1,1,1,0);
        tileSetManager.render((OrthographicCamera) stage.getCamera());


        stage.act(Gdx.graphics.getDeltaTime());

        stage.draw();

        if(TileMapCollisionsManager.getCurrentTileProprieties(player.getX(), player.getY()).get("dialogue") != null && npcDialogue == null){
            npcDialogue = new NPCDialogue(player.getX() +10, player.getY()+30, TileMapCollisionsManager.getCurrentTileProprieties(player.getX(), player.getY()).get("content").toString());
            stage.addActor(npcDialogue);
        }
        else if(TileMapCollisionsManager.getCurrentTileProprieties(player.getX(), player.getY()).get("dialogue") == null && npcDialogue != null){
            stage.getActors().removeIndex(stage.getActors().size-1);
            npcDialogue = null;
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
