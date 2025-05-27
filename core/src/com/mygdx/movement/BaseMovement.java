package com.mygdx.movement;

import com.badlogic.gdx.math.Vector2;

public class BaseMovement {
    public Vector2 position = new Vector2(),
            angle = new Vector2(1, 0),
            speed = new Vector2(),
            origin = new Vector2(),
            center = new Vector2(),
            anchor = null;

    /**
     * Used for movement each frame.
     */
    public float distance = 0;

    /**
     * Used when un-anchored.
     */
    public float x = 0, y = 0;

    public BaseMovement(Vector2 center) {
        this(center.x, center.y);
    }

    public BaseMovement(float centerX, float centerY) {
        center.x = centerX;
        center.y = centerY;
        align();
    }

    public BaseMovement() {
    }

    /**
     * Align the position to be centered.
     */
    public void align() {
        position.sub(center);
    }

    /**
     * Move
     * 
     * @return
     */
    public Vector2 move(float delta) {
        speed.setAngleDeg(angle.angleDeg());
        Vector2 movement = speed.cpy().scl(delta);
        position.add(movement);
        recalcOrigin();
        return position;
    }

    public Vector2 mutiply(float delta) {
        speed.setAngleDeg(angle.angleDeg());
        Vector2 movement = speed.scl(distance).cpy().scl(delta);
        position.add(movement);
        recalcOrigin();
        return position;
    }

    public void offset() {
        position.add(speed);
        recalcOrigin();
    }

    public void offset(Vector2 offset) {
        position.add(offset);
        recalcOrigin();
    }

    public Vector2 getCenterWorldCoords() {
        return new Vector2(anchor.x + position.x + center.x, anchor.y + position.y + center.y);
    }

    public Vector2 getWorldCoords() {
        return new Vector2(anchor.x + position.x, anchor.y + position.y);
    }

    /**
     * Anchors this movement to an anchor's world position.
     * 
     * @param anchor The anchor's world position.
     */
    public void anchor(Vector2 anchor) {
        this.anchor = anchor;
        recalcOrigin();
    }

    public void recalcOrigin() {
        if (anchor == null)
            return;

        origin.set(-position.x, -position.y);
    }

    public void unAnchor() {
        this.origin.set(center);
        this.anchor = null;
    }
}
