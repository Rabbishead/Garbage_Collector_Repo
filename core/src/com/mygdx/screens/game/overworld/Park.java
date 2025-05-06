package com.mygdx.screens.game.overworld;

import java.util.Collections;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.Data;
import com.mygdx.entities.ForegroundMapComponent;
import com.mygdx.entities.Player;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.screens.generic.playable.PlayableScreen;

public class Park extends PlayableScreen {

    public Park() {
        super(ResourceEnum.PARK);

        Collections.addAll(mapComponents,
                new ForegroundMapComponent.ForegroundMapComponentBuilder()
                        .coordinates(new Vector2(Data.TILE * 50, Data.TILE * 50))
                        .texture(ResourceEnum.MARMOT_PIZZA)
                        .singlePieceWidth(3)
                        .singlePieceHeight(3)
                        .animationRate(0.5f)
                        .delay(100)
                        .build(),

                        new ForegroundMapComponent.ForegroundMapComponentBuilder()
                        .coordinates(new Vector2(Data.TILE * 25, Data.TILE * 25))
                        .texture(ResourceEnum.SPARK_BUILDING_2)
                        .singlePieceWidth(5)
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