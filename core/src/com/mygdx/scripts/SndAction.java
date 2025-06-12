package com.mygdx.scripts;

import com.mygdx.controllers.messages.MSG;
import com.mygdx.controllers.messages.MsgManager;
import com.mygdx.entities.ScriptableActor;

public class SndAction implements ScriptAction{
    private MSG[] msgs;

    public SndAction(MSG... msgs) {
        this.msgs = msgs;
    }


    @Override
    public void perform(ScriptableActor actor) {
        for (MSG msg : msgs) {
            MsgManager.sendStageMsg(msg);
        }
    }
}
