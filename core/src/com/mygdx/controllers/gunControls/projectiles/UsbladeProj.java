package com.mygdx.controllers.gunControls.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.Utils;
import com.mygdx.resources.ResourceEnum;

public class UsbladeProj extends BaseMelee {

    public UsbladeProj(Vector2 origin, float barrel, float angle, boolean direction) {
        super(Utils.getTexture(ResourceEnum.USBLADE), origin, angle, 90, 100, direction);
        setOffset(barrel, 0);
    }
}
