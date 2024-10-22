package com.mygdx.savings;

import com.badlogic.gdx.utils.Json;

public class SavingsManager {
    private static Json json;
    private static Settings s = new Settings();
    
    public static void save(){
        if(json == null) json = new Json();
        s.updateData();
        System.out.println(json.toJson(s));
    }
    public static void load(){
        
    }
}
