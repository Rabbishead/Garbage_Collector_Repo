package com.mygdx.dialogues;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bladecoder.ink.runtime.Story;
import com.mygdx.Data;
import com.mygdx.Utils;
import com.mygdx.delay.DelayManager;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.states.StateManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Boss dialogue manager
 */
public class ComplexDialogue extends Actor {

    private String questionString;
    private Texture questionTexture;
    private String choice1String;
    private String choice2String;
    private Texture choiceTexture;

    private BitmapFont font;

    private Story story;

    public ComplexDialogue(String path) {
        setX(0);
        setY(0);
        questionString = "";
        choice1String = "";
        choice2String = "";
        questionTexture = Utils.getTexture(ResourceEnum.COMPLEX_DIALOGUE);
        choiceTexture = Utils.getTexture(ResourceEnum.CHOICE);

        font = new BitmapFont();

        InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream(path);
        String json = getString(systemResourceAsStream);

        try {
            story = new Story(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        continueStory();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (!choice1String.isEmpty())
            batch.draw(choiceTexture, 32, 64, 410, 128);
        if (!choice2String.isEmpty())
            batch.draw(choiceTexture, Data.VIEWPORT_X - 410 - 32, 64, 410, 128);
        batch.draw(questionTexture, 32, Data.VIEWPORT_Y - 128 - 128, Data.VIEWPORT_X - 64, 128);
        font.draw(batch, choice1String, 32 + 32, 64 + 32);
        font.draw(batch, choice2String, Data.VIEWPORT_X - 410, 64 + 32);
        font.draw(batch, questionString, 64, Data.VIEWPORT_Y - 160);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        questionString = getQuestion();
        ArrayList<String> choices = getResponses();
        if (choices.isEmpty()) {
            choice1String = "";
            choice2String = "";
        } else if (choices.size() == 1) {
            choice1String = choices.get(0);
        } else {
            choice1String = choices.get(0);
            choice2String = choices.get(1);
        }

    }

    public String getChoice1Text() {
        return choice1String;
    }

    public String getChoice2Text() {
        return choice2String;
    }

    public String getString(InputStream systemResourceAsStream) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(systemResourceAsStream), StandardCharsets.UTF_8))) {
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

    public ArrayList<String> getResponses() {
        ArrayList<String> responses = new ArrayList<>();
        story.getCurrentChoices().forEach(element -> responses.add(element.getText()));
        return responses;
    }

    public void continueStory() {
        if (story.canContinue()) {
            try {
                story.Continue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean canContinue() {
        return story.canContinue();
    }

    public void chose(int optionNumber) {
        try {

            story.chooseChoiceIndex(optionNumber);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    public int choiceNumber() {
        return story.getCurrentChoices().size();
    }

    public void manage() {
        if (StateManager.getState("pause").equals("true") && DelayManager.isDelayOver(this)) {

            int numberOfChoices = getNumberOfChoices();

            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                if (numberOfChoices > 0)
                    chose(0);

                if (canContinue())
                    continueStory();

                else {
                    StateManager.updateState("pause", "false");
                    DelayManager.resetDelay(this);
                    remove();
                }
            }
            if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
                if (numberOfChoices > 0)
                    chose(1);

                if (canContinue())
                    continueStory();

                else {
                    StateManager.updateState("pause", "false");
                    DelayManager.resetDelay(this);
                    remove();
                }
            }
        }
    }

    private int getNumberOfChoices() {
        return story.getCurrentChoices().size();
    }
}
