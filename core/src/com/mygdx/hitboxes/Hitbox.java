package com.mygdx.hitboxes;

import com.badlogic.gdx.math.Rectangle;

import java.util.function.Consumer;

public class Hitbox extends Rectangle {
    private boolean active;
    private Consumer<Hitbox> action;

    public Hitbox(boolean active, Consumer<Hitbox> action) {
        super();
        this.active = active;
        this.action = action;
    }

    public Hitbox(float x, float y, float width, float height, boolean active, Consumer<Hitbox> action) {
        super(x, y, width, height);
        this.active = active;
        this.action = action;
    }

    public void onHit(Collider r) {
        if (!active || !this.overlaps(r))
            return;
        System.out.println("accepting action");
        action.accept(this);
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setAction(Consumer<Hitbox> action) {
        this.action = action;
    }

    public boolean isActive() {
        return active;
    }

    public Consumer<Hitbox> getAction() {
        return action;
    }
}
