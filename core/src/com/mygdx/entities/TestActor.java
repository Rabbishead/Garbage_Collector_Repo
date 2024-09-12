package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.Utils;
import com.mygdx.animations.PlayerAnimationManager;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.resources.ResourceEnum;

public class TestActor extends Actor {
    public TestActor(float x, float y){
        setX(x);
        setY(y);

        setWidth(32);
        setBounds(getX(), getY(), getWidth(), getHeight());
        setTouchable(Touchable.enabled);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(Utils.getTexture(ResourceEnum.TESTACTOR), getX(), getY(), 32, 32);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(TileMapCollisionsManager.canMove(getX()+5, getY() + 5)){
            setX(getX()+1);
            setY(getY()+1);
        }


    }
}
