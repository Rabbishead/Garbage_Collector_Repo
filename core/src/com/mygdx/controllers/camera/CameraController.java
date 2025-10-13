package com.mygdx.controllers.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.stage.GCStage;

public class CameraController {
    private static CameraShaker cameraShaker;
    private static Vector2 mouseDirection;
    private static float mouseAngle;
    private static Vector2 xDirection;
    private static float xAngle;
    private static final float lerpFactor = 5;

    public static void initCamera() {
        OrthographicCamera gameCamera = (OrthographicCamera) GCStage.get().getCamera();
        float shakeRadius = 15f;
        float minimumShakeRadius = 3f;
        float radiusFallOffFactor = 0.50f;
        cameraShaker = new CameraShaker(gameCamera, shakeRadius, minimumShakeRadius, radiusFallOffFactor);
    }

    public static void updateCamera() {
        Vector3 cameraPosition = GCStage.get().getCamera().position;

        cameraShaker.origPosition = cameraPosition.cpy();
        cameraShaker.update(Gdx.graphics.getDeltaTime());

        cameraPosition.x += (GCStage.get().getPlayer().getX() - cameraPosition.x) * lerpFactor * Gdx.graphics.getDeltaTime();
        cameraPosition.y += (GCStage.get().getPlayer().getY() - cameraPosition.y) * lerpFactor * Gdx.graphics.getDeltaTime();

        // Apply the changes
        GCStage.get().getCamera().position.set(cameraPosition);
        GCStage.get().getCamera().update();
    }

    public static void applyShakeEffect() {
        cameraShaker.startShaking();
    }

    public static void calculateMouseAngle(Vector2 position) {
        float mX = Gdx.input.getX(), mY = Gdx.input.getY();
        Vector2 tmp = GCStage.get().getViewport().unproject(new Vector2(mX, mY));
        Vector2 dir = new Vector2();
        dir.set(tmp).sub(position.x, position.y).nor();
        mouseDirection = dir;
        mouseAngle = dir.angleDeg();
    }

    public static void calculateThowardsPos(Vector2 position1, Vector2 position2) {
        Vector2 dir = new Vector2();
        dir.set(position2).sub(position1.x, position1.y).nor();
        xDirection = dir;
        xAngle = dir.angleDeg();
    }

    public static float getAngle(Vector2 position1, Vector2 position2){
        calculateThowardsPos(position1, position2);
        return getXAngle();
    }

    public static Vector2 getMouseDirection() {
        return mouseDirection;
    }

    public static float getMouseAngle() {
        return mouseAngle;
    }

    public static Vector2 getXDirection() {
        return xDirection;
    }

    public static float getXAngle() {
        return xAngle;
    }
}
