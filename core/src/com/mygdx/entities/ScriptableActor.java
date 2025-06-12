package com.mygdx.entities;

import com.mygdx.resources.ResourceEnum;

public interface ScriptableActor {

    public abstract void move(float x, float y);
    public abstract void changeAnimation(ResourceEnum e);
    public abstract void wait(float time);
}