package com.mygdx.scripts;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.controllers.messages.MSG;
import com.mygdx.entities.ScriptableActor;

public class ListenAction implements ScriptAction{
    private MSG msg;
    private int maxWaitingSeconds = 30;
    
    public ListenAction(MSG m) {
        msg = m;
    }
    public ListenAction(MSG m, int maxTime) {
        msg = m;
        maxWaitingSeconds = maxTime;
    }

    @Override
    public void perform(ScriptableActor actor) {
        System.out.println("Listening to:" + msg);
        actor.listen(msg);
        actor.addAction(
            Actions.sequence(
                Actions.delay(maxWaitingSeconds),
                Actions.run(() -> {
                    if(actor.listeningMSG == msg) actor.resetListen();
                })
            )
        );
    }
}
