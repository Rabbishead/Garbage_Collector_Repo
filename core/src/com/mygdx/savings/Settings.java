package com.mygdx.savings;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.Utils;

public class Settings implements com.badlogic.gdx.utils.Json.Serializable{
    public static Vector2 playerCoordinates = new Vector2();

    public void updateData(){
        playerCoordinates.x = Utils.getPlayer().getX();
        playerCoordinates.y = Utils.getPlayer().getY();
    }

    @Override
    public void write(Json json) {
        json.writeValue("x", playerCoordinates.x);
        json.writeValue("y", playerCoordinates.y);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        playerCoordinates.x = jsonData.getFloat("x");
        playerCoordinates.y = jsonData.getFloat("y");
        Utils.getPlayer().setX(playerX);
        Utils.getPlayer().setY(playerY);
        Utils.getStage().getCamera().position.x = playerX;
        Utils.getStage().getCamera().position.y = playerY;
    }
    
}
