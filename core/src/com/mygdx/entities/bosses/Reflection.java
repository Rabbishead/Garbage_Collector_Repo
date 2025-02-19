package com.mygdx.entities.bosses;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.Utils;
import com.mygdx.delay.DelayManager;
import com.mygdx.entities.npcs.NPC;
import com.mygdx.resources.ResourceEnum;

public class Reflection extends NPC {

    private Action movement;

    public Reflection(NPCBuilder npcBuilder) {
        super(npcBuilder);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

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

    public static class ReflectionBuilder extends NPCBuilder{

        public ReflectionBuilder coordinates(Vector2 coordinates) {
            super.coordinates(coordinates);
            return this;
        }

        public ReflectionBuilder texture(ResourceEnum texture) {
            this.textureEnum = texture;
            return this;
        }
        public ReflectionBuilder path(String[] path) {
            this.path = path;
            return this;
        }

        public Reflection build() {
            return new Reflection(this);
        }
    }
}
