package com.mygdx.player.gunControls.guns;

import com.mygdx.Utils;
import com.mygdx.player.gunControls.GunController;
import com.mygdx.player.gunControls.projectiles.Bullet;

public class Sniper extends Gun {
    
    public Sniper() {
        super();
    }

    public int leftTrigger() {
        Utils.getStage().addActor(new Bullet(Utils.player.getOriginX() + Utils.player.getX(), Utils.player.getOriginY() + Utils.player.getY()));
        GunController.get().setCooldown(30);
        GunController.get().resetCooldown();
        return 1;
    }
    
    public int rightTrigger() {
        Utils.getStage().addActor(new Bullet(Utils.player.getOriginX() + Utils.player.getX(), Utils.player.getOriginY() + Utils.player.getY()));
        GunController.get().setCooldown(50);
        return 2;
    }

    public int middleTrigger() {
        return 0;
    }
}
