package com.mygdx.screens.game.overworld;

import java.util.Collections;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.Data;
import com.mygdx.entities.ForegroundMapComponent;
import com.mygdx.entities.Player;
import com.mygdx.entities.bosses.Reflection;
import com.mygdx.entities.npcs.NPC;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.screens.generic.playable.PlayableScreen;

public class Slums extends PlayableScreen {

    public Slums() {
        super("SLUMS", ResourceEnum.SLUMS);

        Collections.addAll(mapComponents,
                new ForegroundMapComponent.ForegroundMapComponentBuilder()
                        .coordinates(new Vector2(Data.TILE * 20, Data.TILE * 12))
                        .texture(ResourceEnum.LAMP)
                        .singlePieceWidth(1)
                        .singlePieceHeight(2)
                        .animationRate(2f)
                        .delay(100)
                        .build(),

                new ForegroundMapComponent.ForegroundMapComponentBuilder()
                        .coordinates(new Vector2(Data.TILE * 20, Data.TILE * 7))
                        .texture(ResourceEnum.SPARK_BUILDING_2)
                        .singlePieceWidth(5)
                        .singlePieceHeight(2)
                        .animationRate(0.2f)
                        .delay(1000)
                        .build(),

                new ForegroundMapComponent.ForegroundMapComponentBuilder()
                        .coordinates(new Vector2(Data.TILE * 23, Data.TILE * 7))
                        .texture(ResourceEnum.MARMOT_PIZZA)
                        .singlePieceWidth(3)
                        .singlePieceHeight(3)
                        .animationRate(1)
                        .delay(2000)
                        .build(),

                new ForegroundMapComponent.ForegroundMapComponentBuilder()
                        .coordinates(new Vector2(Data.TILE * 10, Data.TILE * 20))
                        .texture(ResourceEnum.PALACE)
                        .singlePieceWidth(4)
                        .singlePieceHeight(4)
                        .build(),

                new ForegroundMapComponent.ForegroundMapComponentBuilder()
                        .coordinates(new Vector2(400, 800))
                        .texture(ResourceEnum.PALACE)
                        .singlePieceWidth(4)
                        .singlePieceHeight(4)
                        .build(),

                new ForegroundMapComponent.ForegroundMapComponentBuilder()
                        .coordinates(new Vector2(400, 1000))
                        .texture(ResourceEnum.LONG_LAMP)
                        .singlePieceWidth(2)
                        .singlePieceHeight(2)
                        .build());

        Collections.addAll(npcs,
                new Reflection.NPCBuilder()
                        .coordinates(new Vector2(200, 1200))
                        .texture(ResourceEnum.BLACKMARKETEER)
                        .path(new String[] { "-" })
                        .story(ResourceEnum.ADEPTUS_1)
                        .build(),
                new NPC.NPCBuilder()
                        .coordinates(new Vector2(160, 1000))
                        .texture(ResourceEnum.JERKINS)
                        .path(new String[] { "DWW--ASS", "WWDSS-A" })
                        .story(ResourceEnum.ADEPTUS_1)
                        .build(),
                new NPC.NPCBuilder()
                        .coordinates(new Vector2(300, 700))
                        .texture(ResourceEnum.JERKINS)
                        .path(new String[] { "DWW--ASS", "WWDSS-A" })
                        .story(ResourceEnum.ADEPTUS_1)
                        .build());

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
