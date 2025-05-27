package com.mygdx.screens.game.overworld;

import java.util.Collections;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.Data;
import com.mygdx.entities.ForegroundMapComponentEnum;
import com.mygdx.entities.Player;
import com.mygdx.entities.npcs.NPC;
import com.mygdx.entities.npcs.Reflection;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.screens.generic.playable.PlayableScreen;

public class RichDistrict extends PlayableScreen {

    public RichDistrict() {
        super(ResourceEnum.RICH_DISTRICT);
        

        Collections.addAll(npcs,
                new Reflection.ReflectionBuilder()
                        .coordinates(new Vector2(Data.TILE * 30, Data.TILE * 15))
                        .texture(ResourceEnum.BLACKMARKETEER)
                        .path("-")
                        .story(ResourceEnum.ADEPTUS_1)
                        .build(),
                new NPC.NPCBuilder()
                        .coordinates(new Vector2(Data.TILE * 50, Data.TILE * 15))
                        .texture(ResourceEnum.JERKINS)
                        .path("DWW--ASS", "WWDSS-A")
                        .story(ResourceEnum.ADEPTUS_1)
                        .build(),
                new NPC.NPCBuilder()
                        .coordinates(new Vector2(300, 700))
                        .texture(ResourceEnum.JERKINS)
                        .path("DWW--ASS", "WWDSS-A")
                        .story(ResourceEnum.ADEPTUS_1)
                        .build());


        Collections.addAll(mapComponents,
                ForegroundMapComponentEnum.MARMOT_PIZZA
                        .coord(Data.TILE * 59, Data.TILE * 25)
                        .build(),

                ForegroundMapComponentEnum.ABANDONED_BUILDING
                        .coord(Data.TILE * 62, Data.TILE * 25)
                        .build(),
                
                ForegroundMapComponentEnum.ABANDONED_BUILDING
                        .coord(Data.TILE * 65, Data.TILE * 25)
                        .build(),


                ForegroundMapComponentEnum.SKYSCRAPER_MEDIUM
                        .coord(Data.TILE * 68, Data.TILE * 25)
                        .build(),


                ForegroundMapComponentEnum.SYNTH
                        .coord(Data.TILE * 71, Data.TILE * 25)
                        .build(),
                
                ForegroundMapComponentEnum.SYNTH
                        .coord(Data.TILE * 81, Data.TILE * 28)
                        .build()
                );


        updateStage();

        stage.getCamera().translate(player.getX(), player.getY(), 0);
    }

    @Override
    public void show() {
        super.show();
        player.setMovementStyle(Player.Styles.REALTIME);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}