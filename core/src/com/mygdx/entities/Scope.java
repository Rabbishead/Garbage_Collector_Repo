package com.mygdx.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.Utils;
import com.mygdx.resources.ResourceEnum;

public class Scope extends GameActor{
    private Texture texture;

    public Scope(Vector2 coords){
        this.texture = Utils.getTexture(ResourceEnum.SCOPE);
        setCoords(coords);
    }
    public Scope(float x, float y){
        this(new Vector2(x, y));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getCoords().x, getCoords().y);
    }

    @Override
    public void act(float delta) {
        addAction(Actions.moveTo(Utils.getPlayer().getX(), Utils.getPlayer().getY(), 2));

        super.act(delta);
    }
}
