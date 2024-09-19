package com.mygdx.hitboxes;

import com.badlogic.gdx.math.Rectangle;

import java.util.function.BiConsumer;

public class Hitbox extends Rectangle {
    private boolean active;
    private boolean touched = false;
    private BiConsumer<Hitbox, Collider> onHit;
    private BiConsumer<Hitbox, Collider> onLeave;

    public Hitbox(boolean active, BiConsumer<Hitbox, Collider> onHit, BiConsumer<Hitbox, Collider> onLeave) {
        super();
        this.active = active;
        this.onHit = onHit;
        this.onLeave = onLeave;
    }

    public Hitbox(float x, float y, float width, float height, boolean active, BiConsumer<Hitbox, Collider> onHit, BiConsumer<Hitbox, Collider> onLeave) {
        super(x, y, width, height);
        this.active = active;
        this.onHit = onHit;
        this.onLeave = onLeave;
    }

    public void onHit(Collider r) {
        if (!active || !this.overlaps(r)) {
            if (!touched)
                return;
            onLeave.accept(this, r);
            touched = false;
        }
        onHit.accept(this, r);
        touched = true;
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
