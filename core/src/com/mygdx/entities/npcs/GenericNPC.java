package com.mygdx.entities.npcs;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.animations.ActorAnimationManager;
import com.mygdx.dialogues.Dialogue;
import com.mygdx.hitboxes.Hitbox;
import com.mygdx.movement.MovementStyle;
import com.mygdx.resources.ResourceEnum;

public class GenericNPC extends Actor{
    protected ActorAnimationManager animationManager;
    protected MovementStyle movementStyle;
    protected Dialogue npcDialogue;

    protected Hitbox hitbox = new Hitbox(false, null);

    protected GenericNPC(GenericNPCBuilder npcBuilder) {
        super();
        setX(npcBuilder.coordinates.x);
        setY(npcBuilder.coordinates.y);

        setWidth(32);
        setHeight(32);

        animationManager = new ActorAnimationManager(npcBuilder.textureEnum);
        
        this.debug();

        setTouchable(Touchable.enabled);
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
        animationManager.setCurrentAnimation(movementStyle.move());
        animationManager.updateAnimation(delta);
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        hitbox.setPosition(getX(), getY());
    }





    public static abstract class GenericNPCBuilder {
        protected Vector2 coordinates;
        protected ResourceEnum textureEnum;
    
        public GenericNPCBuilder coordinates(Vector2 coordinates) {
            this.coordinates = coordinates;
            return this;
        }
    
        public GenericNPCBuilder texture(ResourceEnum texture) {
            this.textureEnum = texture;
            return this;
        }
    
        public GenericNPC build() {
            return new GenericNPC(this);
        }
    }
}
