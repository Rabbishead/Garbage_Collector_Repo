package com.mygdx.controllers.messages;

public enum MSG {
    DIALOGUE_TRIGGERED(0),
    DIALOGUE_DONE(1),
    BLOCK_WALLS(2),
    UNBLOCK_WALLS(3),
    SHOT(4),
    CHANGE_MOV_STYLE(5),
    ACT_YOU_IDIOT(6);

    public int code;

    MSG(int number){
        code = number;
    }
}