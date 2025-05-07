package com.mygdx.screens.game.overworld;

import java.util.Collections;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.Data;
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
                        .path(new String[] { "-" })
                        .story(ResourceEnum.ADEPTUS_1)
                        .build(),
                new NPC.NPCBuilder()
                        .coordinates(new Vector2(Data.TILE * 50, Data.TILE * 15))
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