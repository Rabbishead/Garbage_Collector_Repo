package com.mygdx.controllers.gunControls.guns;

import com.mygdx.Utils;
import com.mygdx.controllers.gunControls.GunController;
import com.mygdx.controllers.gunControls.projectiles.Stone;
import com.mygdx.entities.Player;
import com.mygdx.resources.ResourceEnum;

public class Slingshot extends BaseGun {

    public Slingshot() {
        super(Utils.getTexture(ResourceEnum.DEFAULT), Player.center, 0);
        flip(false, true);
    }

    public int leftTrigger() {
        Utils.getStage().addActor(new Stone(Player.center, getWidth(), true));
        GunController.get().setCooldown(10);
        GunController.get().resetCooldown();
        return 1;
    }

    public int rightTrigger() {
        Utils.getStage().addActor(new Stone(Player.center, getWidth(), true));
        GunController.get().setCooldown(20);
        return 2;
    }

    public int middleTrigger() {
        return 0;
    }
}
