package com.mygdx.dataSave;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.badlogic.gdx.utils.Json;
import com.mygdx.Utils;
import com.mygdx.entities.Player;

public class DataSavingsManager {

    private static Json json;

    public static void saveData(){
        if(json == null) json = new Json();
        String s = json.prettyPrint(Utils.getPlayer());
        s = json.toJson(Utils.getPlayer(), Player.class);
        writeData(s);
    }

    public static void loadData(){
        if(json == null) json = new Json();
        String s = readFile();
        
        System.out.println(json.toJson(s));

    }
    private static String readFile(){
        try {
            String result = "";
            FileReader r = new FileReader("savings/savings.json");
            BufferedReader br = new BufferedReader(r);
            String s = br.readLine();
            while(s != null){
                result += s;
                s = br.readLine();
            }
            br.close();
            return result;
        } catch (Exception e) {e.printStackTrace();}
        
        return "";
    }
    private static void writeData(String s){
        try {

            FileWriter file = new FileWriter("savings/savings.json");
            file.write(s);
            file.close();

        } catch (IOException e) {e.printStackTrace();}
    }
}
