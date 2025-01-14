package com.mygdx.dialogues;

import com.bladecoder.ink.runtime.Story;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public class ComplexDialogueManager {
    private Story story;

    public ComplexDialogueManager(){
        InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream("dialogues/complexTest.json");
        String json = getString(systemResourceAsStream);

        try {
            story = new Story(json);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getString(InputStream systemResourceAsStream) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(systemResourceAsStream), StandardCharsets.UTF_8))){
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString().replace('\uFEFF', ' ');
    }
    public String getQuestion() {
        try {
            return story.getCurrentText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public ArrayList<String> getResponses(){
        ArrayList<String> responses = new ArrayList<>();
        story.getCurrentChoices().forEach(element -> responses.add(element.getText()));
        return responses;
    }

    public void continueStory(){
        if(story.canContinue()) {
            try {
                story.Continue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public boolean canContinue(){
        return story.canContinue();
    }

    public void chose(int optionNumber){
        try {

            story.chooseChoiceIndex(optionNumber);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
    public int choiceNumber(){
        return story.getCurrentChoices().size();
    }
}
