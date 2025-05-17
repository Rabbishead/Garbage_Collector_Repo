package com.mygdx.controllers.gunControls.guns;

import com.mygdx.Utils;
import com.mygdx.controllers.camera.CameraController;
import com.mygdx.controllers.gunControls.GunController;
import com.mygdx.controllers.gunControls.projectiles.UsbladeProj;
import com.mygdx.entities.Player;
import com.mygdx.resources.ResourceEnum;

public class Usblade extends BaseGun {
    public Usblade() {
        super(Utils.getTexture(ResourceEnum.USBLADE), Player.center, 45);
        setOffset(30, 0);
        // flip(true, false);
    }

    public int leftTrigger() {
        super.leftTrigger();
        Utils.getStage().addActor(new UsbladeProj(
                Player.center,
                getWidth(),
                CameraController.getMouseAngle() + angleOffset,
                flipped));

        GunController.get().setCooldown(50);
        return 0;
    }

}
