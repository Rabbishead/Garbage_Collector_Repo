package com.mygdx.controllers.gunControls.guns;

import com.mygdx.Utils;
import com.mygdx.controllers.camera.CameraController;
import com.mygdx.controllers.delay.DelayManager;
import com.mygdx.controllers.gunControls.GunController;
import com.mygdx.controllers.gunControls.projectiles.Projectile;
import com.mygdx.entities.Player;
import com.mygdx.resources.ResourceEnum;

public class Sniper extends BaseGun {
    private int bullets = 100;

    public Sniper() {
        super(Utils.getTexture(ResourceEnum.SNIPER), Player.center, 0);
        setOffset(30, 0, 0);
        flip(true, false);
        DelayManager.registerObject(this, 90f);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        DelayManager.updateDelay(this);
        Utils.setDebugString(bullets + "");

        if (GunController.get().isCurrent(this) && !DelayManager.isDelayOver(this))
            return;
        if (bullets < 100)
            bullets += 1;
    }

    public int leftTrigger() {
        if (bullets > 0) {
            super.leftTrigger();
            Projectile proj = new Projectile(Player.center, getWidth(), CameraController.getMouseAngle() + angleOffset);
            Utils.getStage().addActor(proj);
            bullets -= 1;

            CameraController.applyShakeEffect();
            GunController.get().setCooldown(3);
            DelayManager.resetDelay(this);

            return 0;
        } else {
            return 1;
        }
    }

    public int rightTrigger() {
        super.rightTrigger();
        Projectile proj = new Projectile(Player.center, getWidth(), CameraController.getMouseAngle() + angleOffset);
        Utils.getStage().addActor(proj);
        return 2;
    }

    public int middleTrigger() {
        super.middleTrigger();
        return 0;
    }

    public int getBullets() {
        return bullets;
    }

    @Override
    public void destroy() {
        DelayManager.unregisterObject(this);
    }
}
