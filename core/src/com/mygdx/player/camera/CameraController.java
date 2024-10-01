package com.mygdx.player.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class CameraController {
    private static OrthographicCamera gameCamera;
    private static OrthographicCamera hudCamera;

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

    public static void applyShakeEffect(){
        
    }
}
