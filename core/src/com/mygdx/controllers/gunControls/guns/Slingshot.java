package com.mygdx.controllers.gunControls.guns;

import com.mygdx.controllers.gunControls.GunController;
import com.mygdx.controllers.gunControls.projectiles.Stone;
import com.mygdx.resources.RM;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.stage.GCStage;

public class Slingshot extends BaseGun {

    public Slingshot() {
        super(RM.get().getTexture(ResourceEnum.DEFAULT), GCStage.get().getPlayer().center, 0);
        flip(false, true);
    }

    public int leftTrigger() {
        GCStage.get().addActor(new Stone(GCStage.get().getPlayer().center, getWidth(), true));
        GunController.get().setCooldown(10);
        GunController.get().resetCooldown();
        return 1;
    }

    public int rightTrigger() {
        GCStage.get().addActor(new Stone(GCStage.get().getPlayer().center, getWidth(), true));
        GunController.get().setCooldown(20);
        return 2;
    }

    public int middleTrigger() {
        return 0;
    }
}
