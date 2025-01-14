package com.mygdx.entities.npcs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.Utils;
import com.mygdx.delay.DelayManager;
import com.mygdx.dialogues.ComplexDialogue;
import com.mygdx.dialogues.DialogueLoader;
import com.mygdx.hitboxes.Hitbox;
import com.mygdx.movement.npc.NPCRealtimeMovementStyle;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.states.StateManager;

public class ComplexNPC extends GenericNPC{

    protected ComplexNPC(ComplexNPCBuilder npcBuilder) {
        super(npcBuilder);
        DelayManager.registerObject(this, 100);

        movementStyle = new NPCRealtimeMovementStyle(this);

        hitbox = new Hitbox(getX(), getY(), getWidth(), getHeight(), 0, true, "enemy,npc");
        hitbox.setOnHit((hitbox, collider) -> {
            if (collider.containsTag("player") && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && StateManager.getState("pause").equals("false") && DelayManager.isDelayOver(this)) {
                Utils.getCurrentHud().addComponent(new ComplexDialogue());
                StateManager.updateState("pause", "true");
            }
        });
        
        Utils.getHitboxHandler().registerHitbox(hitbox);
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
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
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
