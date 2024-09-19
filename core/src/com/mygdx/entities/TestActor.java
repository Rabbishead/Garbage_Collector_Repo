package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.Utils;
import com.mygdx.delay.DelayManager;
import com.mygdx.dialogues.DialogueLoader;
import com.mygdx.dialogues.NPCDialogue;
import com.mygdx.hitboxes.Hitbox;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.resources.ResourceEnum;

public class TestActor extends Actor {
    boolean dialogueActive = false;
    private Hitbox hitbox = new Hitbox(false, null);
    
    public TestActor(float x, float y) {
        setX(x + 100);
        setY(y + 100);
        setWidth(32);
        setHeight(32);
        setBounds(getX(), getY(), getWidth(), getHeight());
        setTouchable(Touchable.enabled);
        hitbox = new Hitbox(getX(), getY(), getWidth(), getHeight(), true, (hitbox, collider) -> {
            // this.remove();
            // Utils.getHitboxHandler().unRegisterHitbox(hitbox);
            if (dialogueActive) {
                DelayManager.updateDelay(this);
                if (DelayManager.getCurrentDelay(this) <= 0) {
                    dialogueActive = false;
                }
                return;
            }
            NPCDialogue npcDialogue = new NPCDialogue(getX() + 40, getY() + 50,
                    DialogueLoader.getLine("testNPCDialogue1"));
            getStage().addActor(npcDialogue);
            dialogueActive = true;
            DelayManager.registerObject(this, 60);
        });
        Utils.getHitboxHandler().registerHitbox(hitbox);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(Utils.getTexture(ResourceEnum.TESTACTOR), getX(), getY(), 32, 32);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (TileMapCollisionsManager.canMove(getX() + 5, getY() + 5)) {
            setX(getX() + 0.5f);
            setY(getY() + 0.5f);
        }
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        hitbox.setPosition(getX(), getY());
    }
}
