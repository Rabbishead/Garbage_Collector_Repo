package com.mygdx.hitboxes;

import com.badlogic.gdx.math.Rectangle;

import java.util.concurrent.*;

public class HitboxHandler {
    
    private final CopyOnWriteArrayList<Hitbox> hitboxes = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Collider> colliders = new CopyOnWriteArrayList<>();
    private Rectangle player;

    public HitboxHandler() {
    }

    public HitboxHandler(Rectangle player) {
        this.player = player;
    }

    public void registerHitbox(Hitbox h) {
        hitboxes.add(h);
    }

    public void registerCollider(Collider r){
        colliders.add(r);
    }

    public void unRegisterHitbox(Hitbox h) {
        hitboxes.remove(h);
    }

    public void unRegisterCollider(Collider r){
        colliders.remove(r);
    }

    public void checkHitboxes(){
        for (Hitbox h: hitboxes) {
            for (Collider s: colliders) {
                h.onHit(s);
            }
        }
    }
}