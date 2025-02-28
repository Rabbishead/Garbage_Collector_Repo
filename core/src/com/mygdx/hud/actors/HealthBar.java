package com.mygdx.hud.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.Utils;
import com.mygdx.resources.ResourceEnum;

public class HealthBar extends Actor{
    Texture t;

    public HealthBar(){
        t = Utils.getTexture(ResourceEnum.HEALTH_BAR);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(t, 0,0, 260, 64);
    }
}
