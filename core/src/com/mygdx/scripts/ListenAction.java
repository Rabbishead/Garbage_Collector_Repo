package com.mygdx.scripts;

import com.mygdx.controllers.messages.MSG;
import com.mygdx.entities.ScriptableActor;

public class ListenAction implements ScriptAction{
    public ListenAction() {
        
    }

    private MSG[] msgs;


    @Override
    public void perform(ScriptableActor actor) {
        System.out.println("Listening");
    }
}
