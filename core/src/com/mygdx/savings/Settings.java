package com.mygdx.savings;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.Utils;
import com.mygdx.screens.ScreensManager;
import com.mygdx.screens.generic.playable.PlayableScreen;

public class Settings implements com.badlogic.gdx.utils.Json.Serializable{

    private String lastRoom;
    private Vector2 mainScreenCoordinates = new Vector2();
    private Vector2 secondScreenCoordinates = new Vector2();
    private Vector2 sandstoneArenaCoordinates = new Vector2();
    private Vector2 cityCoordinates = new Vector2();

    public void updateData(){
        lastRoom = Utils.getActiveScreen().getName();
        if(!ScreensManager.isNull("MAIN_SCREEN")){
            PlayableScreen s = ScreensManager.getPlayableScreen("MAIN_SCREEN");
            mainScreenCoordinates = s.getPlayerCoordinates();
        }
        
        if(!ScreensManager.isNull("SECOND_SCREEN")){
            PlayableScreen s = ScreensManager.getPlayableScreen("SECOND_SCREEN");
            secondScreenCoordinates = s.getPlayerCoordinates();

        }
        if(!ScreensManager.isNull("SANDSTONE_ARENA")){
            PlayableScreen s = ScreensManager.getPlayableScreen("SANDSTONE_ARENA");
            sandstoneArenaCoordinates = s.getPlayerCoordinates();
        }
        if(!ScreensManager.isNull("CITY_SCREEN")){
            PlayableScreen s = ScreensManager.getPlayableScreen("CITY_SCREEN");
            cityCoordinates = s.getPlayerCoordinates();
        }
    }

    @Override
    public void write(Json json) {
        json.writeValue("LAST_ROOM", lastRoom);
        json.writeValue("MAIN_SCREEN", mainScreenCoordinates);
        json.writeValue("SECOND_SCREEN", secondScreenCoordinates);
        json.writeValue("SANDSTONE_ARENA", sandstoneArenaCoordinates);
        json.writeValue("CITY_SCREEN", cityCoordinates);

    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        lastRoom = jsonData.getString("LAST_ROOM");

        mainScreenCoordinates.x = jsonData.get("MAIN_SCREEN").getFloat("x");
        mainScreenCoordinates.y = jsonData.get("MAIN_SCREEN").getFloat("y");

        secondScreenCoordinates.x = jsonData.get("SECOND_SCREEN").getFloat("x");
        secondScreenCoordinates.y = jsonData.get("SECOND_SCREEN").getFloat("y");

        sandstoneArenaCoordinates.x = jsonData.get("SANDSTONE_ARENA").getFloat("x");
        sandstoneArenaCoordinates.y = jsonData.get("SANDSTONE_ARENA").getFloat("y");

        cityCoordinates.x = jsonData.get("CITY_SCREEN").getFloat("x");
        cityCoordinates.y = jsonData.get("CITY_SCREEN").getFloat("y");
    }

    public Vector2 getPlayerCoordinates(String screenName){
        switch (screenName) {
            case "MAIN_SCREEN" -> {
                return mainScreenCoordinates;
            }
            case "SECOND_SCREEN" -> {
                return secondScreenCoordinates;
            }
            case "SANDSTONE_ARENA" -> {
                return sandstoneArenaCoordinates;
            }
            case "CITY_SCREEN" -> {
                return cityCoordinates;
            }
        }
        return new Vector2();
    }

    public String getLastRoom() {
        return lastRoom;
    }
}
