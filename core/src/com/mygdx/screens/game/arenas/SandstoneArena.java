package com.mygdx.screens.game.arenas;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.Utils;
import com.mygdx.entities.Player;
import com.mygdx.entities.bosses.Reflection;
import com.mygdx.entities.npcs.NPC;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.messages.MsgManager;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.screens.generic.playable.PlayableScreen;

public class SandstoneArena extends PlayableScreen {
    private NPC testNPC2 = new NPC.NPCBuilder()
            .coordinates(new Vector2(200, 300))
            .texture(ResourceEnum.PLAYER)
            .path(new String[] { "-" })
            .story(ResourceEnum.ADEPTUS_2)
            .build();

    private Reflection reflection = new Reflection.ReflectionBuilder()
            .coordinates(new Vector2(300, 300))
            .texture(ResourceEnum.BLACKMARKETEER)
            .path(new String[] { "-" })
            .story(ResourceEnum.ADEPTUS_2)
            .build();

    public SandstoneArena() {
        super("ARENA_ROOM_1", "assets\\map\\arenaRoom1\\sandstone_map.tmx");
        stage.addActor(testNPC2);
        stage.addActor(reflection);
        stage.getCamera().translate(player.getX(), player.getY(), 0);

        stageMsg.addListener(reflection, MsgManager.codes.get(MsgManager.MSG.SHOT));
    }

    @Override
    public void show() {
        super.show();
        if (Math.random() < 0.5f)
            Utils.playAudio(ResourceEnum.REFLECTION_1);
        else
            Utils.playAudio(ResourceEnum.REFLECTION_3);
        player.setMovementStyle(Player.Styles.REALTIME);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if (TileMapCollisionsManager.changeMovementStyle())
            player.swapMovementStyle();
    }
}
