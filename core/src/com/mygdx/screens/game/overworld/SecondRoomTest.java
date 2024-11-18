package com.mygdx.screens.game.overworld;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.entities.Player;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.map.TileSetManager;
import com.mygdx.screens.generic.playable.PlayableScreen;

public class SecondRoomTest extends PlayableScreen {

    public SecondRoomTest(){
        super("SECOND_SCREEN");
        stage.getCamera().translate(player.getX(),player.getY(), 0);
        player.setMovementStyle(Player.Styles.REALTIME);
    }

    @Override
    public void show() {
        super.show();
        tileSetManager = new TileSetManager("map/map_beta.tmx");
        TileMapCollisionsManager.layer = ((TiledMapTileLayer) tileSetManager.getMap().getLayers().get(0));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
