package com.mygdx.dialogues;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class DialogueLoader {
    private HashMap<String, String> loadedLines;

    public DialogueLoader(String file) {
        System.out.println("[info] loading file: " + file); // da mettere logger
        loadFile(new File(file));
    }

    private void loadFile(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while (br.ready()) {
                String line = br.readLine();
                if (line == "" || line.startsWith("#"))
                    continue;
                String[] keyVal = line.split("=");
                loadedLines.put(keyVal[0], keyVal[1]);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getLine(String key) {
        return loadedLines.get(key);
    }
}
