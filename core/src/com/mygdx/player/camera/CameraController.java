package com.mygdx.player.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.Utils;

public class CameraController {
    private static OrthographicCamera gameCamera;
    private static OrthographicCamera hudCamera;
    private static Viewport currentViewport;
    private static CameraShaker cameraShaker;

    public static void initCamera(){
        gameCamera = (OrthographicCamera) Utils.getStage().getCamera();
        currentViewport = Utils.getStage().getViewport();
        hudCamera = new OrthographicCamera();
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
