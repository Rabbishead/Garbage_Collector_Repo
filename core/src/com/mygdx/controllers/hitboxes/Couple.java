package com.mygdx.controllers.hitboxes;

public class Couple {
    private Hitbox h;
    private Collider r;

    public Couple(Hitbox hitbox, Collider collider) {
        h = hitbox;
        r = collider;
    }

    public Hitbox getHitbox() {
        return h;
    }

    public Collider getCollider() {
        return r;
    }

    public boolean contains(Hitbox hitbox) {
        return h.equals(hitbox);
    }

    public boolean contains(Collider collider) {
        return r.equals(collider);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!obj.getClass().equals(getClass())) return false;
        Couple c = (Couple) obj;
        return h.equals(c.h) && r.equals(c.r);
    }
}
