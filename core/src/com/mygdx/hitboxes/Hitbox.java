package com.mygdx.hitboxes;

import com.badlogic.gdx.math.Rectangle;

import java.util.function.BiConsumer;

public class Hitbox extends Rectangle {
    private boolean active;
    private BiConsumer<Hitbox, Collider> onHit;

    public Hitbox(boolean active, BiConsumer<Hitbox, Collider> onHit) {
        super();
        this.active = active;
        this.onHit = onHit;
    }

    public Hitbox(float x, float y, float width, float height, boolean active, BiConsumer<Hitbox, Collider> onHit) {
        super(x, y, width, height);
        this.active = active;
        this.onHit = onHit;
    }

    public void onHit(Collider r) {
        if (!active || !this.overlaps(r))
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
