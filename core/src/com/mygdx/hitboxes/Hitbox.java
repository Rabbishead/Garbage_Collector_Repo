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

    public Hitbox(float x, float y, float width, float height, int degrees, String tags, float[] vertices,
            boolean active) {
        super(new float[] { 0, 0, width, 0, width, height, 0, height });
        setOrigin(width / 2, height / 2);
        setPosition(x, y);
        setRotation(degrees);
        this.active = active;
        this.tags = tags.split(",");
        stringTags = tags;
        isNull = false;
    }

    /**
     * Creates a box Hitbox with specified position, size, rotation, and tags.
     * Tags are a list of names separated by a comma, the String should contain no
     * spaces.
     * 
     * @param x       where am I? (horizontally speaking)
     * @param y       where am I? (vertically speaking)
     * @param width   as large as the sea!
     * @param height  as tall as the sky!
     * @param degrees specifies the hitbox's rotation.
     * @param tags    hitbox's tags to get differentiated in groups.
     */
    public Hitbox(float x, float y, float width, float height, int degrees, String tags, boolean active) {
        this(x, y, width, height, degrees, tags, new float[] { 0, 0, width, 0, width, height, 0, height }, active);
    }

    /**
     * Creates a box Hitbox with specified position, size, and rotation.
     * 
     * @param x       where am I? (horizontally speaking)
     * @param y       where am I? (vertically speaking)
     * @param width   as large as the sea!
     * @param height  as tall as the sky!
     * @param degrees specifies the hitbox's rotation.
     */
    public Hitbox(float x, float y, float width, float height, int degrees, boolean active) {
        this(x, y, width, height, degrees, "all", active);
    }

    /**
     * Creates a box Hitbox with specified position and size.
     * 
     * @param x      where am I? (horizontally speaking)
     * @param y      where am I? (vertically speaking)
     * @param width  as large as the sea!
     * @param height as tall as the sky!
     */
    public Hitbox(float x, float y, float width, float height, boolean active) {
        this(x, y, width, height, 0, active);
    }

    /**
     * Creates a voided Hitbox to modify with no consequences before creating the
     * actual Hitbox.
     */
    public Hitbox() {
        super();
        isNull = true;
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x - getOriginX(), y - getOriginY());
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
