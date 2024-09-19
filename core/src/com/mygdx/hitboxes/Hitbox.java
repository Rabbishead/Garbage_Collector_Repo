package com.mygdx.hitboxes;

import com.badlogic.gdx.math.Rectangle;

import java.util.function.BiConsumer;

public class Hitbox extends Rectangle {
    private boolean active;
    private BiConsumer<Hitbox, Collider> action;

    public Hitbox(boolean active, BiConsumer<Hitbox, Collider> action) {
        super();
        this.active = active;
        this.action = action;
    }

    public Hitbox(float x, float y, float width, float height, boolean active, BiConsumer<Hitbox, Collider> action) {
        super(x, y, width, height);
        this.active = active;
        this.action = action;
    }

    public void onHit(Collider r) {
        if (!active || !this.overlaps(r))
            return;
        System.out.println("accepting action");
        action.accept(this, r);
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setAction(BiConsumer<Hitbox, Collider> action) {
        this.action = action;
    }

    public boolean isActive() {
        return active;
    }

    public BiConsumer<Hitbox, Collider> getAction() {
        return action;
    }
}
