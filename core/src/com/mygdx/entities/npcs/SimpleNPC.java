package com.mygdx.entities.npcs;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.Utils;
import com.mygdx.delay.DelayManager;
import com.mygdx.dialogues.DialogueLoader;
import com.mygdx.dialogues.NPCDialogue;
import com.mygdx.hitboxes.Hitbox;
import com.mygdx.movement.npc.NPCRealtimeMovementStyle;
import com.mygdx.resources.ResourceEnum;

public class SimpleNPC extends GenericNPC {

    int lf = 100;

    protected SimpleNPC(SimpleNPCBuilder npcBuilder) {
        super(npcBuilder);
        npcDialogue = new NPCDialogue(0, 0, "");

        String[] path = npcBuilder.path;
        movementStyle = new NPCRealtimeMovementStyle(this, path);

        hitbox = new Hitbox(getX(), getY(), 16, 16, 0, true, "enemy,npc");
        hitbox.setOnHit((hitbox, collider) -> {
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
        DelayManager.updateDelay(this);
        npcDialogue.setPosition(getX() + 40, getY() + 50);
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();

    }

    public static class SimpleNPCBuilder extends GenericNPCBuilder {

        private String[] path;

        public SimpleNPCBuilder coordinates(Vector2 coordinates) {
            this.coordinates = coordinates;
            return this;
        }

        public SimpleNPCBuilder texture(ResourceEnum texture) {
            this.textureEnum = texture;
            return this;
        }

        public SimpleNPCBuilder path(String[] path) {
            this.path = path;
            return this;
        }

        public SimpleNPC build() {
            return new SimpleNPC(this);
        }
    }
}
