package com.mygdx.entities;

import com.badlogic.gdx.math.Vector2;
import com.bladecoder.ink.runtime.Story;
import com.mygdx.Utils;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.resources.TextureEnum;

public abstract class AbstractNPCBuilder<T extends AbstractNPCBuilder<T>> {
    protected Vector2 coordinates, size = new Vector2(16, 32);
    protected TextureEnum textureEnum;
    protected Story story;
    protected ResourceEnum autoStartedScript;
    protected ResourceEnum startingAnimation;

    public abstract T getThis();

    public T coordinates(Vector2 coordinates) {
        this.coordinates = coordinates;
        return getThis();
    }

    public T coordinates(float x, float y) {
        this.coordinates = new Vector2(x, y);
        return getThis();
    }

    public T size(Vector2 size) {
        this.size = size;
        return getThis();
    }

    public T size(float x, float y) {
        this.size = new Vector2(x, y);
        return getThis();
    }

    public T texture(TextureEnum texture) {
        this.textureEnum = texture;
        return getThis();
    }

    public T story(ResourceEnum e) {
        this.story = Utils.getStory(e);
        return getThis();
    }

    public T autoStartedScript(ResourceEnum e){
        this.autoStartedScript = e;
        return getThis();
    }

    public T startingAnimation(ResourceEnum e){
        this.startingAnimation = e;
        return getThis();
    }
}
