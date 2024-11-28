package com.mygdx.screens.game.overworld;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entities.Player;
import com.mygdx.entities.npcs.SimpleNPC;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.map.TileSetManager;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.screens.generic.playable.PlayableScreen;

public class MainScreen extends PlayableScreen {
    private SimpleNPC testNPC1 = new SimpleNPC.SimpleNPCBuilder()
            .coordinates(new Vector2(160, 160))
            .texture(ResourceEnum.PLAYER)
            .path(new String[] { "DWW--ASS", "WWDSS-A" })
            .build();

    private SimpleNPC testNPC2 = new SimpleNPC.SimpleNPCBuilder()
            .coordinates(new Vector2(200, 300))
            .texture(ResourceEnum.PLAYER)
            .path(new String[] { "DW-WASS", "W-WDS-SA" })
            .build();

    private SimpleNPC testNPC3 = new SimpleNPC.SimpleNPCBuilder()
            .coordinates(new Vector2(300, 250))
            .texture(ResourceEnum.PLAYER)
            .path(new String[] { "DW-WASDD", "W-WDS-SA" })
            .build();

    public MainScreen(){
        super("MAIN_SCREEN");
        stage.addActor(testNPC1);
        stage.addActor(testNPC2);
        stage.addActor(testNPC3);

        stage.getCamera().translate(player.getX(), player.getY(), 0);
    }

    @Override
    public void show() {
        super.show();

        player.setMovementStyle(Player.Styles.REALTIME);
        tileSetManager = new TileSetManager("map/map.tmx");
        TileMapCollisionsManager.layer = ((TiledMapTileLayer) tileSetManager.getMap().getLayers().get(0));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
