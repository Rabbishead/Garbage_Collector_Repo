package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.Utils;
import com.mygdx.animations.ActorAnimationManager;
import com.mygdx.delay.DelayManager;
import com.mygdx.dialogues.DialogueLoader;
import com.mygdx.dialogues.NPCDialogue;
import com.mygdx.hitboxes.Hitbox;
import com.mygdx.movement.MovementStyle;
import com.mygdx.movement.npc.NPCRealtimeMovementStyle;
import com.mygdx.resources.ResourceEnum;

public class TestActor extends Actor {
    private ActorAnimationManager animationManager;
    private MovementStyle movementStyle;

    private Hitbox hitbox = new Hitbox(false, null);
    private NPCDialogue npcDialogue = new NPCDialogue(getX() + 40, getY() + 50,
            DialogueLoader.getLine("testNPCDialogue1"));

    public TestActor(float x, float y) {
        setX(x);
        setY(y);
        setWidth(32);
        setHeight(32);
        setTouchable(Touchable.enabled);

        hitbox = new Hitbox(getX(), getY(), getWidth(), getHeight(), 0, true, "enemy,npc",
                (hitbox, collider) -> {
                    if (collider.containsTag("player")) {
                        npcDialogue = new NPCDialogue(getX() + 40, getY() + 50,
                                DialogueLoader.getLine("testNPCDialogue1"));
                        Utils.getStage().addActor(npcDialogue);
                        hitbox.setActive(false);
                        DelayManager.registerObject(this, 100, object -> {
                            npcDialogue.remove();
                            hitbox.setActive(true);
                            collider.setCollided(false);
                        });
                    } else if (collider.containsTag("projectile")) {
                        this.remove();
                        Utils.getHitboxHandler().unRegisterHitbox(hitbox);
                    }
                });
        animationManager = new ActorAnimationManager(ResourceEnum.PLAYER);
        movementStyle = new NPCRealtimeMovementStyle(this);
        Utils.getHitboxHandler().registerHitbox(hitbox);
        this.debug();
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
        animationManager.setCurrentAnimation(movementStyle.move());
        animationManager.updateAnimation(delta);
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        hitbox.setPosition(getX(), getY());
        npcDialogue.setPosition(getX() + 40, getY() + 50);
    }

}
