package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.Utils;
import com.mygdx.resources.ResourceEnum;

public class Stone extends Actor {
    public Stone(Vector2 v2, int x, int y) {
        Vector2 newv2 = new Vector2(x, y);
        newv2.setAngleDeg(v2.angleDeg(newv2));
        newv2.scl(8);
        this.moveBy(x, y);
        setX(x + 16);
        setY(y + 16);

        setWidth(8);
        setBounds(getX(), getY(), getWidth(), getHeight());
        setTouchable(Touchable.enabled);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(Utils.getTexture(ResourceEnum.STONE), getX(), getY());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
    }
}
