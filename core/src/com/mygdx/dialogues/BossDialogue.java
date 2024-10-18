package com.mygdx.dialogues;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.Data;

/**
 * Boss dialogue manager
 */
public class BossDialogue extends Actor{
    private Texture texture;
    private String textToDisplay;
    private final BitmapFont font;
    public BossDialogue(String textToDisplay){
        font = new BitmapFont();
        setX(0);
        setY(0);
        setWidth(2);
        setHeight(3);
        texture = new Texture("dialogues/images/bossDialogueBox.png");
        this.textToDisplay = textToDisplay;
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY(), Data.VIEWPORT_X/2, Data.VIEWPORT_Y/2);
        font.draw(batch, textToDisplay, getX()+20, getY()+25);
    }
    public void setTextToDisplay(String text){
        textToDisplay = text;
    }
}
