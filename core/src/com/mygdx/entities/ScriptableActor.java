package com.mygdx.entities;

import com.mygdx.resources.ResourceEnum;
import com.mygdx.scripts.Script;

public abstract class ScriptableActor extends GameActor{
    protected Script script;

    public void doScript(ResourceEnum s){
        script = new Script(s);
        script.proceed(this);
    }

    public void move(float x, float y){
        moveTo(x, y);
    }

    public void proceed(){
        script.proceed(this);
    }

    public abstract void changeAnimation(ResourceEnum e);
    public abstract void wait(float time);
    
    
}