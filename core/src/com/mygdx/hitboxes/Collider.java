package com.mygdx.hitboxes;

import com.badlogic.gdx.math.Polygon;

public class Collider extends Polygon {
    private String tag = "";

    public Collider() {
        super();
    }

    public Collider(String tag) {
        super();
        this.tag = tag;
    }

    public Collider(float x, float y, float width, float height, int degrees) {
        super(new float[] { 0, 0, width, 0, width, height, 0, height });
        this.setPosition(x, y);
        this.setOrigin(width / 2, height / 2);
        this.setRotation(degrees);
    }

    public Collider(float x, float y, float width, float height, int degrees, String tag) {
        this(x, y, width, height, degrees);
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
