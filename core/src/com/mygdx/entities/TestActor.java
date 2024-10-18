package com.mygdx.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.Utils;
import com.mygdx.delay.DelayManager;
import com.mygdx.dialogues.BossDialogue;
import com.mygdx.dialogues.DialogueLoader;
import com.mygdx.dialogues.NPCDialogue;
import com.mygdx.hitboxes.Hitbox;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.resources.ResourceEnum;

public class TestActor extends Actor{
    private Hitbox hitbox = new Hitbox(false, null);
    private NPCDialogue npcDialogue = new NPCDialogue(getX() + 40, getY() + 50,
            DialogueLoader.getLine("testNPCDialogue1"));
    private BossDialogue bossDialogue;
    private Texture texture = Utils.getTexture(ResourceEnum.TESTACTOR);

    public TestActor(float x, float y) {
        setX(x + 100);
        setY(y + 100);
        setWidth(32);
        setHeight(32);
        setTouchable(Touchable.enabled);

        hitbox = new Hitbox(getX(), getY(), getWidth(), getHeight(), 0, true, (hitbox, collider) -> {
            if (collider.getTag().equals("player")) {
                npcDialogue = new NPCDialogue(getX() + 40, getY() + 50,
                DialogueLoader.getLine("testNPCDialogue1"));
                Utils.getStage().addActor(npcDialogue);
                hitbox.setActive(false);
                DelayManager.registerObject(this, 100, object -> {
                    npcDialogue.remove();
                    hitbox.setActive(true);
                    collider.setCollided(false);
                });
            } else if (collider.getTag().equals("projectile")) {
                this.remove();
                Utils.getHitboxHandler().unRegisterHitbox(hitbox);
            }
        });

        Utils.getHitboxHandler().registerHitbox(hitbox);
        this.debug();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY(), 32, 32);
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.polygon(hitbox.getTransformedVertices());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (TileMapCollisionsManager.canMove(getX() + 5, getY() + 5)) {
            setX(getX() + 0.5f);
            setY(getY() + 0.5f);
        }
        DelayManager.updateDelay(this);
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        hitbox.setPosition(getX(), getY());
        npcDialogue.setPosition(getX() + 40, getY() + 50);
    }
    
}
