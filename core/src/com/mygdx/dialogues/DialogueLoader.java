package com.mygdx.dialogues;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class DialogueLoader {
    private static HashMap<String, String> loadedLines;
    public enum Languages{
        ITALIAN,
        ENGLISH,
        SPANISH,
        FRENCH,
        RUSSIAN
    }
    private static Languages activeLanguage;

    public static void loadFile() {
        File file = null;
        switch (activeLanguage) {
            case ITALIAN -> { file = new File("assets/dialogues/lang/dialoguesIta.txt"); }
            case ENGLISH -> { file = new File("assets/dialogues/lang/dialoguesEng.txt"); }
        }

        loadedLines = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while (br.ready()) {
                String line = br.readLine();
                if (line == "" || line.startsWith("#"))
                    continue;
                String[] keyVal = line.split("=");
                loadedLines.put(keyVal[0].trim(), keyVal[1].trim());
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getLine(String key) {
        return loadedLines.get(key);
    }

    public static void setLang(Languages language){
        activeLanguage = language;
    }
    public static Languages getActiveLanguage() {
        return activeLanguage;
    }

}