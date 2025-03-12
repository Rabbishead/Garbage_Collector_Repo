package com.mygdx.hitboxes;

import java.util.ArrayList;
import java.util.function.BiConsumer;

import com.badlogic.gdx.math.Polygon;
import com.mygdx.Utils;

public class Collider extends Polygon {
    private String[] tags;
    private String stringTags;
    private String[] searchTags;
    private boolean collided;
    private BiConsumer<Collider, Hitbox> onHit;
    private BiConsumer<Collider, Hitbox> onLeave;
    private ArrayList<String> keys;
    private float width, height;
    public final boolean isNull;

    public Collider(float x, float y, float width, float height, float degrees, String tags, String searchTags) {
        super(new float[] { 0, 0, width, 0, width, height, 0, height });
        this.width = width;
        this.height = height;
        float hW = width / 2, hH = height / 2;
        this.setPosition(x - hW, y - hH);
        this.setOrigin(hW, hH);
        this.setRotation(degrees);
        this.tags = tags.split(",");
        this.searchTags = searchTags.split(",");
        this.stringTags = tags;
        this.collided = false;
        this.keys = new ArrayList<>();
        isNull = false;
    }

    public Collider(float x, float y, float width, float height, float degrees, String tags) {
        this(x, y, width, height, degrees, tags, "all");
    }

    public Collider(float x, float y, float width, float height, float degrees) {
        this(x, y, width, height, degrees, "none", "all");
    }

    public Collider() {
        super();
        isNull = true;
    }

    @Override
    public void setPosition(float x, float y) {
        float hW = width / 2, hH = height / 2;
        super.setPosition(x - hW, y - hH);
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
