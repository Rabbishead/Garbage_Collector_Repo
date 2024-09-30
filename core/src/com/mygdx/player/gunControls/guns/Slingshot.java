package com.mygdx.player.gunControls.guns;

import com.mygdx.Utils;
import com.mygdx.player.gunControls.GunController;
import com.mygdx.player.gunControls.projectiles.Stone;

public class Slingshot extends Gun {

    public Slingshot() {
        super();
    }

    public int leftTrigger() {
        Utils.getStage().addActor(new Stone(Utils.player.getOriginX() + Utils.player.getX(), Utils.player.getOriginY() + Utils.player.getY()));
        GunController.get().setCooldown(10);
        GunController.get().resetCooldown();
        return 1;
    }
    
    public int rightTrigger() {
        Utils.getStage().addActor(new Stone(Utils.player.getOriginX() + Utils.player.getX(), Utils.player.getOriginY() + Utils.player.getY()));
        GunController.get().setCooldown(20);
        return 2;
    }

    public int middleTrigger() {
        return 0;
    }
}
