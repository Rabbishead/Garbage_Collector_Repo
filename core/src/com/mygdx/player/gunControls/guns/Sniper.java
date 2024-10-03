package com.mygdx.player.gunControls.guns;

import com.mygdx.Utils;
import com.mygdx.player.gunControls.GunController;
import com.mygdx.player.gunControls.projectiles.Bullet;

public class Sniper extends Gun {
    
    public Sniper() {
        super();
    }

    public int leftTrigger() {
        Utils.getStage().addActor(new Bullet(Utils.player.center.x, Utils.player.center.y));
        GunController.get().setCooldown(30);
        GunController.get().resetCooldown();
        return 1;
    }
    
    public int rightTrigger() {
        Utils.getStage().addActor(new Bullet(Utils.player.center.x, Utils.player.center.y));
        GunController.get().setCooldown(50);
        return 2;
    }

    public int middleTrigger() {
        return 0;
    }
}
