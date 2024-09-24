package com.mygdx.hitboxes;

import java.util.concurrent.CopyOnWriteArrayList;

import com.mygdx.Utils;

public class HitboxHandler {

    private final CopyOnWriteArrayList<Hitbox> hitboxes = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Collider> colliders = new CopyOnWriteArrayList<>();

    public HitboxHandler() {
        Utils.setHitboxHandler(this);
    }

    public void registerHitbox(Hitbox h) {
        hitboxes.add(h);
    }

    public void registerCollider(Collider r) {
        colliders.add(r);
    }

    public void unRegisterHitbox(Hitbox h) {
        hitboxes.remove(h);
    }

    public void unRegisterCollider(Collider r) {
        colliders.remove(r);
    }

    public void checkHitboxes() {
        for (Hitbox h : hitboxes) {
            for (Collider s : colliders) {
                h.onHit(s);
            }
        }
    }
}