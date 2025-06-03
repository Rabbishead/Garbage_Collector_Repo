package com.mygdx.movement;

import com.badlogic.gdx.math.Vector2;

public class BaseMovement {
    public Vector2 position = new Vector2(),
            angle = new Vector2(1, 0),
            velocity = new Vector2(),
            origin = new Vector2(),
            center = new Vector2(),
            coords = new Vector2(),
            centerCoords = new Vector2(),
            anchor = null;

    /**
     * Used for movement each frame.
     */
    public Float speed = 0f;

    /**
     * Used when un-anchored.
     */
    public Float x = 0f, y = 0f;

    public BaseMovement(Vector2 center) {
        this(center.x, center.y);
    }

    public BaseMovement(float centerX, float centerY) {
        center.x = centerX;
        center.y = centerY;
        align();
        unAnchor();
    }

    public BaseMovement() {
    }

    public void init(float speed, float angle) {
        this.angle.setAngleDeg(angle);
        this.speed = speed;
        velocity.set(speed, 0).setAngleDeg(angle);
    }

    /*
    public void link(BaseMovement bm) {
        position = bm.position;
        angle = bm.angle;
        velocity = bm.velocity;
        anchor(bm.anchor);
    }
    */

    /**
     * Align the position to be centered.
     */
    public void align() {
        position.sub(center);
    }

    /**
     * Move.
     * 
     * @return The modified position.
     */
    public Vector2 move(float delta) {
        velocity.setAngleDeg(angle.angleDeg());
        Vector2 movement = velocity.cpy().scl(delta);
        position.add(movement);
        recalcOrigin();
        return getWorldCoords();
    }

    public Vector2 mutiply(float delta) {
        velocity.setAngleDeg(angle.angleDeg());
        Vector2 movement = velocity.scl(speed).cpy().scl(delta);
        position.add(movement);
        recalcOrigin();
        return getWorldCoords();
    }

    public void offset(Vector2 offset) {
        position.add(offset);
        recalcOrigin();
    }

    public Vector2 getCenterWorldCoords() {
        Vector2 wc = getWorldCoords();
        centerCoords.set(wc.x + center.x, wc.y + center.y);
        return centerCoords;
    }

    public Vector2 getWorldCoords() {
        coords.set(anchor.x + position.x, anchor.y + position.y);
        return coords;
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
