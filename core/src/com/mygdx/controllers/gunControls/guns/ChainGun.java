package com.mygdx.controllers.gunControls.guns;

import com.mygdx.Utils;
import com.mygdx.controllers.camera.CameraController;
import com.mygdx.controllers.delay.DelayManager;
import com.mygdx.controllers.gunControls.GunController;
import com.mygdx.controllers.gunControls.projectiles.Projectile;
import com.mygdx.entities.Player;
import com.mygdx.resources.ResourceEnum;

public class ChainGun extends BaseGun {
    private int bullets = 100, mag = 100, reloadAmount = 1;
    private float waitingTime = 90f, timeOn = 10f, timeOff = 3f, cooldown = 3f;
    private Object reload = new Object();

    /**
     * ChainGun class
     */
    public ChainGun() {
        super(Utils.getTexture(ResourceEnum.CHAINGUN), Player.center, 0);
        setOffset(30, 0, 0);
        flip(true, false);

        // waitingTime = time initiate reload
        DelayManager.registerObject(this, waitingTime);
    }

    @Override
    public void onCurrent() {
        super.onCurrent();

        // timeOn = time for bullet while having chainGun as current
        DelayManager.registerObject(reload, timeOn);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        DelayManager.updateDelay(this);
        DelayManager.updateDelay(reload);
        Utils.setDebugString(bullets + "");

        if (!DelayManager.isDelayOver(this))
            return;

        if (!DelayManager.isDelayOver(reload))
            return;

        if (bullets < mag) {
            bullets += reloadAmount;
            DelayManager.resetDelay(reload);
        }
    }

    @Override
    public void onSwitched() {
        super.onSwitched();

        // timeOn = time for bullet while having chainGun in background
        DelayManager.registerObject(reload, timeOff);
    }

    public int leftTrigger() {
        super.leftTrigger();
        Projectile proj = new Projectile(Player.center, getWidth(), CameraController.getMouseAngle() + angleOffset, true);
        Utils.getStage().addActor(proj);
        bullets -= 1;

        CameraController.applyShakeEffect();

        // cooldown = cooldown between projectiles
        GunController.get().setCooldown(cooldown);
        DelayManager.resetDelay(this);

        return bullets > 0 ? 0 : 1;
    }

    public int rightTrigger() {
        super.rightTrigger();
        Projectile proj = new Projectile(Player.center, getWidth(), CameraController.getMouseAngle() + angleOffset, true);
        Utils.getStage().addActor(proj);

        GunController.get().setCooldown(50);
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
