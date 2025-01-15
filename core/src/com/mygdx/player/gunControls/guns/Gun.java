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
    protected boolean flipped, flipX, flipY;

    public Gun(Texture t, int angleOffset, float positionOffset, boolean flipX, boolean flipY) {
        s = new Sprite(t);
        s.flip(true, false);
        flipped = true;
        setWidth(t.getWidth());
        setHeight(t.getHeight());
        pos = new Vector2(1, 0).scl(positionOffset);
        pos.set(pos.x - s.getWidth() / 2, pos.y - s.getHeight() / 2);
        s.setOrigin(-pos.x, -pos.y);

        this.angleOffset = angleOffset;
        this.flipX = flipX;
        this.flipY = flipY;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        s.draw(batch);
    }

    @Override
    public void act(float delta) {
        Vector2 center = Utils.getPlayer().center;
        setPosition(center.x + pos.x, center.y + pos.y);
        float angle = CameraController.getMouseAngle();
        boolean left = flipped && angle > 90 && angle <= 270;
        boolean right = !flipped && (angle <= 90 || angle > 270);

        if (left || right) {
            s.flip(flipX, flipY);
            angleOffset = -angleOffset;
            flipped = !flipped;
        }

        float calculatedAngle = angle + angleOffset;
        if (angleOffset != 0) {
            boolean lockAt90left = angle > 90 && angle <= 270 && (calculatedAngle <= 90 || calculatedAngle > 270) && angleOffset < 0;
            boolean lockAt90right = (angle <= 90 || angle > 270) && calculatedAngle > 90 && calculatedAngle <= 270 && angleOffset > 0;
            boolean lockAt270left = angle > 90 && angle <= 270 && (calculatedAngle <= 90 || calculatedAngle > 270) && angleOffset > 0;
            boolean lockAt270right = (angle <= 90 || angle > 270) && calculatedAngle > 90 && calculatedAngle <= 270 && angleOffset < 0;

            if (lockAt90left)
                calculatedAngle = 90.01f;
            if (lockAt90right)
                calculatedAngle = 90;
            if (lockAt270left)
                calculatedAngle = 270;
            if (lockAt270right)
                calculatedAngle = 169.99f;
        }

        s.setRotation(calculatedAngle);
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
