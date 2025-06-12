package com.mygdx.scripts;

import com.mygdx.entities.ScriptableActor;

public class WaitAction implements ScriptAction{

    private int time;

    public WaitAction(int time) {
        this.time = time;
    }


    @Override
    public void perform(ScriptableActor actor) {
        System.out.println("Waiting " + time);
    }
}
