package com.mygdx.controllers.hitboxes;

import java.util.ArrayList;
import java.util.function.Consumer;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.Utils;
import com.mygdx.controllers.messages.LockedInfo;
import com.mygdx.controllers.messages.ObjectInfo;
import com.mygdx.movement.BaseMovement;

public class Hitbox extends Polygon {
    private boolean active, registered = false;
    private ArrayList<Tags> tags = new ArrayList<>();
    private Consumer<Collider> onHit;
    private Consumer<Collider> onLeave;
    private BaseMovement movement;
    private LockedInfo extraInfo;
    public final boolean isNull;

    /**
     * Creates a Hitbox with specified position, size, rotation, and form.
     * 
     * @param anchor   the hitbox's anchor coordinates.
     * @param degrees  specifies the hitbox's rotation.
     * @param vertices an array whose elements in pairs represent the x and y of the
     *                 polygon's vertices.
     */
    public Hitbox(Vector2 anchor, int degrees, float[] vertices, boolean active) {
        super(vertices);
        movement = new BaseMovement(getCentroid(new Vector2()));
        setOrigin(movement.center.x, movement.center.y);
        movement.anchor(anchor);
        setPosition();
        setRotation(degrees);
        this.tags = new ArrayList<>();
        this.active = active;
        isNull = false;
    }

    /**
     * Creates a box Hitbox with specified position, size, and rotation.
     * 
     * @param anchor  the hitbox's anchor coordinates.
     * @param width   as large as the sea!
     * @param height  as tall as the sky!
     * @param degrees specifies the hitbox's rotation.
     */
    public Hitbox(Vector2 anchor, float width, float height, int degrees, boolean active) {
        this(anchor, degrees, new float[] { 0, 0, width, 0, width, height, 0, height }, active);
    }

    /**
     * Creates a box Hitbox with specified position and size.
     * 
     * @param anchor  the hitbox's anchor coordinates.
     * @param width   as large as the sea!
     * @param height  as tall as the sky!
     * @param degrees specifies the hitbox's rotation.
     */
    public Hitbox(Vector2 anchor, float width, float height, boolean active) {
        this(anchor, width, height, 0, active);
    }

    /**
     * Creates a voided Hitbox to modify with no consequences before creating the
     * actual Hitbox.
     */
    public Hitbox() {
        super();
        isNull = true;
    }

    public void setPosition() {
        Vector2 worldCoords = movement.getWorldCoords();
        super.setPosition(worldCoords.x, worldCoords.y);
    }

    public void setOffset(float x, float y) {
        movement.offset(new Vector2(x, y));
        setOrigin(movement.origin.x, movement.origin.y);
        setPosition();
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
            onHit(r);
            r.onHit(this);
        }
        return true;
    }

    public void onHit(Collider r) {
        if (onHit != null)
            onHit.accept(r);
    }

    public void onLeave(Collider r) {
        if (onLeave != null)
            onLeave.accept(r);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean containsTag(Tags tag) {
        return tags.contains(tag);
    }

    public ArrayList<Tags> getTags() {
        return tags;
    }

    public void setTags(Tags... tags) {
        this.tags.clear();
        for (Tags tag : tags) {
            this.tags.add(tag);
        }
    }

    public LockedInfo getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(ObjectInfo extraInfo) {
        if (this.extraInfo != null)
            return;

        this.extraInfo = new LockedInfo(extraInfo);
    }

    public void setOnHit(Consumer<Collider> onHit) {
        this.onHit = onHit;
    }

    public void setOnLeave(Consumer<Collider> onLeave) {
        this.onLeave = onLeave;
    }

    /***
     * Register method to add the HitBox to the Event handler with a check.
     * 
     * @return {@code true} if the HitBox has been added.
     */
    public boolean register() {
        if (isNull || registered)
            return false;
        if (tags.isEmpty())
            tags.add(Tags.ALL);
        Utils.getHitboxHandler().registerHitbox(this);
        registered = true;
        return true;
    }

    /***
     * Register method to remove the HitBox to the Event handler with a check.
     * 
     * @return {@code true} if the HitBox has been removed.
     */
    public boolean unregister() {
        if (isNull || !registered)
            return false;
        Utils.getHitboxHandler().unRegisterHitbox(this);
        registered = false;
        return true;
    }
}
