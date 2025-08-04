package com.mygdx.entities;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.Utils;
import com.mygdx.controllers.messages.MSG;
import com.mygdx.movement.player.AutoMovementManager;

public abstract class GameActor extends Actor implements Telegraph {

    protected final AutoMovementManager autoMovementManager = new AutoMovementManager(this);

    public GameActor(){

    }

    public GameActor(MSG... msgs){
        for (MSG msg : msgs) {
            Utils.getActiveScreen().subscribe(this, msg);
        }
    }

    public void setCoords(Vector2 coords) {
        setX(coords.x);
        setY(coords.y);
    }

    public Vector2 getCoords() {
        return new Vector2(getX(), getY());
    }

    public void moveTo(Vector2 coords) {
        autoMovementManager.goTo(coords);
    }

    public void moveTo(float x, float y) {
        autoMovementManager.goTo(new Vector2(x, y));
    }

    public boolean isAutoWalking() {
        return autoMovementManager.isAnimationInProgress();
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        return true;
    }
}
