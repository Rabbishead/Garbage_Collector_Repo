package com.mygdx.controllers.gunControls.guns;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.Utils;
import com.mygdx.controllers.camera.CameraController;
import com.mygdx.controllers.messages.MsgManager;
import com.mygdx.controllers.messages.MsgManager.MSG;

public class BaseGun extends Actor {
    protected Sprite s;
    protected Vector2 pos, origin;
    protected int angleOffset;
    protected boolean flipped, stop = false;

    public BaseGun(Texture t, Vector2 origin, int angleOffset) {
        s = new Sprite(t);
        flipped = true;
        setWidth(s.getWidth());
        setHeight(s.getHeight());
        pos = new Vector2(0, 0);
        pos.set(pos.x - getWidth() / 2, pos.y - getHeight() / 2);
        s.setOrigin(-pos.x, -pos.y);

        this.origin = origin;
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
        origin = Utils.getPlayer().center;
        setPosition(origin.x + pos.x, origin.y + pos.y);
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
        pos = new Vector2(x, y);
        pos.set(pos.x - getWidth() / 2, pos.y - getHeight() / 2);
        s.setOrigin(-pos.x, -pos.y);
    }

    public void destroy() {
    }

    public void discard() {
        // TODO DISCARD
    }
}
