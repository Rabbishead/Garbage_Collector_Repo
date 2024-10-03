package com.mygdx.player.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class CameraController {
    private static OrthographicCamera gameCamera;
    private static OrthographicCamera hudCamera;
    private static CameraShaker cameraShaker;

    public static OrthographicCamera getGameCamera() {
        return gameCamera;
    }
    public static OrthographicCamera getHudCamera() {
        return hudCamera;
    }
    public static void setGameCamera(OrthographicCamera gameCamera) {
        CameraController.gameCamera = gameCamera;
    }
    public static void setHudCamera(OrthographicCamera hudCamera) {
        CameraController.hudCamera = hudCamera;
    }

    public static void initCamera(){
        float shakeRadius = 30f;
        float minimumShakeRadius = 3f;
        float radiusFallOffFactor = 0.90f;
        cameraShaker = new CameraShaker(gameCamera, shakeRadius, minimumShakeRadius, radiusFallOffFactor);

    }

    public static void updateCamera(){
        cameraShaker.update(Gdx.graphics.getDeltaTime());
    }

    public static void applyShakeEffect(){
        cameraShaker.startShaking();
    }
}
