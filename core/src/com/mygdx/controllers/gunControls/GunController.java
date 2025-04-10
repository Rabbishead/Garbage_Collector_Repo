package com.mygdx.controllers.gunControls;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.controllers.gunControls.guns.BaseGun;
import com.mygdx.controllers.gunControls.guns.DummyGun;
import com.mygdx.delay.DelayManager;

public class GunController extends Actor {
    private static GunController instance;
    private final ArrayList<BaseGun> gunlist;
    private int gunIndex;
    private BaseGun currentGun, dummyGun;

    public static GunController get() {
        if (instance == null)
            instance = new GunController();
        return instance;
    }

    private GunController() {
        gunlist = new ArrayList<>();
        dummyGun = new DummyGun();
        gunlist.add(dummyGun);
        DelayManager.registerObject(this, 10);
        init();
    }

    private void init() {
        gunIndex = 0;
        currentGun = gunlist.get(0);
        currentGun.onCurrent();
    }

    /**
     * Gets the guns from the GunsEnum to add them to the Gunlist.
     */
    public void loadGuns() {
        gunlist.clear();
        loadGuns(GunsEnum.values());
    }

    /**
     * Adds a chain of guns to the Gunlist.
     * 
     * @param gunEnums The GunsEnum instances to add.
     */
    public void loadGuns(GunsEnum... gunEnums) {
        gunlist.remove(dummyGun);
        for (GunsEnum gunEnum : gunEnums) {
            if (!gunEnum.isActive())
                continue;
            gunlist.add(gunEnum.gun());
        }

        init();
    }

    public void removeGun(BaseGun gun) {
        gunlist.remove(gun);

        if (gunlist.isEmpty())
            gunlist.add(dummyGun);

        init();
    }

    public void setCooldown(int cooldown) {
        DelayManager.registerObject(this, cooldown);
        DelayManager.resetDelay(this);
    }

    public void resetCooldown() {
        DelayManager.resetDelay(this);
    }

    public boolean isCurrent(BaseGun gun) {
        return gun.equals(currentGun);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        currentGun.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        DelayManager.updateDelay(this);

        BaseGun nextGun = gunlist.get(gunIndex);
        if (!isCurrent(nextGun)) {
            currentGun.onSwitched();
            currentGun = nextGun;
            currentGun.onCurrent();
            setCooldown(50);
        }
        updateGuns(delta);

        if (gunlist.isEmpty() || !DelayManager.isDelayOver(this))
            return;

        int j = 0;
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            j = currentGun.leftTrigger();
        } else if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            j = currentGun.rightTrigger();
        }
        gunIndex = (gunIndex + j) % gunlist.size();
    }

    public void updateGuns(float delta) {
        for (BaseGun gun : gunlist) {
            gun.act(delta);
        }
    }
}