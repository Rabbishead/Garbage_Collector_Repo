package com.mygdx.hud.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class Fps extends Actor implements Disposable{
    private BitmapFont font;

    public Fps(){
        font = new BitmapFont();
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        font.draw(batch, "Upper left, FPS=" + Gdx.graphics.getFramesPerSecond(), 0, getStage().getCamera().viewportHeight);
    }
    @Override
    public void dispose() {
        font.dispose();
    }
}
