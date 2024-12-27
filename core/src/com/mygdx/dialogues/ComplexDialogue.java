package com.mygdx.dialogues;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.Data;
import com.mygdx.Utils;
import com.mygdx.resources.ResourceEnum;

/**
 * Boss dialogue manager
 */
public class ComplexDialogue extends Dialogue{

    private Texture choice1;
    private String choice1Text;
    private Texture choice2;
    private String choice2Text;
    private BitmapFont font;

    public ComplexDialogue(String npcText, String choice1Text, String choice2Text){
        super(0, 0, npcText);
        texture = Utils.getTexture(ResourceEnum.COMPLEX_DIALOGUE);
        choice1 = Utils.getTexture(ResourceEnum.CHOICE);
        this.choice1Text = choice1Text;
        choice2 = Utils.getTexture(ResourceEnum.CHOICE);
        this.choice2Text = choice2Text;
        font = new BitmapFont();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(choice1, 32, 64, 410, 128);
        batch.draw(choice2, Data.VIEWPORT_X-410-32, 64, 410, 128);
        batch.draw(texture, 32, Data.VIEWPORT_Y - 128 - 128, Data.VIEWPORT_X - 64, 128);
        font.draw(batch, choice1Text, 32+32, 64+32);
        font.draw(batch, choice2Text, Data.VIEWPORT_X-410, 64+32);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public String getChoice1Text() {
        return choice1Text;
    }
    public String getChoice2Text() {
        return choice2Text;
    }
}
