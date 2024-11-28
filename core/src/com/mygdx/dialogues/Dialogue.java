package com.mygdx.dialogues;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Dialogue extends Actor{
    protected Texture texture;
    protected String textToDisplay;
    protected BitmapFont font;

    public Dialogue(float x, float y, String textToDisplay){
        font = new BitmapFont();
        setX(x);
        setY(y);
        setWidth(2);
        setHeight(3);
        this.textToDisplay = textToDisplay;
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
    public void setTextToDisplay(String text){
        textToDisplay = text;
    }
}
