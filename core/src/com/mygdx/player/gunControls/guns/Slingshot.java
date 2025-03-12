package com.mygdx.player.gunControls.guns;

import com.mygdx.Utils;
import com.mygdx.player.gunControls.GunController;
import com.mygdx.player.gunControls.projectiles.Stone;
import com.mygdx.resources.ResourceEnum;

public class Slingshot extends Gun {

    public Slingshot() {
        super(Utils.getTexture(ResourceEnum.DEFAULT), 0);
        flip(false, true);
    }

    public int leftTrigger() {
        Utils.getStage().addActor(new Stone(Utils.getPlayer().center, getWidth()));
        GunController.get().setCooldown(10);
        GunController.get().resetCooldown();
        return 1;
    }

    public int rightTrigger() {
        Utils.getStage().addActor(new Stone(Utils.getPlayer().center, getWidth()));
        GunController.get().setCooldown(20);
        return 2;
    }

    public int middleTrigger() {
        return 0;
    }
}
