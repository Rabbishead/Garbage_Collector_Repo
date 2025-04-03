package com.mygdx.player.gunControls;

import com.mygdx.player.gunControls.guns.*;

public enum GunsEnum {
    SNIPER(new Sniper(), true);

    private final BaseGun gun;
    private boolean active;

    GunsEnum(BaseGun gun, boolean active) {
        this.gun = gun;
        this.active = active;
    }

    public BaseGun gun() {
        return gun;
    }

    public boolean isActive() {
        return active;
    }
}