package com.mygdx.controllers.gunControls.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.Utils;
import com.mygdx.resources.ResourceEnum;

public class Projectile extends BaseBullet {

    public Projectile(Vector2 origin, float barrel, float angle) {
        super(Utils.getTexture(ResourceEnum.BULLET), origin, 1000f, 1500f, angle);
        setOffset(barrel, 0, angle);
        flip(false, true);
        attachInfo(2);
        info.integerInfo.put("damage", 1);
    }
}