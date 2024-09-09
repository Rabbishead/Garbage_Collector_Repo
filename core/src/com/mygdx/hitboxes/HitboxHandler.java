package com.mygdx.hitboxes;

import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class HitboxHandler {
    private final ArrayList<Hitbox> hitboxes = new ArrayList<>();
    private final ArrayList<Rectangle> colliders = new ArrayList<>();
    private Rectangle player;

    public HitboxHandler(){}

    public HitboxHandler(Rectangle player){
        this.player = player;
    }

    public void registerHitbox(Hitbox h){
        hitboxes.add(h);
    }

    public void registerCollider(Rectangle r){
        colliders.add(r);
    }

    public void checkHitboxes(){
        for (Hitbox h: hitboxes) {
            for (Rectangle s: colliders) {
                h.onHit(s);
            }
        }
    }
}