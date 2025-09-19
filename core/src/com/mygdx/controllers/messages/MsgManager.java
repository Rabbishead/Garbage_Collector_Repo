package com.mygdx.controllers.messages;

import com.badlogic.gdx.ai.msg.MessageDispatcher;

public class MsgManager {

    private static final MessageDispatcher systemMsg = new MessageDispatcher();
    private static MessageDispatcher currentStageMsg;

    public static void sendSystemMsg(MSG msg) {
        systemMsg.dispatchMessage(msg.code);
    }

    /**
     * sends msgs to the stage message dispatcher
     * @param msgs
     */
    public static void sendStageMsg(MSG... msgs) {
        for (MSG msg : msgs) {
            currentStageMsg.dispatchMessage(msg.code);
        }
    }

    public static void setCurrentStageMsg(MessageDispatcher currentStageMsg) {
        MsgManager.currentStageMsg = currentStageMsg;
    }
}
