package com.mygdx.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import com.mygdx.resources.RM;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.stage.GCStage;

public class Scope extends GameActor{
    private Texture texture;

    public Scope(Vector2 coords){
        this.texture = RM.get().getTexture(ResourceEnum.SCOPE);
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
        addAction(Actions.moveTo(GCStage.get().getPlayer().getX(), GCStage.get().getPlayer().getY(), 2));

        super.act(delta);
    }
}
