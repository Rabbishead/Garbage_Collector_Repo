package com.mygdx.controllers.gunControls;

import com.mygdx.controllers.gunControls.guns.*;
import com.mygdx.savings.SavingsManager;

public enum GunsEnum {
    CHAINGUN(new ChainGun(), SavingsManager.getSavings().getSelectedGun(0)),
    USBLADE(new Usblade(), SavingsManager.getSavings().getSelectedGun(1));

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