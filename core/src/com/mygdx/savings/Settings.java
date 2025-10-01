package com.mygdx.savings;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.Utils;
import com.mygdx.screens.ScreensManager;

public class Settings implements com.badlogic.gdx.utils.Json.Serializable {
    private String lastRoom;
    private boolean fightging;
    private Vector2 lastRoomCoordinates = new Vector2();
    private boolean[] flags, selectedGuns = new boolean[] { true, true };

    public void updateData() {
        lastRoom = ScreensManager.getLastPlayableActiveScreen();
        lastRoomCoordinates = ScreensManager.getPlayableScreen(ScreensManager.getLastPlayableActiveScreen())
                .getPlayerCoordinates();
        fightging = Utils.getPlayer().isFighting();
        flags = new boolean[] { true, true, false };
        selectedGuns = new boolean[] { true, true };
    }

    @Override
    public void write(Json json) {
        json.writeValue("LAST_ROOM", lastRoom);
        json.writeValue("PLAYER", lastRoomCoordinates);
        json.writeValue("FIGHTING", fightging);
        json.writeValue("FLAGS", flags);
        json.writeValue("SELECTED_GUNS", selectedGuns);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        lastRoom = jsonData.getString("LAST_ROOM");

        lastRoomCoordinates.x = jsonData.get("PLAYER").getFloat("x");
        lastRoomCoordinates.y = jsonData.get("PLAYER").getFloat("y");

        fightging = jsonData.getBoolean("FIGHTING");

        //flags = jsonData.get("FLAGS").asBooleanArray();
        //selectedGuns = jsonData.get("SELECTED_GUNS").asBooleanArray();
    }

    public Vector2 getPlayerCoordinates() {
        return lastRoomCoordinates;
    }

    public String getLastRoom() {
        return lastRoom;
    }

    public boolean getFlag(int i) {
        return flags[i];
    }

    public boolean getSelectedGun(int i) {
        return selectedGuns[i];
    }

    public boolean isFightging() {
        return fightging;
    }
}
