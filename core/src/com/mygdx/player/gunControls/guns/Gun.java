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
    protected Vector2 pos;
    protected int angleOffset;

    public Gun(Texture t, int angleOffset, float positionOffset) {
        s = new Sprite(t);
        setWidth(t.getWidth());
        setHeight(t.getHeight());
        pos = new Vector2(1, 0).scl(positionOffset);
        pos.set(pos.x - s.getWidth() / 2, pos.y - s.getHeight() / 2);
        s.setOrigin(-pos.x, -pos.y);

        this.angleOffset = angleOffset;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        s.draw(batch);
    }

    @Override
    public void act(float delta) {
        Vector2 center = Utils.getPlayer().center;
        setPosition(center.x + pos.x, center.y + pos.y);
        s.setRotation(CameraController.getMouseAngle() + angleOffset);
    }

    @Override
    protected void positionChanged() {
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
