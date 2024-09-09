package com.mygdx.hitboxes;

import com.badlogic.gdx.math.Rectangle;

import java.util.function.Consumer;

public class Hitbox extends Rectangle {
    private boolean active;
    private Consumer<Hitbox> action;

    public Hitbox(boolean active, Consumer<Hitbox> action){
        this.active = active;
        this.action = action;
    }

    public void onHit(Rectangle r){
        if (!active || !this.overlaps(r)) return;
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
