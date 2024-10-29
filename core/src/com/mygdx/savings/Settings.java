package com.mygdx.savings;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.Utils;
import com.mygdx.screens.GenericScreen;
import com.mygdx.screens.ScreensManager;
import com.mygdx.screens.ScreensManager.ScreenEnum;
import com.mygdx.screens.game.arenas.SandstoneArena;
import com.mygdx.screens.game.overworld.MainScreen;
import com.mygdx.screens.game.overworld.SecondRoomTest;

public class Settings implements com.badlogic.gdx.utils.Json.Serializable{

    private String lastRoom;
    private Vector2 mainScreenCoordinates = new Vector2();
    private Vector2 secondScreenCoordinates = new Vector2();
    private Vector2 sandstoneArenaCoordinates = new Vector2();

    public void updateData(){
        lastRoom = ((GenericScreen) Utils.getScreen()).getName();
        if(!ScreensManager.isNull(ScreenEnum.MAIN_SCREEN)){
            MainScreen m = (MainScreen) ScreensManager.getScreen(ScreenEnum.MAIN_SCREEN);
            mainScreenCoordinates = m.getPlayerCoordinates();
        }
        
        if(!ScreensManager.isNull(ScreenEnum.SECOND_SCREEN)){
            SecondRoomTest s = (SecondRoomTest) ScreensManager.getScreen(ScreenEnum.SECOND_SCREEN);
            secondScreenCoordinates = s.getPlayerCoordinates();
        }
        if(!ScreensManager.isNull(ScreenEnum.SANDSTONE_ARENA)){
            SandstoneArena s = (SandstoneArena) ScreensManager.getScreen(ScreenEnum.SANDSTONE_ARENA);
            sandstoneArenaCoordinates = s.getPlayerCoordinates();
        }
    }

    @Override
    public void write(Json json) {
        json.writeValue("LAST_ROOM", lastRoom);
        json.writeValue("MAIN_SCREEN", mainScreenCoordinates);
        json.writeValue("SECOND_ROOM", secondScreenCoordinates);
        json.writeValue("SANDSTONE_ARENA", sandstoneArenaCoordinates);

    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        lastRoom = jsonData.getString("LAST_ROOM");

        mainScreenCoordinates.x = jsonData.get("MAIN_SCREEN").getFloat("x");
        mainScreenCoordinates.y = jsonData.get("MAIN_SCREEN").getFloat("y");

        secondScreenCoordinates.x = jsonData.get("SECOND_ROOM").getFloat("x");
        secondScreenCoordinates.y = jsonData.get("SECOND_ROOM").getFloat("y");

        sandstoneArenaCoordinates.x = jsonData.get("SANDSTONE_ARENA").getFloat("x");
        sandstoneArenaCoordinates.y = jsonData.get("SANDSTONE_ARENA").getFloat("y");
    }

    public Vector2 getPlayerCoordinates(String screenName){
        switch (screenName) {
            case "MAIN_SCREEN" -> {
                return mainScreenCoordinates;
            }
            case "SECOND_ROOM" -> {
                return secondScreenCoordinates;
            }
            case "SANDSTONE_ARENA" -> {
                return sandstoneArenaCoordinates;
            }
        }
        return new Vector2();
    }

    public String getLastRoom() {
        return lastRoom;
    }
}
