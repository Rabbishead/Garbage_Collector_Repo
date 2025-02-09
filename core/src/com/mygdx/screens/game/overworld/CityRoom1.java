package com.mygdx.screens.game.overworld;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.entities.ForegroundMapComponent;
import com.mygdx.entities.Player;
import com.mygdx.entities.npcs.ComplexNPC;
import com.mygdx.entities.npcs.SimpleNPC;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.screens.generic.playable.PlayableScreen;

public class CityRoom1 extends PlayableScreen {

    private ForegroundMapComponent lamp1 = new ForegroundMapComponent(new Vector2(200, 1338));

    private ComplexNPC reflection = new ComplexNPC.ComplexNPCBuilder()
        .coordinates(new Vector2(200, 1200))
        .texture(ResourceEnum.BLACKMARKETEER)
        .build();

    private SimpleNPC testNPC1 = new SimpleNPC.SimpleNPCBuilder()
        .coordinates(new Vector2(160, 1000))
        .texture(ResourceEnum.JERKINS)
        .path(new String[] { "DWW--ASS", "WWDSS-A" })
        .build();
        private SimpleNPC testNPC2 = new SimpleNPC.SimpleNPCBuilder()
        .coordinates(new Vector2(300, 700))
        .texture(ResourceEnum.BLACKMARKETEER)
        .path(new String[] { "DWW--ASS", "WWDSS-A" })
        .build();
    

    public CityRoom1(){
        super("CITY_ROOM_1", "assets\\map\\cityRoom1\\cityRoom1.tmx");
        stage.addActor(lamp1);
        stage.addActor(reflection);
        stage.addActor(testNPC1);
        stage.addActor(testNPC2);

        stageMsg.addListener(testNPC1, 0);
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
