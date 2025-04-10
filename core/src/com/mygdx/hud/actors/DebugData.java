package com.mygdx.hud.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.Data;

public class DebugData extends Actor implements Disposable{
    private final BitmapFont font;
    GlyphLayout g = new GlyphLayout();

    private String text = "";

    public DebugData() {
        font = new BitmapFont();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        g.setText(font, "Debug: =" + text);
        System.out.println(g.width);
        font.draw(batch, g, Data.VIEWPORT_X-128,
                getStage().getCamera().viewportHeight);
    }

    @Override
    public void dispose() {
        font.dispose();
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
