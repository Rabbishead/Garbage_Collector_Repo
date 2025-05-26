package com.mygdx.screens.game.overworld;

import java.util.Collections;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.Data;
import com.mygdx.entities.ForegroundMapComponent;
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
                new ForegroundMapComponent.ForegroundMapComponentBuilder()
                        .coordinates(new Vector2(Data.TILE * 59, Data.TILE * 25))
                        .texture(ResourceEnum.MARMOT_PIZZA)
                        .singlePieceWidth(3)
                        .singlePieceHeight(3)
                        .animationRate(0.2f)
                        .delay(1000)
                        .build(),

                new ForegroundMapComponent.ForegroundMapComponentBuilder()
                        .coordinates(new Vector2(Data.TILE * 62, Data.TILE * 25))
                        .texture(ResourceEnum.ABANDONED_BUILDING)
                        .singlePieceWidth(3)
                        .singlePieceHeight(2)
                        .animationRate(4f)
                        .delay(500)
                        .build(),
                
                        new ForegroundMapComponent.ForegroundMapComponentBuilder()
                        .coordinates(new Vector2(Data.TILE * 65, Data.TILE * 25))
                        .texture(ResourceEnum.ENERGYPLANT_BUILDING)
                        .singlePieceWidth(3)
                        .singlePieceHeight(2)
                        .animationRate(0.4f)
                        .delay(500)
                        .build(),

                new ForegroundMapComponent.ForegroundMapComponentBuilder()
                        .coordinates(new Vector2(Data.TILE * 68, Data.TILE * 25))
                        .texture(ResourceEnum.SPARK_BUILDING_1)
                        .singlePieceWidth(3)
                        .singlePieceHeight(4)
                        .animationRate(0.2f)
                        .delay(1000)
                        .build(),
                new ForegroundMapComponent.ForegroundMapComponentBuilder()
                        .coordinates(new Vector2(Data.TILE * 71, Data.TILE * 25))
                        .texture(ResourceEnum.SYNTH)
                        .singlePieceWidth(3)
                        .singlePieceHeight(2)
                        .animationRate(1f)
                        .delay(500)
                        .build(),
                new ForegroundMapComponent.ForegroundMapComponentBuilder()
                        .coordinates(new Vector2(Data.TILE * 62, Data.TILE * 25))
                        .texture(ResourceEnum.ABANDONED_BUILDING)
                        .singlePieceWidth(3)
                        .singlePieceHeight(2)
                        .animationRate(0.2f)
                        .delay(1000)
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