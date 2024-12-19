package com.mygdx.screens.game.overworld;

import com.mygdx.entities.Player;
import com.mygdx.screens.generic.playable.PlayableScreen;

public class CityRoom3 extends PlayableScreen {
    
    public CityRoom3(){
        super("CITY_ROOM_3", "assets\\map\\cityRoom3\\cityRoom3.tmx");
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