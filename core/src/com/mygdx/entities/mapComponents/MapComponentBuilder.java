package com.mygdx.entities.mapComponents;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.Data;
import com.mygdx.resources.ResourceEnum;

public class MapComponentBuilder {
    protected Vector2 coordinates;
    protected ResourceEnum[] textureEnum;
    protected float width = Data.TILE;
    protected float height = Data.TILE;
    protected float animationRate = 0.2f;
    protected float delay = 0;
    protected ResourceEnum startingAnimation;
    protected boolean fade = true;

    public MapComponentBuilder coordinates(Vector2 coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    public MapComponentBuilder texture(ResourceEnum... texture) {
        this.textureEnum = texture;
        return this;
    }

    public MapComponentBuilder width(float width) {
        this.width = width;
        return this;
    }

    public MapComponentBuilder height(float height) {
        this.height = height;
        return this;
    }

    public MapComponentBuilder size(float width, float height){
        this.width = width;
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

    public MapComponentBuilder notFade() {
        this.fade = false;
        return this;
    }

    public MapComponent build() {
        return new MapComponent(this);
    }
}
