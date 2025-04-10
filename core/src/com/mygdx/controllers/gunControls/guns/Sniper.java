package com.mygdx.controllers.gunControls.guns;

import com.mygdx.Utils;
import com.mygdx.controllers.camera.CameraController;
import com.mygdx.controllers.gunControls.GunController;
import com.mygdx.controllers.gunControls.projectiles.Projectile;
import com.mygdx.delay.DelayManager;
import com.mygdx.resources.ResourceEnum;

public class Sniper extends BaseGun {
    private int bullets = 100;

    public Sniper() {
        super(Utils.getTexture(ResourceEnum.SNIPER), 0);
        setOffset(30, 0);
        flip(true, false);
    }

    @Override
    public void onCurrent() {
        DelayManager.registerObject(this, 90);
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

    @Override
    public void onSwitched() {
        DelayManager.unregisterObject(this);
    }

    public int leftTrigger() {
        if (bullets > 0) {
            Utils.getStage().addActor(
                    new Projectile(Utils.getPlayer().center, getWidth(),
                            CameraController.getMouseAngle() + angleOffset));
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
        Utils.getStage().addActor(
                new Projectile(Utils.getPlayer().center, getWidth(), CameraController.getMouseAngle() + angleOffset));
        return 2;
    }

    public int middleTrigger() {
        return 0;
    }

    public int getBullets() {
        return bullets;
    }
}
