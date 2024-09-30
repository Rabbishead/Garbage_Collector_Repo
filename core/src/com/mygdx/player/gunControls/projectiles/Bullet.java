package com.mygdx.player.gunControls.projectiles;

import com.mygdx.Utils;
import com.mygdx.resources.ResourceEnum;

public class Bullet extends Projectile {

    public Bullet(float nozzleX, float nozzleY) {
        super(Utils.getTexture(ResourceEnum.BULLET), 6, 10, nozzleX, nozzleY, 1000f, 90);
        this.debug();
    }
}