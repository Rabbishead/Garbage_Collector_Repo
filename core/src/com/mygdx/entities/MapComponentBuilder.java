package com.mygdx.entities;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.resources.ResourceEnum;

public class MapComponentBuilder {
    protected Vector2 coordinates;
    protected ResourceEnum[] textureEnum;
    protected int width = 1;
    protected int height = 1;
    protected float animationRate = 0.1f;
    protected float delay = 0;
    protected ResourceEnum startingAnimation;
    protected boolean fade = false;

    public MapComponentBuilder coordinates(Vector2 coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    public MapComponentBuilder texture(ResourceEnum... texture) {
        this.textureEnum = texture;
        return this;
    }

    public MapComponentBuilder width(int width) {
        this.width = width;
        return this;
    }

    public MapComponentBuilder height(int height) {
        this.height = height;
        return this;
    }

    public MapComponentBuilder animationRate(float animationRate) {
        this.animationRate = animationRate;
        return this;
    }

    public MapComponentBuilder delay(float delay) {
        this.delay = delay;
        return this;
    }

    public MapComponentBuilder startingAnimation(ResourceEnum startingAnimation) {
        this.startingAnimation = startingAnimation;
        return this;
    }

    public MapComponentBuilder fade() {
        this.fade = true;
        return this;
    }

    public MapComponent build() {
        return new MapComponent(this);
    }
}
