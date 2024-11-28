package com.mygdx.entities.npcs;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.Utils;
import com.mygdx.delay.DelayManager;
import com.mygdx.hitboxes.Hitbox;
import com.mygdx.movement.npc.NPCRealtimeMovementStyle;
import com.mygdx.resources.ResourceEnum;

public class ComplexNPC extends GenericNPC{

    protected ComplexNPC(ComplexNPCBuilder npcBuilder) {
        super(npcBuilder);

        movementStyle = new NPCRealtimeMovementStyle(this);
        setTouchable(Touchable.enabled);

        hitbox = new Hitbox(getX(), getY(), getWidth(), getHeight(), 0, true, "enemy,npc",
            (hitbox, collider) -> {
                
            }
        );
        
        Utils.getHitboxHandler().registerHitbox(hitbox);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(animationManager.getCurrentFrame(), getX(), getY());
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.polygon(hitbox.getTransformedVertices());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        DelayManager.updateDelay(this);
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        hitbox.setPosition(getX(), getY());
    }





    public static class ComplexNPCBuilder extends GenericNPCBuilder{
    
        public ComplexNPCBuilder coordinates(Vector2 coordinates) {
            super.coordinates(coordinates);
            return this;
        }
    
        public ComplexNPCBuilder texture(ResourceEnum texture) {
            this.textureEnum = texture;
            return this;
        }
    
        public ComplexNPC build() {
            return new ComplexNPC(this);
        }
    }
}
