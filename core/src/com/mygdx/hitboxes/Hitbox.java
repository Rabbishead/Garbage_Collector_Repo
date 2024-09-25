package com.mygdx.hitboxes;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;

import java.util.function.BiConsumer;

public class Hitbox extends Polygon {
    private boolean active;
    private BiConsumer<Hitbox, Collider> onHit;

    public Hitbox(boolean active, BiConsumer<Hitbox, Collider> onHit) {
        super();
        this.active = active;
        this.onHit = onHit;
    }

    public Hitbox(float x, float y, float width, float height, int degrees, boolean active,
            BiConsumer<Hitbox, Collider> onHit) {
        super(new float[] { 0, 0, width, 0, width, height, 0, height });
        this.setPosition(x, y);
        this.setOrigin(width / 2, height / 2);
        this.setRotation(degrees);
        this.active = active;
        this.onHit = onHit;
    }

    public void onHit(Collider r) {
        if (!active)
            return;
        boolean collision = Intersector.overlapConvexPolygons(this, r);
        r.setCollided(collision);
        if (!collision)
            return;
        onHit.accept(this, r);
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setOnHit(BiConsumer<Hitbox, Collider> action) {
        this.onHit = action;
    }

    public boolean isActive() {
        return active;
    }

    public BiConsumer<Hitbox, Collider> getOnHit() {
        return onHit;
    }
}
