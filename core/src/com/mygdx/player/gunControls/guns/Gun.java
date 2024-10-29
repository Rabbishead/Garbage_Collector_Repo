package com.mygdx.player.gunControls.guns;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.player.camera.CameraController;

public class Gun extends Actor {
    protected Sprite s;

    public Gun() {
    }

    public Gun(Texture t) {
        s = new Sprite(t);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //s.draw(batch);
        //s.setRotation(CameraController.getMouseAngle());
    }

    public int leftTrigger() {
        return 0;
    }

    public int rightTrigger() {
        return 0;
    }

    public int middleTrigger() {
        return 0;
    }
}
