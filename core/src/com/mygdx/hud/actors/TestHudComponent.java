package com.mygdx.hud.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;

public class TestHudComponent extends Actor{
    Texture t;

    public TestHudComponent(){
        t = new Texture("assets/testActor.png");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(t, 0,0, 50, 50);
    }
}
