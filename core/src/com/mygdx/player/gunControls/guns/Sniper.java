package com.mygdx.player.gunControls.guns;

import com.mygdx.Utils;
import com.mygdx.player.camera.CameraController;
import com.mygdx.player.gunControls.GunController;
import com.mygdx.player.gunControls.projectiles.Bullet;
import com.mygdx.resources.ResourceEnum;

public class Sniper extends Gun {
    
    public Sniper() {
        super(Utils.getTexture(ResourceEnum.DEFAULT), 0, 30);
    }

    public int leftTrigger() {
        Utils.getStage().addActor(new Bullet(Utils.getPlayer().center.x, Utils.getPlayer().center.y));
        CameraController.applyShakeEffect();
        GunController.get().setCooldown(3);
        GunController.get().resetCooldown();
        return 1;
    }
    
    public int rightTrigger() {
        Utils.getStage().addActor(new Bullet(Utils.getPlayer().center.x, Utils.getPlayer().center.y));
        GunController.get().setCooldown(50);
        return 2;
    }

    public int middleTrigger() {
        return 0;
    }
}
