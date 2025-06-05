package com.mygdx.controllers.gunControls.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.Utils;
import com.mygdx.resources.ResourceEnum;

public class UsbladeProj extends BaseMelee {

    public UsbladeProj(Vector2 origin, float barrel, float angle, boolean direction) {
        super(Utils.getTexture(ResourceEnum.USBLADE), origin, angle, 90, 500, direction);
        setOffset(barrel, 0, 0);
        attachInfo(2);
        info.integerInfo.put("damage", 1);
    }
}
