package com.mygdx.player.gunControls.guns;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.Utils;
import com.mygdx.player.gunControls.GunController;
import com.mygdx.player.gunControls.projectiles.Stone;

public class Slingshot extends Gun {

    public Slingshot() {
        super();
    }

    public int leftTrigger() {
        Actor player = Utils.player;
        Utils.getStage().addActor(new Stone(
                new Vector2(player.getOriginX() + player.getX(), player.getOriginY() + player.getY()), Gdx.input.getX(), Gdx.input.getY()));
        GunController.get().setCooldown(10);
        GunController.get().resetCooldown();
        return 1;
    }
    
    public int rightTrigger() {
        Actor player = Utils.player;
        Utils.getStage().addActor(new Stone(
                new Vector2(player.getOriginX() + player.getX(), player.getOriginY() + player.getY()), Gdx.input.getX(), Gdx.input.getY()));
        GunController.get().setCooldown(20);
        return 2;
    }

    public int middleTrigger() {
        return 0;
    }
}
