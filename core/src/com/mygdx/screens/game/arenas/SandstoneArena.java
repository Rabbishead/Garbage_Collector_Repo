package com.mygdx.screens.game.arenas;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.entities.Player;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.map.TileSetManager;
import com.mygdx.screens.generic.playable.PlayableScreen;

public class SandstoneArena extends PlayableScreen {

    public SandstoneArena() {
        super("SANDSTONE_ARENA");
        stage.getCamera().translate(512, 288, 0);
    }

    @Override
    public void show() {
        super.show();
        player.setMovementStyle(Player.Styles.TILED);
        player.setX(player.getX() - 16);
        player.setY(player.getY() - 16);

        tileSetManager = new TileSetManager("map/sandstone/sandstone_map.tmx");
        TileMapCollisionsManager.layer = ((TiledMapTileLayer) tileSetManager.getMap().getLayers().get(0));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
