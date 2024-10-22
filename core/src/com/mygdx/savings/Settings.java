package com.mygdx.savings;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.Utils;
import com.mygdx.player.camera.CameraController;
import com.mygdx.screens.ScreensManager;
import com.mygdx.screens.ScreensManager.ScreenEnum;

public class Settings implements com.badlogic.gdx.utils.Json.Serializable{
    public static float playerX;
    public static float playerY;

    public void updateData(){
        playerX = Utils.getPlayer().getX();
        playerY = Utils.getPlayer().getY();
    }

    @Override
    public void write(Json json) {
        json.writeValue("x", playerX);
        json.writeValue("y", playerY);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        playerX = jsonData.getFloat("x");
        playerY = jsonData.getFloat("y");
        System.out.println(playerX + " " + playerY);
        Utils.getPlayer().setX(playerX);
        Utils.getPlayer().setY(playerY);
        Utils.getStage().getCamera().position.x = playerX;
        Utils.getStage().getCamera().position.y = playerY;
    }
    
}
