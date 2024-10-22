package com.mygdx.savings;

import java.io.FileWriter;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter.OutputType;

public class SavingsManager {
    private static Json json;
    private static Settings s;
    
    public static void save(){
        if(json == null) {
            json = new Json();
            json.setOutputType(OutputType.json);
            s = new Settings();
        }
        s.updateData();
        writeFile(json.toJson(s));
    }
    public static void load(){
        if(json == null){
            json = new Json();
            json.setOutputType(OutputType.json);
            s = new Settings();
        } 
        FileHandle file = Gdx.files.local("savings/savings.json");
        String jsonFile = file.readString();
        s = json.fromJson(Settings.class, jsonFile);
    }

    private static void writeFile(String s){
        try {
            FileWriter myWriter = new FileWriter("savings/savings.json");
            myWriter.write(s);
            myWriter.close();
        } catch (IOException e) {e.printStackTrace();}
    }
}
