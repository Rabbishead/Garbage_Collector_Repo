package com.mygdx.player.gunControls.guns;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.Utils;
import com.mygdx.player.camera.CameraController;

public class Gun extends Actor {
    protected Sprite s;
    protected Vector2 position;
    protected int angleOffset;

    public Gun(Texture t, int angleOffset, float positionOffset) {
        s = new Sprite(t);
        setWidth(t.getWidth());
        setHeight(t.getHeight());
        s.setOrigin(getWidth()/2, 0);
        position = new Vector2().scl(positionOffset);
        s.setPosition(position.x, position.y);
        this.angleOffset = angleOffset;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        s.draw(batch);
        s.setRotation(CameraController.getMouseAngle() + angleOffset);
    }

    @Override
    public void act(float delta) {
        Vector2 center = Utils.getPlayer().center;
        setPosition(center.x, center.y);
        s.setPosition(getX(), getY());
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
