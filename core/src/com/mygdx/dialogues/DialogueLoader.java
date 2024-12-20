package com.mygdx.dialogues;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class DialogueLoader {
    
    /**
     * Contains all the dialogue lines of the selected language
     */
    private static HashMap<String, String> loadedLines;

    /**
     * all the supported languages
     */
    public enum Languages{
        ITALIAN,
        ENGLISH,
        SPANISH,
        FRENCH,
        RUSSIAN
    }

    /*
     * the selected language
     */
    private static Languages activeLanguage;

    /**
     * loads the .txt file corresponding to the selected language
     */
    public static void loadFile() {
        File file = null;
        switch (activeLanguage) {
            case ITALIAN -> { file = new File("assets/dialogues/lang/dialoguesIta.txt"); }
            case ENGLISH -> { file = new File("assets/dialogues/lang/dialoguesEng.txt"); }
            case FRENCH -> {}
            case SPANISH -> {}
            case RUSSIAN -> {}
        }

        loadedLines = new HashMap<>();
        try {
            assert file != null;
            BufferedReader br = new BufferedReader(new FileReader(file));
            while (br.ready()) {
                String line = br.readLine();
                if (line.isEmpty() || line.startsWith("#"))
                    continue;
                String[] keyVal = line.split("=");
                loadedLines.put(keyVal[0].trim(), keyVal[1].trim());
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the dialogue associated with key
     */
    public static String getLine(String key) {
        return loadedLines.get(key);
    }

    /**
     * sets the active language
     */
    public static void setLang(Languages language){
        activeLanguage = language;
    }
}