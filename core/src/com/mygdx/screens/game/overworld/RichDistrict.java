package com.mygdx.screens.game.overworld;

import com.mygdx.Data;
import com.mygdx.entities.ForegroundMapComponentEnum;
import com.mygdx.entities.NPC;
import com.mygdx.entities.Reflection;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.screens.generic.PlayableScreen;

public class RichDistrict extends PlayableScreen {

        private NPC particularNPC = new NPC.NPCBuilder()
                        .coordinates(Data.TILE * 60, Data.TILE * 20)
                        .size(16, 28)
                        .texture(ResourceEnum.BLACKMARKETEER)
                        .build();

        private NPC particularNPC2 = new NPC.NPCBuilder()
                        .coordinates(Data.TILE * 55, Data.TILE * 25)
                        .texture(ResourceEnum.JERKINS)
                        .story(ResourceEnum.ADEPTUS_2)
                        .build();

        public RichDistrict() {
                super(ResourceEnum.RICH_DISTRICT);

                addAll(
                                particularNPC,
                                particularNPC2,

                                new Reflection.ReflectionBuilder()
                                                .coordinates(Data.TILE * 30, Data.TILE * 15)
                                                .texture(ResourceEnum.BLACKMARKETEER)
                                                .story(ResourceEnum.ADEPTUS_1)
                                                .build(),

                                ForegroundMapComponentEnum.MARMOT_PIZZA
                                                .coord(Data.TILE * 59, Data.TILE * 25)
                                                .build(),

                                ForegroundMapComponentEnum.ENERGYPLANT_BUILDING
                                                .coord(Data.TILE * 62, Data.TILE * 25)
                                                .build(),

                                ForegroundMapComponentEnum.ABANDONED_BUILDING
                                                .coord(Data.TILE * 65, Data.TILE * 25)
                                                .build(),

                                ForegroundMapComponentEnum.ABANDONED_BUILDING
                                                .coord(Data.TILE * 65, Data.TILE * 27)
                                                .build(),

                                ForegroundMapComponentEnum.SKYSCRAPER_MEDIUM
                                                .coord(Data.TILE * 68, Data.TILE * 25)
                                                .build(),

                                ForegroundMapComponentEnum.SYNTH
                                                .coord(Data.TILE * 71, Data.TILE * 25)
                                                .build(),

                                ForegroundMapComponentEnum.LAMP
                                                .coord(Data.TILE * 81, Data.TILE * 28)
                                                .build(),

                                ForegroundMapComponentEnum.SKYSCRAPER_1_BACK
                                                .coord(Data.TILE * 80, Data.TILE * 21)
                                                .build(),

                                ForegroundMapComponentEnum.SKYSCRAPER_1_BACK
                                                .coord(Data.TILE * 75, Data.TILE * 21)
                                                .build(),

                                ForegroundMapComponentEnum.SKYSCRAPER_1_SIDE
                                                .coord(Data.TILE * 65, Data.TILE * 20)
                                                .build());
        }

        @Override
        public void show() {
                super.show();
                particularNPC.doScript(ResourceEnum.TEST_SCRIPT);
                particularNPC2.doScript(ResourceEnum.TEST_3);
        }

        @Override
        public void render(float delta) {
                super.render(delta);
        }
}