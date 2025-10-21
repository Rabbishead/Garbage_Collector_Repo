package com.mygdx.gunControls.projectiles;

import com.badlogic.gdx.math.Vector2;

import com.mygdx.resources.RM;
import com.mygdx.resources.ResourceEnum;

public class Projectile extends BaseBullet {

    public Projectile(Vector2 origin, float barrel, float angle, boolean ally) {
        super(RM.get().getTexture(ResourceEnum.BULLET), origin, 1000f, 1500f, angle, ally);
        setOffset(barrel, 0, angle);
        flip(false, true);
        attachInfo(2);
        info.integerInfo.put("damage", 1);
    }
}