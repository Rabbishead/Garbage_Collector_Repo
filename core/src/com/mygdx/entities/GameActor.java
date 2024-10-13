package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.utils.Disposable;

public class GameActor extends Actor implements Disposable{
    protected Sprite sprite;
    public Vector2 center = new Vector2();

    protected GameActor(int x, int y){
        setX(x + 16);
        setY(y + 16);
        setOrigin(getWidth() / 2, getHeight() / 2);
        
        center.x = getX() + getOriginX();
        center.y = getY() + getOriginY();
    }
    protected GameActor(int x, int y, int width, int height){
        setX(x + 16);
        setY(y + 16);
        setWidth(width);
        setHeight(width);
        setOrigin(getWidth() / 2, getHeight() / 2);

        center.x = getX() + getOriginX();
        center.y = getY() + getOriginY();
    }
    @Override
    public void dispose() {
    }
}
