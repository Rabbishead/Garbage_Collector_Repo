package com.mygdx.controllers.gunControls.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.Utils;
import com.mygdx.resources.ResourceEnum;

public class UsbladeProj extends BaseBullet {
    float endpoint;
    float reduction;
    boolean direction;

    public UsbladeProj(Vector2 origin, float barrel, float angle, boolean direction) {
        super(Utils.getTexture(ResourceEnum.USBLADE), origin, barrel, 0, 0, angle);
        this.direction = direction;

        setOrigin(-getOriginX(), -getOriginY());
        System.out.println("Origine: " + origin.x + " " + origin.y);
        System.out.println("xy: " + getX() + " " + getY());
        System.out.println("calculations: " + (-((origin.x-getX())-getWidth()/2)) + " " + (-((origin.y-getY())-getHeight()/2)));

        s.setOrigin(origin.x-getX(), origin.y-getY());
        endpoint = direction ? angle - 45 : angle + 45;
        reduction = direction ? -1 : 1;
    }

    @Override
    public void act(float delta) {
        s.rotate(reduction);
        collider.rotate(reduction);

        if (collider.isCollided())
            delete();
        if (direction) {
            if (s.getRotation() <= endpoint)
                delete();
        } else {
            if (s.getRotation() >= endpoint)
                delete();
        }
    }

}
