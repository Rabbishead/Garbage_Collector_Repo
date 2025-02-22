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
    private StateEnum prevState;

    public StateEnum getState() {
        return state;
    }

    public StateEnum getPrevState() {
        return prevState;
    }

    public void setState(StateEnum state) {
        this.prevState = this.state;
        this.state = state;
        System.out.println(state);
    }
}
