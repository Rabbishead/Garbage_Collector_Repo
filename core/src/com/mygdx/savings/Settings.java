package com.mygdx.savings;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.screens.ScreensEnum;
import com.mygdx.screens.ScreensManager;

public class Settings implements com.badlogic.gdx.utils.Json.Serializable {

    private ScreensEnum lastRoom;
    private Vector2 lastRoomCoordinates = new Vector2();
    private boolean[] flags;

    public void updateData() {
        lastRoom = ScreensManager.getLastPlayableActiveScreen();
        lastRoomCoordinates = ScreensManager.getPlayableScreen(ScreensManager.getLastPlayableActiveScreen())
                .getPlayerCoordinates();
        flags = new boolean[] { true, true, false };
    }

    @Override
    public void write(Json json) {
        json.writeValue("LAST_ROOM", lastRoom);
        json.writeValue("PLAYER", lastRoomCoordinates);
        json.writeValue("FLAGS", flags);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        lastRoom = ScreensEnum.valueOf(jsonData.getString("LAST_ROOM"));
        System.out.println(lastRoom);

        lastRoomCoordinates.x = jsonData.get("PLAYER").getFloat("x");
        lastRoomCoordinates.y = jsonData.get("PLAYER").getFloat("y");

        flags = jsonData.get("FLAGS").asBooleanArray();
    }

    public Vector2 getPlayerCoordinates() {
        return lastRoomCoordinates;
    }

    public ScreensEnum getLastRoom() {
        return lastRoom;
    }

    public boolean getFlag(int i) {
        return flags[i];
    }
}
