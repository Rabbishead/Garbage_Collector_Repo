package com.mygdx.dialogues;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class DialogueLoader {
    private static HashMap<String, String> loadedLines = new HashMap<>();

    public static void loadFile(File file) {
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
}
