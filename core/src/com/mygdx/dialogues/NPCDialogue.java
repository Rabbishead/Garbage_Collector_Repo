package com.mygdx.dialogues;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * NPC dialogues manager
 * it's an actor, so we can subscribe it to MainScreen's stage
 */
public class NPCDialogue extends Dialogue{
    
    public NPCDialogue(float x, float y, String textToDisplay){
        super(x, y, textToDisplay);
        texture = new Texture("dialogues/images/dialogueBox.jpg");

    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY(), 80, 50);
        font.draw(batch, textToDisplay, getX()+20, getY()+25);
    }
}
