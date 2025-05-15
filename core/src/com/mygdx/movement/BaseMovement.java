package com.mygdx.movement;

import com.badlogic.gdx.math.Vector2;

public class BaseMovement {
    public Vector2 position = new Vector2(),
            angle = new Vector2(1, 0),
            movement = new Vector2(),
            origin = new Vector2(),
            center = new Vector2(),
            anchor = new Vector2();
    
    /**
     * Used for movement each frame.
     */
    public float distance = 0;

    /**
     * Used when un-anchored.
     */
    public float x = 0, y = 0;

    public BaseMovement(Vector2 anchor, Vector2 origin, Vector2 center, Vector2 position, float distance, float angle) {
        this.origin = origin;
        this.center = center;
        this.distance = distance;
        this.position = position;
        anchor(anchor);
        this.angle.setAngleDeg(angle);

        movement.set(distance, 0).setAngleDeg(angle);
    }

    public BaseMovement() {
    }

    /**
     * Scales the movement by delta, to do once.
     * @param delta
     */
    public void scaleByDelta(float delta) {
        movement.scl(delta);
    }

    /**
     * Align the position to be centered
     */
    public void align() {
        position.sub(center);
    }

    /**
     * Move
     * @return
     */
    public Vector2 move() {
        movement.setAngleDeg(angle.angleDeg());
        return position.add(movement);
    }

    public Vector2 mutiply(float delta) {
        movement.setAngleDeg(angle.angleDeg());
        movement.scl(distance).scl(delta);
        return position.add(movement);
    }

    public void offset() {
        position.add(movement);
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
        return getCenterWorldCoords().sub(center);
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
        Vector2 cenWorldPos = getCenterWorldCoords();
        origin.set(anchor.x - cenWorldPos.x, anchor.y - cenWorldPos.y);
    }

    public void unAnchor(Vector2 origin) {
        this.origin = origin;
        this.anchor = getCenterWorldCoords().add(origin);
    }
}
