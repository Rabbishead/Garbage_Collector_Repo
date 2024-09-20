package com.mygdx.gunControls.guns;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.Utils;

public class Slingshot extends Gun {

    public Slingshot() {
        super();
    }

    public int leftTrigger() {
        Utils.getStage().addActor(new Stone(
                new Vector2(Utils.player.getX(), Utils.player.getY()), Gdx.input.getX(), Gdx.input.getY()));
        return 1;
    }
    
    public int rightTrigger() {
        return 2;
    }

    public int middleTrigger() {
        return 0;
    }
}
