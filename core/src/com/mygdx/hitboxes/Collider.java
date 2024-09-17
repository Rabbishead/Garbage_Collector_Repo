package com.mygdx.hitboxes;

import com.badlogic.gdx.math.Rectangle;

public class Collider extends Rectangle {
    private String tag = "";

    public Collider() {
        super();
    }

    public Collider(String tag) {
        super();
        this.tag = tag;
    }

    public Collider(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public Collider(float x, float y, float width, float height, String tag) {
        super(x, y, width, height);
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
