package com.mygdx.hitboxes;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;

import java.util.function.BiConsumer;

public class Hitbox extends Polygon {
    private boolean active;
    private String[] tags;
    private BiConsumer<Hitbox, Collider> onHit;
    private BiConsumer<Hitbox, Collider> onLeave;

    public Hitbox(float x, float y, float width, float height, int degrees, boolean active, String tags) {
        super(new float[] { 0, 0, width, 0, width, height, 0, height });
        this.setPosition(x, y);
        this.setOrigin(width / 2, height / 2);
        this.setRotation(degrees);
        this.active = active;
        this.tags = tags.split(",");
    }

    public Hitbox(float x, float y, float width, float height, int degrees, boolean active) {
        this(x, y, width, height, degrees, active, "all");
    }

    public Hitbox() {
        super();
    }

    public boolean isHit(Collider r, boolean activate) {
        if (!active)
            return false;
        boolean collision = Intersector.overlapConvexPolygons(this, r);
        r.setCollided(collision);
        if (!collision)
            return false;
        if (activate) {
            onHit(r);
            r.onHit(this);
        }
        return true;
    }

    public void onHit(Collider r) {
        if (onHit != null)
            onHit.accept(this, r);
    }

    public void onLeave(Collider r) {
        if (onLeave != null)
            onLeave.accept(this, r);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags.split(",");
    }

    public void setOnHit(BiConsumer<Hitbox, Collider> onHit) {
        this.onHit = onHit;
    }

    public void setOnLeave(BiConsumer<Hitbox, Collider> onLeave) {
        this.onLeave = onLeave;
    }
}
