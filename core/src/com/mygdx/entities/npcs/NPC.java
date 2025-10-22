package com.mygdx.entities.npcs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.AnimationManager;
import com.mygdx.dialogues.Dialogue;
import com.mygdx.entities.helpers.ScriptableActor;
import com.mygdx.hitboxes.Hitbox;
import com.mygdx.hitboxes.Tags;
import com.mygdx.hud.Hud;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.scripts.Script;

public class NPC extends ScriptableActor {

    protected int lf = 2;

    protected Hitbox hitbox = new Hitbox();
    public Vector2 center = new Vector2();
    public Script script;
    protected String name;
    private Dialogue currentDialogue = null;

    public NPC(NPCBuilder npcBuilder) {
        super();
        setTouchable(Touchable.enabled);

        setSize(npcBuilder.size.x, npcBuilder.size.y);
        setOrigin(getWidth() / 2, getHeight() / 2);

        if(npcBuilder.atlas){
            animationManager = new AnimationManager(ResourceEnum.NPCS, false, npcBuilder.textureEnum);
        }
        else {
            animationManager = new AnimationManager((int) (npcBuilder.size.x), false, npcBuilder.textureEnum);
        }

        
        if (npcBuilder.startingAnimation != null)
            animationManager.setCurrentAnimation(npcBuilder.startingAnimation);

        name = npcBuilder.textureEnum.toString();

        hitbox = new Hitbox(center, npcBuilder.size.x, npcBuilder.size.y, true);
        hitbox.setTags(Tags.NPC, Tags.ENEMY);
        hitbox.setOnFrame(collider -> {
            if (!hitbox.touching(collider))
                return;
            if (!collider.containsTag(Tags.PLAYER))
                return;

            boolean leftPressed = Gdx.input.isButtonJustPressed(Input.Buttons.LEFT);

            if (leftPressed && currentDialogue == null && npcBuilder.story != null) {
                currentDialogue = new Dialogue(npcBuilder.story, this);
                Hud.stage().addActor(currentDialogue);
                return;
            }
        });
        hitbox.register();
        setPosition(npcBuilder.coordinates.x, npcBuilder.coordinates.y);

        if (npcBuilder.autoStartedScript != null)
            doScript(npcBuilder.autoStartedScript);

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

        if (currentDialogue != null)
            if (!currentDialogue.isRunning())
                currentDialogue = null;

        if (currentDialogue == null) {
            autoMovementManager.update();
            if (!autoMovementManager.hasFinished())
                animationManager
                        .setCurrentAnimation(ResourceEnum.valueOf(name + "_" + autoMovementManager.getOrientation()));

        }

        animationManager.updateAnimation(delta);
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
