package com.mygdx.entities;

import com.badlogic.gdx.math.Vector2;
import com.bladecoder.ink.runtime.Story;
import com.mygdx.Utils;
import com.mygdx.resources.ResourceEnum;

public abstract class AbstractNPCBuilder<T extends AbstractNPCBuilder<T>> {
    public Vector2 coordinates, size = new Vector2(16, 32);
    public ResourceEnum textureEnum;
    public String[] path = new String[] { "-" };
    public Story story;

    public abstract T getThis();

    public T coordinates(Vector2 coordinates) {
        this.coordinates = coordinates;
        return getThis();
    }

    public T size(Vector2 size) {
        this.size = size;
        return getThis();
    }

    public T texture(ResourceEnum texture) {
        this.textureEnum = texture;
        return getThis();
    }

    public T path(String... path) {
        this.path = path;
        return getThis();
    }

    public T story(ResourceEnum e) {
        this.story = Utils.getStory(e);
        return getThis();
    }
}
