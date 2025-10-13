package com.mygdx.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.animations.AnimationManager;
import com.mygdx.controllers.dialogues.Dialogue;
import com.mygdx.controllers.hitboxes.Hitbox;
import com.mygdx.controllers.hitboxes.Tags;
import com.mygdx.scripts.Script;
import com.mygdx.states.StateEnum;
import com.mygdx.states.StateManager;
import com.mygdx.resources.ResourceEnum;

public class NPC extends ScriptableActor{

    protected int lf = 2;

    protected Hitbox hitbox = new Hitbox();
    public Vector2 center = new Vector2();
    public Script script;
    private String name;

    public NPC(NPCBuilder npcBuilder) {
        super();
        setTouchable(Touchable.enabled);

        setSize(npcBuilder.size.x, npcBuilder.size.y);
        setOrigin(getWidth() / 2, getHeight() / 2);

        animationManager = new AnimationManager((int)(npcBuilder.size.x), npcBuilder.textureEnum.getAnimationRate(), npcBuilder.textureEnum.getDelay(), npcBuilder.textureEnum);
        if(npcBuilder.startingAnimation != null) animationManager.setCurrentAnimation(npcBuilder.startingAnimation);

        name = npcBuilder.textureEnum.toString();
        

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
                new Dialogue(npcBuilder.story);
                StateManager.updateBoolState(StateEnum.PAUSE, true);
                return;
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


        if(npcBuilder.autoStartedScript != null) doScript(npcBuilder.autoStartedScript);
        
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

        autoMovementManager.update();
        if(!autoMovementManager.hasFinished()) animationManager.setCurrentAnimation(ResourceEnum.valueOf(name + "_" + autoMovementManager.getOrientation()));
                
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
