package com.mygdx.entities.bosses;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.Utils;
import com.mygdx.delay.DelayManager;
import com.mygdx.entities.npcs.ComplexNPC;
import com.mygdx.resources.ResourceEnum;

public class Reflection extends ComplexNPC {

    private Action movement;

    public Reflection(ComplexNPCBuilder npcBuilder) {
        super(npcBuilder);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(animationManager.getCurrentFrame(), getX(), getY());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        DelayManager.updateDelay(this);

        Vector2 playerPos = Utils.getPlayer().getCoords();

        if(Utils.getPlayer().isTiledWalking()){
            movement = Actions.moveTo(playerPos.x, playerPos.y, 5);
            addAction(movement);
        }
        else{
            getActions().clear();
        }
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
    }

    public static class ReflectionBuilder extends ComplexNPCBuilder{

        public Reflection.ReflectionBuilder coordinates(Vector2 coordinates) {
            super.coordinates(coordinates);
            return this;
        }

        public Reflection.ReflectionBuilder texture(ResourceEnum texture) {
            this.textureEnum = texture;
            return this;
        }

        public Reflection build() {
            return new Reflection(this);
        }
    }
}
