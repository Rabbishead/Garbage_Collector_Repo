package com.mygdx.dialogues;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * NPC dialogues manager
 * it's an actor so we can subscrbe it to MainScreen's stage
 * @see MainScreen
 */
public class NPCDialogue extends Actor {
    private Texture texture;
    private String textToDisplay;
    private final BitmapFont font;
    public NPCDialogue(float x, float y, String textToDisplay){
        font = new BitmapFont();
        setX(x);
        setY(y);
        setWidth(2);
        setHeight(3);
        texture = new Texture("dialogues/images/dialogueBox.jpg");
        this.textToDisplay = textToDisplay;
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY(), 80, 50);
        font.draw(batch, textToDisplay, getX()+20, getY()+25);
    }
    public void setTextToDisplay(String text){
        textToDisplay = text;
    }
}
