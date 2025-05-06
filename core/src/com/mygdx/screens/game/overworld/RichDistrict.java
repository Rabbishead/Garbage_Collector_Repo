package com.mygdx.screens.game.overworld;

import com.mygdx.entities.Player;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.screens.generic.playable.PlayableScreen;

public class RichDistrict extends PlayableScreen {

    public RichDistrict() {
        super(ResourceEnum.RICH_DISTRICT);
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