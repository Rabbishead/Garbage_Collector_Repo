package com.mygdx.hitboxes;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;

import java.util.function.BiConsumer;

public class Hitbox extends Polygon {
    private boolean active;
    private String[] tags;
    private BiConsumer<Hitbox, Collider> onHit;

    public Hitbox(float x, float y, float width, float height, int degrees, boolean active, String tags,
            BiConsumer<Hitbox, Collider> onHit) {
        super(new float[] { 0, 0, width, 0, width, height, 0, height });
        this.setPosition(x, y);
        this.setOrigin(width / 2, height / 2);
        this.setRotation(degrees);
        this.active = active;
        this.onHit = onHit;
        this.tags = tags.split(",");
    }

    public Hitbox(float x, float y, float width, float height, int degrees, boolean active,
            BiConsumer<Hitbox, Collider> onHit) {
        this(x, y, width, height, degrees, active, "all", onHit);
    }

    public Hitbox(boolean active, BiConsumer<Hitbox, Collider> onHit) {
        this(0, 0, 1, 1, 0, active, onHit);
    }

    public Hitbox(String tag) {
        this(0, 0, 1, 1, 0, true, tag, null);
    }

    public void onHit(Collider r) {
        if (!active)
            return;
        boolean collision = Intersector.overlapConvexPolygons(this, r);
        r.setCollided(collision);
        if (!collision)
            return;
        onHit.accept(this, r);
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

    public BiConsumer<Hitbox, Collider> getOnHit() {
        return onHit;
    }

    public void setOnHit(BiConsumer<Hitbox, Collider> onHit) {
        this.onHit = onHit;
    }
}
