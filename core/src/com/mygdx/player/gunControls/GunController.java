package com.mygdx.player.gunControls;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.delay.DelayManager;
import com.mygdx.player.gunControls.guns.Gun;

public class GunController extends Actor {
    private static GunController instance;
    private final ArrayList<Gun> guns;
    private int gunIndex;
    private Gun currentGun;

    public static GunController get() {
        if (instance == null)
            instance = new GunController();
        return instance;
    }

    private GunController() {
        guns = new ArrayList<>();
        gunIndex = 0;
        DelayManager.registerObject(this, 10);
    }

    /**
     * Adds a chain of guns to the Gunlist.
     * 
     * @param guns The guns instances to add.
     */
    public void loadGuns(Gun ...guns) {
        for (Gun gun : guns) {
            this.guns.add(gun);
        }
    }

    public void removeGun(Gun gun) {
        guns.remove(gun);
    }

    public void setCooldown(int cooldown) {
        DelayManager.registerObject(this, cooldown);
    }

    public void resetCooldown() {
        DelayManager.resetDelay(this);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        currentGun.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        DelayManager.updateDelay(this);
        currentGun = guns.get(gunIndex);
        currentGun.act(delta);
        if (guns.isEmpty() || !DelayManager.isDelayOver(this))
            return;
        int j = 0;
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            j = currentGun.leftTrigger();
        } else if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            j = currentGun.rightTrigger();
        }
        gunIndex = (gunIndex + j) % guns.size();
    }
}