package com.mygdx.controllers.gunControls.guns;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.controllers.camera.CameraController;
import com.mygdx.controllers.messages.MSG;
import com.mygdx.controllers.messages.MsgManager;
import com.mygdx.movement.BaseMovement;

public class BaseGun extends Actor {
    protected Sprite s;
    protected BaseMovement movement = new BaseMovement();
    protected float angleOffset;
    protected boolean flipped, stop = false;

    public BaseGun(Texture t, Vector2 origin, float angleOffset) {
        s = new Sprite(t);
        flipped = true;
        setSize(s.getWidth(), s.getHeight());
        movement.center = new Vector2(getWidth() / 2, getHeight() / 2);
        movement.anchor(origin);
        //movement.align();
        s.setOrigin(movement.origin.x, movement.origin.y);
        System.out.println("Origin: " + movement.origin);
        System.out.println("Player center" + origin);

        this.angleOffset = angleOffset;
    }

    public BaseGun() {
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        s.draw(batch);
    }

    @Override
    public void act(float delta) {
        Vector2 tmp = movement.getCenterWorldCoords();
        setPosition(tmp.x, tmp.y);

        float angle = CameraController.getMouseAngle();

        boolean left = angle > 90 && angle <= 270;
        boolean right = (angle <= 90 || angle > 270);

        boolean flipLeft = flipped && left;
        boolean flipRight = !flipped && right;

        if (flipLeft || flipRight) {
            s.flip(false, true);
            angleOffset = -angleOffset;
            flipped = !flipped;
        }

        float calculatedAngle = angle + angleOffset;
        if (angleOffset != 0 && stop) {
            boolean calculatedLeft = calculatedAngle > 90 && calculatedAngle <= 270;
            boolean calculatedRight = (calculatedAngle <= 90 || calculatedAngle > 270);

            boolean lockAtLeft = left && calculatedRight;
            boolean lockAtRight = right && calculatedLeft;

            if (lockAtLeft)
                calculatedAngle = angleOffset < 0 ? 90 : 270;
            if (lockAtRight)
                calculatedAngle = angleOffset < 0 ? 270 : 90;
        }

        s.setRotation(calculatedAngle);
    }

    @Override
    protected void positionChanged() {
        s.setPosition(getX(), getY());
        movement.x = getX();
        movement.y = getY();
    }

    public void onSwitched() {
    }

    public void onCurrent() {
    }

    public int leftTrigger() {
        MsgManager.sendStageMsg(MSG.SHOT);
        return 0;
    }

    public int rightTrigger() {
        MsgManager.sendStageMsg(MSG.SHOT);
        return 0;
    }

    public int middleTrigger() {
        MsgManager.sendStageMsg(MSG.SHOT);
        return 0;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public void flip(boolean x, boolean y) {
        s.flip(x, y);
    }

    public void setOffset(Vector2 offset) {
        setOffset(offset.x, offset.y);
    }

    public void setOffset(float x, float y) {
        movement.offset(new Vector2(x, y));
        s.setOrigin(movement.origin.x, movement.origin.y);
    }

    public void destroy() {
    }

    public void discard() {
        // TODO DISCARD
    }
}
