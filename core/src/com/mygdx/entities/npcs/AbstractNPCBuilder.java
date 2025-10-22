package com.mygdx.entities.npcs;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.dialogues.GameStory;
import com.mygdx.resources.RM;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.resources.TextureEnum;

public abstract class AbstractNPCBuilder<T extends AbstractNPCBuilder<T>> {
    protected Vector2 coordinates, size = new Vector2(16, 32);
    protected TextureEnum textureEnum;
    protected boolean atlas = true;
    protected GameStory story;
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

    public T noAtlas(){
        this.atlas = false;
        return getThis();
    }

    public T story(ResourceEnum e) {
        this.story = RM.get().getStory(e);
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
