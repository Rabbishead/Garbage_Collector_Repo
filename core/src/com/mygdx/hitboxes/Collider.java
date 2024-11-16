package com.mygdx.hitboxes;

import com.badlogic.gdx.math.Polygon;

public class Collider extends Polygon {
    private String[] tags;
    private String stringTags;
    private String[] searchTags;
    private boolean collided;

    public Collider(float x, float y, float width, float height, float degrees, String tags, String searchTags) {
        super(new float[] { 0, 0, width, 0, width, height, 0, height });
        this.setPosition(x, y);
        this.setOrigin(width / 2, height / 2);
        this.setRotation(degrees);
        this.tags = tags.split(",");
        this.searchTags = searchTags.split(",");
        this.stringTags = tags;
        this.collided = false;
    }

    public Collider(float x, float y, float width, float height, float degrees, String tags) {
        this(x, y, width, height, degrees, tags, "all");
    }

    public Collider(float x, float y, float width, float height, float degrees) {
        this(x, y, width, height, degrees, "none", "all");
    }

    public Collider(String tag, String searchTags) {
        this(0, 0, 1, 1, 0, tag, searchTags);
    }

    public Collider(String tag) {
        this(0, 0, 1, 1, 0, tag);
    }

    public Collider() {
        this(0, 0, 1, 1, 0);
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
}
