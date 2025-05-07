package com.mygdx.controllers.dialogues;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bladecoder.ink.runtime.Story;
import com.mygdx.Data;
import com.mygdx.Utils;
import com.mygdx.controllers.delay.DelayManager;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.states.StateEnum;
import com.mygdx.states.StateManager;
import java.util.ArrayList;

public class ComplexDialogue extends Actor {

    private String questionString;
    private Texture questionTexture;
    private String choice1String;
    private String choice2String;
    private Texture choiceTexture;

    private BitmapFont font;

    private Story story;

    private final int CHOICE_WIDTH = 410, CHOICE_HEIGHT = 128;

    public ComplexDialogue(Story story) {
        setX(0);
        setY(0);
        questionString = "";
        choice1String = "";
        choice2String = "";
        questionTexture = Utils.getTexture(ResourceEnum.COMPLEX_DIALOGUE);
        choiceTexture = Utils.getTexture(ResourceEnum.CHOICE);
        this.story = story;
        font = new BitmapFont();
        continueStory();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (!choice1String.isEmpty()) {
            batch.draw(choiceTexture, 32, 64, CHOICE_WIDTH, CHOICE_HEIGHT);
            font.draw(batch, choice1String, 64, 96);
        }
        if (!choice2String.isEmpty()) {
            batch.draw(choiceTexture, Data.VIEWPORT_X - CHOICE_WIDTH - 32, 64, CHOICE_WIDTH, CHOICE_HEIGHT);
            font.draw(batch, choice2String, Data.VIEWPORT_X - CHOICE_WIDTH, 96);
        }
        batch.draw(questionTexture, 32, Data.VIEWPORT_Y - 256, Data.VIEWPORT_X - 64, CHOICE_HEIGHT);
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

            e.printStackTrace();
        }
    }

    public int choiceNumber() {
        return story.getCurrentChoices().size();
    }

    public void manage() {
        if (!StateManager.getBoolState(StateEnum.PAUSE) || !DelayManager.isDelayOver(this))
            return;

        int numberOfChoices = getNumberOfChoices();

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if (numberOfChoices > 0)
                chose(0);

            if (canContinue())
                continueStory();

            else {
                removeDialogue();
            }
        }
        if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
            if (numberOfChoices > 0)
                chose(1);

            if (canContinue())
                continueStory();

            else {
                removeDialogue();
            }
        }
    }

    private int getNumberOfChoices() {
        return story.getCurrentChoices().size();
    }

    private void removeDialogue(){
        StateManager.updateBoolState(StateEnum.PAUSE, false);
        DelayManager.resetDelay(this);
        remove();
    }
}
