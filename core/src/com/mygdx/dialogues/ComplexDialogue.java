package com.mygdx.dialogues;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.Data;
import com.mygdx.Utils;
import com.mygdx.resources.ResourceEnum;

import java.util.ArrayList;

/**
 * Boss dialogue manager
 */
public class ComplexDialogue extends Dialogue{
    private ComplexDialogueManager complexDialogueManager;
    private Texture choice1;
    private String choice1Text;
    private Texture choice2;
    private String choice2Text;
    private String question;
    private BitmapFont font;

    public ComplexDialogue(){
        super(0, 0, "");
        texture = Utils.getTexture(ResourceEnum.COMPLEX_DIALOGUE);
        choice1 = Utils.getTexture(ResourceEnum.CHOICE);
        choice2 = Utils.getTexture(ResourceEnum.CHOICE);
        font = new BitmapFont();
        choice1Text = "";
        choice2Text = "";
        complexDialogueManager = new ComplexDialogueManager();
        complexDialogueManager.continueStory();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(!choice1Text.isEmpty()) batch.draw(choice1, 32, 64, 410, 128);
        if(!choice2Text.isEmpty()) batch.draw(choice2, Data.VIEWPORT_X-410-32, 64, 410, 128);
        batch.draw(texture, 32, Data.VIEWPORT_Y - 128 - 128, Data.VIEWPORT_X - 64, 128);
        font.draw(batch, choice1Text, 32+32, 64+32);
        font.draw(batch, choice2Text, Data.VIEWPORT_X-410, 64+32);
        font.draw(batch, question, 64, Data.VIEWPORT_Y-160);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        question = complexDialogueManager.getQuestion();
        ArrayList<String> choices = complexDialogueManager.getResponses();
        if(choices.isEmpty()){
            choice1Text = "";
            choice2Text = "";
        }
        else if(choices.size() == 1){
            choice1Text = choices.get(0);
        }
        else {
            choice1Text = choices.get(0);
            choice2Text = choices.get(1);
        }

    }

    public String getChoice1Text() {
        return choice1Text;
    }
    public String getChoice2Text() {
        return choice2Text;
    }

    public boolean canContinue(){
        return complexDialogueManager.canContinue();
    }
    public void continueDialogue(){
        complexDialogueManager.continueStory();
    }
    public void chose(int optionNumber){
        complexDialogueManager.chose(optionNumber);
    }
    public int getNumberOfChoices(){
        return complexDialogueManager.choiceNumber();
    }
}
