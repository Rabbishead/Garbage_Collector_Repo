package com.mygdx.screens.game.arenas;

import java.text.NumberFormat.Style;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.mygdx.entities.Player;
import com.mygdx.entities.npcs.SimpleNPC;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.screens.generic.playable.PlayableScreen;

public class SandstoneArena extends PlayableScreen {
    private SimpleNPC testNPC2 = new SimpleNPC.SimpleNPCBuilder()
            .coordinates(new Vector2(200, 300))
            .texture(ResourceEnum.PLAYER)
            .path(new String[] { "-" })
            .build();

    public SandstoneArena() {
        super("ARENA_ROOM_1", "assets\\map\\arenaRoom1\\sandstone_map.tmx");
        stage.addActor(testNPC2);
        stage.getCamera().translate(player.getX(),player.getY(), 0);
    }

    @Override
    public void show() {
        super.show();

        player.setMovementStyle(Player.Styles.REALTIME);
        player.setX(player.getX());
        player.setY(player.getY());
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if(TileMapCollisionsManager.changeMovementStyle()) player.swapMovementStyle();
    }
}
