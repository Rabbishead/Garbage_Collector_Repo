package com.mygdx.scripts;

import com.mygdx.entities.ScriptableActor;

public class MovAction implements ScriptAction{
    private float x, y;

    public MovAction(float x, float y){
        this.x = x;
        this.y = y;
    }


    @Override
    public void perform(ScriptableActor actor) {
        actor.move(x, y);
    }
}
