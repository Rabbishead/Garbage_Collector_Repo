package com.mygdx.controllers.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.Utils;

public class CameraController {
    private static Viewport currentViewport;
    private static CameraShaker cameraShaker;
    private static Vector2 mouseDirection;
    private static float mouseAngle;
    private static Vector2 xDirection;
    private static float xAngle;
    private static final float lerpFactor = 5;

    public static void initCamera() {
        OrthographicCamera gameCamera = (OrthographicCamera) Utils.getStage().getCamera();
        currentViewport = Utils.getStage().getViewport();
        float shakeRadius = 15f;
        float minimumShakeRadius = 3f;
        float radiusFallOffFactor = 0.50f;
        cameraShaker = new CameraShaker(gameCamera, shakeRadius, minimumShakeRadius, radiusFallOffFactor);
    }

    public static void updateCamera() {
        Vector3 cameraPosition = Utils.getStage().getCamera().position;

        cameraShaker.origPosition = cameraPosition.cpy();
        cameraShaker.update(Gdx.graphics.getDeltaTime());

        cameraPosition.x += (Utils.getPlayer().getX() - cameraPosition.x) * lerpFactor * Gdx.graphics.getDeltaTime();
        cameraPosition.y += (Utils.getPlayer().getY() - cameraPosition.y) * lerpFactor * Gdx.graphics.getDeltaTime();

        // Apply the changes
        Utils.getStage().getCamera().position.set(cameraPosition);
        Utils.getStage().getCamera().update();
    }

    public static void applyShakeEffect() {
        cameraShaker.startShaking();
    }

    public static void calculateMouseAngle(Vector2 position) {
        float mX = Gdx.input.getX(), mY = Gdx.input.getY();
        Vector2 tmp = currentViewport.unproject(new Vector2(mX, mY));
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
