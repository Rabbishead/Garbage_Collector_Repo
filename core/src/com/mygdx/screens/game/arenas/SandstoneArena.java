package com.mygdx.screens.game.arenas;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.entities.Player;
import com.mygdx.entities.npcs.SimpleNPC;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.screens.generic.playable.PlayableScreen;

public class SandstoneArena extends PlayableScreen {
    private SimpleNPC testNPC2 = new SimpleNPC.SimpleNPCBuilder()
            .coordinates(new Vector2(200, 300))
            .texture(ResourceEnum.PLAYER)
            .path(new String[] { "-" })
            .build();

    public SandstoneArena() {
        super("ARENA_ROOM_1", "assets\\map\\sandstone\\sandstone_map.tmx");
        stage.addActor(testNPC2);
        stage.getCamera().translate(512, 288, 0);
    }

    @Override
    public void show() {
        super.show();

        player.setMovementStyle(Player.Styles.TILED);
        player.setX(player.getX());
        player.setY(player.getY());
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
