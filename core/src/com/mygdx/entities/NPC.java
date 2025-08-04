package com.mygdx.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.animations.ActorAnimationManager;
import com.mygdx.controllers.delay.DelayManager;
import com.mygdx.controllers.dialogues.ComplexDialogue;
import com.mygdx.controllers.dialogues.NPCDialogue;
import com.mygdx.controllers.hitboxes.Hitbox;
import com.mygdx.controllers.hitboxes.Tags;
import com.mygdx.movement.MovementStyle;
import com.mygdx.movement.npc.NPCRoamingMovementStyle;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.scripts.Script;
import com.mygdx.states.StateEnum;
import com.mygdx.states.StateManager;

public class NPC extends ScriptableActor{

    protected int lf = 2;

    protected ActorAnimationManager animationManager;
    protected MovementStyle movementStyle;
    protected NPCDialogue npcDialogue;
    protected Hitbox hitbox = new Hitbox();
    protected boolean smallDialogueGoing;
    public Vector2 center = new Vector2();
    public Script script;

    public NPC(NPCBuilder npcBuilder) {
        super();
        setTouchable(Touchable.enabled);

        setSize(npcBuilder.size.x, npcBuilder.size.y);
        setOrigin(getWidth() / 2, getHeight() / 2);

        animationManager = new ActorAnimationManager(npcBuilder.textureEnum);
        npcDialogue = new NPCDialogue(0, 0, "");
        DelayManager.registerObject(npcDialogue, 0f);
        
        String[] path = npcBuilder.path;
        movementStyle = new NPCRoamingMovementStyle(this, path);
        smallDialogueGoing = false;

        hitbox = new Hitbox(center, npcBuilder.size.x, npcBuilder.size.y, true);
        hitbox.setTags(Tags.NPC, Tags.ENEMY);
        hitbox.setOnFrame(collider -> {
            if (!hitbox.touching(collider))
                return;
            if (!collider.containsTag(Tags.PLAYER))
                return;

            boolean leftPressed = Gdx.input.isButtonJustPressed(Input.Buttons.LEFT);
            boolean inPause = StateManager.getBoolState(StateEnum.PAUSE);
            
            if (leftPressed && !inPause && npcBuilder.story != null) {
                new ComplexDialogue(npcBuilder.story);
                StateManager.updateBoolState(StateEnum.PAUSE, true);
                return;
            }
            if (!smallDialogueGoing) {
                /*
                 * npcDialogue = new NPCDialogue(getX() + 40, getY() + 50,
                 * DialogueLoader.getLine("testNPCDialogue1"));
                 * Utils.getStage().addActor(npcDialogue);
                 * smallDialogueGoing = true;
                 * DelayManager.registerObject(npcDialogue, 100, object -> {
                 * npcDialogue.remove();
                 * smallDialogueGoing = false;
                 * collider.setCollided(false);
                 * 
                 * });
                 */
            }
        });
        hitbox.setOnHit(collider -> {
            if (collider.containsTag(Tags.PROJECTILE)) {
                Integer dmg = collider.getExtraInfo().getIntegerInfo("damage");
                if (dmg != null)
                    lf -= dmg;
                else
                    System.out.println("no damage value");

                if (lf <= 0) {
                    this.remove();
                    hitbox.unregister();
                }
            }
        });
        hitbox.register();
        setPosition(npcBuilder.coordinates.x, npcBuilder.coordinates.y);

        debug();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(animationManager.getCurrentFrame(), getX(), getY());
    }

    @Override
    public void act(float delta) {
        super.act(delta);


        animationManager.setCurrentAnimation(
                autoMovementManager.update() ? autoMovementManager.getOrientation() : movementStyle.move());
                
        animationManager.updateAnimation(delta);
        
        //npcDialogue.setPosition(getX() + 40, getY() + 50);
        DelayManager.updateDelay(npcDialogue);
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        center.x = getX() + getOriginX();
        center.y = getY() + getOriginY();
        hitbox.setPosition();
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.polygon(hitbox.getTransformedVertices());
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        return true;
    }

    @Override
    public void changeAnimation(ResourceEnum e) {
        //CAMBIO ANIMAZIONE
    }

    @Override
    public void wait(float time) {
        //ASPETTARE
    }

    public Vector2 getCoords() {
        return new Vector2(getX(), getY());
    }

    public static class NPCBuilder extends AbstractNPCBuilder<NPCBuilder> {

        public NPC build() {
            return new NPC(this);
        }

        @Override
        public NPCBuilder getThis() {
            return this;
        }
    }

}
