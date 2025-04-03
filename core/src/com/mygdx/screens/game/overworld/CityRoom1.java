package com.mygdx.screens.game.overworld;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.entities.ForegroundMapComponent;
import com.mygdx.entities.Player;
import com.mygdx.entities.bosses.Reflection;
import com.mygdx.entities.npcs.NPC;
import com.mygdx.messages.MsgManager;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.screens.generic.playable.PlayableScreen;

public class CityRoom1 extends PlayableScreen {

    private ForegroundMapComponent lamp1 = new ForegroundMapComponent.ForegroundMapComponentBuilder()
            .coordinates(new Vector2(200, 1338))
            .texture(ResourceEnum.LAMP)
            .singlePieceWidth(1)
            .singlePieceHeight(2)
            .build();
    private ForegroundMapComponent palace1 = new ForegroundMapComponent.ForegroundMapComponentBuilder()
            .coordinates(new Vector2(200, 400))
            .texture(ResourceEnum.PALACE)
            .singlePieceWidth(4)
            .singlePieceHeight(4)
            .build();
    private ForegroundMapComponent palace2 = new ForegroundMapComponent.ForegroundMapComponentBuilder()
            .coordinates(new Vector2(400, 800))
            .texture(ResourceEnum.PALACE)
            .singlePieceWidth(4)
            .singlePieceHeight(4)
            .build();

    private ForegroundMapComponent longLamp = new ForegroundMapComponent.ForegroundMapComponentBuilder()
            .coordinates(new Vector2(400, 1000))
            .texture(ResourceEnum.LONG_LAMP)
            .singlePieceWidth(2)
            .singlePieceHeight(2)
            .build();

    private Reflection reflection = new Reflection.ReflectionBuilder()
        .coordinates(new Vector2(200, 1200))
        .texture(ResourceEnum.BLACKMARKETEER)
        .path(new String[]{"-"})
        .complexDialoguePath("dialogues/complexTest.json")
        .build();

    private NPC testNPC1 = new NPC.NPCBuilder()
        .coordinates(new Vector2(160, 1000))
        .texture(ResourceEnum.JERKINS)
        .path(new String[] { "DWW--ASS", "WWDSS-A" })
        .complexDialoguePath("dialogues/generics/adeptus1_JSON.json")
        .build();

    private NPC testNPC2 = new NPC.NPCBuilder()
        .coordinates(new Vector2(300, 700))
        .texture(ResourceEnum.JERKINS)
        .path(new String[] { "DWW--ASS", "WWDSS-A" })
        .complexDialoguePath("dialogues/generics/adeptus2_JSON.json")
        .build();
    

    public CityRoom1(){
        super("CITY_ROOM_1", "assets\\map\\cityRoom1\\cityRoom1.tmx");
        stage.addActor(lamp1);
        stage.addActor(longLamp);
        stage.addActor(palace1);
        stage.addActor(palace2);
        stage.addActor(reflection);
        stage.addActor(testNPC1);
        stage.addActor(testNPC2);

        stageMsg.addListener(testNPC1, MsgManager.codes.get(MsgManager.MSG.DIALOGUE_DONE));
        stageMsg.addListener(testNPC2, 0);
        stageMsg.addListener(reflection, 0);

        stage.getCamera().translate(player.getX(),player.getY(), 0);
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
