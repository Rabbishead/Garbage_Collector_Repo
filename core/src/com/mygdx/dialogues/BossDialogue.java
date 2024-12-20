package com.mygdx.dialogues;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.Data;
import com.mygdx.Utils;

/**
 * Boss dialogue manager
 */
public class BossDialogue extends Dialogue{

    public BossDialogue(String textToDisplay){
        super(Data.VIEWPORT_X - Utils.getPlayer().getX(), Utils.getPlayer().getY(), textToDisplay);
        texture = new Texture("dialogues/images/bossDialogueBox.png");
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY(), (float) Data.VIEWPORT_X /2, (float) Data.VIEWPORT_Y /2);
        font.draw(batch, textToDisplay, getX()+20, getY()+25);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
