package com.mygdx.screens.game.overworld;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.entities.ForegroundMapComponent;
import com.mygdx.entities.Player;
import com.mygdx.entities.npcs.NPC;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.screens.generic.playable.PlayableScreen;

public class CityRoom2 extends PlayableScreen {

    private ForegroundMapComponent lamp1 = new ForegroundMapComponent.ForegroundMapComponentBuilder()
            .coordinates(new Vector2(200, 1338))
            .texture(ResourceEnum.LAMP)
            .singlePieceWidth(4)
            .singlePieceHeight(4)
            .build();

    private NPC reflection = new NPC.NPCBuilder()
            .coordinates(new Vector2(100, 1200))
            .texture(ResourceEnum.PLAYER)
            .path(new String[] { "-" })
            .complexDialoguePath("dialogues/complexTest.json")
            .build();

    public CityRoom2() {
        super("CITY_ROOM_2", "assets\\map\\cityRoom2\\cityRoom2.tmx");
        stage.addActor(lamp1);
        stage.addActor(reflection);

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