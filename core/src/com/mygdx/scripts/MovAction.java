package com.mygdx.scripts;

import com.mygdx.entities.ScriptableActor;

public class MovAction implements ScriptAction{
    private float x, y;
    private boolean relative;

    public MovAction(float x, float y, boolean relative){
        this.x = x;
        this.y = y;
        this.relative = relative;
    }


    @Override
    public void perform(ScriptableActor actor) {
        actor.move(x, y, relative);
    }
}
