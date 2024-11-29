package com.mygdx.entities.npcs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.Data;
import com.mygdx.Utils;
import com.mygdx.delay.DelayManager;
import com.mygdx.dialogues.BossDialogue;
import com.mygdx.dialogues.DialogueLoader;
import com.mygdx.dialogues.NPCDialogue;
import com.mygdx.hitboxes.Hitbox;
import com.mygdx.movement.npc.NPCRealtimeMovementStyle;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.states.StateManager;

public class ComplexNPC extends GenericNPC{

    protected ComplexNPC(ComplexNPCBuilder npcBuilder) {
        super(npcBuilder);
        npcDialogue = new BossDialogue("");
        DelayManager.registerObject(this, 100);

        movementStyle = new NPCRealtimeMovementStyle(this);
        StateManager.updateState("complexDialogueActive", "false");

        hitbox = new Hitbox(getX(), getY(), getWidth(), getHeight(), 0, true, "enemy,npc",
            (hitbox, collider) -> {
                if (collider.containsTag("player") && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && !StateManager.getState("complexDialogueActive").equals("true") && DelayManager.isDelayOver(this)) {
                    npcDialogue = new BossDialogue(DialogueLoader.getLine("testNPCDialogue1"));
                    
                    Utils.getStage().addActor(npcDialogue);
                    StateManager.updateState("complexDialogueActive", "true");
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
        System.out.println(Utils.getPlayer().getX() + " : " + (Data.VIEWPORT_X - Utils.getPlayer().getX()));
        DelayManager.updateDelay(this);

        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && StateManager.getState("complexDialogueActive").equals("true")){
            StateManager.updateState("complexDialogueActive", "false");
            DelayManager.resetDelay(this);
            npcDialogue.remove();
        }
        if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT) && StateManager.getState("complexDialogueActive").equals("true")){
            StateManager.updateState("complexDialogueActive", "false");
            DelayManager.resetDelay(this);
            npcDialogue.remove();
        }
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
