package com.mygdx.hud.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.mygdx.resources.RM;
import com.mygdx.resources.ResourceEnum;

public class HealthBar extends Actor {
    Texture t;

    public HealthBar() {
        t = RM.get().getTexture(ResourceEnum.HEALTH_BAR);
        setSize(t.getWidth(), t.getHeight());

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a);

        // Draw your texture
        batch.draw(t, getX(), getY(), getWidth(), getHeight());

        batch.setColor(1f, 1f, 1f, 1f);

    }
}
