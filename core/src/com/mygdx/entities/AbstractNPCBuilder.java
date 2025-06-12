package com.mygdx.entities;

import java.util.ArrayList;
import com.badlogic.gdx.math.Vector2;
import com.bladecoder.ink.runtime.Story;
import com.mygdx.Utils;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.scripts.Script;

public abstract class AbstractNPCBuilder<T extends AbstractNPCBuilder<T>> {
    protected Vector2 coordinates, size = new Vector2(16, 32);
    protected ResourceEnum textureEnum;
    protected String[] path = new String[] { "-" };
    protected Story story;
    protected ArrayList<Script> scripts = new ArrayList<>();

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

    public T scripts(ResourceEnum... scripts){
        for (ResourceEnum resourceEnum : scripts) {
            this.scripts.add(new Script(resourceEnum));
        }
        return getThis();
    }
}
