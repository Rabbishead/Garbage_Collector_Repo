package com.mygdx.entities.npcs;

public class StateController {
    public enum StateEnum {
        FOLLOW_PLAYER,
        FLEE,
        CIRLE_AROUND,
        WANDER,
        STILL
    }

    private StateEnum state;

    public StateEnum getState() {
        return state;
    }
    public void setState(StateEnum state) {
        this.state = state;
    }
}
