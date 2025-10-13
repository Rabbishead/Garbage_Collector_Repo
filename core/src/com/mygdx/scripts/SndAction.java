package com.mygdx.scripts;

import com.mygdx.controllers.messages.MSG;
import com.mygdx.entities.ScriptableActor;

public class SndAction implements ScriptAction{
    private MSG msg;

    public SndAction(MSG m){
        msg = m;
    }


    @Override
    public void perform(ScriptableActor actor) {
        System.out.println("Sending: " + msg);
        actor.proceed();
        
    }
}
