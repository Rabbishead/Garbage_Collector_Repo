package com.mygdx.messages;

import com.badlogic.gdx.ai.msg.MessageDispatcher;
import java.util.Map;

public class MsgManager {
    public static Map<MSG, Integer> codes = Map.of(
            MSG.DIALOGUE_TRIGGERED, 0,
            MSG.DIALOGUE_DONE, 1,
            MSG.BLOCK_WALLS, 2,
            MSG.UNBLOCK_WALLS, 3
    );

    public enum MSG{
        DIALOGUE_TRIGGERED,
        DIALOGUE_DONE,
        BLOCK_WALLS,
        UNBLOCK_WALLS
    }

    private static final MessageDispatcher systemMsg = new MessageDispatcher();
    private static MessageDispatcher currentStageMsg;

    public static void sendSystemMsg(int msg){
        systemMsg.dispatchMessage(msg);
    }
    public static void sendSystemMsg(MSG msg){
        systemMsg.dispatchMessage(codes.get(msg));
    }
    public static void sendStageMsg(int msg){
        currentStageMsg.dispatchMessage(msg);
    }
    public static void sendStageMsg(MSG msg){
        currentStageMsg.dispatchMessage(codes.get(msg));
    }

    public static void setCurrentStageMsg(MessageDispatcher currentStageMsg) {
        MsgManager.currentStageMsg = currentStageMsg;
    }
}
