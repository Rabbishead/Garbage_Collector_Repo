package com.mygdx.entities.npcs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.Utils;
import com.mygdx.animations.ActorAnimationManager;
import com.mygdx.delay.DelayManager;
import com.mygdx.dialogues.ComplexDialogue;
import com.mygdx.dialogues.DialogueLoader;
import com.mygdx.dialogues.NPCDialogue;
import com.mygdx.entities.GameActor;
import com.mygdx.hitboxes.Hitbox;
import com.mygdx.messages.MsgManager;
import com.mygdx.movement.MovementStyle;
import com.mygdx.movement.npc.NPCRealtimeMovementStyle;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.states.StateManager;

public class NPC extends GameActor{

    protected int lf = 100;

    protected ActorAnimationManager animationManager;
    protected MovementStyle movementStyle;
    protected NPCDialogue npcDialogue;
    protected Hitbox hitbox = new Hitbox();
    protected boolean smallDialogueGoing;


    public NPC(NPCBuilder npcBuilder) {
        super();
        setX(npcBuilder.coordinates.x);
        setY(npcBuilder.coordinates.y);

        setWidth(32);
        setHeight(32);

        animationManager = new ActorAnimationManager(npcBuilder.textureEnum);
        
        this.debug();

        setTouchable(Touchable.enabled);
        

        npcDialogue = new NPCDialogue(0, 0, "");
        DelayManager.registerObject(npcDialogue, 0);
        DelayManager.registerObject(this, 0);

        String[] path = npcBuilder.path;
        movementStyle = new NPCRealtimeMovementStyle(this, path);

        smallDialogueGoing = false;

        hitbox = new Hitbox(getX(), getY(), 16, 16, 0, true, "enemy,npc");
        hitbox.setOnHit((hitbox, collider) -> {
            if (collider.containsTag("player") && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && StateManager.getState("pause").equals("false") && DelayManager.isDelayOver(this)) {
                Utils.getCurrentHud().addComponent(new ComplexDialogue());
                StateManager.updateState("pause", "true");
                return;
            }
            if (collider.containsTag("player") && !smallDialogueGoing) {
                npcDialogue = new NPCDialogue(getX() + 40, getY() + 50,
                        DialogueLoader.getLine("testNPCDialogue1"));
                Utils.getStage().addActor(npcDialogue);
                smallDialogueGoing = true;
                DelayManager.registerObject(npcDialogue, 100, object -> {
                    npcDialogue.remove();
                    smallDialogueGoing = false;
                    collider.setCollided(false);
                });
            } else if (collider.containsTag("projectile")) {
                if (lf <= 0) {
                    this.remove();
                    Utils.getHitboxHandler().unRegisterHitbox(hitbox);
                } else
                    lf--;
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
        animationManager.setCurrentAnimation(movementStyle.move());
        animationManager.updateAnimation(delta);
        npcDialogue.setPosition(getX() + 40, getY() + 50);
        DelayManager.updateDelay(this);
        DelayManager.updateDelay(npcDialogue);
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        hitbox.setPosition(getX(), getY());
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.polygon(hitbox.getTransformedVertices());
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        System.out.println("Handled!!");
        return true;
    }


    public static class NPCBuilder{
        protected Vector2 coordinates;
        protected ResourceEnum textureEnum;
        protected String[] path;
    
        public NPCBuilder coordinates(Vector2 coordinates) {
            this.coordinates = coordinates;
            return this;
        }
    
        public NPCBuilder texture(ResourceEnum texture) {
            this.textureEnum = texture;
            return this;
        }
        public NPCBuilder path(String[] path) {
            this.path = path;
            return this;
        }
    
        public NPC build() {
            return new NPC(this);
        }
    }
}
