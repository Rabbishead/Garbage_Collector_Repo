package com.mygdx.player.gunControls.guns;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.Utils;
import com.mygdx.player.gunControls.GunController;
import com.mygdx.player.gunControls.projectiles.Stone;

public class Slingshot extends Gun {
    Actor player;

    public Slingshot() {
        super();
        player = Utils.player;
    }

    public int leftTrigger() {
        Utils.getStage().addActor(new Stone(player.getOriginX() + player.getX(), player.getOriginY() + player.getY()));
        GunController.get().setCooldown(10);
        GunController.get().resetCooldown();
        return 1;
    }
    
    public int rightTrigger() {
        Utils.getStage().addActor(new Stone(player.getOriginX() + player.getX(), player.getOriginY() + player.getY()));
        GunController.get().setCooldown(20);
        return 2;
    }

    public int middleTrigger() {
        return 0;
    }
}
