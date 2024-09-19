package com.mygdx.gunControls.guns;

public class Slingshot extends Gun{

    public Slingshot() {
        super();
    }

    public int rightTrigger() {
        
        return 1;
    }

    public int leftTrigger() {
        return 2;
    }

    public int middleTrigger() {
        return 0;
    }
}
