package com.mygdx.screens.game.arenas;

import com.mygdx.Data;

import com.mygdx.controllers.messages.MSG;
import com.mygdx.entities.Reflection;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.resources.RM;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.resources.TextureEnum;
import com.mygdx.screens.generic.PlayableScreen;
import com.mygdx.stage.GCStage;

public class ReflectionArena extends PlayableScreen {

    public ReflectionArena() {
        super(ResourceEnum.REFLECTION_ARENA);
        addAll(new Reflection.ReflectionBuilder().coordinates(Data.TILE * 10, Data.TILE * 10)
                .texture(TextureEnum.BLACKMARKETEER).build());
    }

    @Override
    public void show() {
        super.show();
        if (Math.random() < 0.5f)
            RM.get().playAudio(ResourceEnum.REFLECTION_1);
        else
            RM.get().playAudio(ResourceEnum.REFLECTION_3);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if (TileMapCollisionsManager.changeFightingState()) {
            GCStage.get().send(MSG.BLOCK_WALLS);
            GCStage.get().send(MSG.SWAP_FIGHT_STATE);

        }
    }
}
