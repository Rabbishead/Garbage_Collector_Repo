package com.mygdx.screens.game.arenas;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entities.Player;
import com.mygdx.entities.npcs.SimpleNPC;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.map.TileSetManager;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.screens.generic.playable.PlayableScreen;

public class SandstoneArena extends PlayableScreen {
    private SimpleNPC testNPC2 = new SimpleNPC.SimpleNPCBuilder()
            .coordinates(new Vector2(200, 300))
            .texture(ResourceEnum.PLAYER)
            .path(new String[] { "-" })
            .build();

    public SandstoneArena() {
        super("SANDSTONE_ARENA");
        stage.addActor(testNPC2);
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
