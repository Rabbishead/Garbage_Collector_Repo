package com.mygdx.controllers.gunControls.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.Utils;
import com.mygdx.resources.ResourceEnum;

public class Projectile extends BaseBullet {

    public Projectile(Vector2 origin, float barrel, float angle) {
        super(Utils.getTexture(ResourceEnum.BULLET), origin, barrel, 1000f, 1500f, angle);
        s.rotate90(true);
        collider.rotate(-90);
        flip(false, true);
        this.debug();
    }
}