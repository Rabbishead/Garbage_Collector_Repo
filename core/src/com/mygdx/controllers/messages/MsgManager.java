package com.mygdx.controllers.messages;

import com.badlogic.gdx.ai.msg.MessageDispatcher;

public class MsgManager {

    private static final MessageDispatcher systemMsg = new MessageDispatcher();
    private static MessageDispatcher currentStageMsg;

    public static void sendSystemMsg(MSG msg) {
        systemMsg.dispatchMessage(msg.code);
    }

    public static void sendStageMsg(MSG msg) {
        currentStageMsg.dispatchMessage(msg.code);
    }

    public static void setCurrentStageMsg(MessageDispatcher currentStageMsg) {
        MsgManager.currentStageMsg = currentStageMsg;
    }
}
