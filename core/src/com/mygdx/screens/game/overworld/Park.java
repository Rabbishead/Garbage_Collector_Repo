package com.mygdx.screens.game.overworld;

import com.mygdx.Data;
import com.mygdx.entities.NPC;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.screens.generic.PlayableScreen;

public class Park extends PlayableScreen {

    public Park() {
        super(ResourceEnum.PARK);

        addAll(
                new NPC.NPCBuilder()
                        .coordinates(Data.TILE * 40, Data.TILE * 20)
                        .texture(ResourceEnum.JERKINS)
                        .story(ResourceEnum.ADEPTUS_2)
                        .autoStartedScript(ResourceEnum.MEET_1)
                        .build(),
                new NPC.NPCBuilder()
                        .coordinates(Data.TILE * 55, Data.TILE * 30)
                        .texture(ResourceEnum.BLACKMARKETEER)
                        .story(ResourceEnum.ADEPTUS_1)
                        .autoStartedScript(ResourceEnum.MEET_2)
                        .build());

    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}