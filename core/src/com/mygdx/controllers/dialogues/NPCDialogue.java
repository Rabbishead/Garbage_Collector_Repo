package com.mygdx.controllers.dialogues;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.Utils;
import com.mygdx.resources.ResourceEnum;

/**
 * NPC dialogues manager
 * it's an actor, so we can subscribe it to MainScreen's stage
 */
public class NPCDialogue extends Actor {

    private String textToDisplay;
    private Texture texture;
    private BitmapFont font;

    public NPCDialogue(float x, float y, String textToDisplay) {
        setX(x);
        setY(y);
        texture = Utils.getTexture(ResourceEnum.SIMPLE_DIALOGUE);
        font = new BitmapFont();
        this.textToDisplay = textToDisplay;

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY(), 80, 50);
        font.draw(batch, textToDisplay, getX() + 20, getY() + 25);
    }

    public void setTextToDisplay(String textToDisplay) {
        this.textToDisplay = textToDisplay;
    }
}
