package com.mygdx.screens.game.arenas;

import com.mygdx.Data;
import com.mygdx.Utils;
import com.mygdx.controllers.messages.MSG;
import com.mygdx.controllers.messages.MsgManager;
import com.mygdx.entities.npcs.Reflection;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.screens.generic.playable.PlayableScreen;

public class ReflectionArena extends PlayableScreen {

    public ReflectionArena() {
        super(ResourceEnum.REFLECTION_ARENA);
        addAll(new Reflection.ReflectionBuilder().coordinates(Data.TILE * 10, Data.TILE * 10)
                .texture(ResourceEnum.BLACKMARKETEER).build());
    }

    @Override
    public void show() {
        super.show();
        if (Math.random() < 0.5f)
            Utils.playAudio(ResourceEnum.REFLECTION_1);
        else
            Utils.playAudio(ResourceEnum.REFLECTION_3);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if (TileMapCollisionsManager.changeMovementStyle()) {
            MsgManager.sendStageMsg(MSG.BLOCK_WALLS);
            MsgManager.sendStageMsg(MSG.CHANGE_MOV_STYLE);

        }
    }
}
