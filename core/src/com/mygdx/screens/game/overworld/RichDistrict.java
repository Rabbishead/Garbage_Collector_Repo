package com.mygdx.screens.game.overworld;

import com.mygdx.Data;
import com.mygdx.entities.npcs.NPC;
import com.mygdx.entities.npcs.Reflection;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.resources.TextureEnum;
import com.mygdx.screens.generic.PlayableScreen;

public class RichDistrict extends PlayableScreen {

        private NPC particularNPC = new NPC.NPCBuilder()
                        .coordinates(Data.TILE * 60, Data.TILE * 20)
                        .size(16, 28)
                        .texture(TextureEnum.BLACKMARKETEER)
                        .autoStartedScript(ResourceEnum.TEST_SCRIPT)
                        .build();

        private NPC particularNPC2 = new NPC.NPCBuilder()
                        .coordinates(Data.TILE * 55, Data.TILE * 25)
                        .texture(TextureEnum.JERKINS)
                        .story(ResourceEnum.ADEPTUS_1)
                        .autoStartedScript(ResourceEnum.TEST_3)
                        .build();

        public RichDistrict() {
                super(ResourceEnum.RICH_DISTRICT);
                addAll(
                                particularNPC,
                                particularNPC2,

                                new Reflection.ReflectionBuilder()
                                                .coordinates(Data.TILE * 30, Data.TILE * 15)
                                                .texture(TextureEnum.BLACKMARKETEER)
                                                .story(ResourceEnum.ADEPTUS_1)
                                                .build()


                                );
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