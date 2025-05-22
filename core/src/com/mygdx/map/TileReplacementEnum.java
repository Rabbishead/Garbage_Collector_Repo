package com.mygdx.map;

import com.mygdx.controllers.messages.MSG;

public enum TileReplacementEnum {
    WALL(MSG.BLOCK_WALLS),
    CHANGE_MOV_STYLE(MSG.CHANGE_MOV_STYLE);

    private MSG msg;

    TileReplacementEnum(MSG msg){
        this.msg = msg;
    }

    public MSG getMsg() {
        return msg;
    }
}
