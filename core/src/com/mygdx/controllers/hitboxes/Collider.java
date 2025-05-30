package com.mygdx.controllers.hitboxes;

import java.util.ArrayList;
import java.util.function.Consumer;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.Utils;
import com.mygdx.controllers.messages.LockedInfo;
import com.mygdx.controllers.messages.ObjectInfo;
import com.mygdx.movement.BaseMovement;

public class Collider extends Polygon {
    private ArrayList<Tags> tags;
    private ArrayList<Tags> searchTags;
    private boolean collided, registered = false;
    private Consumer<Hitbox> onHit;
    private Consumer<Hitbox> onLeave;
    private BaseMovement movement;
    private LockedInfo extraInfo;
    public final boolean isNull;

    /**
     * Creates a Collider with specified position, size, rotation, and form.
     * 
     * @param anchor   the colliders' anchor coordinates.
     * @param degrees  specifies the colliders' rotation.
     * @param vertices an array whose elements in pairs represent the x and y of
     *                 the polygon's vertices.
     */
    public Collider(Vector2 anchor, float degrees, float[] vertices) {
        super(vertices);
        movement = new BaseMovement(getCentroid(new Vector2()));
        setOrigin(movement.center.x, movement.center.y);
        movement.anchor(anchor);
        setPosition();
        setRotation(degrees);
        this.tags = new ArrayList<>();
        this.searchTags = new ArrayList<>();
        collided = false;
        isNull = false;
    }

    /**
     * Creates a box Collider with specified position, size, and rotation.
     * 
     * @param anchor  the colliders' anchor coordinates.
     * @param width   as large as the sea!
     * @param height  as tall as the sky!
     * @param degrees specifies the colliders' rotation.
     */
    public Collider(Vector2 anchor, float width, float height, float degrees) {
        this(anchor, degrees, new float[] { 0, 0, width, 0, width, height, 0, height });
    }

    /**
     * Creates a box Collider with specified position and size.
     * 
     * @param anchor the colliders' anchor coordinates.
     * @param width  as large as the sea!
     * @param height as tall as the sky!
     */
    public Collider(Vector2 anchor, float width, float height) {
        this(anchor, width, height, 0);
    }

    /**
     * Creates a voided Collider to modify with no consequences of creating the
     * actual Collider.
     */
    public Collider() {
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

    public void onHit(Hitbox h) {
        if (onHit != null)
            onHit.accept(h);
    }

    public void onLeave(Hitbox h) {
        if (onLeave != null)
            onLeave.accept(h);
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

    public boolean containsSearchTag(Tags tag) {
        return searchTags.contains(tag);
    }

    public ArrayList<Tags> getSearchTags() {
        return searchTags;
    }

    public void setSearchTags(Tags... searchTags) {
        this.searchTags.clear();
        for (Tags tag : searchTags) {
            this.searchTags.add(tag);
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

    public boolean isCollided() {
        return collided;
    }

    public void setCollided(boolean collided) {
        this.collided = collided;
    }

    public void setOnHit(Consumer<Hitbox> onHit) {
        this.onHit = onHit;
    }

    public void setOnLeave(Consumer<Hitbox> onLeave) {
        this.onLeave = onLeave;
    }

    /***
     * Register method to add the Collider to the Event handler with a check.
     * 
     * @return {@code true} if the Collider has been added.
     */
    public boolean register() {
        if (isNull || registered)
            return false;
        if (tags.isEmpty())
            tags.add(Tags.NONE);
        if (searchTags.isEmpty())
            tags.add(Tags.ALL);
        Utils.getHitboxHandler().registerCollider(this);
        registered = true;
        return true;
    }

    /***
     * Register method to remove the Collider to the Event handler with a check.
     * 
     * @return {@code true} if the Collider has been removed.
     */
    public boolean unregister() {
        if (isNull || !registered)
            return false;
        Utils.getHitboxHandler().unRegisterCollider(this);
        registered = false;
        return true;
    }
}
