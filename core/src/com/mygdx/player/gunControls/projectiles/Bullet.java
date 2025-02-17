package com.mygdx.player.gunControls.projectiles;

import com.mygdx.Utils;
import com.mygdx.resources.ResourceEnum;

public class Bullet extends Projectile {

    public Bullet(float barrel, float angle) {
        super(Utils.getTexture(ResourceEnum.BULLET), barrel, 1000f, 1500f, angle);
        this.debug();
    }
}