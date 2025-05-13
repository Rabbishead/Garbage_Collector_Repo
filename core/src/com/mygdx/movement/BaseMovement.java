package com.mygdx.movement;

import com.badlogic.gdx.math.Vector2;

public class BaseMovement {
    public Vector2 anchor = new Vector2(),
            origin = new Vector2(),
            center = new Vector2(),
            angle = new Vector2(1, 0),
            movement = new Vector2(),
            position = new Vector2();
    public float distance = 0;

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

    public void scaleByDelta(float delta) {
        movement.scl(delta);
    }

    public void center() {
        position.sub(origin);
    }

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
        return new Vector2(position.x + origin.x, position.y + origin.y);
    }

    public Vector2 getWorldCoords() {
        return new Vector2(position.x + origin.x, position.y + origin.y);
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
        Vector2 worldPos = getCenterWorldCoords();
        origin.set(anchor.x - worldPos.x, anchor.y - worldPos.y);
    }

    public void unAnchor(Vector2 origin) {
        this.origin = origin;
        this.anchor = getCenterWorldCoords().add(origin);
    }
}
