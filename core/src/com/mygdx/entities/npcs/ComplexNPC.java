package com.mygdx.entities.npcs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.Utils;
import com.mygdx.delay.DelayManager;
import com.mygdx.dialogues.BossDialogue;
import com.mygdx.dialogues.DialogueLoader;
import com.mygdx.dialogues.NPCDialogue;
import com.mygdx.hitboxes.Hitbox;
import com.mygdx.movement.npc.NPCRealtimeMovementStyle;
import com.mygdx.resources.ResourceEnum;

public class ComplexNPC extends GenericNPC{

    private boolean interacting = false;
    private boolean speaking = false;

    protected ComplexNPC(ComplexNPCBuilder npcBuilder) {
        super(npcBuilder);
        npcDialogue = new BossDialogue("");

        movementStyle = new NPCRealtimeMovementStyle(this);

        hitbox = new Hitbox(getX(), getY(), getWidth(), getHeight(), 0, true, "enemy,npc",
            (hitbox, collider) -> {
                if (collider.containsTag("player") && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                    npcDialogue = new BossDialogue(DialogueLoader.getLine("testNPCDialogue1"));
                    Utils.getStage().addActor(npcDialogue);
                }
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

        npcDialogue.setPosition(getX() + 40, getY() + 50);
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
