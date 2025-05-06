package com.mygdx.hitboxes;

import java.util.ArrayList;
import java.util.function.BiConsumer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.Utils;

public class Collider extends Polygon {
    private String[] tags;
    private String stringTags;
    private String[] searchTags;
    private boolean collided;
    private Vector2 center;
    private BiConsumer<Collider, Hitbox> onHit;
    private BiConsumer<Collider, Hitbox> onLeave;
    private ArrayList<String> keys;
    public final boolean isNull;

    /**
     * Creates a Collider with specified position, size, rotation, tags, and form.
     * Tags are a list of names separated by a comma, the String should contain no
     * spaces.
     * 
     * @param center     the colliders' center coordinates.
     * @param width      as large as the sea!
     * @param height     as tall as the sky!
     * @param degrees    specifies the colliders' rotation.
     * @param tags       colliders' tags to differentiate what to do on collision.
     * @param searchTags specifies what hiboxes can the collider collide with.
     * @param vertices   an array whose elements in pairs represent the x and y of
     *                   the polygon's vertices.
     */
    public Collider(Vector2 center, float width, float height, float degrees, String tags, String searchTags,
            float[] vertices) {
        super(vertices);
        this.center = center;
        setOrigin(width / 2, height / 2);
        setPosition();
        setRotation(degrees);
        this.tags = tags.split(",");
        this.searchTags = searchTags.split(",");
        stringTags = tags;
        collided = false;
        keys = new ArrayList<>();
        isNull = false;
    }

    /**
     * Creates a box Collider with specified position, size, rotation, and tags.
     * Tags are a list of names separated by a comma, the String should contain no
     * spaces.
     * 
     * @param center     the colliders' center coordinates.
     * @param width      as large as the sea!
     * @param height     as tall as the sky!
     * @param degrees    specifies the colliders' rotation.
     * @param tags       colliders' tags to differentiate what to do on collision.
     * @param searchTags specifies what hiboxes can the collider collide with.
     */
    public Collider(Vector2 center, float width, float height, float degrees, String tags, String searchTags) {
        this(center, width, height, degrees, tags, searchTags,
                new float[] { 0, 0, width, 0, width, height, 0, height });
    }

    /**
     * Creates a box Collider with specified position, size, rotation, and tags.
     * Tags are a list of names separated by a comma, the String should contain no
     * spaces.
     * 
     * @param center  the colliders' center coordinates.
     * @param width   as large as the sea!
     * @param height  as tall as the sky!
     * @param degrees specifies the colliders' rotation.
     * @param tags    colliders' tags to differentiate what to do on collision.
     */
    public Collider(Vector2 center, float width, float height, float degrees, String tags) {
        this(center, width, height, degrees, tags, "all");
    }

    /**
     * Creates a box Collider with specified position, size, and rotation.
     * 
     * @param center  the colliders' center coordinates.
     * @param width   as large as the sea!
     * @param height  as tall as the sky!
     * @param degrees specifies the colliders' rotation.
     */
    public Collider(Vector2 center, float width, float height, float degrees) {
        this(center, width, height, degrees, "none");
    }

    /**
     * Creates a box Collider with specified position and size.
     * 
     * @param center the colliders' center coordinates.
     * @param width  as large as the sea!
     * @param height as tall as the sky!
     */
    public Collider(Vector2 center, float width, float height) {
        this(center, width, height, 0);
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
        super.setPosition(center.x - getOriginX(), center.y - getOriginY());
    }

    public void setPosition(Vector2 center) {
        this.center = center;
        setPosition();
    }

    @Override
    public void setPosition(float x, float y) {
        this.center = new Vector2(x, y);
        setPosition();
    }

    public void setOffset(float x, float y) {
        Vector2 pos = new Vector2(x, y);
        pos.set(pos.x - getOriginX(), pos.y - getOriginY());
        setPosition(center.x + pos.x, center.y + pos.y);
        setOrigin(-pos.x, -pos.y);
    }

    public void onHit(Hitbox h) {
        if (onHit != null)
            onHit.accept(this, h);
    }

    public void onLeave(Hitbox h) {
        if (onLeave != null)
            onLeave.accept(this, h);
    }

    public void register(Hitbox h) {
        keys.add(h.toString());
    }

    public ArrayList<String> getKeys() {
        return keys;
    }

    public void clearKeys() {
        keys.clear();
    }

    public boolean containsTag(String tag) {
        return stringTags.contains(tag);
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags.split(",");
        this.stringTags = tags;
    }

    public String[] getSearchTags() {
        return searchTags;
    }

    public void setSearchTags(String searchTags) {
        this.searchTags = searchTags.split(",");
    }

    public boolean isCollided() {
        return collided;
    }

    public void setCollided(boolean collided) {
        this.collided = collided;
    }

    public void setOnHit(BiConsumer<Collider, Hitbox> onHit) {
        this.onHit = onHit;
    }

    public void setOnLeave(BiConsumer<Collider, Hitbox> onLeave) {
        this.onLeave = onLeave;
    }

    /***
     * Register method to add the Collider to the Event handler with a check.
     * 
     * @return {@code true} if the Collider has been added.
     */
    public boolean register() {
        if (isNull)
            return false;
        Utils.getHitboxHandler().registerCollider(this);
        return true;
    }

    /***
     * Register method to remove the Collider to the Event handler with a check.
     * 
     * @return {@code true} if the Collider has been removed.
     */
    public boolean unregister() {
        if (isNull)
            return false;
        Utils.getHitboxHandler().unRegisterCollider(this);
        return true;
    }
}
