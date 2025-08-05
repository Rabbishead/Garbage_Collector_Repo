package com.mygdx.scripts;

import com.mygdx.controllers.messages.MSG;
import com.mygdx.entities.ScriptableActor;

public class ListenAction implements ScriptAction{
    private MSG msg;
    
    public ListenAction(MSG m) {
        msg = m;
    }

    @Override
    public void perform(ScriptableActor actor) {
        actor.listen(msg);
        System.out.println("Listening");
    }
}
