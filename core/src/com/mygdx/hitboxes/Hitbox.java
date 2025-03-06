package com.mygdx.hitboxes;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.mygdx.Utils;

import java.util.function.BiConsumer;

public class Hitbox extends Polygon {
    private boolean active;
    private String[] tags;
    private String stringTags;
    private BiConsumer<Hitbox, Collider> onHit;
    private BiConsumer<Hitbox, Collider> onLeave;
    public final boolean isNull;

    public Hitbox(float x, float y, float width, float height, int degrees, boolean active, String tags) {
        super(new float[] { 0, 0, width, 0, width, height, 0, height });
        this.setPosition(x, y);
        this.setOrigin(width / 2, height / 2);
        this.setRotation(degrees);
        this.active = active;
        this.tags = tags.split(",");
        this.stringTags = tags;
        isNull = false;
    }

    public Hitbox(float x, float y, float width, float height, int degrees, boolean active) {
        this(x, y, width, height, degrees, active, "all");
    }

    public Hitbox() {
        super();
        isNull = true;
    }

    public boolean isHit(Collider r, boolean activate) {
        if (!active)
            return false;
        boolean collision = Intersector.overlapConvexPolygons(this, r);
        r.setCollided(collision);
        if (!collision) {
            Utils.getHitboxHandler().removeContact(r, this);
            return false;
        }
        if (activate) {
            Utils.getHitboxHandler().storeContact(r, this);
            r.register(this);
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

    public boolean containsTag(String tag) {
        return stringTags.contains(tag);
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

    /***
     * Register method to add the HitBox to the Event handler with a check.
     * 
     * @return {@code true} if the HitBox has been added.
     */
    public boolean register() {
        if (isNull)
            return false;
        Utils.getHitboxHandler().registerHitbox(this);
        return true;
    }

    /***
     * Register method to remove the HitBox to the Event handler with a check.
     * 
     * @return {@code true} if the HitBox has been removed.
     */
    public boolean unregister() {
        if (isNull)
            return false;
        Utils.getHitboxHandler().unRegisterHitbox(this);
        return true;
    }
}
