package com.mygdx.screens.game.arenas;

import com.mygdx.Utils;
import com.mygdx.entities.Player;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.screens.generic.playable.PlayableScreen;

public class SandstoneArena extends PlayableScreen {

    public SandstoneArena() {
        super("REFLECTION_ARENA", ResourceEnum.REFLECTION_ARENA);

        stage.getCamera().translate(player.getX(), player.getY(), 0);
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
