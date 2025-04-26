package com.mygdx.screens.game.arenas;

import java.util.Collections;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.Data;
import com.mygdx.Utils;
import com.mygdx.entities.Player;
import com.mygdx.entities.bosses.Reflection;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.screens.generic.playable.PlayableScreen;

public class SandstoneArena extends PlayableScreen {

    public SandstoneArena() {
        super("REFLECTION_ARENA", ResourceEnum.REFLECTION_ARENA);

        stage.getCamera().translate(player.getX(), player.getY(), 0);
        Collections.addAll(npcs,
                new Reflection.ReflectionBuilder()
                        .coordinates(new Vector2(Data.TILE * 5, Data.TILE * 5))
                        .size(new Vector2(16, 32))
                        .texture(ResourceEnum.BLACKMARKETEER)
                        .build());

        updateStage();
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
