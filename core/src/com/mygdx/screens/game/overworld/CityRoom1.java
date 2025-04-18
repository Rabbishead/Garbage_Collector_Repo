package com.mygdx.screens.game.overworld;

import java.util.stream.Stream;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entities.ForegroundMapComponent;
import com.mygdx.entities.Player;
import com.mygdx.entities.bosses.Reflection;
import com.mygdx.entities.npcs.NPC;
import com.mygdx.messages.MsgManager;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.screens.generic.playable.PlayableScreen;

public class CityRoom1 extends PlayableScreen {

    public int TILE = 32;

    private ForegroundMapComponent[] mapComponents = new ForegroundMapComponent[] {
        new ForegroundMapComponent.ForegroundMapComponentBuilder()
            .coordinates(new Vector2(TILE * 20, TILE * 12))
            .texture(ResourceEnum.LAMP)
            .singlePieceWidth(1)
            .singlePieceHeight(2)
            .animationRate(1000f)
            .build(),

        new ForegroundMapComponent.ForegroundMapComponentBuilder()
            .coordinates(new Vector2(TILE * 20, TILE * 7))
            .texture(ResourceEnum.SPARK_BUILDING_2)
            .singlePieceWidth(5)
            .singlePieceHeight(2)
            .animationRate(0.1f)
            .build(),

        new ForegroundMapComponent.ForegroundMapComponentBuilder()
            .coordinates(new Vector2(TILE * 10, TILE *20))
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
            .build()
    };

    private Reflection reflection = new Reflection.ReflectionBuilder()
            .coordinates(new Vector2(200, 1200))
            .texture(ResourceEnum.BLACKMARKETEER)
            .path(new String[] { "-" })
            .story(ResourceEnum.ADEPTUS_1)
            .build();

    private NPC testNPC1 = new NPC.NPCBuilder()
            .coordinates(new Vector2(160, 1000))
            .texture(ResourceEnum.JERKINS)
            .path(new String[] { "DWW--ASS", "WWDSS-A" })
            .story(ResourceEnum.ADEPTUS_1)
            .build();

    private NPC testNPC2 = new NPC.NPCBuilder()
            .coordinates(new Vector2(300, 700))
            .texture(ResourceEnum.JERKINS)
            .path(new String[] { "DWW--ASS", "WWDSS-A" })
            .story(ResourceEnum.ADEPTUS_1)
            .build();

    public CityRoom1() {
        super("CITY_ROOM_1", "assets\\map\\cityRoom1\\cityRoom1.tmx");
        Stream.of(mapComponents).forEach(comp -> stage.addActor(comp));
        stage.addActor(reflection);
        stage.addActor(testNPC1);
        stage.addActor(testNPC2);

        stageMsg.addListener(reflection, MsgManager.codes.get(MsgManager.MSG.SHOT));

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
