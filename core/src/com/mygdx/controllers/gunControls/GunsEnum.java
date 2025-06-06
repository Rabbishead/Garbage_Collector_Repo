package com.mygdx.controllers.gunControls;

import com.mygdx.controllers.gunControls.guns.*;

public enum GunsEnum {
    CHAINGUN(new ChainGun(), true),
    USBLADE(new Usblade(), true);

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